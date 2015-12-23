package src.activities.Step09;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-10-26.
 */
public class ActStep0902 extends StageActivity {
    private final LinearLayout layout[] = new LinearLayout[2];
    private final LinearLayout rices[] = new LinearLayout[2];
    private final LinearLayout soups[] = new LinearLayout[10];
    public boolean isRight = false;
    public int iAnswer = 0;
    public Step0902DataSet dataSet = new Step0902DataSet();
    private int iRetryCount = 0;
    private Button btnAnswer[] = new Button[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_09_2);

        layout[0] = (LinearLayout)findViewById(R.id.layout1);
        layout[1] = (LinearLayout)findViewById(R.id.layout2);

        rices[0] = (LinearLayout)findViewById(R.id.rice1);
        rices[1] = (LinearLayout)findViewById(R.id.rice2);

        soups[0] = (LinearLayout)findViewById(R.id.soup1);
        soups[1] = (LinearLayout)findViewById(R.id.soup2);
        soups[2] = (LinearLayout)findViewById(R.id.soup3);
        soups[3] = (LinearLayout)findViewById(R.id.soup4);
        soups[4] = (LinearLayout)findViewById(R.id.soup5);
        soups[5] = (LinearLayout)findViewById(R.id.soup6);
        soups[6] = (LinearLayout)findViewById(R.id.soup7);
        soups[7] = (LinearLayout)findViewById(R.id.soup8);
        soups[8] = (LinearLayout)findViewById(R.id.soup9);

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

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

    synchronized public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage - 1);

        for(int i = 0 ; i < 9 ; i++) soups[i].setVisibility(View.GONE);
        for(int i = 0 ; i < 2 ; i ++) rices[i].setVisibility(View.GONE);

        for(int i = 0 ; i < 2; i++){
            if(dataSet.rice[i] == 2) continue;
            else rices[i].setVisibility(View.VISIBLE);
        }

        for(int i = 0 ; i < 4; i++){
            if(dataSet.soup[i] == 9) continue;
            else soups[dataSet.soup[i]].setVisibility(View.VISIBLE);
        }

        for(int i = 0 ; i < 3 ; i++)
            btnAnswer[i].setText(dataSet.btn[i]);

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
                    iRetryCount = 0;
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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0903.class);
        startActivity(intent);
    }

    public class Step0902DataSet{

        private final int rices[][][] = {
                {{1,2}, {1,2}, {1,2}},
                {{1,2}, {1,2}, {0,2}},
                {{0,1}, {0,1}, {0,1}},
                {{2,2}, {2,2}, {0,1}},
        };

        private final int soups[][][] = {
                {{1,4,9,9},{1,9,9,9},{0,1,3,9}},
                {{0,1,3,9},{2,3,9,9},{0,1,3,4}},
                {{2,3,9,9},{0,4,9,9},{1,2,3,9}},
                {{5,7,9,9},{6,8,9,9},{0,2,3,4}}
        };

        private final String btns[][][] = {
                {{"2가지", "3가지", "4가지"}, {"1가지", "2가지", "3가지"}, {"1가지", "2가지", "3가지"}},
                {{"2가지", "3가지", "4가지"}, {"1가지", "2가지", "3가지"}, {"2가지", "4가지", "6가지"}},
                {{"3가지", "4가지", "5가지"}, {"3가지", "4가지", "5가지"}, {"2가지", "4가지", "6가지"}},
                {{"3가지", "6가지", "9가지"}, {"2가지", "6가지", "9가지"}, {"2가지", "6가지", "8가지"}},
        };

        private final String anss[][] ={
                {"2가지", "1가지", "3가지"},
                {"3가지", "2가지", "4가지"},
                {"4가지", "4가지", "6가지"},
                {"6가지", "6가지", "8가지"}
        };

        int rice[] = new int[5], soup[] = new int[10];
        String ans = new String();
        String btn[] = new String[3];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            if(iStage < 3 || (iStage == 3 && rand == 2)) { // 2 frames
                layout[0].setVisibility(View.VISIBLE);
                layout[1].setVisibility(View.GONE);
                soup = soups[iStage][rand];
                rice = rices[iStage][rand];
            } else {  // frames
                soup = soups[iStage][rand];
                layout[0].setVisibility(View.GONE);
                layout[1].setVisibility(View.VISIBLE);
            }
            btn = btns[iStage][rand];
            ans = anss[iStage][rand];
        }
    }
}