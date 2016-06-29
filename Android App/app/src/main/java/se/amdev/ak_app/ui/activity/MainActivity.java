package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.LoginFragment;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
