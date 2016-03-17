package src.activities.Step06;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-09-06.
 */

public class Step0602Activity extends StageActivity
{
    private int iRetryCount = 0;
    public boolean isRight = false;
    private Button btn1, btn2, btn3;
    private TextView x1;
    private FrameLayout x2;
    private TextView box[] = new TextView[10];
    private FrameLayout boxes[] = new FrameLayout[10];
    private ImageView pinks[] = new ImageView[10];

    public Step0602DataSet dataSet = new Step0602DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_06_02);

        x1 = (TextView)findViewById(R.id.gone_1);
        x2 = (FrameLayout)findViewById(R.id.gone_2);

        btn1 = (Button)findViewById(R.id.btn_answer_1);
        btn2 = (Button)findViewById(R.id.btn_answer_2);
        btn3 = (Button)findViewById(R.id.btn_answer_3);

        pinks[0] = (ImageView)findViewById(R.id.pink1);
        pinks[1] = (ImageView)findViewById(R.id.pink2);
        pinks[2] = (ImageView)findViewById(R.id.pink3);
        pinks[3] = (ImageView)findViewById(R.id.pink4);
        pinks[4] = (ImageView)findViewById(R.id.pink5);

        box[0] = (TextView)findViewById(R.id.bx1);
        box[1] = (TextView)findViewById(R.id.bx2);
        box[2] = (TextView)findViewById(R.id.bx3);
        box[3] = (TextView)findViewById(R.id.bx4);
        box[4] = (TextView)findViewById(R.id.bx5);
        box[5] = (TextView)findViewById(R.id.bx6);

        boxes[0] = (FrameLayout)findViewById(R.id.box1);
        boxes[1] = (FrameLayout)findViewById(R.id.box2);
        boxes[2] = (FrameLayout)findViewById(R.id.box3);
        boxes[3] = (FrameLayout)findViewById(R.id.box4);
        boxes[4] = (FrameLayout)findViewById(R.id.box5);

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

        btn3.setOnClickListener(new View.OnClickListener() {
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

        if(iStage == 5) {
            x1.setVisibility(View.GONE);
            x2.setVisibility(View.GONE);
        }
        else
        {
            x1.setVisibility(View.VISIBLE);
            x2.setVisibility(View.VISIBLE);
        }

        if(iStage == 1 || iStage == 5) btn3.setVisibility(View.GONE);
        else btn3.setVisibility(View.VISIBLE);

        btn1.setText(dataSet.strbtn[0]);
        btn2.setText(dataSet.strbtn[1]);
        btn3.setText(dataSet.strbtn[2]);

        for(int i = 0 ; i < 6 ; i++)
        {
            if(i == dataSet.blk)
                box[i].setText("");
            else
                box[i].setText(dataSet.prob[i]);
        }

        if(iStage == 5) {
            for (int i = 0; i < 5; i++) {
                LinearLayout.LayoutParams frameParam = (LinearLayout.LayoutParams)boxes[i].getLayoutParams();
                frameParam.width= (int)getResources().getDimension(R.dimen.wp18);

                android.view.ViewGroup.LayoutParams frameParam2 = pinks[i].getLayoutParams();
                frameParam2.width = (int)getResources().getDimension(R.dimen.wp18);

                boxes[i].setLayoutParams(frameParam);
                pinks[i].setLayoutParams(frameParam2);
                box[i].setWidth((int)getResources().getDimension(R.dimen.wp18));
            }
        }
        else
        {
            for (int i = 0; i < 5; i++) {
                LinearLayout.LayoutParams frameParam = (LinearLayout.LayoutParams)boxes[i].getLayoutParams();
                frameParam.width= (int)getResources().getDimension(R.dimen.wp13);

                android.view.ViewGroup.LayoutParams frameParam2 = pinks[i].getLayoutParams();
                frameParam2.width = (int)getResources().getDimension(R.dimen.wp13);

                boxes[i].setLayoutParams(frameParam);
                pinks[i].setLayoutParams(frameParam2);
                box[i].setWidth((int)getResources().getDimension(R.dimen.wp13));
            }
        }

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
        Intent intent = new Intent(this, Step0603Activity.class);
        startActivity(intent);
    }

    public class Step0602DataSet {

        private final String pb[][][]={
                {{"1","2","1","2","1","2"}, {"8","9","8","9","8","9"},{"4","5","4","5","4","5",}},
                {{"1","2","3","1","2","3"}, {"7","8","9","7","8","9"},{"4","5","6","4","5","6",}},
                {{"1","2","3","1","2","3"}, {"5","6","7","5","6","7"},{"6","5","4","6","5","4",}},
                {{"11","12","13","14","15","16"}, {"8","7","6","5","4","3"}, {"3","4","5","6","7","8"}},
                {{"111","112","113","114","115",""}, {"118","117","116","115","114",""}, {"58","59","60","61","62",""}}
        };

        private final String arrBtn[][][] ={
                {{"1", "2",""},{"8","9",""},{"4","5",""}},
                {{"1","2","3"},{"7","8","9"},{"5","6","4"}},
                {{"1","2","3"},{"6","5","7"},{"4","5","6"}},
                {{"13","15","17"}, {"2","3","4"}, {"5","7","2"}},
                {{"114","117",""},{"114","115",""},{"61","63",""}}
        };

        private final String ansbtn[][] = {{"1","8","4"},{"3","9","6"},{"2","6","5"},{"15","4","7"},{"114","115","61"}}; // 답 버튼

        private final int blanklist[]={4,5,1,4,3};

        String ans = new String();
        String prob[] = new String[10];
        String strbtn[] = new String[10];
        int blk;

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            blk = blanklist[iStage];
            ans = ansbtn[iStage][rand];
            strbtn = arrBtn[iStage][rand];
            prob = pb[iStage][rand];
        }
    }
}