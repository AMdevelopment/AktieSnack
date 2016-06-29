package se.amdev.aktiesnackserverweb.jsonreaderandwriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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

import se.amdev.aktiesnackserverweb.jsonadapter.StockAdapter;
import se.amdev.aktiesnackserverweb.model.StockWeb;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockParser implements MessageBodyReader<StockWeb>, MessageBodyWriter<StockWeb> {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(StockWeb.class, new StockAdapter()).create();

    @Override
    public long getSize(StockWeb stock, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return 0;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAssignableFrom(StockWeb.class);
    }

    @Override
    public void writeTo(StockWeb post, Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, Object> map,
                        OutputStream stream)
            throws IOException, WebApplicationException {
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(stream))) {
            gson.toJson(post, StockWeb.class, writer);
        }
    }

    @Override
    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAssignableFrom(StockWeb.class);
    }

    @Override
    public StockWeb readFrom(Class<StockWeb> type, Type genericType, Annotation[] annotation, MediaType mediaType, MultivaluedMap<String, String> map, InputStream stream)
            throws IOException, WebApplicationException {
        return gson.fromJson(new InputStreamReader(stream), StockWeb.class);
    }
}
