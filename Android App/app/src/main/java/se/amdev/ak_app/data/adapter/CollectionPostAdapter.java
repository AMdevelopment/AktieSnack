package se.amdev.ak_app.data.adapter;

import com.google.gson.Gson;
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

import se.amdev.ak_app.data.model.PostWeb;

public final class CollectionPostAdapter implements JsonDeserializer<ArrayList<PostWeb>>, JsonSerializer<ArrayList<PostWeb>> {

	@Override
	public JsonElement serialize(ArrayList<PostWeb> list, Type type, JsonSerializationContext context) {
		Collection<PostWeb> posts = new ArrayList<>();
		JsonObject json = new JsonObject();
		JsonArray jsonPosts = new JsonArray();

		for(PostWeb p : list){
			posts.add(p);
		}
		for(PostWeb p : posts){
			jsonPosts.add(new Gson().toJsonTree(p, PostWeb.class));
		}

		json.add("posts", jsonPosts);

		return json;
	}

	@Override
	public ArrayList<PostWeb> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		return null;
	}
}
