package com.naver.task.npacecountertask.DataBaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by minus on 2016-09-24.
 */

public class DataBaseHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "pacetest.db";
    public static final String TABLE_NAME = "pacetest";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "CREATE TABLE if not exists " + DBControl.DATABASE_TABLE + "(" +
                        DBControl.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBControl.KEY_DATE + " TEXT, " +
                        DBControl.KEY_START_STEP + " BIGINT, " +
                        DBControl.KEY_STOP_STEP + " BIGINT " +
                        " );";

        db.execSQL(DATABASE_CREATE);
        Log.d("minus", "create table : ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DATABASE_DROP = "DROP TABLE IF EXISTS " + DBControl.DATABASE_TABLE;
        String DATABASE_CREATE =
                "CREATE TABLE if not exists " + DBControl.DATABASE_TABLE + "(" +
                        DBControl.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBControl.KEY_DATE + " TEXT, " +
                        DBControl.KEY_START_STEP + " BIGINT, " +
                        DBControl.KEY_STOP_STEP + " BIGINT " +
                        " );";
        db.execSQL(DATABASE_DROP);
        db.execSQL(DATABASE_CREATE);
        Log.d("minus", "onUpdate table : ");
    }

}
