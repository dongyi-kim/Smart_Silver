package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-07-29.
 */
public class ActStep0702 extends StageActivity{
    private TextView txtDescription;
    private ImageView imgSubway;
    public final Button btnAnswer[] = new Button[2];

    private int iRetryCount = 0;
    public boolean isRight = false;
    public Step0702DataSet dataSet = new Step0702DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_2);

        txtDescription = (TextView)findViewById(R.id.txt_description);
        imgSubway = (ImageView)findViewById(R.id.img_subway);
        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_2);

        for(int i = 0; i < 2; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int iSelectIndex = (v == btnAnswer[0]? 0 : 1);
                    if(dataSet.iAnswer == dataSet.iAnswerSet[iSelectIndex]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtDescription.setText(dataSet.sDescription);
        imgSubway.setImageResource(dataSet.iImageSource);
        btnAnswer[0].setText(dataSet.iAnswerSet[0]);
        btnAnswer[1].setText(dataSet.iAnswerSet[1]);

        startRecording();
    }

    public synchronized void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        countUpTry();
        if(isRight || iRetryCount > 1) stopRecording(isRight);

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
        Intent intent = new Intent(this, ActStep0703.class);
        startActivity(intent);
    }

    public class Step0702DataSet{
        private final String arrDescription[][] = {
                {"약수에 사는 점순이 할머니가 삼각지에 가려고 합니다.\n갈아타지 않을 때, 몇 정거장 가나요?", "이촌에 사는 김 할머니가 옥수에 가려고 합니다.\n갈아타지 않을 때, 몇 정거장 가나요?","공덕에서 충무로까지 가려고 합니다.\n서울역에서 한 번 갈아타고 갈 때, 모두 몇 정거장 가나요?"},
                {"양재에 사는 조옥자씨가 언니와 가락시장에 가려고 합니다.\n갈아타지 않을 때, 몇 정거장 가나요?", "오금에 사는 김 할머니가 대치에 가려고 합니다.\n갈아타지 않을 때, 모두 몇 정거장 가나요?", "정자에 사는 김 할머니가 청계산입구에 가려고 합니다.\n갈아타지 않을 때, 몇 정거장 가나요?"},
                {"조옥자씨는 서울역에서 흑석동에 가려고 합니다.\n노량진에서 한 번 갈아타면 모두 몇 정거장 가나요?","김 할머니는 서울역에서 옥수에 가려고 합니다.\n충무로에서 한 번 갈아타면 모두 몇 정거장 가나요?","김 할머니는 노량진에서 옥수에 가려고 합니다.\n용산에서 한 번 갈아타면 모두 몇 정거장 가나요?"},
                {"선릉에서 남한산성입구에 가려고 합니다.\n복정에서 한 번 갈아타면 모두 몇 정거장 가나요?","신천에서 가락시장에 가려고 합니다.\n잠실에서 한 번 갈아타면 모두 몇 정거장 가나요?","모란에 사는 김 할머니가 경찰병원에 가려고 합니다.\n가락시장에서 한 번 갈아타면 모두 몇 정거장 가나요?"},
                {"강남에서 미금까지 가는데,\n어느 역에서 갈아타야 총 6정거장 걸릴까요?","선릉에서 가락시장까지 가는데,\n어느 역에서 갈아타야 총 7정거장 걸릴까요?","양재에서 복정까지 가는데,\n어느 역에서 갈아타야 총 7정거장 걸릴까요?"}
        };

        private final String arrAnswerSet[][][] = {
                {{"5 정거장","8 정거장"}, {"1 정거장","3 정거장"}, {"4 정거장","6 정거장"}},
                {{"8 정거장","10 정거장"},{"4 정거장","7 정거장"},{"2 정거장","4 정거장"}},
                {{"5 정거장","10 정거장"},{"4 정거장","7 정거장"},{"5 정거장","8 정거장"}},
                {{"9 정거장","15 정거장"}, {"4 정거장","7 정거장"},{"5 정거장","10 정거장"}},
                {{"정자","선릉"},{"잠실","도곡"},{"도곡","수서"}}
        };

        private final String arrAnswer[][] = {
                {"5 정거장", "3 정거장", "4 정거장"},
                {"8 정거장", "7 정거장", "2 정거장"},
                {"5 정거장", "7 정거장", "5 정거장"},
                {"9 정거장", "4 정거장", "10 정거장"},
                {"정자","잠실", "도곡"}
        };

        private final int arrImageSource[][] = {
                {R.drawable.subway_1_1, R.drawable.subway_1_2, R.drawable.subway_1_3},
                {R.drawable.subway_2_1, R.drawable.subway_2_2, R.drawable.subway_2_3},
                {R.drawable.subway_3_1, R.drawable.subway_3_2, R.drawable.subway_3_3},
                {R.drawable.subway_4_1, R.drawable.subway_4_2, R.drawable.subway_4_3},
                {R.drawable.subway_5_1, R.drawable.subway_5_2, R.drawable.subway_5_3},
        };

        public String sDescription;
        public String iAnswerSet[] = new String[2];
        public String iAnswer;
        public int iImageSource;

        public void setData(int iStage){
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2
            int iSeed = iStage - 1;

            sDescription = arrDescription[iSeed][rand];
            iAnswerSet[0] = arrAnswerSet[iSeed][rand][0];
            iAnswerSet[1] = arrAnswerSet[iSeed][rand][1];
            iAnswer = arrAnswer[iSeed][rand];
            iImageSource = arrImageSource[iSeed][rand];
        }
    }
}
