package com.dev.android.yuu.pronounciationpractice.util;

import com.dev.android.yuu.pronounciationpractice.model.UserScoreModel;

import java.util.ArrayList;

/**
 * Created by Chieko on 8/15/14.
 */
public class UserDataRecordUtil {

    private static ArrayList<UserScoreModel> userScores = null;

    public static void Load()
    {

    }

    public static void SaveScore(int level, float score, boolean isDone)
    {
        if(null ==  UserDataRecordUtil.userScores) return;

        for(UserScoreModel userScore : UserDataRecordUtil.userScores)
        {
            if(level == userScore.getLevel())
            {
                // already exists
                userScore.setData(level, score, isDone);
                return;
            }
        }

        // new data
        UserScoreModel newScoreModel = new UserScoreModel();
        newScoreModel.setData(level, score, isDone);
        UserDataRecordUtil.userScores.add(newScoreModel);
    }

}
