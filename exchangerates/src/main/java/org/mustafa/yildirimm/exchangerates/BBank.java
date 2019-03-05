package org.mustafa.yildirimm.exchangerates;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.*;



public class BBank extends wsmodel {
	


	public static void main(String[] args) throws Exception {

		BBank http = new BBank();
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
	
	}

	// HTTP GET request
	public void sendGet() throws Exception {

		String url = "https://api.exchangeratesapi.io/latest?base=USD&symbols=TRY";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		System.out.println("RESS"+response);
		
		in.close();
		
		
		//String json = "{\"name\": \"Bob\", \"id\": \"123\"}";

		// Method 1: parsing into a JSON element
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		System.out.println(jsonObject.get("TRY").getAsString());

		// Method 2: parsing into a Java Object
		wsmodel WSMODEL = new Gson().fromJson(response, wsmodel.class);
		//System.out.println(ws);

	}
}
