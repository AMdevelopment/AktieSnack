package se.amdev.aktiesnackserverweb.jsonadapter;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverweb.model.PostWeb;
import se.amdev.aktiesnackserverweb.model.UserWeb;

/**
 * Created by Martin on 26/05/16.
 */

public final class PostAdapter implements JsonDeserializer<PostWeb>, JsonSerializer<PostWeb> {

    @Override
    public JsonElement serialize(PostWeb post, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add("user", new Gson().toJsonTree(post.getUser(), UserWeb.class));
        json.addProperty("postNumber", post.getPostNumber());
        json.addProperty("postId", post.getPostId());
        json.addProperty("text", post.getText());
        json.addProperty("vote", post.getVote());
        json.addProperty("createdTime", post.getCreationTime());
        json.addProperty("lastUpdatedTime", post.getLastUpdatedTime());

        return json;
    }

    @Override
    public PostWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        String text = json.get("text").getAsString();

        return new PostWeb(text);
    }
}

