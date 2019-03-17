package org.exchangerate.com.outerws;

import java.math.BigInteger;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessData {
	WSData data= null;
	JSONObject output= null;
	JSONObject config = null;
	HashMap<String, String> elements = new HashMap<String, String>();
	HashMap<String, BigInteger> changes = new HashMap<String, BigInteger>();
	public ProcessData(JSONObject config){
		this.config = config;
	}
	
	public void process(){
		data = new WSData();
		data.makeRequest();
		setKeys();
		JSONObject rates = data.getRequestResponse();
		setOutput(rates);
		System.out.println(output.toString());
		
		}
	private void setKeys(){
		elements.put("sell", config.getJSONObject("elements").getString("sell"));
		elements.put("buy", config.getJSONObject("elements").getString("buy"));
		elements.put("minor", config.getJSONObject("elements").getString("minor"));
		elements.put("major", config.getJSONObject("elements").getString("major"));
		elements.put("minorChoice", config.getJSONObject("elements").getString("minorChoice"));
		elements.put("majorChoice", config.getJSONObject("elements").getString("majorChoice"));
		
		changes.put("sellChange", config.getJSONObject("changes").getBigInteger("sellChange"));
		changes.put("buyChange", config.getJSONObject("changes").getBigInteger("buyChange"));
		changes.put("swapCurrency", config.getJSONObject("changes").getBigInteger("swapCurrency"));
	}
	private void setOutput(JSONObject rates){
		JSONArray array = rates.getJSONObject("response").getJSONArray("exchangeRateList");
		JSONObject object = new JSONObject();
		for (int i=0; i<array.length(); i++) {
			JSONObject temp = array.getJSONObject(i);
		    if(temp.get("minorCurrency").equals(elements.get("minorChoice")) && temp.get("majorCurrency").equals(elements.get("majorChoice"))){
		    	JSONObject temp2 = new JSONObject();
		    	temp2.put("sell", temp.get(elements.get("sell")));
		    	object = temp2;
		    }
		    
		}
		
		output = object;
		
	}
}
