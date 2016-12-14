package org.muferobotics.olympics.core;

import android.app.Application;

import org.muferobotics.olympics.model.User;
import org.muferobotics.olympics.rest.MUFEClient;

public class App extends Application {
    MUFEClient mufeClient;
    User user;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initilize User session
        initilizeUser();
    }

    public MUFEClient getMufeClient() {
        return mufeClient;
    }

    public Cache getCache() {
        return Cache.getIntance(this);
    }

    public void initilizeUser() {
        user = getCache().getUser();
    }
}
