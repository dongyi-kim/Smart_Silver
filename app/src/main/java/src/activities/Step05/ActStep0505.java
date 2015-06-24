package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-06-24.
 */
public class ActStep0505 extends StageActivity {
    private TextView txtDescription;
    public final ImageButton ibtnAnswer[] = new ImageButton[2];
    public final TextView txtAnswerDescription[] = new TextView[2];

    public int iAnswerIndex = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0505DataSet dataSet = new Step0505DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_5);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        ibtnAnswer[0] = (ImageButton)findViewById(R.id.ibtn_answer_1);
        ibtnAnswer[1] = (ImageButton)findViewById(R.id.ibtn_answer_2);
        txtAnswerDescription[0] = (TextView)findViewById(R.id.txt_answer_description_1);
        txtAnswerDescription[1] = (TextView)findViewById(R.id.txt_answer_description_2);

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
            ibtnAnswer[i].setImageResource(dataSet.iImageSource[i]);
            txtAnswerDescription[i].setText(dataSet.sAnswerDescription[i]);
        }
        iAnswerIndex = (dataSet.iWholePrice[0] / dataSet.iImageCount[0] < dataSet.iWholePrice[1] / dataSet.iImageCount[1]? 0 : 1);

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

    public class Step0505DataSet{
        //private enum ePictureType {goodgam, hongshi, apple, flower, waterhippo, cherry};
        private final int songpyeon = 0, hobakjeon = 1, apple = 2;

        private final String arrDescriptionType[] = {"송편", "호박전", "사과"};
        private final String arrUnit[] = {"접시", "접시", "묶음", "접시", "개"};
        private final int arrImageSource[][] = new int[4][11];
        private final int arrImageCount[][] = {{1, 2}, {2, 3}, {3, 4}, {2, 5}, {9, 8}};
        private final int arrImageType[] = {songpyeon, hobakjeon, apple, songpyeon, apple};
        private final int arrWholePrice[][] = {{1200, 1800}, {1200, 2100}, {2400, 3600}, {1800, 3500}, {7200, 7200}};

        public final int iImageCount[] = new int[2];
        public final int iImageSource[] = new int[2];
        public final int iWholePrice[] = new int[2];
        public String sAnswerDescription[] = new String[2];
        public String sDescription;
        public int iImageType;

        public Step0505DataSet(){
            arrImageSource[songpyeon][1] = R.drawable.img_songpyeon_1;
            arrImageSource[songpyeon][2] = R.drawable.img_songpyeon_2;
            arrImageSource[songpyeon][3] = R.drawable.img_songpyeon_2_small;
            arrImageSource[songpyeon][5] = R.drawable.img_songpyeon_5;
            arrImageSource[hobakjeon][2] = R.drawable.img_hobakjeon_2;
            arrImageSource[hobakjeon][3] = R.drawable.img_hobakjeon_3;
            arrImageSource[apple][3] = R.drawable.img_apple_3;
            arrImageSource[apple][4] = R.drawable.img_apple_4;
            arrImageSource[apple][8] = R.drawable.img_apple_8;
            arrImageSource[apple][9] = R.drawable.img_apple_9;
        }

        public void setData(int iSeed){
            iImageType = arrImageType[iSeed];
            sDescription = " 노인학교에서 가을축제 준비를 하기 위해 시장에 갔습니다.\n값이 더 저렴한 " + arrDescriptionType[iImageType] + "의 그림을 누르세요!";

            for(int i = 0; i < 2; i++){
                iImageCount[i] = arrImageCount[iSeed][i];
                iImageSource[i] = arrImageSource[iImageType][iImageCount[i]];
                if(iSeed == 3 && i == 0) iImageSource[i] = arrImageSource[iImageType][iImageCount[i] + 1];
                iWholePrice[i] = arrWholePrice[iSeed][i];

                sAnswerDescription[i] = iImageCount[i] + arrUnit[iSeed] + " " + iWholePrice[i] + "원";
            }
        }
    }
}
