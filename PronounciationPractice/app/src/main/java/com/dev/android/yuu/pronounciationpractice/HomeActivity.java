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
import android.widget.Button;
import android.widget.LinearLayout;

import com.dev.android.yuu.pronounciationpractice.R;

import java.util.ArrayList;

public class HomeActivity extends Activity implements View.OnClickListener {

    // Level Buttons
    private static int LEVEL_BUTTON_NUM = 10;
    private ArrayList<Button> mLevelButtons = null;

    private LinearLayout mLinearLayoutLevelButtons = null;
    private int LinearLayoutLevelButtonId = R.id.linearlayout_level_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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
    private void createLevelButtons()
    {
        this.mLinearLayoutLevelButtons = (LinearLayout)findViewById(this.LinearLayoutLevelButtonId);
        this.mLevelButtons = new ArrayList<Button>();

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        lp.setMargins(50, 20, 50, 20);

        for(int i = 0; i < this.LEVEL_BUTTON_NUM; i++)
        {
            Button button = new Button(this);
            button.setLayoutParams(lp);
            button.setText("Level " + String.valueOf(i + 1));
            button.setTag(i + 1);
            button.setOnClickListener(this);
            button.setBackground(getResources().getDrawable(R.drawable.button_blue_basic));

            this.mLevelButtons.add(button);
            this.mLinearLayoutLevelButtons.addView(button);
        }
    }
    private void startQuestionScreen(int level)
    {
        Intent i = new Intent(HomeActivity.this, PronounciationPracticeActivity.class);
        i.putExtra("question_level", level);
        startActivity(i);

    }


}
