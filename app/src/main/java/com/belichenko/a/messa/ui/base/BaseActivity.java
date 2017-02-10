package com.belichenko.a.messa.ui.base;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.belichenko.a.messa.MessaApplication;
import com.belichenko.a.messa.injection.component.ActivityComponent;
import com.belichenko.a.messa.injection.component.DaggerActivityComponent;
import com.belichenko.a.messa.injection.module.ActivityModule;
import com.belichenko.a.messa.util.Consts;


public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    private boolean mPaused;
    private long mLastClickTime = SystemClock.elapsedRealtime();

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MessaApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public boolean isPaused() {
        return mPaused;
    }

    @Override
    protected void onPause() {
        mPaused = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPaused = false;
    }

    protected boolean isClickAllowed() {
        long now = SystemClock.elapsedRealtime();
        if (now - mLastClickTime > Consts.CLICK_TIME_INTERVAL) {
            mLastClickTime = now;
            return true;
        } else return false;
    }

}
