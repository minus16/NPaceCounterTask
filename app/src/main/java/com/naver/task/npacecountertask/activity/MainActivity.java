package com.naver.task.npacecountertask.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.naver.task.npacecountertask.R;
import com.naver.task.npacecountertask.activity.CommonActivity;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
