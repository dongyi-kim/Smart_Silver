package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by Acka on 2015-06-22.
 */
public class ActStep0504 extends StageActivity {

    private static final int ROW_COUNT = 2;
    private static final int COLUMN_COUNT = 7;

    private LinearLayout linearDrawfieldRow[] = new LinearLayout[ROW_COUNT];
    public final FrameLayout frameGrid[][] = new FrameLayout[ROW_COUNT][COLUMN_COUNT];
    public final ImageButton ibtnShape[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final TextView txtShape[][] = new TextView[ROW_COUNT][COLUMN_COUNT];
    public final Button btnAnswer[] = new Button[3];
    public int iAnswerValue[] = new int[3];
    private TextView txtDescription;
    private TextView txtAnswerDescription;

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
            frameGrid[i][0] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_1));
            frameGrid[i][1] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_2));
            frameGrid[i][2] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_3));
            frameGrid[i][3] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_4));
            frameGrid[i][4] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_5));
            frameGrid[i][5] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_6));
            frameGrid[i][6] = (FrameLayout)(linearDrawfieldRow[i].findViewById(R.id.frame_col_7));

            ibtnShape[i][0] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_1));
            ibtnShape[i][1] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_2));
            ibtnShape[i][2] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_3));
            ibtnShape[i][3] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_4));
            ibtnShape[i][4] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_5));
            ibtnShape[i][5] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_6));
            ibtnShape[i][6] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_7));

            txtShape[i][0] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_1));
            txtShape[i][1] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_2));
            txtShape[i][2] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_3));
            txtShape[i][3] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_4));
            txtShape[i][4] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_5));
            txtShape[i][5] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_6));
            txtShape[i][6] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_7));
        }

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);
        txtDescription = (TextView)findViewById(R.id.txt_description);
        txtAnswerDescription = (TextView)findViewById(R.id.txt_answer_description);

        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < COLUMN_COUNT; j++){
                txtShape[i][j].setText("");
            }
        }

        //button listener
        for(int i = 0; i <3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int x = 0;
                    for(int ii = 0; ii < 3; ii++){
                        if(btnAnswer[ii] == v){
                            x = ii; break;
                        }
                    }

                    if(iAnswerValue[x] == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });// end of button listener

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        txtDescription.setText(dataSet.sDescription);
        txtAnswerDescription.setText(dataSet.sAnswerDescription);

        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3) * 100;
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = 100;
        for(int i = 0; i < 3; i++){
            iAnswerValue[i] = iFirstAnsNumber;
            iFirstAnsNumber += 100;
        }

        for(int i = 0; i < ROW_COUNT; i++){
            linearDrawfieldRow[i].setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams)linearDrawfieldRow[i].getLayoutParams();
            param.weight = 1;
            linearDrawfieldRow[i].setLayoutParams(param);
            for(int j = 0; j < COLUMN_COUNT; j++)
                frameGrid[i][j].setVisibility(View.VISIBLE);
        }


        for(int i = 0; i < ROW_COUNT; i++){
            int sum = dataSet.arrLineDrawCount[i][0] + dataSet.arrLineDrawCount[i][1] + dataSet.arrLineDrawCount[i][2];
            if(sum == 0){
                linearDrawfieldRow[i].setVisibility(View.GONE);
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams)linearDrawfieldRow[i].getLayoutParams();
                param.weight = 0.2f;
                linearDrawfieldRow[i].setLayoutParams(param);
                continue;
            }

            int cnt = 0;
            for(int j = 0; j < dataSet.arrLineDrawCount[i][0]; j++)
                frameGrid[i][cnt++].setVisibility(View.INVISIBLE);

            for(int j = 0; j < dataSet.arrLineDrawCount[i][1]; j++)
                frameGrid[i][cnt++].setVisibility(View.VISIBLE);

            for(int j = 0; j < dataSet.arrLineDrawCount[i][2]; j++)
                frameGrid[i][cnt++].setVisibility(View.INVISIBLE);

            for(int j = sum; j < COLUMN_COUNT; j++)
                frameGrid[i][cnt++].setVisibility(View.GONE);
        }

        for(int i = 0; i < 3; i++){
            btnAnswer[i].setText("" + iAnswerValue[i] + "원");
        }

        StartRecording();
    }


    private void drawScreen(int iShapeCount){
        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < COLUMN_COUNT; j++){
                if(COLUMN_COUNT * i + j < iShapeCount){
                    ibtnShape[i][j].setImageResource(R.drawable.img_shape_star);
                    ibtnShape[i][j].setVisibility(View.VISIBLE);
                }
                else{
                    ibtnShape[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
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
        Intent intent = new Intent(this, ActStep0504.class);
        startActivity(intent);
    }

    public class Step0504DataSet {
        private final String arrDescription[] = {" 노인학교에서 가을축제 준비를 하기 위해 시장에 갔습니다.\n송편 한 접시의 가격은 얼마입니까?",
                " 노인학교에서 가을축제 준비를 하기 위해 동대문에 갔습니다.\n냅킨 한 묶음의 가격은 얼마입니까?",
                " 노인학교에서 가을축제 준비를 하기 위해 시장에 갔습니다.\n송편 한 접시의 가격은 얼마입니까?",
                " 노인학교에서 가을축제 준비를 하기 위해 과일 가게에 갔습니다.\n사과 한 팩의 가격은 얼마입니까?",
                " 노인학교에서 가을축제 준비를 하기 위해 시장에 갔습니다.\n호박전 한 소쿠리의 가격은 얼마입니까?"};
        private final String arrUnit[] = {"접시", "묶음", "접시", "팩", "소쿠리"};
        private final int arrSetCount[] = {2, 3, 4, 5, 7};
        private final int arrWholePrice[] = {1800, 300, 3200, 3500, 4900};
        private final int arrLineDrawCountListSet[][][] = {{{1, 2, 1}, {0, 0, 0}},
                {{1, 3, 1}, {0, 0, 0}},
                {{0, 4, 0}, {0, 0, 0}},
                {{0, 5, 0}, {0, 0, 0}},
                {{0, 4, 0}, {1, 3, 1}}};

        public int arrLineDrawCount[][] = new int[2][3];
        public String sDescription;
        public String sAnswerDescription;
        public int iAnswer;

        public void setData(int iSeed) {
            sDescription = arrDescription[iSeed];
            sAnswerDescription = "" + arrSetCount[iSeed] + arrUnit[iSeed] + " " + arrWholePrice[iSeed] + "원";
            iAnswer = arrWholePrice[iSeed] / arrSetCount[iSeed];

            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 3; j++) {
                    arrLineDrawCount[i][j] = arrLineDrawCountListSet[iSeed][i][j];
                }
        }
    }

}