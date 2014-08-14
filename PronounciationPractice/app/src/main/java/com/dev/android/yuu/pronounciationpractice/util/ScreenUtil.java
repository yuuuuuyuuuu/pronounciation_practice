package com.dev.android.yuu.pronounciationpractice.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Chieko on 8/14/14.
 */
public class ScreenUtil {

    public static Point GetWindowSize(Context context)
    {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);

        return size;
    }

}
