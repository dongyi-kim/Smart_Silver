package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import src.viewes.DrawView;

/**
 * Created by Acka on 2015-05-17.
 */
public class ActStep0502 extends StageActivity {
    private DrawView mDrawingView;
    private LinearLayout mDrawingPad;

    private TextView txtDescription;
    private ImageView imgFruits;
    public final Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0502DataSet dataSet = new Step0502DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_2);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        imgFruits = (ImageView)findViewById(R.id.img_many_fruits);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        mDrawingView = new DrawView(this);
        mDrawingView.setColorCode(0xFFFFFFFF);

        mDrawingPad=(LinearLayout)findViewById(R.id.draw_field_0502);
        mDrawingPad.addView(mDrawingView);

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(((Button) v).getText().toString()) == dataSet.iAnswer)
                        isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        txtDescription.setText(dataSet.sDescription);
        imgFruits.setImageResource(dataSet.iFruitImage);

        int iNextExample = dataSet.iAnswer - rand.nextInt(2);
        if(iNextExample < 0) iNextExample = 0;

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));

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
                    mDrawingView.clearView();
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
        Intent intent = new Intent(this, ActStep0503.class);
        startActivity(intent);
    }

    public class Step0502DataSet {
        //private enum ePictureType {goodgam, hongshi, apple, flower, waterhippo, cherry};
        private final int strawberry = 0, gam = 1, pear = 2, apple = 3;

        private final String arrDescription[] = {" 평생학교 행복반에서 간식으로 딸기를 두 개씩 나누어 먹으려고 합니다. 몇 명이 먹을 수 있습니까? 손가락으로 두 개씩 묶어보세요!",
                " 평생학교 지혜반에서 간식으로 감을 네 개씩 나누어 먹으려고 합니다. 몇 명이 먹을 수 있습니까? 손가락으로 네 개씩 묶어보세요!",
                " 평생학교 소망반에서 간식으로 반쪽 짜리 배를 세 개씩 나누어 먹으려고 합니다. 몇 명이 먹을 수 있습니까? 손가락으로 세 개씩 묶어보세요!",
                " 평생학교 소망반에서 간식으로 사과를 여섯 개씩 나누어 가려고 합니다. 몇 명이 나눌 수 있습니까? 손가락으로 여섯 개씩 묶어보세요!",
                " 평생학교 지혜반에서 간식으로 사과를 여덟 개씩 나누어 가려고 합니다. 몇 명이 나눌 수 있습니까? 손가락으로 여덟 개씩 묶어보세요!"};
        private final int arrFruitType[] = {strawberry, gam, pear, apple, apple};
        private final int arrFruitCount[] = {12, 16, 15, 30, 32};
        private final int arrShareCount[] = {2, 4, 3, 6, 8};
        private final int arrImageSource[][][] = new int[5][100][10];

        public String sDescription;
        public int iFruitType;
        public int iFruitCount;
        public int iShareCount;
        public int iFruitImage;
        public int iAnswer;

        public Step0502DataSet() {
            arrImageSource[strawberry][12][2] = R.drawable.strawberry_12;
            arrImageSource[gam][16][4] = R.drawable.gam_16;
            arrImageSource[pear][15][3] = R.drawable.pear_15;
            arrImageSource[apple][30][6] = R.drawable.apple_30;
            arrImageSource[apple][32][8] = R.drawable.apple_32;
        }

        public void setData(int iSeed) {
            iFruitType = arrFruitType[iSeed];
            sDescription = arrDescription[iSeed];
            iFruitCount = arrFruitCount[iSeed];
            iShareCount = arrShareCount[iSeed];
            iFruitImage = arrImageSource[iFruitType][iFruitCount][iShareCount];

            iAnswer = iFruitCount / iShareCount;
        }
    }
}