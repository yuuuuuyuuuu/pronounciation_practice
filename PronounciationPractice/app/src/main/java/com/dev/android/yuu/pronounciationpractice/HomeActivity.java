package com.dev.android.yuu.pronounciationpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dev.android.yuu.pronounciationpractice.R;

import java.util.ArrayList;

public class HomeActivity extends Activity implements View.OnClickListener {

    private Button mButtonLevel1 = null;
    private int ButtonLevel1Id = R.id.button_level1;

    private Button mButtonLevel2 = null;
    private int ButtonLevel2Id = R.id.button_level2;

    private Button mButtonLevel3 = null;
    private int ButtonLevel3Id = R.id.button_level3;

    // Level Buttons
    private static int LEVEL_BUTTON_NUM = 10;
    private ArrayList<Button> mLevelButtons = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.createLevelButtons();
        this.setUiEventHandlers();
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

        if(this.ButtonLevel1Id == viewId)
        {
            this.startQuestionScreen(1);
        }
    }

    /* Private Methods */
    private void setUiEventHandlers()
    {
        this.mButtonLevel1 = (Button)findViewById(this.ButtonLevel1Id);
        this.mButtonLevel2 = (Button)findViewById(this.ButtonLevel2Id);
        this.mButtonLevel3 = (Button)findViewById(this.ButtonLevel3Id);

        this.mButtonLevel1.setOnClickListener(this);
        this.mButtonLevel2.setOnClickListener(this);
        this.mButtonLevel3.setOnClickListener(this);
    }

    private void createLevelButtons()
    {

    }
    private void startQuestionScreen(int level)
    {
        Intent i = new Intent(HomeActivity.this, PronounciationPracticeActivity.class);
        i.putExtra("question_level", level);
        startActivity(i);

    }


}
