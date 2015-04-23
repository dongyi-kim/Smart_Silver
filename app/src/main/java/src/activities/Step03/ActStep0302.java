package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-23.
 */
public class ActStep0302 extends StageActivity{
    private TextView txtProcess[] = new TextView[3];
    private TextView txtAnswer[] = new TextView[4];
    private Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iProcess = 0;
    public int iCurrentResult = 0;
    public int iNextResult = 0;

    public Step0302DataSet processSet = new Step0302DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_2);

        txtProcess[0] = (TextView)findViewById(R.id.txt_process_1);
        txtProcess[1] = (TextView)findViewById(R.id.txt_process_2);
        txtProcess[2] = (TextView)findViewById(R.id.txt_process_3);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);
        txtAnswer[2] = (TextView)findViewById(R.id.txt_answer_3);
        txtAnswer[3] = (TextView)findViewById(R.id.txt_answer_4);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Button btnCurrentButton = (Button)v;
                    int iSelectAnswer = Integer.parseInt(btnCurrentButton.getText().toString());
                    if(iSelectAnswer == iNextResult) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }


    public void setQuestion(boolean isRetry, Object object){
        if(!isRetry) {
            int iRandomSeed = 2 * iStage + rand.nextInt(1);
            processSet.setData(iRandomSeed);
            StartRecording();
        }

        //delete last process
        for(int i = 0; i < 4; i++)
            txtAnswer[i].setText("");

        //set process
        for(int i = 0; i < 3; i++)
            txtProcess[i].setText("" + processSet.arrProcess[i]);

        //set button
        int iChangeCount = 0, iButtonValue = processSet.startNumber;
        boolean isChanged[] = new boolean[3];
        while(iChangeCount < 3){
            int index = rand.nextInt(3);
            if(isChanged[index]) continue;
            iButtonValue += processSet.arrProcess[iChangeCount++];
            btnAnswer[index].setText("" + iButtonValue);
            isChanged[index] = true;
        }

        iProcess = 0;
        iNextResult = processSet.startNumber;
        setNextProcess();
    }

    public void setNextProcess(){
        txtAnswer[iProcess].setText("" + iNextResult);
        iCurrentResult = iNextResult;

        if(iProcess < 3)
            iNextResult = iCurrentResult + processSet.arrProcess[iProcess];
    }


    public void checkAnswer(Object object){
        if(isRight){
            iProcess++;
            setNextProcess();

            if(iProcess > 2){
                txtAnswer[iProcess].setText("" + iNextResult);
                DlgResultMark dlg = new DlgResultMark(this, isRight);
                dlg.show();
                StopRecording(isRight);

                dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(iStage >= NUM_OF_STAGE) goNext();
                        else {
                            iRetryCount = 0;
                            iStage++;
                            setQuestion(false);
                        }
                    }
                });
            }
        }//isRight

        else{
            DlgResultMark dlg = new DlgResultMark(this, isRight);
            dlg.show();
            if(iRetryCount > 1) StopRecording(isRight);

            dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if(iRetryCount > 1){
                        iRetryCount = 0;
                        iStage++;
                        setQuestion(false);
                    }
                    else{
                        iRetryCount++;
                        setQuestion(true);
                    }
                }
            });
        }// !isRight
    }

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStep0303.class);
        startActivity(intent);
    }

    public class Step0302DataSet {
        private static final int MAX_SET_NUMBER = 7;

        /*If file I/O
        private static final int arrStartNumber[] = new int[MAX_SET_NUMBER];
        private static final int[] arrProcessSet[] = new int[MAX_SET_NUMBER][];
        */
        //else
        private final int arrStartNumber[] = {500, 400, 700, 500, 790, 101, 602};
        private final int[] arrProcessSet[] = {{-100, -10, -1},
                {-100, -100, -100},
                {-100, -100, -100},
                {-10, -10, -10},
                {-10, -10, -10},
                {-1, -1, -1},
                {-1, -1, -1}};
        public int startNumber;
        public int arrProcess[] = new int[4];

        public Step0302DataSet(){
            //If file I/O, read file and set data
        }

        public void setData(int iSeed){
            if(iSeed >= MAX_SET_NUMBER) iSeed = MAX_SET_NUMBER - 1;

            startNumber = arrStartNumber[iSeed];
            for(int i = 0; i < 3; i++)
                arrProcess[i] = arrProcessSet[iSeed][i];
        }
    }
}
