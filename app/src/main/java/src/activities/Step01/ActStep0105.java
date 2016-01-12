package src.activities.Step01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.Step02.*;
import src.activities.FrameActivity;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0105 extends StageActivity {

    private ImageView img;
    private LinearLayout btnset1;
    private LinearLayout btnset2;
    private static int Count = 0;

    private Button btnAnswer[] = new Button[5];
    private Step0105NumberSet dataSet = new Step0105NumberSet();
    private boolean ans;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_step_01_5);

        img = (ImageView)findViewById(R.id.img_1_5);

        btnset1 = (LinearLayout)findViewById(R.id.btnSet1);
        btnset2 = (LinearLayout)findViewById(R.id.btnSet2);

        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_ans_3);
        btnAnswer[3] = (Button)findViewById(R.id.btn_ans_4);
        btnAnswer[4] = (Button)findViewById(R.id.btn_ans_5);

        for(int i = 0; i < 5; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btnCurrentButton = (Button) v;

                    String iSelectAnswer = btnCurrentButton.getText().toString();

                    if (iSelectAnswer.equals(dataSet.strAns)) ans = true;
                    else ans = false;

                    checkAnswer();
                }
            });
        }
        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        int Seed = iStage - 1;

        dataSet.setData(Seed);

        img.setImageResource(dataSet.img);

        if(Seed <= 3)
        {
            btnset1.setVisibility(View.VISIBLE);
            btnset2.setVisibility(View.GONE);
            for(int i = 0 ; i < 2 ; i++)
                btnAnswer[i].setText(dataSet.btnTxt[i]);
        }
        else
        {
            btnset1.setVisibility(View.GONE);
            btnset2.setVisibility(View.VISIBLE);
            for(int i = 0 ; i < 3 ; i++)
                btnAnswer[i+2].setText(dataSet.btnTxt[i]);
        }

        StartRecording();
    }

    @Override
    public synchronized void checkAnswer(Object object){
        DlgResultMark dlg = new DlgResultMark(this, ans);
        dlg.show();

        if(ans || Count > 1) StopRecording(ans);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                if(ans || Count > 1){
                    iStage++;
                    if(iStage > NUM_OF_STAGE) goNext();
                    else {
                        Count = 0;
                        setQuestion(false);
                    }
                }
                else{
                    Count++;
                }
            }
        });
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0201.class);
        startActivity(intent);
    }

    public class Step0105NumberSet {
        public int img;
        public String btnTxt[];
        public String strAns;

        private final int imgList[][] = {
                {R.drawable.clock_1_5_1_0200,R.drawable.clock_1_5_1_0115,R.drawable.clock_1_5_1_0540},
                {R.drawable.clock_1_5_1_0320,R.drawable.clock_1_5_1_0655,R.drawable.clock_1_5_4_0938},
                {R.drawable.clock_1_5_4_1200,R.drawable.clock_1_5_4_0816,R.drawable.clock_1_5_4_0938},
                {R.drawable.clock_1_5_4_0538,R.drawable.clock_1_5_4_0429,R.drawable.clock_1_5_4_0727},
                {R.drawable.clock_1_5_6_0928,R.drawable.clock_1_5_6_1017,R.drawable.clock_1_5_6_0548},
        };

        private final String strAnsList[][] = {
                {"두 시", "한 시 십오 분", "다섯 시 사십 분"},
                {"세 시 이십 분", "여섯 시 오십오 분", "아홉 시 삼십팔 분"},
                {"열두 시", "여덟 시 십육 분", "아홉 시 삼십팔 분"},
                {"다섯 시 삼십팔 분", "네 시 이십구 분", "일곱 시 이십칠 분"},
                {"아홉 시 이십팔 분", "열 시 십칠 분", "다섯 시 사십팔 분"}
        };

        private final String txtBtnList[][][] = {
                {{"두 시 십이 분", "두 시"}, {"한 시", "한 시 십오 분"}, {"다섯 시 이십 분", "다섯 시 사십 분"}},
                {{"세 시", "세 시 이십 분"}, {"일곱 시", "여섯 시 오십오 분"}, {"여덟 시 십 분", "아홉 시 삼십팔 분"}},
                {{"열두 시", "열두 시 십이 분"}, {"여덟 시 십육 분", "열두 시 십육 분"}, {"아홉 시 삼십 분", "아홉 시 삼십팔 분"}},
                {{"다섯 시", "다섯 시 삼십팔 분"}, {"네 시 이십 분", "네 시 이십구 분"}, {"일곱 시", "일곱 시 이십칠 분"}},
                {{"아홉 시", "아홉 시 이십팔 분", "아홉 시 오십육 분"}, {"열 시 십칠 분", "열 시 십오 분", "열 시 십팔 분"}, {"다섯 시 사십 분", "다섯 시 사십이 분", "다섯 시 사십팔 분"}}
        };

        public void setData(int iSeed) {

            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            img = imgList[iSeed][rand];
            btnTxt = txtBtnList[iSeed][rand];
            strAns = strAnsList[iSeed][rand];
        }
    }
}
