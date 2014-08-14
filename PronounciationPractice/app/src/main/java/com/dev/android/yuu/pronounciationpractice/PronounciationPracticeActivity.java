package com.dev.android.yuu.pronounciationpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.controller.QuestionController;
import com.dev.android.yuu.pronounciationpractice.controller.SpeechRecognitionController;
import com.dev.android.yuu.pronounciationpractice.custoninterface.QuestionModelListener;
import com.dev.android.yuu.pronounciationpractice.custoninterface.SpeechRecognitionInterface;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PronounciationPracticeActivity extends Activity implements View.OnClickListener, SpeechRecognitionInterface, QuestionModelListener{

    private SpeechRecognitionController mSpeechRecognitionController = null;
    private QuestionController mQuestionController = null;

    private int mQuestionLevel = 0;

    // UI
    private Button mButtonListen = null;
    private int ListenButtonId = R.id.button_user_control_listen;

    private Button mButtonSpeak = null;
    private int SpeakButtonId = R.id.button_user_control_speak;

    private TextView mTextViewCurrentQuestion = null;
    private int CurrentQuestionTextViewId = R.id.textview_current_question;

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

        Intent i = getIntent();
        this.mQuestionLevel = i.getIntExtra("question_level", 1);

        this.setUiEventHandlers();

        this.mSpeechRecognitionController = new SpeechRecognitionController(this, this);
        this.mQuestionController = new QuestionController(this, this);
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



    @Override
    public void onSpeechResult(ArrayList<String> results, float[] scores) {

        String firstCandidate = results.get(0);

        this.mTextViewResult.setText(firstCandidate);
        this.mTextViewScore.setText(String.valueOf(scores[0]));

        this.mQuestionController.checkAnswer(firstCandidate);

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

    @Override
    public void onQuestionInitialized(String firstQuestion) {
        DebugUtil.DebugLog(this.getClass().toString(), "onQuestionInitialized");
        this.setQuestion(firstQuestion);

    }

    @Override
    public void onQuestionUpdated(String newQuestion) {
        DebugUtil.DebugLog(this.getClass().toString(), "onQuestionUpdated");
        this.setQuestion(newQuestion);
    }

    @Override
    public void onQuestionEnded() {

        this.processFinalize();
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

        this.mTextViewCurrentQuestion = (TextView)findViewById(this.CurrentQuestionTextViewId);
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

    private void setQuestion(String questionText)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "setQuestion");
        this.mTextViewCurrentQuestion.setText(questionText);
    }

    private void processFinalize()
    {
        this.mSpeechRecognitionController.destroyRecognizer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.finish();
    }


}
