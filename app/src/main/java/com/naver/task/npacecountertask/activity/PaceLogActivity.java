package com.naver.task.npacecountertask.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.naver.task.npacecountertask.DataBaseManager.DBControl;
import com.naver.task.npacecountertask.DataBaseManager.DBManager;
import com.naver.task.npacecountertask.R;
import com.naver.task.npacecountertask.Utils.PaceCounterUtil;

import java.util.ArrayList;

/**
 * Created by minus on 2016-09-23.
 */

public class PaceLogActivity extends ListActivity {

    ArrayList<String> strings = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pace_log);

        DrawList();
    }
    private void DrawList()
    {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        setListAdapter(adapter);
    }

    private void getLog()
    {
        DBManager dm = new DBManager(this);
        Cursor c = dm.getAll();

        strings.clear();

        while (c.moveToNext()) {
            String list_string = null;
            int _id = c.getInt(c.getColumnIndex(DBControl.KEY_ID));
            String _date = c.getString(c.getColumnIndex(DBControl.KEY_DATE));
            int _start = c.getInt(c.getColumnIndex(DBControl.KEY_START_STEP));
            int _stop = c.getInt(c.getColumnIndex(DBControl.KEY_STOP_STEP));
            int step = _stop - _start;
            //String dateformat = _date.substring(0,4) + "." + _date.substring(5,6) + "."+_date.substring(7,8);
            StringBuffer sb = new StringBuffer(_date);
            sb.insert(4, ".");
            sb.insert(7, ".");

            String dis = PaceCounterUtil.calculateDistance(step);
            list_string = "날짜 : "+sb.toString() + "    걸음: " + step + "    거리 : " + dis;
            strings.add(list_string);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //list refresh
        getLog();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
