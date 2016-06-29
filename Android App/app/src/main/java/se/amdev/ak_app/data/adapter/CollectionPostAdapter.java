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

import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.ThreadWeb;

public final class CollectionPostAdapter implements JsonDeserializer<ArrayList<PostWeb>>, JsonSerializer<ArrayList<PostWeb>> {

	private Gson gson = new GsonBuilder().registerTypeAdapter(ThreadWeb.class, new PostAdapter()).create();

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
		JsonObject json = arg0.getAsJsonObject();
		JsonArray threads = json.get("posts").getAsJsonArray();
		ArrayList<PostWeb> postWebs = new ArrayList<>();

		int i = 0;
		for(JsonElement e : threads){
			postWebs.add(gson.fromJson(e, PostWeb.class));
			i++;
		}

		return postWebs;
	}
}
