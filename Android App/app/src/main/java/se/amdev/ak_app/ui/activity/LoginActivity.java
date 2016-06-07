package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.fragment.LoginFragment;

/**
 * Created by Martin on 07/06/16.
 */
public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ApplicationLoader.user != null){
            Intent intent = new Intent(getApplication(), ThreadsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected Fragment getFragment() {
        return new LoginFragment();
    }
}
