package com.dev.android.yuu.pronounciationpractice.controller;

import android.content.Context;
import android.os.Debug;

import com.dev.android.yuu.pronounciationpractice.model.SpeechRecognitionListener;
import com.dev.android.yuu.pronounciationpractice.model.SpeechRecognizerModel;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

/**
 * Created by Chieko on 8/10/14.
 */
public class SpeechRecognitionController {

    private Context mContext = null;
    private SpeechRecognizerModel mSpeechRecognizerModel= null;
    private SpeechRecognitionListener mSpeechRecognitionListener = null;

    public SpeechRecognitionController(Context context)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mContext = context;

        this.initialize();
    }

    /* Public Methods */
    public boolean startListening()
    {
        boolean result = false;

        DebugUtil.AssertNull("SpeechRecognizerModel is null", this.mSpeechRecognizerModel);

        this.mSpeechRecognizerModel.start();
        result = true;

        return result;
    }

    public boolean stopListening()
    {
        boolean result = false;

        DebugUtil.AssertNull("SpeechRecognizerModel is null", this.mSpeechRecognizerModel);

        this.mSpeechRecognizerModel.stop();
        result = true;

        return result;
    }





    /* Private Methods */
    private void initialize()
    {
        this.mSpeechRecognitionListener = new SpeechRecognitionListener();
        this.mSpeechRecognizerModel = new SpeechRecognizerModel(this.mContext, this.mSpeechRecognitionListener);

    }

}
