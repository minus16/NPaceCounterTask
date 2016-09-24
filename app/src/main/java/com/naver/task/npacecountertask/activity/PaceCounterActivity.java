package com.naver.task.npacecountertask.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.naver.task.npacecountertask.GeocodingUtil.GeocodingManager;
import com.naver.task.npacecountertask.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        setContentView(R.layout.pace_counter);

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

    public void setPaceCount(int cnt)
    {
        TextView tv = (TextView) findViewById(R.id.pace_count_info);
        if(tv!=null)
            tv.setText(String.valueOf(cnt));
    }

    public void setLocationTxt(String txt)
    {
        TextView tv = (TextView) findViewById(R.id.cur_loc_txt);
        if(tv!=null)
            tv.setText(String.valueOf(txt));
    }

    public void showPopupWindow()
    {
        //startActivity(new Intent(this, MiniWindow.class));
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
                setPaceCount(cnt);
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
                int a = 10;
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
