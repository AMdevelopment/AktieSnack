package se.amdev.aktiesnackserverweb.jsonadapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverweb.model.UserWeb;

/**
 * Created by Martin on 26/05/16.
 */

public final class UserAdapter implements JsonDeserializer<UserWeb>, JsonSerializer<UserWeb> {

    @Override
    public JsonElement serialize(UserWeb user, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("username", user.getUsername());
        json.addProperty("email", user.getEmail());
        json.addProperty("firstName", user.getFirstName());
        json.addProperty("lastName", user.getLastName());
        json.addProperty("createdTime", user.getCreationTime());
        json.addProperty("lastUpdatedTime", user.getLastUpdatedTime());

        return json;
    }

    @Override
    public UserWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        String username = json.get("username").getAsString();
        String email = json.get("email").getAsString();
        String firstName = json.get("firstName").getAsString();
        String lastName = json.get("lastName").getAsString();

        return new UserWeb(username, email, firstName, lastName);
    }
}

