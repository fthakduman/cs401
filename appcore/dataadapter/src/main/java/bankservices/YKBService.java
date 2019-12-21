package bankservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class YKBService {
    JSONObject requestResponse = null;


    public YKBService() {

    }


    public void makeRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String urlToken = "https://api.yapikredi.com.tr/auth/oauth/v2/token";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_JSON);

        headers.add("Accept", "application/json");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("scope", "oob");
        body.add("grant_type", "client_credentials");
        body.add("client_id", "l7xxb890fe1707ec48b28a783b75eee2a93c");
        body.add("client_secret", "2fa84e0ead8d49a293d102f34055f1c6");
        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
        ResponseEntity<String> result = restTemplate.exchange(urlToken, HttpMethod.POST, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();


        JsonNode root = null;
        try {
            root = mapper.readTree(result.getBody());
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        JsonNode accessToken = root.path("access_token");
        JsonNode tokenType = root.path("token_type");
        JsonNode expiresIn = root.path("expires_in");


        String authorizationToken = tokenType.asText() + " " + accessToken.asText();

        ////////////////////////////////////////////////////////////////

        StringBuilder urlBuilder = new StringBuilder("https://api.yapikredi.com.tr/api/investmentrates/v1/currencyRates");
        URL url = null;
        try {
            url = new URL(urlBuilder.toString());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn.setRequestProperty("Authorization", authorizationToken);
        try {
            conn.getResponseCode();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedReader rd = null;
        try {
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            rd.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        requestResponse = new JSONObject(sb.toString());
        conn.disconnect();
    }

    public JSONObject getRequestResponse() {
        return requestResponse;
    }
}