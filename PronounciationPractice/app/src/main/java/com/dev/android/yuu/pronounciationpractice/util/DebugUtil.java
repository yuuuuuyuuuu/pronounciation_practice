package com.dev.android.yuu.pronounciationpractice.util;

import android.util.Log;
import junit.framework.Assert;

/**
 * Created by Chieko on 8/3/14.
 */
public class DebugUtil {

    public static void DebugLog(String... args)
    {
        String msg = "";

        for(String s : args)
        {
            msg += s + ":";
        }

        Log.d("DebugLog", msg);
    }

    public static void AssertEquals(String message, boolean expected, boolean actual)
    {
        Log.d("AssertEquals", "assertEquals(" + message + ", " + expected + ", " + actual);
        Assert.assertEquals(message, expected, actual);
    }

    public static void AssertNotNull(String message, Object object)
    {
        Log.d("AssertNotNull", "assertNotNull(" + message + ", " + object + ")");
        Assert.assertNotNull(message, object);
    }

    public static void AssertNull(String message, Object object)
    {
        Log.d("AssertNull", "assertNull(" + message + ", " + object + ")");
        Assert.assertNull(message, object);
    }



}
