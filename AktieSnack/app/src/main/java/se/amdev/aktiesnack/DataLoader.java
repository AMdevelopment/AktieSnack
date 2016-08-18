package se.amdev.aktiesnack;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import se.amdev.aktiesnack.model.PostWeb;
import se.amdev.aktiesnack.model.ThreadWeb;
import se.amdev.aktiesnack.model.UserWeb;

/**
 * Created by Martin on 2016-08-12.
 */
public class DataLoader extends Application{

    private static ArrayList<ThreadWeb> threads;

    @Override
    public void onCreate() {
        for(int i = 0; i < 34; i++){
            threads = new ArrayList<>();
            ArrayList<PostWeb> posts = new ArrayList<>();
            UserWeb user = new UserWeb("sdf", "dsf", "sf", "sdf", "fds", "fsdf");
            posts.add(new PostWeb(user, "dfsf", "dsfds", "sdfgsd", "dsfsd"));
            threads.add(new ThreadWeb("sdf", "dsf", posts, "sf", "sdf", "fds", "fsdf"));
            System.out.println("HEJ" + i);
        }
        super.onCreate();
    }

    public static ArrayList<ThreadWeb> getThreads() {
        return threads;
    }
}
