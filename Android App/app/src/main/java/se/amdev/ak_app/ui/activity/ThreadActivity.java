package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.LoginFragment;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

public class ThreadActivity extends SingleFragmentActivity {

    public static ProgressBar refreshBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ApplicationLoader.threadList != null) {
            updateThreads();
        }
    }

    @Override
    public void onBackPressed() {
        if(ThreadsFragment.drawerLayout.isDrawerOpen(GravityCompat.START)){
            ThreadsFragment.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void updateThreads(){
        ThreadsFragment.refresh.setVisibility(View.GONE);
        refreshBar = (ProgressBar) findViewById(R.id.toolbar_threads_bar);
        refreshBar.setIndeterminate(true);
        refreshBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
        refreshBar.setVisibility(View.VISIBLE);
        ApplicationLoader.loaderThread();
    }

    @Override
    protected Fragment getFragment() {
        return new ThreadsFragment();
    }
}
