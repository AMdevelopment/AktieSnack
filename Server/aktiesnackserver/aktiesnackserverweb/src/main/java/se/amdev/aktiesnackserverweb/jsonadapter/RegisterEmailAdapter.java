package se.amdev.aktiesnackserverweb.jsonadapter;

	import java.lang.reflect.Type;

	import com.google.gson.JsonDeserializationContext;
	import com.google.gson.JsonDeserializer;
	import com.google.gson.JsonElement;
	import com.google.gson.JsonObject;
	import com.google.gson.JsonParseException;
	import com.google.gson.JsonSerializationContext;
	import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverdata.model.InquiryData;

	public final class RegisterEmailAdapter implements JsonDeserializer<InquiryData>, JsonSerializer<InquiryData> {

	    @Override
	    public JsonElement serialize(InquiryData email, Type type, JsonSerializationContext context) {
	        JsonObject json = new JsonObject();
	        
	        json.addProperty("email", email.getEmail());

	        return json;
	    }

	    @Override
	    public InquiryData deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
	        JsonObject json = arg0.getAsJsonObject();
	        String email = json.get("email").getAsString();

	        return new InquiryData(email);
	    }
	
}
