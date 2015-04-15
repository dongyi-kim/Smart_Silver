package src.activities.Step02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-15.
 */
public class ActStep0202 extends StageActivity{
    private final TextView txtOperandCell[] = new TextView[2];
    private TextView txtOperatorCell;
    private TextView txtAnswerCell;
    private final Button btnAnswer[] = new Button[2];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public char cAnswer = 'O';

    public Step0202DataSet dataSet = new Step0202DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_2);

        txtOperandCell[0] = (TextView)findViewById(R.id.txt_operand_1);
        txtOperandCell[1] = (TextView)findViewById(R.id.txt_operand_2);
        txtOperatorCell = (TextView)findViewById(R.id.txt_operator);
        txtAnswerCell = (TextView)findViewById(R.id.txt_answer);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_isRight);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_isFalse);

        //button listener
        for(int i = 0; i < 2; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Button btnCurrent = (Button)v;
                    if(btnCurrent.getText().charAt(0) == cAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage  * 2 + rand.nextInt(1);
        dataSet.setData(iRandomSeed);

        txtOperandCell[0].setText("" + dataSet.iOperandSet[0]);
        txtOperandCell[1].setText("" + dataSet.iOperandSet[1]);
        txtOperatorCell.setText("" + dataSet.cOperator);
        txtAnswerCell.setText("" + dataSet.iAnswer);

        int iAnswer = dataSet.iOperandSet[0];
        if(dataSet.cOperator == '+') iAnswer += dataSet.iOperandSet[1];
        else iAnswer -= dataSet.iOperandSet[1];

        cAnswer = (iAnswer == dataSet.iAnswer? 'O' : 'X');
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
        Intent intent = new Intent(this, ActStep0203.class);
        startActivity(intent);
    }

    public class Step0202DataSet {
        private final int arrOperandSet[][] = {{5, 3}, {2, 4}, {5, 3}, {8, 2}, {5, 7}, {6, 1}, {7, 4}, {12, 3}, {8, 7}, {14, 7}, {17, 9}};
        private final char arrOperatorSet[] = {'+', '+', '-', '-', '+', '-', '+', '-', '+', '-', '-'};
        private final int arrAnswerSet[] = {8, 7, 3, 6, 13, 4, 11, 7, 12, 6, 7};

        public int iOperandSet[] = new int[2];
        public char cOperator;
        public int iAnswer;

        public void setData(int iSeed) {
            iOperandSet[0] = arrOperandSet[iSeed][0];
            iOperandSet[1] = arrOperandSet[iSeed][1];
            cOperator = arrOperatorSet[iSeed];
            iAnswer = arrAnswerSet[iSeed];
        }
    }
}
