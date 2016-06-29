package se.amdev.aktiesnackserverweb.jsonadapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverweb.model.StockWeb;

public final class CollectionStockAdapter implements JsonDeserializer<ArrayList<StockWeb>>, JsonSerializer<ArrayList<StockWeb>> {

	@Override
	public JsonElement serialize(ArrayList<StockWeb> list, Type type, JsonSerializationContext context) {
		Collection<StockWeb> stocks = new ArrayList<>();
		JsonObject json = new JsonObject();
		JsonArray jsonStocks = new JsonArray();
		list.forEach(s -> stocks.add(s));
		stocks.forEach(s -> jsonStocks.add(new Gson().toJsonTree(s, StockWeb.class)));

		json.add("stocks", jsonStocks);

		return json;
	}

	@Override
	public ArrayList<StockWeb> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		return null;
	}
}
