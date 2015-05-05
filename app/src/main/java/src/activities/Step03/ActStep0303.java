package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-29.
 */
public class ActStep0303 extends StageActivity{
    private TextView txtDiscription;
    private ImageView imgGrandma;
    private ImageView imgGrandfa;
    private ImageView imgGrandmaFoodSpace;
    private ImageView imgGrandfaFoodSpace;
    private TextView txtGrandmaFood;
    private TextView txtGrandfaFood;
    private Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0303DataSet dataSet = new Step0303DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_3);

        txtDiscription = (TextView)findViewById(R.id.text_discription);
        imgGrandma = (ImageView)findViewById(R.id.imgGrandma);
        imgGrandfa = (ImageView)findViewById(R.id.imgGrandfa);
        imgGrandmaFoodSpace = (ImageView)findViewById(R.id.img_grandma_food_space);
        imgGrandfaFoodSpace = (ImageView)findViewById(R.id.img_grandfa_food_space);
        txtGrandmaFood = (TextView)findViewById(R.id.txt_grandma_food);
        txtGrandfaFood = (TextView)findViewById(R.id.txt_grandfa_food);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Button btnCurrentButton = (Button)v;
                    int iSelectAnswer = Integer.parseInt(btnCurrentButton.getText().toString());
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
        imgGrandma.setImageResource(dataSet.iGrandmaImage);
        imgGrandfa.setImageResource(dataSet.iGrandfaImage);
        if(iStage >= 4){
            imgGrandmaFoodSpace.setImageResource(R.drawable.empty_space_the_number_food_extend);
            imgGrandfaFoodSpace.setImageResource(R.drawable.empty_space_the_number_food_extend);
        }
        txtGrandmaFood.setText(dataSet.sGrandmaFood);
        txtGrandfaFood.setText(dataSet.sGrandfaFood);

        //set button
        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + dataSet.iBtnList[i]);

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
        Intent intent = new Intent(this, ActStep0304.class);
        startActivity(intent);
    }

    public class Step0303DataSet {
        private final String sDiscriptionList[] = {" 할머니와 할아버지가 손주들에게 받은 송편의 합은 모두 몇 개인가요? 아래 숫자를 누르세요!",
                " 할머니 할아버지가 홍시 선물을 받았습니다. 모두 몇 개인지 아래 수를 누르세요!",
                " 할머니와 할아버지가 추석에 사용할 밤을 까려고 합니다. 모두 몇 개의 밤을 까야 할까요? 아래 숫자를 누르세요!",
                " 할머니와 할아버지가 제사준비를 하고 있습니다. 할머니와 할아버지가 전을 부칩니다. 얼만큼의 전을 부쳐야 할까요? 아래 숫자를 누르세요!"};
        private final int iFoodType[] = {0, 1, 2, 0, 3};
        private final int iGrandmaImageList[] = {R.drawable.grandma_with_songpyoen, R.drawable.grandma_with_hongshi, R.drawable.grandma_with_bam, R.drawable.grandma_with_jeon};
        private final int iGrandfaImageList[] = {R.drawable.grandfa_with_songpyoen, R.drawable.grandfa_with_hongshi, R.drawable.grandfa_with_bam, R.drawable.grandfa_with_jeon};
        private final String sGrandmaFoodList[] = {"깨송편5+콩송편10", "홍시 5개", "밤 24개", "깨송편5+콩송편10", "녹두전 20장\n호박전 40개\n동태전 30개"};
        private final String sGrandfaFoodList[] = {"깨송편5+콩손편10", "홍시 7개", "밤 30개", "깨송편5+콩송편10", "녹두전 2장\n호박전 3개\n동태전 5개"};
        private final int iAnswerList[] = {30, 12, 54, 30, 100};
        private final int iBtnListSet[][] = {{10, 20, 30},
                {10, 12, 15},
                {54, 44, 64},
                {10, 20, 30},
                {90, 100, 110}};

        public String sDiscription;
        public int iGrandmaImage;
        public int iGrandfaImage;
        public String sGrandmaFood;
        public String sGrandfaFood;
        public int iAnswer;
        public int iBtnList[] = new int[3];

        public void setData(int iSeed){
            sDiscription = sDiscriptionList[iFoodType[iSeed]];
            iGrandmaImage = iGrandmaImageList[iFoodType[iSeed]];
            iGrandfaImage = iGrandfaImageList[iFoodType[iSeed]];
            sGrandmaFood = sGrandmaFoodList[iSeed];
            sGrandfaFood = sGrandfaFoodList[iSeed];
            iAnswer = iAnswerList[iSeed];

            for(int i = 0; i < 3; i++)
                iBtnList[i] = iBtnListSet[iSeed][i];
        }
    }
}
