package com.dev.android.yuu.pronounciationpractice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.dev.android.yuu.pronounciationpractice.R;
import com.dev.android.yuu.pronounciationpractice.controller.QuestionController;
import com.dev.android.yuu.pronounciationpractice.controller.SpeechRecognitionController;
import com.dev.android.yuu.pronounciationpractice.custoninterface.QuestionModelListener;
import com.dev.android.yuu.pronounciationpractice.custoninterface.SpeechRecognitionInterface;
import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;
import com.dev.android.yuu.pronounciationpractice.util.ScreenUtil;
import com.dev.android.yuu.pronounciationpractice.util.UserDataRecordUtil;
import com.dev.android.yuu.pronounciationpractice.view.UserControllerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PronounciationPracticeActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener, SpeechRecognitionInterface, QuestionModelListener, Animation.AnimationListener {

    private SpeechRecognitionController mSpeechRecognitionController = null;
    private QuestionController mQuestionController = null;

    private int mQuestionLevel = 0;

    // UI
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

    private TextView mTextViewLevel = null;
    private int LevelTextViewId = R.id.textview_level;

    private TextView mTextViewQuestionNumStatus = null;
    private int QuestionNumStatusId = R.id.textview_question_num_status;

    private String mNextQuestion = "";

    // Dialog
    private AlertDialog.Builder mScoreDialog = null;

    // Animation
    private TranslateAnimation mQuestionSlideInAnimation = null;
    private static int SLIDE_IN_ANIM_DURATION = 500;

    private TranslateAnimation mQuestionSlideOutAnimation = null;
    private static int SLIDE_OUT_ANIM_DURATION = 500;

    private RotateAnimation mMicRotationAnimation = null;
    private static int MIC_ROTATION_ANIM_DURATION = 800;

    private static float USER_ACHIEVEMENT_THRESHOLD = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pronounciation_practice);

        Intent i = getIntent();
        this.mQuestionLevel = i.getIntExtra("question_level", 1);

        this.setUiEventHandlers();
        this.initializeAnimation();

        this.mSpeechRecognitionController = new SpeechRecognitionController(this, this);
        this.mQuestionController = new QuestionController(this, this);

        this.initialize();
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


        if(viewId == this.SpeakButtonId)
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
    public void onReadyForSpeech()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "onReadyForSpeech");
        this.mButtonSpeak.startAnimation(this.mMicRotationAnimation);
    }

    @Override
    public void onEndOfSpeech()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "onEndOfSpeech");
        this.mButtonSpeak.clearAnimation();
    }

    @Override
    public void onRmsChanged(float volume) {
        this.mTextViewVolume.setText(String.valueOf(volume));
    }

    @Override
    public void onError(int errorCode)
    {
        DebugUtil.DebugLog(this.getClass().toString(), "onError", "errorCode:" + String.valueOf(errorCode));
        this.mButtonSpeak.clearAnimation();
    }

    @Override
    public void onQuestionInitialized(String firstQuestion) {
        DebugUtil.DebugLog(this.getClass().toString(), "onQuestionInitialized");
        this.storeNextQuestion(firstQuestion);
        this.setNewQuestion();
    }

    @Override
    public void onQuestionUpdated(String newQuestion) {
        DebugUtil.DebugLog(this.getClass().toString(), "onQuestionUpdated");
        this.storeNextQuestion(newQuestion);
        this.destroyOldQuestion();
    }

    @Override
    public void onQuestionEnded() {

        this.storeNextQuestion("You finished!!");

        this.mScoreDialog.setMessage("Score: " + String.valueOf(this.mQuestionController.getScore()));
        this.mScoreDialog.show();

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if(animation.equals(this.mQuestionSlideOutAnimation))
        {
            DebugUtil.DebugLog(this.getClass().toString(), "SlideOutEnded");
            this.setNewQuestion();
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /* Private Methods */
    private void initialize()
    {
        this.mScoreDialog = new AlertDialog.Builder(this);
        this.mScoreDialog.setTitle("Score");
        this.mScoreDialog.setPositiveButton("OK", this);

        String firstQuestion = this.mQuestionController.getCurrentQuestion();

        this.storeNextQuestion(firstQuestion);
        this.setNewQuestion();
    }

    private void setUiEventHandlers()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "setUiEventHandlers");

        this.mButtonSpeak = (Button)findViewById(this.SpeakButtonId);

        this.mButtonSpeak.setOnClickListener(this);

        this.mTextViewCurrentQuestion = (TextView)findViewById(this.CurrentQuestionTextViewId);
        this.mTextViewResult = (TextView)findViewById(this.ResultTextViewId);
        this.mTextViewScore = (TextView)findViewById(this.ScoreTextViewId);
        this.mTextViewVolume = (TextView)findViewById(this.VolumeTextViewId);
        this.mTextViewLevel = (TextView)findViewById(this.LevelTextViewId);
        this.mTextViewQuestionNumStatus = (TextView)findViewById(this.QuestionNumStatusId);

        this.mTextViewLevel.setText("Level " + this.mQuestionLevel);
    }

    private void initializeAnimation()
    {
        Point windowSize = ScreenUtil.GetWindowSize(this);

        this.mQuestionSlideInAnimation = new TranslateAnimation(windowSize.x, 0, 0, 0);
        this.mQuestionSlideInAnimation.setDuration(this.SLIDE_IN_ANIM_DURATION);
        this.mQuestionSlideInAnimation.setAnimationListener(this);

        this.mQuestionSlideOutAnimation = new TranslateAnimation(0, -windowSize.x, 0, 0);
        this.mQuestionSlideOutAnimation.setDuration(this.SLIDE_OUT_ANIM_DURATION);
        this.mQuestionSlideOutAnimation.setAnimationListener(this);

        this.mMicRotationAnimation = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.mMicRotationAnimation.setDuration(this.MIC_ROTATION_ANIM_DURATION);
        this.mMicRotationAnimation.setRepeatCount(Animation.INFINITE);

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

    private void destroyOldQuestion()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "destroyOldQuestion");
        this.mTextViewCurrentQuestion.startAnimation(this.mQuestionSlideOutAnimation);
    }

    private void setNewQuestion()
    {
        DebugUtil.DebugLog(this.getClass().toString(), "setNewQuestion");
        this.mTextViewCurrentQuestion.setText(this.mNextQuestion);

        int currentQuestionIndex = this.mQuestionController.getCurrentIndex();
        int questionSize = this.mQuestionController.getQuestionSize();

        this.mTextViewQuestionNumStatus.setText((currentQuestionIndex + 1) + " / " + questionSize);

        this.mTextViewCurrentQuestion.startAnimation(this.mQuestionSlideInAnimation);
    }

    private void processFinalize()
    {
        // Save score
        float score = this.mQuestionController.getScore();
        boolean isDone = false;
        if(this.USER_ACHIEVEMENT_THRESHOLD <= score) isDone = true;

        UserDataRecordUtil.UpdateScore(this.mQuestionLevel, score, isDone);
        UserDataRecordUtil.Save(this);

        this.mSpeechRecognitionController.destroyRecognizer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.finish();
    }

    private void storeNextQuestion(String nextQuestion)
    {
        this.mNextQuestion = nextQuestion;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int whichButton)
    {
        if(whichButton == DialogInterface.BUTTON_POSITIVE)
        {
            this.processFinalize();
        }
    }
}
