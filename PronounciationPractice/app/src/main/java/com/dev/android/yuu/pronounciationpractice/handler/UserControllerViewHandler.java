package com.dev.android.yuu.pronounciationpractice.handler;

import android.app.Activity;
import android.view.View;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

/**
 * Created by Chieko on 8/10/14.
 */
public class UserControllerViewHandler implements View.OnClickListener{


    private UserControllerView mUserControllerView = null;

    public UserControllerViewHandler(UserControllerView userControllerView)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mUserControllerView = userControllerView;
    }

    @Override
    public void onClick(View view) {

        DebugUtil.DebugLog(this.getClass().toString(), "onClick");

        int viewId = view.getId();

        if(R.id.button_startListening_debug == viewId)
        {
            this.mUserControllerView.OnDebugButtonStartListeningClicked();
        }
    }
}
