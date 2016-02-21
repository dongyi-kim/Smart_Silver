package src.activities.Step04;

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
 * Created by Acka on 2015-04-27.
 */
public class ActStep0401 extends StageActivity{
    private final ImageView imgPicture[] = new ImageView[2];
    public final Button btnAnswer[] = new Button[2];
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
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);

        for(int i = 0; i < 2; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iSelectIndex = (v == btnAnswer[0]? 0 : 1);
                    if (dataSet.bIsAnswer[iSelectIndex]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

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
        Intent intent = new Intent(this, ActStep0402.class);
        startActivity(intent);
    }

    public class Step0401DataSet{
        //private enum ePictureType {goodgam, hongshi, apple, flower, waterhippo, cherry};
        private final int yakgwa = 0, dried_persimmon = 1, pumpkin_pancake = 2, songpyeon = 3, apple = 4, flower = 5;
        private final int MAX_SET_NUMBER = 4;

        private final String arrCountUnit[] = {"개", "개", "개", "개", "개", "송이"};
        private final int arrRandomBase[] = {2, 3, 2, 2, 2, 2};
        private final int arrRandomRange[] = {5, 4, 5, 5, 5, 3};
        private final int arrImageSource[][][] = {{{}, {}, {0, R.drawable.img_mult_yakgwa_2_1, R.drawable.img_mult_yakgwa_2_2, R.drawable.img_mult_yakgwa_2_3, R.drawable.img_mult_yakgwa_2_4},
                {0, R.drawable.img_mult_yakgwa_3_1, R.drawable.img_mult_yakgwa_3_2, R.drawable.img_mult_yakgwa_3_3, R.drawable.img_mult_yakgwa_3_4},
                {0, R.drawable.img_mult_yakgwa_4_1, R.drawable.img_mult_yakgwa_4_2, R.drawable.img_mult_yakgwa_4_3, R.drawable.img_mult_yakgwa_4_4},
                {0, R.drawable.img_mult_yakgwa_5_1, R.drawable.img_mult_yakgwa_5_2, R.drawable.img_mult_yakgwa_5_3, R.drawable.img_mult_yakgwa_5_4},
                {0, R.drawable.img_mult_yakgwa_6_1, R.drawable.img_mult_yakgwa_6_2, R.drawable.img_mult_yakgwa_6_3, R.drawable.img_mult_yakgwa_6_4}},
                {{}, {}, {}, {0, R.drawable.img_mult_dried_persimmon_3_1, R.drawable.img_mult_dried_persimmon_3_2, R.drawable.img_mult_dried_persimmon_3_3, R.drawable.img_mult_dried_persimmon_3_4},
                        {0, R.drawable.img_mult_dried_persimmon_4_1, R.drawable.img_mult_dried_persimmon_4_2, R.drawable.img_mult_dried_persimmon_4_3, R.drawable.img_mult_dried_persimmon_4_4},
                        {0, R.drawable.img_mult_dried_persimmon_5_1, R.drawable.img_mult_dried_persimmon_5_2, R.drawable.img_mult_dried_persimmon_5_3, R.drawable.img_mult_dried_persimmon_5_4},
                        {0, R.drawable.img_mult_dried_persimmon_6_1, R.drawable.img_mult_dried_persimmon_6_2, R.drawable.img_mult_dried_persimmon_6_3, R.drawable.img_mult_dried_persimmon_6_4}},
                {{}, {}, {0, R.drawable.img_mult_pumpkin_pancake_2_1, R.drawable.img_mult_pumpkin_pancake_2_2, R.drawable.img_mult_pumpkin_pancake_2_3, R.drawable.img_mult_pumpkin_pancake_2_4},
                        {0, R.drawable.img_mult_pumpkin_pancake_3_1, R.drawable.img_mult_pumpkin_pancake_3_2, R.drawable.img_mult_pumpkin_pancake_3_3, R.drawable.img_mult_pumpkin_pancake_3_4},
                        {0, R.drawable.img_mult_pumpkin_pancake_4_1, R.drawable.img_mult_pumpkin_pancake_4_2, R.drawable.img_mult_pumpkin_pancake_4_3, R.drawable.img_mult_pumpkin_pancake_4_4},
                        {0, R.drawable.img_mult_pumpkin_pancake_5_1, R.drawable.img_mult_pumpkin_pancake_5_2, R.drawable.img_mult_pumpkin_pancake_5_3, R.drawable.img_mult_pumpkin_pancake_5_4},
                        {0, R.drawable.img_mult_pumpkin_pancake_6_1, R.drawable.img_mult_pumpkin_pancake_6_2, R.drawable.img_mult_pumpkin_pancake_6_3, R.drawable.img_mult_pumpkin_pancake_6_4}},
                {{}, {}, {0, R.drawable.img_mult_songpyeon_2_1, R.drawable.img_mult_songpyeon_2_2, R.drawable.img_mult_songpyeon_2_3, R.drawable.img_mult_songpyeon_2_4},
                        {0, R.drawable.img_mult_songpyeon_3_1, R.drawable.img_mult_songpyeon_3_2, R.drawable.img_mult_songpyeon_3_3, R.drawable.img_mult_songpyeon_3_4},
                        {0, R.drawable.img_mult_songpyeon_4_1, R.drawable.img_mult_songpyeon_4_2, R.drawable.img_mult_songpyeon_4_3, R.drawable.img_mult_songpyeon_4_4},
                        {0, R.drawable.img_mult_songpyeon_5_1, R.drawable.img_mult_songpyeon_5_2, R.drawable.img_mult_songpyeon_5_3, R.drawable.img_mult_songpyeon_5_4},
                        {0, R.drawable.img_mult_songpyeon_6_1, R.drawable.img_mult_songpyeon_6_2, R.drawable.img_mult_songpyeon_6_3, R.drawable.img_mult_songpyeon_6_4}},
                {{}, {}, {0, R.drawable.img_mult_apple_2_1, R.drawable.img_mult_apple_2_2, R.drawable.img_mult_apple_2_3, R.drawable.img_mult_apple_2_4},
                        {0, R.drawable.img_mult_apple_3_1, R.drawable.img_mult_apple_3_2, R.drawable.img_mult_apple_3_3, R.drawable.img_mult_apple_3_4},
                        {0, R.drawable.img_mult_apple_4_1, R.drawable.img_mult_apple_4_2, R.drawable.img_mult_apple_4_3, R.drawable.img_mult_apple_4_4},
                        {0, R.drawable.img_mult_apple_5_1, R.drawable.img_mult_apple_5_2, R.drawable.img_mult_apple_5_3, R.drawable.img_mult_apple_5_4},
                        {0, R.drawable.img_mult_apple_6_1, R.drawable.img_mult_apple_6_2, R.drawable.img_mult_apple_6_3, R.drawable.img_mult_apple_6_4}},
                {{}, {}, {0, R.drawable.img_mult_flower_2_1, R.drawable.img_mult_flower_2_2, R.drawable.img_mult_flower_2_3, R.drawable.img_mult_flower_2_4},
                        {0, R.drawable.img_mult_flower_3_1, R.drawable.img_mult_flower_3_2, R.drawable.img_mult_flower_3_3, R.drawable.img_mult_flower_3_4},
                        {0, R.drawable.img_mult_flower_4_1, R.drawable.img_mult_flower_4_2, R.drawable.img_mult_flower_4_3, R.drawable.img_mult_flower_4_4}}};

        public String sCountDescription[] = new String[2];
        public int iImageResource[] = new int[2];
        public boolean bIsAnswer[] = new boolean[2];

        public void setData(int iStage) {
            int iSeed = rand.nextInt(3);

            bIsAnswer[0] = rand.nextBoolean(); bIsAnswer[1] = !bIsAnswer[0];
            for(int i = 0; i < 2; i++){
                int iSingleSet = arrRandomBase[2 * iSeed + i] + rand.nextInt(arrRandomRange[2 * iSeed + i]);
                int iSetCount = 1 + rand.nextInt(MAX_SET_NUMBER);

                iImageResource[i] = arrImageSource[2 * iSeed + i][iSingleSet][iSetCount];

                if(!bIsAnswer[i]){
                    int iRandNum = rand.nextInt(4);
                    if(iRandNum == 0 && iSingleSet != 2) iSingleSet--;
                    else if(iRandNum == 1) iSingleSet++;
                    else if(iRandNum == 2 && iSetCount != 1) iSetCount--;
                    else if(iRandNum == 3) iSetCount++;
                    else iSetCount++;
                }

                sCountDescription[i] = "" + iSingleSet + arrCountUnit[2 * iSeed + i] + "씩\n" + iSetCount + "묶음";
            }
        }
    }
}
