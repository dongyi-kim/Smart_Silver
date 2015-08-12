package src.activities.Step10;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-08-12.
 */
public class ActStep10 extends StageActivity {
    private static final int BUTTON_COUNT = 12;

    private TextView txtStepNumber;
    private TextView txtFomula;
    public TextView txtInputAnswer;
    public final Button btnAnswerButton[] = new Button[BUTTON_COUNT];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step10DataSet dataSet = new Step10DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_10);

        NUM_OF_STAGE = 10;

        txtStepNumber = (TextView)findViewById(R.id.txt_step_number);
        txtFomula = (TextView)findViewById(R.id.txt_fomula);
        txtInputAnswer = (TextView)findViewById(R.id.txt_input_answer);

        btnAnswerButton[0] = (Button)findViewById(R.id.btn_number_0);
        btnAnswerButton[1] = (Button)findViewById(R.id.btn_number_1);
        btnAnswerButton[2] = (Button)findViewById(R.id.btn_number_2);
        btnAnswerButton[3] = (Button)findViewById(R.id.btn_number_3);
        btnAnswerButton[4] = (Button)findViewById(R.id.btn_number_4);
        btnAnswerButton[5] = (Button)findViewById(R.id.btn_number_5);
        btnAnswerButton[6] = (Button)findViewById(R.id.btn_number_6);
        btnAnswerButton[7] = (Button)findViewById(R.id.btn_number_7);
        btnAnswerButton[8] = (Button)findViewById(R.id.btn_number_8);
        btnAnswerButton[9] = (Button)findViewById(R.id.btn_number_9);
        btnAnswerButton[10] = (Button)findViewById(R.id.btn_erase);
        btnAnswerButton[11] = (Button)findViewById(R.id.btn_submit);


        //button listener for 7_4_1
        for(int i = 0; i < BUTTON_COUNT; i++){
            btnAnswerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int iBtnIndex = 0;
                    for(int ii = 0; ii < BUTTON_COUNT; ii++)
                        if(btnAnswerButton[ii] == v) iBtnIndex = ii;

                    if(iBtnIndex == 11){
                        if(dataSet.iAnswer == Integer.parseInt(txtInputAnswer.getText().toString())) isRight = true;
                        else isRight = false;
                        checkAnswer();
                    }
                    else if(iBtnIndex == 10){
                        if(txtInputAnswer.length() <= 0) ;
                        else txtInputAnswer.setText(txtInputAnswer.getText().toString().substring(0, txtInputAnswer.length() - 1));
                    }
                    else txtInputAnswer.setText(txtInputAnswer.getText().toString() + iBtnIndex);
                }
            });
        }

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtFomula.setText(dataSet.sFomula);
        txtInputAnswer.setText("");

        StartRecording();
    }

    public void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if(isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iStage++;
                    iRetryCount = 0;

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

    public class Step10DataSet {
        public String sFomula;
        public int iAnswer;

        public void setData(int iStage) {
            int iOperand1 = -1, iOperand2 = -1, iOperand3 = -1;

            if(iStage == 1 || iStage == 4 || iStage == 5 || iStage == 9) iOperand1 = rand.nextInt(10);
            else if(iStage == 2 || iStage == 6 || iStage == 8 || iStage == 10) iOperand1 = (rand.nextInt(9) + 1) * 10;
            else iOperand1 = rand.nextInt(81) + 10;

            if(iStage == 4) iOperand2 = rand.nextInt(81) + 10;
            else iOperand2 = rand.nextInt(10);

            if(iStage == 6 || iStage == 7){
                sFomula = "" + iOperand1 + " - " + iOperand2;
                iAnswer = iOperand1 - iOperand2;
            }
            else if(iStage == 9 || iStage == 10){
                sFomula = "" + iOperand1 + " X " + iOperand2;
                iAnswer = iOperand1 * iOperand2;
            }
            else{
                sFomula = "" + iOperand1 + " + " + iOperand2;
                iAnswer = iOperand1 + iOperand2;
            }

            if(iStage == 5){
                iOperand3 = rand.nextInt(10);
                sFomula += " + " + iOperand3;
                iAnswer += iOperand3;
            }
            else if(iStage == 8){
                iOperand3 = rand.nextInt(10);
                sFomula += " - " + iOperand3;
                iAnswer -= iOperand3;
            }

            sFomula += " = ";
        }
    }
}
