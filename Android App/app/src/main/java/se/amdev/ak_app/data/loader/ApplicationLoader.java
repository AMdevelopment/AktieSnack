package se.amdev.ak_app.data.loader;

import android.app.Application;
import android.content.Context;
import android.view.View;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.LocalStorageHandler;
import se.amdev.ak_app.data.MD5encryption;
import se.amdev.ak_app.data.loader.Api.Service;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

/**
 * Created by Martin on 31/05/16.
 */
public class ApplicationLoader extends Application {

    public static Service api;
    public static ArrayList<ThreadWeb> threadList;
    public static ArrayList<ThreadWeb> letterList;
    public static ArrayList<ThreadWeb> favoritList;
    public static ArrayList<PostWeb> postList;
    public static HashMap<String, ThreadWeb> threadWebHashMap;
    public static HashMap<String, StockWeb> stockWebHashMap;
    public static HashMap<Integer, String> alphabetHash;
    public static ArrayList<String> alphabet;
    public static UserWeb user;
    public static String actualList;
    public static boolean alphaChecker = false;
    public static int threadPosition;
    private static Context context;

    public ApplicationLoader() {
        api = new Service();
        threadList = new ArrayList<>();
        letterList = new ArrayList<>();
        postList = new ArrayList<>();
        stockWebHashMap = new HashMap<>();
        alphabetHash = new HashMap<>();
        threadWebHashMap = new HashMap<>();
        alphabet = new ArrayList<>();
        favoritList = new ArrayList<>();
//        favoritList.add(new ThreadWeb("dd",",",new ArrayList<PostWeb>(),"","","",""));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        getLocalStorage();
        loaderThread();
        setAlphabet();
    }

    public void getLocalStorage(){
        UserWeb userWeb = LocalStorageHandler.getUser(getBaseContext());
        if(userWeb != null){
            user = userWeb;
        } else {
            user = null;
        }
    }

    public static void setUpThreadHash(){
        for(ThreadWeb t : threadList){
            threadWebHashMap.put(t.getThreadNumber(), t);
        }
        LocalStorageHandler.getFavorit(context);
    }

    public void setAlphabet(){
       List<String> alpha = Arrays.asList(getResources().getStringArray(R.array.alphabet));
        int i = 0;
        for(String a : alpha){
            alphabetHash.put(i, a);
            alphabet.add(a);
            i++;
        }
    }

    public static void post(int position, String text) {
        HashMap<String, String> params = new HashMap<>();
        params.put("tn", PostActivity.threadName);
        params.put("user", user.getUsername());
        api.postPosts("posts", params, new PostWeb(text));
    }

    public static void loginUser(String username, String password){
        //do encryption
        api.loginUser("", username, password);
    }

    public static void regUser(UserWeb user){
        //do encryption
        api.registerUser("users", user);
    }

    public static void loaderThread() {
        api.getStocks("stocks");
        api.getAllThreads("threads");
    }

    public static void loadPosts(String threadName) {
        postList.clear();
        api.getPosts("posts", threadName);
    }

    public static void changePass(String password){
        try {
            api.changeUserPass("users", user.setPassword(MD5encryption.encrypt(password)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void votePost(String direction, String postNumber){
        HashMap<String, String> params = new HashMap<>();
        params.put("pn", postNumber);
        params.put("vote", direction);
        params.put("user", user.getUsername());
        api.postPosts("posts", params, new PostWeb(""));
    }
}
