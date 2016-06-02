package se.amdev.ak_app.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import se.amdev.ak_app.R;

/**
 * Created by Martin on 31/05/16.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.single_fragment);

        if(fragment == null) {
            fragment = getFragment();
            manager.beginTransaction().add(R.id.single_fragment, fragment).commit();
        }
    }

    protected abstract Fragment getFragment();
}
