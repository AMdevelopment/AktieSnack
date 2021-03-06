package se.amdev.aktiesnackserverweb.jsonreaderandwriter;

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
import com.google.gson.stream.JsonWriter;

import se.amdev.aktiesnackserverweb.jsonadapter.UserAdapter;
import se.amdev.aktiesnackserverweb.model.UserWeb;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Martin on 26/05/16.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserParser implements MessageBodyReader<UserWeb>, MessageBodyWriter<UserWeb> {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(UserWeb.class, new UserAdapter()).create();

    @Override
    public long getSize(UserWeb user, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return 0;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAssignableFrom(UserWeb.class);
    }

    @Override
    public void writeTo(UserWeb user, Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, Object> map,
                        OutputStream stream)
            throws IOException, WebApplicationException {
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(stream))) {
            gson.toJson(user, UserWeb.class, writer);
        }
    }

    @Override
    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAssignableFrom(UserWeb.class);
    }

    @Override
    public UserWeb readFrom(Class<UserWeb> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, String> map, InputStream stream)
            throws IOException, WebApplicationException {
        return gson.fromJson(new InputStreamReader(stream), UserWeb.class);
    }
}