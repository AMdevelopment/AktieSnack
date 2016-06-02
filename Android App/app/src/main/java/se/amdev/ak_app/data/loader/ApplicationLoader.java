package se.amdev.ak_app.data.loader;

import android.app.Application;

import java.util.ArrayList;

import se.amdev.ak_app.data.loader.Api.Service;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.activity.MainActivity;

/**
 * Created by Martin on 31/05/16.
 */
public class ApplicationLoader extends Application{

    public static ArrayList<ThreadWeb> threadList;
    public static ArrayList<PostWeb> postList;

    public ApplicationLoader(){
        threadList = new ArrayList<>();
        postList = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loader();
    }

    public void loader(){
        Service apiService = new Service();
        apiService.getAll("thread");
    }

    public static void getPost(int position){
        postList.clear();
        postList.addAll(threadList.get(position).getPosts());
    }
}
