package se.amdev.aktiesnackserverweb.jsonadapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverweb.model.StockWeb;

public final class StockAdapter implements JsonDeserializer<StockWeb>, JsonSerializer<StockWeb> {

    @Override
    public JsonElement serialize(StockWeb stock, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("stockName", stock.getStockName());
        json.addProperty("changePercent", stock.getChangePercent());
        json.addProperty("changeCurrency", stock.getChangeCurrency());
        json.addProperty("askPrice", stock.getAskPrice());
        json.addProperty("bidPrice", stock.getBidPrice());
        json.addProperty("dayLowCurrency", stock.getDayLowCurrency());
        json.addProperty("dayHighCurrency", stock.getDayHighCurrency());
        json.addProperty("dayRevenue", stock.getDayRevenue());
        json.addProperty("marketValue", stock.getMarketValue());

        return json;
    }

    @Override
    public StockWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        String stockName = json.get("stockName").getAsString();
        String changePercent = json.get("changePercent").getAsString();
        String changeCurrency = json.get("changeCurrency").getAsString();
        String askPrice = json.get("askPrice").getAsString();
        String bidPrice = json.get("bidPrice").getAsString();
        String dayLowCurrency = json.get("dayLowCurrency").getAsString();
        String dayHighCurrency = json.get("dayHighCurrency").getAsString();
        String dayRevenue = json.get("dayRevenue").getAsString();
        String marketValue = json.get("marketValue").getAsString();

        return new StockWeb(stockName)
        		.setChangePercent(changePercent)
        		.setChangeCurrency(changeCurrency)
        		.setAskPrice(askPrice)
        		.setBidPrice(bidPrice)
        		.setDayLowCurrency(dayLowCurrency)
        		.setDayHighCurrency(dayHighCurrency)
        		.setDayRevenue(dayRevenue)
        		.setMarketValue(marketValue);
    }
}