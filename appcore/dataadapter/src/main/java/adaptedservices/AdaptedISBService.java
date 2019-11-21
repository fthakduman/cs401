package adaptedservices;

import adapter.CurrencyDataAdapter;
import bankservices.ISBService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Random;

public class AdaptedISBService implements CurrencyDataAdapter {
    
    private static volatile AdaptedISBService serviceInstance;

    private static final String bankName = "ISBank";
    
    LocalDateTime requestTime = null;
    String majorCurrency = null;
    String minorCurrency = null;
    double sellRate = 0;
    double buyRate = 0;
    JSONObject serviceResponse;

    private AdaptedISBService(String majorCurrency,String minorCurrency) {
        this.majorCurrency = majorCurrency;
        this.minorCurrency = minorCurrency;
    }
    
    @Override
    public void makeRequest() {
        ISBService isb = new ISBService();
        isb.makeRequest();
        serviceResponse = isb.getRequestResponse();
        requestTime = LocalDateTime.now();
        parseResponse();
        sellRate = Double.valueOf(manipulateSell(Double.toString(sellRate),Double.toString(buyRate),true));
        buyRate = Double.valueOf(manipulateBuy(Double.toString(buyRate)));
    }
    private void parseResponse(){
        if(serviceResponse == null){
            throw new NullPointerException("The bank base.collector.service response is null");
        }
        else{
            JSONArray responseArray =  serviceResponse.getJSONArray("data");
            for(int i=0; i<responseArray.length(); i++){
                JSONObject tmp = (JSONObject) responseArray.get(i);
                if(tmp.get("code").equals(majorCurrency)){
                    this.sellRate = Double.valueOf((String) tmp.get("effective_rate_sell"));
                    this.buyRate = Double.valueOf((String) tmp.get("effective_rate_buy"));
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
    public static AdaptedISBService getInstance(String majorCurrency,String minorCurrency) {
        
        if(serviceInstance == null) {
            synchronized (AdaptedISBService.class) {
                if(serviceInstance == null) {
                    serviceInstance = new AdaptedISBService(majorCurrency,minorCurrency);

                }
            }
        }
        return serviceInstance;
    }
    private String manipulateSell(String value, String buyRate, boolean isTest) {
        Random rand = new Random();
        double sellRate = Double.parseDouble(value);
        if (Math.random() <= (50 / 100)) {
            int positive = rand.nextInt(50);
            sellRate = sellRate + (sellRate * positive / 100);
        }
        if (Math.random() > (50/ 100)) {
            int negative = rand.nextInt(50);
            sellRate = sellRate - (sellRate * negative / 100);
        }
        double buyRateDouble = Double.parseDouble(buyRate);
        if (!isTest) {
            if (sellRate - buyRateDouble > 0) {
                return Double.toString(sellRate);
            } else {
                double increase = Math.random();
                return Double.toString(buyRateDouble + increase);
            }
        } else {
            return Double.toString(sellRate);
        }
    }

    private String manipulateBuy(String value) {
        Random rand = new Random();
        double result = Double.parseDouble(value);
        if (Math.random() <= (50 / 100)) {
            int positive = rand.nextInt(50);
            result = result + (result * positive / 100);
        }
        if (Math.random() > (50 / 100)) {
            int negative = rand.nextInt(50);
            result = result - (result * negative / 100);
        }
        return Double.toString(result);
    }

}
