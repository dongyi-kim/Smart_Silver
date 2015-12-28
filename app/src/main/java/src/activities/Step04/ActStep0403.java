package src.activities.Step04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    public final Button btnAnswer[] = new Button[2];

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
        txtAnswerDescription = (TextView)findViewById(R.id.txt_answer_description);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);

        for(int i = 0; i < 2; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(Integer.parseInt(((Button)v).getText().toString()) == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtAnswerDescription.setText(dataSet.sAnswerDescription);
        imgHippo.setImageResource(dataSet.iPictureSource);

        if(rand.nextBoolean()){
            btnAnswer[0].setText("" + dataSet.iAnotherExample);
            btnAnswer[1].setText("" + dataSet.iAnswer);
        }
        else{
            btnAnswer[0].setText("" + dataSet.iAnswer);
            btnAnswer[1].setText("" + dataSet.iAnotherExample);
        }

        StartRecording();
    }

    public synchronized void checkAnswer(Object o){
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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0404.class);
        startActivity(intent);
    }

    public class Step0403DataSet{
        private final int arrAnswerCount[][] = {{3, 2}, {4, 2}, {5, 2}, {7, 2}, {8, 2}, {5, 3}, {3, 4}, {5, 4}, {8, 3}, {3, 2, 2}, {4, 2, 2}, {5, 2, 2}, {2, 3, 3}};
        private final int arrImageSource[] = {R.drawable.img_mult_hippo_3_2, R.drawable.img_mult_hippo_4_2, R.drawable.img_mult_hippo_5_2, R.drawable.img_mult_hippo_7_2,
                R.drawable.img_mult_hippo_8_2, R.drawable.img_mult_hippo_5_3, R.drawable.img_mult_hippo_3_4, R.drawable.img_mult_hippo_5_4, R.drawable.img_mult_hippo_8_3,
                R.drawable.img_mult_hippo_2_3_2, R.drawable.img_mult_hippo_2_4_2, R.drawable.img_mult_hippo_2_5_2, R.drawable.img_mult_hippo_3_2_3};

        public String sAnswerDescription;
        public int iPictureSource;
        public int iAnotherExample;
        public int iAnswer;

        public void setData(int iStage){
            int iSeed = (iStage > 3 ? (9 + (iStage - 4) * 2 + rand.nextInt(2)) : ((iStage - 1) * 3 + rand.nextInt(3)));
            iPictureSource = arrImageSource[iSeed];
            sAnswerDescription = "" + arrAnswerCount[iSeed][0] + " × " + arrAnswerCount[iSeed][1];
            iAnswer = arrAnswerCount[iSeed][0] * arrAnswerCount[iSeed][1];

            if(iStage > 3){
                sAnswerDescription += " × " + arrAnswerCount[iSeed][2];
                iAnswer *= arrAnswerCount[iSeed][2];
            }

            iAnotherExample = iAnswer + (rand.nextBoolean() ? -1 : 1) * arrAnswerCount[iSeed][rand.nextInt(2)];
        }
    }
}