package se.amdev.aktiesnackserverweb.jsonadapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverweb.model.ThreadWeb;

/**
 * Created by Martin on 26/05/16.
 */

public final class ThreadAdapter implements JsonDeserializer<ThreadWeb>, JsonSerializer<ThreadWeb> {

	@Override
	public JsonElement serialize(ThreadWeb thread, Type type, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("threadName", thread.getThreadName());
		json.addProperty("description", thread.getDescription());
		json.addProperty("numberOfPosts", thread.getNumberOfPosts());
		json.addProperty("currency", thread.getCurrency());
        json.addProperty("lastUpdatedTime", thread.getLastUpdatedTime());
        json.addProperty("creationTime", thread.getCreationTime());

//		JsonArray array = new JsonArray();
//		if (thread.getPosts() != null) {
//			thread.getPosts().forEach(p -> array.add(new Gson().toJson(p, PostWeb.class)));
//		}
//		json.add("posts", array);

		return json;
	}

	@Override
	public ThreadWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject json = arg0.getAsJsonObject();
		String threadNumber = json.get("threadName").getAsString();
		String description = json.get("description").getAsString();
		String currency = json.get("currency").getAsString();

		return new ThreadWeb(threadNumber, description, currency);
	}
}
