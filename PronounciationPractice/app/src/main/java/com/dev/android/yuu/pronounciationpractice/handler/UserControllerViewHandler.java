package com.dev.android.yuu.pronounciationpractice.handler;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.controller.SpeechRecognitionController;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

/**
 * Created by Chieko on 8/10/14.
 */
public class UserControllerViewHandler implements View.OnClickListener{

    private Activity mParentActivity = null;

    private SpeechRecognitionController mSpeechRecognitionController = null;

    public UserControllerViewHandler(Activity parentActivity, SpeechRecognitionController speechRecognitionController)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mParentActivity = parentActivity;
        this.mSpeechRecognitionController = speechRecognitionController;

        this.setUiEventHanlders();
    }

    private void setUiEventHanlders()
    {
        DebugUtil.AssertNotNull("mParentActivity is null.", this.mParentActivity);

        Button buttonSpeak = (Button)this.mParentActivity.findViewById(R.id.button_user_control_speak);
        buttonSpeak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        DebugUtil.DebugLog(this.getClass().toString(), "onClick");

        int viewId = view.getId();
        if(R.id.button_user_control_speak == viewId)
        {
            DebugUtil.AssertNotNull("mSpeechRecognitionController is null.", this.mSpeechRecognitionController);
            this.mSpeechRecognitionController.startListening();
        }

    }
}
