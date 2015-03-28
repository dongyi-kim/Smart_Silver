package src.activities.Step01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

import cdmst.smartsilver.R;
import src.activities.FrameActivity;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0104 extends StageActivity {
    public static final int MAX_ROW_NUMBER = 2;
    public static final int MAX_COLUMN_NUMBER = 2;

    private FrameLayout frameGrid[][] = new FrameLayout[2][2];
    public ImageButton ibtnNumber[][] = new ImageButton[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private TextView txtNumber[][] = new TextView[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private TextView txtDiscription;

    private boolean isSelected[][] = new boolean[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    public final int iBtnValue[][] = new int[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private int iLevel = 0;
    public int iAnswerCount = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step04NumberSet numberSet = new Step04NumberSet();
    private Random rand = new Random();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_4);

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

                        if(iBtnValue[r][c] % 2 == (numberSet.isOdd? 1 : 0)) {
                            iAnswerCount++;
                            isRight = true;
                        }
                        else {
                            isRight = false;
                        }
                        checkAnswer();
                    }
                });
            } // end of button listener

        setQuestion(false);
    }


    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = 2 * iLevel + rand.nextInt(2);
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
        int iHeightMarginSum = 100;
        int iWidthMarginSum = 100;

        for(int i = 0; i < MAX_ROW_NUMBER; i++)
            for(int j = 0; j < MAX_COLUMN_NUMBER; j++){
                int t = rand.nextInt(iHeightMarginSum) + 1;
                int b = iHeightMarginSum - t;
                int l = rand.nextInt(iWidthMarginSum) + 1;
                int r = iHeightMarginSum - l;

                LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
    }


    public void checkAnswer(Object object){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    if(iLevel >= 3) goNext();
                    else {
                        iRetryCount = 0;
                        iLevel++;
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
        Intent intent = new Intent(this, ActStep0105.class);
        startActivity(intent);
    }
}
