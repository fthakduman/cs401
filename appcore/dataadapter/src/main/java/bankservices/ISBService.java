package bankservices;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.net.URI;

public class ISBService {
    JSONObject requestResponse = null;

    public ISBService() {
    }

    public void makeRequest(){
        try {
            HttpResponse<String> response = Unirest.get("https://api.sandbox.isbank.com.tr/v1/exchange-rates/")
                    .header("x-ibm-client-secret", "bF7gC7gW2sF0sL0wA2uH7uY0hV1sD3wJ3sL2uD5sU4kP6yP7dI")
                    .header("x-ibm-client-id", "d341a80c-f61f-40f2-9861-e6fe385f4b30")
                    .asString();

            requestResponse = new JSONObject(response.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    public JSONObject getRequestResponse() {
        return requestResponse;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
