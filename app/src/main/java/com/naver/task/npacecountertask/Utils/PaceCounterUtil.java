package com.naver.task.npacecountertask.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by minus on 2016-09-24.
 */

public class PaceCounterUtil {

    static public String calculateDistance(int cnt)
    {
        String result = "";
        int av_step = 65;
        int distance = (cnt*av_step)/100;
        if(distance < 1000)
        {
            result = String.valueOf(distance) + " m";
        }
        else
        {
            String km = String.valueOf(distance / 1000);
            String m = String.valueOf(distance % 1000);
            m = m.substring(0,1);
            m = "."+m+ " km";
            result = km + m;
        }
        return result;
    }

    static public String getDate()
    {
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyyMMdd").format(cDate);
        Log.d("minus", "date : "+ fDate);
        return fDate;
    }
}
