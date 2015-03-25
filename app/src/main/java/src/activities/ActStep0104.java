package src.activities;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import java.util.Random;

import cdmst.smartsilver.R;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0104 extends FrameActivity {

    public static final int ROW_COUNT = 5;
    public static final int COLUMN_COUNT = 5;
    public static final int NUM_OF_STAGE = 5;

    private LinearLayout linearDrawfield;
    private LinearLayout linearDrawfieldRow[] = new LinearLayout[ROW_COUNT];
    public final ImageButton ibtnNumber[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final TextView txtNumber[][] = new TextView[ROW_COUNT][COLUMN_COUNT];
    private TextView txtDiscription;

    private boolean isSelected[][] = new boolean[ROW_COUNT][COLUMN_COUNT];
    public final int iBtnValue[][] = new int[ROW_COUNT][COLUMN_COUNT];
    public int iAnswerCount = 0;
    public boolean isOdd = true;
    public int iNumCount = 0;
    public int iCorrectCount = 0;
    private int iStageCount = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_4);

        linearDrawfield = (LinearLayout)findViewById(R.id.drawfield);
        linearDrawfieldRow[0] = (LinearLayout)findViewById(R.id.drawfield_row_1);
        linearDrawfieldRow[1] = (LinearLayout)findViewById(R.id.drawfield_row_2);
        linearDrawfieldRow[2] = (LinearLayout)findViewById(R.id.drawfield_row_3);
        linearDrawfieldRow[3] = (LinearLayout)findViewById(R.id.drawfield_row_4);
        linearDrawfieldRow[4] = (LinearLayout)findViewById(R.id.drawfield_row_5);
        for(int i = 0; i < ROW_COUNT; i++){
            ibtnNumber[i][0] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_1));
            ibtnNumber[i][1] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_2));
            ibtnNumber[i][2] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_3));
            ibtnNumber[i][3] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_4));
            ibtnNumber[i][4] = (ImageButton)(linearDrawfieldRow[i].findViewById(R.id.btn_col_5));

            txtNumber[i][0] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_1));
            txtNumber[i][1] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_2));
            txtNumber[i][2] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_3));
            txtNumber[i][3] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_4));
            txtNumber[i][4] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_5));
        }
        txtDiscription = (TextView)findViewById(R.id.txt_discription);


        //button listener
        for(int i = 0; i < ROW_COUNT; i++)
            for(int j = 0; j < COLUMN_COUNT; j++){
                ibtnNumber[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int r = 0, c = 0;
                        for(int ii = 0; ii < ROW_COUNT; ii++)
                            for(int jj = 0; jj < COLUMN_COUNT; jj++)
                                if(ibtnNumber[ii][jj] == v){
                                    r = ii; c = jj; break;
                                }

                        if(iBtnValue[r][c] % 2 == (isOdd? 1 : 0)){
                            iCorrectCount++;
                            ibtnNumber[r][c].setVisibility(View.INVISIBLE);
                            txtNumber[r][c].setVisibility(View.INVISIBLE);

                            if(iCorrectCount == iAnswerCount){
                                isRight = true;
                                endFunction();
                            }
                        }
                        else{
                            isRight = false;
                            endFunction();
                        }
                    }
                });
            } // end of button listener

        setNewScreen();


    }

    private void setNewScreen(){
        iAnswerCount = iCorrectCount = iRetryCount = 0;
        iNumCount = rand.nextInt(6) + 2;
        setDiscription();
        getNumbers(iNumCount);
        setNumberView();
    }


    private void setDiscription(){
        isOdd = rand.nextBoolean();
        if(isOdd) txtDiscription.setText("다음 중 홀수를 찾아 누르세요.");
        else txtDiscription.setText("다음 중 짝수를 찾아 누르세요.");
    }

    private void getNumbers(int iNumCount){
        int cnt = 0;

        for(int i = 0; i < ROW_COUNT; i++)
            for(int j = 0; j < COLUMN_COUNT; j++){
                isSelected[i][j] = false;
            }

        while(cnt < iNumCount){
            int i = rand.nextInt(ROW_COUNT);
            int j = rand.nextInt(COLUMN_COUNT);
            if(isSelected[i][j]) continue;

            int x = rand.nextInt(100);
            isSelected[i][j] = true;
            iBtnValue[i][j] = x;
            txtNumber[i][j].setText(Integer.toString(x));
            if(x % 2 == (isOdd? 1 : 0)) iAnswerCount++;
            cnt++;
        }

        if(iAnswerCount == 0) getNumbers(iNumCount);
    }

    private void setNumberView(){
        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < COLUMN_COUNT; j++){
                if(isSelected[i][j]){
                    ibtnNumber[i][j].setVisibility(View.VISIBLE);
                    txtNumber[i][j].setVisibility(View.VISIBLE);
                }
                else {
                    ibtnNumber[i][j].setVisibility(View.INVISIBLE);
                    txtNumber[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void endFunction(){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    setNewScreen();
                    iStageCount++;
                }
                else{
                    iCorrectCount = 0;
                    iRetryCount++;
                    setNumberView();
                }
            }
        });
    }

}
