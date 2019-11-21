package adaptedservices;

import adapter.CurrencyDataAdapter;
import bankservices.YKBService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Iterator;


public class AdaptedYKBService implements CurrencyDataAdapter {

    private static volatile AdaptedYKBService serviceInstance;

    private final String  bankName = "YapıKrediBank";
    LocalDateTime requestTime = null;
    String majorCurrency = null;
    String minorCurrency = null;
    double sellRate = 0;
    double buyRate = 0;
    JSONObject serviceResponse;

    private AdaptedYKBService(String majorCurrency,String minorCurrency) {
        this.majorCurrency = majorCurrency;
        this.minorCurrency = minorCurrency;
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
            throw new NullPointerException("The bank base.collector.service response is null");
        }
        else{
            JSONArray responseArray = ((JSONObject) serviceResponse.get("response")).getJSONArray("exchangeRateList");
            for(int i=0; i<responseArray.length(); i++){
                JSONObject tmp = (JSONObject) responseArray.get(i);
                if(tmp.get("majorCurrency").equals(majorCurrency) && tmp.get("minorCurrency").equals(minorCurrency)){
                    this.sellRate = Double.valueOf((String) tmp.get("sellRate"));
                    this.buyRate = Double.valueOf((String) tmp.get("buyRate"));
                }
            }


        }
    }
    public void refresh(){
        this.sellRate = 0;
        this.buyRate = 0;
        this.requestTime = null;
        this.serviceResponse = null;

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


    public static AdaptedYKBService getInstance(String majorCurrency,String minorCurrency) {
        if(serviceInstance == null) {
            synchronized (AdaptedYKBService.class) {
                if(serviceInstance == null) {
                    serviceInstance = new AdaptedYKBService(majorCurrency,minorCurrency);

                }
            }
        }
        return serviceInstance;
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
