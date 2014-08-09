package com.dev.android.yuu.pronounciationpractice.model;

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
    public SpeechRecognizerModel(Context context)
    {
        this.mContext = context;
        this.initialize();
    }

    /* Public Methods */
    public boolean start()
    {
        DebugUtil.AssertNull("SpeechRecognizer is null.", this.mSpeechRecognizer);
        DebugUtil.AssertNull("RecognizerIntent is null", this.mRecognizerIntent);

        this.mSpeechRecognizer.startListening(this.mRecognizerIntent);

        return true;
    }

    public boolean cancel()
    {
        return false;
    }

    public boolean stop()
    {
        return false;
    }

    /* Private Methods */
    private boolean initialize()
    {
        DebugUtil.AssertNotNull("SpeechRecognitionListener is not null.", this.mSpeechRecognitionListener);
        DebugUtil.AssertNotNull("SpeechRecognizer is not null", this.mSpeechRecognizer);
        DebugUtil.AssertNull("Context is null.", this.mContext);

        this.mSpeechRecognitionListener = new SpeechRecognitionListener();
        this.mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.mContext);
        this.mSpeechRecognizer.setRecognitionListener(this.mSpeechRecognitionListener);

        this.mRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        this.mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // this.mRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "発音してください。");

        return true;
    }

}
