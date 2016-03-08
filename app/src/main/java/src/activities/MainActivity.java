package src.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;
import src.MyApplication;
import src.activities.Report.ReportActivity;
import src.data.DB;
import src.activities.ResultPage.StatisticsActivity;
import ui.RippleView;


public class MainActivity extends FrameActivity {



    @Bind(R.id.ripple_setting) RippleView rippleSetting;
    @Bind(R.id.ripple_learning) RippleView rippleLearn;
    @Bind(R.id.ripple_my_result) RippleView rippleMyResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB.INIT(getApplicationContext());
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
//
//        rippleLearn = (RippleView) findViewById( R.id.ripple_learning);
//        rippleMyResult = (RippleView) findViewById(R.id.ripple_my_result);
//        rippleDeveloper = (RippleView) findViewById(R.id.ripple_developer);
        loadSetting();

    }


    private void loadSetting(){

//        SharedPreferences pref = getSharedPreferences(MyApplication.NAME_PREFERENCE, MODE_PRIVATE);
//        MyApplication app = MyApplication.getInstance();
//        app.setSoundEffectEnabled(pref.getBoolean(MyApplication.KEY_SOUND_EFFECT, true));
//        app.setSoundBackgroundEnabled(pref.getBoolean(MyApplication.KEY_SOUND_BACKGROUND, true));
//        app.setSoundVoiceEnabled(pref.getBoolean(MyApplication.KEY_SOUND_VOICE, true));
    }
    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onGetEvent(Object vSender, Object obj) {
        if (vSender == rippleLearn) {
            Intent intent = new Intent(this, ActStartLearning.class);
            startActivity(intent);
            return;
        } else if (vSender == rippleMyResult) {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        }else if(vSender == rippleSetting){
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
//        else if (vSender == rippleDeveloper) {
//            Intent intent = new Intent(this, ActTest.class);
//            startActivity(intent);
//        }
    }

}
