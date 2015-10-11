package src.activities.Step04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.activities.Step05.ActStep0501;
import src.activities.Step05.ActStep0502;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-05-13.
 */
public class ActStep0405 extends StageActivity {
    private TextView txtDescription;
    private final ImageView imgPicture[] = new ImageView[2];
    public final Button btnAnswer[] = new Button[2];
    public final TextView txtAnswer[] = new TextView[2];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0405DataSet dataSet = new Step0405DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_04_5);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        imgPicture[0] = (ImageView)findViewById(R.id.img_picture_1);
        imgPicture[1] = (ImageView)findViewById(R.id.img_picture_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);


        for(int i = 0; i < 2; i++) {
            btnAnswer[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent motionEvent)
                {
                    if (motionEvent.getPointerCount() < 1)
                    {
                        int iSelectIndex = (v == btnAnswer[0] ? 0 : 1);
                        if (dataSet.bIsBig[iSelectIndex]) isRight = true;
                        else isRight = false;
                        checkAnswer();
                    }
                    return false;
                }
            });
        }

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        for(int i = 0; i < 2; i++){
            imgPicture[i].setImageResource(dataSet.iImageResource[i]);
            txtAnswer[i].setText(dataSet.sCountDescription[i]);
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
        Intent intent = new Intent(this, ActStep0501.class);
        startActivity(intent);
    }

    public class Step0405DataSet{
        private final String arrDescription[] ={"노인학교 가을 축제에 필요한 화장지를 사러\n마트에 갔습니다. 더 많은 화장지를 찾아 누르세요.",
                "단양에 가서 마늘을 사려고 합니다. 어느 마늘이\n더 많을까요? 더 많은 것을 찾아 누르세요.",
                "딸네 가게에서 쓸 냅킨을 사러 동대문에 갔습니다.\n더 많은 냅킨을 찾아 누르세요."};
        private final String arrCountUnit[][] = {{"개씩", "쪽짜리", "개씩"}, {"묶음", "개", "묶음"}};
        private final int arrImageCount[][][][] = {{{{2, 4}, {3, 3}, {4, 3}, {4, 4}, {5, 3}, {5, 4}},
                {{2, 8}, {2, 9}, {4, 2}, {4, 3}, {5, 2}, {5, 3}}},
                {{{7, 4}, {7, 6}, {7 ,8}}, {{9, 4}, {9, 5}, {9, 6}}},
                {{{10, 3}, {10, 4}, {10, 5}}, {{5, 7}, {5, 8}, {5, 9}}}};
        private final int arrImageSource[][][] = {{{R.drawable.img_mult_tissue1_2_4, R.drawable.img_mult_tissue1_3_3, R.drawable.img_mult_tissue1_4_3, R.drawable.img_mult_tissue1_4_4, R.drawable.img_mult_tissue1_5_3, R.drawable.img_mult_tissue1_5_4},
                {R.drawable.img_mult_tissue2_2_8, R.drawable.img_mult_tissue2_2_9, R.drawable.img_mult_tissue2_4_2, R.drawable.img_mult_tissue2_4_3, R.drawable.img_mult_tissue2_5_2, R.drawable.img_mult_tissue2_5_3}},
                {{R.drawable.img_mult_onion1_4, R.drawable.img_mult_onion1_6, R.drawable.img_mult_onion1_8}, {R.drawable.img_mult_onion2_4, R.drawable.img_mult_onion2_5, R.drawable.img_mult_onion2_6}},
                {{R.drawable.img_mult_napkin1_3, R.drawable.img_mult_napkin1_4, R.drawable.img_mult_napkin1_5}, {R.drawable.img_mult_napkin2_7, R.drawable.img_mult_napkin2_8, R.drawable.img_mult_napkin2_9}}};

        public String txtDescription;
        public String sCountDescription[] = new String[2];
        public int iImageResource[] = new int[2];
        public boolean bIsBig[] = new boolean[2];

        public void setData(int iStage) {
            int iStageType = iStage / 2;
            int iMaxSetCount = arrImageCount[iStageType][0].length;
            int iSeed[] = {(1 - (iStage % 2)) * (iMaxSetCount / 2) + rand.nextInt((iMaxSetCount + 1) / 2), 0};
            int iLeftAmount = arrImageCount[iStageType][0][iSeed[0]][0] * arrImageCount[iStageType][0][iSeed[0]][1];
            int iTolerance = Math.min(arrImageCount[iStageType][0][iSeed[0]][0], arrImageCount[iStageType][0][iSeed[0]][1]);
            int iRightAmout = 0, iDifferent = 0;

            txtDescription = arrDescription[iStageType];

            if(iStageType == 0 && iSeed[0] == 0){
                iSeed[1] = 3;
                iRightAmout = arrImageCount[iStageType][1][iSeed[1]][0] * arrImageCount[iStageType][1][iSeed[1]][1];
            }
            else{
                do {
                    do{ iSeed[1] = rand.nextInt(iMaxSetCount); } while(arrImageCount[iStageType][0][iSeed[0]][0] != arrImageCount[iStageType][1][iSeed[1]][0]
                            && arrImageCount[iStageType][0][iSeed[0]][0] != arrImageCount[iStageType][1][iSeed[1]][1]
                            && arrImageCount[iStageType][0][iSeed[0]][1] != arrImageCount[iStageType][1][iSeed[1]][0]
                            && arrImageCount[iStageType][0][iSeed[0]][1] != arrImageCount[iStageType][1][iSeed[1]][1]);

                    iRightAmout = arrImageCount[iStageType][1][iSeed[1]][0] * arrImageCount[iStageType][1][iSeed[1]][1];
                    iDifferent = Math.abs(iLeftAmount - iRightAmout);
                } while (iDifferent != 0 && iDifferent <= iTolerance);
            }

            bIsBig[0] = iLeftAmount > iRightAmout;
            bIsBig[1] = iRightAmout > iLeftAmount;

            for(int i = 0; i < 2; i++){
                iImageResource[i] = arrImageSource[iStageType][i][iSeed[i]];
                sCountDescription[i] = "" + arrImageCount[iStageType][i][iSeed[i]][0] + arrCountUnit[iStageType][0] + " " + arrImageCount[iStageType][i][iSeed[i]][1] + arrCountUnit[iStageType][1];
            }
        }
    }
}
