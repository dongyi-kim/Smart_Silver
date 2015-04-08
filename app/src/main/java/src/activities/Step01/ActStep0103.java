package src.activities.Step01;

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
import src.activities.FrameActivity;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by waps12b on 15. 3. 15..
 */
public class ActStep0103 extends StageActivity {

    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 7;

    private LinearLayout linearDrawfield;
    private LinearLayout linearDrawfieldRow[] = new LinearLayout[ROW_COUNT];
    public final ImageButton ibtnShape[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final TextView txtShape[][] = new TextView[ROW_COUNT][COLUMN_COUNT];
    public final Button btnAnswer[] = new Button[3];
    public int iAnswerValue[] = new int[3];
    private TextView txtDiscription;

    private int iStage = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0103DataSet dataSet = new Step0103DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_3);

        linearDrawfield = (LinearLayout)findViewById(R.id.drawfield);
        linearDrawfieldRow[0] = (LinearLayout)findViewById(R.id.drawfield_row_1);
        linearDrawfieldRow[1] = (LinearLayout)findViewById(R.id.drawfield_row_2);
        linearDrawfieldRow[2] = (LinearLayout)findViewById(R.id.drawfield_row_3);
        linearDrawfieldRow[3] = (LinearLayout)findViewById(R.id.drawfield_row_4);
        for(int i = 0; i < ROW_COUNT; i++){
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
        txtDiscription = (TextView)findViewById(R.id.txt_discription);

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
        int iRandomSeed = 2 * iStage + rand.nextInt(1);
        dataSet.setData(iRandomSeed);

        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3);
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = 1;
        for(int i = 0; i < 3; i++){
            iAnswerValue[i] = iFirstAnsNumber++;
        }

        for(int i = 0; i < ROW_COUNT; i++){
            if(dataSet.arrLineDrawCount[i][0] == 0 && dataSet.arrLineDrawCount[i][1] == 0 && dataSet.arrLineDrawCount[i][2] == 0){
                linearDrawfieldRow[i].setVisibility(View.GONE);
                continue;
            }
            int j = 0;
            for(; j < dataSet.arrLineDrawCount[i][0]; j++)
                ibtnShape[i][j].setVisibility(View.INVISIBLE);

            for(; j < dataSet.arrLineDrawCount[i][1]; j++)
                ibtnShape[i][j].setVisibility(View.VISIBLE);

            for(; j < dataSet.arrLineDrawCount[i][2]; j++)
                ibtnShape[i][j].setVisibility(View.INVISIBLE);

            for(; j < COLUMN_COUNT; j++)
                ibtnShape[i][j].setVisibility(View.GONE);
        }

        for(int i = 0; i < 3; i++){
            btnAnswer[i].setText("" + iAnswerValue[i]);
        }
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

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iStage++;
                    if(iStage < 5) setQuestion(false);
                    else goNext();
                }
                else{
                    iRetryCount++;
                }
            }
        });
    }

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStep0104.class);
        startActivity(intent);
    }

    public class Step0103DataSet {
        private static final int MAX_SET_NUMBER = 11;

        private final int arrAnswerList[] = {3, 2, 4, 5, 6, 7, 7, 8, 10, 11, 19};
        private final int arrLineDrawCountListSet[][][] = {{{1, 3, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{1, 2, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{1, 4, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{1, 5, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{1, 3, 3}, {2, 3, 1}, {0, 0, 0}, {0, 0, 0}},
                {{1, 3, 1}, {1, 4 , 1}, {0, 0, 0}, {0, 0, 0}},
                {{0, 7, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{2, 3, 2}, {1, 5, 1}, {0, 0, 0}, {0, 0, 0}},
                {{0, 5, 1}, {1, 5, 0}, {0, 0, 0},{0, 0, 0}},
                {{1, 1, 5}, {1, 5, 1}, {1, 5, 1}, {0, 0, 0}},
                {{1, 5, 1}, {1, 5, 1},{1, 5, 1}, {1, 4, 2}}};

        public int iAnswer;
        public int arrLineDrawCount[][] = new int[4][3];

        public Step0103DataSet(){
            //If file I/O, read file and set data
        }

        public void setData(int iSeed) {
            iAnswer = arrAnswerList[iSeed];

            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 3; j++) {
                    arrLineDrawCount[i][j] = arrLineDrawCountListSet[iSeed][i][j];
                }
        }
    }

}
