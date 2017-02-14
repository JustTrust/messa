package com.belichenko.a.messaga.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.belichenko.a.messaga.MessagaClient;

/**
 * Created by Belichenko Anton on 14.02.17.
 * mailto: a.belichenko@gmail.com
 */

public enum PreferenceHelper {

    INSTANCE;

    private SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MessagaClient.INSTANCE.getContext());

    public void addEmail(String email){
        sp.edit().putString("email_key", email).apply();
    }
}
