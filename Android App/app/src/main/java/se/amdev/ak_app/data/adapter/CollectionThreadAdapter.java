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
import se.amdev.ak_app.data.model.ThreadWeb;


public class CollectionThreadAdapter implements JsonDeserializer<ArrayList<ThreadWeb>>, JsonSerializer<ArrayList<ThreadWeb>> {

	private Gson gson = new GsonBuilder().registerTypeAdapter(ThreadWeb.class, new ThreadAdapter()).create();
	@Override
	public JsonElement serialize(ArrayList<ThreadWeb> list, Type type, JsonSerializationContext context) {
		Collection<ThreadWeb> threads = new ArrayList<>();
		JsonObject json = new JsonObject();
		JsonArray jsonThreads = new JsonArray();

		for(ThreadWeb t : list){
			threads.add(t);
		}

		for(ThreadWeb t : threads){
			jsonThreads.add(new Gson().toJsonTree(t, ThreadWeb.class));
		}

		json.add("threads", jsonThreads);

		return json;
	}

	@Override
	public ArrayList<ThreadWeb> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject json = arg0.getAsJsonObject();
		JsonArray threads = json.get("threads").getAsJsonArray();
		ArrayList<ThreadWeb> threadWebs = new ArrayList<>();

		int i = 0;
		for(JsonElement e : threads){
			threadWebs.add(gson.fromJson(e, ThreadWeb.class));
			i++;
		}

		return threadWebs;
	}
}
