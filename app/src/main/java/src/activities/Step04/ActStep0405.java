package src.activities.Step04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.activities.Step05.ActStep0502;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-05-13.
 */
public class ActStep0405 extends StageActivity {
    private TextView txtDescription;
    private final ImageView imgPicture[] = new ImageView[2];
    public final ImageButton ibtnAnswer[] = new ImageButton[2];
    public final TextView txtAnswer[] = new TextView[2];

    public int iAnswerIndex = 0;
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
        ibtnAnswer[0] = (ImageButton)findViewById(R.id.ibtn_answer_1);
        ibtnAnswer[1] = (ImageButton)findViewById(R.id.ibtn_answer_2);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);

        for(int i = 0; i < 2; i++)
            ibtnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iSelectIndex = (v == ibtnAnswer[0]? 0 : 1);
                    if(iSelectIndex == iAnswerIndex) isRight = true;
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

        for(int i = 0; i < 2; i++) {
            imgPicture[i].setImageResource(dataSet.iPictureSource[i]);
            txtAnswer[i].setText(dataSet.sImageDescription[i]);
        }
        iAnswerIndex = (dataSet.iPictureCount[0][0] * dataSet.iPictureCount[0][1] >= dataSet.iPictureCount[1][0] * dataSet.iPictureCount[1][1]? 0 : 1);

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
        Intent intent = new Intent(this, ActStep0502.class);
        startActivity(intent);
    }

    public class Step0405DataSet{
        //private enum ePictureType {goodgam, hongshi, apple, flower, waterhippo, cherry};
        private final int tissue = 0, garlic = 1, napkin = 2;

        private final String arrDescriptionType[] = {" 노인학교 가을 축제를 준비합니다. 화장지를 사러 마트에 갔습니다.\n더 많은 화장지를 찾아 단추를 누르세요!",
                " 단양에 가서 마늘을 사려고 합니다. 어느 마늘이 더 많을까요?\n더 많은 것을 찾아 단추를 누르세요!",
                " 딸네 가게에서 쓸 냅킨을 사러 동대문에 갔습니다.\n더 많은 냅킨을 찾아 단추를 누르세요!"};
        private final String arrDescriptionUnit[][] = {{"개씩", "묶음"}, {"쪽짜리", "개"}, {"개짜리", "묶음"}};
        private final int arrImageSource[][][] = new int[4][11][11];
        private final int arrPictureCount[][][] = {{{3, 3}, {2, 6}}, {{5, 3}, {3, 8}}, {{7, 9}, {9, 5}}, {{8, 7}, {9, 5}}, {{10, 4}, {5, 9}}};
        private final int arrImageType[] = {tissue, tissue, garlic, garlic, napkin};

        public final int iPictureCount[][] = new int[2][2];
        public final int iPictureSource[] = new int[2];
        public String sImageDescription[] = new String[2];
        public String sDescription;
        public int iImageType;

        public Step0405DataSet(){
            arrImageSource[tissue][3][3] = R.drawable.mult_3_3_tissue;
            arrImageSource[tissue][2][6] = R.drawable.mult_2_6_tissue;
            arrImageSource[tissue][5][3] = R.drawable.mult_5_3_tissue;
            arrImageSource[tissue][3][8] = R.drawable.mult_3_8_tissue;
            arrImageSource[garlic][7][9] = R.drawable.mult_7_9_garlic;
            arrImageSource[garlic][8][7] = R.drawable.mult_8_7_garlic;
            arrImageSource[garlic][9][5] = R.drawable.mult_9_5_garlic;
            arrImageSource[napkin][10][4] = R.drawable.mult_10_4_napkin;
            arrImageSource[napkin][5][9] = R.drawable.mult_5_9_napkin;
        }

        public void setData(int iSeed){
            iImageType = arrImageType[iSeed];
            sDescription = arrDescriptionType[iImageType];

            for(int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++)
                    iPictureCount[i][j] = arrPictureCount[iSeed][i][j];
                iPictureSource[i] = arrImageSource[iImageType][iPictureCount[i][0]][iPictureCount[i][1]];
                sImageDescription[i] = arrPictureCount[iSeed][i][0] + arrDescriptionUnit[iImageType][0] + "\n"
                        + arrPictureCount[iSeed][i][1] + arrDescriptionUnit[iImageType][1];
            }
        }
    }
}