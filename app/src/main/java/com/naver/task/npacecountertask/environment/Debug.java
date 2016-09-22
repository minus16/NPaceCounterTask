package com.naver.task.npacecountertask.environment;

import android.util.Log;

/**
 * Created by minus on 2016-09-23.
 */

public class Debug {

    public static void TraceS2(String tag) {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        if (stack.length < 3)
            return;

        String msg_file = stack[2].getFileName();
        msg_file = msg_file.substring(0, msg_file.length() - 4);
        //String msg = msg_file + stack[2].getMethodName() + "." + stack[2].getLineNumber();
        String msg = msg_file + stack[2].getMethodName();
        Log.i(tag, msg);
    }
}
