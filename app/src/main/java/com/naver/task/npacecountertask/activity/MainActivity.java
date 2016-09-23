package com.naver.task.npacecountertask.activity;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.naver.task.npacecountertask.R;


public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawTab();
    }

    private void drawTab()
    {
        //TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec(getText(R.string.tab1).toString()).setIndicator(getText(R.string.tab1).toString())
                .setContent(new Intent(this, PaceCounterActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(getText(R.string.tab2).toString()).setIndicator(getText(R.string.tab2).toString())
                .setContent(new Intent(this, PaceLogActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
