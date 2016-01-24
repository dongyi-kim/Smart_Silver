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

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0103 extends StageActivity {
    public static final int MAX_ROW_NUMBER = 2;
    public static final int MAX_COLUMN_NUMBER = 2;

    public static final int iSampleCount[] = {2, 3, 2, 3, 3};
    public static final int iBaseNumber[] = {1, 1, 10, 10, 100};
    public static final int iRandomRange[] = {5, 9, 30, 90, 200};

    private LinearLayout linearGrid[][] = new LinearLayout[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private LinearLayout linearRow[] = new LinearLayout[MAX_ROW_NUMBER];
    public ImageButton ibtnNumber[][] = new ImageButton[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    public TextView txtNumber[][] = new TextView[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private TextView txtDescription;

    private View emptyWidth[][] = new View[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];
    private View emptyHeight[][] = new View[MAX_ROW_NUMBER][MAX_COLUMN_NUMBER];

    public static final int iWidthBase[] = {5, 10};
    public static final int iWidthRange[] = {10, 10};

    private int iRetryCount = 0;
    public boolean isRight = false;
    public boolean bAnswerIsOdd = true;

    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_3);

        linearGrid[0][0] = (LinearLayout)findViewById(R.id.linear_grid_1_1);
        linearGrid[0][1] = (LinearLayout)findViewById(R.id.linear_grid_1_2);
        linearGrid[1][0] = (LinearLayout)findViewById(R.id.linear_grid_2_1);
        linearGrid[1][1] = (LinearLayout)findViewById(R.id.linear_grid_2_2);
        ibtnNumber[0][0] = (ImageButton)findViewById(R.id.ibtn_grid_1_1);
        ibtnNumber[0][1] = (ImageButton)findViewById(R.id.ibtn_grid_1_2);
        ibtnNumber[1][0] = (ImageButton)findViewById(R.id.ibtn_grid_2_1);
        ibtnNumber[1][1] = (ImageButton)findViewById(R.id.ibtn_grid_2_2);
        txtNumber[0][0] = (TextView)findViewById(R.id.txt_grid_1_1);
        txtNumber[0][1] = (TextView)findViewById(R.id.txt_grid_1_2);
        txtNumber[1][0] = (TextView)findViewById(R.id.txt_grid_2_1);
        txtNumber[1][1] = (TextView)findViewById(R.id.txt_grid_2_2);
        txtDescription = (TextView)findViewById(R.id.txt_description);

        linearRow[0] = (LinearLayout)findViewById(R.id.linear_row_1);
        linearRow[1] = (LinearLayout)findViewById(R.id.linear_row_2);

        emptyWidth[0][0] = (View)findViewById(R.id.empty_left_grid_1_1);
        emptyWidth[0][1] = (View)findViewById(R.id.empty_left_grid_1_2);
        emptyWidth[1][0] = (View)findViewById(R.id.empty_left_grid_2_1);
        emptyWidth[1][1] = (View)findViewById(R.id.empty_left_grid_2_2);
        emptyHeight[0][0] = (View)findViewById(R.id.empty_upper_grid_1_1);
        emptyHeight[0][1] = (View)findViewById(R.id.empty_upper_grid_1_2);
        emptyHeight[1][0] = (View)findViewById(R.id.empty_upper_grid_2_1);
        emptyHeight[1][1] = (View)findViewById(R.id.empty_upper_grid_2_2);

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

                        if((Integer.parseInt(txtNumber[r][c].getText().toString()) % 2) == (bAnswerIsOdd? 1 : 0)) isRight = true;
                        else isRight = false;

                        checkAnswer();
                    }
                });
            } // end of button listener

        setQuestion(false);
    }


    public synchronized void setQuestion(boolean isRetry, Object object){
        bAnswerIsOdd = (rand.nextInt(2) == 0 ? false : true);
        txtDescription.setText(bAnswerIsOdd? "다음 중 홀수를 찾아 누르세요." : "다음 중 짝수를 찾아 누르세요.");

        int iOddCount = 0, iEvenCount = 0;
        for(int i = 0; i < MAX_ROW_NUMBER; i++){
            boolean bSelected = rand.nextBoolean();

            linearRow[i].setVisibility(View.VISIBLE);
            for(int j = 0; j < MAX_COLUMN_NUMBER; j++){
                if(iOddCount + iEvenCount >= iSampleCount[iStage - 1]){
                    linearGrid[i][j].setVisibility(View.GONE);
                    continue;
                }

                int iRemainCount = MAX_ROW_NUMBER * MAX_COLUMN_NUMBER - (i * MAX_COLUMN_NUMBER + j);
                if(iOddCount + iEvenCount + iRemainCount <= iSampleCount[iStage - 1] || ((iSampleCount[iStage - 1] == 2 && bSelected) || rand.nextInt(1) == 1)){
                    boolean bOddPossible = true, bEvenPossible = true; bSelected = true;

                    if(iOddCount + iEvenCount == iSampleCount[iStage - 1] - 1){
                        if(bAnswerIsOdd && iOddCount== 0) bEvenPossible = false;
                        if(!bAnswerIsOdd && iEvenCount == 0) bOddPossible = false;
                    }
                    if(bAnswerIsOdd && iOddCount >= 1) bOddPossible = false;
                    else if(!bAnswerIsOdd && iEvenCount >= 1) bEvenPossible = false;

                    int iRandomNumber;
                    boolean bIsOdd;
                    while(true) {
                        iRandomNumber = iBaseNumber[iStage - 1] + rand.nextInt(iRandomRange[iStage - 1]);

                        bIsOdd = iRandomNumber % 2 == 1 ? true : false;
                        if(bIsOdd && bOddPossible) break;
                        if(!bIsOdd && bEvenPossible) break;
                    }

                    if(bIsOdd) iOddCount++;
                    else iEvenCount++;

                    txtNumber[i][j].setText("" + iRandomNumber);
                    linearGrid[i][j].setVisibility(View.VISIBLE);
                }
                else linearGrid[i][j].setVisibility(View.GONE);
            }

            if(iSampleCount[iStage - 1] == 2 && bSelected){
                linearRow[1 - i].setVisibility(View.GONE);
                break;
            }
        }

        for(int i = 0; i < MAX_ROW_NUMBER; i++){
            for(int j = 0; j < MAX_COLUMN_NUMBER; j++){
                int iWidthWeight = iWidthBase[j] + rand.nextInt(iWidthRange[j]);
                LinearLayout.LayoutParams emptyParam = (LinearLayout.LayoutParams) emptyWidth[i][j].getLayoutParams();
                emptyParam.weight = iWidthWeight / 100f;
                emptyWidth[i][j].setLayoutParams(emptyParam);

                int iHeightWeight = 5 + rand.nextInt(20);
                emptyParam = (LinearLayout.LayoutParams) emptyHeight[i][j].getLayoutParams();
                emptyParam.weight = iHeightWeight / 100f;
                emptyHeight[i][j].setLayoutParams(emptyParam);
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
                    iRetryCount = 0;

                    if(iStage > NUM_OF_STAGE) goNext();
                    else setQuestion(false);
                }
                else iRetryCount++;
            }
        });
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0104.class);
        startActivity(intent);
    }
}
