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
import src.activities.Step06.Step0605Activity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-06-24.
 */
public class ActStep0505 extends StageActivity {
    private final ImageView imgPicture[] = new ImageView[2];
    public final Button btnAnswer[] = new Button[2];
    public final TextView txtAnswer[] = new TextView[2];
    private TextView txtDescription;

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0505DataSet dataSet = new Step0505DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_5);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        imgPicture[0] = (ImageView)findViewById(R.id.img_picture_1);
        imgPicture[1] = (ImageView)findViewById(R.id.img_picture_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);

        for(int i = 0; i < 2; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iSelectIndex = (v == btnAnswer[0]? 0 : 1);
                    if(dataSet.bIsAnswer[iSelectIndex]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        if(iStage == NUM_OF_STAGE) txtDescription.setText("노인학교에서 가을축제 준비를 하기 위해 시장에 갔습니다. 한 개 당 가격이 더 저렴한 것은 어느 것일까요?");
        for(int i = 0; i < 2; i++){
            imgPicture[i].setImageResource(dataSet.iImageResource[i]);
            txtAnswer[i].setText(dataSet.sCountDescription[i]);
        }

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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, Step0605Activity.class);
        startActivity(intent);
    }

    public class Step0505DataSet{
        private final String arrCountUnit[] = {"접시", "접시", "접시", "접시", "개"};
        private final int arrSetCount[][] = {{1, 2}, {2, 3}, {3, 4}, {2, 5}, {9, 8}};
        private final int arrImageSource[][] = {{R.drawable.img_mult_songpyeon_4_1, R.drawable.img_mult_songpyeon_4_2},
                {R.drawable.img_mult_pumpkin_pancake_4_2, R.drawable.img_mult_pumpkin_pancake_4_3}, {R.drawable.img_mult_apple_4_3, R.drawable.img_mult_apple_4_4},
                {R.drawable.img_mult_songpyeon_5_2, R.drawable.img_mult_songpyeon_5_5}, {R.drawable.img_set_apple_9, R.drawable.img_set_apple_8}};

        public String sCountDescription[] = new String[2];
        public int iImageResource[] = new int[2];
        public boolean bIsAnswer[] = new boolean[2];

        public void setData(int iStage) {
            int iPrice[] = {6 + rand.nextInt(5), 0};

            bIsAnswer[0] = iPrice[0] == 10 ? false : rand.nextBoolean();
            bIsAnswer[1] = !bIsAnswer[0];
            iPrice[1] = iPrice[0] + (bIsAnswer[1] ? -1 : 1);

            for(int i = 0; i < 2; i++){
                iPrice[i] *= arrSetCount[iStage - 1][i];

                iImageResource[i] = arrImageSource[iStage - 1][i];
                sCountDescription[i] = "" + arrSetCount[iStage - 1][i] + arrCountUnit[iStage - 1] + " ";
                if(iPrice[i] >= 10) sCountDescription[i] += "" + iPrice[i] / 10 + ",";
                sCountDescription[i] += "" + iPrice[i] % 10 + "00원";
            }
        }
    }
}