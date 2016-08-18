package se.amdev.aktiesnack.activity;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.amdev.aktiesnack.R;
import se.amdev.aktiesnack.fragment.ThreadsFragment;

public class ThreadsListActivity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected Fragment getFragment() {
        return new ThreadsFragment();
    }
}
