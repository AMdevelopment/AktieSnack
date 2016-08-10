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

import se.amdev.aktiesnackserverweb.jsonadapter.PostAdapter;
import se.amdev.aktiesnackserverweb.model.PostWeb;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Martin on 26/05/16.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostParser implements MessageBodyReader<PostWeb>, MessageBodyWriter<PostWeb> {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(PostWeb.class, new PostAdapter()).create();

    @Override
    public long getSize(PostWeb post, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return 0;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAssignableFrom(PostWeb.class);
    }

    @Override
    public void writeTo(PostWeb post, Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, Object> map,
                        OutputStream stream)
            throws IOException, WebApplicationException {
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(stream))) {
            gson.toJson(post, PostWeb.class, writer);
        }
    }

    @Override
    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAssignableFrom(PostWeb.class);
    }

    @Override
    public PostWeb readFrom(Class<PostWeb> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, String> map, InputStream stream)
            throws IOException, WebApplicationException {
        return gson.fromJson(new InputStreamReader(stream), PostWeb.class);
    }
}