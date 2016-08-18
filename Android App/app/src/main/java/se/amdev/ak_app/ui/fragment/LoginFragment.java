package se.amdev.ak_app.ui.fragment;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.LocalStorageHandler;
import se.amdev.ak_app.data.MD5encryption;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.activity.LoginActivity;

/**
 * Created by Martin on 07/06/16.
 */
public class LoginFragment extends Fragment {

    private Button registerButton;
    private Button loginButton;
    private TextView headerText;
    private EditText password;
    private EditText username;
    private CheckBox checkBox;
    private static boolean saveUser;
    private static FragmentActivity activity;
    public static Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        activity = getActivity();
        context = getContext();

        registerButton = (Button) view.findViewById(R.id.user_register_button);
        loginButton = (Button) view.findViewById(R.id.user_login_button);
        headerText = (TextView) view.findViewById(R.id.user_login_headertext);
        password = (EditText) view.findViewById(R.id.user_login_password);
        username = (EditText) view.findViewById(R.id.user_login_username);
        checkBox = (CheckBox) view.findViewById(R.id.user_login_checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveUser = isChecked;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ApplicationLoader.loginUser(username.getText().toString(), MD5encryption.encrypt(password.getText().toString()));
                    System.out.println(MD5encryption.encrypt(password.getText().toString()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("register", true);
                startActivity(intent);
            }
        });

        return view;
    }

    public static void loginSucc(){
        ((LoginActivity)activity).loginSuccesful();
    }

    public static void loginFail(){
        Toast.makeText(context, R.string.loginscreen_fail, Toast.LENGTH_LONG).show();
    }

    public static boolean getSaveUserBoolean(){
        return saveUser;
    }
    public static void saveUser(UserWeb user){
        LocalStorageHandler.setUser(context, user);
    }
}
