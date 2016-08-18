package se.amdev.ak_app.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.StockWeb;
import se.amdev.ak_app.data.model.ThreadWeb;

/**
 * Created by Martin on 31/05/16.
 */
public class ThreadAdapter extends ArrayAdapter<ThreadWeb> {
    private View view;

    public ThreadAdapter(Context context, ArrayList<ThreadWeb> threads) {
        super(context, R.layout.thread_row, threads);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater threadInflater = LayoutInflater.from(getContext());

        view = threadInflater.inflate(R.layout.thread_row, parent, false);
        ThreadWeb thread = getItem(position);
        TextView nameText = (TextView) view.findViewById(R.id.thread_name);
        TextView amountText = (TextView) view.findViewById(R.id.thread_amount);

        nameText.setText(thread.getThreadNumber());

        StockWeb stock = ApplicationLoader.stockWebHashMap.get(thread.getDescription());

        ApplicationLoader.threadList.get(position).setStock(stock);

        if((position % 2) == 0){
            view.setBackgroundColor(Color.parseColor("#FFE4E4E4"));
        }

        if(stock != null) {
            if (stock.getChangePercent().startsWith("-")) {
                amountText.setTextColor(Color.RED);
            } else {
                amountText.setTextColor(Color.parseColor("#009700"));
            }
            amountText.setText(stock.getChangePercent());
        } else {
            amountText.setText("n/a");
        }
        return view;
    }
}