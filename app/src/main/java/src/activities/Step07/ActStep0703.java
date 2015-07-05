package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-07-04.
 */
public class ActStep0703 extends StageActivity {

    private static final int BUTTON_COUNT = 7;

    private TextView txtDescription;
    public final Button btnAnswerButton[] = new Button[BUTTON_COUNT];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0703DataSet dataSet = new Step0703DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_3);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        btnAnswerButton[0] = (Button)findViewById(R.id.btn_bank_name);
        btnAnswerButton[1] = (Button)findViewById(R.id.btn_account_number);
        btnAnswerButton[2] = (Button)findViewById(R.id.btn_deal_date_2);
        btnAnswerButton[3] = (Button)findViewById(R.id.btn_deal_date_4);
        btnAnswerButton[4] = (Button)findViewById(R.id.btn_deal_date_6);
        btnAnswerButton[5] = (Button)findViewById(R.id.btn_balance_3);
        btnAnswerButton[6] = (Button)findViewById(R.id.btn_balance_6);

        //button listener
        for(int i = 0; i < BUTTON_COUNT; i++){
            btnAnswerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int iButtonIndex = 0;
                    for(int j = 0; j < BUTTON_COUNT; j++)
                        if(v == btnAnswerButton[j]) iButtonIndex = j;

                    if(iButtonIndex == dataSet.iAnswerIndex)isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        }

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        txtDescription.setText(dataSet.sDescription);

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
                    iStage++;
                    iRetryCount = 0;

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
        Intent intent = new Intent(this, ActMain.class);
        startActivity(intent);
    }

    public class Step0703DataSet {
        private final String arrDescription[] = {"아래 통장에서 계좌번호를 찾아 누르세요!",
                "아래 통장에서 ‘김수현’에게  10000원을 이체한 날짜를 찾아 누르세요!",
                "아래 통장에서 ‘이제신’에게 10000원을 입금하고 난 후\n통장에 남은 금액은 얼마인지 찾아 누르세요!",
                "아래 통장을 보고 ‘남동식’에게 10000원을 입금하고 난 후\n통장에 남은 금액은 얼마인지 찾아 누르세요!",
                "아래 통장에서 ‘민형준’에게 이체한 날짜를 찾아 누르세요!\n"};
        private final int arrAnswerButtonIndex[] = {1, 2, 5, 6, 3};

        public String sDescription;
        public int iAnswerIndex;

        public void setData(int iSeed) {
            sDescription = arrDescription[iSeed];
            iAnswerIndex = arrAnswerButtonIndex[iSeed];
        }
    }
}