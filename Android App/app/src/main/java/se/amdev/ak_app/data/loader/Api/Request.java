package se.amdev.ak_app.data.loader.Api;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import se.amdev.ak_app.data.model.ThreadWeb;


/**
 * Created by Martin on 31/05/16.
 */
public interface Request {

    @GET("{what}")
    Call<ArrayList<ThreadWeb>> getAll(@Path("what") String path);

    @GET("thread")
    Call<ArrayList<ThreadWeb>> getTop(@QueryMap Map<String, String> param);
}
