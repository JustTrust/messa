package com.belichenko.a.messaga;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("onCreate: ");
    }
}
