package src.activities.Step05;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-05-19.
 */
public class ActStep0503 extends StageActivity {
    private ImageButton ibtnPotSmall;
    private ImageView imgWater;
    private TextView txtPotSamllCapacity;
    private TextView txtPotBigCapacity;
    public final Button btnAnswer[] = new Button[3];

    public  int iWaterLevel = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0503DataSet dataSet = new Step0503DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_05_3);

        ibtnPotSmall = (ImageButton) findViewById(R.id.ibtn_pot_small);
        imgWater = (ImageView) findViewById(R.id.img_water_in_pot);
        txtPotSamllCapacity = (TextView) findViewById(R.id.txt_small_pot_capacity);
        txtPotBigCapacity = (TextView) findViewById(R.id.txt_big_pot_capacity);
        btnAnswer[0] = (Button) findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button) findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button) findViewById(R.id.btn_answer_3);

        ibtnPotSmall.setOnClickListener(clickSmallPot);

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

    View.OnClickListener clickSmallPot = new View.OnClickListener() {
        public void onClick(View v) {
            Bitmap bmFull = BitmapFactory.decodeResource(getResources(), R.drawable.img_water_in_pot);
            imgWater.setImageBitmap(cropBitmapHeight(bmFull, (100 * iWaterLevel) / dataSet.iAnswer));
        }
    };

    private Bitmap cropBitmapHeight(Bitmap bmOrigin, int iHeightRatio) {
        if (iHeightRatio <= 1) iHeightRatio = 100;
        else if (iHeightRatio >= 99) iHeightRatio = 0;
        else{
            iHeightRatio = 90 - ((iHeightRatio * 70) / 100) ;
            iHeightRatio -= iHeightRatio / 10;
        }

        int iHeight = bmOrigin.getHeight() * iHeightRatio / 100;

        Bitmap bmOverlay = Bitmap.createBitmap(bmOrigin.getWidth(), bmOrigin.getHeight(), Bitmap.Config.ARGB_8888);

        Paint p = new Paint();
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Canvas c = new Canvas(bmOverlay);
        c.drawBitmap(bmOrigin, 0, 0, null);
        c.drawRect(0, 0, bmOrigin.getWidth(), iHeight, p);

        iWaterLevel++; if(iWaterLevel > dataSet.iAnswer) iWaterLevel = 0;
        return bmOverlay;
    }

    public synchronized void setQuestion(boolean isRetry, Object object) {
        dataSet.setData(iStage);

        iWaterLevel = 0;
        Bitmap bmFull = BitmapFactory.decodeResource(getResources(), R.drawable.img_water_in_pot);
        imgWater.setImageBitmap(cropBitmapHeight(bmFull, 0));

        txtPotBigCapacity.setText("" + dataSet.iBigCapacity + "되");
        txtPotSamllCapacity.setText("" + dataSet.iSmallCapacity + "되");

        int iNextExample = dataSet.iAnswer - rand.nextInt(2);
        for (int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));

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
        Intent intent = new Intent(this, ActStep0504.class);
        startActivity(intent);
    }

    public class Step0503DataSet {
        private int arrRandomBase[] = {10, 14, 16, 20};
        private int arrRandomRange[] = {6, 11, 20, 30};

        public int iAnswer;
        public int iBigCapacity;
        public int iSmallCapacity;

        public void setData(int iStage) {
            int iSeed = iStage - 1;

            do{
                int iDivisorOrder = 0;
                iBigCapacity = arrRandomBase[iSeed] + rand.nextInt(arrRandomRange[iSeed]);
                for(iSmallCapacity = 2; iSmallCapacity <= iBigCapacity; iSmallCapacity++) {
                    if ((iBigCapacity % iSmallCapacity) == 0) iDivisorOrder++;
                    if (iDivisorOrder == iStage) break;
                }

            } while(iSmallCapacity < 2 || iSmallCapacity >= 10);

            iAnswer = iBigCapacity / iSmallCapacity;
        }
    }
}