package com.belichenko.a.messaga.data;

import com.belichenko.a.messaga.MessagaClient;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

/**
 * Created by Belichenko Anton on 14.02.17.
 * mailto: a.belichenko@gmail.com
 */

public enum DatabaseHelper {

    INSTANCE;

    private final SqlBrite sqlBrite = new SqlBrite.Builder().build();
    private final BriteDatabase mDataBase = sqlBrite.wrapDatabaseHelper(new DbOpenHelper(MessagaClient.INSTANCE.getContext()), Schedulers.io());

    public void addMessage(String message){
        mDataBase.execute(message);
    }
}
