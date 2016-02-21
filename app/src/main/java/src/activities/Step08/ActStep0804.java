package src.activities.Step08;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-09-25.
 */
public class ActStep0804 extends StageActivity {

    private int iRetryCount = 0;
    public boolean isRight = false;
    private Button btn1, btn2;
    private TextView description;

    private ImageView mainimg;

    public Step0804DataSet dataSet = new Step0804DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_08_4);

        description = (TextView)findViewById(R.id.txt_description);

        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);

        mainimg = (ImageView)findViewById(R.id.img);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnCurrentButton = (Button) v;
                String iSelectAnswer = btnCurrentButton.getText().toString();
                if (dataSet.ans.equals(iSelectAnswer)) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnCurrentButton = (Button) v;
                String iSelectAnswer = btnCurrentButton.getText().toString();
                if (dataSet.ans.equals(iSelectAnswer)) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object) {
        dataSet.setData(iStage - 1);

        btn1.setText(dataSet.strbtn[0]);
        btn2.setText(dataSet.strbtn[1]);

        description.setText(dataSet.des);
        mainimg.setImageResource(dataSet.img);

        startRecording();
    }

    public synchronized void checkAnswer(Object o) {
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        countUpTry();
        if (isRight || iRetryCount > 1) stopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isRight || iRetryCount > 1) {
                    iStage++;
                    iRetryCount = 0;
                    if (iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                } else {
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object) {
        // check
        Intent intent = new Intent(this, ActStep0805.class);
        startActivity(intent);
    }

    public class Step0804DataSet {

        private final String deslist[][] = {
                {"다음은 9호선 열차 시간표입니다.\n가장 많은 기차가 배치된 시간대는 언제인가요?",
                        "다음은 9호선 열차 시간표입니다.\n가장 많은 기차가 배치된 시간대는 언제인가요?",
                        "다음은 9호선 열차 시간표입니다.\n가장 많은 기차가 배치된 시간대는 언제인가요?",},

                {"다음은 9호선 열차 시간표입니다.\n가장 적은 기차가 배치된 시간대는 언제인가요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n가장 적은 열차가 배치된 시간대는 언제인가요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n가장 적은 열차가 배치된 시간대는 언제인가요?",},

                {"다음은 9호선 열차 시간표에 대한 안내문입니다.\n오후 7시 대에는 몇 개의 기차가 배차되어 있나요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n오전 8시 대에는 몇 개의 열차가 배차되어 있나요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n오후 2시 대에는 몇 대의 열차가 배차되어 있나요?",},

                {"다음은 9호선 열차 시간표에 대한 안내문입니다.\n오후 4시 대에는 몇 개의 기차가 배차되어 있나요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n오전 11시 대에는 몇 개의 열차가 배차되어 있나요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n오후 8시 대에는 몇 대의 열차가 배차되어 있나요?",},

                {"다음은 9호선 열차 시간표에 대한 안내문입니다.\n첫 차는 몇 시 몇 분에 있나요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n첫 차는 몇 시 몇 분에 있나요?",
                        "다음은 9호선 열차 시간표에 대한 안내문입니다.\n가장 늦게 운행하는 열차는 몇 시 몇 분에 있습니까?",},
        };


        private final String arrBtn[][][] ={
                {{"오전 8시", "오후 8시"}, {"오전 7시", "오후 7시"}, {"오전 9시", "오후 8시"}},
                {{"오전 6시", "오후 9시"}, {"오전 5시", "오후 9시"}, {"오전 6시", "오후 9시"}},
                {{"6 대", "8 대"}, {"10 대", "8 대"}, {"3 대", "5 대"},},
                {{"5 대", "8 대"}, {"6 대", "4 대"}, {"4 대", "6 대"},},
                {{"오전 5시", "오전 6시 22분"}, {"오전 5시", "오전 5시 46분"}, {"오후 11시", "오후 11시 48분"}}
        };

        private final int arrImg[] = {R.drawable.graph_8_4_1, R.drawable.graph_8_4_2, R.drawable.graph_8_4_3};
        private final String ansbtn[][] = {
                {"오전 8시","오전 7시","오후 8시",},
                {"오전 6시","오전 5시","오전 6시",},
                {"6 대","8 대","5 대",},
                {"5 대","4 대","6 대",},
                {"오전 6시 22분","오전 5시 46분","오후 11시 48분"}
        };

        int img;
        String des = new String();
        String ans = new String();
        String strbtn[] = new String[10];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            des = deslist[iStage][rand];
            img = arrImg[rand];
            ans = ansbtn[iStage][rand];
            strbtn = arrBtn[iStage][rand];
        }
    }
}