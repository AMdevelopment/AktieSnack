package se.amdev.aktiesnack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import se.amdev.aktiesnack.R;
import se.amdev.aktiesnack.model.ThreadWeb;

/**
 * Created by Martin on 2016-08-12.
 */
public class ThreadsListAdapter extends ArrayAdapter<ThreadWeb>{

    private View view;

    public ThreadsListAdapter(Context context, ArrayList<ThreadWeb> threads){
        super(context, R.layout.thread_listview_row, threads);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater threadInflater = LayoutInflater.from(getContext());

        view = threadInflater.inflate(R.layout.thread_listview_row, parent, false);
        ThreadWeb threadWeb = getItem(position);
        TextView nameText = (TextView) view.findViewById(R.id.thread_name);
        TextView amountText = (TextView) view.findViewById(R.id.thread_amount);

        nameText.setText(threadWeb.getThreadNumber());

        amountText.setText(threadWeb.getNumberOfPosts());

        return view;
    }
}
