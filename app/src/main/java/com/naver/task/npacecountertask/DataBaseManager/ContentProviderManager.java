package com.naver.task.npacecountertask.DataBaseManager;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by minus on 2016-09-23.
 */

public class ContentProviderManager extends ContentProvider{

    public static final String AUTHORITY = "com.naver.task.npacecountertask";
    //public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/pacetestDB");
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/testDB");

    //?
    /*
    public static final int DB_ALL = 1;
    public static final int DB_ITEM = 2;
    */

    /*
    //?
    private static final UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "pacetestDB", DB_ALL);
        mUriMatcher.addURI(AUTHORITY, "pacetestDB/#", DB_ITEM);
    }
    //?
    */

    private DataBaseHelper mDbHelper;


    @Override
    public boolean onCreate() {
        mDbHelper = new DataBaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int count = 0;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String segment = uri.getPathSegments().get(1);
        count = db.update(DBControl.DATABASE_TABLE, values, DBControl.KEY_ID + "=" + segment, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        Log.d("minus", "update table : "+ count);

        return count;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long insertedID;
        Uri retUri = null;

        insertedID = db.insert(DBControl.DATABASE_TABLE, null, values);

        if(insertedID > 0){
            retUri = ContentUris.withAppendedId(CONTENT_URI, insertedID);
            getContext().getContentResolver().notifyChange(retUri, null);
        }
        Log.d("minus", "insert table : ");

        return retUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String segment = uri.getPathSegments().get(1);
        if (TextUtils.isEmpty(selection)) {
            selection = DBControl.KEY_ID + "=" + segment;
        } else {
            selection = DBControl.KEY_ID + "=" + segment + " AND (" + selection + ")";
        }
        count = db.delete(DBControl.DATABASE_TABLE, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        Log.d("minus", "delete table : ");

        return count;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        qb.setTables(DBControl.DATABASE_TABLE);
        String segment = uri.getPathSegments().get(1);
        qb.appendWhere(DBControl.KEY_ID + "=" + uri.getPathSegments().get(1));

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        if(c != null){
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }

        Log.d("minus", "query table : ");

        return c;
    }

    ////////////


}
