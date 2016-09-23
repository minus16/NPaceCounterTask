package com.naver.task.npacecountertask.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.naver.task.npacecountertask.R;
import com.naver.task.npacecountertask.services.PaceCounterService;

/**
 * Created by minus on 2016-09-23.
 */

public class PaceCounterActivity extends CommonActivity{

    Button mStartBtn = null;
    Context mContext = null;
    private boolean bStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.pace_counter);

        //set Button
        mStartBtn = (Button) findViewById(R.id.start_btn);
        changeBtnTxt(bStart);


        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bStart == false)//do start
                {
                    Intent intent = new Intent(getApplicationContext(), PaceCounterService.class);
                    startService(intent);
                    bStart = true;
                }
                else//do stop
                {
                    Intent intent = new Intent(getApplicationContext(), PaceCounterService.class);
                    stopService(intent);
                    bStart = false;
                }
                changeBtnTxt(bStart);
            }
        });
    }

    public void setPaceCount(int cnt)
    {
        TextView tv = (TextView) findViewById(R.id.pacecount);
        tv.setText(cnt);
    }

    public void showPopupWindow()
    {
        startActivity(new Intent(this, MiniWindow.class));
    }
    private void changeBtnTxt(boolean b)
    {
        if(b == false)
        {
            mStartBtn.setText(getText(R.string.start));
        }
        else
        {
            mStartBtn.setText(getText(R.string.stop));
        }
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
        showPopupWindow();
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
