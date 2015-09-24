package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import src.viewes.DrawView;

/**
 * Created by Acka on 2015-05-19.
 */
public class ActStep0503 extends StageActivity {
    private ImageButton ibtnPotSmall;
    private ImageView imgPotBig;
    private TextView txtPotSamllCapacity;
    private TextView txtPotBigCapacity;
    public final Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0503DataSet dataSet = new Step0503DataSet();
    private Random rand = new Random();

    private int iBigPotFill;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_3);

        ibtnPotSmall = (ImageButton)findViewById(R.id.ibtn_pot_small);
        imgPotBig = (ImageView)findViewById(R.id.img_pot_big);
        txtPotSamllCapacity = (TextView)findViewById(R.id.txt_pot_small_capacity);
        txtPotBigCapacity = (TextView)findViewById(R.id.txt_pot_big_capacity);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        ibtnPotSmall.setOnClickListener(clickSmallPot);
        for(int i = 0; i < 3; i++)
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

    View.OnClickListener clickSmallPot = new View.OnClickListener() {
        public void onClick(View v) {
            iBigPotFill += dataSet.iPotSmallCapacity;
            if(iBigPotFill > dataSet.iPotBigCapacity)
                iBigPotFill = 0;

            imgPotBig.setImageResource(dataSet.iPotBigImage[iBigPotFill]);
        }
    };

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        iBigPotFill = 0;
        imgPotBig.setImageResource(dataSet.iPotBigImage[iBigPotFill]);
        txtPotSamllCapacity.setText("" + dataSet.iPotSmallCapacity + "되");
        txtPotBigCapacity.setText("" + dataSet.iPotBigCapacity + "되");

        int iNextExample = dataSet.iAnswer - rand.nextInt(2);
        if(iNextExample < 0) iNextExample = 0;

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));

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
        Intent intent = new Intent(this, ActStep0504.class);
        startActivity(intent);
    }

    public class Step0503DataSet {
        private final int arrPotBigCapacity[] = {12, 12, 12, 12, 12};
        private final int arrPotSmallCapacity[] = {1, 2, 3, 4, 6};
        public final int arrPotBigImageSource[][] = new int[13][13];

        public int iPotBigCapacity;
        public int iPotSmallCapacity;
        public int iPotBigImage[] = new int[13];
        public int iAnswer;

        public Step0503DataSet() {
            arrPotBigImageSource[12][0] = R.drawable.pot_0_percent;
            arrPotBigImageSource[12][1] = R.drawable.pot_8_percent;
            arrPotBigImageSource[12][2] = R.drawable.pot_17_percent;
            arrPotBigImageSource[12][3] = R.drawable.pot_25_percent;
            arrPotBigImageSource[12][4] = R.drawable.pot_33_percent;
            arrPotBigImageSource[12][5] = R.drawable.pot_42_percent;
            arrPotBigImageSource[12][6] = R.drawable.pot_50_percent;
            arrPotBigImageSource[12][7] = R.drawable.pot_59_percent;
            arrPotBigImageSource[12][8] = R.drawable.pot_67_percent;
            arrPotBigImageSource[12][9] = R.drawable.pot_75_percent;
            arrPotBigImageSource[12][10] = R.drawable.pot_83_percent;
            arrPotBigImageSource[12][11] = R.drawable.pot_92_percent;
            arrPotBigImageSource[12][12] = R.drawable.pot_100_percent;
        }

        public void setData(int iSeed) {
            iPotBigCapacity = arrPotBigCapacity[iSeed];
            iPotSmallCapacity = arrPotSmallCapacity[iSeed];

            for(int i = 0; i <= iPotBigCapacity; i++)
                iPotBigImage[i] = arrPotBigImageSource[iPotBigCapacity][i];

            iAnswer = iPotBigCapacity / iPotSmallCapacity;
        }
    }
}