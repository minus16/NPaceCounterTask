package com.naver.task.npacecountertask.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import com.naver.task.npacecountertask.environment.Preferences;


/**
 * Created by minus on 2016-09-23.
 */

public class PaceCounterService extends Service implements SensorEventListener{

    private SensorManager sensorManager = null;
    private Sensor countSensor = null;
    private ResultReceiver resultReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("minus", "start service");

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
        Log.d("minus", "stop service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("minus", String.valueOf(event.values[0]));
        Bundle b = new Bundle();
        b.putFloat("step", event.values[0]);
        resultReceiver.send(Preferences.PACE_COUNT_MSG,b);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
