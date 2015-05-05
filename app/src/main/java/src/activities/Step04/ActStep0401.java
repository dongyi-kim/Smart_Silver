package src.activities.Step04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-27.
 */
public class ActStep0401 extends StageActivity{
    private final ImageView imgPicture[] = new ImageView[2];
    public final ImageButton ibtnAnswer[] = new ImageButton[2];
    public final TextView txtAnswer[] = new TextView[2];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0401DataSet dataSet = new Step0401DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_04_1);

        imgPicture[0] = (ImageView)findViewById(R.id.img_picture_1);
        imgPicture[1] = (ImageView)findViewById(R.id.img_picture_2);
        ibtnAnswer[0] = (ImageButton)findViewById(R.id.ibtn_answer_1);
        ibtnAnswer[1] = (ImageButton)findViewById(R.id.ibtn_answer_2);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);

        for(int i = 0; i < 2; i++)
            ibtnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iSelectIndex = (v == ibtnAnswer[0]? 0 : 1);
                    if(dataSet.iAnswerCount[iSelectIndex][0] == dataSet.iPictureCount[iSelectIndex][0] &&
                            dataSet.iAnswerCount[iSelectIndex][1] == dataSet.iPictureCount[iSelectIndex][1]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        for(int i = 0; i < 2; i++){
            imgPicture[i].setImageResource(dataSet.iPictureSource[i]);

            String sAnswer = "";
            sAnswer += dataSet.iAnswerCount[i][0];
            if(dataSet.isFlower[i]) sAnswer += "송이씩\n";
            else sAnswer += "개씩\n";
            sAnswer += dataSet.iAnswerCount[i][1] + "묶음";

            txtAnswer[i].setText(sAnswer);
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
        Intent intent = new Intent(this, ActStep0402.class);
        startActivity(intent);
    }

    public class Step0401DataSet{
        //private enum ePictureType {goodgam, hongshi, apple, flower, waterhippo, cherry};
        private final int goodgam = 0, hongshi = 1, apple = 2, flower = 3, waterhippo = 4, cherry = 5;

        private final int arrImageSource[][][] = new int[26][11][11];
        private final int arrPictureCount[][][] = {{{2, 2}, {5, 2}},
                {{4, 3}, {5, 2}},
                {{4, 1}, {5, 2}},
                {{3, 6}, {2, 9}},
                {{7, 2}, {5, 3}}};
        private final int arrAnswerCount[][][] = {{{5, 2}, {5, 2}},
                {{4, 3}, {5, 3}},
                {{4, 2}, {5, 2}},
                {{3, 6}, {2, 6}},
                {{7, 3}, {5, 3}}};
        private final int arrImageType[][] = {{goodgam, hongshi}, {apple, hongshi}, {apple, flower}, {apple, cherry}, {waterhippo, flower}};

        public final int iPictureCount[][] = new int[2][2];
        public final int iAnswerCount[][] = new int[2][2];
        public final int iPictureSource[] = new int[2];
        public final boolean isFlower[] = new boolean[2];

        public Step0401DataSet(){
            arrImageSource[goodgam][2][2] = R.drawable.mult_2_2_goodgam;
            arrImageSource[hongshi][5][2] = R.drawable.mult_5_2_hongshi;
            arrImageSource[apple][4][1] = R.drawable.mult_4_1_apple;
            arrImageSource[apple][4][3] = R.drawable.mult_4_3_apple;
            arrImageSource[apple][3][6] = R.drawable.mult_3_6_apple;
            arrImageSource[flower][5][2] = R.drawable.mult_5_2_flower;
            arrImageSource[flower][5][3] = R.drawable.mult_5_3_flower;
            arrImageSource[waterhippo][7][2] = R.drawable.mult_7_2_waterhippo;
            arrImageSource[cherry][2][9] = R.drawable.mult_2_9_cherry;
        }

        public void setData(int iSeed){
            for(int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    iPictureCount[i][j] = arrPictureCount[iSeed][i][j];
                    iAnswerCount[i][j] = arrAnswerCount[iSeed][i][j];
                }

                iPictureSource[i] = arrImageSource[arrImageType[iSeed][i]][iPictureCount[i][0]][iPictureCount[i][1]];
                if(arrImageType[iSeed][i] == flower) isFlower[i] = true;
            }
        }
    }
}
