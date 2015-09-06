package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.ActStartLearning;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-13.
 */
public class ActStep0305 extends StageActivity{
    private TextView txtDescription;
    private LinearLayout linearFrame[] = new LinearLayout[2];
    private LinearLayout linearSpend1_2;
    private ImageView imgPayMoney;
    private ImageView imgSpendThing[] = new ImageView[2];
    private Button btnAnswer[] = new Button[3];
    private TextView txtSpendDescription1[] = new TextView[3];
    private TextView txtSpendDescription2[] = new TextView[5];

    private int iRetryCount = 0;
    public boolean isRight = false;

    private Random rand = new Random();
    public Step0305DataSet dataSet = new Step0305DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_5);

        //NUM_OF_STAGE = 4;

        txtDescription = (TextView)findViewById(R.id.txt_description);
        linearFrame[0] = (LinearLayout)findViewById(R.id.linear_frame_1_to_3);
        linearFrame[1] = (LinearLayout)findViewById(R.id.linear_frame_4);
        linearSpend1_2 = (LinearLayout)findViewById(R.id.linear_spend_1_2);
        imgPayMoney = (ImageView)findViewById(R.id.img_pay_money);
        imgSpendThing[0] = (ImageView)findViewById(R.id.img_spend_1);
        imgSpendThing[1] = (ImageView)findViewById(R.id.img_spend_2);

        txtSpendDescription1[0] = (TextView)findViewById(R.id.txt_pay_description);
        txtSpendDescription1[1] = (TextView)findViewById(R.id.txt_spend_decription_1);
        txtSpendDescription1[2] = (TextView)findViewById(R.id.txt_spend_decription_2);
        txtSpendDescription2[0] = (TextView)findViewById(R.id.txt_spend_1);
        txtSpendDescription2[1] = (TextView)findViewById(R.id.txt_spend_2);
        txtSpendDescription2[2] = (TextView)findViewById(R.id.txt_spend_3);
        txtSpendDescription2[3] = (TextView)findViewById(R.id.txt_spend_4);
        txtSpendDescription2[4] = (TextView)findViewById(R.id.txt_spend_5);

        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        linearFrame[1].setVisibility(View.GONE);
        linearSpend1_2.setVisibility(View.GONE);
        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    String sSelect = ((Button)v).getText().toString();
                    int iSelect = 0;

                    for(int i = 0; sSelect.charAt(i) != ','; i++){
                        iSelect *= 10;
                        iSelect += sSelect.charAt(i) - '0';
                    }
                    iSelect *= 1000;

                    if(iSelect== dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        if(iStage == 1){
            imgPayMoney.setImageResource(R.drawable.icon_paper_meoney_10000);
            imgSpendThing[0].setImageResource(R.drawable.icon_coin_wallet);
        }
        else if(iStage == 2){
            imgPayMoney.setImageResource(R.drawable.icon_paper_meoney_15000);
            imgSpendThing[0].setImageResource(R.drawable.icon_sundaeguk);
            imgSpendThing[1].setImageResource(R.drawable.icon_sundaeguk);
            linearSpend1_2.setVisibility(View.VISIBLE);
        }
        else if(iStage == 3){
            imgSpendThing[0].setImageResource(R.drawable.icon_grandma);
            imgSpendThing[1].setImageResource(R.drawable.icon_grandfa);
            imgPayMoney.setImageResource(R.drawable.icon_paper_meoney_50000);
        }
        else if(iStage == NUM_OF_STAGE){
            linearFrame[0].setVisibility(View.GONE);
            linearFrame[1].setVisibility(View.VISIBLE);
        }
        dataSet.setData(iStage);

        int iGapMult = (iStage <= 2 ? 1000 : 10000);
        int iFirstAnsNumber = dataSet.iAnswer - rand.nextInt(3) * iGapMult;
        if(iFirstAnsNumber <= 0) iFirstAnsNumber = dataSet.iAnswer - (dataSet.iAnswer > 2 * iGapMult ? 2 : 1) * iGapMult;
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setText("" + iFirstAnsNumber / 1000 + ",000원");
            iFirstAnsNumber += iGapMult;
        }

        txtDescription.setText(dataSet.sDescription);

        if(iStage == NUM_OF_STAGE){
            for(int i = 0; i < 5; i++)
                txtSpendDescription2[i].setText(dataSet.sMoneyDescription[i]);
        }
        else {
            for (int i = 0; i < 3; i++)
                txtSpendDescription1[i].setText(dataSet.sMoneyDescription[i]);
        }
        StartRecording();
    }


    public void checkAnswer(Object object){
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

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStartLearning.class);
        startActivity(intent);
    }

    public class Step0305DataSet{
        private final String arrDescription[] = {"할아버지가 할머니에게 동전지갑을 선물하려고 합니다.\n할아버지가 받아야 할 거스름돈은 얼마입니까?",
                "할머니와 할아버지는 순댓국 2그릇을 먹었습니다.\n받아야 할 거스름돈은 얼마입니까?",
                "할머니와 할아버지가 마당놀이를 보러 가서 입장료를 냈습니다.\n두 분이 받을 거스름돈은 얼마입니까?",
                "할아버지는 할머니와 같이 마당놀이를 함께 보고, 순댓국을 먹고, 할머니에게 동전지갑을 선물했습니다. 오늘 쓴 돈은 모두 얼마입니까?"};
        private final String arrMoneyDescription[][][] = {{{"낸 돈 10,000", "동전지갑 3,000"}, {"낸 돈 15,000", "순대국\n6,000"}, {"낸 돈 50,000", "입장권\n15,000"}, {"", "", ""}},
        {{"낸 돈 10,000", "동전지갑 5,000"}, {"낸 돈 15,000", "순대국\n5,000"}, {"낸 돈 50,000", "입장권\n10,000"}, {"", "", ""}},
        {{"낸 돈 10,000", "동전지갑 8,000"}, {"낸 돈 15,000", "순대국\n6,500"}, {"낸 돈 50,000", "입장권\n20,000"}, {"", "", ""}}};
        private final int arrAnswer[][] = {{7000, 3000, 20000},
                {5000, 5000, 30000},
                {2000, 2000, 10000}};
        private final int arrPayMoney[] = {10000, 15000, 50000};

        private int arrSeed[] = new int[3];
        public String sDescription;
        public String sMoneyDescription[] = new String[5];
        public int iAnswer;

        void setData(int iStage){
            int iSeed = rand.nextInt(3);

            sDescription = arrDescription[iStage - 1];
            if(iStage == NUM_OF_STAGE){
                iAnswer = 0;
                for(int i = 0; i < 3; i++)
                    iAnswer += arrPayMoney[i] - arrAnswer[arrSeed[i]][i];

                for(int i = 0; i < 5; i++)
                    sMoneyDescription[i] = arrMoneyDescription[arrSeed[(5 - i) / 2]][(5 - i) / 2][1];
            }
            else{
                sMoneyDescription[0] = arrMoneyDescription[iSeed][iStage - 1][0];
                sMoneyDescription[1] = sMoneyDescription[2] = arrMoneyDescription[iSeed][iStage - 1][1];
                iAnswer = arrAnswer[iSeed][iStage - 1];

                arrSeed[iStage - 1] = iSeed;
            }
        }
    }
}
