package com.belichenko.a.messaga;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Belichenko Anton on 16.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class BackgroundManager implements Application.ActivityLifecycleCallbacks {

    private static final long BACKGROUND_DELAY = 500;

    private static BackgroundManager sInstance;

    public interface Listener {
        void onBecameForeground();
        void onBecameBackground();
    }

    private boolean mInBackground = true;
    private final List<Listener> listeners = new ArrayList<>();
    private final Handler mBackgroundDelayHandler = new Handler();
    private Runnable mBackgroundTransition;

    public static BackgroundManager getInstance(Application application) {
        if (sInstance == null) {
            sInstance = new BackgroundManager(application);
        }
        return sInstance;
    }

    private BackgroundManager(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    public boolean isInBackground() {
        return mInBackground;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (mBackgroundTransition != null) {
            mBackgroundDelayHandler.removeCallbacks(mBackgroundTransition);
            mBackgroundTransition = null;
        }

        if (mInBackground) {
            mInBackground = false;
            notifyOnBecameForeground();
            Timber.d("Application went to foreground");
        }
    }

    private void notifyOnBecameForeground() {
        for (Listener listener : listeners) {
            try {
                listener.onBecameForeground();
            } catch (Exception e) {
                Timber.d("Listener threw exception! %s", e);
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (!mInBackground && mBackgroundTransition == null) {
            mBackgroundTransition = new Runnable() {
                @Override
                public void run() {
                    mInBackground = true;
                    mBackgroundTransition = null;
                    notifyOnBecameBackground();
                    Timber.d("Application went to background");
                }
            };
            mBackgroundDelayHandler.postDelayed(mBackgroundTransition, BACKGROUND_DELAY);
        }
    }

    private void notifyOnBecameBackground() {
        for (Listener listener : listeners) {
            try {
                listener.onBecameBackground();
            } catch (Exception e) {
                Timber.d("Listener threw exception! %s", e);
            }
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}
}