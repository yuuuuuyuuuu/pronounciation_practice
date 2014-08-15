package com.dev.android.yuu.pronounciationpractice;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.model.LevelComparator;
import com.dev.android.yuu.pronounciationpractice.model.UserScoreModel;
import com.dev.android.yuu.pronounciationpractice.util.UserDataRecordUtil;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends Activity implements View.OnClickListener {

    // Level Buttons
    private static int LEVEL_BUTTON_NUM = 10;
    private ArrayList<Button> mLevelButtons = null;

    private LinearLayout mLinearLayoutLevelButtons = null;
    private int LinearLayoutLevelButtonId = R.id.linearlayout_level_button;

    // Intent ID
    private final int INTENT_PRONOUNCIATION_PRACTICE_ACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_home);

        this.createLevelButtons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();

        if(view instanceof Button)
        {
            int level = (Integer)view.getTag();

            if(1 <= level && level <= this.LEVEL_BUTTON_NUM)
            {
                this.startQuestionScreen(level);
            }
        }
    }

    /* Private Methods */
    private void clearLevelButtons()
    {
        if(null == this.mLinearLayoutLevelButtons)
        {
            this.mLinearLayoutLevelButtons = (LinearLayout)findViewById(this.LinearLayoutLevelButtonId);
        }

        this.mLinearLayoutLevelButtons.removeAllViews();
    }

    private void createLevelButtons()
    {

        this.mLinearLayoutLevelButtons = (LinearLayout)findViewById(this.LinearLayoutLevelButtonId);
        this.mLevelButtons = new ArrayList<Button>();

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        lp.setMargins(50, 20, 50, 20);

        // User scores
        UserDataRecordUtil.Load(this);
        ArrayList<UserScoreModel> userScores = UserDataRecordUtil.GetScores();
        Collections.sort(userScores, new LevelComparator());

        for(int i = 0; i < this.LEVEL_BUTTON_NUM; i++)
        {
            Button button = new Button(this);
            button.setLayoutParams(lp);
            button.setTag(i + 1);
            button.setOnClickListener(this);
            button.setBackground(getResources().getDrawable(R.drawable.button_blue_basic));

            String buttonLabel = "初級 " + String.valueOf(i + 1);

            if(0 == i)
            {
                if(0 < userScores.size())
                {
                    UserScoreModel usm = userScores.get(0);
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   スコア：" + String.valueOf(usm.getScore());
                }
                else
                {
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   未チャレンジ";
                }

                button.setEnabled(true);
            }
            else
            {
                if(i < userScores.size())
                {
                    UserScoreModel usm = userScores.get(i);
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   スコア：" + String.valueOf(usm.getScore());
                    button.setEnabled(true);
                }
                else if(i == userScores.size())
                {
                    UserScoreModel usm_prev = userScores.get(i - 1);
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   未チャレンジ";
                    button.setEnabled(usm_prev.getDoneFlag());
                }
                else
                {
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   未チャレンジ";
                    button.setEnabled(false);
                }
            }

            /*
            if(1 <= userScores.size() && i <= userScores.size() && 0 != i)
            {
                UserScoreModel usm = userScores.get(i - 1);
                button.setEnabled(usm.getDoneFlag());
                buttonLabel = "初級 "  + String.valueOf(i + 1) + "   スコア：" + String.valueOf(usm.getScore());
            }
            else if(0 == i)
            {
                UserScoreModel usm = null;
                if(0 < userScores.size())
                {
                    usm = userScores.get(0);
                }

                if(null != usm && 1 == usm.getLevel())
                {
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   スコア：" + String.valueOf(usm.getScore());
                }
                else
                {
                    buttonLabel = "初級 "  + String.valueOf(i + 1) + "   未チャレンジ";
                }

                button.setEnabled(true);
            }
            else
            {
                buttonLabel = "初級 "  + String.valueOf(i + 1) + "   未チャレンジ";
                button.setEnabled(false);
            }
            */


            button.setTextSize(18);
            button.setText(buttonLabel);

            this.mLevelButtons.add(button);
            this.mLinearLayoutLevelButtons.addView(button);
        }
    }
    private void startQuestionScreen(int level)
    {
        Intent i = new Intent(HomeActivity.this, PronounciationPracticeActivity.class);
        i.putExtra("question_level", level);
        startActivityForResult(i, this.INTENT_PRONOUNCIATION_PRACTICE_ACTIVITY);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(this.INTENT_PRONOUNCIATION_PRACTICE_ACTIVITY == requestCode)
        {
            // relaod user scores
            UserDataRecordUtil.Load(this);
            this.clearLevelButtons();
            this.createLevelButtons();
        }
    }


}
