package com.belichenko.a.messaga;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MessagaClient implements BackgroundManager.Listener {

    private static MessagaClient sInstance;

    private Context mContext;
    private BackgroundManager mBackgroundManager;
    private boolean mRegister;

    public static MessagaClient getInstance() {
        if (sInstance == null) {
            sInstance = new MessagaClient();
        }
        return sInstance;
    }

    private MessagaClient() {}

    public boolean isRegistered() {
        return mRegister;
    }

    public Context getContext() {
        return mContext;
    }

    public void register(WeakReference<Context> context) {
        mContext = context.get();
        if (mContext == null) {
            mRegister = false;
            Timber.d("register: context = null");
        } else {
            if (BuildConfig.DEBUG) {
                Timber.plant(new Timber.DebugTree() {
                    @Override
                    protected String createStackElementTag(StackTraceElement element) {
                        return super.createStackElementTag(element)
                                + "::Line:" + element.getLineNumber() + ""
                                + "::" + element.getMethodName() + "()";
                    }
                });
            }
            mRegister = true;
            mBackgroundManager = BackgroundManager.getInstance((Application) mContext);
            mBackgroundManager.registerListener(this);
            MsgWebSocket.getInstance();
            Timber.d("register successful");
        }
    }

    @Override
    public void onBecameForeground() {
        MsgWebSocket.getInstance().goForeground();
    }

    @Override
    public void onBecameBackground() {
        MsgWebSocket.getInstance().goBackground();
    }

}
