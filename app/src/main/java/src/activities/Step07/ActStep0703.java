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

    private TextView txtDescription;
    private Button btnlist[] = new Button[18];
    private TextView txtlist[] = new TextView[18];
    private String money[] = new String[6];
    private TextView iaccount;

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0703DataSet dataSet = new Step0703DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_3);

        txtDescription = (TextView)findViewById(R.id.txt_description);

        btnlist[0] = (Button)findViewById(R.id.btn_1);
        btnlist[1] = (Button)findViewById(R.id.btn_2);
        btnlist[2] = (Button)findViewById(R.id.btn_3);
        btnlist[3] = (Button)findViewById(R.id.btn_4);
        btnlist[4] = (Button)findViewById(R.id.btn_5);
        btnlist[5] = (Button)findViewById(R.id.btn_6);
        btnlist[6] = (Button)findViewById(R.id.btn_7);
        btnlist[7] = (Button)findViewById(R.id.btn_8);
        btnlist[8] = (Button)findViewById(R.id.btn_9);
        btnlist[9] = (Button)findViewById(R.id.btn_10);
        btnlist[10] = (Button)findViewById(R.id.btn_11);
        btnlist[11] = (Button)findViewById(R.id.btn_12);
        btnlist[12] = (Button)findViewById(R.id.btn_13);
        btnlist[13] = (Button)findViewById(R.id.btn_14);
        btnlist[14] = (Button)findViewById(R.id.btn_15);
        btnlist[15] = (Button)findViewById(R.id.btn_16);
        btnlist[16] = (Button)findViewById(R.id.btn_17);

        txtlist[0] = (TextView)findViewById(R.id.txt_1);
        txtlist[1] = (TextView)findViewById(R.id.txt_2);
        txtlist[2] = (TextView)findViewById(R.id.txt_3);
        txtlist[3] = (TextView)findViewById(R.id.txt_4);
        txtlist[4] = (TextView)findViewById(R.id.txt_5);
        txtlist[5] = (TextView)findViewById(R.id.txt_6);
        txtlist[6] = (TextView)findViewById(R.id.txt_7);
        txtlist[7] = (TextView)findViewById(R.id.txt_8);
        txtlist[8] = (TextView)findViewById(R.id.txt_9);
        txtlist[9] = (TextView)findViewById(R.id.txt_10);
        txtlist[10] = (TextView)findViewById(R.id.txt_11);
        txtlist[11] = (TextView)findViewById(R.id.txt_12);
        txtlist[12] = (TextView)findViewById(R.id.txt_13);
        txtlist[13] = (TextView)findViewById(R.id.txt_14);
        txtlist[14] = (TextView)findViewById(R.id.txt_15);
        txtlist[15] = (TextView)findViewById(R.id.txt_16);
        txtlist[16] = (TextView)findViewById(R.id.txt_17);

        for(int i = 0; i < 17; i++)
            btnlist[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int i;
                    Button btnCurrentButton = (Button)v;

                    for(i = 0 ; i < 17; i++) if(btnCurrentButton == btnlist[i]) break;

                    if(dataSet.iAnswerIndex== i) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });


        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;

        dataSet.setData(iRandomSeed);

        txtDescription.setText(dataSet.sDescription);

        txtlist[1].setText(dataSet.account);

        for(int i = 0 ; i < 6 ; i++) {
            btnlist[8+i].setText(dataSet.money[i]);
            txtlist[8+i].setText(dataSet.money[i]);
        }

        for(int i = 0 ; i < 17;i++) {
            txtlist[i].setVisibility(View.VISIBLE);
            btnlist[i].setVisibility(View.GONE);
        }

        for(int i = 0 ; i < 3 ; i++) {
            txtlist[dataSet.iBtnList[i]].setVisibility(View.GONE);
            btnlist[dataSet.iBtnList[i]].setVisibility(View.VISIBLE);
        }

        StartRecording();
    }

    public synchronized void checkAnswer(Object o){
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

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0704.class);
        startActivity(intent);
    }

    public class Step0703DataSet {
        private final String arrDescription[][]={
                {"아래 통장에서 계좌번호를 나타내는 곳을 찾아 누르세요.", "아래 통장에서 계좌번호를 찾아 누르세요.", "아래 통장에서 계좌번호를 찾아 누르세요."},
                {"아래 통장에서 ‘김수현’이  10,000원을 이체한 날짜를 나타내는 곳을 찾아 누르세요.", "아래 통장에서 ‘민형준’이  20,000원을 이체한 날짜를 나타내는 곳을 찾아 누르세요.", "아래 통장에서 ‘남동식’이  10,000원을 이체한 날짜를 나타내는 곳을 찾아 누르세요."},
                {"아래 통장에서 ‘이제신’이 20,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요.", "아래 통장에서 ‘이제신’이 20,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요.", "아래 통장에서 ‘박형식’이 맡긴 금액은 얼마인지 찾아 누르세요."},
                {"아래 통장을 보고 ‘남동식’이  10,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요.", "아래 통장을 보고 ‘남동식’이  10,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요.", "아래 통장을 보고 ‘유지은’이  10,000원을 입금하고 난 후 통장에 남은 금액은 얼마인지 찾아 누르세요."},
                {"아래 통장에서 ‘민형준’이 이체한 날짜를 찾아 누르세요.", "아래 통장에서 ‘남동식’이 이체한 날짜를 찾아 누르세요.", "아래 통장에서 민형준은 얼마를 입금했는지 찾아 누르세요."}
        };

        private final int arrAnswerButtonIndex[][] = {{1,1,1},{3,5,7},{10,10,14},{13,13,12},{5,7,15}};
        private final int BtnList[][][] ={
                {{0,1,3},{0,1,3},{0,1,3}},
                {{3,5,7},{3,5,7},{3,5,7}},
                {{4,10,12}, {4,10,12},{14,15,16}},
                {{7,10,13,},{7,10,13},{10,11,12}},
                {{5,7,10},{3,5,7},{15,16,14}}};

        private final String  accountList[]={"계좌번호 123456-78-9101112", "계좌번호 100037-56-166616", "계좌번호 222-11-4321-123"};
        private final String  moneyList[][]={{"310,000 ", "320,000 ","340,000 ","360,000 ","370,000 ","380,000 "},{"410,000 ","420,000 ","440,000 ","460,000 ","470,000 ","480,000 "}, {"357,200 ","367,200 ","387,200 ","407,200 ","417,200 ","427,200 "}};

        public String sDescription;
        public String account;
        public int iAnswerIndex;
        public int iBtnList[] = new int[3];
        public String money[] = new String[6];

        public void setData(int iSeed)
        {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2
            sDescription = arrDescription[iSeed][rand];
            iAnswerIndex = arrAnswerButtonIndex[iSeed][rand];
            iBtnList = BtnList[iSeed][rand];
            money = moneyList[rand];
            account = accountList[rand];
        }
    }
}
