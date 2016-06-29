package se.amdev.ak_app.data.model;

/**
 * Created by Martin on 27/06/16.
 */
public class StockWeb {

        private String stockName;

        private String changePercent;

        private String changeCurrency;

        private String askPrice;

        private String bidPrice;

        private String dayLowCurrency;

        private String dayHighCurrency;

        private String dayRevenue;

        private String marketValue;

        protected StockWeb() {
        }

        public StockWeb(String stockName){
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

        public StockWeb setAskPrice(String askPrice) {
            this.askPrice = askPrice;
            return this;
        }

        public StockWeb setBidPrice(String bidPrice) {
            this.bidPrice = bidPrice;
            return this;
        }

        public StockWeb setChangeCurrency(String changeCurrency) {
            this.changeCurrency = changeCurrency;
            return this;
        }

        public StockWeb setChangePercent(String changePercent) {
            this.changePercent = changePercent;
            return this;
        }

        public StockWeb setDayHighCurrency(String dayHighCurrency) {
            this.dayHighCurrency = dayHighCurrency;
            return this;
        }

        public StockWeb setDayLowCurrency(String dayLowCurrency) {
            this.dayLowCurrency = dayLowCurrency;
            return this;
        }

        public StockWeb setDayRevenue(String dayRevenue) {
            this.dayRevenue = dayRevenue;
            return this;
        }

        public StockWeb setMarketValue(String marketValue) {
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

            if (object instanceof StockWeb) {
                StockWeb otherObject = (StockWeb) object;

                return this.stockName.equals(otherObject.stockName);
            }
            return false;
        }
}