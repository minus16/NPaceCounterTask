package com.naver.task.npacecountertask.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import com.naver.task.npacecountertask.DataBaseManager.DBControl;
import com.naver.task.npacecountertask.DataBaseManager.DBManager;
import com.naver.task.npacecountertask.Utils.PaceCounterUtil;
import com.naver.task.npacecountertask.environment.Preferences;

import java.io.FileDescriptor;
import java.io.PrintWriter;


/**
 * Created by minus on 2016-09-23.
 */

public class PaceCounterService extends Service implements SensorEventListener{

    //Geocoder
    private SensorManager sensorManager = null;
    private Sensor countSensor = null;
    private ResultReceiver resultReceiver;

    private int STEP_COUNT = 0;

    private boolean isFirstStep = true;
    DBManager dm = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("minus", "start service");

        dm= new DBManager(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        resultReceiver = intent.getParcelableExtra("receiver");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);

        String date = PaceCounterUtil.getDate();
        dm.update(date, STEP_COUNT);

        /*
        Cursor c = DBControl.findQuery(this, date);
        DBControl.updateItem(this, c.getColumnIndex(DBControl.KEY_ID), STEP_COUNT );
        */

        Log.d("minus", "stop service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("minus", String.valueOf(event.values[0]));

        STEP_COUNT = (int)event.values[0];
        Bundle b = new Bundle();
        b.putFloat("step", event.values[0]);
        resultReceiver.send(Preferences.PACE_COUNT_MSG,b);

        //insert STEP_COUNT to content Provider
        //Cursor c = DBControl.findQuery(this, date);
        //if(c.getCount()==0)
        //    DBControl.insertItem(this, PaceCounterUtil.getDate(), (int) (event.values[0]), 0);

        if(isFirstStep)
        {
            //select and insert
            String date = PaceCounterUtil.getDate();
            if( dm.findbyDate(date) == true)
            {
                //skip
                Log.d("minus", "daily skip");
            }
            else
            {
                dm.insert(date,STEP_COUNT);
            }
        }
        else
        {
            //skip
            Log.d("minus", "first skip");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
