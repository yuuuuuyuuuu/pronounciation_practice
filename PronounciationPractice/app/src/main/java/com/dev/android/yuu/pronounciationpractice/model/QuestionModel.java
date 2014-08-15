package com.dev.android.yuu.pronounciationpractice.model;

import com.dev.android.yuu.pronounciationpractice.controller.QuestionController;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.util.QuestionDataUtil;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/14/14.
 */
public class QuestionModel {

    private QuestionController mQuestionController = null;

    private ArrayList<String> mQuestions = null;
    private int mCurrentQuestionLevel = -1;
    private int mCurrentQuestionIndex = -1;

    private int mLevel = 0;

    public QuestionModel(QuestionController questionController, int level)
    {
        this.mQuestionController = questionController;
        this.mLevel = level;

        this.initialize();
    }

    /* Public Methods */
    public String Next()
    {
        this.mCurrentQuestionIndex++;

        if(this.mQuestions.size() == this.mCurrentQuestionIndex)
        {
            this.mCurrentQuestionIndex = this.mQuestions.size() - 1;
        }

        return this.mQuestions.get(this.mCurrentQuestionIndex);
    }

    public String Previous()
    {
        this.mCurrentQuestionIndex--;

        if(0 == this.mCurrentQuestionIndex)
        {
            this.mCurrentQuestionIndex = 0;
        }

        return this.mQuestions.get(this.mCurrentQuestionIndex);
    }

    public String Current()
    {
        return this.mQuestions.get(this.mCurrentQuestionIndex);
    }

    public void reset()
    {
        this.mCurrentQuestionIndex = 0;
    }

    public void setLevel(int level)
    {
        this.mCurrentQuestionLevel = level;
    }

    public int getSize()
    {
        return this.mQuestions.size();
    }

    public int getCurrentIndex()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "getCurrentIndex", String.valueOf(this.mCurrentQuestionIndex));
        return this.mCurrentQuestionIndex;
    }

    public boolean hasNext()
    {
        boolean result = false;

        if(this.mCurrentQuestionIndex == this.mQuestions.size() - 1)
        {
            result = false;
        }
        else
        {
            result = true;
        }

        return result;
    }
    /* Private Methods */
    private void initialize()
    {
        boolean loadSuccess = this.load();

        if(loadSuccess) this.mCurrentQuestionIndex = 0;
    }

    private boolean load()
    {
        boolean result = false;

        /*
        // test data
        this.mQuestions = new ArrayList<String>();
        this.mQuestions.add("Hello");
        this.mQuestions.add("Contribution");
        this.mQuestions.add("Mobile");
        this.mQuestions.add("Refrigerator");
        */

        this.mQuestions = QuestionDataUtil.GetQuestion(this.mLevel);
        result = true;

        return result;
    }





}
