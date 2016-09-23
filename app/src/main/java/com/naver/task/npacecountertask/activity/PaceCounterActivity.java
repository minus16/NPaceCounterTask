package com.naver.task.npacecountertask.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.naver.task.npacecountertask.R;

/**
 * Created by minus on 2016-09-23.
 */

public class PaceCounterActivity extends CommonActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pace_counter);
    }

    public void setPaceCount(int cnt)
    {
        TextView tv = (TextView) findViewById(R.id.pacecount);
        tv.setText(cnt);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
