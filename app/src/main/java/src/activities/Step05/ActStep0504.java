package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by waps12b on 15. 3. 15..
 */
public class ActStep0504 extends StageActivity {
    private static final int ROW_COUNT = 2;
    private static final int COLUMN_COUNT = 7;
    private static final float BUTTON_WEIGHT = 0.2f;
    private static final float MARGIN_WEIGHT = 0.01f;

    private final LinearLayout linearDrawfieldRow[] = new LinearLayout[ROW_COUNT];
    public final ImageButton ibtnCell[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final View emptyBetween[][] = new View[ROW_COUNT][COLUMN_COUNT - 1];
    public final View emptySide[][] = new View[ROW_COUNT][2];

    public final Button btnAnswer[] = new Button[3];
    private TextView txtDescription;
    public TextView txtPriceDescription;

    public int iAnswerIndex = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0504DataSet dataSet = new Step0504DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_4);

        linearDrawfieldRow[0] = (LinearLayout)findViewById(R.id.drawfield_row_1);
        linearDrawfieldRow[1] = (LinearLayout)findViewById(R.id.drawfield_row_2);
        for(int i = 0; i < ROW_COUNT; i++){
            ibtnCell[i][0] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_1));
            ibtnCell[i][1] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_2));
            ibtnCell[i][2] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_3));
            ibtnCell[i][3] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_4));
            ibtnCell[i][4] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_5));
            ibtnCell[i][5] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_6));
            ibtnCell[i][6] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.ibtn_col_7));

            emptyBetween[i][0] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_between_col_1_2));
            emptyBetween[i][1] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_between_col_2_3));
            emptyBetween[i][2] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_between_col_3_4));
            emptyBetween[i][3] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_between_col_4_5));
            emptyBetween[i][4] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_between_col_5_6));
            emptyBetween[i][5] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_between_col_6_7));

            emptySide[i][0] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_left));
            emptySide[i][1] = (View)(linearDrawfieldRow[i].findViewById(R.id.empty_right));
        }

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);
        txtDescription = (TextView)findViewById(R.id.txt_description);
        txtPriceDescription = (TextView)findViewById(R.id.txt_price_description);

        //button listener
        for(int i = 0; i <3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(v == btnAnswer[iAnswerIndex]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });// end of button listener

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtDescription.setText(dataSet.sDescription);
        txtPriceDescription.setText(dataSet.sPriceDescription);

        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3) * 100;
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = 100;
        for(int i = 0; i < 3; i++){
            if(iFirstAnsNumber == dataSet.iAnswer) iAnswerIndex = i;
            btnAnswer[i].setText("" + iFirstAnsNumber + "원");
            iFirstAnsNumber += 100;
        }

        for(int r = 0; r < ROW_COUNT; r++) {
            if(dataSet.iLineCount[r] == 0){
                linearDrawfieldRow[r].setVisibility(View.GONE);
                break;
            }
            else linearDrawfieldRow[r].setVisibility(View.VISIBLE);

            for (int i = 0; i < COLUMN_COUNT; i++) {
                if (i >= dataSet.iLineCount[r]) {
                    ibtnCell[r][i].setVisibility(View.GONE);
                    emptyBetween[r][i - 1].setVisibility(View.GONE);
                } else {
                    ibtnCell[r][i].setVisibility(View.VISIBLE);
                    ibtnCell[r][i].setImageResource(dataSet.iIconSource);
                    emptyBetween[r][i].setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams emptyParams = (LinearLayout.LayoutParams) emptyBetween[r][i].getLayoutParams();
                    emptyParams.weight = MARGIN_WEIGHT * dataSet.iMarginMagnif;
                    emptyBetween[r][i].setLayoutParams(emptyParams);

                    LinearLayout.LayoutParams cellParams = (LinearLayout.LayoutParams) ibtnCell[r][i].getLayoutParams();
                    cellParams.weight = BUTTON_WEIGHT;
                    ibtnCell[r][i].setLayoutParams(cellParams);
                }
            }

            float fWeightSum = dataSet.iLineCount[r] * BUTTON_WEIGHT + (dataSet.iLineCount[r] - 1) * MARGIN_WEIGHT * dataSet.iMarginMagnif;
            LinearLayout.LayoutParams emptyLeft = (LinearLayout.LayoutParams) emptySide[r][0].getLayoutParams();
            LinearLayout.LayoutParams emptyRight = (LinearLayout.LayoutParams) emptySide[r][1].getLayoutParams();
            emptyLeft.weight = (1.0f - fWeightSum) * dataSet.iSideRatio[r][0] / (float)(dataSet.iSideRatio[r][0] + dataSet.iSideRatio[r][1]);
            emptyRight.weight = (1.0f - fWeightSum) * dataSet.iSideRatio[r][1] / ((float)dataSet.iSideRatio[r][0] + dataSet.iSideRatio[r][1]);
            emptySide[r][0].setLayoutParams(emptyLeft);
            emptySide[r][1].setLayoutParams(emptyRight);
        }

        StartRecording();
    }

    public void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if(isRight || iRetryCount > 1) StopRecording(isRight);

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

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStep0505.class);
        startActivity(intent);
    }

    public class Step0504DataSet {
        private final int arrLineCount[][] = {{}, {}, {2, 0}, {3, 0}, {4, 0}, {3, 2}, {4, 2}, {4, 3}};
        private final int arrIconSource[] = {R.drawable.icon_set_songpyeon_4, R.drawable.icon_signle_napkin_thick, R.drawable.icon_set_songpyeon_5, R.drawable.icon_set_apple_5, R.drawable.icon_set_pumpkin_pancake_4};
        private final String arrCountUnit[] = {"접시", "묶음", "접시", "팩", "소쿠리"};
        private final String arrPlaceDescription[] = {"시장", "동대문", "시장", "과일 가게", "시장"};
        private final String arrThingDescription[] = {"송편", "냅킨", "송편", "사과", "호박전"};

        public String sDescription;
        public String sPriceDescription;
        public int iAnswer;
        public int iLineCount[] = new int[2];
        public int iSideRatio[][] = {{1, 1}, {1, 1}};
        public int iMarginMagnif;
        public int iIconSource;

        public void setData(int iStage) {
            int iSeed = iStage - 1;
            sDescription = "노인학교에서 가을축제 준비를 하기 위해 " + arrPlaceDescription[iSeed] + "에 갔습니다. "
                    + arrThingDescription[iSeed] + " 한 " + arrCountUnit[iSeed] + "의 가격은 얼마입니까?";

            int iCount = 2 + rand.nextInt(6);
            iAnswer = (2 + rand.nextInt(8)) * 100;

            sPriceDescription = "" + iCount + "" + arrCountUnit[iSeed] + " ";
            if((iCount * iAnswer) / 1000 > 0) sPriceDescription += "" + (iCount * iAnswer) / 1000 + ",";
            if((iCount * iAnswer) % 1000 > 0) sPriceDescription += "" + (iCount * iAnswer) % 1000 + "원";
            else sPriceDescription += "000원";

            iLineCount[0] = arrLineCount[iCount][0];
            iLineCount[1] = arrLineCount[iCount][1];

            iMarginMagnif = 1;
            if(iCount < 4) iMarginMagnif += 4 - iCount;
            iIconSource = arrIconSource[iSeed];
        }
    }

}