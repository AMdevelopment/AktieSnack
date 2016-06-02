package se.amdev.ak_app.data.loader.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import se.amdev.ak_app.data.model.ThreadWeb;


/**
 * Created by Martin on 31/05/16.
 */
public interface Request {

    @GET("{what}")
    Call<ArrayList<ThreadWeb>> getAll(@Path("what") String path);
}
