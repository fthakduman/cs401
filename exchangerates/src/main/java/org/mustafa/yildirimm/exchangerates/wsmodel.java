package org.mustafa.yildirimm.exchangerates;

public class wsmodel {

	public String sellRate;
	public String minorCurrency;
	public String majorCurrency;
	public String buyRate;
	public String date;
	public String time;
	
	public wsmodel() {
		
	}
	
	public wsmodel(String sellRate, String minorCurrency, String majorCurrency, String buyRate, String date, String time) {
		
		this.sellRate = sellRate;
		this.minorCurrency = minorCurrency;
		this.majorCurrency = majorCurrency;
		this.buyRate = buyRate;
		this.date = date;
		this.time = time;
	}
	
	public String getSellRate() {
		return sellRate;
	}
	public void setSellRate(String sellRate) {
		this.sellRate = sellRate;
	}
	public String getMinorCurrency() {
		return minorCurrency;
	}
	public void setMinorCurrency(String minorCurrency) {
		this.minorCurrency = minorCurrency;
	}
	public String getMajorCurrency() {
		return majorCurrency;
	}
	public void setMajorCurrency(String majorCurrency) {
		this.majorCurrency = majorCurrency;
	}
	public String getBuyRate() {
		return buyRate;
	}
	public void setBuyRate(String buyRate) {
		this.buyRate = buyRate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
}
