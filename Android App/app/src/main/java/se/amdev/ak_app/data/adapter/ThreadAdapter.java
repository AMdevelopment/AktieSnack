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

/**
 * Created by Martin on 26/05/16.
 */

public final class ThreadAdapter implements JsonDeserializer<ThreadWeb>, JsonSerializer<ThreadWeb> {

    private Gson gson = new GsonBuilder().registerTypeAdapter(PostWeb.class, new PostAdapter()).create();

    @Override
    public JsonElement serialize(ThreadWeb thread, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("threadName", thread.getThreadNumber());
        json.addProperty("description", thread.getDescription());

        return json;
    }

    @Override
    public ThreadWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        String threadNumber = json.get("threadName").getAsString();
        String description = json.get("description").getAsString();
        String numberOfPosts = json.get("numberOfPosts").getAsString();
        String creationTime = json.get("creationTime").getAsString();
        String lastUpdatedTime = json.get("lastUpdatedTime").getAsString();
        String currency = json.get("currency").getAsString();

        Collection<PostWeb> posts = new ArrayList<>();

        return new ThreadWeb(threadNumber, description, posts, currency, creationTime, lastUpdatedTime, numberOfPosts);
    }
}

