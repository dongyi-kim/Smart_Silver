package src.activities.Step08;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.ActStartLearning;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-07-05.
 */

public class Step0804Activity extends StageActivity {

    private TextView txtDiscription;
    private ImageView img;
    private Button btnAnswer[] = new Button[3];

    private boolean ans;
    private static int Count = 0;

    public Step0804DataSet dataSet = new Step0804DataSet();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_08_04);

        txtDiscription = (TextView)findViewById(R.id.txt_discription);

        img = (ImageView)findViewById(R.id.img_6_5);

        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_2);

        for(int i = 0; i < 2; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btnCurrentButton = (Button) v;

                    String iSelectAnswer = btnCurrentButton.getText().toString();

                    if (iSelectAnswer.equals(dataSet.strAns)) ans = true;
                    else ans = false;

                    checkAnswer();
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

        btnAnswer[0].setText(dataSet.btnTxt[0]);
        btnAnswer[1].setText(dataSet.btnTxt[1]);

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
        Intent intent = new Intent(this, ActStartLearning.class);
        startActivity(intent);
    }
}