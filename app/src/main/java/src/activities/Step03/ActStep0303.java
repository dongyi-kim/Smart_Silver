package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-29.
 */
public class ActStep0303 extends StageActivity{
    private TextView txtDescription;
    private ImageView imgFood[] = new ImageView[2];
    private TextView txtFoodDescription[] = new TextView[2];
    private Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    private Random rand = new Random();
    public Step0303DataSet dataSet = new Step0303DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_3);

        //NUM_OF_STAGE = 4;

        txtDescription = (TextView)findViewById(R.id.txt_description);
        imgFood[0] = (ImageView)findViewById(R.id.img_food_set_1);
        imgFood[1] = (ImageView)findViewById(R.id.img_food_set_2);
        txtFoodDescription[0] = (TextView)findViewById(R.id.txt_food_description_1);
        txtFoodDescription[1] = (TextView)findViewById(R.id.txt_food_description_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(Integer.parseInt(((Button)v).getText().toString()) == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }


    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        int iGapMult = dataSet.iAnswer <= 10 ? 1 : (dataSet.iAnswer % 10 == 0 ? 10 : 1);
        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3) * iGapMult;
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = 1 * iGapMult;
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setText("" + iFirstAnsNumber);
            iFirstAnsNumber += iGapMult;
        }

        if(iStage >= NUM_OF_STAGE - 1) {
            for(int i = 0; i < 2; i++) {
                txtFoodDescription[i].setTextSize(getResources().getDimension(R.dimen.wp5) / 2);
                LinearLayout.LayoutParams txtParam = (LinearLayout.LayoutParams) txtFoodDescription[i].getLayoutParams();
                txtParam.leftMargin = 0;
                txtParam.rightMargin = 0;

                if(iStage == NUM_OF_STAGE) txtParam.height = (int)getResources().getDimension(R.dimen.wp14);
                txtFoodDescription[i].setLayoutParams(txtParam);
            }
        }

        txtDescription.setText(dataSet.sDescription);
        imgFood[0].setImageResource(dataSet.iImageResource[0]);
        imgFood[1].setImageResource(dataSet.iImageResource[1]);
        txtFoodDescription[0].setText(dataSet.sFoodDescription[0]);
        txtFoodDescription[1].setText(dataSet.sFoodDescription[1]);

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
                    if(iStage > NUM_OF_STAGE) goNext();
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

    public class Step0303DataSet{
        private final String arrDescription[] = {"할머니와 할아버지가 홍시 선물을 받았습니다.\n받은 홍시를 모두 더하면 몇 개인가요?",
                "할머니와 할아버지가 추석에 사용할 밤을 까려고 합니다.\n모두 몇 개의 밤을 까야 할까요?",
                "할머니와 할아버지가 홍시 선물을 받았습니다.\n받은 송편을 모두 더하면 몇 개인가요?",
                "할머니와 할아버지가 추석에 사용할 전을 만들었습니다.\n만든 전은 모두 몇 장입니까?"};
        private final int arrSampleCount[][][][] = {{{{5}, {7}}, {{24}, {30}}, {{5, 10}, {5, 10}}, {{20, 40, 30}, {2, 3, 5}}},
                {{{7}, {8}}, {{25}, {25}}, {{2, 3}, {5, 10}}, {{10, 20, 10}, {2, 3, 5}}},
                {{{8}, {9}}, {{32}, {40}}, {{2, 3}, {3, 2}}, {{10, 10, 10}, {10, 5, 5}}}};
        private final String arrFoodKind[][] = {{"홍시"}, {"밤"}, {"깨송편", "콩송편"}, {"녹두전", "호박전", "동태전"}};
        private final String arrCountUnit[] = {"개", "개", "개", "장"};

        private int arrImageResource[][][] = {{{R.drawable.icon_set_dried_persimmon_5, R.drawable.icon_set_dried_persimmon_7}, {R.drawable.icon_set_chestnut_many, R.drawable.icon_set_chestnut_many}, {R.drawable.icon_set_songpyeon_5_and_10, R.drawable.icon_set_songpyeon_5_and_10}, {R.drawable.icon_set_pumpkin_pancake_top_many, R.drawable.icon_set_pumpkin_pancake_top_5}},
                {{R.drawable.icon_set_dried_persimmon_7, R.drawable.icon_set_dried_persimmon_8}, {R.drawable.icon_set_chestnut_many, R.drawable.icon_set_chestnut_many}, {R.drawable.icon_set_songpyeon_2_and_3, R.drawable.icon_set_songpyeon_5_and_10}, {R.drawable.icon_set_pumpkin_pancake_top_10, R.drawable.icon_set_pumpkin_pancake_top_5}},
                {{R.drawable.icon_set_dried_persimmon_8, R.drawable.icon_set_dried_persimmon_9}, {R.drawable.icon_set_chestnut_many, R.drawable.icon_set_chestnut_many}, {R.drawable.icon_set_songpyeon_2_and_3, R.drawable.icon_set_songpyeon_2_and_3}, {R.drawable.icon_set_pumpkin_pancake_top_10, R.drawable.icon_set_pumpkin_pancake_top_5}}};


        public String sDescription;
        public int iAnswer;
        public String sFoodDescription[] = new String[2];
        public int iImageResource[] = new int[2];

        void setData(int iStage){
            int iSeed = rand.nextInt(3);

            iAnswer = 0;
            sDescription = arrDescription[iStage - 1];
            int iSampleCount = arrSampleCount[iSeed][iStage - 1][0].length;

            for(int i = 0; i < 2; i++){
                sFoodDescription[i] = arrFoodKind[iStage - 1][0] + " " + arrSampleCount[iSeed][iStage - 1][i][0] + arrCountUnit[iStage - 1];
                if(iSampleCount > 1) sFoodDescription[i] += " + " + arrFoodKind[iStage - 1][1] + " " + arrSampleCount[iSeed][iStage - 1][i][1] + arrCountUnit[iStage - 1];
                if(iSampleCount > 2) sFoodDescription[i] += "\n+ " + arrFoodKind[iStage - 1][2] + " " + arrSampleCount[iSeed][iStage - 1][i][2] + arrCountUnit[iStage - 1];

                for(int j = 0; j < iSampleCount; j++)
                    iAnswer += arrSampleCount[iSeed][iStage - 1][i][j];
                iImageResource[i] = arrImageResource[iSeed][iStage - 1][i];
            }
        }
    }
}
