package com.dev.android.yuu.pronounciationpractice.model;

import java.io.Serializable;

/**
 * Created by Chieko on 8/15/14.
 */
public class UserScoreModel implements Serializable{

    private int mLevel = -1;
    private float mScore = 0;
    private boolean mIsDone = false;

    public UserScoreModel()
    {

    }

    public void setData(int level, float score, boolean isDone)
    {
        this.mLevel = level;
        this.mScore = score;
        this.mIsDone = isDone;
    }

    public int getLevel()
    {
        return this.mLevel;
    }

    public float getScore()
    {
        return this.mScore;
    }

    public boolean getDoneFlag()
    {
        return this.mIsDone;
    }

}
