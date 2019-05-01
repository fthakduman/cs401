	package org.exchangerate.com.outerws;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ProcessData {
	WSData data= null;
	JSONObject output= null;
	JSONObject config = null;
	HashMap<String, String> elements = new HashMap<String, String>();
	HashMap<String, BigInteger> faultmodel = new HashMap<String, BigInteger>();
	public ProcessData(JSONObject config){
		  
	        this.config = config;
	}
	
	public JSONObject getMockRates(){
		data = new WSData();
		data.makeRequest();
		setKeys();
		JSONObject rates = data.getRequestResponse();
		setOutput(rates);
		System.out.println(output.toString());
		return output;
		
		}
	private void setKeys(){
		elements.put("sell", config.getJSONObject("elements").getString("sell"));
		elements.put("buy", config.getJSONObject("elements").getString("buy"));
		elements.put("minor", config.getJSONObject("elements").getString("minor"));
		elements.put("major", config.getJSONObject("elements").getString("major"));
		elements.put("minorChoice", config.getJSONObject("elements").getString("minorChoice"));
		elements.put("majorChoice", config.getJSONObject("elements").getString("majorChoice"));
		
		faultmodel.put("delayprobability", config.getJSONObject("faultmodel").getJSONObject("delay").getBigInteger("probability"));
		faultmodel.put("delayamount", config.getJSONObject("faultmodel").getJSONObject("delay").getBigInteger("amount"));
		////// sellfault
		faultmodel.put("spositiveProb", config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("positiveProb"));
		faultmodel.put("spositiveRange", config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("positiveRange"));
		faultmodel.put("snegativeProb", config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("negativeProb"));
		faultmodel.put("snegativeRange", config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("negativeRange"));
		/////////buyfault
		faultmodel.put("bpositiveProb", config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("positiveProb"));
		faultmodel.put("bpositiveRange", config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("positiveRange"));
		faultmodel.put("bnegativeProb", config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("negativeProb"));
		faultmodel.put("bnegativeRange", config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("negativeRange"));
	}
	private void setOutput(JSONObject rates){
		JSONArray array = rates.getJSONObject("response").getJSONArray("exchangeRateList");
		JSONObject temp = null;
		JSONObject object = new JSONObject();
		for (int i=0; i<array.length(); i++) {
			temp = array.getJSONObject(i);
		    if(temp.get("minorCurrency").equals(elements.get("minorChoice")) && temp.get("majorCurrency").equals(elements.get("majorChoice"))){
		    	
		    	String manipulatedSell = manipulateSell( (String) temp.get(elements.get("sell")));
		    	temp.put(elements.get("sell"), manipulatedSell);
		    	
		    	String manipulatedBuy = manipulateBuy( (String) temp.get(elements.get("buy")));
		    	temp.put(elements.get("buy"),manipulatedBuy );
		    	array.put(i, temp);
		    	
		    	output = rates.put("response", array);
		    
		    }
		    
		}
		
		//output = temp;
		
		try {
			System.out.println("delay operation has started");
			manipulateDelay();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void manipulateDelay() throws InterruptedException{
		if( Math.random() <= (faultmodel.get("delayprobability").doubleValue()/100)) {
			TimeUnit.MILLISECONDS.sleep((long) faultmodel.get("delayamount").doubleValue());
			}
	}
	private String manipulateSell(String value){
		Random rand = new Random();
		double result = Double.parseDouble(value);
		if( Math.random() <= (faultmodel.get("spositiveProb").doubleValue()/100)) {
			  int positive = rand.nextInt(faultmodel.get("spositiveRange").intValue());
			  result = result + (result*positive/100);
			}
		if( Math.random() <= (faultmodel.get("snegativeProb").doubleValue()/100)) {
			  int negative = rand.nextInt(faultmodel.get("snegativeRange").intValue());
			  result = result - (result*negative/100);
			}
		return Double.toString(result);
	}
	private String manipulateBuy(String value){
		Random rand = new Random();
		double result = Double.parseDouble(value);
		if( Math.random() <= (faultmodel.get("bpositiveProb").doubleValue()/100)) {
			  int positive = rand.nextInt(faultmodel.get("bpositiveRange").intValue());
			  result = result + (result*positive/100);
			}
		if( Math.random() <= (faultmodel.get("bnegativeProb").doubleValue()/100)) {
			  int negative = rand.nextInt(faultmodel.get("bnegativeRange").intValue());
			  result = result - (result*negative/100);
			}
		return Double.toString(result);
	}
	
}
