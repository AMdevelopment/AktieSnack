package se.amdev.ak_app.data.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;

/**
 * Created by Martin on 26/05/16.
 */

public final class PostAdapter implements JsonDeserializer<PostWeb>, JsonSerializer<PostWeb> {

    @Override
    public JsonElement serialize(PostWeb post, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add("user", new Gson().toJsonTree(post.getUser(), UserWeb.class));
        json.add("thread", new Gson().toJsonTree(post.getThread(), ThreadWeb.class));
        json.addProperty("postNumber", post.getPostNumber());
        json.addProperty("text", post.getText());

        return json;
    }

    @Override
    public PostWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        UserWeb user = new Gson().fromJson(json.get("user"), UserWeb.class);
        ThreadWeb thread = new Gson().fromJson(json.get("thread"), ThreadWeb.class);
        String postNumber = json.get("postNumber").getAsString();
        String text = json.get("text").getAsString();
        String creationTime = json.get("creationTime").getAsString();
        String lastUpdatedTime = json.get("lastUpdatedTime").getAsString();

        return new PostWeb(user, thread,postNumber, text, creationTime, lastUpdatedTime);
    }
}

