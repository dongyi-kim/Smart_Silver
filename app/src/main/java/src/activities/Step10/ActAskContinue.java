package src.activities.Step10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.data.DB;
import src.data.ResultData;
import src.activities.MainActivity;
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

        ResultData[] result = DB.getResultData( String.format("SELECT * FROM %s WHERE step = '%d' ; ", DB.TABLE_RESULT, 10) );


        int iResultCount = result.length;
        int iPastLevel = 0;
        int iCorrectCount = 0;
        int iMaxCorrectCount = 0;
        long iCurrentRecord = 0;
        long iBestRecord = 1l << 62;

        for(int i = 0; i < iResultCount; i++){
            if(iPastLevel == 10){
                if(iCorrectCount >= iMaxCorrectCount || (iCorrectCount == iMaxCorrectCount && iCurrentRecord < iBestRecord)) {
                    iMaxCorrectCount = iCorrectCount;
                    iBestRecord = iCurrentRecord;
                }

                iCurrentRecord = iPastLevel = iCorrectCount = 0;
            }

            if(result[i].iStage - iPastLevel != 1){
                iCurrentRecord = iPastLevel = 0; continue;
            }

            if(result[i].isSuccess) iCorrectCount++;
            iCurrentRecord += result[i].getMilliTime();
            iPastLevel++;
        }

        if(iCorrectCount >= iMaxCorrectCount || (iCorrectCount == iMaxCorrectCount && iCurrentRecord < iBestRecord)) {
            txtCurrentRecord.setTextColor(0xFFEE3333);
            iMaxCorrectCount = iCorrectCount;
            iBestRecord = iCurrentRecord;
        }

        txtCurrentRecord.setText("" + iCorrectCount * 10 + "점(" + iCurrentRecord / 1000 + " 초)");
        txtBestRecord.setText("" + iMaxCorrectCount * 10 + "점(" + iBestRecord / 1000 + " 초)");
    }

    View.OnClickListener clickBtnContinue = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent;
            if(v == btnRetry) intent = new Intent(v.getContext(), ActStep10.class);
            else intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
        }
    };
}
