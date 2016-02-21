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


    public synchronized void setQuestion(boolean isRetry, Object object){

        if(!isRetry) {
            processSet.setData(iStage);
            startRecording();
        }

        //delete last process
        for(int i = 0; i < 4; i++)
            txtAnswer[i].setText("");

        //set process
        for(int i = 0; i < 3; i++)
            txtProcess[i].setText("" + processSet.iProcess);

        //set button
        int iChangeCount = 0, iButtonValue = processSet.startNumber;
        boolean isChanged[] = new boolean[3];
        while(iChangeCount < 3){
            int index = rand.nextInt(3);
            if(isChanged[index]) continue;
            iButtonValue += processSet.iProcess;
            btnAnswer[index].setText("" + iButtonValue);
            isChanged[index] = true;
            iChangeCount++;
        }

        iProcess = 0;
        iNextResult = processSet.startNumber;
        setNextProcess();
    }

    public void setNextProcess(){
        txtAnswer[iProcess].setText("" + iNextResult);
        iCurrentResult = iNextResult;

        if(iProcess < 3)
            iNextResult = iCurrentResult + processSet.iProcess;
    }


    public synchronized void checkAnswer(Object object){
        countUpTry();
        if(isRight){
            iProcess++;
            setNextProcess();

            if(iProcess > 2){
                txtAnswer[iProcess].setText("" + iNextResult);
                DlgResultMark dlg = new DlgResultMark(this, isRight);
                dlg.show();
                stopRecording(isRight);

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
            if(iRetryCount > 1) stopRecording(isRight);

            dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if(iRetryCount > 1){
                        if(iStage >= NUM_OF_STAGE) goNext();
                        else {
                            iRetryCount = 0;
                            iStage++;
                            setQuestion(false);
                        }
                    }
                    else{
                        iRetryCount++;
                        setQuestion(true);
                    }
                }
            });
        }// !isRight
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0303.class);
        startActivity(intent);
    }

    public class Step0302DataSet {
        private final int arrHundredBase[] = {4, 1, 2, 1, 2};
        private final int arrHundredRange[] = {2, 3, 4, 5, 5};
        private final int arrTenBase[] = {0, 3, 0, 1, 0};
        private final int arrTenRange[] = {10, 7, 3, 9, 1};
        private final int arrOneBase[] = {0, 0, 0, 3, 0};
        private final int arrOneRange[] = {1, 1, 1, 7, 3};
        private final int arrProcessSet[] = {-100, -10, -10, -1, -1};

        public int startNumber;
        public int iProcess;

        public void setData(int iStage){
            int iSeed = iStage - 1;

            startNumber = (arrHundredBase[iSeed] + rand.nextInt(arrHundredRange[iSeed])) * 100
                    + (arrTenBase[iSeed] + rand.nextInt(arrTenRange[iSeed])) * 10 +  (arrOneBase[iSeed] + rand.nextInt(arrOneRange[iSeed]));
            iProcess = arrProcessSet[iSeed];
        }
    }
}
