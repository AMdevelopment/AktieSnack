package se.amdev.ak_app.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import se.amdev.ak_app.ui.fragment.ThreadsFragment;

public class ThreadsActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getFragment() {
        return new se.amdev.ak_app.ui.fragment.ThreadsFragment();
    }
}
