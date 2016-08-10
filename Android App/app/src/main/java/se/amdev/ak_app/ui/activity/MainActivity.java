package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.LoginFragment;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

public class MainActivity extends SingleFragmentActivity {

    public static ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bar = (ProgressBar) findViewById(R.id.progress_bar_main);
        bar.setVisibility(View.VISIBLE);
        ApplicationLoader.loaderThread();
    }

    @Override
    protected Fragment getFragment() {
        Intent intent = getIntent();
        if(ApplicationLoader.user != null) {
            return new ThreadsFragment();
        } else {
            if(intent.getStringExtra("guest") != null){
                if(intent.getStringExtra("guest").equals("true")){
                    return new ThreadsFragment();
                }
            }
            return new LoginFragment();
        }
    }
}
