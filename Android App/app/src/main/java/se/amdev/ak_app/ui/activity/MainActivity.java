package se.amdev.ak_app.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import java.util.ArrayList;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.ui.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getFragment() {
        return new MainFragment();
    }
}
