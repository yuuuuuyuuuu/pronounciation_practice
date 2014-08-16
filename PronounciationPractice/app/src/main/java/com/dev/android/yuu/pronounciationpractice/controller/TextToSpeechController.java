package com.dev.android.yuu.pronounciationpractice.controller;

import android.content.Context;

import com.dev.android.yuu.pronounciationpractice.model.TextToSpeechModel;

/**
 * Created by Chieko on 8/16/14.
 */
public class TextToSpeechController {

    private Context mContext = null;
    private TextToSpeechModel mTextToSpeechModel = null;

    public TextToSpeechController(Context context)
    {
        this.mContext = context;
        this.initialize();
    }

    /* Public Methods */
    public void Speak(String text)
    {
        this.mTextToSpeechModel.Speak(text);
    }

    /* Private Methods */
    private void initialize()
    {
        this.mTextToSpeechModel = new TextToSpeechModel(this.mContext);
    }

}
