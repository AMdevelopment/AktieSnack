package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.PostFragment;

public class PostActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int position = intent.getIntExtra("Pos", 0);
        String type = intent.getStringExtra("Type");
        ApplicationLoader.getPost(position, type);
    }

    @Override
    protected Fragment getFragment() {
        return new PostFragment();
    }
}
