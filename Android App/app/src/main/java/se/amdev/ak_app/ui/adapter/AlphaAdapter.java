package se.amdev.ak_app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;

/**
 * Created by Martin on 09/08/16.
 */
public class AlphaAdapter extends ArrayAdapter<String> {
    private View view;

    public AlphaAdapter(Context context, ArrayList<String> alpha){
        super(context, R.layout.alpha_row, alpha);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater threadInflater = LayoutInflater.from(getContext());

        view = threadInflater.inflate(R.layout.alpha_row, parent, false);
        String alpha = getItem(position);
        TextView alphaText = (TextView) view.findViewById(R.id.alpha_letter);

        alphaText.setText(alpha);

        return view;
    }
}