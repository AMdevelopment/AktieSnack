package se.amdev.ak_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.adapter.PostAdapter;

/**
 * Created by Martin on 01/06/16.
 */
public class PostFragment extends Fragment {

    public static ListView listView;
    public static ListAdapter postAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container, false);

        postAdapter = new PostAdapter(getContext(), ApplicationLoader.postList);
        listView = (ListView) view.findViewById(R.id.main_post_listview);

        System.out.println("LIST SET");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        setList();
        return view;
    }

    public static void setList() {
        listView.setAdapter(postAdapter);
    }
}
