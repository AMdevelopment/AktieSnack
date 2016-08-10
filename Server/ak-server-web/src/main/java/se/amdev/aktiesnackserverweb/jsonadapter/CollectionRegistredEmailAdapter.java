package se.amdev.aktiesnackserverweb.jsonadapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.amdev.aktiesnackserverdata.model.InquiryData;

public final class CollectionRegistredEmailAdapter implements JsonDeserializer<ArrayList<InquiryData>>, JsonSerializer<ArrayList<InquiryData>> {

	@Override
	public JsonElement serialize(ArrayList<InquiryData> list, Type type, JsonSerializationContext context) {
		Collection<InquiryData> emails = new ArrayList<>();
		JsonObject json = new JsonObject();
		JsonArray jsonEmails = new JsonArray();
		list.forEach(e -> emails.add(e));
		emails.forEach(e -> jsonEmails.add(new Gson().toJsonTree(e, InquiryData.class)));

		json.add("registredEmails", jsonEmails);

		return json;
	}

	@Override
	public ArrayList<InquiryData> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		return null;
	}
}
