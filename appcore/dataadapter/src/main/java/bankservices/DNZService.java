package bankservices;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.net.URI;

public class DNZService {

    JSONObject requestResponse = null;

    public DNZService() {
    }

    public void makeRequest(){
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api-gateway.intertech.com.tr/BankingApiV01/GetCurrencyRateList");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "0b8050a7936c4c04a89d2f9cd620e3f9");


            // Request body
            StringEntity reqEntity = new StringEntity("{\n" +
                    "\n" +
                    "    \"Header\": {\n" +
                    "\n" +
                    "\t\"AppKey\": \"qxP3JWuR5xMvuQ84\",\n" +
                    "\t\n" +
                    "\t        \"Channel\": \"API\",\n" +
                    "        \"ChannelSessionId\": \"\",  \n" +
                    "\n" +
                    "        \"ChannelRequestId\": \"\" \n" +
                    "\n" +
                    "    },\n" +
                    "\n" +
                    "    \"Parameters\": [\n" +
                    "\n" +
                    "        {}\n" +
                    "\n" +
                    "    ]\n" +
                    "\n" +
                    "}", "UTF-8");
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                requestResponse = new JSONObject(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
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
