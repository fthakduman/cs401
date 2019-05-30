package com.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.composite.util.FileUtil;
import com.composite.util.OutputBank;
import com.composite.util.Result;
import com.google.gson.Gson;

public class Tests {
	private final static String USER_AGENT = "Mozilla/5.0";
	private static Calendar beforeCall = null;
	private static Calendar afterCall = null;

	private static Calendar getCurrentTime() {
		return Calendar.getInstance();
	}

	public static String getISOformat(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String nowAsISO = df.format(date);
		return nowAsISO;
	}

	public static void main(String[] args) throws Exception {
		runAllTests();
	}

	private static void runAllTests() throws Exception {
		runTest0(); // Testing correctness of the composite web service
		runTest1(); // Testing speed of the composite web service
	}

	private static void runTest0() throws Exception {
		beforeCall = getCurrentTime();
		System.out.println("------------------TEST0 IS WORKING-------------------------------");
		System.out.println("Testing: Correctness of the web service");
		String sendGet = sendGet("http://" + FileUtil.SERVER1 + ":8080/Composite/rest/getComposite");
		Result result = parseOutput(sendGet);

		OutputBank bestBank = new OutputBank();
		double minDiff = 9999999.0;
		for (OutputBank outputBank : result.getBanks().values()) {
			double sellRate = Double.parseDouble(outputBank.getSellRate());
			double buyRate = Double.parseDouble(outputBank.getBuyRate());
			double diffBetweenSellAndBuy = sellRate - buyRate;
			if (minDiff > diffBetweenSellAndBuy) {
				minDiff = diffBetweenSellAndBuy;
				bestBank.setBankName("MOST RELAIBLE BANK NAME IS: " + outputBank.getBankName());
				bestBank.setSellRate(outputBank.getSellRate());
				bestBank.setBuyRate(outputBank.getBuyRate());
				bestBank.setDiff(minDiff + "");
			}
		}
		if (bestBank.getBankName().equals(result.getSelectedBank().getBankName())) {
			System.out.println("The web service works correctly.");
		} else {
			System.out.println("The web service does not work correctly.");
		}
		afterCall = getCurrentTime();
		System.out.println();
	}

	private static void runTest1() {
		System.out.println("------------------TEST1 IS WORKING-------------------------------");
		System.out.println("Testing: Speed of the web service");
		System.out.println("One mock service runs 5 seconds in average, there are 5 calls to mock service here.");
		System.out.println("Composite service runs 30 seconds in average.");

		System.out.println("The time before the call: " + getISOformat(beforeCall.getTime()));
		beforeCall.add(Calendar.SECOND, 50);
		System.out.println("The time before the call: " + getISOformat(afterCall.getTime()));

		// Fark 15 saniyeden büyük mü?
		if (beforeCall.compareTo(afterCall) == 1) {
			System.out.println("The web service ran in less than 35 seconds.");
		} else {
			System.out.println("The web service took more time than expected.");
		}
		System.out.println();
	}

	// HTTP GET request
	private static String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		return response.toString();
	}

	private static Result parseOutput(String sendGet) {
		Gson g = new Gson();
		Result value = g.fromJson(sendGet, Result.class);
		return value;
	}

}
