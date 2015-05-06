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
 * Created by Acka on 2015-05-06.
 */
public class ActStep0404 extends StageActivity {
    private ImageView imgSerlap;
    private TextView txtMarginTop;
    private TextView txtAnswerDescription;
    public final ImageButton ibtnAnswer[] = new ImageButton[3];
    public final TextView txtAnswer[] = new TextView[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0404DataSet dataSet = new Step0404DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_04_4);

        imgSerlap = (ImageView)findViewById(R.id.img_serlap);
        txtMarginTop = (TextView)findViewById(R.id.txt_margin_top);
        txtAnswerDescription = (TextView)findViewById(R.id.txt_answer);
        ibtnAnswer[0] = (ImageButton)findViewById(R.id.ibtn_answer_1);
        ibtnAnswer[1] = (ImageButton)findViewById(R.id.ibtn_answer_2);
        ibtnAnswer[2] = (ImageButton)findViewById(R.id.ibtn_answer_3);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);
        txtAnswer[2] = (TextView)findViewById(R.id.txt_answer_3);

        for(int i = 0; i < 3; i++)
            ibtnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iBtnIndex = 0;
                    while(ibtnAnswer[iBtnIndex] != v) iBtnIndex++;

                    if(Integer.parseInt(txtAnswer[iBtnIndex].getText().toString()) == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);


        imgSerlap.setImageResource(dataSet.iPictureSource);
        txtAnswerDescription.setText(dataSet.sAnswerDesciption);

        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams)txtMarginTop.getLayoutParams();
        param.weight = dataSet.fMarginTop;
        txtMarginTop.setLayoutParams(param);

        for(int i = 0; i < 3; i++)
            txtAnswer[i].setText("" + dataSet.iExample[i]);

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

    public class Step0404DataSet{
        private final String arrAnswerDescription[] = {"¹ÙÁö 3¹ú¾¿ 3´Ü", "¾ç¸» 6Â¦ 2ÁÙ", "Æ¼¼ÅÃ÷ 7Àå¾¿ 3ÁÙ", "¾çÆÄ 9°³ 4¹­À½", "ÆÄ 10°³Â¥¸® 3´Ü"};
        private final int arrImageSource[] = {R.drawable.serlap1_floor3, R.drawable.serlap1_floor2, R.drawable.serlap1_floor4, R.drawable.serlap2_floor3, R.drawable.serlap2_floor1};
        private final float arrMarginTop[] = {0.3f, 0.4f, 0.2f, 0.15f, 0.4f};
        private final int arrAnswer[] = {9, 12, 21, 36, 30};
        private final int arrExample[][] = {{7, 8, 9}, {9, 11, 12}, {28, 21, 14}, {27, 36, 45}, {20, 30, 40}};

        public final int iExample[] = new int[3];
        public int iPictureSource;
        public float fMarginTop;
        public int iAnswer;
        public String sAnswerDesciption;

        public void setData(int iSeed){
            iExample[0] = arrExample[iSeed][0];
            iExample[1] = arrExample[iSeed][1];
            iExample[2] = arrExample[iSeed][2];
            fMarginTop = arrMarginTop[iSeed];
            iPictureSource = arrImageSource[iSeed];
            iAnswer = arrAnswer[iSeed];
            sAnswerDesciption = arrAnswerDescription[iSeed];
        }
    }
}