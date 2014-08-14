package com.dev.android.yuu.pronounciationpractice.model;

import com.dev.android.yuu.pronounciationpractice.controller.QuestionController;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/14/14.
 */
public class QuestionModel {

    private QuestionController mQuestionController = null;

    private ArrayList<String> mQuestions = null;
    private int mCurrentQuestionLevel = -1;
    private int mCurrentQuestionIndex = -1;


    public QuestionModel(QuestionController questionController)
    {
        this.mQuestionController = questionController;

        this.initialize();
    }

    /* Public Methods */
    public String Next()
    {
        this.mCurrentQuestionIndex++;

        if(this.mQuestions.size() == this.mCurrentQuestionIndex)
        {
            this.mCurrentQuestionIndex = this.mQuestions.size() - 1;
            this.mQuestionController.onQuestionEnded();
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

    /* Private Methods */
    private void initialize()
    {
        boolean loadSuccess = this.load();

        if(loadSuccess) this.mCurrentQuestionIndex = 0;
    }

    private boolean load()
    {
        boolean result = false;

        // test data
        this.mQuestions = new ArrayList<String>();
        this.mQuestions.add("Hello");
        this.mQuestions.add("Contribution");
        this.mQuestions.add("Mobile");
        this.mQuestions.add("Coincidence");
        this.mQuestions.add("Refrigerator");

        result = true;

        return result;
    }





}
