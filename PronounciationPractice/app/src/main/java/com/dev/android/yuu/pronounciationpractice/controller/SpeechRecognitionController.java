package com.dev.android.yuu.pronounciationpractice.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;

import com.dev.android.yuu.pronounciationpractice.handler.UserControllerViewHandler;
import com.dev.android.yuu.pronounciationpractice.model.SpeechRecognitionListener;
import com.dev.android.yuu.pronounciationpractice.model.SpeechRecognizerModel;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

/**
 * Created by Chieko on 8/10/14.
 */
public class SpeechRecognitionController {

    private Activity mParentActivity = null;

    // Model
    private SpeechRecognizerModel mSpeechRecognizerModel= null;
    private SpeechRecognitionListener mSpeechRecognitionListener = null;

    // View
    private UserControllerViewHandler mUserControllerViewHandler = null;

    public SpeechRecognitionController(Activity parentActivity)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mParentActivity = parentActivity;

        this.initialize();
    }

    /* Public Methods */
    public boolean startListening()
    {
        boolean result = false;

        DebugUtil.AssertNotNull("SpeechRecognizerModel is null", this.mSpeechRecognizerModel);

        this.mSpeechRecognizerModel.start();
        result = true;

        return result;
    }

    public boolean stopListening()
    {
        boolean result = false;

        DebugUtil.AssertNotNull("SpeechRecognizerModel is null", this.mSpeechRecognizerModel);

        this.mSpeechRecognizerModel.stop();
        result = true;

        return result;
    }

    /* Private Methods */
    private void initialize()
    {
        // model
        this.mSpeechRecognitionListener = new SpeechRecognitionListener();
        this.mSpeechRecognizerModel = new SpeechRecognizerModel((Context)this.mParentActivity, this.mSpeechRecognitionListener);

        // handler
        this.mUserControllerViewHandler = new UserControllerViewHandler(this.mParentActivity, this);
    }

}
