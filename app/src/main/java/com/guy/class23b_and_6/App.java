package com.guy.class23b_and_6;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Imager.initHelper(this);
    }
}
