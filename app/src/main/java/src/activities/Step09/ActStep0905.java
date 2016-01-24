package src.activities.Step09;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.ActStartLearning;
import src.activities.StageActivity;
import src.activities.Step10.ActStep10;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-10-30.
 */
public class ActStep0905 extends StageActivity {

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;

    public Step0905DataSet dataSet = new Step0905DataSet();

    private Button btnAnswer[] = new Button[3];
    private ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_09_5);

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        img = (ImageView)findViewById(R.id.img);

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((Button) v).getText().equals(dataSet.ans)) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage - 1);
        for(int i = 0 ; i < 3 ; i++)
            btnAnswer[i].setText(dataSet.btn[i]);
        img.setImageResource(dataSet.img);

        StartRecording();
    }

    public synchronized void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if(isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isRight || iRetryCount > 1) {
                    iRetryCount = 0;
                    iStage++;
                    if (iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                } else {
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStartLearning.class);
        startActivity(intent);
    }

    public class Step0905DataSet{

        private final int imgset[][] = {
                {R.drawable.map_9_5_9,R.drawable.map_9_5_2,R.drawable.map_9_5_1},
                {R.drawable.map_9_5_1,R.drawable.map_9_5_4,R.drawable.map_9_5_3}, // 9_5_4 수정해야됨 그림
                {R.drawable.map_9_5_10,R.drawable.map_9_5_5,R.drawable.map_9_5_6},
                {R.drawable.map_9_5_11,R.drawable.map_9_5_7,R.drawable.map_9_5_8}
        };

        private final String btnset[][][] = {
                {{"1가지", "2가지", "3가지"},{"1가지", "2가지", "3가지"},{"1가지", "2가지", "3가지"}},
                {{"1가지", "2가지", "3가지"},{"2가지", "3가지", "4가지"},{"1가지", "2가지", "3가지"}},
                {{"2가지", "3가지", "4가지"},{"2가지", "3가지", "4가지"},{"2가지", "4가지", "6가지"}},
                {{"1가지", "2가지", "3가지"},{"2가지", "4가지", "6가지"},{"3가지", "6가지", "7가지"}},
        };

        private final String ansset[][] ={
                {"2가지","3가지","2가지",},
                {"2가지","3가지","3가지"},
                {"3가지","4가지","6가지"},
                {"3가지","6가지","7가지"},
        };

        int img;
        String btn[] = new String[3], ans = new String();

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2
            img = imgset[iStage][rand];
            btn = btnset[iStage][rand];
            ans = ansset[iStage][rand];
        }
    }
}