package se.amdev.ak_app.ui.fragment;

/**
 * Created by Martin on 2016-08-15.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class RegisterUserFragment extends Fragment {

    private Button registerButton;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText username;
    private EditText password;
    private static FragmentActivity activity;
    private static Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_user_fragment, container, false);

        context = getContext();
        activity = getActivity();

        registerButton = (Button) view.findViewById(R.id.register_button);
        firstName = (EditText) view.findViewById(R.id.user_register_firstname);
        lastName = (EditText) view.findViewById(R.id.user_register_lastname);
        email = (EditText) view.findViewById(R.id.user_register_email);
        username = (EditText) view.findViewById(R.id.user_register_username);
        password = (EditText) view.findViewById(R.id.user_register_password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ApplicationLoader.regUser(new UserWeb(username.getText().toString(), MD5encryption.encrypt(password.getText().toString()),
                            email.getText().toString(), firstName.getText().toString(), lastName.getText().toString()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    public static void regSucc(){
        ((LoginActivity)activity).regSuccesful();
    }

    public static void registrationFail(){
        Toast.makeText(context, R.string.regscreen_reg_fail, Toast.LENGTH_SHORT).show();
    }
}
