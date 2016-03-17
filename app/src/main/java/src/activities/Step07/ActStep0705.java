package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.activities.Step06.Step0601Activity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-08-06.
 */

public class ActStep0705 extends StageActivity {

    private TextView txtDescription;
    public final Button btnAnswerButton[] = new Button[27];

    private LinearLayout img1, img2;
    private LinearLayout layout1, layout2;
    private static int iBtn = 26;

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0705DataSet dataSet = new Step0705DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_5);

        txtDescription = (TextView)findViewById(R.id.txt_description);

        layout1 = (LinearLayout)findViewById(R.id.layout_7_5_1);
        layout2 = (LinearLayout)findViewById(R.id.layout_7_5_2);

        img1 = (LinearLayout)findViewById(R.id.img_7_5_1);
        img2 = (LinearLayout)findViewById(R.id.img_7_5_2);

        btnAnswerButton[0] = (Button)findViewById(R.id.btn_ans_1_1);
        btnAnswerButton[1] = (Button)findViewById(R.id.btn_ans_1_2);
        btnAnswerButton[2] = (Button)findViewById(R.id.btn_ans_1_3);
        btnAnswerButton[3] = (Button)findViewById(R.id.btn_ans_1_4);
        btnAnswerButton[4] = (Button)findViewById(R.id.btn_ans_1_5);
        btnAnswerButton[5] = (Button)findViewById(R.id.btn_ans_1_6);
        btnAnswerButton[6] = (Button)findViewById(R.id.btn_ans_1_7);
        btnAnswerButton[7] = (Button)findViewById(R.id.btn_ans_1_8);
        btnAnswerButton[8] = (Button)findViewById(R.id.btn_ans_1_9);
        btnAnswerButton[9] = (Button)findViewById(R.id.btn_ans_1_10);
        btnAnswerButton[10] = (Button)findViewById(R.id.btn_ans_1_11);
        btnAnswerButton[11] = (Button)findViewById(R.id.btn_ans_1_12);
        btnAnswerButton[12] = (Button)findViewById(R.id.btn_ans_1_13);
        btnAnswerButton[13] = (Button)findViewById(R.id.btn_ans_1_14);
        btnAnswerButton[14] = (Button)findViewById(R.id.btn_ans_1_15);
        btnAnswerButton[15] = (Button)findViewById(R.id.btn_ans_1_16);
        btnAnswerButton[16] = (Button)findViewById(R.id.btn_ans_1_17);
        btnAnswerButton[17] = (Button)findViewById(R.id.btn_ans_1_18);
        btnAnswerButton[18] = (Button)findViewById(R.id.btn_ans_2_1);
        btnAnswerButton[19] = (Button)findViewById(R.id.btn_ans_2_2);
        btnAnswerButton[20] = (Button)findViewById(R.id.btn_ans_2_3);
        btnAnswerButton[21] = (Button)findViewById(R.id.btn_ans_2_4);
        btnAnswerButton[22] = (Button)findViewById(R.id.btn_ans_2_5);
        btnAnswerButton[23] = (Button)findViewById(R.id.btn_ans_2_6);
        btnAnswerButton[24] = (Button)findViewById(R.id.btn_ans_2_7);
        btnAnswerButton[25] = (Button)findViewById(R.id.btn_ans_2_8);
        btnAnswerButton[26] = (Button)findViewById(R.id.btn_ans_1_19);

        //button listener
        for(int i = 0; i <= iBtn; i++){
            btnAnswerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Button curBtn = (Button) v;

                    for(int j = 0; j <= iBtn; j++) {
                        if (curBtn == btnAnswerButton[j]) {
                            if(dataSet.iAnswerIndex == j) isRight = true;
                            else isRight = false;
                            break;
                        }
                    }
                    checkAnswer();
                }
            });
        }
        setQuestion(false);
    }

    public synchronized void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        countUpTry();
        if(isRight || iRetryCount > 1) stopRecording(isRight);

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

    public synchronized void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;

        dataSet.setData(iRandomSeed);

        if(iStage <= 3){
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            img1.setBackgroundResource(dataSet.img);
        }

        else{
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            img2.setBackgroundResource(dataSet.img);
        }

        for(int i = 0 ; i < 26 ; i++)
            btnAnswerButton[i].setVisibility(View.INVISIBLE);

        txtDescription.setText(dataSet.sDescription);

        for(int i = 0 ; i < 3 ; i++)
            btnAnswerButton[dataSet.arrBtn[i]].setVisibility(View.VISIBLE);

        startRecording();
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, Step0601Activity.class);
        startActivity(intent);
    }

    public class Step0705DataSet {
        public String sDescription;
        public int img;
        public int iAnswerIndex;
        public int arrBtn[];

        private final int imgList[][] = {
                {R.drawable.receipt_7_5_1_1, R.drawable.receipt_7_5_1_2, R.drawable.receipt_7_5_1_3, },
                {R.drawable.receipt_7_5_2_1, R.drawable.receipt_7_5_2_2, R.drawable.receipt_7_5_2_3, },
                {R.drawable.receipt_7_5_3_1, R.drawable.receipt_7_5_3_2, R.drawable.receipt_7_5_3_3, },
                {R.drawable.receipt_7_5_4_1, R.drawable.receipt_7_5_4_2, R.drawable.receipt_7_5_4_3, },
                {R.drawable.receipt_7_5_5_1, R.drawable.receipt_7_5_5_2, R.drawable.receipt_7_5_5_3, },
        };

        private final String arrDescription[][] = {
                {"아래 영수증은 김 할머니의 진료비 계산서입니다.\n수납금액은 얼마인지 찾아 누르세요.", "아래 영수증은 김 할머니의 진료비 계산서입니다.\n주사금액은 얼마인지 찾아 누르세요.", "아래 영수증은 김 할머니의 진료비 계산서입니다.\n진찰료는 얼마인지 찾아 누르세요."},
                {"아래 영수증은 김 할머니의 진료비 계산서입니다.\n식대는 얼마인지 찾아 누르세요.", "아래 영수증은 김 할머니의 진료비 계산서입니다.\n검사료는 얼마인지 찾아 누르세요.", "아래 영수증은 김 할머니의 진료비 계산서입니다.\n입원비는 얼마인지 찾아 누르세요.",},
                {"아래 영수증은 김 할머니의 진료비 계산서입니다.\n선택진료료는 얼마인지 찾아 누르세요.", "아래 영수증은 김 할머니의 진료비 계산서입니다.\n투약 및 조제료(약값)는 얼마인지 찾아 누르세요.", "아래 영수증은 김 할머니의 진료비 계산서입니다.\n영상진단 및 방사선치료비는 얼마인지 찾아 누르세요.",},
                {"다음은 김영자씨의 건강보험료 고지서입니다.\n이번 달 보험료는 얼마인지 누르세요.", "다음은 김영자씨의 건강보험료 고지서입니다.\n이번 달 보험료는 언제 사용한 것인지 찾아 누르세요.", "다음은 김영자씨의 건강보험료 고지서입니다.\n이번 달 보험료의 ‘납부자 번호’를 찾아 누르세요.",},
                {"다음은 김영자씨의 건강보험료 고지서입니다.\n이번 달 보험료를 언제까지 납부해야 하는지 찾아 누르세요.", "다음은 김영자씨의 건강보험료 고지서입니다.\n이번 달 납기 후 금액을 찾아 누르세요.", "아래 고지서는 무엇에 관한 고지서인지 찾아 누르세요."}
        };

        private final int ansArray[][]={
                {26, 4, 0},
                {2, 7, 1},
                {17, 3, 8},
                {21, 19, 20},
                {22, 25, 18}
        };

        private final int btnArray[][][]={ // 18
                {{2,12,26},{2,4,17},{0,4,17}},
                {{2,12,17},{2,7,12},{1,7,12}},
                {{2,12,17},{2,3,12},{2,8,12}},
                {{21,22,23},{19,22,24},{19,20,22}},
                {{21,22,23},{22,23,25},{18,21,22}}
        };

        public void setData(int iSeed) {
            int seed = iSeed;

            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            img = imgList[seed][rand];
            sDescription = arrDescription[seed][rand];
            iAnswerIndex = ansArray[seed][rand];
            arrBtn = btnArray[seed][rand];
        }
    }
}