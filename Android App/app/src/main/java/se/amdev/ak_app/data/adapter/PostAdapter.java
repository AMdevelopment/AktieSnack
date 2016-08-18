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
        json.addProperty("text", post.getText());

        return json;
    }

    @Override
    public PostWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        UserWeb user = new Gson().fromJson(json.get("user"), UserWeb.class);
        String postNumber = json.get("postNumber").getAsString();
        String text = json.get("text").getAsString();
        String postId = json.get("postId").getAsString();
        int vote = json.get("vote").getAsInt();
        String creationTime = json.get("creationTime").getAsString();
        String lastUpdatedTime = json.get("lastUpdatedTime").getAsString();

        return new PostWeb(user, postNumber, postId, text, vote, creationTime, lastUpdatedTime);
    }
}

