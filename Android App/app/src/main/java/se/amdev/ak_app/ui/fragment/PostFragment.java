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
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.activity.AnswerActivity;
import se.amdev.ak_app.ui.adapter.PostAdapter;

/**
 * Created by Martin on 01/06/16.
 */
public class PostFragment extends Fragment {

    public static ListView listView;
    public static ListAdapter postAdapter;
    public static DrawerLayout drawerLayout;
    public static Button logOut;
    public static Button post;
    public static Button fav;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);

        post = (Button) view.findViewById(R.id.post_button_post);
        fav = (Button) view.findViewById(R.id.post_button_fav);
        logOut = (Button) view.findViewById(R.id.user_slide_button);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout_post);
        postAdapter = new PostAdapter(getContext(), ApplicationLoader.postList);
        listView = (ListView) view.findViewById(R.id.main_post_listview);

        System.out.println("LIST SET");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        if(ApplicationLoader.user == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            TextView username = (TextView) view.findViewById(R.id.user_slide_username);
            TextView firstname = (TextView) view.findViewById(R.id.user_slide_firstname);
            TextView lastname = (TextView) view.findViewById(R.id.user_slide_lastname);

            username.setText(ApplicationLoader.user.getUsername());
            firstname.setText(ApplicationLoader.user.getFirstName());
            lastname.setText(ApplicationLoader.user.getLastName());
        }

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AnswerActivity.class);
                startActivity(intent);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.getText().equals("FAV")){
                    ApplicationLoader.favThreadList.add(ApplicationLoader.threadList.get(AnswerActivity.threadPosition));

                } else {
                    ApplicationLoader.favThreadList.remove(ApplicationLoader.threadList.get(AnswerActivity.threadPosition));
                }
                changeButton();
            }
        });

        setList();
        return view;
    }

    public static void setList() {
        listView.setAdapter(postAdapter);
    }

    public static void changeButton() {
        if(ApplicationLoader.favThreadList.contains(ApplicationLoader.threadList.get(AnswerActivity.threadPosition))){
            fav.setText("UNFAV");
        } else {
            fav.setText("FAV");
        }
    }
}
