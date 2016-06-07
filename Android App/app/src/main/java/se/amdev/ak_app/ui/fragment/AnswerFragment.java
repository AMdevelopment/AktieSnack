package se.amdev.ak_app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import se.amdev.ak_app.R;
import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.ui.activity.ThreadsActivity;

/**
 * Created by Martin on 07/06/16.
 */
public class AnswerFragment extends Fragment {
    public static View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(ApplicationLoader.user != null) {
            view = inflater.inflate(R.layout.answer_fragment, container, false);
        }else{
            view = inflater.inflate(R.layout.login_fragment, container, false);
        }

        return view;
    }
}
