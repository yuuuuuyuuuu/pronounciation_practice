package com.dev.android.yuu.pronounciationpractice.controller;

import android.content.Context;

import com.dev.android.yuu.pronounciationpractice.custoninterface.QuestionModelListener;
import com.dev.android.yuu.pronounciationpractice.model.QuestionModel;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

/**
 * Created by Chieko on 8/14/14.
 */
public class QuestionController {

    private Context mContext = null;
    private QuestionModelListener mListener = null;

    private QuestionModel mQuestionModel = null;

    private int mUserTrialNum = 0;

    public QuestionController(Context context, QuestionModelListener listener)
    {
        this.mContext = context;
        this.mListener = listener;

        this.mQuestionModel = new QuestionModel(this);
    }

    /* Public Methods */
    public boolean checkAnswer(String userAnswer)
    {
        boolean result = false;

        this.mUserTrialNum++;

        String currentTarget = this.mQuestionModel.Current();

        if(userAnswer.toLowerCase().equals(currentTarget.toLowerCase()))
        {
            result = true;

            if(this.mQuestionModel.hasNext())
            {
                this.mListener.onQuestionUpdated(this.mQuestionModel.Next());

            }
            else
            {
                this.mListener.onQuestionEnded();
            }
        }

        return result;
    }

    public int getQuestionSize()
    {
        return this.mQuestionModel.getSize();
    }

    public int getCurrentIndex(){ return this.mQuestionModel.getCurrentIndex(); }

    public int getUserTrial(){return this.mUserTrialNum; }

    public String getCurrentQuestion(){return this.mQuestionModel.Current();}

    public float getScore()
    {
        float score = 100 * ((float)(this.mQuestionModel.getCurrentIndex() + 1) / (float)this.mUserTrialNum);

        DebugUtil.DebugLog(this.getClass().toString(), "getScore:", String.valueOf(score));
        DebugUtil.DebugLog(this.getClass().toString(), "mUserTrial:", String.valueOf(this.mUserTrialNum));

        return score;
    }

    public void resetUserTrial()
    {
        this.mUserTrialNum = 0;
    }

    /* Private Methods */



}
