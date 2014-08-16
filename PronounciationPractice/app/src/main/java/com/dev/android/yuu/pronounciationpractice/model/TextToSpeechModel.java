package com.dev.android.yuu.pronounciationpractice.model;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.dev.android.yuu.pronounciationpractice.util.DebugUtil;

import java.util.Locale;

/**
 * Created by Chieko on 8/3/14.
 */
public class TextToSpeechModel implements TextToSpeech.OnInitListener {

    private Context mContext = null;
    private TextToSpeech mTextToSpeech = null;

    public TextToSpeechModel(Context context)
    {
        this.mContext = context;
        this.initialize();
    }

    /* Public Methods */
    public void Speak(String text)
    {
        if(text.length() < 0) return;

        if(this.mTextToSpeech.isSpeaking())
        {
            this.mTextToSpeech.stop();
        }

        this.mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {

        if(TextToSpeech.SUCCESS == status)
        {
            Locale enLocale = Locale.ENGLISH;
            if(this.mTextToSpeech.isLanguageAvailable(enLocale) > TextToSpeech.LANG_AVAILABLE)
            {
                this.mTextToSpeech.setLanguage(enLocale);
            }
            else
            {
                DebugUtil.DebugLog(this.getClass().toString(), "onInit", "English is NOT AVAILABLE in text-to-speech");
            }
        }
    }

    /* Private Methods */
    private void initialize()
    {
        this.mTextToSpeech = new TextToSpeech(this.mContext, this);
    }
}
