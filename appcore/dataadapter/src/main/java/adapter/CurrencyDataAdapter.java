package adapter;

import java.time.LocalDateTime;

public interface CurrencyDataAdapter {


    public void makeRequest();

    public String getBankName();

    public LocalDateTime getRequestTime();

    public String getMajorCurrency();

    public String getMinorCurrency();

    public double getSellRate();

    public double getBuyRate();

}
