package se.amdev.ak_app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.PostFragment;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

public class PostActivity extends SingleFragmentActivity {

    public static ProgressBar postBar;
    public static String threadName;
    public static String stockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        threadName = intent.getStringExtra("threadName");
        stockName = intent.getStringExtra("stockName");
        ApplicationLoader.loadPosts(threadName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        postBar = (ProgressBar) findViewById(R.id.toolbar_post_bar);
        postBar.setIndeterminate(true);
        postBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
        PostFragment.refresh.setVisibility(View.GONE);
        postBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if(PostFragment.answerShowed) {
            PostFragment.postLayout.setVisibility(View.GONE);
            PostFragment.answerShowed = false;
        } else {
            super.onBackPressed();
        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    @Override
    protected Fragment getFragment() {
        return new PostFragment();
    }
}
