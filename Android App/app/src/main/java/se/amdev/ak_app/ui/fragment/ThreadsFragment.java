package se.amdev.ak_app.ui.fragment;

import android.content.Intent;
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

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.adapter.ThreadAdapter;

/**
 * Created by Martin on 31/05/16.
 */
public class ThreadsFragment extends Fragment {

    public static ListView allThreadsListView;
    public static ListView topThreadsListView;
    public static ListAdapter topThreadAdapter;
    public static ListAdapter allThreadAdapter;
    public static DrawerLayout drawerLayout;
    public static Button logOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threads_fragment, container, false);

        logOut = (Button) view.findViewById(R.id.user_slide_button);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout_threads);
        allThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.threadList);
        topThreadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.topThreadList);
        allThreadsListView = (ListView) view.findViewById(R.id.main_thread_listview);
        topThreadsListView = (ListView) view.findViewById(R.id.main_thread_top_listview);

        allThreadsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("Pos", position);
                intent.putExtra("Type", "all");
                startActivity(intent);
            }
        });

        topThreadsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("Pos", position);
                intent.putExtra("Type", "top");
                startActivity(intent);
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
        topThreadsListView.setAdapter(topThreadAdapter);
    }
}
