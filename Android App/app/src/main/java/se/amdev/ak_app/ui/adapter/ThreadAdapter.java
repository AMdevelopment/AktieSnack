package se.amdev.ak_app.ui.adapter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.model.ThreadWeb;

/**
 * Created by Martin on 31/05/16.
 */
public class ThreadAdapter extends ArrayAdapter<ThreadWeb> {
    private View view;

    public ThreadAdapter(Context context, ArrayList<ThreadWeb> threads){
        super(context, R.layout.thread_row, threads);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater threadInflater = LayoutInflater.from(getContext());

        view = threadInflater.inflate(R.layout.thread_row, parent, false);
        ThreadWeb thread = getItem(position);
        TextView nameText = (TextView) view.findViewById(R.id.thread_name);
        TextView amountText = (TextView) view.findViewById(R.id.thread_amount);

        nameText.setText(thread.getDescription());
        if(thread.getPosts() == null) {
            amountText.setText("0");
        }else{
            amountText.setText(String.valueOf(thread.getPosts().size()));
        }

        return view;
    }
}