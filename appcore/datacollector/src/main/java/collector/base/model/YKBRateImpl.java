package collector.base.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.DayOfWeek;
import java.time.Month;

@Document(indexName = "rates", type = "ykb")
public class YKBRateImpl implements YKBRate {

    @Id
    private String id;
    private final String  bankName = "YapıKrediBank";
    private String majorCurrency = null;
    private String minorCurrency = null;
    private double sellRate = 0;
    private double buyRate = 0;
    private int currencyYear;
    private Month currencyMonth;
    private int currencyMonthValue;
    private int currencyDayOfMonthValue;
    private int currencyHour;
    private int currencyMinute;
    private DayOfWeek currencyDayOfWeek;


    public YKBRateImpl(String majorCurrency, String minorCurrency) {
        this.majorCurrency = majorCurrency;
        this.minorCurrency = minorCurrency;
    }
    public YKBRateImpl() {

    }

    public void setCurrencyYear(int currencyYear) {
        this.currencyYear = currencyYear;
    }

    public void setCurrencyMonth(Month currencyMonth) {
        this.currencyMonth = currencyMonth;
    }

    @Override
    public Month getCurrencyMonth() {
        return null;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    @Override
    public String getBankName() {
        return bankName;
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

    public void setCurrencyMonthValue(int currencyMonthValue) {
        this.currencyMonthValue = currencyMonthValue;
    }

    public void setCurrencyDayOfMonthValue(int currencyDayOfMonthValue) {
        this.currencyDayOfMonthValue = currencyDayOfMonthValue;
    }

    public void setCurrencyHour(int currencyHour) {
        this.currencyHour = currencyHour;
    }

    public void setMinute(int minute) {
        this.currencyMinute = minute;
    }

    public void setCurrencyDayOfWeek(DayOfWeek currencyDayOfWeek) {
        this.currencyDayOfWeek = currencyDayOfWeek;
    }

    @Override
    public int getCurrencyYear() {
        return currencyYear;
    }

    @Override
    public int getCurrencyMonthValue() {
        return currencyDayOfMonthValue;
    }

    @Override
    public int getCurrencyDayofMonthValue() {
        return currencyDayOfMonthValue;
    }

    @Override
    public DayOfWeek getCurrencyDayOfWeek() {
        return currencyDayOfWeek;
    }

    @Override
    public int getCurrencyHour() {
        return currencyHour;
    }

    @Override
    public int getMinute() {
        return currencyMinute;
    }



}
