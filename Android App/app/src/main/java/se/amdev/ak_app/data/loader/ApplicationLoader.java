package se.amdev.ak_app.data.loader;

import android.app.Application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import se.amdev.ak_app.data.loader.Api.Service;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

/**
 * Created by Martin on 31/05/16.
 */
public class ApplicationLoader extends Application {

    public static ArrayList<ThreadWeb> threadList;
    public static ArrayList<ThreadWeb> topThreadList;
    public static ArrayList<PostWeb> postList;
    public static UserWeb user;

    public ApplicationLoader() {
        threadList = new ArrayList<>();
        topThreadList = new ArrayList<>();
        postList = new ArrayList<>();
        user = new UserWeb("axWre", "axelwredlert@gmail.com", "Axel", "Wredlert", "34", "54");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loaderThread();
        loaderTopThread();
    }

    public void loaderThread() {
        Service api = new Service();
        api.getAllThreads("thread");
    }

    public void loaderTopThread() {
        Service api = new Service();
        api.getTopThreads("thread?tn=102");
    }

    public static void getPost(int position, String type) {
        postList.clear();
        if (type.equals("top")) {
            postList.addAll(topThreadList.get(position).getPosts());
            Collections.reverse(postList);
        } else if (type.equals("all")) {
            postList.addAll(threadList.get(position).getPosts());
            Collections.reverse(postList);
        }
    }
}
