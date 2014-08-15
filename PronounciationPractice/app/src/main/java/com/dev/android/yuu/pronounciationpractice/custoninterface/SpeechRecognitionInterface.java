package com.dev.android.yuu.pronounciationpractice.custoninterface;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/14/14.
 */
public interface SpeechRecognitionInterface {

    public abstract void onSpeechResult(ArrayList<String> results, float[] scores);
    public abstract void onReadyForSpeech();
    public abstract void onEndOfSpeech();
    public abstract void onRmsChanged(float volume);
    public abstract void onError(int errorCode);

}
