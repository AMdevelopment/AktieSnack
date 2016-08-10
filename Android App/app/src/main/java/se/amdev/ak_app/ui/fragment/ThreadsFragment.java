package se.amdev.ak_app.ui.fragment;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.activity.AnswerActivity;
import se.amdev.ak_app.ui.activity.MainActivity;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.adapter.AlphaAdapter;
import se.amdev.ak_app.ui.adapter.ThreadAdapter;

/**
 * Created by Martin on 31/05/16.
 */
public class ThreadsFragment extends Fragment {

    public static ListView allThreadsListView;
    public static ListView favThreadsListView;
    public static ListView alphaListView;
    public static ListAdapter topThreadAdapter;
    public static ListAdapter allThreadAdapter;
    public static ListAdapter alphaAdapter;
    public static ListAdapter alphaThreadAdapter;
    public static DrawerLayout drawerLayout;
    public static Button logOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_threads_fragment, container, false);

        logOut = (Button) view.findViewById(R.id.user_slide_button);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout_threads);
        allThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.threadList);
        topThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.favThreadList);
        allThreadsListView = (ListView) view.findViewById(R.id.main_thread_listview);
        favThreadsListView = (ListView) view.findViewById(R.id.main_thread_top_listview);
        alphaListView = (ListView) view.findViewById(R.id.alphabet_list);
        alphaAdapter = new AlphaAdapter(getContext(), ApplicationLoader.alphabet);
        alphaListView.setAdapter(alphaAdapter);

        allThreadsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AnswerActivity.threadPosition = position;
                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("Pos", position);
                intent.putExtra("Type", "all");
                startActivity(intent);
            }
        });

        favThreadsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AnswerActivity.threadPosition = position;
                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("Pos", position);
                intent.putExtra("Type", "top");
                startActivity(intent);
            }
        });

        alphaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    setList();
                } else {
                    ApplicationLoader.letterList = new ArrayList<>();
                    for (ThreadWeb t : ApplicationLoader.threadList) {
                        if (t.getThreadNumber().startsWith(ApplicationLoader.alphabet.get(position))) {
                            ApplicationLoader.letterList.add(t);
                        }
                    }

                    alphaThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.letterList);
                    updateAlpha();
                }
            }
        });

        if(ApplicationLoader.user == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else{
            TextView username = (TextView) view.findViewById(R.id.user_slide_username);
            TextView firstname = (TextView) view.findViewById(R.id.user_slide_firstname);
            TextView lastname = (TextView) view.findViewById(R.id.user_slide_lastname);

            username.setText(ApplicationLoader.user.getUsername());
            firstname.setText(ApplicationLoader.user.getFirstName());
            lastname.setText(ApplicationLoader.user.getLastName());
        }
        setList();
        return view;
    }

    public static void setList() {
        allThreadsListView.setAdapter(allThreadAdapter);
        favThreadsListView.setAdapter(topThreadAdapter);
    }

    public static void updateAlpha(){
        allThreadsListView.setAdapter(alphaThreadAdapter);
    }
}
