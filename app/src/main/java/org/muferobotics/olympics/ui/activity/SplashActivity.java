package org.muferobotics.olympics.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.muferobotics.olympics.R;
import org.muferobotics.olympics.core.App;
import org.muferobotics.olympics.model.User;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private App app;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        app = (App) getApplication();
        user = app.getCache().getUser();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (user != null) {
                    Intent intent = new Intent(SplashActivity.this, PrimaryActivity.class);
                    startActivity(intent);
                    // close splash screen
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    // close splash screen
                    SplashActivity.this.finish();
                }
            }
            //}
        }, 1000);
    }
}
