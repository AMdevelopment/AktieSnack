package se.amdev.ak_app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
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
import se.amdev.ak_app.data.LocalStorageHandler;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.activity.LoginActivity;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.activity.ThreadActivity;
import se.amdev.ak_app.ui.adapter.AlphaAdapter;
import se.amdev.ak_app.ui.adapter.ThreadAdapter;

/**
 * Created by Martin on 31/05/16.
 */
public class ThreadsFragment extends Fragment {

    public static ListView allThreadsListView;
    public static ListView alphaListView;
    public static ListView favListView;
    public static ListAdapter allThreadAdapter;
    public static ListAdapter alphaAdapter;
    public static ListAdapter alphaThreadAdapter;
    public static ListAdapter favThreadAdapter;
    public static DrawerLayout drawerLayout;
    public static Button logOut;
    public static Button refresh;
    public static Button user;
    public static Button passwordButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threads_fragment, container, false);

        logOut = (Button) view.findViewById(R.id.user_slide_button_logout);
        refresh = (Button) view.findViewById(R.id.toolbar_threads_refresh);
        user = (Button) view.findViewById(R.id.toolbar_threads_user);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout_threads);
        allThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.threadList);
        allThreadsListView = (ListView) view.findViewById(R.id.main_thread_listview);
        alphaListView = (ListView) view.findViewById(R.id.alphabet_list);
        favListView = (ListView) view.findViewById(R.id.user_slide_favlistview);
        favThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.favoritList);
        passwordButton = (Button) view.findViewById(R.id.user_slide_button_changepassword);
        alphaAdapter = new AlphaAdapter(getContext(), ApplicationLoader.alphabet);
        alphaListView.setAdapter(alphaAdapter);

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("password", true);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalStorageHandler.logOut(getContext());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("logout", true);
                startActivity(intent);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ThreadActivity) getActivity()).updateThreads();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        allThreadsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationLoader.threadPosition = position;
                Intent intent = new Intent(getActivity(), PostActivity.class);

                if (ApplicationLoader.alphaChecker) {
                    intent.putExtra("threadName", ApplicationLoader.letterList.get(position).getThreadNumber());
                    intent.putExtra("stockName", ApplicationLoader.letterList.get(position).getDescription());
                } else {
                    intent.putExtra("threadName", ApplicationLoader.threadList.get(position).getThreadNumber());
                    intent.putExtra("stockName", ApplicationLoader.threadList.get(position).getDescription());
                }
                startActivity(intent);
            }
        });

        favListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("threadName", ApplicationLoader.favoritList.get(position).getThreadNumber());
                startActivity(intent);
            }
        });

        alphaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ApplicationLoader.alphaChecker = false;
                    setList();
                } else {
                    ApplicationLoader.alphaChecker = true;
                    ApplicationLoader.letterList = new ArrayList<>();
                    for (ThreadWeb t : ApplicationLoader.threadList) {
                        if (t.getThreadNumber().startsWith(ApplicationLoader.alphabet.get(position))) {
                            ApplicationLoader.letterList.add(t);
                        }
                    }
                    alphaThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.letterList);
                    setAlpha();
                }
            }
        });

        if (ApplicationLoader.user == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            TextView username = (TextView) view.findViewById(R.id.user_slide_username);
            TextView firstname = (TextView) view.findViewById(R.id.user_slide_firstname);
            TextView lastname = (TextView) view.findViewById(R.id.user_slide_lastname);
            TextView email = (TextView) view.findViewById(R.id.user_slide_email);

            username.setText(ApplicationLoader.user.getUsername());
            firstname.setText(ApplicationLoader.user.getFirstName());
            lastname.setText(ApplicationLoader.user.getLastName());
            email.setText(ApplicationLoader.user.getEmail());
        }

        setList();
        setFav();
        return view;
    }

    public static void setList() {
        allThreadsListView.setAdapter(allThreadAdapter);
    }

    public static void setAlpha() {
        allThreadsListView.setAdapter(alphaThreadAdapter);
    }

    public static void setFav() {
        if(favListView != null) {
            favListView.setAdapter(favThreadAdapter);
        }
    }

    public static void updateDone(){
        ThreadActivity.refreshBar.setVisibility(View.GONE);
        refresh.setVisibility(View.VISIBLE);
    }
}
