package src;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;

import cdmst.smartsilver.R;

/**
 * Created by waps12b on 16. 2. 17..
 */
public class MyApplication extends Application {

    public static final String KEY_SOUND_EFFECT     = "KEY_SOUND_EFFECT";
    public static final String KEY_SOUND_BACKGROUND = "KEY_SOUND_BACKGROUND";
    public static final String KEY_SOUND_VOICE      = "KEY_SOUND_VOICE";
    public static final String NAME_PREFERENCE      = "NAME_PREFERENCE";

    public static int SOUND_ID_RESULT_SUCCESS = 0;
    public static int SOUND_ID_RESULT_FAIL = 1;


    private boolean soundEffectEnabled;
    private boolean soundBackgroundEnabled;
    private boolean soundVoiceEnabled;

    private SoundPool soundPool;

    public void playSound(int soundId){
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }


    public void setSoundEffectEnabled(boolean enabled){
        this.soundEffectEnabled = enabled;
        SharedPreferences pref = getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_SOUND_EFFECT, soundEffectEnabled);
    }
    public void setSoundBackgroundEnabled(boolean enabled){

        this.soundBackgroundEnabled = enabled;
        SharedPreferences pref = getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_SOUND_BACKGROUND, soundBackgroundEnabled);
    }
    public void setSoundVoiceEnabled(boolean enabled){
        this.soundVoiceEnabled = enabled;
        SharedPreferences pref = getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_SOUND_VOICE, soundVoiceEnabled);

    }

    public boolean isSoundEffectEnabled(){ return this.soundEffectEnabled ; }
    public boolean isSoundBackgroundEnabled(){ return this.soundBackgroundEnabled; }
    public boolean isSoundVoiceEnabled(){ return this.soundVoiceEnabled; }

    public static final MyApplication getInstance(){
        return instance;
    }
    private static MyApplication instance;
    public MyApplication()
    {
        instance = this;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences pref = getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE);

        soundEffectEnabled      = pref.getBoolean(KEY_SOUND_EFFECT, true);
        soundBackgroundEnabled  = pref.getBoolean(KEY_SOUND_BACKGROUND, true);
        soundVoiceEnabled       = pref.getBoolean(KEY_SOUND_VOICE, true);
        soundPool =  new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        SOUND_ID_RESULT_SUCCESS = soundPool.load(this, R.raw.sound_success, 1);
        SOUND_ID_RESULT_FAIL = soundPool.load(this, R.raw.sound_fail, 1);
    }
}
