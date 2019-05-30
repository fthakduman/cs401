package com.mock.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ProcessData {
	WSData data = null;
	JSONObject output = null;
	JSONObject config = null;
	HashMap<String, String> elements = new HashMap<String, String>();
	HashMap<String, BigInteger> faultmodel = new HashMap<String, BigInteger>();
	Bank bank = null;

	public ProcessData() {
		this.bank = new Bank();
	}
	
	public ProcessData(JSONObject config) {
		this.config = config;
		this.bank = new Bank();
	}

	public Bank getMockRates(String bankName, boolean isYkb, boolean isTest) {
		if (!"YKB".equals(bankName)) {
			data = new WSData();
			data.makeRequest();
			setKeys();
			bank.setName(bankName);
			JSONObject rates = data.getRequestResponse();
			setOutput(rates, isYkb, isTest, bankName);
			return bank;
		} else {
			data = new WSData();
			data.makeRequest();
			bank.setName(bankName);
			JSONObject rates = data.getRequestResponse();
			setOutput(rates, isYkb, isTest, bankName);
			return bank;
		}
	}

	private void setKeys() {
		elements.put("sell", config.getJSONObject("elements").getString("sell"));
		elements.put("buy", config.getJSONObject("elements").getString("buy"));
		elements.put("minor", config.getJSONObject("elements").getString("minor"));
		elements.put("major", config.getJSONObject("elements").getString("major"));
		elements.put("minorChoice", config.getJSONObject("elements").getString("minorChoice"));
		elements.put("majorChoice", config.getJSONObject("elements").getString("majorChoice"));

		faultmodel.put("delayprobability",
				config.getJSONObject("faultmodel").getJSONObject("delay").getBigInteger("probability"));
		faultmodel.put("delayamount",
				config.getJSONObject("faultmodel").getJSONObject("delay").getBigInteger("amount"));
		////// sellfault
		faultmodel.put("spositiveProb",
				config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("positiveProb"));
		faultmodel.put("spositiveRange",
				config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("positiveRange"));
		faultmodel.put("snegativeProb",
				config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("negativeProb"));
		faultmodel.put("snegativeRange",
				config.getJSONObject("faultmodel").getJSONObject("sellfault").getBigInteger("negativeRange"));
		///////// buyfault
		faultmodel.put("bpositiveProb",
				config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("positiveProb"));
		faultmodel.put("bpositiveRange",
				config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("positiveRange"));
		faultmodel.put("bnegativeProb",
				config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("negativeProb"));
		faultmodel.put("bnegativeRange",
				config.getJSONObject("faultmodel").getJSONObject("buyfault").getBigInteger("negativeRange"));
	}

	private void setOutput(JSONObject rates, boolean isYkb, boolean isTest, String bankName) {
		JSONArray array = rates.getJSONObject("response").getJSONArray("exchangeRateList");
		JSONObject temp = null;
		for (int i = 0; i < array.length(); i++) {
			temp = array.getJSONObject(i);
			if (temp.get("minorCurrency").equals(elements.get("minorChoice"))
					&& temp.get("majorCurrency").equals(elements.get("majorChoice"))) {
				if (isYkb) {
					// simpleOutput.put(elements.get("sell"), (String)
					// temp.get(elements.get("sell")));
					// simpleOutput.put(elements.get("buy"), (String)
					// temp.get(elements.get("buy")));

					array.put(i, temp);
					output = rates.put("response", array);

				} else {
					String manipulatedBuy = manipulateBuy((String) temp.get(elements.get("buy")));
					temp.put(elements.get("buy"), manipulatedBuy);
					// simpleOutput.put(elements.get("buy"), manipulatedBuy);

					String manipulatedSell = manipulateSell((String) temp.get(elements.get("sell")), manipulatedBuy,
							isTest);
					temp.put(elements.get("sell"), manipulatedSell);
					// simpleOutput.put(elements.get("sell"), manipulatedSell);

					array.put(i, temp);
					output = rates.put("response", array);
				}
			}
			Gson gson = new Gson();
			Conversion obj = gson.fromJson(temp.toString(), Conversion.class);
			bank.getMap().put(i + "", obj);
		}
		try {
			if(!isYkb)
			manipulateDelay();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void manipulateDelay() throws InterruptedException {
		if (Math.random() <= (faultmodel.get("delayprobability").doubleValue() / 100)) {
			TimeUnit.MILLISECONDS.sleep((long) faultmodel.get("delayamount").doubleValue());
		}
	}

	private String manipulateSell(String value, String buyRate, boolean isTest) {
		Random rand = new Random();
		double sellRate = Double.parseDouble(value);
		if (Math.random() <= (faultmodel.get("spositiveProb").doubleValue() / 100)) {
			int positive = rand.nextInt(faultmodel.get("spositiveRange").intValue());
			sellRate = sellRate + (sellRate * positive / 100);
		}
		if (Math.random() <= (faultmodel.get("snegativeProb").doubleValue() / 100)) {
			int negative = rand.nextInt(faultmodel.get("snegativeRange").intValue());
			sellRate = sellRate - (sellRate * negative / 100);
		}
		double buyRateDouble = Double.parseDouble(buyRate);
		if (!isTest) {
			if (sellRate - buyRateDouble > 0) {
				return Double.toString(sellRate);
			} else {
				double increase = Math.random();
				return Double.toString(buyRateDouble + increase);
			}
		} else {
			return Double.toString(sellRate);
		}
	}

	private String manipulateBuy(String value) {
		Random rand = new Random();
		double result = Double.parseDouble(value);
		if (Math.random() <= (faultmodel.get("bpositiveProb").doubleValue() / 100)) {
			int positive = rand.nextInt(faultmodel.get("bpositiveRange").intValue());
			result = result + (result * positive / 100);
		}
		if (Math.random() <= (faultmodel.get("bnegativeProb").doubleValue() / 100)) {
			int negative = rand.nextInt(faultmodel.get("bnegativeRange").intValue());
			result = result - (result * negative / 100);
		}
		return Double.toString(result);
	}
}