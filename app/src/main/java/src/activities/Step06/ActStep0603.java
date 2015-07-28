package src.activities.Step06;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-07-05.
 */

public class ActStep0603 extends StageActivity {

    private TextView txtDiscription;
    private ImageView[]img1 = new ImageView[2]; // 처음 2개그림..
    private ImageView img2;   // 나중 1개 표..
    private Button btnAnswer[] = new Button[4]; // 버튼 4개짜리
    private Button next;
    private boolean ans;
    private static int Count = 0;

    private Button answer;

    private int imgList[];

    private TextView[] txtNum = new TextView[2]; //표 젤왼쪽 숫자..7,8만 없앨 용도..
    private TextView txtLeft, txtRight; // 과일 이름(감,사과 등..)
    private TextView txtTriL, txtTriR;

    private ImageView[] imgLeftGraph = new ImageView[9];
    private ImageView[] imgRightGraph = new ImageView[9]; // 표에 이미지 넣는거..

    private ImageButton IBleft1, IBleft2, IBright1, IBright2; // 삼각형..

    private LinearLayout layout1, layout2, layout3, layout4;

    public Step0603DataSet dataSet = new Step0603DataSet();

    private static int leftCount, rightCount;
    private static int lineMax[]={6,8,6,0,0};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_06_3);

        layout1 = (LinearLayout)findViewById(R.id.layout_6_1);
        layout2 = (LinearLayout)findViewById(R.id.layout_6_2);
        layout3 = (LinearLayout)findViewById(R.id.layout_6_1_1);
        layout4 = (LinearLayout)findViewById(R.id.layout_6_1_2);

        txtTriL = (TextView)findViewById(R.id.txt_fruit_left);
        txtTriR = (TextView)findViewById(R.id.txt_fruit_right);

        img1[0] = (ImageView)findViewById(R.id.img_6_3_1);
        img1[1] = (ImageView)findViewById(R.id.img_6_3_2);
        img2 = (ImageView)findViewById(R.id.img_6_3_3);

        // 설명
        txtDiscription = (TextView)findViewById(R.id.txt_discription);
        // 삼각형
        IBleft1 = (ImageButton)findViewById(R.id.triangle_left_1);
        IBleft2 = (ImageButton)findViewById(R.id.triangle_left_2);
        IBright1 = (ImageButton)findViewById(R.id.triangle_right_1);
        IBright2 = (ImageButton)findViewById(R.id.triangle_right_2);

        IBleft1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftCount > 0) {
                    leftCount--;
                    imgLeftGraph[7 - leftCount].setImageDrawable(null);
                }
            }
        });
        IBleft2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightCount > 0) {
                    rightCount--;
                    imgRightGraph[7 - rightCount].setImageDrawable(null);
                }
            }
        });
        IBright1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftCount < lineMax[iStage - 1]) {
                    imgLeftGraph[7 -leftCount].setImageResource(dataSet.imgGraph[0]);
                    leftCount++;
                }
            }
        });
        IBright2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightCount < lineMax[iStage - 1]) {
                    imgRightGraph[7 -rightCount].setImageResource(dataSet.imgGraph[1]);
                    rightCount++;
                }
            }
        });

        //표왼쪽 숫자..
        txtNum[0] = (TextView)findViewById(R.id.cell_0_0);
        txtNum[1] = (TextView)findViewById(R.id.cell_1_0);

        //표
        imgLeftGraph[0] = (ImageView)findViewById(R.id.cell_0_1);
        imgLeftGraph[1] = (ImageView)findViewById(R.id.cell_1_1);
        imgLeftGraph[2] = (ImageView)findViewById(R.id.cell_2_1);
        imgLeftGraph[3] = (ImageView)findViewById(R.id.cell_3_1);
        imgLeftGraph[4] = (ImageView)findViewById(R.id.cell_4_1);
        imgLeftGraph[5] = (ImageView)findViewById(R.id.cell_5_1);
        imgLeftGraph[6] = (ImageView)findViewById(R.id.cell_6_1);
        imgLeftGraph[7] = (ImageView)findViewById(R.id.cell_7_1);

        imgRightGraph[0] = (ImageView)findViewById(R.id.cell_0_2);
        imgRightGraph[1] = (ImageView)findViewById(R.id.cell_1_2);
        imgRightGraph[2] = (ImageView)findViewById(R.id.cell_2_2);
        imgRightGraph[3] = (ImageView)findViewById(R.id.cell_3_2);
        imgRightGraph[4] = (ImageView)findViewById(R.id.cell_4_2);
        imgRightGraph[5] = (ImageView)findViewById(R.id.cell_5_2);
        imgRightGraph[6] = (ImageView)findViewById(R.id.cell_6_2);
        imgRightGraph[7] = (ImageView)findViewById(R.id.cell_7_2);

        // 버튼 4개짜리
        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_ans_3);
        btnAnswer[3] = (Button)findViewById(R.id.btn_ans_4);

        answer = (Button)findViewById(R.id.btn_ans);
        answer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(leftCount == dataSet.iAns[0] && rightCount == dataSet.iAns[1]) ans = true;
                else ans = false;

                checkAnswer();
            }
        });

        for(int i = 0; i < 4; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Button btnCurrentButton = (Button)v;
                    String iSelectAnswer = btnCurrentButton.getText().toString();

                    if(iSelectAnswer.equals(dataSet.strAns)) ans = true;
                    else ans = false;

                    checkAnswer();
                }
            });
        } // end of button listener

        // 과일이름
        txtLeft = (TextView)findViewById(R.id.cell_8_1);
        txtRight = (TextView)findViewById(R.id.cell_8_2);

        setQuestion(false);
    }

    @Override
    public void setQuestion(boolean isRetry, Object object) {
        int Seed = iStage - 1;

        leftCount = rightCount = 0;

        dataSet.setData(Seed);

        if(Seed < 3)
        {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
        }
        else
        {
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
        }

        img1[0].setImageResource(dataSet.img[0]);
        img1[1].setImageResource(dataSet.img[1]);
        img2.setImageResource(dataSet.img[0]);

        txtLeft.setText(dataSet.fruit[0]);
        txtTriL.setText(dataSet.fruit[0]);
        txtRight.setText(dataSet.fruit[1]);
        txtTriR.setText(dataSet.fruit[1]);

        txtDiscription.setText(dataSet.Discription);

        for(int i = 0 ; i < 8 ; i ++)
        {
            imgLeftGraph[i].setImageDrawable(null);
            imgRightGraph[i].setImageDrawable(null);
        }


        for(int i = 0 ; i < 4 ; i ++)
            btnAnswer[i].setText(dataSet.btnTxt[i]);
    }

    @Override
    public void checkAnswer(Object object) {
        DlgResultMark dlg = new DlgResultMark(this, ans);
        dlg.show();
       dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(ans || Count > 1){
                    iStage++;
                    if(iStage > NUM_OF_STAGE) goNext();
                    else {
                        Count = 0;
                        setQuestion(false);
                    }
                }
                else{
                    Count++;
                }
            }
        });
    }

    @Override
    public void goNext(Object object) {
        Intent intent = new Intent(this, ActStep0604.class);
        startActivity(intent);
    }
}