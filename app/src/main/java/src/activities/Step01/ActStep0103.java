package src.activities.Step01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0103 extends StageActivity {
    public static final int MAX_ROW_NUMBER = 2;
    public static final int MAX_COLUMN_NUMBER = 2;

    private FrameLayout frameGrid[][] = new FrameLayout[2][2];
    public ImageButton ibtnNumber[][] = new ImageButton[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private TextView txtNumber[][] = new TextView[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private TextView txtDiscription;

    private boolean isSelected[][] = new boolean[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    public final int iBtnValue[][] = new int[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private int iStage = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0103NumberSet numberSet = new Step0103NumberSet();
    private Random rand = new Random();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_3);

        frameGrid[0][0] = (FrameLayout)findViewById(R.id.frame_grid_1_1);
        frameGrid[0][1] = (FrameLayout)findViewById(R.id.frame_grid_1_2);
        frameGrid[1][0] = (FrameLayout)findViewById(R.id.frame_grid_2_1);
        frameGrid[1][1] = (FrameLayout)findViewById(R.id.frame_grid_2_2);
        ibtnNumber[0][0] = (ImageButton)findViewById(R.id.btn_grid_1_1);
        ibtnNumber[0][1] = (ImageButton)findViewById(R.id.btn_grid_1_2);
        ibtnNumber[1][0] = (ImageButton)findViewById(R.id.btn_grid_2_1);
        ibtnNumber[1][1] = (ImageButton)findViewById(R.id.btn_grid_2_2);
        txtNumber[0][0] = (TextView)findViewById(R.id.txt_grid_1_1);
        txtNumber[0][1] = (TextView)findViewById(R.id.txt_grid_1_2);
        txtNumber[1][0] = (TextView)findViewById(R.id.txt_grid_2_1);
        txtNumber[1][1] = (TextView)findViewById(R.id.txt_grid_2_2);
        txtDiscription = (TextView)findViewById(R.id.txt_discription);

        //button listener
        for(int i = 0; i < MAX_ROW_NUMBER; i++)
            for(int j = 0; j < MAX_COLUMN_NUMBER; j++){
                ibtnNumber[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int r = 0, c = 0;
                        for(int ii = 0; ii < MAX_ROW_NUMBER; ii++)
                            for(int jj = 0; jj < MAX_COLUMN_NUMBER; jj++)
                                if(ibtnNumber[ii][jj] == v){
                                    r = ii; c = jj; break;
                                }

                        if(iBtnValue[r][c] % 2 == (numberSet.isOdd? 1 : 0)) isRight = true;
                        else isRight = false;
                        checkAnswer();
                    }
                });
            } // end of button listener

        setQuestion(false);
    }


    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = 2 * iStage + rand.nextInt(2);
        numberSet.setData(iRandomSeed);

        txtDiscription.setText(numberSet.isOdd? "다음 중 홀수를 찾아 누르세요." : "다음 중 짝수를 찾아 누르세요.");

        //delete all
        for(int i = 0; i < MAX_ROW_NUMBER; i++)
            for(int j = 0; j < MAX_COLUMN_NUMBER; j++){
                ibtnNumber[i][j].setVisibility(View.INVISIBLE);
                txtNumber[i][j].setVisibility(View.INVISIBLE);
                isSelected[i][j] = false;
            }

        //set button's position
        int iHeightMarginSum = (R.dimen.wp5) / 20000000;
        int iWidthMarginSum = (R.dimen.wp5) / 20000000;

        for(int i = 0; i < MAX_ROW_NUMBER; i++)
            for(int j = 0; j < MAX_COLUMN_NUMBER; j++){
                int t = rand.nextInt(iHeightMarginSum) + 1;
                int b = iHeightMarginSum - t;
                int l = rand.nextInt(iWidthMarginSum) + 1;
                int r = iHeightMarginSum - l;

                LinearLayout.LayoutParams gridParams = ( LinearLayout.LayoutParams)frameGrid[i][j].getLayoutParams();
                gridParams.setMargins(l, t, r, b);
                frameGrid[i][j].setLayoutParams(gridParams);
            }

        // draw buttons
        int iSelectCount = 0, iNumCount = numberSet.iNumCount;
        while(iSelectCount < iNumCount){
            int r = rand.nextInt(2), c = rand.nextInt(2);
            if(isSelected[r][c]) continue;

            isSelected[r][c] = true;
            iBtnValue[r][c] = numberSet.arrNumSet[iSelectCount];
            txtNumber[r][c].setText(Integer.toString(numberSet.arrNumSet[iSelectCount++]));

            ibtnNumber[r][c].setVisibility(View.VISIBLE);
            txtNumber[r][c].setVisibility(View.VISIBLE);
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
                    if(iStage >= 4) goNext();
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
        Intent intent = new Intent(this, ActStep0104.class);
        startActivity(intent);
    }

    public class Step0103NumberSet {
        private static final int MAX_SET_NUMBER = 8;

    /*If file I/O
    private static final boolean arrIsOdd[] = new boolean[MAX_SET_NUMBER];
    private static final int arrNumCount[] = new int[MAX_SET_NUMBER];
    private static final int[] arrNumSetList[] = new int[MAX_SET_NUMBER][];
    */

        //else
        private final boolean arrIsOdd[] = {true, true, false, false, true, true, true, false};
        private final int arrNumCount[] = {4, 2, 3, 2, 2, 3, 2, 3};
        private final int[] arrNumSetList[] = {{1, 2, 6, 8},
                {3, 2},
                {6, 7, 9},
                {39, 14},
                {58, 97},
                {48, 77, 52},
                {121, 150},
                {169, 126, 213}};

        public boolean isOdd;
        public int iNumCount;
        public int arrNumSet[] = new int[4];

        public Step0103NumberSet(){
            //If file I/O, read file and set data
        }

        public void setData(int iSeed){
            isOdd = arrIsOdd[iSeed];
            iNumCount = arrNumCount[iSeed];
            for(int i = 0; i < iNumCount; i++)
                arrNumSet[i] = arrNumSetList[iSeed][i];
        }
    }
}
