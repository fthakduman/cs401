package collector.base.model;

import java.time.DayOfWeek;
import java.time.Month;

public interface YKBRate {

    public String getBankName();

    public String getMajorCurrency();

    public String getMinorCurrency();

    public double getSellRate();

    public double getBuyRate();

    public int getCurrencyYear();

    public int getCurrencyMonthValue();

    public Month getCurrencyMonth();

    public int getCurrencyDayofMonthValue();

    public DayOfWeek getCurrencyDayOfWeek();

    public int getCurrencyHour();

    public int getMinute();


}
