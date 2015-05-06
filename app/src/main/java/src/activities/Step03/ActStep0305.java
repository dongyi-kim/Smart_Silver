package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-13.
 */
public class ActStep0305 extends StageActivity{
    private TextView txtDiscription;
    private LinearLayout arrLinearSet[] = new LinearLayout[5];
    private FrameLayout arrFrameSet[] = new FrameLayout[5];
    private ImageView arrImgSet[] = new ImageView[5];
    private TextView arrTxtSet[] = new TextView[5];
    private ImageView arrImgTxtSpace[] = new ImageView[5];
    private ImageView arrImgSign[] = new ImageView[2];
    private TextView arrMarginTxt[] = new TextView[4];
    private Button arrBtnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0305DataSet dataSet = new Step0305DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_5);

        txtDiscription = (TextView)findViewById(R.id.txt_discription);
        arrLinearSet[0] = (LinearLayout)findViewById(R.id.linear_set_1);
        arrLinearSet[1] = (LinearLayout)findViewById(R.id.linear_set_2);
        arrLinearSet[2] = (LinearLayout)findViewById(R.id.linear_set_3);
        arrLinearSet[3] = (LinearLayout)findViewById(R.id.linear_set_4);
        arrLinearSet[4] = (LinearLayout)findViewById(R.id.linear_set_5_big);
        arrFrameSet[0] = (FrameLayout)findViewById(R.id.frame_set_1);
        arrFrameSet[1] = (FrameLayout)findViewById(R.id.frame_set_2);
        arrFrameSet[2] = (FrameLayout)findViewById(R.id.frame_set_3);
        arrFrameSet[3] = (FrameLayout)findViewById(R.id.frame_set_4);
        arrFrameSet[4] = (FrameLayout)findViewById(R.id.frame_set_5_big);
        arrImgSet[0] = (ImageView)findViewById(R.id.img_set_1);
        arrImgSet[1] = (ImageView)findViewById(R.id.img_set_2);
        arrImgSet[2] = (ImageView)findViewById(R.id.img_set_3);
        arrImgSet[3] = (ImageView)findViewById(R.id.img_set_4);
        arrImgSet[4] = (ImageView)findViewById(R.id.img_set_5_big);
        arrTxtSet[0] = (TextView)findViewById(R.id.txt_set_1);
        arrTxtSet[1] = (TextView)findViewById(R.id.txt_set_2);
        arrTxtSet[2] = (TextView)findViewById(R.id.txt_set_3);
        arrTxtSet[3] = (TextView)findViewById(R.id.txt_set_4);
        arrTxtSet[4] = (TextView)findViewById(R.id.txt_set_5_long);
        arrImgTxtSpace[0] = (ImageView)findViewById(R.id.img_txt_space_1);
        arrImgTxtSpace[1] = (ImageView)findViewById(R.id.img_txt_space_2);
        arrImgTxtSpace[2] = (ImageView)findViewById(R.id.img_txt_space_3);
        arrImgTxtSpace[3] = (ImageView)findViewById(R.id.img_txt_space_4);
        arrImgTxtSpace[4] = (ImageView)findViewById(R.id.img_txt_space_5);
        arrImgSign[0] = (ImageView)findViewById(R.id.img_sign_1);
        arrImgSign[1] = (ImageView)findViewById(R.id.img_sign_2);
        arrMarginTxt[0] = (TextView)findViewById(R.id.txt_for_margin_1);
        arrMarginTxt[1] = (TextView)findViewById(R.id.txt_for_margin_2);
        arrMarginTxt[2] = (TextView)findViewById(R.id.txt_for_margin_3);
        arrMarginTxt[3] = (TextView)findViewById(R.id.txt_for_margin_4);
        arrBtnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        arrBtnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        arrBtnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        //button listener
        for(int i = 0; i < 3; i++){
            arrBtnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    String sSelectAnswer = ((Button) v).getText().toString();
                    int iSelectAnswer = 0, iLength = sSelectAnswer.length();

                    for(int i = 0; i < iLength; i++){
                        char cReadX = sSelectAnswer.charAt(i);
                        if(cReadX == ',') continue;
                        else if(cReadX < '0' || cReadX > '9') break;

                        iSelectAnswer *= 10;
                        iSelectAnswer += cReadX - '0';
                    }

                    if(iSelectAnswer == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage;
        dataSet.setData(iRandomSeed);

        //set problem
        txtDiscription.setText(dataSet.sDiscription);

        for(int i = 0; i < 2; i++){
            if(dataSet.iSignImage[i] == 0){
                arrImgSign[i].setVisibility(View.GONE);
                arrMarginTxt[i * 2].setVisibility(View.GONE);
                arrMarginTxt[i * 2 + 1].setVisibility(View.GONE);
            }
            else{
                arrImgSign[i].setVisibility(View.VISIBLE);
                arrMarginTxt[i * 2].setVisibility(View.VISIBLE);
                arrMarginTxt[i * 2 + 1].setVisibility(View.VISIBLE);
                arrImgSign[i].setImageResource(dataSet.iSignImage[i]);
            }
        }

        for(int i = 0; i < 3; i++){
            arrBtnAnswer[i].setText(dataSet.sExample[i]);

            int j = 0, iLayoutCount = dataSet.iLayoutCount[i];
            for(; j < iLayoutCount; j++){
                arrLinearSet[i * 2 + j].setVisibility(View.VISIBLE);
                arrImgSet[i * 2 + j].setImageResource(dataSet.iImageSet[i][j]);
                arrTxtSet[i * 2 + j].setText(dataSet.sTextSet[i][j]);
                arrImgTxtSpace[i * 2 + j].setImageResource(dataSet.iSpaceImage[i][j]);
            }
            for(; j < (i == 2? 1: 2); j++ ){
                arrLinearSet[i * 2 + j].setVisibility(View.GONE);
            }
        }

        StartRecording();
    }


    public void checkAnswer(Object object){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if(isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iStage++;
                    if(iStage >= NUM_OF_STAGE) goNext();
                    else {
                        iRetryCount = 0;
                        setQuestion(false);
                    }
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

    public class Step0305DataSet {
        private final String arrDiscriptionList[] = {" 할머니와 할아버지가 마당놀이를 보러 가서 입장료로 50,000원을 냈습니다. 마당놀이 1인 입장료가 15,000원 일 때, 두 분이 받을 거스름돈은 얼마입니까? 아래 단추를 누르세요.",
                " 할아버지가 할머니에게 동전지갑을 선물하려고 합니다. 3000원짜리 동전지갑에 10000원을 냈습니다. 할아버지가 받아야 할 거스름돈은 얼마입니까? 아래 단추를 누르세요.",
                " 할머니는 할아버지와 함께 순대국을 먹고 15,000원을 냈습니다. 순대국이 6,000원 일 때, 받아야 할 거스름돈은 얼마입니까? 아래 단추를 누르세요.",
                " 할머니와 할아버지가 마당놀이를 보러 가서 입장료로 50,000원을 냈습니다. 마당놀이 1인 입장료가 15,000원 일 때, 두 분이 받을 거스름돈은 얼마입니까? 아래 단추를 누르세요.",
                " 할아버지는 할머니와 같이 마당놀이를 함께 보고, 순대국을 먹고, 할머니에게 동전지갑을 선물했습니다. 오늘 비용은 모두 얼마가 필요합니까? 아래 단추를 누르세요."};
        private final int arrLayoutCountList[][] = {{1, 2, 0}, {1, 1, 0}, {1, 2, 0}, {1, 2, 0}, {2, 2, 1}};
        private final int arrSignSetList[][] = {{0, 2}, {0, 2}, {0, 2}, {0, 2}, {1, 1}};
        private final int arrSignImage[] = {R.drawable.sign_minus, R.drawable.sign_plus, 0};
        private final int arrImageSetList[][][] = {{{R.drawable.img_korean_dollar_50000, 0}, {R.drawable.img_grandma_only, R.drawable.img_grandfa_only}, {0}},
            {{R.drawable.img_korean_dollar_10000, 0}, {R.drawable.img_coin_wallet, 0}, {0}},
            {{R.drawable.img_korean_dollar_15000, 0}, {R.drawable.img_grandma_only, R.drawable.img_grandfa_only}, {0}},
            {{R.drawable.img_korean_dollar_50000, 0}, {R.drawable.img_grandma_only, R.drawable.img_grandfa_only}, {0}},
            {{R.drawable.img_grandma_only, R.drawable.img_grandfa_only}, {R.drawable.img_korean_food, R.drawable.img_korean_food}, {R.drawable.img_coin_wallet}}};
        private final int arrSpaceImageList[] = {R.drawable.empty_space_the_number_food, R.drawable.empty_space_how_much, R.drawable.empty_space_how_much_long};
        private final int arrSpaceIndexList[][][] = {{{0, 0}, {1, 1}, {0}},
                {{0, 0}, {2, 0}, {0}},
                {{0, 0}, {1, 1}, {0}},
                {{0, 0}, {1, 1}, {0}},
                {{1, 1}, {1, 1}, {2}}};
        private final String arrTextSetList[][][] = {{{"낸 돈 50,000원", ""}, {"입장권 15,000원", "입장권 15,000원"}, {""}},
                {{"낸 돈 10,000원", ""}, {"동전지갑 3,000원", ""}, {""}},
                {{"낸 돈 15,000원", ""}, {"순대국 6,000원", "순대국 6,000원"}, {""}},
                {{"낸 돈 50,000원", ""}, {"입장권 15,000원", "입장권 15,000원"}, {""}},
                {{"입장권 15,000원", "입장권 15,000원"}, {"순대국 6,000원", "순대국 6,000원"}, {"동전지갑 3,000원"}}};
        private final int arrAnswerList[] = {20000, 7000, 3000, 20000, 45000};
        private final String arrExampleSetList[][] = {{"15,000 원", "20,000 원", "30,000 원"},
                {"2,000 원", "5,000 원", "7,000 원"},
                {"1,500 원", "3,000 원", "5,000 원"},
                {"15,000 원", "20,000 원", "30,000 원"},
                {"35,000 원", "45,000 원", "50,000 원"},};

        public String sDiscription;
        public int iLayoutCount[] = new int[3];
        public int iSignImage[] = new int[2];
        public int iImageSet[][] = new int[3][2];
        public int iSpaceImage[][] = new int[3][2];
        public String sTextSet[][] = new String[3][2];
        public int iAnswer;
        public String sExample[] = new String[3];

        public void setData(int iSeed){
            sDiscription = arrDiscriptionList[iSeed];
            iAnswer = arrAnswerList[iSeed];

            iSignImage[0] = arrSignImage[arrSignSetList[iSeed][0]];
            iSignImage[1] = arrSignImage[arrSignSetList[iSeed][1]];

            for(int i = 0; i < 3; i++) {
                iLayoutCount[i] = arrLayoutCountList[iSeed][i];
                sExample[i] = arrExampleSetList[iSeed][i];

                for(int j = 0; j < iLayoutCount[i]; j++){
                    iImageSet[i][j] = arrImageSetList[iSeed][i][j];
                    iSpaceImage[i][j] = arrSpaceImageList[arrSpaceIndexList[iSeed][i][j]];
                    sTextSet[i][j] = arrTextSetList[iSeed][i][j];
                }
            }
        }
    }

}
