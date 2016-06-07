package se.amdev.ak_app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.activity.PostActivity;
import se.amdev.ak_app.ui.activity.ThreadsActivity;
import se.amdev.ak_app.ui.adapter.ThreadAdapter;

/**
 * Created by Martin on 07/06/16.
 */
public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        Button registerButton = (Button) view.findViewById(R.id.user_register_button);
        Button loginButton = (Button) view.findViewById(R.id.user_login_button);
        Button guestButton = (Button) view.findViewById(R.id.login_guest_button);
        TextView headerText = (TextView) view.findViewById(R.id.user_login_headertext);

        headerText.setText("Log in or register");

        loginButton.setText("LOGIN");
        registerButton.setText("REGISTER");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThreadsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
