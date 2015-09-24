package src.activities.Step08;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.activities.Step04.ActStep0403;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-09-07.
 */
public class ActStep0803 extends StageActivity {

    private int iRetryCount = 0;
    public boolean isRight = false;
    private Button btn1, btn2, btn3;

    private TextView box[] = new TextView[10];

    public Step0803DataSet dataSet = new Step0803DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_08_3);

        btn1 = (Button)findViewById(R.id.btn_answer_1);
        btn2 = (Button)findViewById(R.id.btn_answer_2);
        btn3 = (Button)findViewById(R.id.btn_answer_3);

        box[0] = (TextView)findViewById(R.id.bx1);
        box[1] = (TextView)findViewById(R.id.bx2);
        box[2] = (TextView)findViewById(R.id.bx3);
        box[3] = (TextView)findViewById(R.id.bx4);
        box[4] = (TextView)findViewById(R.id.bx5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnCurrentButton = (Button) v;
                String iSelectAnswer = btnCurrentButton.getText().toString();
                if (dataSet.ans.equals(iSelectAnswer)) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnCurrentButton = (Button) v;
                String iSelectAnswer = btnCurrentButton.getText().toString();
                if (dataSet.ans.equals(iSelectAnswer)) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnCurrentButton = (Button) v;
                String iSelectAnswer = btnCurrentButton.getText().toString();
                if (dataSet.ans.equals(iSelectAnswer)) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object) {
        dataSet.setData(iStage - 1);

        btn1.setText(dataSet.strbtn[0]);
        btn2.setText(dataSet.strbtn[1]);
        btn3.setText(dataSet.strbtn[2]);

        for(int i = 0 ; i < 5 ; i++){
            if(i == dataSet.blank)
                box[i].setVisibility(View.INVISIBLE);
            else
                box[i].setVisibility(View.VISIBLE);

            box[i].setText(dataSet.prob[i]);
        }

        StartRecording();
    }

    public void checkAnswer(Object o) {
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if (isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isRight || iRetryCount > 1) {
                    iStage++;
                    iRetryCount = 0;
                    if (iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                } else {
                    iRetryCount++;
                }
            }
        });
    }

    public void goNext(Object object) {
        Intent intent = new Intent(this, ActStep0804.class);
        startActivity(intent);
    }

    public class Step0803DataSet {

        private  final String problist[][][] = {
                {{"2","4","6","8","10"}, {"3","6","9","12","15"},{"1","3","5","7","9"}},
                {{"1","4","7","10","13"},{"4","9","14","19","24"},{"7","10","13","16","19"}},
                {{"1","4","7","10","13"},{"4","8","12","16","20"},{"6","8","10","12","14"}},
                {{"2","6","10","14","18"},{"2","8","14","20","26"}, {"9","11","13","15","17"}},
                {{"2","9","16","23","30"},{"5","10","15","20","25"}, {"7","11","15","19","23"}}
        };

        private final String arrBtn[][][] ={
                {{"7", "8","9"},{"9","18","12"},{"4","7","10"}},
                {{"8","10","11"},{"17","19","21"},{"8","9","10"}},
                {{"4","9","11"},{"4","6","8"},{"11","12","13"}},
                {{"10","11","12"}, {"14","15","16"}, {"12","13","14"}},
                {{"20","23","26"},{"20","23","26"},{"19","20","21"}}
        };

        private final String ansbtn[][] = {{"8","12","7"},{"10","19","10"},{"4","8","12"},{"10","14","13"},{"23","20","19"}}; // 답 버튼

        private final int blanklist[][]={{3,3,3},{3,3,1},{1,1,3},{2,2,2},{3,3,3}};


        String ans = new String();
        String strbtn[] = new String[10];
        String prob[] = new String[10];
        int blank;

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            ans = ansbtn[iStage][rand];
            strbtn = arrBtn[iStage][rand];
            prob = problist[iStage][rand];
            blank = blanklist[iStage][rand];
        }
    }
}