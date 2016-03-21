package src.activities.Step08;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by jhobo_000 on 2015-06-24.
 */
public class Step0803Activity extends StageActivity {

    private TextView txtDiscription;
    private ImageView img;
    private Button btnAnswer[] = new Button[3];
    private PhotoViewAttacher mAttacher;
    private ImageButton IbtnAnswer[] = new ImageButton[4];

    private boolean ans;
    private static int Count = 0;
    public Step0803DataSet dataSet = new Step0803DataSet();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_08_03);

        txtDiscription = (TextView)findViewById(R.id.txt_discription);

        img = (ImageView)findViewById(R.id.img_6_4);

        IbtnAnswer[0] = (ImageButton)findViewById(R.id.btn_ans_1);
        IbtnAnswer[1] = (ImageButton)findViewById(R.id.btn_ans_2);
        IbtnAnswer[2] = (ImageButton)findViewById(R.id.btn_ans_3);
        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_4);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_5);

        mAttacher = new PhotoViewAttacher(img);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_XY);

        for(int i = 0; i < 3; i++){
            IbtnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(iStage <= 3) {
                        if(v.getId() == R.id.btn_ans_1 && dataSet.iAns == 0)
                            ans = true;
                        else if(v.getId() == R.id.btn_ans_2 && dataSet.iAns == 1)
                            ans = true;
                        else if(v.getId() == R.id.btn_ans_3 && dataSet.iAns == 2)
                            ans = true;
                        else ans = false;

                        checkAnswer();
                    }
                }
            });
        } // end of button listener

        for(int i = 0; i < 2; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Button btnCurrentButton = (Button)v;

                    if(iStage >= 4) {
                        String iSelectAnswer = btnCurrentButton.getText().toString();

                        if (iSelectAnswer.equals(dataSet.strAns)) ans = true;
                        else ans = false;

                        checkAnswer();
                    }
                }
            });
        } // end of button listener

        setQuestion(false);
    }


    @Override
    public synchronized void setQuestion(boolean isRetry, Object object) {
        int Seed = iStage - 1;

        dataSet.setData(Seed);

        txtDiscription.setText(dataSet.Discription);
        img.setImageResource(dataSet.img);

        // btn set


        if(Seed >= 3) {
            IbtnAnswer[0].setVisibility(View.GONE);
            IbtnAnswer[1].setVisibility(View.GONE);
            IbtnAnswer[2].setVisibility(View.GONE);
            btnAnswer[0].setVisibility(View.VISIBLE);
            btnAnswer[1].setVisibility(View.VISIBLE);

            btnAnswer[0].setText(dataSet.btnTxt[0]);
            btnAnswer[1].setText(dataSet.btnTxt[1]);
        }
        else
        {
            IbtnAnswer[0].setVisibility(View.VISIBLE);
            IbtnAnswer[1].setVisibility(View.VISIBLE);
            IbtnAnswer[2].setVisibility(View.VISIBLE);
            btnAnswer[0].setVisibility(View.GONE);
            btnAnswer[1].setVisibility(View.GONE);
        }

        startRecording();
    }

    @Override
    public synchronized void checkAnswer(Object object) {
        DlgResultMark dlg = new DlgResultMark(this, ans);
        dlg.show();
        countUpTry();
        if(ans || Count > 1) stopRecording(ans);

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
    public synchronized void goNext(Object object) {
        Intent intent = new Intent(this, Step0804Activity.class);
        startActivity(intent);
    }
}