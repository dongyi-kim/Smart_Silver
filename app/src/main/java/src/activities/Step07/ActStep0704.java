package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-08-04.
 */
public class ActStep0704  extends StageActivity {

    private static final int BUTTON_COUNT = 7;

    private LinearLayout linearRequestList;
    private LinearLayout linearRequestMoney[] = new LinearLayout[2];
    private LinearLayout linearClientList[] = new LinearLayout[2];
    private LinearLayout linearDefaultList[] = new LinearLayout[2];
    private LinearLayout linearUsing[] = new LinearLayout[2];
    private LinearLayout linearTaxBill[] = new LinearLayout[2];

    private TextView txtDescription;
    public final Button btnAnswerButton[] = new Button[BUTTON_COUNT];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0703DataSet dataSet = new Step0703DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_4);

        linearRequestList = (LinearLayout)findViewById(R.id.linear_request_list);
        linearRequestMoney[0] = (LinearLayout)findViewById(R.id.linear_request_money_head);
        linearRequestMoney[1] = (LinearLayout)findViewById(R.id.linear_request_money_contents);
        linearClientList[0] = (LinearLayout)findViewById(R.id.linear_client_list_head);
        linearClientList[1] = (LinearLayout)findViewById(R.id.linear_client_list_contents);
        linearDefaultList[0] = (LinearLayout)findViewById(R.id.linear_default_list_head);
        linearDefaultList[1] = (LinearLayout)findViewById(R.id.linear_default_list_contents);
        linearUsing[0] = (LinearLayout)findViewById(R.id.linear_using_list);
        linearUsing[1] = (LinearLayout)findViewById(R.id.linear_using_compare);
        linearTaxBill[0] = (LinearLayout)findViewById(R.id.linear_tax_bill_head);
        linearTaxBill[1] = (LinearLayout)findViewById(R.id.linear_tax_bill_contents);

        ((GradientDrawable)linearRequestList.getBackground()).setStroke(2, 0xFF4488DD);
        ((GradientDrawable)linearRequestMoney[0].getBackground()).setColor(0xFFEE6611);
        ((GradientDrawable)linearRequestMoney[0].getBackground()).setStroke(2, 0xFFEE6611);
        ((GradientDrawable)linearRequestMoney[1].getBackground()).setColor(0xFFFFEEDD);
        ((GradientDrawable)linearRequestMoney[1].getBackground()).setStroke(2, 0xFFEE5511);
        ((GradientDrawable)linearClientList[0].getBackground()).setColor(0xFF3399EE);
        ((GradientDrawable)linearClientList[0].getBackground()).setStroke(2, 0xFF3399EE);
        ((GradientDrawable)linearClientList[1].getBackground()).setColor(0xFFDDEEFF);
        ((GradientDrawable)linearClientList[1].getBackground()).setStroke(2, 0xFFDDEEFF);
        ((GradientDrawable)linearDefaultList[0].getBackground()).setColor(0xFF331155);
        ((GradientDrawable)linearDefaultList[0].getBackground()).setStroke(2, 0xFF331155);
        ((GradientDrawable)linearDefaultList[1].getBackground()).setStroke(2, 0xFF331155);
        ((GradientDrawable)linearUsing[0].getBackground()).setColor(0xFF6688FF);
        ((GradientDrawable)linearUsing[0].getBackground()).setStroke(2, 0xFF6688FF);
        ((GradientDrawable)linearUsing[1].getBackground()).setColor(0xFF6688FF);
        ((GradientDrawable)linearUsing[1].getBackground()).setStroke(2, 0xFF6688FF);
        ((GradientDrawable)linearTaxBill[0].getBackground()).setColor(0xFF6688FF);
        ((GradientDrawable)linearTaxBill[0].getBackground()).setStroke(2, 0xFF6688FF);
        ((GradientDrawable)linearTaxBill[1].getBackground()).setStroke(2, 0xFF6688FF);

/*
        linearTest = (LinearLayout)findViewById(R.id.linear_test);
        GradientDrawable bgShape = (GradientDrawable)linearTest.getBackground();
        bgShape.setStroke(3, 0xFFFF0000);
*/
/*
        //button listener
        for(int i = 0; i < BUTTON_COUNT; i++){
            btnAnswerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                }
            });
        }
*/
        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;

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
        /*
        private final String arrDescription[] = {"아래 통장에서 계좌번호를 찾아 누르세요!",
                "아래 통장에서 ‘김수현’이  10,000원을 이체한 날짜를 몇 년 몇 월 며칠인가요?",
                "아래 통장에서 ‘이제신’이 20,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요.",
                "아래 통장을 보고 ‘남동식’이  10,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요.",
                "아래 통장에서 ‘민형준’이 이체한 날짜를 찾아 누르세요."};
        private final int arrAnswerButtonIndex[] = {1, 2, 5, 6, 3};

        public String sDescription;
        public int iAnswerIndex;

        public void setData(int iSeed) {
            sDescription = arrDescription[iSeed];
            iAnswerIndex = arrAnswerButtonIndex[iSeed];
        }
        */
    }
}
