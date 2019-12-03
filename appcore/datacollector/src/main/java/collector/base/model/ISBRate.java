package collector.base.model;

import java.time.DayOfWeek;
import java.time.Month;

public interface ISBRate {
    public String getBankName();

    public String getMajorCurrency();

    public String getMinorCurrency();

    public double getSellRate();

    public double getBuyRate();

    public void setSellRate(double sellRate);

    public void setBuyRate(double buyRate);

    public void setCurrencyYear(int currencyYear);

    public void setCurrencyMonth(Month currencyMonth);

    public void setCurrencyMonthValue(int currencyMonthValue);

    public void setCurrencyDayOfMonthValue(int currencyDayOfMonthValue);

    public void setCurrencyHour(int currencyHour);

    public void setCurrencyMinute(int currencyMinute);

    public void setCurrencyDayOfWeek(DayOfWeek currencyDayOfWeek);

    public int getCurrencyYear();

    public int getCurrencyMonthValue();

    public Month getCurrencyMonth();

    public int getCurrencyDayOfMonthValue();

    public DayOfWeek getCurrencyDayOfWeek();

    public int getCurrencyHour();

    public int getCurrencyMinute();
}
