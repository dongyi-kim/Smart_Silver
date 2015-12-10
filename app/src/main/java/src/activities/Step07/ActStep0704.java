package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

public class ActStep0704 extends StageActivity {

    private Button btnAnswerButton[] = new Button[10];
    private TextView txtDescription;
    private LinearLayout ll_1, ll_2;

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0704DataSet dataSet = new Step0704DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_4);

        txtDescription = (TextView)findViewById(R.id.txt_description);

        ll_1 = (LinearLayout)findViewById(R.id.page_7_4_1);
        ll_2 = (LinearLayout)findViewById(R.id.page_7_4_2);

        btnAnswerButton[0] = (Button)findViewById(R.id.btn_1_1);
        btnAnswerButton[1] = (Button)findViewById(R.id.btn_1_2);
        btnAnswerButton[2] = (Button)findViewById(R.id.btn_1_3);
        btnAnswerButton[3] = (Button)findViewById(R.id.btn_1_4);
        btnAnswerButton[4] = (Button)findViewById(R.id.btn_1_5);
        btnAnswerButton[5] = (Button)findViewById(R.id.btn_2_1);
        btnAnswerButton[6] = (Button)findViewById(R.id.btn_2_2);
        btnAnswerButton[7] = (Button)findViewById(R.id.btn_2_3);
        btnAnswerButton[8] = (Button)findViewById(R.id.btn_2_4);
        btnAnswerButton[9] = (Button)findViewById(R.id.btn_2_5);

        for(int i = 0; i < 10; i++){
            btnAnswerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Button curBtn = (Button) v;

                    for(int j = 0; j < 10; j++)
                        if (curBtn == btnAnswerButton[j]) {
                            if(dataSet.ans == j) isRight = true;
                            else isRight = false;
                            break;
                        }
                    checkAnswer();
                }
            });
        }
        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;

        dataSet.setData(iRandomSeed);

        // layout setting

        ll_1.setVisibility(View.GONE);
        ll_2.setVisibility(View.GONE);

        if(iStage <= 3) ll_1.setVisibility(View.VISIBLE);
        else ll_2.setVisibility(View.VISIBLE);

        // btn setting

        for(int i = 0 ; i < 10 ; i++) btnAnswerButton[i].setVisibility(View.INVISIBLE);
        for(int i = 0 ; i < 5 ; i++){
            if(dataSet.atvBtn[i] < 0) continue;
            else btnAnswerButton[dataSet.atvBtn[i]].setVisibility(View.VISIBLE);
        }

        //des set

        txtDescription.setText(dataSet.des);

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
        Intent intent = new Intent(this, ActStep0705.class);
        startActivity(intent);
    }

    public class Step0704DataSet {

        private final String arrDescription[][] ={{"전기요금 납기일을 나타내는 곳을 찾아 누르세요.", "전기요금 납기일을 나타내는 곳을 찾아 누르세요.","전기요금 납기일을 나타내는 곳을 찾아 누르세요."},
                {"전기요금 청구금액을 나타내는 곳을 찾아 누르세요.", "전기요금 청구금액을 나타내는 곳을 찾아 누르세요.","전기요금 청구금액을 나타내는 곳을 찾아 누르세요."},
                {"아래 자료에서 당월 전기사용량을 나타내는 곳을 찾아 누르세요.", "아래 자료에서 당월 전기사용량을 나타내는 곳을 찾아 누르세요.","아래 자료에서 당월 전기사용량을 나타내는 곳을 찾아 누르세요."},
                {"아래 영수증은 평생마트 영수증입니다. 모듬 버섯 불고기는 얼마인지 찾아 누르세요.", "아래 영수증은 평생마트 영수증입니다. 지불할 금액은 모두 얼마인지 찾아 누르세요.","아래 자료는 평생마트 영수증입니다. 상추는 얼마인지 찾아 누르세요."},
                {"아래 영수증은 평생마트 영수증입니다. 평생마트에서 물건을 산 날짜는 언제인지 찾아 누르세요.", "아래 영수증은 평생마트 영수증입니다. 평생마트에서 물건을 산 날짜는 언제인지 찾아 누르세요.","아래 자료는 평생마트 영수증입니다. 오렌지 주스를 몇 개 샀는지 수량을 찾아 누르세요."}
        };

        private final int arrActiveBtn[][][]={
                {{0,1,-1,-1,-1},{0,1,2,3,-1}, {0,1,2,3,4}},
                {{0,1,4,-1,-1}, {0,1,2,3,4},  {0,1,4,-1,-1}},
                {{0,1,2,3,4},   {0,1,2,3,4},  {0,1,2,3,4}},
                {{6,7,-1,-1,-1},{5,6,7,8,-1}, {5,6,7,8,-1}},
                {{5,6,7,8,-1},  {5,6,7,8,-1}, {5,6,9,8,-1}}
        };

        private final int arrAnsBtn[][]={
                {0,0,0},
                {4,4,4},
                {2,2,2},
                {6,8,7},
                {5,5,9},
        };

        public int atvBtn[] = new int[5];
        public String des = new String();
        public int ans;

        public void setData(int iSeed){
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            ans = arrAnsBtn[iSeed][rand];
            atvBtn = arrActiveBtn[iSeed][rand];
            des = arrDescription[iSeed][rand];
        }
    }
}
