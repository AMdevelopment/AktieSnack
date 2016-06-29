package se.amdev.stockdownloader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import se.amdev.aktiesnackserverdata.model.StockData;
import se.amdev.aktiesnackserverdata.model.ThreadData;
import se.amdev.email.SendEmail;

public class YahooStockApi {

	public static final int SYMBOL = 0;
	public static final int CHANGEPERCENT = 1;
	public static final int CHANGECURRENCY = 2;
	public static final int ASKPRICE = 3;
	public static final int BIDPRICE = 4;
	public static final int DAYLOW = 5;
	public static final int DAYHIGH = 6;
	public static final int DAYVOLUME = 7;
	public static final int MARKETVALUE = 8;
	public static final String MESSAGE = "YAHOO STOCK API FAILURE";

	public static Collection<StockData> request(Collection<ThreadData> threads, Collection<StockData> stocks) {
		int stockSize = stocks.size();
		int varv = 1;

		Collection<StockData> returnStocks = new ArrayList<StockData>();

		StringBuilder companys = new StringBuilder();
		for (ThreadData t : threads) {
			companys.append(t.getDescription() + "+");
		}

		String url = "http://finance.yahoo.com/d/quotes.csv?s=" + companys.toString() + "&f=sp2c1abghvv1";

		try {
			URL yhoofin = new URL(url);
			URLConnection data = yhoofin.openConnection();
			Scanner input = new Scanner(data.getInputStream());

			while (input.hasNextLine()) {
				String line = input.nextLine();

				String[] ss = new String[7];
				ss = line.split(",");

				String sym = ss[SYMBOL].replace("\"", "");

				if (!stocks.isEmpty() && varv <= stockSize) {
					for (StockData s : stocks) {
						if (sym.equals(s.getStockName())) {
							returnStocks.add(s.setChangePercent(ss[CHANGEPERCENT].replace("\"", ""))
									.setChangeCurrency(ss[CHANGECURRENCY])
									.setAskPrice(ss[ASKPRICE])
									.setBidPrice(ss[BIDPRICE])
									.setDayLowCurrency(ss[DAYLOW])
									.setDayHighCurrency(ss[DAYHIGH])
									.setDayRevenue(ss[DAYVOLUME])
									.setMarketValue(ss[MARKETVALUE]));
						}
					}
				}
				else {
					returnStocks.add(new StockData(ss[SYMBOL].replace("\"", ""))
							.setChangePercent(ss[CHANGEPERCENT].replace("\"", ""))
							.setChangeCurrency(ss[CHANGECURRENCY])
							.setAskPrice(ss[ASKPRICE])
							.setBidPrice(ss[BIDPRICE])
							.setDayLowCurrency(ss[DAYLOW])
							.setDayHighCurrency(ss[DAYHIGH])
							.setDayRevenue(ss[DAYVOLUME])
							.setMarketValue(ss[MARKETVALUE]));
				}
				varv++;
			}
			input.close();
		}
		catch (Exception ex)

		{
			SendEmail.sendError(MESSAGE, ex);
		}
		return returnStocks;
	}
}
