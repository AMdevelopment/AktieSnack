package se.amdev.ak_app.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.amdev.ak_app.data.loader.ApplicationLoader;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ApplicationLoader.user != null) {
            Intent intent = new Intent(this, ThreadActivity.class);
            startActivity(intent);
            finish();
        } else {
            System.out.println("SPLAAAAAASH");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
