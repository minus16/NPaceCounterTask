package com.naver.task.npacecountertask.DataBaseManager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by minus on 2016-09-24.
 */

public class DBControl {

    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "_date";
    public static final String KEY_START_STEP = "_start_step";
    public static final String KEY_STOP_STEP = "_stop_step";

    public static final String DATABASE_NAME = "pacetest.db";
    public static final String DATABASE_TABLE = "pacetest";
    public static final int DATABASE_VERSION = 1;



//    static public boolean insertItem(Context context, String date, int start, int stop) {
//        boolean bRet = false;
//
//        ContentValues insertValues = new ContentValues();
//
//        insertValues.put(DBControl.KEY_DATE, date);
//        insertValues.put(DBControl.KEY_START_STEP, start);
//        insertValues.put(DBControl.KEY_STOP_STEP, stop);
//
//
//        ContentResolver cr = context.getContentResolver();
//        Uri insertedUri = cr.insert(ContentProviderManager.CONTENT_URI, insertValues);
//
//        if (insertedUri != null) {
//            bRet = true;
//        }
//
//        return bRet;
//    }
//
//    static public boolean updateItem(Context context, int id, int stop) {
//
//        boolean bRet = false;
//        Uri updateUri = ContentProviderManager.CONTENT_URI;
//        //Uri updateUri = ContentUris.withAppendedId(ContentProviderManager.CONTENT_URI, id); ;
//
//        /*
//        String where = DBControl.KEY_DATE + " = '" + updateUri.getPathSegments().get(1) + "'";
//        if (TextUtils.isEmpty(date) == false){
//            where += " AND " + date;
//        }
//        */
//
//        ContentValues updateValues = new ContentValues();
//        updateValues.put(DBControl.KEY_STOP_STEP, stop);
//
//        ContentResolver cr = context.getContentResolver();
//        if (cr.update(updateUri, updateValues, null, null) > 0) {
//            bRet = true;
//        }
//        return bRet;
//    }
//
//    static public Cursor findQuery(Context context, String date) {
//
//        Cursor cursor = null;
//        //Uri queryUri = ContentUris.withAppendedId(ContentProviderManager.CONTENT_URI);
//        //Cursor cursor = context.getContentResolver().query(ContentProviderManager.CONTENT_URI, new String[]{DBControl.KEY_ID}, DBControl.KEY_DATE +  " LIKE ? ", new String[]{date + "%"}, null);
//        ContentResolver cr = context.getContentResolver();
//        try {
//            cursor = cr.query(ContentProviderManager.CONTENT_URI, null, DBControl.KEY_DATE + " LIKE ? ", new String[]{date + "%"}, null);
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//        return cursor;
//    }
//
//    static public Cursor findAllQuery(Context context) {
//
//        Cursor c = null;
//        //Uri queryUri = ContentUris.withAppendedId(ContentProviderManager.CONTENT_URI, 0);
//        //Cursor cursor = context.getContentResolver().query(ContentProviderManager.CONTENT_URI, new String[]{DBControl.KEY_ID}, DBControl.KEY_DATE +  " LIKE ? ", new String[]{date + "%"}, null);
//        try {
//            c = context.getContentResolver().query(ContentProviderManager.CONTENT_URI, null, null, null, null);
//        }
//        catch (Exception e)
//        {
//            int a =10;
//        }
//        return c;
//    }
//
}
