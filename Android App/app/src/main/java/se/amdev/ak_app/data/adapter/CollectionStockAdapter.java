package se.amdev.ak_app.data.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;


public class CollectionStockAdapter implements JsonDeserializer<ArrayList<StockWeb>> {

    private Gson gson = new GsonBuilder().registerTypeAdapter(StockWeb.class, new StockAdapter()).create();
 /*   @Override
    public JsonElement serialize(ArrayList<StockWeb> list, Type type, JsonSerializationContext context) {
        Collection<ThreadWeb> threads = new ArrayList<>();
        JsonObject json = new JsonObject();
        JsonArray jsonThreads = new JsonArray();

        for(StockWeb s : list){
            threads.add(t);
        }

        for(StockWeb s : stocks){
            jsonThreads.add(new Gson().toJsonTree(t, ThreadWeb.class));
        }

        json.add("stocks", jsonStocks);

        return json;
    }
*/    @Override
    public ArrayList<StockWeb> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        JsonArray stocks = json.get("stocks").getAsJsonArray();
        ArrayList<StockWeb> stockWebs = new ArrayList<>();

        int i = 0;
        for(JsonElement e : stocks){
            stockWebs.add(gson.fromJson(e, StockWeb.class));
            i++;
        }

        return stockWebs;
    }
}