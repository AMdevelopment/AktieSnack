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

import se.amdev.aktiesnackserverweb.model.ThreadWeb;

public class CollectionThreadAdapter implements JsonDeserializer<ArrayList<ThreadWeb>>, JsonSerializer<ArrayList<ThreadWeb>> {
	
	@Override
	public JsonElement serialize(ArrayList<ThreadWeb> list, Type type, JsonSerializationContext context) {
		Collection<ThreadWeb> threads = new ArrayList<>();
		JsonObject json = new JsonObject();
		JsonArray jsonThreads = new JsonArray();
		list.forEach(t -> threads.add(t));
		threads.forEach(t -> jsonThreads.add(new Gson().toJsonTree(t, ThreadWeb.class)));

		json.add("threads", jsonThreads);

		return json;
	}

	@Override
	public ArrayList<ThreadWeb> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		return null;
	}
}
