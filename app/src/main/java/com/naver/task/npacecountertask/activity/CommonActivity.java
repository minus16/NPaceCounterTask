package com.naver.task.npacecountertask.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.naver.task.npacecountertask.environment.Debug;

import static com.naver.task.npacecountertask.environment.Preferences.bActivityLOG;

/**
 * Created by minus on 2016-09-23.
 */

public class CommonActivity extends Activity{

    final public static String TAG = "PaceActivityLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onStart();
    }

    @Override
    protected void onResume() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onResume();
    }

    @Override
    protected void onRestart() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onRestart();
    }

    @Override
    protected void onPause() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onPause();
    }

    @Override
    protected void onStop() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.onDestroy();
    }

    @Override
    public void finish() {
        if(bActivityLOG)
            Debug.TraceS2(TAG);

        super.finish();
    }
}
