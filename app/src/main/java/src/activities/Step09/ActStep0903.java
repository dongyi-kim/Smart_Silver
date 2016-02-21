package src.activities.Step09;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-10-30.
 */
public class ActStep0903 extends StageActivity {


    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;

    private ImageView pants[] = new ImageView[2];
    private ImageView shirts[] = new ImageView[3];
    private ImageView man;

    public Step0903DataSet dataSet = new Step0903DataSet();

    private Button btnAnswer[] = new Button[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_09_3);

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        shirts[0] = (ImageView)findViewById(R.id.shirt_1);
        shirts[1] = (ImageView)findViewById(R.id.shirt_2);
        shirts[2] = (ImageView)findViewById(R.id.shirt_3);

        pants[0] = (ImageView)findViewById(R.id.pants_1);
        pants[1] = (ImageView)findViewById(R.id.pants_2);

        man = (ImageView)findViewById(R.id.img_man);

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((Button) v).getText().equals(dataSet.ans)) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage - 1);

        man.setImageResource(dataSet.isman);
        for(int i = 0 ; i < 3 ; i++)
            btnAnswer[i].setText(dataSet.btn[i]);

        for(int i = 0 ; i < 3 ; i++) {
            if (dataSet.cloth[0] > i) {
                shirts[i].setImageResource(dataSet.shirt[i]);
                shirts[i].setVisibility(View.VISIBLE);
            } else {
                shirts[i].setVisibility(View.GONE);
            }
        }

        for(int i = 0 ; i < 2 ; i++){
            if(dataSet.cloth[1] > i) {
                pants[i].setImageResource(dataSet.pants[i]);
                pants[i].setVisibility(View.VISIBLE);
            } else {
                pants[i].setVisibility(View.GONE);
            }
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
        Intent intent = new Intent(this, ActStep0904.class);
        startActivity(intent);
    }

    public class Step0903DataSet{

        private final int ismanset[][]={ // 0이면 여자당
                {R.drawable.img_woman, R.drawable.img_woman, R.drawable.img_woman},
                {R.drawable.img_man,   R.drawable.img_man,   R.drawable.img_woman},
                {R.drawable.img_woman, R.drawable.img_man,   R.drawable.img_woman},
                {R.drawable.img_man,   R.drawable.img_man,   R.drawable.img_man},
                {R.drawable.img_woman, R.drawable.img_woman, R.drawable.img_woman},
        };

        private final String ansset[][]={
                {"2가지", "1가지", "2가지", },
                {"3가지", "1가지", "2가지", },
                {"4가지", "2가지", "3가지", },
                {"4가지", "2가지", "3가지", },
                {"6가지", "3가지", "6가지", },
        };

        private final String btnset[][][] = {
                {{"2가지", "4가지", "6가지"},{"1가지", "2가지", "4가지"},{"1가지", "2가지", "4가지"}},
                {{"2가지", "3가지", "4가지"},{"1가지", "2가지", "4가지"},{"2가지", "4가지", "6가지"}},
                {{"2가지", "4가지", "6가지"},{"1가지", "2가지", "4가지"},{"2가지", "3가지", "4가지"}},
                {{"4가지", "6가지", "8가지"},{"2가지", "3가지", "4가지"},{"2가지", "3가지", "4가지"}},
                {{"5가지", "6가지", "7가지"},{"2가지", "3가지", "4가지"},{"4가지", "5가지", "6가지"}},
        };

        private final int numcloth[][][]={
                {{2,1}, {1,1}, {1,2}}, // 여 여 여
                {{3,1}, {1,1}, {2,1}}, // 남 남 여
                {{2,2}, {2,1}, {3,1}}, // 여 남 여
                {{2,2}, {2,1}, {3,1}}, // 남 남 남
                {{3,2}, {3,1}, {3,2}}, // 여 여 여
        };

        private final int shirtset[][][] = {
                {{R.drawable.tshirt_1, R.drawable.tshirt_5}, {R.drawable.tshirt_3,}, {R.drawable.tshirt_4} },
                {{R.drawable.tshirt_8, R.drawable.tshirt_9, R.drawable.tshirt_10}, {R.drawable.tshirt_12}, {R.drawable.tshirt_6,R.drawable.tshirt_7}},
                {{R.drawable.tshirt_1, R.drawable.tshirt_2}, {R.drawable.tshirt_14, R.drawable.tshirt_13}, {R.drawable.tshirt_6,R.drawable.tshirt_7, R.drawable.tshirt_1}},
                {{R.drawable.tshirt_11,R.drawable.tshirt_12}, {R.drawable.tshirt_13, R.drawable.tshirt_12},{R.drawable.tshirt_11,R.drawable.tshirt_14, R.drawable.tshirt_10}},
                {{R.drawable.tshirt_3, R.drawable.tshirt_4, R.drawable.tshirt_5}, {R.drawable.tshirt_3, R.drawable.tshirt_4, R.drawable.tshirt_5}, {R.drawable.tshirt_6,R.drawable.tshirt_7, R.drawable.tshirt_3}}
        };
        private final int pantsset[][][] = {
                {{R.drawable.pants_4},{R.drawable.pants_4},{R.drawable.pants_4,R.drawable.pants_3}},
                {{R.drawable.pants_7},{R.drawable.pants_7},{R.drawable.pants_3}},
                {{R.drawable.pants_4,R.drawable.pants_3},{R.drawable.pants_7},{R.drawable.pants_3}},
                {{R.drawable.pants_8,R.drawable.pants_7},{R.drawable.pants_7},{R.drawable.pants_7}},
                {{R.drawable.pants_4,R.drawable.pants_3},{R.drawable.pants_4},{R.drawable.pants_4,R.drawable.pants_3}}
        };

        String ans = new String();
        String btn[] = new String[3];
        int isman;
        int cloth[] = new int[10], shirt[] =new int[10], pants[]=new int[10];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            cloth = numcloth[iStage][rand];
            ans = ansset[iStage][rand];
            isman = ismanset[iStage][rand];
            btn = btnset[iStage][rand];
            shirt = shirtset[iStage][rand];
            pants = pantsset[iStage][rand];
        }
    }
}