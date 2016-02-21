package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-09-14.
 */
public class ActStep0501 extends StageActivity {
    private ImageView imgDivide;
    private TextView txtDescription;
    public final Button btnAnswer[] = new Button[2];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;

    public Step0501DataSet dataSet = new Step0501DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_1);

        imgDivide = (ImageView)findViewById(R.id.img_divide);
        txtDescription = (TextView)findViewById(R.id.txt_description);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);

        for(int i = 0; i < 2; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iBtnIndex = btnAnswer[0] == v ? 0 : 1;

                    if(iBtnIndex == dataSet.iAnswerIndex) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtDescription.setText(dataSet.sDescription);
        imgDivide.setImageResource(dataSet.iPictureSource);
        btnAnswer[0].setText("" + dataSet.iAnswerCount[0]);
        btnAnswer[1].setText("" + dataSet.iAnswerCount[1]);

        startRecording();
    }

    public synchronized void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        countUpTry();
        if(isRight || iRetryCount > 1) stopRecording(isRight);

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
        Intent intent = new Intent(this, ActStep0502.class);
        startActivity(intent);
    }

    public class Step0501DataSet{
        private final int jeon = 0, dduck = 1;
        private final int arrImageSource[][] = {{0, 0, R.drawable.img_divide_jeon_2, R.drawable.img_divide_jeon_3, R.drawable.img_divide_jeon_4, 0,
                R.drawable.img_divide_jeon_6, 0, R.drawable.img_divide_jeon_8, 0, R.drawable.img_divide_jeon_10, 0, R.drawable.img_divide_jeon_12},
                {0, 0, R.drawable.img_divide_dduck_2, R.drawable.img_divide_dduck_3, R.drawable.img_divide_dduck_4, 0,
                R.drawable.img_divide_dduck_6, 0, R.drawable.img_divide_dduck_8, R.drawable.img_divide_dduck_9, R.drawable.img_divide_dduck_10, 0, R.drawable.img_divide_dduck_12}};
        private final String arrFoodDescription[] = {"녹두전", "시루떡"};

        public String sDescription;
        public int iAnswerCount[] = new int[2];
        public int iAnswerIndex;
        public int iPictureSource;

        public void setData(int iStage){
            int iFoodSeed = rand.nextInt(2), iDivideCount = 0;
            if(iStage <= 3){
                while(arrImageSource[iFoodSeed][iDivideCount] == 0) iDivideCount = 2 + rand.nextInt(7);
                sDescription = "아래 " + arrFoodDescription[iFoodSeed] + "을 똑같이 나누어 먹으려고 합니다.\n" + arrFoodDescription[iFoodSeed] + "은 모두 몇 명이 먹을 수 있나요?";
            }
            else{
                while(arrImageSource[iFoodSeed][iDivideCount] == 0) iDivideCount = 8 + rand.nextInt(5);
                sDescription = "아래 " + arrFoodDescription[iFoodSeed] + "을 " + iDivideCount + "명이 똑같이 나누어 먹기 위해서는\n몇 등분을 해야 합니까?";
            }

            iPictureSource = arrImageSource[iFoodSeed][iDivideCount];
            iAnswerIndex = rand.nextInt(2);
            iAnswerCount[iAnswerIndex] = iDivideCount;

            int iAddValue = 1 + rand.nextInt(3);
            iAnswerCount[(iAnswerIndex + 1) % 2] = iDivideCount + (iAddValue < iDivideCount ? (rand.nextBoolean() ? -1 : 1) : -1) * (1 + rand.nextInt(3));

            for(int i = 0; i < 2; i++) {
                if (iAnswerCount[i] <= 0) iAnswerCount[i] = 1;
            }
        }
    }
}