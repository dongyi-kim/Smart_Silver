package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-05.
 */
public class ActStep0304 extends StageActivity {
    private final int iDescriptionCount[] = {3, 3, 2, 2, 2};

    private TextView txtDescription;
    private LinearLayout linearFrame[] = new LinearLayout[2];

    private ImageView imgList[] = new ImageView[3];
    private TextView txtFoodDescription1[] = new TextView[3];
    private Button btnAnswer[] = new Button[3];
    private ImageView imgFood[] = new ImageView[2];
    private TextView txtFoodDescription2[] = new TextView[2];

    private int iRetryCount = 0;
    public boolean isRight = false;

    private Random rand = new Random();
    public Step0304DataSet dataSet = new Step0304DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_4);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        linearFrame[0] = (LinearLayout)findViewById(R.id.linear_frame_1_to_2);
        linearFrame[1] = (LinearLayout)findViewById(R.id.linear_frame_3_to_5);

        imgList[0] = (ImageView)findViewById(R.id.img_origin);
        imgList[1] = (ImageView)findViewById(R.id.img_minus_1);
        imgList[2] = (ImageView)findViewById(R.id.img_minus_2);
        txtFoodDescription1[0] = (TextView)findViewById(R.id.txt_origin);
        txtFoodDescription1[1] = (TextView)findViewById(R.id.txt_minus_1);
        txtFoodDescription1[2] = (TextView)findViewById(R.id.txt_minus_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        imgFood[0] = (ImageView)findViewById(R.id.img_food_set_1);
        imgFood[1] = (ImageView)findViewById(R.id.img_food_set_2);
        txtFoodDescription2[0] = (TextView)findViewById(R.id.txt_food_description_1);
        txtFoodDescription2[1] = (TextView)findViewById(R.id.txt_food_description_2);

        linearFrame[1].setVisibility(View.GONE);
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

    public synchronized void setQuestion(boolean isRetry, Object object){
        if(iStage == 3){
            linearFrame[0].setVisibility(View.GONE);
            linearFrame[1].setVisibility(View.VISIBLE);
        }
        dataSet.setData(iStage);

        int iGapMult = dataSet.iAnswer <= 10 ? 1 : (dataSet.iAnswer % 10 == 0 ? 10 : 1);
        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3) * iGapMult;
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = dataSet.iAnswer - (dataSet.iAnswer > 2 * iGapMult ? 2 : 1) * iGapMult;
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setText("" + iFirstAnsNumber);
            iFirstAnsNumber += iGapMult;
        }

        txtDescription.setText(dataSet.sDescription);

        if(iStage <= 2) {
            imgList[0].setImageResource(dataSet.iImageResource[0]);
            imgList[1].setImageResource(dataSet.iImageResource[1]);
            imgList[2].setImageResource(dataSet.iImageResource[2]);
            txtFoodDescription1[0].setText(dataSet.sFoodDescription[0]);
            txtFoodDescription1[1].setText(dataSet.sFoodDescription[1]);
            txtFoodDescription1[2].setText(dataSet.sFoodDescription[2]);
        }
        else{
            imgFood[0].setImageResource(dataSet.iImageResource[0]);
            imgFood[1].setImageResource(dataSet.iImageResource[1]);
            txtFoodDescription2[0].setText(dataSet.sFoodDescription[0]);
            txtFoodDescription2[1].setText(dataSet.sFoodDescription[1]);

            if(iStage >= NUM_OF_STAGE - 1) {
                for(int i = 0; i < 2; i++) {
                    txtFoodDescription2[i].setTextSize(getResources().getDimension(R.dimen.wp5));
                    LinearLayout.LayoutParams txtParam = (LinearLayout.LayoutParams) txtFoodDescription2[i].getLayoutParams();
                    txtParam.leftMargin = 0;
                    txtParam.rightMargin = 0;

                    txtFoodDescription2[i].setLayoutParams(txtParam);
                }
            }
        }

        StartRecording();
    }


    public synchronized void checkAnswer(Object object){
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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0305.class);
        startActivity(intent);
    }

    public class Step0304DataSet{
        private final int arrSampleCount[][][][] = {{{{10}, {2}, {5}}, {{15}, {2}, {2}}, {{50}, {25}}, {{20, 50}, {5, 15}}, {{20, 30}, {4, 6}}},
                {{{10}, {3}, {3}}, {{15}, {2}, {3}}, {{40}, {20}}, {{40, 40}, {5, 15}}, {{10, 20}, {5, 5}}},
                {{{10}, {3}, {5}}, {{15}, {5}, {5}}, {{50}, {20}}, {{20, 40}, {15, 5}}, {{20, 35}, {5, 10}}}};
        private final String arrFoodKind[][] = {{"송편"}, {"홍시"}, {"밤"}, {"약과", "송편"}, {"녹두전", "호박전"}};
        private final String arrCountUnit[] = {"개", "개", "개", "개", "장"};

        private int arrImageResource[][] = {{R.drawable.icon_set_songpyeon_10, R.drawable.icon_grandma, R.drawable.icon_grandfa},
                {R.drawable.icon_set_dried_persimmon_15, R.drawable.icon_grandma, R.drawable.icon_grandfa},
                {R.drawable.img_grandma_grandfa_with_chestnut, R.drawable.img_char_son},
                {R.drawable.img_grandma_grandfa_with_dagwa, R.drawable.img_dagwa},
                {R.drawable.img_grandma_with_pumpkin_pancake, R.drawable.img_char_grandfa_with_book}};

        public String sDescription;
        public int iAnswer;
        public String sFoodDescription[] = new String[3];
        public int iImageResource[] = new int[3];

        void setData(int iStage){
            int iSeed = rand.nextInt(3);

            iAnswer = 0;
            int iSampleCount = arrSampleCount[iSeed][iStage - 1][0].length;

            for(int i = 0; i < arrSampleCount[iSeed][iStage - 1].length; i++){
                sFoodDescription[i] = arrFoodKind[iStage - 1][0] + " " + arrSampleCount[iSeed][iStage - 1][i][0] + arrCountUnit[iStage - 1];
                if(iSampleCount > 1) sFoodDescription[i] += " + " + arrFoodKind[iStage - 1][1] + " " + arrSampleCount[iSeed][iStage - 1][i][1] + arrCountUnit[iStage - 1];
                if(iSampleCount > 2) sFoodDescription[i] += "\n+ " + arrFoodKind[iStage - 1][2] + " " + arrSampleCount[iSeed][iStage - 1][i][2] + arrCountUnit[iStage - 1];

                for(int j = 0; j < iSampleCount; j++) {
                    if(i == 0) iAnswer += arrSampleCount[iSeed][iStage - 1][i][j];
                    else iAnswer -= arrSampleCount[iSeed][iStage - 1][i][j];
                }
                iImageResource[i] = arrImageResource[iStage - 1][i];
            }

            if(iStage == 1) sDescription = "할머니와 할아버지가 손주들이 빚은 송편을\n받았습니다. 두 분이 드시고 남은 송편은 몇 개일까요?";
            else if(iStage == 2) sDescription = "할머니가 홍시를 선물 받았습니다.\n할머니와 할아버지가 드시고 남은 홍시는 몇 개일까요?";
            else if(iStage == 3) sDescription = "추석에 사용할 밤을 까놓았는데,\n손주들이 밤을 가져갔습니다. 밤은 몇 개 남았을까요?";
            else if(iStage == 4) sDescription = "추석에 사용할 약과와 송편을 만들어\n손님께 대접하였습니다. 모두 몇 개가 남았을까요?";
            else sDescription = "할머니와 할아버지는 아래의 전을 만들고,\n먹었습니다. 남은 전은 모두 몇 개일까요?";
        }
    }

}
