package se.amdev.aktiesnackserverdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Stocks")
public class StockData extends EntityModel {

	@Column(nullable = false, unique = true)
	private String stockName;
	
	@Column
	private String changePercent;
	
	@Column
	private String changeCurrency;
	
	@Column
	private String askPrice;
	
	@Column
	private String bidPrice;
	
	@Column
	private String dayLowCurrency;
	
	@Column
	private String dayHighCurrency;
	
	@Column
	private String dayRevenue;

	@Column
	private String marketValue;
	
	protected StockData() {
	}
	
	public StockData(String stockName){
		this.stockName = stockName;
	}
	
	public String getAskPrice() {
		return askPrice;
	}
	
	public String getBidPrice() {
		return bidPrice;
	}
	
	public String getChangeCurrency() {
		return changeCurrency;
	}
	
	public String getChangePercent() {
		return changePercent;
	}
	
	public String getDayHighCurrency() {
		return dayHighCurrency;
	}
	
	public String getDayLowCurrency() {
		return dayLowCurrency;
	}
	
	public String getDayRevenue() {
		return dayRevenue;
	}
	
	public String getMarketValue() {
		return marketValue;
	}
	
	public String getStockName() {
		return stockName;
	}
	
	public StockData setAskPrice(String askPrice) {
		this.askPrice = askPrice;
		return this;
	}
	
	public StockData setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
		return this;
	}
	
	public StockData setChangeCurrency(String changeCurrency) {
		this.changeCurrency = changeCurrency;
		return this;
	}
	
	public StockData setChangePercent(String changePercent) {
		this.changePercent = changePercent;
		return this;
	}
	
	public StockData setDayHighCurrency(String dayHighCurrency) {
		this.dayHighCurrency = dayHighCurrency;
		return this;
	}
	
	public StockData setDayLowCurrency(String dayLowCurrency) {
		this.dayLowCurrency = dayLowCurrency;
		return this;
	}
	
	public StockData setDayRevenue(String dayRevenue) {
		this.dayRevenue = dayRevenue;
		return this;
	}
	
	public StockData setMarketValue(String marketValue) {
		this.marketValue = marketValue;
		return this;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result += 17 * stockName.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (object instanceof StockData) {
			StockData otherObject = (StockData) object;

			return this.stockName.equals(otherObject.stockName);
		}
		return false;
	}
}
