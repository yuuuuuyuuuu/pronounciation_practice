package com.dev.android.yuu.pronounciationpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.controller.SpeechRecognitionController;
import com.dev.android.yuu.pronounciationpractice.custoninterface.SpeechRecognitionInterface;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

import java.util.ArrayList;

public class PronounciationPracticeActivity extends Activity implements View.OnClickListener, SpeechRecognitionInterface{


    // private UserControllerView mUserControllerView = null;
    private SpeechRecognitionController mSpeechRecognitionController = null;

    // UI
    private Button mButtonListen = null;
    private int ListenButtonId = R.id.button_user_control_listen;

    private Button mButtonSpeak = null;
    private int SpeakButtonId = R.id.button_user_control_speak;

    private TextView mTextViewResult = null;
    private int ResultTextViewId = R.id.textview_user_pronounciation_text;

    private TextView mTextViewScore = null;
    private int ScoreTextViewId = R.id.textview_result_text_score;

    private TextView mTextViewVolume = null;
    private int VolumeTextViewId = R.id.textview_volume_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronounciation_practice);

        this.mSpeechRecognitionController = new SpeechRecognitionController(this, this);
        this.setUiEventHandlers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pronounciation_practice, menu);
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

        DebugUtil.DebugLog(this.getClass().toString(), "onClick");

        int viewId = view.getId();

        if(viewId == this.ListenButtonId)
        {
            DebugUtil.DebugLog(this.getClass().toString(), "ListenButton clicked");

            this.startReferenceSpeaking("test");
        }
        else if(viewId == this.SpeakButtonId)
        {
            DebugUtil.DebugLog(this.getClass().toString(), "SpeakButton clicked");

            this.startRecognition();
        }
    }

    /* Private Methods */
    private void setUiEventHandlers()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "setUiEventHandlers");

        this.mButtonListen = (Button)findViewById(this.ListenButtonId);
        this.mButtonSpeak = (Button)findViewById(this.SpeakButtonId);

        DebugUtil.AssertNotNull("mButtonListen is null.", this.mButtonListen);
        DebugUtil.AssertNotNull("mButtonSpeak is null.", this.mButtonSpeak);

        this.mButtonListen.setOnClickListener(this);
        this.mButtonSpeak.setOnClickListener(this);

        this.mTextViewResult = (TextView)findViewById(this.ResultTextViewId);
        this.mTextViewScore = (TextView)findViewById(this.ScoreTextViewId);
        this.mTextViewVolume = (TextView)findViewById(this.VolumeTextViewId);
    }

    private void startRecognition()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "startRecognition");
        this.mSpeechRecognitionController.startListening();
    }

    private void startReferenceSpeaking(String text)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "startReferenceSpeaking");
    }


    @Override
    public void onSpeechResult(ArrayList<String> results, float[] scores) {

        this.mTextViewResult.setText(results.get(0));
        this.mTextViewScore.setText(String.valueOf(scores[0]));

    }

    @Override
    public void onReadyForSpeech() {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onRmsChanged(float volume) {
        this.mTextViewVolume.setText(String.valueOf(volume));
    }
}
