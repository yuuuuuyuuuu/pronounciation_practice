package com.dev.android.yuu.pronounciationpractice.util;

import android.util.Log;

/**
 * Created by Chieko on 8/3/14.
 */
public class DebugUtil {

    static void DebugLog(String... args)
    {
        String msg = "";

        for(String s : args)
        {
            msg += s + "-";
        }

        Log.d("DebugLog", msg);
    }
    
}
