package com.belichenko.a.messa.data.local;

import android.content.Context;

import com.belichenko.a.messa.injection.ApplicationContext;
import com.securepreferences.SecurePreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.belichenko.a.messa.util.Consts;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "project_pref.xml";

    private final SecurePreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = new SecurePreferences(context, Consts.PREFERENCE_PASSWORD, PREF_FILE_NAME);    }

    public void clear() {
        mPref.edit().clear().apply();
    }

}
