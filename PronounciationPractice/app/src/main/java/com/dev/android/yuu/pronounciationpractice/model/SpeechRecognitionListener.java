package com.dev.android.yuu.pronounciationpractice.model;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.dev.android.yuu.pronounciationpractice.controller.SpeechRecognitionController;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/3/14.
 */
public class SpeechRecognitionListener implements RecognitionListener{

    private SpeechRecognitionController mSpeechRecognitionController = null;

    public SpeechRecognitionListener(SpeechRecognitionController speechRecognitionController)
    {
        super();

        this.mSpeechRecognitionController = speechRecognitionController;
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        DebugUtil.DebugLog(this.getClass().toString(), "onReadyForSpeech");
        this.mSpeechRecognitionController.onReadyForSpeech();
    }

    @Override
    public void onBeginningOfSpeech() {
        DebugUtil.DebugLog(this.getClass().toString(), "onBeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float v) {
        //DebugUtil.DebugLog(this.getClass().toString(), "onRmsChanged");
        this.mSpeechRecognitionController.onRmsChanged(v);
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        DebugUtil.DebugLog(this.getClass().toString(), "onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        DebugUtil.DebugLog(this.getClass().toString(), "onEndOfSpeech");
        this.mSpeechRecognitionController.onEndOfSpeech();
    }

    @Override
    public void onError(int i)
    {

        DebugUtil.DebugLog(this.getClass().toString(), "onError");

        this.mSpeechRecognitionController.onError(i);
    }

    @Override
    public void onResults(Bundle bundle) {
        DebugUtil.DebugLog(this.getClass().toString(), "onResults");

        ArrayList<String> results = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        float scores[] = bundle.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);

        String resultStr = new String();
        for(String s : results)
        {
            resultStr += s + ",";
        }

        DebugUtil.DebugLog(this.getClass().toString(), "onResults", "results", results.toString());

        this.mSpeechRecognitionController.onSpeechResult(results, scores);
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        DebugUtil.DebugLog(this.getClass().toString(), "onPartialResults");
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        DebugUtil.DebugLog(this.getClass().toString(), "onEvent");
    }
}
