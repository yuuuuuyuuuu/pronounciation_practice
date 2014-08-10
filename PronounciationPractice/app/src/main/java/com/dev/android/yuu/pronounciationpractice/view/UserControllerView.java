package com.dev.android.yuu.pronounciationpractice.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.controller.SpeechRecognitionController;
import com.dev.android.yuu.pronounciationpractice.handler.UserControllerViewHandler;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

/**
 * Created by Chieko on 8/3/14.
 */
public class UserControllerView {

    private Activity mParentActivity = null;

    private UserControllerViewHandler mUserControllerViewHandler = null;
    private SpeechRecognitionController mSpeechRecognitionController = null;

    /* Debug */
    private Button mDebugButtonStartListening = null;

    public UserControllerView(Activity parentActivity, SpeechRecognitionController speechRecognitionController)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mParentActivity = parentActivity;
        this.mSpeechRecognitionController = speechRecognitionController;

        this.setUiEventHandlers();
    }

    /* Public Methods */
    public void OnDebugButtonStartListeningClicked()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "OnDebugButtonStartListeningClicked()");

        // test
        this.mSpeechRecognitionController.startListening();
    }

    /* Private Methods */
    private boolean setUiEventHandlers()
    {

        DebugUtil.DebugLog(this.getClass().toString(), "setUiEventHandlers");

        boolean result = false;

        this.mDebugButtonStartListening = (Button)this.mParentActivity.findViewById(R.id.button_startListening_debug);
        this.mUserControllerViewHandler = new UserControllerViewHandler(this);
        this.mDebugButtonStartListening.setOnClickListener(this.mUserControllerViewHandler);

        //DebugUtil.AssertNull("mDebugButtonStartListening is null.", this.mDebugButtonStartListening);

        result = true;

        return result;
    }

}
