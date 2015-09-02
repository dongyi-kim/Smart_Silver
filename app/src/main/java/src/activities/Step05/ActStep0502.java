package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import src.viewes.DrawView;

/**
 * Created by Acka on 2015-05-17.
 */
public class ActStep0502 extends StageActivity {
    private DrawView mDrawingView;
    private LinearLayout mDrawingPad;

    private TextView txtDescription;
    private ImageView imgFruits;
    private Button btnRedraw;
    public final Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0502DataSet dataSet = new Step0502DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_2);

        txtDescription = (TextView) findViewById(R.id.txt_description);
        imgFruits = (ImageView) findViewById(R.id.img_many_fruits);
        btnRedraw = (Button) findViewById(R.id.btn_redraw);
        btnAnswer[0] = (Button) findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button) findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button) findViewById(R.id.btn_answer_3);

        mDrawingView = new DrawView(this);
        mDrawingView.setColorCode(0xFFFFFFFF);

        mDrawingPad = (LinearLayout) findViewById(R.id.draw_field_0502);
        mDrawingPad.addView(mDrawingView);

        btnRedraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingPad.removeAllViews();
                mDrawingView.clearView();
                mDrawingPad.addView(mDrawingView);
            }
        });

        for (int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(((Button) v).getText().toString()) == dataSet.iAnswer)
                        isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object) {
        dataSet.setData(iStage);

        txtDescription.setText(dataSet.sDescription);
        imgFruits.setImageResource(dataSet.iFruitImage);

        int iNextExample = dataSet.iAnswer - rand.nextInt(2);
        if (iNextExample < 0) iNextExample = 0;

        for (int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));

        StartRecording();
    }

    public void checkAnswer(Object o) {
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if (isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isRight || iRetryCount > 1) {
                    mDrawingView.clearView();
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

    public void goNext(Object object) {
        Intent intent = new Intent(this, ActStep0503.class);
        startActivity(intent);
    }

    public class Step0502DataSet {
        private final int arrFruitCount[] = {16, 12, 15, 30, 32};
        private final String arrFruitDescription[] = {"감을", "딸기를", "배를", "사과를", "사과를"};
        private final String arrCountDescription[] = {"", "한 개씩", "두 개씩", "세 개씩", "네 개씩", "다섯 개씩", "여섯 개씩", "일곱 개씩", "여덟 개씩", "아홉 개씩"};
        private final int arrImageSource[] = {R.drawable.img_draw_persimmon_16, R.drawable.img_draw_strawberry_12, R.drawable.img_draw_pear_15, R.drawable.img_draw_apple_30, R.drawable.img_draw_apple_32};


        public String sDescription;
        public int iFruitImage;
        public int iAnswer;

        public void setData(int iStage) {
            int iCount = 0;
            do{
                iCount = 2 + rand.nextInt(7);
            } while(arrFruitCount[iStage - 1] % iCount != 0);

            sDescription = "평생학교에서 간식으로 " + arrFruitDescription[iStage - 1] + " 나누어 먹으려 합니다. 한 사람이 " + arrCountDescription[iCount] +
                    " 먹는다면, 몇 명이 먹을 수 있을까요? 손가락으로 " + arrCountDescription[iCount] + " 묶어 보세요.";
            iFruitImage = arrImageSource[iStage - 1];
            iAnswer = arrFruitCount[iStage - 1] / iCount;
        }
    }
}