package com.dev.android.yuu.pronounciationpractice.controller;

import android.content.Context;

import com.dev.android.yuu.pronounciationpractice.custoninterface.QuestionModelListener;
import com.dev.android.yuu.pronounciationpractice.model.QuestionModel;

/**
 * Created by Chieko on 8/14/14.
 */
public class QuestionController {

    private Context mContext = null;
    private QuestionModelListener mListener = null;

    private QuestionModel mQuestionModel = null;

    public QuestionController(Context context, QuestionModelListener listener)
    {
        this.mContext = context;
        this.mListener = listener;

        this.mQuestionModel = new QuestionModel(this);
        this.mListener.onQuestionUpdated(this.mQuestionModel.Current());
    }

    /* Public Methods */
    public boolean checkAnswer(String userAnswer)
    {
        boolean result = false;

        String currentTarget = this.mQuestionModel.Current();

        if(userAnswer.toLowerCase().equals(currentTarget.toLowerCase()))
        {
            this.mListener.onQuestionUpdated(this.mQuestionModel.Next());
            result = true;
        }

        return result;
    }

    /* Private Methods */



}
