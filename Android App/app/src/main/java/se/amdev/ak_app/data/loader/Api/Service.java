package se.amdev.ak_app.data.loader.Api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import se.amdev.ak_app.data.adapter.CollectionThreadAdapter;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.fragment.MainFragment;

/**
 * Created by Martin on 31/05/16.
 */
public class Service {

    public static final String BASE_URL_THREADS = "http://hello.2dphxrmygg.us-west-2.elasticbeanstalk.com/";
    private Retrofit retrofit;
    private Gson gson;

    public void getAll(String what){
        gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new CollectionThreadAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_THREADS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Request Api = retrofit.create(Request.class);
        Call<ArrayList<ThreadWeb>> result = Api.getAll(what);

        result.enqueue(new Callback<ArrayList<ThreadWeb>>() {

            @Override
            public void onResponse(Call<ArrayList<ThreadWeb>> call, Response<ArrayList<ThreadWeb>> response) {
                ApplicationLoader.threadList.addAll(response.body());
                MainFragment.setList();
            }

            @Override
            public void onFailure(Call<ArrayList<ThreadWeb>> call, Throwable t) {
                Log.d("APITEST", "Could not fetch threads: " + t.getMessage());
            }
        });
    }
}