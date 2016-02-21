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
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-10-30.
 */
public class ActStep0904  extends StageActivity {

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;

    public Step0904DataSet dataSet = new Step0904DataSet();

    private TextView des;
    private TextView subs[] = new TextView[2];
    private Button btnAnswer[] = new Button[3];
    private ImageView img1[] = new ImageView[3];
    private ImageView img2[] = new ImageView[2];
    private TextView txt1[] = new TextView[3];
    private TextView txt2[] = new TextView[2];
    private LinearLayout layout1[] = new LinearLayout[2];
    private LinearLayout layout2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_09_4);

        des = (TextView)findViewById(R.id.txt_description);
        subs[0] = (TextView)findViewById(R.id.sub1);
        subs[1] = (TextView)findViewById(R.id.sub2);

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        img1[0] = (ImageView)findViewById(R.id.img_1_1);
        img1[1] = (ImageView)findViewById(R.id.img_1_2);

        img2[0] = (ImageView)findViewById(R.id.img_2_1);
        img2[1] = (ImageView)findViewById(R.id.img_2_2);

        txt1[0] = (TextView)findViewById(R.id.txt_1_1);
        txt1[1] = (TextView)findViewById(R.id.txt_1_2);

        txt2[0] = (TextView)findViewById(R.id.txt_2_1);
        txt2[1] = (TextView)findViewById(R.id.txt_2_2);

        layout1[0] = (LinearLayout)findViewById(R.id.layout1);
        layout1[1] = (LinearLayout)findViewById(R.id.layout2);

        layout2 = (LinearLayout)findViewById(R.id.layout3);

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
        for(int i = 0 ; i < 3 ; i++)
            btnAnswer[i].setText(dataSet.btn[i]);

        des.setText(dataSet.des);
        subs[0].setText(dataSet.subs[0]);
        subs[1].setText(dataSet.subs[1]);

        for(int i = 0 ; i < 2; i++){
            if(dataSet.img1[i] == -1) {
                layout1[i - 1].setVisibility(View.GONE);
            }
            else {
                if(i != 0) layout1[i - 1].setVisibility(View.VISIBLE);
                img1[i].setImageResource(dataSet.img1[i]);
                txt1[i].setText(dataSet.txt1[i]);
            }
        }

        for(int i = 0 ; i < 2; i++){
            if(dataSet.img2[i] == -1){
                if(i == 1) layout2.setVisibility(View.GONE);
            }else {
                if(i == 1) layout2.setVisibility(View.VISIBLE);
                img2[i].setImageResource(dataSet.img2[i]);
                txt2[i].setText(dataSet.txt2[i]);
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
                if (isRight || iRetryCount > 1) {
                    iRetryCount = 0;
                    iStage++;
                    if (iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                } else {
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0905.class);
        startActivity(intent);
    }

    public class Step0904DataSet{

        private  final String desset[][]={
                {"아래의 과일과 우유에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 우유에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 우유에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?"},
                {"아래의 과일과 우유에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 우유에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 우유에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?"},
                {"아래의 과일과 견과류에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 견과류에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 견과류에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?",},
                {"아래의 과일과 야채에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 야채에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?", "아래의 과일과 야채에서 각각 1종류씩 골라 주스를 만들려 합니다. 선택할 수 있는 종류는 모두 몇 가지인가요?"}
        };

        private final String subset[][]={
                {"과일","우유"},
                {"과일","우유"},
                {"과일","견과류"},
                {"과일","야채"}
        };

        private final String btnset[][][] = {
                {{"1가지", "2가지", "3가지"},{"1가지", "2가지", "3가지"},{"1가지", "2가지", "3가지"}},
                {{"2가지", "3가지", "4가지"},{"2가지", "3가지", "4가지"},{"2가지", "3가지", "4가지"}},
                {{"1가지", "2가지", "3가지"},{"1가지", "2가지", "3가지"},{"1가지", "2가지", "3가지"}},
                {{"2가지", "3가지", "4가지"},{"1가지", "2가지", "3가지"},{"2가지", "3가지", "4가지"}},
        };

        private final String ansset[][] ={
                {"1가지","1가지","1가지",},
                {"2가지","2가지","2가지"},
                {"2가지","2가지","2가지"},
                {"4가지","1가지","4가지"},
        };

        private final int imgset1[][][]={
                {{R.drawable.fruit_9_4_10,-1,},                     {R.drawable.fruit_9_4_1, -1},                       {R.drawable.fruit_9_4_10, -1}},
                {{R.drawable.fruit_9_4_5, R.drawable.fruit_9_4_9},  {R.drawable.fruit_9_4_5, R.drawable.fruit_9_4_2},   {R.drawable.fruit_9_4_5, R.drawable.fruit_9_4_9}},
                {{R.drawable.fruit_9_4_10,R.drawable.fruit_9_4_2},  {R.drawable.fruit_9_4_5, R.drawable.fruit_9_4_9},   {R.drawable.fruit_9_4_10, R.drawable.fruit_9_4_2}},
                {{R.drawable.fruit_9_4_6,R.drawable.fruit_9_4_2},   {R.drawable.fruit_9_4_6, -1},                       {R.drawable.fruit_9_4_2,R.drawable.fruit_9_4_6}}
        };

        private final int imgset2[][][]={
                {{R.drawable.fruit_9_4_8, -1}, {R.drawable.fruit_9_4_8, -1}, {R.drawable.fruit_9_4_8, -1}},
                {{R.drawable.fruit_9_4_8, -1}, {R.drawable.fruit_9_4_8, -1}, {R.drawable.fruit_9_4_8, -1}},
                {{R.drawable.fruit_9_4_11, -1}, {R.drawable.fruit_9_4_11, -1}, {R.drawable.fruit_9_4_11, -1}},
                {{R.drawable.fruit_9_4_3, R.drawable.fruit_9_4_4}, {R.drawable.fruit_9_4_3, -1}, {R.drawable.fruit_9_4_3, R.drawable.fruit_9_4_4}},
        };

        private final String txtset1[][][]={
                {{"포도"}, {"딸기"}, {"포도"}},
                {{"블루베리", "키위"}, {"블루베리", "바나나"}, {"블루베리", "키위"}},
                {{"포도", "바나나"}, {"블루베리", "키위"}, {"포도", "바나나"}},
                {{"사과","바나나"}, {"사과"}, {"바나나","사과"}}
        };

        private final String txtset2[][][]={
                {{""}, {""}, {""}},
                {{""}, {""}, {""}},
                {{"호두"}, {"호두"}, {"호두"}},
                {{"당근", "토마토"}, {"당근"}, {"당근", "토마토"}},
        };


        int img1[] = new int[5], img2[] = new int[5];
        String btn[] = new String[3], subs[] = new String[2], ans = new String(), des = new String(), txt1[] = new String[3], txt2[] = new String[3];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2
            System.out.println(iStage + "\n" + rand + "\n");
            btn = btnset[iStage][rand];
            ans = ansset[iStage][rand];
            des = desset[iStage][rand];
            subs = subset[iStage];
            img1 = imgset1[iStage][rand];
            img2 = imgset2[iStage][rand];
            txt1 = txtset1[iStage][rand];
            txt2 = txtset2[iStage][rand];
        }
    }
}