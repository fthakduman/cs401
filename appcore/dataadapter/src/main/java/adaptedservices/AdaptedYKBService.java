package adaptedservices;

import adapter.CurrencyDataAdapter;
import bankservices.YKBService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Iterator;


public class AdaptedYKBService implements CurrencyDataAdapter {

    String bankName = null;
    LocalDateTime requestTime = null;
    String majorCurrency = null;
    String minorCurrency = null;
    double sellRate = 0;
    double buyRate = 0;
    JSONObject serviceResponse;

    public AdaptedYKBService(String bankName,String majorCurrency,String minorCurrency) {
        this.bankName = bankName;
        this.majorCurrency = majorCurrency;
        this.minorCurrency = minorCurrency;
    }

    public AdaptedYKBService() {
    }


    @Override
    public void makeRequest() {
        YKBService ykb = new YKBService();
        ykb.makeRequest();
        serviceResponse = ykb.getRequestResponse();
        requestTime = LocalDateTime.now();
        parseResponse();
    }
    private void parseResponse(){
        if(serviceResponse == null){
            throw new NullPointerException("The bank service response is null");
        }
        else{
            JSONArray responseArray = ((JSONObject) serviceResponse.get("response")).getJSONArray("exchangeRateList");
            Iterator responseIterotor = responseArray.iterator();
            while(responseIterotor.hasNext()){
                JSONObject tmp = (JSONObject) responseIterotor.next();
                if(tmp.get("majorCurrency").equals(majorCurrency) && tmp.get("minorCurrency").equals(minorCurrency)){
                    this.sellRate = Double.valueOf((String) tmp.get("sellRate"));
                    this.buyRate = Double.valueOf((String) tmp.get("buyRate"));
                }
            }


        }
    }

    public String getBankName() {
        return bankName;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public String getMajorCurrency() {
        return majorCurrency;
    }

    public String getMinorCurrency() {
        return minorCurrency;
    }

    public double getSellRate() {
        return sellRate;
    }

    public double getBuyRate() {
        return buyRate;
    }

    @Override
    public String toString() {
        return "AdaptedYKBService{" +
                "bankName='" + bankName + '\'' +
                ", requestTime=" + requestTime +
                ", majorCurrency='" + majorCurrency + '\'' +
                ", minorCurrency='" + minorCurrency + '\'' +
                ", sellRate=" + sellRate +
                ", buyRate=" + buyRate +
                '}';
    }
}
