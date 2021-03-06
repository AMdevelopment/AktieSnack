package se.amdev.ak_app.data.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import se.amdev.ak_app.data.model.UserWeb;


/**
 * Created by Martin on 26/05/16.
 */

public final class UserAdapter implements JsonDeserializer<UserWeb>, JsonSerializer<UserWeb> {

    @Override
    public JsonElement serialize(UserWeb user, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("username", user.getUsername());
        json.addProperty("password", user.getPassword());
        json.addProperty("email", user.getEmail());
        json.addProperty("firstName", user.getFirstName());
        json.addProperty("lastName", user.getLastName());

        return json;
    }

    @Override
    public UserWeb deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        JsonObject json = arg0.getAsJsonObject();
        String username = json.get("username").getAsString();
        String email = json.get("email").getAsString();
        String firstName = json.get("firstName").getAsString();
        String lastName = json.get("lastName").getAsString();
        String creationTime = json.get("createdTime").getAsString();
        String lastUpdatedTime = json.get("lastUpdatedTime").getAsString();

        return new UserWeb(username, "password", email, firstName, lastName, creationTime, lastUpdatedTime);
    }
}

