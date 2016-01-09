package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

        //NUM_OF_STAGE = 4;

        txtDescription = (TextView) findViewById(R.id.txt_description);
        linearLineCell[0] = (LinearLayout) findViewById(R.id.layout_line_1);
        linearLineCell[1] = (LinearLayout) findViewById(R.id.layout_line_2);
        linearLineCell[2] = (LinearLayout) findViewById(R.id.layout_line_3);
        linearLineCell[3] = (LinearLayout) findViewById(R.id.layout_line_4);

        for (int i = 0; i < ROW_COUNT; i++) {
            btnSingleCell[i][0] = (Button) (linearLineCell[i].findViewById(R.id.btn_departure_time));
            btnSingleCell[i][1] = (Button) (linearLineCell[i].findViewById(R.id.btn_turnarroud_time));
            btnSingleCell[i][2] = (Button) (linearLineCell[i].findViewById(R.id.btn_distance));
            btnSingleCell[i][3] = (Button) (linearLineCell[i].findViewById(R.id.btn_fee_normal));
            btnSingleCell[i][4] = (Button) (linearLineCell[i].findViewById(R.id.btn_fee_children));
            btnSingleCell[i][5] = (Button) (linearLineCell[i].findViewById(R.id.btn_fee_secondhand));
        }


        //button listener
        for (int i = 0; i < ROW_COUNT; i++)
            for (int j = 0; j < COLUMN_COUNT; j++) {
                //if (j == 2 || j == 4 || j == 5) continue;
                btnSingleCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int iButtonRow = 0, iButtonColumn = 0;
                        for (int r = 0; r < ROW_COUNT; r++)
                            for (int c = 0; c < COLUMN_COUNT; c++)
                                if (v == btnSingleCell[r][c]) {
                                    iButtonRow = r;
                                    iButtonColumn = c;
                                }

                        if (iButtonRow == dataSet.iAnswerIndex[0] && iButtonColumn == dataSet.iAnswerIndex[1])
                            isRight = true;
                        else isRight = false;
                        checkAnswer();
                    }
                });// end of button listener
            }
        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object) {
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        txtDescription.setText(dataSet.sDescription);

        int iRowCount = dataSet.iLineCount;
        for (int i = 0; i < ROW_COUNT; i++) {
            if (i < iRowCount) {
                linearLineCell[i].setVisibility(View.VISIBLE);
                for (int j = 0; j < COLUMN_COUNT; j++) {
                    btnSingleCell[i][j].setText(dataSet.sButtonText[i][j]);
                    if(dataSet.box[i][j] == 0)
                    {
                        btnSingleCell[i][j].setBackgroundResource(R.drawable.white);
                    }
                    else
                    {
                        btnSingleCell[i][j].setBackgroundResource(R.drawable.borderline_clickable);
                    }
                }
            } else linearLineCell[i].setVisibility(View.GONE);
        }

        StartRecording();
    }

    public synchronized void checkAnswer(Object o) {
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        if (isRight || iRetryCount > 1) StopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isRight || iRetryCount > 1) {
                    iStage++;
                    iRetryCount = 0;

                    if (iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                } else {
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object) {
        Intent intent = new Intent(this, ActStep0702.class);
        startActivity(intent);
    }

    public class Step0701DataSet {
        private final String arrDescription[][] = {
                {"성인 1명이 천안에서 포항까지 가는 버스요금은 얼마인가요?\n필요한 정보가 표시된 곳을 찾아 누르세요.", "성인 1명이 서울에서 부산까지 가는 고속철도요금은 얼마인가요?\n필요한 정보가 표시된 곳을 찾아 누르세요.", "성인 1명이 서울에서 대전까지 가는 기차 요금은 얼마인가요?\n필요한 정보가 표시된 곳을 찾아 누르세요."},
                {"다음 표는 천안에서 포항으로 가는 고속버스 정보입니다.\n포항에 오후 2시(14시) 정도에 도착하려면 천안에서 몇 시 버스를 타야 합니까?", "다음 표는 서울에서 부산으로 가는 고속철도 운행 정보입니다.\n부산에 12시 쯤에 도착하려면 서울에서 몇 시 기차를 타야 합니까?", "다음 표는 서울에서 대전으로 가는 기차운행 정보입니다.\n대전에 2시 쯤(14시)에 도착하려면 서울에서 몇 시 기차를 타야 합니까?"},
                {"다음 표는 천안에서 포항으로 가는 고속버스 정보입니다.\n포항에 낮 12시 정도에 도착하려면 천안에서 몇 시 버스를 타야 합니까?", "다음 표는 서울에서 부산으로 가는 고속철도 운행 정보입니다.\n부산에 오후 6시 정도(18시)에 도착하려면 서울에서 몇 시 기차를 타야 합니까?", "다음 표는 서울에서 대전으로 가는 기차운행 정보입니다.\n대전에 오후 5시 정도(17시)에 도착하려면 서울에서 몇 시 기차를 타야 합니까?"},
                {"다음 표는 천안에서 포항으로 가는 고속버스 정보입니다.\n포항에 낮 2시(14시) 정도에 도착하려면 천안에서 몇 시 버스를 타야 합니까?", "다음 표는 서울에서 부산으로 가는 고속철도 운행 정보입니다.\n서울에서 부산까지 가는 아동의 기차요금은 얼마인가요?", "다음 표는 서울에서 대전으로 가는 기차운행 정보입니다.\n서울에서 대전까지 가는 아동의 기차요금은 얼마인가요? "},
                {"다음 표는 천안에서 포항으로 가는 고속버스 정보입니다.\n천안에서 포항으로 가는데 중고생 버스요금은 얼마인가요? ", "다음 표는 서울에서 부산으로 가는 고속철도 운행 정보입니다.\n서울에서 부산으로 가는데 중고생 기차요금은 얼마인가요?", "다음 표는 서울에서 대전으로 가는 기차운행 정보입니다.\n서울에서 대전으로 가는데 중고생 기차요금은 얼마인가요?"}
        };

        private final int arrLineCount[][] = {{1, 1, 1}, {2, 2, 2}, {4, 4, 4}, {4, 1, 1}, {2, 1, 1}};
        private final String arrButtonText[][][][] = {
                {{{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}}, {{"10:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}}, {{"12:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}}},

                {{{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"10:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                        {{"08:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}, {"10:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}},
                        {{"12:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}, {"13:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}}},

                {{{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"10:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"14:30", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"18:30", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                        {{"08:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}, {"10:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}, {"14:30", "2시간", "0", "40,700원", "15,700원", "24,000원"}, {"18:30", "2시간", "0", "40,700원", "15,700원", "24,000원"}},
                        {{"12:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}, {"13:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}, {"15:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}, {"17:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}}},

                {{{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"10:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"14:30", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"18:30", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                        {{"08:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}},
                        {{"12:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}}},

                {{{"08:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}, {"10:00", "4시간", "0", "20,600원", "10,300원", "16,500원"}},
                        {{"08:00", "2시간", "0", "40,700원", "15,700원", "24,000원"}},
                        {{"12:00", "2시간", "0", "31,600원", "15,700원", "25,120원"}}}
        };

        private final int arrButton[][][][] = {
                {{{1,1,0,1,1,1}}, {{0,0,0,1,1,1,}}, {{0,0,0,1,1,1,}}},

                {{{1,0,0,0,0,0,}, {1,0,0,0,0,0,}},
                        {{1,0,0,0,0,0,}, {1,0,0,0,0,0}},
                        {{1,0,0,0,0,0,}, {1,0,0,0,0,0}}},

                {{{1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}},
                        {{1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}},
                        {{1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}}},

                {{{1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}, {1,0,0,0,0,0,}}, {{0,0,0,1,1,1,}},{{0,0,0,1,1,1,}}},
                {{{0,0,0,1,1,1,}, {0,0,0,0,0,0,}},
                        {{0,0,0,1,1,1,}},
                        {{0,0,0,1,1,1,}}}
        };

        private final int arrAnswerIndex[][][] = {
                {{0, 3}, {0, 3}, {0, 3}},
                {{1, 0}, {1, 0}, {0, 0}},
                {{0, 0}, {2, 0}, {2, 0}},
                {{1, 0}, {0, 4}, {0, 4}},
                {{0, 5}, {0, 5}, {0, 5}}
        };

        public String sDescription;
        public int iLineCount;
        public String sButtonText[][] = new String[4][6];
        public int iAnswerIndex[] = new int[2];
        public int box[][];

        public void setData(int iSeed) {
            int rand = (int) (Math.random() * 3.0); // 0 ~ 2
            sDescription = arrDescription[iSeed][rand];
            iLineCount = arrLineCount[iSeed][rand];
            for (int i = 0; i < iLineCount; i++)
                for (int j = 0; j < 6; j++) {
                    sButtonText[i][j] = arrButtonText[iSeed][rand][i][j];
                }
            box = arrButton[iSeed][rand];
            iAnswerIndex[0] = arrAnswerIndex[iSeed][rand][0];
            iAnswerIndex[1] = arrAnswerIndex[iSeed][rand][1];
        }
    }
}