package src.activities.Step02;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import src.activities.ActMain;

/**
 * Created by Acka on 2015-04-15.
 */
public class ActStep0204 extends StageActivity {
    private final FrameLayout frameNumber[] = new FrameLayout[4];
    private final ImageView imgNumberField[] = new ImageView[4];
    private final TextView txtNumber[] = new TextView[4];
    private final TextView txtOperator[] = new TextView[2];
    private final Button btnAnswer[] = new Button[3];

    public int iAnswer = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0204DataSet dataSet = new Step0204DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_4);

        frameNumber[0] = (FrameLayout)findViewById(R.id.frame_number_1);
        frameNumber[1] = (FrameLayout)findViewById(R.id.frame_number_2);
        frameNumber[2] = (FrameLayout)findViewById(R.id.frame_number_3);
        frameNumber[3] = (FrameLayout)findViewById(R.id.frame_number_4);
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
        dataSet.setData(iStage);

        for(int i = 0; i < 2; i++){
            int iOperator = dataSet.iOperator[i];
            String sOperator = "";

            if(iOperator == 1) sOperator += '+';
            else if(iOperator == -1) sOperator += '-';

            txtOperator[i].setText(sOperator);
            txtOperator[i].setVisibility(View.VISIBLE);
        }

        int iAnswerSign = 1;
        iAnswer = 0;
        for(int i = 0; i < 4; i++){
            int iNumber = dataSet.iNumberSet[i];

            if(iNumber == -2){
                frameNumber[i].setVisibility(View.GONE);
                txtOperator[i - 1].setVisibility(View.GONE);
                continue;
            }

            frameNumber[i].setVisibility(View.VISIBLE);
            if(iNumber == -1){
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
        Intent intent = new Intent(this, ActStep0205.class);
        startActivity(intent);
    }

    public class Step0204DataSet{
        private final int arrNumberSet[][] = {{4, -1, -2, 7}, {-1, 9, -2, 11}, {5, 2, -1, 10}, {2, -1, 7, 15}, {-1, 5, 7, 16}, {9, 8, -1, 19}, {9, 9, -2, -1}, {8, 5, -2, -1}, {7, -1, -2, 5}, {15, 5, -1, 5},
                {4, -1, -2, 6}, {-1, 8, -2, 11}, {3, 4, -1, 10}, {3, -1, 6, 15}, {-1, 6, 6, 16}, {8, 7, -1, 19}, {8, 8, -2, -1}, {9, -1, -2, 6}, {8, -1, -2, 6}, {12, -1, 2, 5},
                {5, -1, -2, 8}, {-1, 7, -2, 11}, {6, 1, -1, 10}, {4, -1, 5, 10}, {4, -1, 5, 15}, {-1, 8, 4, 16}, {6, 9, -1, 19}, {4, 4, -2, -1}, {7, -1, -2, 4}, {5, -1, -2, 3}, {13, 3, -1, 5}};
        private final int arrOperatorSet[][] = {{1, 0}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, -1},
                {1, 0}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, -1},
                {1, 0}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, -1}};

        public final int iNumberSet[] = new int[4];
        public final int iOperator[] = new int[2];

        public void setData(int iStage){
            int iSeed = (iStage - 1) * 2 + rand.nextInt(2) + 10 * rand.nextInt(3);

            for(int i = 0; i < 4; i++)
                iNumberSet[i] = arrNumberSet[iSeed][i];
            iOperator[0] = arrOperatorSet[iSeed][0];
            iOperator[1] = arrOperatorSet[iSeed][1];
        }
    }
}