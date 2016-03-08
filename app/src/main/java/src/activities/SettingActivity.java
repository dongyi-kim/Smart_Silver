package src.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;
import src.MyApplication;

/**
 * Created by waps12b on 16. 3. 8..
 */
public class SettingActivity extends Activity {



    @Bind(R.id.checkbox_sound_effect) CheckBox checkSoundEffect;
    @Bind(R.id.checkbox_sound_background) CheckBox checkSoundBackground;
    @Bind(R.id.checkbox_sound_voice) CheckBox checkSoundVoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication app = MyApplication.getInstance();
        app.setSoundEffectEnabled( checkSoundEffect.isChecked() );
        app.setSoundBackgroundEnabled( checkSoundBackground.isChecked() );
        app.setSoundVoiceEnabled( checkSoundVoice.isChecked() );
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication app = MyApplication.getInstance();
        checkSoundEffect.setChecked(app.isSoundEffectEnabled());
        checkSoundBackground.setChecked(app.isSoundBackgroundEnabled());
        checkSoundVoice.setChecked(app.isSoundVoiceEnabled());
    }
}
