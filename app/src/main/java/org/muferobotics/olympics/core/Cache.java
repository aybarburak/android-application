package org.muferobotics.olympics.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.muferobotics.olympics.model.User;

import java.util.Arrays;
import java.util.Map;

public class Cache {
    private static final String LOG_TAG = "Cache";
    private static final String PREFERENCES_NAME = "org.muferobotics.olmypics";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static Gson GSON;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static Cache instance;

    public static Cache getIntance(Context context) {
        if (instance == null) {
            instance = new Cache(context);
        }

        return instance;
    }

    public Cache(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREFERENCES_NAME, PREFERENCES_MODE);
        this.editor = preferences.edit();

        GsonBuilder builder = new GsonBuilder();
        //builder.registerTypeAdapter(..,..);
        GSON = builder.create();
    }

    public Cache removeObject(String key) {
        editor.remove(key);
        return this;
    }

    public boolean commit() {
        return editor.commit();
    }

    public Cache putObject(String key, Object object) {
        if (object == null) {
            Log.d(LOG_TAG, "putObject/object parameter has a null value.");
        } else if (key.isEmpty() || key == null) {
            Log.d(LOG_TAG, "putObject/key parameter is empty or has a null value.");
        } else {
            editor.putString(key, GSON.toJson(object));
        }
        return this;
    }

    public <T> T getObject(String key, Class<T> c) {
        if (key.isEmpty() || key == null) {
            Log.d(LOG_TAG, "getObject/key parameter is empty or has a null value.");
            return null;
        } else {
            try {
                String data = preferences.getString(key, null);
                T object = GSON.fromJson(data, c);
                return object;
            } catch (Exception e) {
                return null;
            }
        }
    }

    public Cache removeAllPreferencesExcept(String... keyGroup) {
        Map<String, ?> keys = preferences.getAll();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            // If key is not in keyGroup.
            if (!Arrays.asList(keyGroup).contains(entry.getKey())) {
                Log.d(LOG_TAG, "Removing SharedPreferences object.");
                Log.d(LOG_TAG, "Key: " + entry.getKey() + ", Value: " + String.valueOf(entry.getValue()));
                removeObject(entry.getKey());
            }
        }
        return this;
    }

    private static final String USER_DATA = "USER_DATA";
    private static final String REQUEST_KEY = "REQUEST_DATA";

    public String getUserAccessToken() {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(REQUEST_KEY, "");
    }

    public boolean setUserAccessToken(String userAccessToken) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (userAccessToken == null) editor.remove(REQUEST_KEY);
        else editor.putString(REQUEST_KEY, userAccessToken);
        return editor.commit();
    }

    public boolean setUserData(User user) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        // If user wants to log out, pass null userData to clear informations.
        if (user == null) {
            editor.remove(USER_DATA);
        } else {
            editor.putString(USER_DATA, GSON.toJson(user));
        }
        return editor.commit();
    }

    public User getUser() {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        String userData = sharedPref.getString(USER_DATA, null);

        User user = null;

        if (userData != null) {
            user = GSON.fromJson(userData, User.class);
        }
        return user;
    }
}
