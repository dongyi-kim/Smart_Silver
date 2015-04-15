package src.activities.Step02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-15.
 */
public class ActStep0203 extends StageActivity {
    private final TextView txtCalculateSet[] = new TextView[2];
    public final ImageButton btnCalculate[] = new ImageButton[2];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iBigIndex = 0;

    public Step0203DataSet dataSet = new Step0203DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_3);

        txtCalculateSet[0] = (TextView)findViewById(R.id.txt_calculate_1);
        txtCalculateSet[1] = (TextView)findViewById(R.id.txt_calculate_2);
        btnCalculate[0] = (ImageButton)findViewById(R.id.btn_calculate_1);
        btnCalculate[1] = (ImageButton)findViewById(R.id.btn_calculate_2);

        //button listener
        for(int i = 0; i < 2; i++) {
            btnCalculate[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int iIndex = (btnCalculate[0] == v ? 0 : 1);
                    if (iIndex == iBigIndex) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        }

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = (iStage - 1) * 2 + rand.nextInt(1);
        dataSet.setData(iRandomSeed);

        txtCalculateSet[0].setText(dataSet.sCalculateSet[0]);
        txtCalculateSet[1].setText(dataSet.sCalculateSet[1]);

        int iResult[] = new int[2];
        for(int i = 0; i < 2; i++){
            String sCurrentString = txtCalculateSet[i].getText().toString();
            int iLength = sCurrentString.length();
            int iOperand[] = new int[2];
            char cOperator = '+';

            iOperand[0] = iOperand[1] = 0;
            int iOperandIndex = 0;
            for(int iRead = 0; iRead < iLength; iRead++){
                char c = sCurrentString.charAt(iRead);
                if(c < '0' || c > '9'){
                    if(c == '+' || c == '-') cOperator = c;
                    iOperandIndex = 1;
                }
                else {
                    iOperand[iOperandIndex] *= 10;
                    iOperand[iOperandIndex] += c - '0';
                }
            }

            iResult[i] = iOperand[0];
            if(cOperator == '+') iResult[i] += iOperand[1];
            else iResult[i] -= iOperand[1];
        }

        iBigIndex = (iResult[0] > iResult[1] ? 0 : 1);
    }

    public void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iStage++;
                    if(iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                }
                else{
                    iRetryCount++;
                }
            }
        });
    }

    public void goNext(Object object){
        Intent intent = new Intent(this, ActMain.class);
        startActivity(intent);
    }

    public class Step0203DataSet {
        private final String arrCalculateSet[][] = {{"2+1", "2+7"},
                {"5+0", "2+9"}, {"4+8", "7+2"}, {"11+4", "9+8"}, {"5-3", "7-4"},
                {"6-5", "8-1"}, {"9-3", "2+2"}, {"4+5", "12-7"}, {"5+6", "16-8"}};

        public String sCalculateSet[] = new String[2];

        public void setData(int iSeed) {
            if(iSeed > 8) iSeed = 8;
            sCalculateSet[0] = arrCalculateSet[iSeed][0];
            sCalculateSet[1] = arrCalculateSet[iSeed][1];
        }
    }
}