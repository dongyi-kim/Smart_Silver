package src.activities.Step02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-23.
 */
public class ActStep0205 extends StageActivity{
    private final ImageView imgNumberField[] = new ImageView[4];
    private final TextView txtNumber[] = new TextView[4];
    private final TextView txtOperator[] = new TextView[2];
    private final Button btnAnswer[] = new Button[3];

    public int iAnswer = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0205DataSet dataSet = new Step0205DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_4);

        imgNumberField[0] = (ImageView)findViewById(R.id.img_operand_field_1);
        imgNumberField[1] = (ImageView)findViewById(R.id.img_operand_field_2);
        imgNumberField[2] = (ImageView)findViewById(R.id.img_operand_field_3);
        imgNumberField[3] = (ImageView)findViewById(R.id.img_answer_field);
        txtNumber[0] = (TextView)findViewById(R.id.txt_operand_1);
        txtNumber[1] = (TextView)findViewById(R.id.txt_operand_2);
        txtNumber[2] = (TextView)findViewById(R.id.txt_operand_3);
        txtNumber[3] = (TextView)findViewById(R.id.txt_answer);
        txtOperator[0] = (TextView)findViewById(R.id.txt_operator_1);
        txtOperator[1] = (TextView)findViewById(R.id.txt_operator_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(Integer.parseInt(((Button) v).getText().toString()) == iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        for(int i = 0; i < 2; i++){
            int iOperator = dataSet.iOperator[i];
            String sOperator = "";

            if(iOperator == 1) sOperator += '+';
            else if(iOperator == -1) sOperator += '-';
            txtOperator[i].setText(sOperator);
        }

        int iAnswerSign = 1;
        iAnswer = 0;
        for(int i = 0; i < 4; i++){
            int iNumber = dataSet.iNumberSet[i];

            if(iNumber == -2) continue;
            else if(iNumber == -1){
                txtNumber[i].setText("");
                if(i > 0 && i < 3) iAnswerSign = dataSet.iOperator[i - 1];
            }
            else{
                txtNumber[i].setText("" + iNumber);

                if(i >= 3) iNumber *= -1;
                else if(i >= 1) iNumber *= dataSet.iOperator[i - 1];
                iAnswer += iNumber * -1;
            }
            Log.i("tat", "" + iAnswer);
        }
        iAnswer *= iAnswerSign;

        int iNextExample = iAnswer - rand.nextInt(2);
        if(iNextExample < 0) iNextExample = 0;

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));
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

    public class Step0205DataSet{
        private final int arrNumberSet[][] = {{5, 4, -1, 7}, {6, 7, -1, 10},
                {9, -1, 2, 11}, {4, -1, 1, 13}, {-1, 8, 3, 10}};
        private final int arrOperatorSet[][] = {{1, -1}, {1, -1}, {1, -1}, {1, -1}, {1, -1}};

        public final int iNumberSet[] = new int[4];
        public final int iOperator[] = new int[2];

        public void setData(int iSeed){
            for(int i = 0; i < 4; i++)
                iNumberSet[i] = arrNumberSet[iSeed][i];
            iOperator[0] = arrOperatorSet[iSeed][0];
            iOperator[1] = arrOperatorSet[iSeed][1];
        }
    }
}
