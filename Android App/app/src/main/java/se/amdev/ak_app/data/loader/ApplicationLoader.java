package se.amdev.ak_app.data.loader;

import android.app.Application;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.Api.Service;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.activity.MainActivity;

/**
 * Created by Martin on 31/05/16.
 */
public class ApplicationLoader extends Application {

    public static Service api;
    public static ArrayList<ThreadWeb> threadList;
    public static ArrayList<ThreadWeb> letterList;
    public static ArrayList<ThreadWeb> favThreadList;
    public static ArrayList<PostWeb> postList;
    public static HashMap<String, StockWeb> stockWebHashMap;
    public static HashMap<Integer, String> alphabetHash;
    public static ArrayList<String> alphabet;
    public static UserWeb user;
    public static String actualList;

    public ApplicationLoader() {
        api = new Service();
        threadList = new ArrayList<>();
        letterList = new ArrayList<>();
        favThreadList = new ArrayList<>();
        postList = new ArrayList<>();
        stockWebHashMap = new HashMap<>();
        user = new UserWeb("martinfalk", "axelwredlert@gmail.com", "Axel", "Wredlert", "34", "54");
        alphabetHash = new HashMap<>();
        alphabet = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loaderThread();
        System.out.println("HOLLAAAAAA JAG LADDAR");
        setAlphabet();
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
        String threadName;
        if (actualList.equals("top")) {
            threadName = favThreadList.get(position).getThreadNumber();
        } else {
            threadName = threadList.get(position).getThreadNumber();
        }
        api.postPosts("posts", threadName, user.getUsername(), new PostWeb(text));
    }

    public static void loaderThread() {
        api.getStocks("stocks");
        api.getAllThreads("threads");
    }

    public static void loadPosts(String threadName, int position) {
        api.getPosts("posts", threadName, position);
        System.out.println("THREADNAME: " + threadName);
        System.out.println("POSITION: " + position);
    }

    public static void getPost(int position, String type) {
        postList.clear();
        if (type.equals("top")) {
            loadPosts(favThreadList.get(position).getThreadNumber(), position);
            actualList = "top";
        } else if (type.equals("all")) {
            loadPosts(threadList.get(position).getThreadNumber(), position);
            actualList = "all";
        }
    }
}
