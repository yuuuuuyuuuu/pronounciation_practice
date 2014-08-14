package com.dev.android.yuu.pronounciationpractice.model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

/**
 * Created by Chieko on 8/3/14.
 */
public class SpeechRecognizerModel {

    private SpeechRecognizer mSpeechRecognizer = null;
    private SpeechRecognitionListener mSpeechRecognitionListener = null;

    private Context mContext = null;

    private Intent mRecognizerIntent = null;

    /* Constructor */
    public SpeechRecognizerModel(Context context, SpeechRecognitionListener speechRecognitionListener)
    {
        this.mContext = context;
        this.mSpeechRecognitionListener = speechRecognitionListener;

        this.initialize();
    }

    /* Public Methods */
    public boolean start()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "start()");

        DebugUtil.AssertNotNull("SpeechRecognizer is null.", this.mSpeechRecognizer);
        DebugUtil.AssertNotNull("RecognizerIntent is null", this.mRecognizerIntent);

        this.mSpeechRecognizer.startListening(this.mRecognizerIntent);

        return true;
    }

    public boolean cancel()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "cancel()");

        boolean result = false;

        return false;
    }

    public boolean stop()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "stop()");

        boolean result = false;

        this.mSpeechRecognizer.stopListening();

        return result;
    }

    /* Private Methods */
    private boolean initialize()
    {
        this.mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.mContext);
        this.mSpeechRecognizer.setRecognitionListener(this.mSpeechRecognitionListener);

        this.mRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        this.mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        this.mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        this.mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US");

        return true;
    }

}
