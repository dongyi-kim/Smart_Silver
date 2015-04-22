package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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
 * Created by Acka on 2015-04-05.
 */
public class ActStep0304 extends StageActivity {
    private static final int MAX_STAGE_NUMBER = 5;

    private TextView txtDiscription;
    private TextView txtEmptyUpper1;
    private TextView txtEmptyUpper2;
    private TextView txtUnder[] = new TextView[3];
    private TextView txtEmptyUnder1;
    private ImageView imgUpper[] = new ImageView[3];
    private ImageView imgTextSpace[] = new ImageView[3];
    private ImageView imgFoodSign1;
    private ImageView imgFoodSign2;
    private ImageView imgSignBetween;
    private LinearLayout linearUnder2;
    private FrameLayout frameUnder1;
    private Button btnAnswer[] = new Button[3];

    private int iStage = 1;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0304DataSet dataSet = new Step0304DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_4);

        txtDiscription = (TextView)findViewById(R.id.text_discription);
        txtEmptyUpper1 = (TextView)findViewById(R.id.txt_empty_upper_1);
        txtEmptyUpper2 = (TextView)findViewById(R.id.txt_empty_upper_2);
        txtUnder[0] = (TextView)findViewById(R.id.txt_under_1);
        txtEmptyUnder1 = (TextView)findViewById(R.id.txt_under_1);
        txtUnder[1] = (TextView)findViewById(R.id.txt_food_1);
        txtUnder[2] = (TextView)findViewById(R.id.txt_food_2);
        imgUpper[0] = (ImageView)findViewById(R.id.img_upper_1);
        imgUpper[1] = (ImageView)findViewById(R.id.img_upper_2);
        imgUpper[2] = (ImageView)findViewById(R.id.img_upper_3);
        imgTextSpace[0] = (ImageView)findViewById(R.id.img_text_space_1);
        imgTextSpace[1] = (ImageView)findViewById(R.id.img_food_space_1);
        imgTextSpace[2] = (ImageView)findViewById(R.id.img_food_space_2);
        imgFoodSign1 = (ImageView)findViewById(R.id.img_food_sign_1);
        imgFoodSign2 = (ImageView)findViewById(R.id.img_food_sign_2);
        imgSignBetween = (ImageView)findViewById(R.id.img_sign_between);
        linearUnder2 = (LinearLayout)findViewById(R.id.linear_under_2);
        frameUnder1 = (FrameLayout)findViewById(R.id.frame_under_1);
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
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        //set problem
        txtDiscription.setText(dataSet.sDiscription);
        if(iStage >= 4){
            imgTextSpace[1].setImageResource(R.drawable.empty_space_the_number_food_extend);
            imgTextSpace[2].setImageResource(R.drawable.empty_space_the_number_food_extend);
        }

        if(iStage > 2){
            imgUpper[0].setVisibility(View.GONE);
            txtEmptyUpper1.setVisibility(View.GONE);
            frameUnder1.setVisibility(View.GONE);
            imgFoodSign1.setVisibility(View.GONE);
            imgFoodSign2.setVisibility(View.GONE);
            txtEmptyUnder1.setVisibility(View.GONE);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)txtEmptyUpper2.getLayoutParams();
            layoutParams.weight = 0.1f;
            txtEmptyUpper2.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams)linearUnder2.getLayoutParams();
            layoutParams.weight = 0.15f;
            linearUnder2.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams)imgSignBetween.getLayoutParams();
            layoutParams.weight = 0.5f;
            imgSignBetween.setLayoutParams(layoutParams);
        }

        //set button
        for(int i = 0; i < 3; i++) {
            txtUnder[i].setText("" + dataSet.sTextList[i]);
            imgUpper[i].setImageResource(dataSet.iImageList[i]);
            btnAnswer[i].setText("" + dataSet.iBtnList[i]);
        }
    }


    public void checkAnswer(Object object){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    if(iStage >= MAX_STAGE_NUMBER) goNext();
                    else {
                        iRetryCount = 0;
                        iStage++;
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
        Intent intent = new Intent(this, ActStep0305.class);
        startActivity(intent);
    }

    public class Step0304DataSet {
        private final String sDiscriptionList[] = {" 할머니와 할아버지가 손주들에게 받은 송편을 먹었습니다. 송편은 모두 몇 개가 남았을까요? 아래 숫자를 누르세요!",
                " 할머니가 홍시 선물을 받았습니다. 할아버지와 함께 홍시를 나누어 먹었습니다. 홍시는 몇 개 남았을까요? 아래 숫자를 누르세요!",
                " 할머니와 할아버지가 추석에 사용할 밤을 까놓았습니다. 그런데, 손주들이 밤을 계속 가져갑니다. 밤은 몇 개 남았을까요? 아래 숫자를 누르세요!",
                " 할머니와 할아버지가 추석에 사용할 약과와 송편을 만들었습니다. 손님이 오셔서 약과와 송편을 대접하였습니다. 모두 몇 개가 남았을까요? 아래 숫자를 누르세요!",
                " 할머니와 할아버지가 제사준비를 하고 있습니다. 할머니가 전을 부치는데, 할아버지는 옆에서 먹기만 합니다. 남은 전은 모두 몇 개인지 아래 숫자를 누르세요!"};
        private final int iImageListSet[][] = {{R.drawable.img_songpyeon, R.drawable.img_grandma_only, R.drawable.img_grandfa_only},
                {R.drawable.img_hongshi, R.drawable.img_grandma_only, R.drawable.img_grandfa_only},
                {R.drawable.img_grandma_grandfa_with_bam, R.drawable.img_grandma_grandfa_with_bam, R.drawable.img_grandson},
                {R.drawable.img_grandma_grandfa_in_chuseock, R.drawable.img_grandma_grandfa_in_chuseock, R.drawable.img_table_for_guest},
                {R.drawable.grandma_with_jeon, R.drawable.grandma_with_jeon, R.drawable.grandfa_with_jeon}};
        private final String sTextListSet[][] = {{"송편 10개", "송편 2개", "송편 5개"},
                {"홍시 15개", "홍시 2개", "홍시 2개"},
                {"", "밤 50개", "밤 25개"},
                {"", "약과 20개\n송편 50개", "약과 5개\n송편 15개"},
                {"", "녹두전 20장\n호박전 30개\n동태전 30개", "녹두전 2장\n호박전 3개\n동태전 5개"}};
        private final int iAnswerList[] = {3, 11, 25, 50, 70};
        private final int iBtnListSet[][] = {{2, 3, 4},
                {10, 11, 12},
                {20, 25, 30},
                {40, 50, 60},
                {60, 70, 80}};

        public String sDiscription;
        public int iImageList[] = new int[3];
        public String sTextList[] = new String[3];
        public int iAnswer;
        public int iBtnList[] = new int[3];

        public void setData(int iSeed){
            sDiscription = sDiscriptionList[iSeed];
            iAnswer = iAnswerList[iSeed];

            for(int i = 0; i < 3; i++) {
                iImageList[i] = iImageListSet[iSeed][i];
                sTextList[i] = sTextListSet[iSeed][i];
                iBtnList[i] = iBtnListSet[iSeed][i];
            }
        }
    }
}
