package se.amdev.ak_app.data.loader.Api;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;


/**
 * Created by Martin on 31/05/16.
 */
public interface Request {

    @GET("{what}")
    Call<ArrayList<ThreadWeb>> getAllThreads(@Path("what") String path, @QueryMap Map<String, String> param);

    @GET("{what}")
    Call<ArrayList<PostWeb>> getAllPosts(@Path("what") String path, @QueryMap Map<String, String> param);

    @POST("{what}")
    Call<UserWeb> postUser(@Path("what") String path, @QueryMap Map<String, String> param, @Body UserWeb user);

    @PUT("{what}")
    Call<UserWeb> putUser(@Path("what") String path, @Body UserWeb user);

    @POST("{what}")
    Call<UserWeb> logUser(@Path("what") String path, @QueryMap Map<String, String> param);


    @GET("{what}")
    Call<ArrayList<StockWeb>> getStocks(@Path("what") String path);

    @POST("{what}")
    Call<PostWeb> postPost(@Path("what") String path, @QueryMap Map<String, String> param, @Body PostWeb post);
}
