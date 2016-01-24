package src.activities.Step04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.ActStartLearning;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-05-06.
 */
public class ActStep0404 extends StageActivity{
    private LinearLayout linearFrame[] = new LinearLayout[2];
    private ImageView imgDisplay[] = new ImageView[2];
    private TextView txtAnswerDescription[] = new TextView[2];
    private View viewLine[] = new View[2];
    private Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    private Random rand = new Random();
    public Step0404DataSet dataSet = new Step0404DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_04_4);

        linearFrame[0] = (LinearLayout)findViewById(R.id.linear_frame_1_3);
        linearFrame[1] = (LinearLayout)findViewById(R.id.linear_frame_4_5);
        imgDisplay[0] = (ImageView)findViewById(R.id.img_drawer);
        imgDisplay[1] = (ImageView)findViewById(R.id.img_display_stand);
        txtAnswerDescription[0] = (TextView)findViewById(R.id.txt_answer_description_1);
        txtAnswerDescription[1] = (TextView)findViewById(R.id.txt_answer_description_2);
        viewLine[0] = (View)findViewById(R.id.view_line_1);
        viewLine[1] = (View)findViewById(R.id.view_line_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        linearFrame[1].setVisibility(View.GONE);

        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(Integer.parseInt(((Button)v).getText().toString()) == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        float fTextMarginTop[] = {getResources().getDimension(R.dimen.wp10), getResources().getDimension(R.dimen.wp20), getResources().getDimension(R.dimen.wp28),
                getResources().getDimension(R.dimen.wp38), getResources().getDimension(R.dimen.wp18), getResources().getDimension(R.dimen.wp15)};
        float fLineMarginTop[] = {getResources().getDimension(R.dimen.wp15), getResources().getDimension(R.dimen.wp25), getResources().getDimension(R.dimen.wp33),
                getResources().getDimension(R.dimen.wp43), getResources().getDimension(R.dimen.wp25), getResources().getDimension(R.dimen.wp22)};
        float fLineWidth[] = {-1, -1, -1, -1, getResources().getDimension(R.dimen.wp33), getResources().getDimension(R.dimen.wp14)};
        float fLineMarginRight[] = {-1, -1, -1, -1, getResources().getDimension(R.dimen.wp14), getResources().getDimension(R.dimen.wp4)};

        dataSet.setData(iStage);

        int iComponentIndex = (iStage <= 3 ? 0 : 1);

        if(iStage == 4){
            linearFrame[0].setVisibility(View.GONE);
            linearFrame[1].setVisibility(View.VISIBLE);
        }

        LinearLayout.LayoutParams txtParam = (LinearLayout.LayoutParams) txtAnswerDescription[iComponentIndex].getLayoutParams();
        txtParam.topMargin = (int)fTextMarginTop[iStage];
        txtAnswerDescription[iComponentIndex].setLayoutParams(txtParam);

        FrameLayout.LayoutParams lineParam = (FrameLayout.LayoutParams) viewLine[iComponentIndex].getLayoutParams();
        lineParam.topMargin = (int)fLineMarginTop[iStage];
        if(iComponentIndex == 1){
            lineParam.width = (int)fLineWidth[iStage];
            lineParam.rightMargin = (int)fLineMarginRight[iStage];
        } viewLine[iComponentIndex].setLayoutParams(lineParam);

        imgDisplay[iComponentIndex].setImageResource(dataSet.iImageResource);
        txtAnswerDescription[iComponentIndex].setText(dataSet.sAnswerDescription);
        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText(dataSet.sAnswerExample[i]);

        StartRecording();
    }


    public synchronized void checkAnswer(Object object){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if(isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iStage++;
                    if(iStage > NUM_OF_STAGE) goNext();
                    else {
                        iRetryCount = 0;
                        setQuestion(false);
                    }
                }
                else{
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0405.class);
        startActivity(intent);
    }

    public class Step0404DataSet{
        private final int arrRandomBase[][] = {{2, 3}, {3, 4}, {4, 3}, {6, 1}, {7, 2}};
        private final int arrRandomRange[][] = {{3, 1}, {3, 1}, {4, 1}, {5, 3}, {4, 3}};
        private final int arrImageResource[][] = {{R.drawable.img_drawer_floor_2, R.drawable.img_drawer_floor_3, R.drawable.img_drawer_floor_4},
                {R.drawable.img_display_stand_springonion_1, R.drawable.img_display_stand_springonion_2, R.drawable.img_display_stand_springonion_3},
                {R.drawable.img_display_stand_onion_2, R.drawable.img_display_stand_onion_3, R.drawable.img_display_stand_onion_4}};
        private final String arrUnit[][] = {{"바지", "양말", "티셔츠", "양파", "파"},
                {"벌씩", "짝", "장씩", "개", "개짜리"},
                {"단", "줄", "줄", "묶음", "단"}};

        public int iImageResource;
        public String sAnswerDescription;
        public String sAnswerExample[] = new String[3];
        public int iAnswer;

        void setData(int iStage) {
            int iSeed = iStage - 1;
            int iLeftValue = arrRandomBase[iSeed][0] + rand.nextInt(arrRandomRange[iSeed][0]);
            int iRightValue = arrRandomBase[iSeed][1] + rand.nextInt(arrRandomRange[iSeed][1]);

            if(iSeed < 3) iImageResource = arrImageResource[0][iSeed];
            else iImageResource = arrImageResource[iSeed - 2][iRightValue - arrRandomBase[iSeed][1]];

            sAnswerDescription = arrUnit[0][iSeed] + " " + iLeftValue + arrUnit[1][iSeed] + " "  + iRightValue + arrUnit[2][iSeed];
            iAnswer = iLeftValue * iRightValue;

            int iPastValue = iLeftValue * (iRightValue - rand.nextInt(3));
            if(iPastValue <= 0) iPastValue = iLeftValue;
            for(int i = 0; i < 3; i++){
                sAnswerExample[i] = "" + iPastValue;
                iPastValue += iLeftValue;
            }
        }
    }
}