package src.activities.Step10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.DB;
import src.ResultData;
import src.activities.ActMain;
import src.activities.FrameActivity;

/**
 * Created by Acka on 2015-08-24.
 */
public class ActAskContinue extends FrameActivity {
    Button btnRetry;
    Button btnStop;
    TextView txtCurrentRecord;
    TextView txtBestRecord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ask_continue);

        btnRetry = (Button) findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(clickBtnContinue);

        btnStop = (Button) findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(clickBtnContinue);

        txtCurrentRecord = (TextView)findViewById(R.id.txt_current_record);
        txtBestRecord = (TextView)findViewById(R.id.txt_best_record);

        ResultData[] result = DB.SELECT(10, -1, -1, -1, "");

        int iResultCount = result.length;
        int iPastLevel = 0;
        long iCurrentRecord = 0;
        long iBestRecord = 1l << 62;

        for(int i = 0; i < iResultCount; i++){
            if(iPastLevel == 10){
                if(iCurrentRecord < iBestRecord) iBestRecord = iCurrentRecord;
                iCurrentRecord = iPastLevel = 0;
            }

            if(result[i].iStage - iPastLevel != 1){
                iCurrentRecord = iPastLevel = 0; continue;
            }

            iCurrentRecord += result[i].getMilliTime();
            iPastLevel++;
        }

        if(iBestRecord > iCurrentRecord){
            txtCurrentRecord.setTextColor(0xFFEE3333);
            iBestRecord = iCurrentRecord;
        }

        txtCurrentRecord.setText("" + iCurrentRecord / 1000.0 + " 초");
        txtBestRecord.setText("" + iBestRecord / 1000.0 + " 초");
    }

    View.OnClickListener clickBtnContinue = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent;
            if(v == btnRetry) intent = new Intent(v.getContext(), ActStep10.class);
            else intent = new Intent(v.getContext(), ActMain.class);
            startActivity(intent);
        }
    };
}
