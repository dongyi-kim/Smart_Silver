package src.activities.Step06;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.activities.Step09.ActStep0901;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-09-25.
 */
public class Step0604Activity extends StageActivity {

    private int iRetryCount = 0;
    public boolean isRight = false;
    private Button btn1, btn2;
    private TextView description;

    private ImageView mainimg;

    public Step0604DataSet dataSet = new Step0604DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_06_04);

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
        Intent intent = new Intent(this, ActStep0901.class);
        startActivity(intent);
    }

    public class Step0604DataSet {

        private final String deslist[][] = {{"다음은 어느 평생학교 시간표입니다.\n매주 화요일 2교시에는 평생학교에서 무엇을 하나요?","다음은 평생학교 시간표입니다.\n스포츠댄스는 언제 배우나요?","다음은 평생학교 시간표입니다.\n국어는 언제 배우나요?",},
                {"다음은 어느 평생학교 시간표입니다.\n매주 금요일 1교시에는 평생학교에서 무엇을 하나요?","다음은 평생학교 시간표입니다.\n수영은 언제 배우나요? ","다음은 평생학교 시간표입니다.\n수학은 언제 배우나요? ",},
                {"아래 표는 병원에서 규칙적으로 하는 운동시간표입니다.\n평일 오전에는 어떤 운동을 합니까?","아래 표는 병원에서 규칙적으로 하는 운동시간표입니다.\n걷기는 오전과 오후 중 언제 합니까? ","아래 표는 병원에서 규칙적으로 하는 운동시간표입니다.\n체조는 언제 합니까?",},
                {"아래 표는 병원에서 규칙적으로 하는 운동시간표입니다.\n매주 목요일 오후에는 어떤 운동을 합니까?","아래 표는 병원에서 규칙적으로 하는 운동시간표입니다.\n체조는 매주 오전과 오후 중 언제 합니까?","아래 표는 병원에서 규칙적으로 하는 운동시간표입니다.\n걷기는 언제 합니까?",},
                {"아래 표는 병원에서 하는 재활운동시간표 입니다.\n월, 수, 금요일에 규칙적으로 하는 재활운동은 무엇인가요? ","아래 표는 병원에서 하는 재활운동시간표 입니다.\n요가는 언제 합니까?","아래 표는 병원에서 하는 재활운동시간표 입니다.\n아쿠아로빅은 언제 합니까?",},};

        private final String arrBtn[][][] ={{{"국어","수학",},{"수요일 2교시","금요일 1교시",},{"월, 수 2교시","화, 목 1교시",},},
                {{"스포츠댄스","영어",},{"월, 수 2교시","화, 목 1교시",},{"월, 수 2교시","화, 목 2교시",},},
                {{"체조","걷기",},{"오전","오후",},{"평일 오전","평일 오후",},},
                {{"체조 ","걷기",},{"오전","오후",},{"평일 오전","평일 오후",},},
                {{"아쿠아로빅","요가",},{"월,수,금","화,목",},{"월,수,금","화,목",},},};

        private final int arrImg[][] = {
                {R.drawable.graph_8_5_1,R.drawable.graph_8_5_1,R.drawable.graph_8_5_1,},
                {R.drawable.graph_8_5_1,R.drawable.graph_8_5_1,R.drawable.graph_8_5_1,},
                {R.drawable.graph_8_5_2,R.drawable.graph_8_5_2,R.drawable.graph_8_5_2,},
                {R.drawable.graph_8_5_3,R.drawable.graph_8_5_2,R.drawable.graph_8_5_2,},
                {R.drawable.graph_8_5_4,R.drawable.graph_8_5_4,R.drawable.graph_8_5_4,}
        };

        private final String ansbtn[][] = {{"수학","금요일 1교시","월, 수 2교시",},
                {"스포츠댄스","화, 목 1교시","화, 목 2교시",},
                {"체조","오후","평일 오전",},
                {"걷기", "오전","평일 오후",},
                {"아쿠아로빅","화,목","월,수,금",},};

        int img;
        String des = new String();
        String ans = new String();
        String strbtn[] = new String[2];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2
            des = deslist[iStage][rand];
            img = arrImg[iStage][rand];
            ans = ansbtn[iStage][rand];
            strbtn = arrBtn[iStage][rand];
        }
    }
}