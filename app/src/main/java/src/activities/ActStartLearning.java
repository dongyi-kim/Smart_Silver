package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cdmst.smartsilver.R;
import src.data.DB;
import src.data.ResultData;
import src.Setting;
import src.Utility;

/**
 * Created by waps12b on 15. 7. 26..
 */
public class ActStartLearning extends FrameActivity {

    Button btnStepSelector;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_start_learning);

        btnStepSelector = (Button) findViewById( R.id.btn_step_selector );
        btnStepSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodo();
            }
        });
    }

    public void dodo(){
        Intent intent = new Intent(this, ActTest.class);
        startActivity(intent);
    }


    @Override
    public void onGetEvent(Object vSender, Object obj) {
        int from, to;

        if (vSender == findViewById(R.id.ripple_set_01)) {
            from = 1;
            to = 3;
        } else if (vSender == findViewById(R.id.ripple_set_02)) {
            from = 4;
            to = 6;
        } else if (vSender == findViewById(R.id.ripple_set_03)) {
            from = 7;
            to = 9;
        } else if (vSender == findViewById(R.id.ripple_set_04)) {
            from = 10;
            to = 10;
        } else {
            return;
        }

        int iStep = from;
        int iLevel = 1;


        String query = String.format("SELECT * FROM %s WHERE '%d' <= step AND step <= '%d' ORDER BY id DESC LIMIT 1", DB.TABLE_RESULT, from, to);

        ResultData[] data = DB.getResultData(query);
        if (data != null) {
            iStep = data[0].iStep;
            iLevel = data[0].iLevel;
            if (data[0].iStage == Setting.arrNumOfStage[iStep-1][iLevel-1]) {
                if (++iLevel > Setting.arrNumOfStage[iStep-1].length) {
                    iLevel = 1;
                    if (++iStep > to) {
                        iStep = from;
                    }
                }
            }
        }
        Intent intent = new Intent(this, Utility.getStepClass(iStep, iLevel));
        startActivity(intent);
    }
}
