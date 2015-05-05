package src.activities.Step04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-27.
 */
public class ActStep0403 extends StageActivity {
    private ImageView imgHippo;
    private TextView txtAnswerDescription;
    private final TextView txtMarginTop[] = new TextView[5];
    private final TextView txtMarginLeft[] = new TextView[5];
    public final ImageButton ibtnAnswer[] = new ImageButton[5];
    public final TextView txtAnswer[] = new TextView[5];

    private final float fMaxMarginTop = 0.16f;
    private final float fMaxMarginLeft[] = {0.2f, 0.2f, 0.2f, 0.2f, 0.7f};

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;

    public Step0403DataSet dataSet = new Step0403DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_04_3);

        imgHippo = (ImageView)findViewById(R.id.img_hippo);
        txtAnswerDescription = (TextView)findViewById(R.id.txt_answer);
        txtMarginTop[0] = (TextView)findViewById(R.id.txt_margin_top_1);
        txtMarginTop[1] = (TextView)findViewById(R.id.txt_margin_top_2);
        txtMarginTop[2] = (TextView)findViewById(R.id.txt_margin_top_3);
        txtMarginTop[3] = (TextView)findViewById(R.id.txt_margin_top_4);
        txtMarginTop[4] = (TextView)findViewById(R.id.txt_margin_top_5);
        txtMarginLeft[0] = (TextView)findViewById(R.id.txt_margin_left_1);
        txtMarginLeft[1] = (TextView)findViewById(R.id.txt_margin_left_2);
        txtMarginLeft[2] = (TextView)findViewById(R.id.txt_margin_left_3);
        txtMarginLeft[3] = (TextView)findViewById(R.id.txt_margin_left_4);
        txtMarginLeft[4] = (TextView)findViewById(R.id.txt_margin_left_5);
        ibtnAnswer[0] = (ImageButton)findViewById(R.id.ibtn_answer_1);
        ibtnAnswer[1] = (ImageButton)findViewById(R.id.ibtn_answer_2);
        ibtnAnswer[2] = (ImageButton)findViewById(R.id.ibtn_answer_3);
        ibtnAnswer[3] = (ImageButton)findViewById(R.id.ibtn_answer_4);
        ibtnAnswer[4] = (ImageButton)findViewById(R.id.ibtn_answer_5);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);
        txtAnswer[2] = (TextView)findViewById(R.id.txt_answer_3);
        txtAnswer[3] = (TextView)findViewById(R.id.txt_answer_4);
        txtAnswer[4] = (TextView)findViewById(R.id.txt_answer_5);

        for(int i = 0; i < 5; i++)
            ibtnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iBtnIndex = 0;
                    while(ibtnAnswer[iBtnIndex] != v) iBtnIndex++;

                    if(Integer.parseInt(txtAnswer[iBtnIndex].getText().toString()) == iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        String sAnswerDescription = "" + dataSet.iAnswerCount[0] + " X " + dataSet.iAnswerCount[1];
        if(dataSet.iAnswerCount[2] != 1) sAnswerDescription += " X " + dataSet.iAnswerCount[2];

        txtAnswerDescription.setText(sAnswerDescription);
        imgHippo.setImageResource(dataSet.iPictureSource);
        iAnswer = dataSet.iAnswerCount[0] * dataSet.iAnswerCount[1] * dataSet.iAnswerCount[2];

        int iNextExample = iAnswer - rand.nextInt(4);
        if(iNextExample < 1) iNextExample = 1;
        for(int i = 0; i < 5; i++){
            txtAnswer[i].setText("" + (iNextExample + i));

            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams)txtMarginTop[i].getLayoutParams();
            param.weight = (float)rand.nextInt((int)(fMaxMarginTop * 100)) / 100;
            txtMarginTop[i].setLayoutParams(param);

            param = (LinearLayout.LayoutParams)txtMarginLeft[i].getLayoutParams();
            param.weight = (float)rand.nextInt((int)(fMaxMarginLeft[i] * 100)) / 100;
            txtMarginLeft[i].setLayoutParams(param);
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
                if(isRight || iRetryCount > 1){
                    iRetryCount = 0;
                    iStage++;
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
        Intent intent = new Intent(this, ActMain.class);
        startActivity(intent);
    }

    public class Step0403DataSet{
        private final int arrImageSource[][][] = new int[11][11][11];
        private final int arrAnswerCount[][] = {{7, 2, 1}, {8, 2, 1}, {3, 2, 2}, {4, 2, 2}, {5, 2, 2}};

        public final int iAnswerCount[] = new int[3];
        public int iPictureSource;

        public Step0403DataSet(){
            arrImageSource[7][2][1] = R.drawable.mult_7_2_waterhippo;
            arrImageSource[8][2][1] = R.drawable.mult_8_2_waterhippo;
            arrImageSource[3][2][2] = R.drawable.mult_3_2_2_waterhippo;
            arrImageSource[4][2][2] = R.drawable.mult_4_2_2_waterhippo;
            arrImageSource[5][2][2] = R.drawable.mult_5_2_2_waterhippo;
        }

        public void setData(int iSeed){
            iAnswerCount[0] = arrAnswerCount[iSeed][0];
            iAnswerCount[1] = arrAnswerCount[iSeed][1];
            iAnswerCount[2] = arrAnswerCount[iSeed][2];
            iPictureSource = arrImageSource[iAnswerCount[0]][iAnswerCount[1]][iAnswerCount[2]];
        }
    }
}