package src.activities.Step09;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-09-24.
 */
public class ActStep0901 extends StageActivity {
    private final int MAX_UPPER_COUNT = 4;
    private final int MAX_UNDER_COUNT = 3;

    private final LinearLayout linearLayout[] = new LinearLayout[2];
    private final ImageView imgUpper[] = new ImageView[MAX_UPPER_COUNT];
    private final ImageView imgUnder[] = new ImageView[MAX_UNDER_COUNT];
    private final View emptySpace[] = new View[2];
    public final Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iAnswer = 0;
    public int iPastAnswer = 0;

    public Step0901DataSet dataSet = new Step0901DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_09_1);

        linearLayout[0] = (LinearLayout) findViewById(R.id.linear_upper);
        linearLayout[1] = (LinearLayout) findViewById(R.id.linear_under);
        imgUpper[0] = (ImageView) findViewById(R.id.img_upper_1);
        imgUpper[1] = (ImageView) findViewById(R.id.img_upper_2);
        imgUpper[2] = (ImageView) findViewById(R.id.img_upper_3);
        imgUpper[3] = (ImageView) findViewById(R.id.img_upper_4);
        imgUnder[0] = (ImageView) findViewById(R.id.img_under_1);
        imgUnder[1] = (ImageView) findViewById(R.id.img_under_2);
        imgUnder[2] = (ImageView) findViewById(R.id.img_under_3);
        emptySpace[0] = (View) findViewById(R.id.empty_upper_left);
        emptySpace[1] = (View) findViewById(R.id.empty_under_right);
        btnAnswer[0] = (Button) findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button) findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button) findViewById(R.id.btn_answer_3);

        emptySpace[0].setVisibility(View.GONE);

        for (int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((Button) v).getText().charAt(0) == dataSet.iAnswer + '0') isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object) {
        dataSet.setData(iStage);

        if (iStage == NUM_OF_STAGE) {
            for (int i = 0; i < 2; i++) {
                LinearLayout.LayoutParams linearParam = (LinearLayout.LayoutParams) linearLayout[i].getLayoutParams();
                linearParam.height = (int) getResources().getDimension(R.dimen.wp30);
                linearLayout[i].setLayoutParams(linearParam);
            }

            for (int i = 0; i < MAX_UPPER_COUNT; i++) {
                LinearLayout.LayoutParams imgParam = (LinearLayout.LayoutParams) imgUpper[i].getLayoutParams();
                imgParam.height = (int) getResources().getDimension(R.dimen.wp28);
                imgUpper[i].setLayoutParams(imgParam);
                imgUpper[i].setVisibility(View.VISIBLE);
            }

            imgUpper[3].setVisibility(View.GONE);
            if (dataSet.iAnswer == 6) emptySpace[0].setVisibility(View.VISIBLE);
            else {
                emptySpace[1].setVisibility(View.GONE);
                imgUnder[2].setVisibility(View.GONE);
            }

            for (int i = 0; i < 3; i++) imgUpper[i].setImageResource(dataSet.iPictureSource[i]);
            for (int i = dataSet.iAnswer - 1; i >= 3; i--)
                imgUnder[i - 3].setImageResource(dataSet.iPictureSource[i]);
        } else {
            for (int i = 0; i < MAX_UPPER_COUNT; i++) {
                if (i < dataSet.iAnswer) {
                    imgUpper[i].setVisibility(View.VISIBLE);
                    imgUpper[i].setImageResource(dataSet.iPictureSource[i]);
                } else imgUpper[i].setVisibility(View.GONE);
            }
        }

        int iNextExample = dataSet.iAnswer - rand.nextInt(3);
        if (iNextExample <= 0) iNextExample = 1;

        for (int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i) + "가지");

        StartRecording();
    }

    public synchronized void checkAnswer(Object o) {
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if (isRight || iRetryCount > 1) StopRecording(isRight);

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

    public synchronized void goNext(Object object) {
        Intent intent = new Intent(this, ActStep0902.class);
        startActivity(intent);
    }

    public class Step0901DataSet {
        private final int MAX_CAN_COUNT = 6;
        private final int iImageResource[] = {R.drawable.icon_recyclecan_glass, R.drawable.icon_recyclecan_metal, R.drawable.icon_recyclecan_paper, R.drawable.icon_recyclecan_plastic, R.drawable.icon_recyclecan_styrofoam, R.drawable.icon_recyclecan_vinyl};

        public int iAnswer;
        public int iPictureSource[] = new int[MAX_CAN_COUNT];

        public void setData(int iStage) {
            iAnswer = (iStage == NUM_OF_STAGE ? 5 + rand.nextInt(2) : iPastAnswer);
            while (iAnswer == iPastAnswer) iAnswer = 2 + rand.nextInt(3);

            boolean bSelected[] = new boolean[MAX_CAN_COUNT];
            for (int i = 0; i < iAnswer; i++) {
                int iSelectCan = rand.nextInt(MAX_CAN_COUNT);
                while (bSelected[iSelectCan]) iSelectCan = rand.nextInt(MAX_CAN_COUNT);

                iPictureSource[i] = iImageResource[iSelectCan];
                bSelected[iSelectCan] = true;
            }

            iPastAnswer = iAnswer;
        }
    }
}