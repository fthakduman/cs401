package adaptedservices;

import adapter.CurrencyDataAdapter;
import bankservices.DNZService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class AdaptedDNZService implements CurrencyDataAdapter {

    private static volatile AdaptedDNZService serviceInstance;

    private static final String bankName = "DenizBank";
    LocalDateTime requestTime = null;
    String majorCurrency = null;
    String minorCurrency = null;
    double sellRate = 0;
    double buyRate = 0;
    JSONObject serviceResponse;

    private AdaptedDNZService(String majorCurrency,String minorCurrency) {
        this.majorCurrency = majorCurrency;
        this.minorCurrency = minorCurrency;
    }

    @Override
    public void makeRequest() {
        DNZService dnz = new DNZService();
        dnz.makeRequest();
        serviceResponse = dnz.getRequestResponse();
        requestTime = LocalDateTime.now();
        parseResponse();
    }

    private void parseResponse(){
        if(serviceResponse == null){
            throw new NullPointerException("The bank base.collector.service response is null");
        }
        else{
            JSONArray responseArray = ((JSONObject) serviceResponse.get("Data")).getJSONArray("Rates");
            for(int i=0; i<responseArray.length(); i++){
                JSONObject tmp = (JSONObject) responseArray.get(i);
                if(tmp.get("CurrencyRate").equals(majorCurrency)){
                    this.sellRate = Double.valueOf((String) tmp.get("CashExchangeRate"));
                    this.buyRate = Double.valueOf((String) tmp.get("CashChangeRate"));
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

    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    @Override
    public String getMajorCurrency() {
        return majorCurrency;
    }

    @Override
    public String getMinorCurrency() {
        return minorCurrency;
    }

    @Override
    public double getSellRate() {
        return sellRate;
    }

    @Override
    public double getBuyRate() {
        return buyRate;
    }
    public static AdaptedDNZService getInstance(String majorCurrency,String minorCurrency) {
        if(serviceInstance == null) {
            synchronized (AdaptedDNZService.class) {
                if(serviceInstance == null) {
                    serviceInstance = new AdaptedDNZService(majorCurrency,minorCurrency);

                }
            }
        }
        return serviceInstance;
    }
}
