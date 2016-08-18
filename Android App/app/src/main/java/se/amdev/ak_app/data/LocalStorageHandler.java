package se.amdev.ak_app.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import java.net.ConnectException;
import java.util.ArrayList;

import se.amdev.ak_app.data.loader.ApplicationLoader;
import se.amdev.ak_app.data.model.ThreadWeb;
import se.amdev.ak_app.data.model.UserWeb;
import se.amdev.ak_app.ui.fragment.ThreadsFragment;

/**
 * Created by Martin on 2016-08-16.
 */
public class LocalStorageHandler {

    static final String PREF_USERNAME = "username";
    static final String PREF_FIRSTNAME = "firstname";
    static final String PREF_LASTNAME = "lastname";
    static final String PREF_EMAIL = "email";
    static final String PREF_FAVORIT = "favorit";

    static SharedPreferences getSharedPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUser(Context context, UserWeb user) {
        SharedPreferences preferences = getSharedPref(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_USERNAME, user.getUsername());
        editor.putString(PREF_FIRSTNAME, user.getFirstName());
        editor.putString(PREF_LASTNAME, user.getLastName());
        editor.putString(PREF_EMAIL, user.getEmail());
        editor.apply();
    }

    public static void logOut(Context context) {
        SharedPreferences preferences = getSharedPref(context);
        preferences.edit().remove(PREF_USERNAME).remove(PREF_EMAIL).remove(PREF_FIRSTNAME).remove(PREF_LASTNAME).apply();
    }

    public static UserWeb getUser(Context context) {
        String username = getSharedPref(context).getString(PREF_USERNAME, "");
        String email = getSharedPref(context).getString(PREF_EMAIL, "");
        String firstname = getSharedPref(context).getString(PREF_FIRSTNAME, "");
        String lastname = getSharedPref(context).getString(PREF_LASTNAME, "");

        if (username.length() == 0) {
            return null;
        } else {
            return new UserWeb(username, "PASSWORD", email, firstname, lastname);
        }
    }

    public static void setFavorit(Context context, ArrayList<ThreadWeb> favoritList) {
        SharedPreferences preferences = getSharedPref(context);
        SharedPreferences.Editor editor = preferences.edit();
        int i = 1;
        for (ThreadWeb t : favoritList) {
            editor.putString(PREF_FAVORIT + i, t.getThreadNumber());
            i++;
            editor.apply();
        }
    }

    public static void getFavorit(Context context) {
        ApplicationLoader.favoritList.clear();
        for (int i = 1; i < 11; i++) {
            String threadName = getSharedPref(context).getString(PREF_FAVORIT + i, "");
            if (threadName.length() != 0) {
                if (!ApplicationLoader.favoritList.contains(ApplicationLoader.threadWebHashMap.get(threadName))) {
                    ApplicationLoader.favoritList.add(ApplicationLoader.threadWebHashMap.get(threadName));
                }
            }
        }
        ThreadsFragment.setFav();
    }

    public static void removeFav(Context context, int position) {
        SharedPreferences preferences = getSharedPref(context);
        preferences.edit().remove(PREF_FAVORIT + position).apply();
    }
}
