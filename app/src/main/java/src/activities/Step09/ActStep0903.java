package src.activities.Step09;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-10-30.
 */
public class ActStep0903 extends StageActivity {


    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;

    private TextView desciprtion;

    private LinearLayout cap;

    private ImageView pants[] = new ImageView[2];
    private ImageView caps;
    private ImageView shirts[] = new ImageView[3];
    private ImageView man;

    public Step0903DataSet dataSet = new Step0903DataSet();

    private Button btnAnswer[] = new Button[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_09_3);

        desciprtion = (TextView)findViewById(R.id.txt_description);

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        shirts[0] = (ImageView)findViewById(R.id.shirt_1);
        shirts[1] = (ImageView)findViewById(R.id.shirt_2);
        shirts[2] = (ImageView)findViewById(R.id.shirt_3);

        pants[0] = (ImageView)findViewById(R.id.pants_1);
        pants[1] = (ImageView)findViewById(R.id.pants_2);

        caps = (ImageView)findViewById(R.id.cap_1);
        cap = (LinearLayout)findViewById(R.id.cap);

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

    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage - 1);

        desciprtion.setText(dataSet.des);
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
            if(dataSet.cloth[1] > i){
                pants[i].setImageResource(dataSet.pants[i]);
                pants[i].setVisibility(View.VISIBLE);
            }else{
                pants[i].setVisibility(View.GONE);
            }
        }

        if(dataSet.cloth[2] == 1){
            cap.setVisibility(View.VISIBLE);
        }
        else{
            cap.setVisibility(View.GONE);
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
        Intent intent = new Intent(this, ActStep0904.class);
        startActivity(intent);
    }

    public class Step0903DataSet{

        private final String desset[][]={
                {"아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", },
                {"아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", },
                {"아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 모자와 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 모자와 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", },
                {"아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 모자와 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 모자와 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", },
                {"아래의 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 모자와 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 모자와 상의와 하의에서 각각 1종류씩 골라 인형에 입히려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", },
        };

        private final int numcloth[][][]={
                {{2,1,0}, {1,1,0}, {1,2,0}},
                {{3,1,0}, {1,1,0}, {2,1,0}},
                {{2,2,0}, {1,1,1}, {2,1,1}},
                {{2,2,0}, {2,1,1}, {2,1,1}},
                {{3,2,0}, {1,1,1}, {2,2,1}},
        };

        private final int ismanset[][]={ // 0이면 여자당
                {R.drawable.img_woman, R.drawable.img_woman, R.drawable.img_woman},
                {R.drawable.img_man, R.drawable.img_man, R.drawable.img_woman},
                {R.drawable.img_woman, R.drawable.img_man, R.drawable.img_woman},
                {R.drawable.img_man, R.drawable.img_man, R.drawable.img_man},
                {R.drawable.img_woman, R.drawable.img_woman, R.drawable.img_woman},
        };

        private final String ansset[][]={
                {"2가지", "1가지", "2가지", },
                {"3가지", "1가지", "2가지", },
                {"4가지", "1가지", "2가지", },
                {"4가지", "2가지", "2가지", },
                {"6가지", "1가지", "4가지", },
        };

        private final String btnset[][][] = {
                {{"2가지", "4가지", "6가지"},{"1가지", "2가지", "4가지"},{"1가지", "2가지", "4가지"}},
                {{"2가지", "3가지", "4가지"},{"1가지", "2가지", "4가지"},{"2가지", "4가지", "6가지"}},
                {{"2가지", "4가지", "6가지"},{"1가지", "2가지", "4가지"},{"2가지", "4가지", "6가지"}},
                {{"4가지", "6가지", "8가지"},{"2가지", "3가지", "4가지"},{"2가지", "3가지", "4가지"}},
                {{"5가지", "6가지", "7가지"},{"1가지", "2가지", "4가지"},{"1가지", "2가지", "4가지"}},
        };
        private final int shirtset[][][] = {
                {{R.drawable.tshirt_1, R.drawable.tshirt_5},{R.drawable.tshirt_3,}, {R.drawable.tshirt_4} },
                {{R.drawable.tshirt_8, R.drawable.tshirt_9, R.drawable.tshirt_10}, {R.drawable.tshirt_12}, {R.drawable.tshirt_6,R.drawable.tshirt_7}},
                {{R.drawable.tshirt_1, R.drawable.tshirt_2}, {R.drawable.tshirt_14}, {R.drawable.tshirt_6,R.drawable.tshirt_7}},
                {{R.drawable.tshirt_11,R.drawable.tshirt_12},{R.drawable.tshirt_13,R.drawable.tshirt_12},{R.drawable.tshirt_11,R.drawable.tshirt_14}},
                {{R.drawable.tshirt_3,R.drawable.tshirt_4,R.drawable.tshirt_5},{R.drawable.tshirt_3},{R.drawable.tshirt_6,R.drawable.tshirt_7}}
        };
        private final int pantsset[][][] = {
                {{R.drawable.pants_4},{R.drawable.pants_4},{R.drawable.pants_4,R.drawable.pants_3}},
                {{R.drawable.pants_7},{R.drawable.pants_7},{R.drawable.pants_3}},
                {{R.drawable.pants_4,R.drawable.pants_3},{R.drawable.pants_7},{R.drawable.pants_3}},
                {{R.drawable.pants_8,R.drawable.pants_7},{R.drawable.pants_7},{R.drawable.pants_7}},
                {{R.drawable.pants_4,R.drawable.pants_3},{R.drawable.pants_4},{R.drawable.pants_4,R.drawable.pants_3}}
        };
        private final int capset[][][] = {
                {{0},{0},{0}},
                {{0},{0},{0}},
                {{0},{R.drawable.cap_3},{R.drawable.cap_3}},
                {{0},{R.drawable.cap_3},{R.drawable.cap_3}},
                {{0},{R.drawable.cap_3},{R.drawable.cap_3}},
        };

        String ans = new String(), des = new String();
        String btn[] = new String[3];
        int isman;
        int cloth[] = new int[10], shirt[] =new int[10], cap[]=new int[10], pants[]=new int[10];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            cloth = numcloth[iStage][rand];
            ans = ansset[iStage][rand];
            des = desset[iStage][rand];
            isman = ismanset[iStage][rand];
            btn = btnset[iStage][rand];
            shirt = shirtset[iStage][rand];
            cap = capset[iStage][rand];
            pants = pantsset[iStage][rand];
        }
    }
}