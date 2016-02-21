package src.activities.Step01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
public class ActStep0102 extends StageActivity {
    private static final int ROW_COUNT = 2;
    private static final int COLUMN_COUNT = 7;
    private static final float BUTTON_WEIGHT = 0.12f;
    private static final float MARGIN_WEIGHT = 0.01f;

    private final LinearLayout linearDrawfieldRow[] = new LinearLayout[ROW_COUNT];
    public final ImageButton ibtnCell[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final View emptyBetween[][] = new View[ROW_COUNT][COLUMN_COUNT - 1];
    public final View emptySide[][] = new View[ROW_COUNT][2];

    public final Button btnAnswer[] = new Button[3];
    public int iAnswerValue[] = new int[3];
    private TextView txtDescription;

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0102DataSet dataSet = new Step0102DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_2);

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

        //button listener
        for(int i = 0; i <3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(Integer.parseInt(((Button)v).getText().toString()) == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });// end of button listener

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3);
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = 1;
        for(int i = 0; i < 3; i++){
            iAnswerValue[i] = iFirstAnsNumber++;
            btnAnswer[i].setText("" + iAnswerValue[i]);
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

        startRecording();
    }

    public synchronized void checkAnswer(Object o){
        countUpTry();
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0103.class);
        startActivity(intent);
    }

    public class Step0102DataSet {
        private final int arrVariationCount[] = {0, 0, 1, 1, 1, 2, 3, 2, 2, 1, 1};

        private final int arrLineCount2[][] = {{2, 0}};
        private final int arrLineCount3[][] = {{3, 0}};
        private final int arrLineCount4[][] = {{4, 0}};
        private final int arrLineCount5[][] = {{5, 0}, {2, 3}};
        private final int arrLineCount6[][] = {{3, 3}, {3, 3}, {6, 0}};
        private final int arrLineCount7[][] = {{3, 4}, {4, 3}};
        private final int arrLineCount8[][] = {{3, 5}, {4, 4}};
        private final int arrLineCount9[][] = {{4, 5}};
        private final int arrLineCount10[][] = {{5, 5}};
        private final int arrLineCount[][][] = {{{}}, {{}}, arrLineCount2, arrLineCount3, arrLineCount4, arrLineCount5,
                arrLineCount6, arrLineCount7, arrLineCount8, arrLineCount9, arrLineCount10};

        private final int arrSideRatio2[][][] = {{{1, 1}}};
        private final int arrSideRatio3[][][] = {{{1, 1}}};
        private final int arrSideRatio4[][][] = {{{1, 1}}};
        private final int arrSideRatio5[][][] = {{{1, 1}}, {{2, 5}, {3, 2}}};
        private final int arrSideRatio6[][][] = {{{12, 30}, {30, 12}}, {{2, 3}, {3, 2}}, {{1, 1}}};
        private final int arrSideRatio7[][][] = {{{1, 1}, {1, 1}}, {{1, 5}, {4, 1}}};
        private final int arrSideRatio8[][][] = {{{1, 1}, {1, 1}}, {{1, 4}, {4, 1}}};
        private final int arrSideRatio9[][][] = {{{8, 15}, {1, 1}}};
        private final int arrSideRatio10[][][] = {{{1, 1}, {1, 1}}};
        private final int arrSideRatio[][][][] = {{{{}}}, {{{}}}, arrSideRatio2, arrSideRatio3, arrSideRatio4, arrSideRatio5,
                arrSideRatio6, arrSideRatio7, arrSideRatio8, arrSideRatio9, arrSideRatio10};

        private final int arrMarginMagnif[][] = {{}, {}, {10}, {8}, {8}, {5, 6}, {5, 8, 2}, {8, 2}, {2, 4}, {2}, {2}};
        private final int arrIconSource[] = {R.drawable.icon_single_eggplant, R.drawable.icon_single_green_circle, R.drawable.icon_single_pear,
                R.drawable.icon_single_mandarin, R.drawable.icon_single_octopus};

        public int iAnswer;
        public int iLineCount[] = new int[2];
        public int iSideRatio[][] = new int[2][2];
        public int iMarginMagnif;
        public int iIconSource;

        public void setData(int iStage) {
            if(iStage <= 3) iAnswer = iStage + 1 + rand.nextInt(3);
            else if(iStage <= 4) iAnswer = iStage + 1 + rand.nextInt(4);
            else iAnswer = iStage + 2 + rand.nextInt(4);

            int iSeed = rand.nextInt(arrVariationCount[iAnswer]);
            iLineCount[0] = arrLineCount[iAnswer][iSeed][0];
            iLineCount[1] = arrLineCount[iAnswer][iSeed][1];

            iSideRatio[0][0] = arrSideRatio[iAnswer][iSeed][0][0];
            iSideRatio[0][1] = arrSideRatio[iAnswer][iSeed][0][1];
            if(iLineCount[1] != 0){
                iSideRatio[1][0] = arrSideRatio[iAnswer][iSeed][1][0];
                iSideRatio[1][1] = arrSideRatio[iAnswer][iSeed][1][1];
            }

            iMarginMagnif = arrMarginMagnif[iAnswer][iSeed];
            iIconSource = arrIconSource[rand.nextInt(5)];
        }
    }

}