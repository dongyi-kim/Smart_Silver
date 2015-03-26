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
public class ActStep0103 extends FrameActivity {

    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 5;
    private static final int NUM_OF_STAGE = 3;
    private static final int SHAPE_COUNT[] = {5, 10, 20, 20, 20};

    private LinearLayout linearDrawfield;
    private LinearLayout linearDrawfieldRow[] = new LinearLayout[ROW_COUNT];
    public final ImageButton ibtnShape[][] = new ImageButton[ROW_COUNT][COLUMN_COUNT];
    public final TextView txtShape[][] = new TextView[ROW_COUNT][COLUMN_COUNT];
    public final Button btnAnswer[] = new Button[3];
    public int iAnswerValue[] = new int[3];
    private TextView txtDiscription;

    private int iLevel = 0;
    public int iShapeCount = 0;
    public int iAnswerCount = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

            txtShape[i][0] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_1));
            txtShape[i][1] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_2));
            txtShape[i][2] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_3));
            txtShape[i][3] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_4));
            txtShape[i][4] = (TextView)(linearDrawfieldRow[i].findViewById(R.id.txt_col_5));
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

                    if(iAnswerValue[x] == iShapeCount){
                        isRight = true;
                        endFunction();
                    }
                    else{
                        isRight = false;
                        endFunction();
                    }
                }
            });// end of button listener

        setNewLevel(iLevel);
    }

    private void setNewLevel(int iLevel){
        iShapeCount = rand.nextInt(SHAPE_COUNT[iLevel]) + 1;
        int iFirstAnsNumber = iShapeCount - rand.nextInt(3);
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = 1;
        for(int i = 0; i < 3; i++){
            iAnswerValue[i] = iFirstAnsNumber++;
        }

        drawScreen(iShapeCount);
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

        for(int i = 0; i < 3; i++){
            btnAnswer[i].setText("" + iAnswerValue[i]);
        }
    }

    public void endFunction(){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iAnswerCount++;
                    if(iLevel < NUM_OF_STAGE)
                    {
                        setNewLevel(++iLevel);
                    }else
                    {  // go next stage
                        Intent intent = new Intent(  ((DlgResultMark)dialog).getContext(), ActStep0104.class);
                        startActivity(intent);
                    }
                }
                else{
                    iRetryCount++;
                    iAnswerCount--;
                }
            }
        });
    }

}
