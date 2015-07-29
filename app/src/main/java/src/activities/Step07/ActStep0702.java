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
import src.activities.Step04.ActStep0402;
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

    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtDescription.setText(dataSet.sDescription);
        imgSubway.setImageResource(dataSet.iImageSource);
        btnAnswer[0].setText("" + dataSet.iAnswerSet[0] + "정거장");
        btnAnswer[1].setText("" + dataSet.iAnswerSet[1] + "정거장");

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
        Intent intent = new Intent(this, ActStep0703.class);
        startActivity(intent);
    }

    public class Step0702DataSet{
        private final String arrDescription[] = {"아래 표는 지하철 노선도입니다. 약수에 사는 점순이 할머니는 삼각지에 가려고 합니다. 약수에서 삼각지까지 가장 빠르게 가려면 몇 정거장 가야 합니까?",
                "아래 표는 지하철 노선도입니다. 양재에 사는 조옥자씨는 언니와 가락시장에 가려고 합니다. 양재에서 가락시장까지 가장 빠르게 가려면 몇 정거장 가야 합니까?",
                "아래 표는 지하철 노선도입니다. 조옥자씨가 서울역에서 흑석동에 가려고 합니다. 서울역에서 흑석동까지 가장 빠르게 가려면 몇 정거장 가야 합니까?",
                "아래 표는 지하철 노선도입니다. 선릉에서 남한산성입구까지 1번 갈아타고 가려면 몇 정거장 가야 합니까?",
                "아래 표는 지하철 노선도입니다.강남에서 미금까지 1번 갈아타고 가려면 몇 정거장 가야 합니까?"};

        private final int arrAnswerSet[][] = {{5, 8}, {8, 10}, {5, 10}, {9, 15}, {6, 10}};
        private final int arrAnswer[] = {5, 8, 5, 9, 6};
        private final int arrImageSource[] = new int[5];

        public String sDescription;
        public int iAnswerSet[] = new int[2];
        public int iAnswer;
        public int iImageSource;

        public Step0702DataSet(){
            arrImageSource[0] = R.drawable.subway_yaksu_samgakji;
            arrImageSource[1] = R.drawable.subway_yangjae_garak;
            arrImageSource[2] = R.drawable.subway_seoulstation_heuksuck;
            arrImageSource[3] = R.drawable.subway_sunleon_namhansansung;
            arrImageSource[4] = R.drawable.subway_gangnam_miguem;
        }

        public void setData(int iStage){
            int iSeed = iStage - 1;

            sDescription = arrDescription[iSeed];
            iAnswerSet[0] = arrAnswerSet[iSeed][0];
            iAnswerSet[1] = arrAnswerSet[iSeed][1];
            iAnswer = arrAnswer[iSeed];
            iImageSource = arrImageSource[iSeed];
        }
    }
}
