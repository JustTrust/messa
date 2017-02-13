package com.belichenko.a.messaga.listeners;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public interface OnSuccessErrorListener {
    void onSuccess();

    void onError(String error);
}
