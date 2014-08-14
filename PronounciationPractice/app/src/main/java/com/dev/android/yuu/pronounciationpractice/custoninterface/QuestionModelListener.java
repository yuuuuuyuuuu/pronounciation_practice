package com.dev.android.yuu.pronounciationpractice.custoninterface;

/**
 * Created by Chieko on 8/14/14.
 */
public interface QuestionModelListener {

    public abstract void onQuestionInitialized(String firstQuestion);
    public abstract void onQuestionUpdated(String newQuestion);
    public abstract void onQuestionEnded();

}
