package se.amdev.aktiesnack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import se.amdev.aktiesnack.DataLoader;
import se.amdev.aktiesnack.R;
import se.amdev.aktiesnack.adapter.ThreadsListAdapter;
import se.amdev.aktiesnack.model.ThreadWeb;

/**
 * Created by Martin on 2016-08-11.
 */


public class ThreadsFragment extends Fragment {

    private static ListView threadsListView;
    private static ArrayList<ThreadWeb> threads;
    private static ListAdapter threadsAdapter;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        threads = DataLoader.getThreads();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_threads, container, false);

        threadsAdapter = new ThreadsListAdapter(getContext(), threads);
        threadsListView = (ListView) view.findViewById(R.id.threads_listview);

        threadsListView.setAdapter(threadsAdapter);
        System.out.println("HEJ");
        return view;
    }
}
