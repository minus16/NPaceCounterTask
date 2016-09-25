package com.naver.task.npacecountertask.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.naver.task.npacecountertask.R;

public class MiniWindow extends AppCompatActivity {

    PopupWindow mPopup = null;
    int mCurrentX = 0;
    int mCurrentY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mini_window);

        popMiniWindow();

    }

    @Override
    protected void onDestroy() {

        if(mPopup!=null) {
            if (mPopup.isShowing()){
                mPopup.dismiss();
            }
        }
        super.onDestroy();
    }

    public void popMiniWindow() {
        final View cv = new View(this);
        setContentView(cv);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = getLayoutInflater().inflate(R.layout.activity_mini_window, null);

        /*
        WindowManager.LayoutParams layOutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(v, layOutParams);
        */

        mPopup = new PopupWindow(v, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);


        View.OnTouchListener otl = new View.OnTouchListener() {
            private float mDx;
            private float mDy;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mDx = mCurrentX - event.getRawX();
                    mDy = mCurrentY - event.getRawY();
                } else if (action == MotionEvent.ACTION_MOVE) {
                    mCurrentX = (int) (event.getRawX() + mDx);
                    mCurrentY = (int) (event.getRawY() + mDy);
                    mPopup.update(mCurrentX, mCurrentY, -1, -1);
                }
                return true;
            }
        };
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.mini_layer);
        ll.setOnTouchListener(otl);

        cv.post(new Runnable() {
            @Override
            public void run() {
               // mPopup.showAtLocation(cv, Gravity.NO_GRAVITY, mCurrentX, mCurrentY);
                mPopup.showAtLocation(cv, Gravity.CENTER, 0, 0);
            }
        });

    }
}
