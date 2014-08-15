package com.dev.android.yuu.pronounciationpractice.model;

import java.util.Comparator;

/**
 * Created by Chieko on 8/15/14.
 */
public class LevelComparator implements Comparator<UserScoreModel> {

    @Override
    public int compare(UserScoreModel userScoreModel1, UserScoreModel userScoreModel2) {

        return userScoreModel1.getLevel() < userScoreModel2.getLevel() ? -1 : 1;
    }
}
