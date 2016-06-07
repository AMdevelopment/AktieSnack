package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.AnswerFragment;
import se.amdev.ak_app.ui.fragment.LoginFragment;

/**
 * Created by Martin on 07/06/16.
 */
public class AnswerActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getFragment() {
        return new AnswerFragment();
    }
}

