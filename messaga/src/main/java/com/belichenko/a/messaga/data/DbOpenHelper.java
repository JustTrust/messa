package com.belichenko.a.messaga.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Belichenko Anton on 14.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "messaga.db";
    public static final int DATABASE_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            //Uncomment line below if you want to enable foreign keys
            //db.execSQL("PRAGMA foreign_keys=ON;");

            //Add other tables here
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(Table.DROP_TABLE_IF_EXISTS + CouponsTable.TABLE_NAME);
        onCreate(db);
    }
}
