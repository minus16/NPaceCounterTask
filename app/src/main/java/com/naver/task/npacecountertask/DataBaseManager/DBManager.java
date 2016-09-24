package com.naver.task.npacecountertask.DataBaseManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by minus on 2016-09-24.
 */

public class DBManager {

    Context mContext = null;

    SQLiteDatabase db;
    DataBaseHelper helper;


    public DBManager(Context context)
    {
        this.mContext = context;
        helper = new DataBaseHelper(mContext);
    }

    // insert
    public void insert(String date, int start) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBControl.KEY_DATE, date);
        values.put(DBControl.KEY_START_STEP, start);
        values.put(DBControl.KEY_STOP_STEP, 0);
        db.insert(DBControl.DATABASE_TABLE, null, values);

    }
    // update
    public void update (String date, int stop) {
        db = helper.getWritableDatabase(); //db 객체를 얻어온다. 쓰기가능

        ContentValues values = new ContentValues();
        values.put(DBControl.KEY_STOP_STEP, stop);    //age 값을 수정
        db.update(DBControl.DATABASE_TABLE, values, DBControl.KEY_DATE+"=?", new String[]{date});

    }

    // delete
    public void delete (String date) {
        db = helper.getWritableDatabase();
        db.delete(DBControl.DATABASE_TABLE, DBControl.KEY_DATE+"=?", new String[]{date});
    }

    // select
    public void selectAll() {

        db = helper.getReadableDatabase();
        Cursor c = db.query(DBControl.DATABASE_TABLE, null, null, null, null, null, null);

        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex(DBControl.KEY_ID));
            String _date = c.getString(c.getColumnIndex(DBControl.KEY_DATE));
            int _start = c.getInt(c.getColumnIndex(DBControl.KEY_START_STEP));
            int _stop = c.getInt(c.getColumnIndex(DBControl.KEY_STOP_STEP));

            Log.d("minus", "result : " +_id +" : "+ _date +" : "+_start + " : "+_stop);
        }
    }
    // select
    public Cursor getAll() {

        db = helper.getReadableDatabase();
        Cursor c = db.query(DBControl.DATABASE_TABLE, null, null, null, null, null, null);

        return c;
    }

    public boolean findbyDate(String date)
    {
        db = helper.getReadableDatabase();
        //Cursor c = db.query(DBControl.DATABASE_TABLE, null, null, null, null, null, null);
        Cursor c = db.rawQuery("SELECT * FROM "+DBControl.DATABASE_TABLE+ " WHERE "+DBControl.KEY_DATE + " = '" + date.trim() + "'", null);
        if(c!=null) {
            if (c.getCount() > 0) {
                return true;
            }
        }

        return false;
    }

    public int getDateStep(String date)
    {
        int _start = 0;
        db = helper.getReadableDatabase();
        //Cursor c = db.query(DBControl.DATABASE_TABLE, null, null, null, null, null, null);
        Cursor c = db.rawQuery("SELECT * FROM "+DBControl.DATABASE_TABLE+ " WHERE "+DBControl.KEY_DATE + " = '" + date.trim() + "'", null);

        while (c.moveToNext()) {
            _start = c.getInt(c.getColumnIndex(DBControl.KEY_START_STEP));
        }


        return _start;
    }

}
