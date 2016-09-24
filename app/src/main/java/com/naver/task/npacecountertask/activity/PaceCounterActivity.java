package com.naver.task.npacecountertask.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DebugUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.naver.task.npacecountertask.DataBaseManager.DBManager;
import com.naver.task.npacecountertask.GeocodingUtil.GeocodingManager;
import com.naver.task.npacecountertask.R;
import com.naver.task.npacecountertask.Utils.PaceCounterUtil;
import com.naver.task.npacecountertask.environment.Debug;
import com.naver.task.npacecountertask.environment.Preferences;
import com.naver.task.npacecountertask.services.PaceCounterService;

/**
 * Created by minus on 2016-09-23.
 */

public class PaceCounterActivity extends CommonActivity{

    private Button mStartBtn = null;
    private Context mContext = null;
    private boolean bStart = false;
    public MResultReceiver resultReceiver;
    private int mSTEP = 0;
    private boolean isFirstStep = true;
    private int mTodayStep = 0;

    DBManager dm =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        dm = new DBManager(this);
        setContentView(R.layout.pace_counter);

        getTodayFirstStep();

        //AsyncTask
        new GeoASyncTask().execute();


        resultReceiver = new MResultReceiver(null);

        //set Button
        mStartBtn = (Button) findViewById(R.id.start_btn);
        changeBtnTxt(bStart);


        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bStart == false)//do start
                {
                    Intent intent = new Intent(getApplicationContext(), PaceCounterService.class);
                    intent.putExtra("receiver", resultReceiver);
                    startService(intent);
                    bStart = true;
                }
                else//do stop
                {
                    Intent intent = new Intent(getApplicationContext(), PaceCounterService.class);
                    intent.putExtra("receiver", resultReceiver);
                    stopService(intent);
                    bStart = false;
                }
                changeBtnTxt(bStart);
            }
        });
    }

    public void getTodayFirstStep()
    {
        String date = PaceCounterUtil.getDate();
        mTodayStep = dm.getDateStep(date);
    }

    public void setPaceCount(int cnt)
    {
        TextView tv = (TextView) findViewById(R.id.pace_count_info);
        if(tv!=null)
            tv.setText(String.valueOf(cnt));

        setDistanceTxt(cnt);
    }

    public void setLocationTxt(String txt)
    {
        TextView tv = (TextView) findViewById(R.id.cur_loc_txt);
        if(tv!=null)
            tv.setText(String.valueOf(txt));
    }

    public void setDistanceTxt(int cnt)
    {
        String distance = PaceCounterUtil.calculateDistance(cnt);
        TextView tv = (TextView) findViewById(R.id.pace_move_distance);
        if(tv!=null)
            tv.setText(String.valueOf(distance));
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

        Intent intent = new Intent(getApplicationContext(), PaceCounterService.class);
        intent.putExtra("receiver", resultReceiver);
        stopService(intent);
    }

    @Override
    public void finish() {
        super.finish();
    }


    class MResultReceiver extends ResultReceiver
    {
        public MResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode==Preferences.PACE_COUNT_MSG)
            {
                int cnt = (int)resultData.getFloat("step");
                mSTEP = cnt;
                if(isFirstStep)
                {
                    String date = PaceCounterUtil.getDate();
                    mTodayStep = dm.getDateStep(date);
                }
                mSTEP = mSTEP-mTodayStep;
                int todat_cnt = cnt - mTodayStep;
                setPaceCount(todat_cnt);
            }
        }
    }

    public class GeoASyncTask extends AsyncTask<Void, Void, String>
    {
        public String result ="";
        GeocodingManager gm = new GeocodingManager(mContext);

        @Override
        protected String doInBackground(Void... params) {

            try {
                result = gm.getAddress();
            }
            catch (Exception e)
            {
                Log.d("minus", "exception : " + e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            setLocationTxt(result);
            gm.stopUsingGPS();
        }
    }
}
