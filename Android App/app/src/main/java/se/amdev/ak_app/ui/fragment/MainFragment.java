package se.amdev.ak_app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.activity.MainActivity;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.adapter.ThreadAdapter;

/**
 * Created by Martin on 31/05/16.
 */
public class MainFragment extends Fragment {

    public static ListView listView;
    public static ListAdapter threadAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        threadAdapter = new ThreadAdapter(getContext(), ApplicationLoader.threadList);
        listView = (ListView) view.findViewById(R.id.main_thread_listview);

        System.out.println("LIST SET");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("Pos", position);
                startActivity(intent);
            }
        });

        setList();
        return view;
    }

    public static void setList() {
        listView.setAdapter(threadAdapter);
    }
}
