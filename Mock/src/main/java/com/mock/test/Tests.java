package com.mock.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.mock.util.Bank;
import com.mock.util.Conversion;
import com.mock.util.FileUtil;

public class Tests {
	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
		runAllTests();
	}

	private static void runAllTests() throws Exception {
		runTest0(); // Testing test0config.json on mock service
		runTest1(); // Testing test1config.json on mock service
		runTest2(); // Testing test2config.json on mock service
		runTest3(); // Testing Web Service Parameter on mock service
		runTest4(); // Testing Speed of the web service on mock service
	}

	private static void runTest0() throws Exception {
		System.out.println("------------------TEST0 IS WORKING-------------------------------");
		System.out.println("Testing: " + "test0config.json");
		try {
			sendGet("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/Test");
		} catch (IOException exception) {
			System.out.println("The config file does not exists.");
		}
		System.out.println();
	}

	private static void runTest1() throws Exception {
		System.out.println("------------------TEST1 IS WORKING-------------------------------");
		System.out.println("Testing: " + "test1config.json");
		try {
			sendGet("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/Test1");
		} catch (Exception exception) {
			System.out.println("Config file is not well formatted.");
		}
		System.out.println();
	}

	private static void runTest2() throws Exception {
		System.out.println("------------------TEST2 IS WORKING-------------------------------");
		System.out.println("Testing: " + "test2config.json");
		String sendGetYkb = sendGet("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/Test2");
		try {
			isNegativeValue(parseOutput(sendGetYkb));
		} catch (RuntimeException exception) {
			System.out.println(exception);
		}
		System.out.println();
	}

	private static void isNegativeValue(Bank bank) throws Exception {
		for (Conversion conversion : bank.getMap().values()) {
			if ("TL".equals(conversion.getMinorCurrency()) && "USD".equals(conversion.getMajorCurrency())) {
				double sellRate = Double.parseDouble(conversion.getSellRate());
				double buyRate = Double.parseDouble(conversion.getBuyRate());
				double diffBetweenSellAndBuy = sellRate - buyRate;
				if (diffBetweenSellAndBuy < 0)
					throw new RuntimeException("\n" + "Sell rate: " + sellRate + "\n" + "Buy rate: " + buyRate + "\n"
							+ "Diff: " + diffBetweenSellAndBuy + "\n" + "Sell rate can not be lower than buy rate");
				else
					System.out.println("Test did not create any exception.");
			}
		}
	}

	private static void runTest3() throws Exception {
		System.out.println("------------------TEST3 IS WORKING-------------------------------");
		System.out.println("Testing: Web Service Parameter");
		try {
			sendGet("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/Akbank");
		} catch (Exception exception) {
			System.out.println("This parameter is not suitable for web service and does not have a config.");
		}
		System.out.println();
	}

	private static void runTest4() throws Exception {
		System.out.println("------------------TEST4 IS WORKING-------------------------------");
		System.out.println("Testing: Speed of the web service");
		System.out.println("Mock service runs 5.1 seconds in average.");

		Calendar cal = Calendar.getInstance();
		System.out.println("The time before the call: " + getISOformat(cal.getTime()));
		cal.add(Calendar.SECOND, 10);
		Date beforeCall = cal.getTime();

		sendGet("http://" + FileUtil.SERVER1 + ":8080/Mock/rest/getRates/Bank/YKB");
		cal = Calendar.getInstance();
		Date afterCall = cal.getTime();

		System.out.println("The time after the call: " + getISOformat(afterCall));

		// Fark 10 saniyeden büyük mü?
		if (beforeCall.compareTo(afterCall) == 1) {
			System.out.println("The web service ran in less than 15 seconds.");
		} else {
			System.out.println("The web service took more time than expected.");
		}
		System.out.println();
	}

	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public static String getISOformat(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String nowAsISO = df.format(date);
		return nowAsISO;
	}

	// HTTP GET request
	private static String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		// int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

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

	private static Bank parseOutput(String sendGet) {
		Gson g = new Gson();
		Bank value = g.fromJson(sendGet, Bank.class);
		return value;
	}
}
