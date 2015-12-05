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

public class ActStep0704 extends StageActivity {

    private TextView txtDescription;
    private Button btnlist[] = new Button[18];
    private TextView txtlist[] = new TextView[18];
    private String money[] = new String[6];
    private TextView iaccount;

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0704DataSet dataSet = new Step0704DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_3);

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
        Intent intent = new Intent(this, ActStep0704.class);
        startActivity(intent);
    }

    public class Step0704DataSet {
        private final String arrDescription[] = {"전기요금 납기일을 나타내는 곳을 찾아 누르세요.",
                "전기요금 청구금액을 나타내는 곳을 찾아 누르세요.",
                "아래 자료에서 당월 전기사용량을 나타내는 곳을 찾아 누르세요.",
                "아래 영수증은 평생마트 영수증입니다. 모듬 버섯 불고기는 얼마인지 찾아 누르세요.",
                "아래 영수증은 평생마트 영수증입니다. 평생마트에서 물건을 산 날짜는 언제인지 찾아 누르세요."};
        private final int arrAnswerButtonIndex[] = {0, 4, 2};
        private final int arrReceiptImage[] = {R.drawable.receipt_7_4_4_1, R.drawable.receipt_7_4_5_1};

        private final String  accountList[]={"계좌번호 123456-78-9101112", "계좌번호 100037-56-166616", "계좌번호 222-11-4321-123"};
        private final String  moneyList[][]={{"310,000 ", "320,000 ","340,000 ","360,000 ","370,000 ","380,000 "},{"410,000 ","420,000 ","440,000 ","460,000 ","470,000 ","480,000 "}, {"357,200 ","367,200 ","387,200 ","407,200 ","417,200 ","427,200 "}};

        public String sDescription;
        public String account;
        public int iAnswerIndex;
        public int iBtnList[] = new int[3];
        public String money[] = new String[6];

        public void setData(int iSeed)
        {
        }
    }
}
