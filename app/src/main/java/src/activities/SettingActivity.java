package src.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;
import src.MyApplication;
import src.data.DB;

/**
 * Created by waps12b on 16. 3. 8..
 */
public class SettingActivity extends Activity {



    @Bind(R.id.checkbox_sound_effect) CheckBox checkSoundEffect;
    @Bind(R.id.checkbox_sound_background) CheckBox checkSoundBackground;
    @Bind(R.id.checkbox_sound_voice) CheckBox checkSoundVoice;
    @Bind(R.id.btn_remove_data_all) Button btnRemoveDataAll;
    @Bind(R.id.btn_remove_data_today) Button btnRemoveDataToday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        btnRemoveDataAll.setOnClickListener(onClickRemoveData);
        btnRemoveDataToday.setOnClickListener(onClickRemoveData);


    }

    View.OnClickListener onClickRemoveData = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            String strToday = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            final String query = "DELETE FROM " + DB.TABLE_RESULT + ( (v==btnRemoveDataToday) ? " WHERE timestamp >= \""+strToday+"\" ;" : "") ;
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            DB.execSQL(query);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:

                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
            builder.setMessage("정말 삭제하시겠습니까?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    };

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
