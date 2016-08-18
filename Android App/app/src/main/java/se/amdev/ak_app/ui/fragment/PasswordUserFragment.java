package se.amdev.ak_app.ui.fragment;

/**
 * Created by Martin on 2016-08-15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.MD5encryption;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.activity.LoginActivity;

/**
 * Created by Martin on 07/06/16.
 */
public class PasswordUserFragment extends Fragment {

    private static FragmentActivity activity;
    private static Context context;
    private static EditText passOne;
    private static EditText passTwo;
    private static Button changeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_reset_fragment, container, false);

        context = getContext();
        activity = getActivity();

        passOne = (EditText) view.findViewById(R.id.user_change_password_1);
        passTwo = (EditText) view.findViewById(R.id.user_change_password_2);
        changeButton = (Button) view.findViewById(R.id.password_button);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordOne = passOne.getText().toString();
                String passwordTwo = passTwo.getText().toString();

                if(passwordOne.equals(passwordTwo)){
                    ApplicationLoader.changePass(passwordOne);
                } else {
                    Toast.makeText(context, R.string.passwordscreen_match, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public static void passSucc(){
        Toast.makeText(context, R.string.passwordscreen_success, Toast.LENGTH_SHORT).show();
    }

    public static void passFail(){
        Toast.makeText(context, R.string.passwordscreen_fail, Toast.LENGTH_SHORT).show();
    }
}
