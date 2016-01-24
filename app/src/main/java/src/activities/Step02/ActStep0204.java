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

    private int iRetryCount = 0;
    private boolean isRight = false;

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
                    if(Integer.parseInt(((Button) v).getText().toString()) == dataSet.iNumberSet[dataSet.iEmptyIndex]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        for(int i = 0; i < 2; i++){
            if(dataSet.iOperator[i] == 0){
                txtOperator[i].setVisibility(View.INVISIBLE);
                frameNumber[i + 1].setVisibility(View.GONE);
            }
            else {
                txtOperator[i].setVisibility(View.VISIBLE);
                frameNumber[i + 1].setVisibility(View.VISIBLE);
                txtOperator[i].setText((dataSet.iOperator[i] == 1 ? "+" : "―"));
            }
        }

        for(int i = 0; i < 4; i++){
            if(i == dataSet.iEmptyIndex){
                imgNumberField[i].setVisibility(View.VISIBLE);
                txtNumber[i].setText("");
            }
            else{
                imgNumberField[i].setVisibility(View.INVISIBLE);
                txtNumber[i].setText("" + dataSet.iNumberSet[i]);
            }
        }

        int iNextExample = dataSet.iNumberSet[dataSet.iEmptyIndex] - rand.nextInt(2);
        if(iNextExample < 0) iNextExample = 0;
        if(iNextExample > 7) iNextExample = 7;

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));

        StartRecording();
    }

    public synchronized void checkAnswer(Object o){
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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0205.class);
        startActivity(intent);
    }

    public class Step0204DataSet{
        private final int arrOperator[][] = {{1, 1}, {1, 1, 1}, {1, 1, 1}, {1, -1}, {1, -1, -1}};
        public int iNumberSet[] = new int[4];
        public int iOperator[] = new int[2];
        public int iEmptyIndex;

        public void setData(int iStage){
            int iSeed = iStage - 1;

            for(int i = 1; i < arrOperator[iSeed].length; i++)
                iOperator[i - 1] = arrOperator[iSeed][i];
            if(arrOperator[iSeed].length < 3) iOperator[1] = 0;

            do{
                iEmptyIndex = rand.nextInt(4);
            }while(iEmptyIndex == 2 && arrOperator[iSeed].length <= 2);

            do{
                iNumberSet[3] = 0;
                for(int i = 0; i < arrOperator[iSeed].length; i++){
                    iNumberSet[i] = 1 + rand.nextInt(9);
                    iNumberSet[3] += arrOperator[iSeed][i] * iNumberSet[i];
                }
            }while(iNumberSet[3] < 0 || (iEmptyIndex == 3 && iNumberSet[3] >= 10));
        }
    }
}