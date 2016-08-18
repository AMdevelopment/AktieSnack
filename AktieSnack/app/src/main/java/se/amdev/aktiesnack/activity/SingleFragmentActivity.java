package se.amdev.aktiesnack.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import se.amdev.aktiesnack.R;

/**
 * Created by Martin on 2016-08-12.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_single_fragment);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = getFragment();
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        System.out.println("SINGLE");
    }

    protected abstract Fragment getFragment();
}
