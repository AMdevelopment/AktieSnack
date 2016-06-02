package se.amdev.aktiesnackserverdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Stocks")
public class StockData extends EntityModel {

	@Column(nullable = false)
	private String stockName;

	protected StockData() {
	}
	
	public StockData(String stockName) {
		this.stockName = stockName;
	}

	public String getStockName() {
		return stockName;
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
