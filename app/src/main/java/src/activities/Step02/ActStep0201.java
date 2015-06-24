package src.activities.Step02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-15.
 */
public class ActStep0201 extends StageActivity {

    private static final int ROW_COUNT = 5;
    private static final int COLUMN_COUNT = 10;

    private TextView txtDiscription;
    private LinearLayout linearLineCell[] = new LinearLayout[ROW_COUNT];
    public final ImageButton ibtnSingleCell[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final TextView txtSingleCell[][] = new TextView[ROW_COUNT][COLUMN_COUNT];
    public final ImageView imgCorrectMark[][] = new ImageView[ROW_COUNT][COLUMN_COUNT];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iNextAnswer = 0;
    public boolean bCellSelected[][] = new boolean[ROW_COUNT][COLUMN_COUNT];

    public Step0201DataSet dataSet = new Step0201DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_1);

        txtDiscription = (TextView)findViewById(R.id.txt_discription);
        linearLineCell[0] = (LinearLayout)findViewById(R.id.layout_line_1);
        linearLineCell[1] = (LinearLayout)findViewById(R.id.layout_line_2);
        linearLineCell[2] = (LinearLayout)findViewById(R.id.layout_line_3);
        linearLineCell[3] = (LinearLayout)findViewById(R.id.layout_line_4);
        linearLineCell[4] = (LinearLayout)findViewById(R.id.layout_line_5);
        for(int i = 0; i < ROW_COUNT; i++){
            ibtnSingleCell[i][0] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_1));
            ibtnSingleCell[i][1] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_2));
            ibtnSingleCell[i][2] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_3));
            ibtnSingleCell[i][3] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_4));
            ibtnSingleCell[i][4] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_5));
            ibtnSingleCell[i][5] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_6));
            ibtnSingleCell[i][6] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_7));
            ibtnSingleCell[i][7] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_8));
            ibtnSingleCell[i][8] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_9));
            ibtnSingleCell[i][9] = (ImageButton)(linearLineCell[i].findViewById(R.id.btn_cell_10));

            txtSingleCell[i][0] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_1));
            txtSingleCell[i][1] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_2));
            txtSingleCell[i][2] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_3));
            txtSingleCell[i][3] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_4));
            txtSingleCell[i][4] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_5));
            txtSingleCell[i][5] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_6));
            txtSingleCell[i][6] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_7));
            txtSingleCell[i][7] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_8));
            txtSingleCell[i][8] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_9));
            txtSingleCell[i][9] = (TextView)(linearLineCell[i].findViewById(R.id.txt_cell_10));

            imgCorrectMark[i][0] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_1));
            imgCorrectMark[i][1] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_2));
            imgCorrectMark[i][2] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_3));
            imgCorrectMark[i][3] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_4));
            imgCorrectMark[i][4] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_5));
            imgCorrectMark[i][5] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_6));
            imgCorrectMark[i][6] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_7));
            imgCorrectMark[i][7] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_8));
            imgCorrectMark[i][8] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_9));
            imgCorrectMark[i][9] = (ImageView)(linearLineCell[i].findViewById(R.id.img_correct_cell_10));
         }


        //button listener
        for(int i = 0; i < ROW_COUNT; i++)
        for(int j = 0; j < COLUMN_COUNT; j++)
            ibtnSingleCell[i][j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int r = 0, c = 0;
                    for(int ii = 0; ii < ROW_COUNT; ii++)
                    for(int jj = 0; jj < COLUMN_COUNT; jj++){
                        if(ibtnSingleCell[ii][jj] == v){
                            r = ii;  c = jj; break;
                        }
                    }

                    if(!bCellSelected[r][c]){
                        Log.i("tag", "get:" + Integer.parseInt(txtSingleCell[r][c].getText().toString()) + " ans:" + iNextAnswer);
                        if(Integer.parseInt(txtSingleCell[r][c].getText().toString()) == iNextAnswer){
                            imgCorrectMark[r][c].setVisibility(View.VISIBLE);
                            bCellSelected[r][c] = true;
                            iNextAnswer -= dataSet.iDistanceNumber;

                            if(iNextAnswer <= dataSet.iStartNumber - 10 * dataSet.iRowCount){
                                isRight = true;
                                checkAnswer();
                            }
                        }
                        else{
                            isRight = false;
                            checkAnswer();
                        }
                    }
                }
            });// end of button listener

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        txtDiscription.setText(dataSet.sDiscription);

        int iRowCount = dataSet.iRowCount, iCurrentNumber = dataSet.iStartNumber;
        for(int i = 0; i < iRowCount; i++){
            linearLineCell[i].setVisibility(View.VISIBLE);

            for(int j = 0; j < COLUMN_COUNT; j++) {
                txtSingleCell[i][j].setText("" + iCurrentNumber--);
                imgCorrectMark[i][j].setVisibility(View.INVISIBLE);
                bCellSelected[i][j] = false;
            }
        }

        for(int i = iRowCount; i < ROW_COUNT; i++){
            linearLineCell[i].setVisibility(View.GONE);
        }

        iNextAnswer = dataSet.iStartNumber - dataSet.iDistanceNumber;

        imgCorrectMark[0][0].setVisibility(View.VISIBLE);
        bCellSelected[0][0] = true;

        StartRecording();
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
        Intent intent = new Intent(this, ActStep0202.class);
        startActivity(intent);
    }

    public class Step0201DataSet {
        private final String arrDiscription[] = {"다음 수를 50에서 3씩 거꾸로 건너 띄며 세기.\n해당 숫자를 누르세요.",
                "다음 수를 50에서 5씩 거꾸로 건너 띄며 세기.\n해당 숫자를 누르세요.",
                "다음 수를 100에서 5씩 거꾸로 건너 띄며 세기.\n해당 숫자를 누르세요.",
                "다음 수를 100에서 7씩 거꾸로 건너 띄며 세기.\n해당 숫자를 누르세요.",
                "다음 수를 70에서 7씩 거꾸로 건너 띄며 세기.\n해당 숫자를 누르세요."};
        private final int arrStartNumber[] = {50, 50, 100, 100, 70};
        private final int arrRowCount[] = {3, 3, 2, 3, 3};
        private final int arrDistanceNumber[] = {3, 5, 5, 7, 7};

        public String sDiscription;
        public int iStartNumber;
        public int iRowCount;
        public int iDistanceNumber;

        public void setData(int iSeed) {
            sDiscription = arrDiscription[iSeed];
            iStartNumber = arrStartNumber[iSeed];
            iRowCount = arrRowCount[iSeed];
            iDistanceNumber = arrDistanceNumber[iSeed];
        }
    }
}
