package se.amdev.ak_app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.ThreadWeb;

/**
 * Created by Martin on 01/06/16.
 */
public class PostAdapter extends ArrayAdapter<PostWeb> {
    private View view;

    public PostAdapter(Context context, ArrayList<PostWeb> posts){
        super(context, R.layout.post_row, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater threadInflater = LayoutInflater.from(getContext());

        view = threadInflater.inflate(R.layout.post_row, parent, false);
        PostWeb post = getItem(position);
        TextView username = (TextView) view.findViewById(R.id.post_username);
        TextView text = (TextView) view.findViewById(R.id.post_text);
        TextView time = (TextView) view.findViewById(R.id.post_time);
        TextView answer = (TextView) view.findViewById(R.id.post_answer);

        username.setText(post.getUser().getUsername());
        text.setText(post.getText());
        time.setText(post.getCreationTime());
        answer.setText("ANSWER");
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ANSWER", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
