package org.mustafa.yildirimm.exchangerates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




	public class YapiKrediWS {
		public static void main(String[] args) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		String urlToken = "https://api.yapikredi.com.tr/auth/oauth/v2/token";
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		//headers.add("CONTENT_TYPE", MediaType.APPLICATION_JSON);
		
		headers.add("Accept","application/json");
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("scope", "oob");
		body.add("grant_type", "client_credentials");
		body.add("client_id", "l7xxb890fe1707ec48b28a783b75eee2a93c");
		body.add("client_secret", "2fa84e0ead8d49a293d102f34055f1c6");
		HttpEntity<?> entity= new HttpEntity<Object>(body, headers);
		ResponseEntity<String> result = restTemplate.exchange(urlToken, HttpMethod.POST, entity, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(result.getBody());
		JsonNode accessToken = root.path("access_token");
		JsonNode tokenType = root.path("token_type");
		JsonNode expiresIn = root.path("expires_in");



		String authorizationToken = tokenType.asText() + " " + accessToken.asText();
		System.out.println("AUTHORİZATİON TOKEN  "+authorizationToken);

		////////////////////////////////////////////////////////////////

		StringBuilder urlBuilder = new StringBuilder("https://api.yapikredi.com.tr/api/investmentrates/v1/currencyRates");
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", authorizationToken);
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
	}
}