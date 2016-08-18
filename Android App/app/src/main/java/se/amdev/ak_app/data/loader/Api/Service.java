package se.amdev.ak_app.data.loader.Api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import se.amdev.ak_app.data.adapter.CollectionPostAdapter;
import se.amdev.ak_app.data.adapter.CollectionStockAdapter;
import se.amdev.ak_app.data.adapter.CollectionThreadAdapter;
import se.amdev.ak_app.data.adapter.UserAdapter;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.fragment.LoginFragment;
import se.amdev.ak_app.ui.fragment.PasswordUserFragment;
import se.amdev.ak_app.ui.fragment.PostFragment;
import se.amdev.ak_app.ui.fragment.RegisterUserFragment;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

/**
 * Created by Martin on 31/05/16.
 */
public class Service {

    public static final String BASE_URL = "http://stolktalktommy.nuui2tmhaj.eu-central-1.elasticbeanstalk.com";
    private Retrofit retrofit;
    private Gson gson;

    public void getAllThreads(String what) {
        gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new CollectionThreadAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HashMap<String, String> params = new HashMap<>();

        final Request Api = retrofit.create(Request.class);
        Call<ArrayList<ThreadWeb>> result = Api.getAllThreads(what, params);

        result.enqueue(new Callback<ArrayList<ThreadWeb>>() {

            @Override
            public void onResponse(Call<ArrayList<ThreadWeb>> call, Response<ArrayList<ThreadWeb>> response) {
                if (ApplicationLoader.threadList.isEmpty() || (!ApplicationLoader.threadList.containsAll(response.body()))) {
                    ApplicationLoader.threadList.clear();
                    if (response.body() != null) {
                        ApplicationLoader.threadList.addAll(response.body());
                        ApplicationLoader.setUpThreadHash();
                    }
                }
                if(ThreadsFragment.allThreadsListView != null) {
                    if (!ApplicationLoader.alphaChecker) {
                        ThreadsFragment.setList();
                    }
                    ThreadsFragment.updateDone();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ThreadWeb>> call, Throwable t) {
                Log.d("APITEST", "Could not fetch threads: " + t.getMessage());
            }
        });
    }

    public void getPosts(String what, String threadName) {
        gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new CollectionPostAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HashMap<String, String> params = new HashMap<>();
        params.put("tn", threadName);

        Request Api = retrofit.create(Request.class);
        Call<ArrayList<PostWeb>> result = Api.getAllPosts(what, params);

        result.enqueue(new Callback<ArrayList<PostWeb>>() {

            @Override
            public void onResponse(Call<ArrayList<PostWeb>> call, Response<ArrayList<PostWeb>> response) {
                if (response.body() != null) {
                    ApplicationLoader.postList.addAll(response.body());
                    Collections.reverse(ApplicationLoader.postList);
                }
                PostFragment.updateDone();
            }

            @Override
            public void onFailure(Call<ArrayList<PostWeb>> call, Throwable t) {
                Log.d("APITEST", "Could not fetch posts: " + t.getMessage());
            }
        });
    }

    public void postPosts(String what, HashMap<String, String> param, PostWeb post) {
        gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new CollectionPostAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final HashMap<String, String> params = param;

        Request Api = retrofit.create(Request.class);
        Call<PostWeb> result = Api.postPost(what, params, post);

        result.enqueue(new Callback<PostWeb>() {

            @Override
            public void onResponse(Call<PostWeb> call, Response<PostWeb> response) {
                PostFragment.updatePostView();
                if(params.containsKey("pn")){
                    if(response.code() == 400){
                        PostFragment.alreadyVote();
                    }
                }
            }

            @Override
            public void onFailure(Call<PostWeb> call, Throwable t) {
                Log.d("APITEST", "Could not post the post: " + t.getMessage());
            }
        });
    }

    public void getStocks(String what) {
        gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new CollectionStockAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HashMap<String, String> params = new HashMap<>();

        Request Api = retrofit.create(Request.class);
        Call<ArrayList<StockWeb>> result = Api.getStocks(what);

        result.enqueue(new Callback<ArrayList<StockWeb>>() {

            @Override
            public void onResponse(Call<ArrayList<StockWeb>> call, Response<ArrayList<StockWeb>> response) {
                if (ApplicationLoader.stockWebHashMap.isEmpty()) {
                    ApplicationLoader.stockWebHashMap.clear();
                    if (response.body() != null) {
                        for (StockWeb s : response.body()) {
                            ApplicationLoader.stockWebHashMap.put(s.getStockName(), s);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StockWeb>> call, Throwable t) {
                Log.d("APITEST", "Could not post the post: " + t.getMessage());
            }
        });
    }

    public void registerUser(String what, UserWeb user) {
        gson = new GsonBuilder().registerTypeAdapter(UserWeb.class, new UserAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HashMap<String, String> params = new HashMap<>();

        Request Api = retrofit.create(Request.class);
        Call<UserWeb> result = Api.postUser(what, params, user);

        result.enqueue(new Callback<UserWeb>() {

            @Override
            public void onResponse(Call<UserWeb> call, Response<UserWeb> response) {
                if(response.code() == 201){
                    RegisterUserFragment.regSucc();
                } else {
                    System.out.println(response.code());
                    RegisterUserFragment.registrationFail();
                }
            }

            @Override
            public void onFailure(Call<UserWeb> call, Throwable t) {
                Log.d("APITEST", "Could not post the post: " + t.getMessage());
            }
        });
    }

    public void loginUser(String what, String username, String password) {
        gson = new GsonBuilder().registerTypeAdapter(UserWeb.class, new UserAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://stolktalktommy.nuui2tmhaj.eu-central-1.elasticbeanstalk.com/users/login/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        Request Api = retrofit.create(Request.class);
        Call<UserWeb> result = Api.postUser(what, params, new UserWeb(username, password, "fdsfds", "gfdsgdfs", "dfsgdfsgdfs", "gdfsgdfs", "fdgdfsgdf"));

        result.enqueue(new Callback<UserWeb>() {

            @Override
            public void onResponse(Call<UserWeb> call, Response<UserWeb> response) {
                if(response.code() == 200) {
                    ApplicationLoader.user = response.body();
                    LoginFragment.loginSucc();
                    if(LoginFragment.getSaveUserBoolean()){
                        LoginFragment.saveUser(response.body());
                    }
                } else {
                    LoginFragment.loginFail();
                }
            }

            @Override
            public void onFailure(Call<UserWeb> call, Throwable t) {
                Log.d("APITEST", "Could not post the post: " + t.getMessage());
            }
        });
    }

    public void changeUserPass(String what, UserWeb user) {
        gson = new GsonBuilder().registerTypeAdapter(UserWeb.class, new UserAdapter()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HashMap<String, String> params = new HashMap<>();

        Request Api = retrofit.create(Request.class);
        Call<UserWeb> result = Api.putUser(what, user);

        result.enqueue(new Callback<UserWeb>() {

            @Override
            public void onResponse(Call<UserWeb> call, Response<UserWeb> response) {
                if(response.code() == 200) {
                    PasswordUserFragment.passSucc();
                } else {
                    PasswordUserFragment.passFail();
                }
            }

            @Override
            public void onFailure(Call<UserWeb> call, Throwable t) {
                Log.d("APITEST", "Could not post the post: " + t.getMessage());
            }
        });
    }
}
