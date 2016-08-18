package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import se.amdev.ak_app.ui.fragment.LoginFragment;
import se.amdev.ak_app.ui.fragment.PasswordUserFragment;
import se.amdev.ak_app.ui.fragment.RegisterUserFragment;

/**
 * Created by Martin on 07/06/16.
 */
public class LoginActivity extends SingleFragmentActivity {

    boolean logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        logout = intent.getBooleanExtra("logout", false);
    }

    @Override
    public void onBackPressed() {
        if (!logout) {
            super.onBackPressed();
        }
    }

    public void loginSuccesful() {
        Intent intent = new Intent(getApplication(), ThreadActivity.class);
        startActivity(intent);
    }

    public void regSuccesful() {
        Intent intent = new Intent(getApplication(), LoginActivity.class);
        intent.putExtra("register", false);
        startActivity(intent);
    }

    @Override
    protected Fragment getFragment() {
        if (getIntent().getBooleanExtra("register", false)) {
            return new RegisterUserFragment();
        } else if (getIntent().getBooleanExtra("password", false)) {
            return new PasswordUserFragment();
        } else {
            return new LoginFragment();
        }
    }
}

