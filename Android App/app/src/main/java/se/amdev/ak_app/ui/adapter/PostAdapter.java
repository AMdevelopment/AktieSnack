package se.amdev.ak_app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.PostWeb;
import se.amdev.ak_app.data.model.ThreadWeb;

/**
 * Created by Martin on 01/06/16.
 */
public class PostAdapter extends ArrayAdapter<PostWeb> {
    private View view;
    private ArrayList<PostWeb> postWebs;
    public static TextView vote;
    public static ImageView dislike;
    public static ImageView like;

    public PostAdapter(Context context, ArrayList<PostWeb> posts) {
        super(context, R.layout.post_row, posts);
        postWebs = posts;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater threadInflater = LayoutInflater.from(getContext());

        view = threadInflater.inflate(R.layout.post_row, parent, false);
        PostWeb post = getItem(position);
        TextView username = (TextView) view.findViewById(R.id.post_username);
        TextView text = (TextView) view.findViewById(R.id.post_text);
        TextView time = (TextView) view.findViewById(R.id.post_time);
        vote = (TextView) view.findViewById(R.id.post_vote);
        dislike = (ImageView) view.findViewById(R.id.post_button_dislike);
        like = (ImageView) view.findViewById(R.id.post_button_like);

        RelativeLayout footer = (RelativeLayout) view.findViewById(R.id.post_footer_border);

        if (position == postWebs.size() - 1) {
            footer.setVisibility(View.GONE);
        }

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationLoader.votePost("up", ApplicationLoader.postList.get(position).getPostId());
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationLoader.votePost("down", ApplicationLoader.postList.get(position).getPostId());
            }
        });

        vote.setText(String.valueOf(post.getVote()));
        username.setText(post.getUser().getUsername());
        text.setText(post.getText());
        time.setText(post.getCreationTime());

        return view;
    }
}
