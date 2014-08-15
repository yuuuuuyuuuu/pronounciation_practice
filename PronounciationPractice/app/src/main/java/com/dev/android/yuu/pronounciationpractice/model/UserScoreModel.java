package com.dev.android.yuu.pronounciationpractice.model;

/**
 * Created by Chieko on 8/15/14.
 */
public class UserScoreModel {

    private int level = -1;
    private float score = 0;
    private boolean isDone = false;

    public UserScoreModel()
    {

    }

    public void setData(int level, float score, boolean done)
    {

    }

    public int getLevel()
    {
        return this.level;
    }

    public float getScore()
    {
        return this.score;
    }

    public boolean getDoneFlag()
    {
        return this.isDone;
    }

}
