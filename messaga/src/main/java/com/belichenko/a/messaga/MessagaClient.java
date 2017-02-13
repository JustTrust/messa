package com.belichenko.a.messaga;

import android.content.Context;

import timber.log.Timber;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public enum MessagaClient {

    INSTANCE;

    private boolean mConnected;
    private boolean mRegister;
    private ConnectionListener mConnectionListener;
    private AuthenticationListener mAuthenticationListener;


    public boolean isRegistered() {
        return mRegister;
    }

    public boolean isConnected() {
        return mConnected;
    }

    public void register(Context context) {
        if (context == null) {
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
            Timber.d("register successful");
        }
    }

    public void connect() {
        if (isRegistered()) {
            if (isConnected()) {
                Timber.d("connect: already connected");
                if (mConnectionListener != null) {
                    mConnectionListener.onError("connect: already connected");
                }
            } else {
                mConnected = true;
                Timber.d("connected successful");
                if (mConnectionListener != null) {
                    mConnectionListener.onSuccess();
                }
            }
        } else {
            mConnected = false;
            Timber.d("connect: client not registered yet");
            if (mConnectionListener != null) {
                mConnectionListener.onError("connect: client not registered yet");
            }
        }
    }

    public void registerConnectionListener(ConnectionListener connectionListener) {
        mConnectionListener = connectionListener;
    }

    public void registerAuthenticationListener(AuthenticationListener authenticationListener) {
        mAuthenticationListener = authenticationListener;
    }

    public interface ConnectionListener {
        void onSuccess();

        void onError(String error);
    }

    public interface AuthenticationListener {
        void onSuccess();

        void onError(String error);
    }


}
