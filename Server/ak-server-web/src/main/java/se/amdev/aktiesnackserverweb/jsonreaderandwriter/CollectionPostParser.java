package se.amdev.aktiesnackserverweb.jsonreaderandwriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import se.amdev.aktiesnackserverweb.jsonadapter.CollectionPostAdapter;
import se.amdev.aktiesnackserverweb.model.PostWeb;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionPostParser implements MessageBodyReader<Collection<PostWeb>>, MessageBodyWriter<Collection<PostWeb>> {

	private static final Type workItemDataCollectionType = new TypeToken<Collection<PostWeb>>()
	{
	}.getType();
	private static final Gson gson = new GsonBuilder().registerTypeAdapter(workItemDataCollectionType, new CollectionPostAdapter()).create();

	@Override
	public long getSize(Collection<PostWeb> arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] anno, MediaType mediaType) {
		if (!List.class.isAssignableFrom(type) || !MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType)) {
			return false;
		}

		if (!(genericType instanceof ParameterizedType)) {
			return false;
		}

		final ParameterizedType parameterizedType = (ParameterizedType) genericType;
		final Type actualType = parameterizedType.getActualTypeArguments()[0];

		return actualType.equals(PostWeb.class);
	}

	@Override
	public void writeTo(Collection<PostWeb> list, Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, Object> map,
			OutputStream stream)
					throws IOException, WebApplicationException {
		try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(stream))) {
			gson.toJson(list, workItemDataCollectionType, writer);
		}
	}

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		return arg0.isAssignableFrom(ArrayList.class);
	}

	@Override
	public Collection<PostWeb> readFrom(Class<Collection<PostWeb>> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		return gson.fromJson(new InputStreamReader(entityStream), workItemDataCollectionType);
	}
}