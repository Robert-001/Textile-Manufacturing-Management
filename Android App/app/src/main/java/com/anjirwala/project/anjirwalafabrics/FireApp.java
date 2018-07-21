package com.anjirwala.project.anjirwalafabrics;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Anuj Anjirwala on 04-Aug-17.
 */

public class FireApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
