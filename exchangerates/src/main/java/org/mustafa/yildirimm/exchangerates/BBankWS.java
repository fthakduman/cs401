package org.mustafa.yildirimm.exchangerates;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.*;



public class BBankWS extends BankWS {
	JSONObject requestResponse = null;
	JSONObject processedResponse = null;
	
	Date date;
	SimpleDateFormat dateFormat;
	SimpleDateFormat timeFormat;
	
	public BBankWS(){
		makeRequest();
		date = new Date();
		dateFormat = new SimpleDateFormat ("yyyy.MM.dd");
		timeFormat = new SimpleDateFormat ("hh:mm:ss a zzz");
	}
	public void printResponse(){
		System.out.println(requestResponse.toString());
	}

	public void makeRequest(){
		String url = "https://api.exchangeratesapi.io/latest?base=USD&symbols=TRY";
		
		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// optional default is GET
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = null;
		try {
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requestResponse = new JSONObject(response.toString());
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
