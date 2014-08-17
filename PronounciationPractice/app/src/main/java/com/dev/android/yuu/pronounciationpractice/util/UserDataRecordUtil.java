package com.dev.android.yuu.pronounciationpractice.util;

import android.app.Activity;
import android.content.Context;

import com.dev.android.yuu.pronounciationpractice.model.UserScoreModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by Chieko on 8/15/14.
 */
public class UserDataRecordUtil{

    private static ArrayList<UserScoreModel> userScores = new ArrayList<UserScoreModel>();

    private static String DATA_FILE_NAME = "pc_score_data.dat";

    public static void Load(Activity parentActivity)
    {

        try
        {
            FileInputStream fis = parentActivity.openFileInput(UserDataRecordUtil.DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            UserDataRecordUtil.userScores = (ArrayList<UserScoreModel>)ois.readObject();
            ois.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (StreamCorruptedException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void Save(Activity parentActivity)
    {
        try
        {
            FileOutputStream fos = parentActivity.openFileOutput(UserDataRecordUtil.DATA_FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(UserDataRecordUtil.userScores);
            oos.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void Reset()
    {
        UserDataRecordUtil.userScores = new ArrayList<UserScoreModel>();
    }

    public static void UpdateScore(int level, int score, boolean isDone)
    {
        if(null ==  UserDataRecordUtil.userScores)
        {
            UserDataRecordUtil.userScores = new ArrayList<UserScoreModel>();
        }

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

    public static ArrayList<UserScoreModel> GetScores()
    {
        return UserDataRecordUtil.userScores;
    }


}
