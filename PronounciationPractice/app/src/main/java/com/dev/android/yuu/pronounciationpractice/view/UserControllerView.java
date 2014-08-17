package com.dev.android.yuu.pronounciationpractice.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.dev.android.yuu.pronounciationpractice.handler.UserControllerViewHandler;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

/**
 * Created by Chieko on 8/3/14.
 */
public class UserControllerView extends View{

    private Activity mParentActivity = null;

    private UserControllerViewHandler mUserControllerViewHandler = null;

    /* Debug */
    private Button mDebugButtonStartListening = null;

    public UserControllerView(Activity parentActivity)
    {
        super(parentActivity);

        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mParentActivity = parentActivity;

        this.setUiEventHandlers();
    }

    /* Public Methods */
    public void OnDebugButtonStartListeningClicked()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "OnDebugButtonStartListeningClicked()");
    }

    /* Private Methods */
    private boolean setUiEventHandlers()
    {

        DebugUtil.DebugLog(this.getClass().toString(), "setUiEventHandlers");

        boolean result = false;

        /*
        this.mDebugButtonStartListening = (Button)this.mParentActivity.findViewById(R.id.button_user_control_speak);
        this.mUserControllerViewHandler = new UserControllerViewHandler(this);
        this.mDebugButtonStartListening.setOnClickListener(this.mUserControllerViewHandler);
        */

        result = true;

        return result;
    }

}
