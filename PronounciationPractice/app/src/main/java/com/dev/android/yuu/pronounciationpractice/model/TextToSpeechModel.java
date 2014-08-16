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

    private boolean mTtsReady = false;

    public TextToSpeechModel(Context context)
    {
        this.mContext = context;
        this.initialize();
    }

    /* Public Methods */
    public void Speak(String text)
    {
        if(text.length() < 0) return;
        if(!this.mTtsReady) return;

        if(this.mTextToSpeech.isSpeaking())
        {
            this.mTextToSpeech.stop();
        }

        this.mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void destroy()
    {
        if(null != this.mTextToSpeech)
        {
            this.mTextToSpeech.stop();
            this.mTextToSpeech.shutdown();
        }
    }
    @Override
    public void onInit(int status) {

        if(TextToSpeech.SUCCESS == status)
        {
            Locale enLocale = Locale.ENGLISH;
            if(this.mTextToSpeech.isLanguageAvailable(enLocale) >= TextToSpeech.LANG_AVAILABLE)
            {
                this.mTextToSpeech.setLanguage(enLocale);
                this.mTtsReady = true;
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
