package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.ActMain;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-06-24.
 */
public class ActStep0701 extends StageActivity {

    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 6;

    private TextView txtDescription;
    private LinearLayout linearLineCell[] = new LinearLayout[ROW_COUNT];
    public final Button btnSingleCell[][] = new Button[ROW_COUNT][COLUMN_COUNT];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0701DataSet dataSet = new Step0701DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_1);

        NUM_OF_STAGE = 4;

        txtDescription = (TextView)findViewById(R.id.txt_description);
        linearLineCell[0] = (LinearLayout)findViewById(R.id.layout_line_1);
        linearLineCell[1] = (LinearLayout)findViewById(R.id.layout_line_2);
        linearLineCell[2] = (LinearLayout)findViewById(R.id.layout_line_3);
        linearLineCell[3] = (LinearLayout)findViewById(R.id.layout_line_4);
        for(int i = 0; i < ROW_COUNT; i++){
            btnSingleCell[i][0] = (Button)(linearLineCell[i].findViewById(R.id.btn_departure_time));
            btnSingleCell[i][1] = (Button)(linearLineCell[i].findViewById(R.id.btn_turnarroud_time));
            btnSingleCell[i][2] = (Button)(linearLineCell[i].findViewById(R.id.btn_distance));
            btnSingleCell[i][3] = (Button)(linearLineCell[i].findViewById(R.id.btn_fee_normal));
            btnSingleCell[i][4] = (Button)(linearLineCell[i].findViewById(R.id.btn_fee_children));
            btnSingleCell[i][5] = (Button)(linearLineCell[i].findViewById(R.id.btn_fee_secondhand));
        }


        //button listener
        for(int i = 0; i < ROW_COUNT; i++)
            for(int j = 0; j < COLUMN_COUNT; j++) {
                if (j == 2 || j == 4 || j == 5) continue;
                btnSingleCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int iButtonRow = 0, iButtonColumn = 0;
                        for(int r  = 0; r < ROW_COUNT; r++) for(int c = 0; c < COLUMN_COUNT; c++)
                                if(v == btnSingleCell[r][c]){ iButtonRow = r; iButtonColumn = c; }

                        if (iButtonRow == dataSet.iAnswerIndex[0] && iButtonColumn == dataSet.iAnswerIndex[1])
                            isRight = true;
                        else isRight = false;
                        checkAnswer();
                    }
                });// end of button listener
            }
        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        txtDescription.setText(dataSet.sDescription);

        int iRowCount = dataSet.iLineCount;
        for(int i = 0; i < ROW_COUNT; i++) {
            if(i < iRowCount) {
                linearLineCell[i].setVisibility(View.VISIBLE);
                for (int j = 0; j < COLUMN_COUNT; j++)
                    btnSingleCell[i][j].setText(dataSet.sButtonText[i][j]);
            }
            else linearLineCell[i].setVisibility(View.GONE);
        }

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

    public class Step0701DataSet {
        private final String arrDescription[] = {" 아래 그림은 천안에서 포항으로 가는 고속버스 운행시간입니다.\n조옥자씨는 포항에 있는 언니네 가려고 합니다. 천안에서 포항까지\n몇 시간이 소요되는지 아래 자료에서 찾아 누르세요!",
        " 아래 그림은 천안에서 포항으로 가는 고속버스 운행시간입니다.\n조옥자씨는 포항에 있는 언니네 가려고 합니다. \n버스 요금은 얼마인지 해당 요금을 누르세요!",
        " 아래 그림은 천안에서 포항으로 가는 고속버스 운행시간입니다.\n포항에서 오후 2시(14시) 정도에 도착하려면 천안에서\n몇 시 버스를 타야 하는지 해당하는 출발시간을 누르세요!",
        " 아래 그림은 천안에서 포항으로 가는 고속버스 운행시간입니다.\n포항에서 낮 12시 정도에 도착하려면 천안에서\n몇 시 버스를 타야 하는지 해당하는 출발시간을 누르세요!"};
        private final int arrLineCount[] = {1, 1, 2, 4};
        private final String arrButtonText[][][] = {{{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                {{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                {{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"10:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                {{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"10:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"14:30", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"18:30", "4시간", "0", "20,600원", "10,300원", "16,500원"}}};
        private final int arrAnswerIndex[][] = {{0, 1}, {0, 3}, {1, 0}, {0, 0}};

        public String sDescription;
        public int iLineCount;
        public String sButtonText[][] = new String[4][6];
        public int iAnswerIndex[] = new int[2];

        public void setData(int iSeed) {
            sDescription = arrDescription[iSeed];
            iLineCount = arrLineCount[iSeed];

            for(int i = 0; i < iLineCount; i++)
                for(int j = 0; j < 6; j++){
                    sButtonText[i][j] = arrButtonText[iSeed][i][j];
                }
            iAnswerIndex[0] = arrAnswerIndex[iSeed][0];
            iAnswerIndex[1] = arrAnswerIndex[iSeed][1];
        }
    }
}
