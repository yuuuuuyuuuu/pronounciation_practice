package com.dev.android.yuu.pronounciationpractice.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;

import com.dev.android.yuu.pronounciationpractice.custoninterface.SpeechRecognitionInterface;
import com.dev.android.yuu.pronounciationpractice.handler.UserControllerViewHandler;
import com.dev.android.yuu.pronounciationpractice.model.SpeechRecognitionListener;
import com.dev.android.yuu.pronounciationpractice.model.SpeechRecognizerModel;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/10/14.
 */
public class SpeechRecognitionController {

    private SpeechRecognitionInterface mSpeechRecognitionInterface = null;

    private Context mContext = null;

    // Model
    private SpeechRecognizerModel mSpeechRecognizerModel= null;
    private SpeechRecognitionListener mSpeechRecognitionListener = null;

    // View
    private UserControllerViewHandler mUserControllerViewHandler = null;

    public SpeechRecognitionController(Context context, SpeechRecognitionInterface speechRecognitionInterface)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "Constructor");

        this.mContext = context;
        this.mSpeechRecognitionInterface = speechRecognitionInterface;

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

    public void destroyRecognizer()
    {
        this.mSpeechRecognizerModel.destroy();
    }

    public void onSpeechResult(ArrayList<String> resultStrings, float[] scores)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "onSpeechResult");

        this.mSpeechRecognitionInterface.onSpeechResult(resultStrings, scores);
    }

    public void onRmsChanged(float volume)
    {
        this.mSpeechRecognitionInterface.onRmsChanged(volume);
    }

    public void onReadyForSpeech()
    {
        this.mSpeechRecognitionInterface.onReadyForSpeech();
    }

    public void onEndOfSpeech()
    {
        this.mSpeechRecognitionInterface.onEndOfSpeech();
    }

    /* Private Methods */
    private void initialize()
    {
        // model
        this.mSpeechRecognitionListener = new SpeechRecognitionListener(this);
        this.mSpeechRecognizerModel = new SpeechRecognizerModel(this.mContext, this.mSpeechRecognitionListener);

    }

}
