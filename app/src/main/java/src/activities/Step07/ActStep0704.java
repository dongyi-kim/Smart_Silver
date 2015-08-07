package src.activities.Step07;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
    private static final int BUTTON_COUNT = 5;
    private static final int CELL_ROW = 4;
    private static final int CELL_COLUMN = 5;

    private FrameLayout frame7_4_[] = new FrameLayout[2];

    private LinearLayout linearRequestList;
    private LinearLayout linearRequestMoney[] = new LinearLayout[2];
    private LinearLayout linearClientList[] = new LinearLayout[2];
    private LinearLayout linearDefaultList[] = new LinearLayout[2];
    private LinearLayout linearUsing[] = new LinearLayout[2];
    private LinearLayout linearTaxBill[] = new LinearLayout[2];
    private TextView txtDescription;
    public final Button btnAnswerButton[] = new Button[BUTTON_COUNT];

    private ImageView imgReceipt;
    public final Button btnSingleCell[][] = new Button[CELL_ROW][CELL_COLUMN];
    public final int iRowCount[] = {1, 5, 5, 3};

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0704DataSet dataSet = new Step0704DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_07_4);

        txtDescription = (TextView)findViewById(R.id.txt_description);

        frame7_4_[0] = (FrameLayout)findViewById(R.id.frame_7_4_1);
        frame7_4_[1] = (FrameLayout)findViewById(R.id.frame_7_4_2);

        btnAnswerButton[0] = (Button)findViewById(R.id.btn_payday);
        btnAnswerButton[1] = (Button)findViewById(R.id.btn_using_period);
        btnAnswerButton[2] = (Button)findViewById(R.id.btn_this_month);
        btnAnswerButton[3] = (Button)findViewById(R.id.btn_last_month);
        btnAnswerButton[4] = (Button)findViewById(R.id.btn_amount);

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


        imgReceipt = (ImageView)findViewById(R.id.img_receipt);

        btnSingleCell[0][0] = (Button)findViewById(R.id.btn_date);
        btnSingleCell[1][0] = (Button)findViewById(R.id.btn_quantity_1);
        btnSingleCell[1][1] = (Button)findViewById(R.id.btn_quantity_2);
        btnSingleCell[1][2] = (Button)findViewById(R.id.btn_quantity_3);
        btnSingleCell[1][3] = (Button)findViewById(R.id.btn_quantity_4);
        btnSingleCell[1][4] = (Button)findViewById(R.id.btn_quantity_5);
        btnSingleCell[2][0] = (Button)findViewById(R.id.btn_price_1);
        btnSingleCell[2][1] = (Button)findViewById(R.id.btn_price_2);
        btnSingleCell[2][2] = (Button)findViewById(R.id.btn_price_3);
        btnSingleCell[2][3] = (Button)findViewById(R.id.btn_price_4);
        btnSingleCell[2][4] = (Button)findViewById(R.id.btn_price_5);
        btnSingleCell[3][0] = (Button)findViewById(R.id.btn_tax_amount);
        btnSingleCell[3][1] = (Button)findViewById(R.id.btn_add_amount);
        btnSingleCell[3][2] = (Button)findViewById(R.id.btn_total_amount);

        // set button location
        LinearLayout linearReceipt = (LinearLayout) findViewById(R.id.linear_whole_receipt);
        FrameLayout.LayoutParams receiptParams = (FrameLayout.LayoutParams)linearReceipt.getLayoutParams();

        Drawable d = getResources().getDrawable(R.drawable.receipt_7_4_4_1);
        double iImageHeight = d.getIntrinsicHeight();
        double iImageWidth = d.getIntrinsicWidth();

        Display display = getWindowManager().getDefaultDisplay();
        double iLayoutHeight = display.getHeight() * 0.8;
        double iLayoutWidth = display.getWidth() * 0.8;

        double fHeightRatio = iLayoutHeight / iImageHeight;
        double fWidthRatio = iLayoutWidth / iImageWidth;

        if(fHeightRatio < fWidthRatio){
            receiptParams.height = (int)(iImageHeight * fHeightRatio);
            receiptParams.width = (int)(iImageWidth * fHeightRatio);
        }
        else{
            receiptParams.height = (int)(iImageHeight * fWidthRatio);
            receiptParams.width = (int)(iImageWidth * fWidthRatio);
        }

        linearReceipt.setLayoutParams(receiptParams);
        //end of setting

        //button listener for 7_4_1
        for(int i = 0; i < BUTTON_COUNT; i++){
            btnAnswerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int iBtnIndex = 0;
                    for(int ii = 0; ii < BUTTON_COUNT; ii++)
                        if(btnAnswerButton[ii] == v) iBtnIndex = ii;

                    if(dataSet.iAnswerIndex == iBtnIndex) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        }

        //button listener for 7_4_2
        for(int i = 0; i < CELL_ROW; i++)
            for(int j = 0; j < iRowCount[i]; j++) {
                btnSingleCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int iRow = 0, iColumn = 0;
                        for (int ii = 0; ii < CELL_ROW; ii++)
                            for(int jj = 0; jj < iRowCount[ii]; jj++)
                                if (btnSingleCell[ii][jj] == v) { iRow = ii; iColumn = jj; }

                        if (dataSet.iAnswerRow == iRow && dataSet.iAnswerColumn == iColumn) isRight = true;
                        else isRight = false;
                        checkAnswer();
                    }
                });
            }


        frame7_4_[1].setVisibility(View.INVISIBLE);
        setQuestion(false);
    }

    public void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        txtDescription.setText(dataSet.sDescription);

        if(iStage > 3){
            for(int i = 0; i < CELL_ROW; i++)
                for(int j = 0; j < iRowCount[i]; j++){
                    if(dataSet.arrApear[i][j] == 1) btnSingleCell[i][j].setVisibility(View.VISIBLE);
                    else btnSingleCell[i][j].setVisibility(View.INVISIBLE);
                }

            imgReceipt.setImageResource(dataSet.iReceiptImage);
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

                    if(iStage == 4){
                        frame7_4_[0].setVisibility(View.GONE);
                        frame7_4_[1].setVisibility(View.VISIBLE);
                    }

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
        Intent intent = new Intent(this, ActStep0705.class);
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

        private final int arrApearList[][][] = {{{0}, {0, 0, 0, 0, 0}, {1, 0, 0, 1, 0}, {0, 0, 0}},
                {{1}, {0, 0, 0, 0, 0}, {1, 0, 0, 1, 0}, {0, 0, 1}}};
        private final int arrAnswerButtonRow[] = {2, 0};
        private final int arrAnswerButtonColumn[] = {0, 0};

        public String sDescription;

        public int iAnswerIndex;

        public int arrApear[][] = new int[CELL_ROW][CELL_COLUMN];
        public int iAnswerRow;
        public int iAnswerColumn;
        public int iReceiptImage;

        public void setData(int iStage) {
            int iSeed = iStage - 1;

            sDescription = arrDescription[iSeed];

            if(iSeed < 3) iAnswerIndex = arrAnswerButtonIndex[iSeed];
            else{
                iAnswerRow = arrAnswerButtonRow[iSeed - 3];
                iAnswerColumn = arrAnswerButtonColumn[iSeed - 3];
                iReceiptImage = arrReceiptImage[iSeed - 3];

                for(int i = 0; i < CELL_ROW; i++)
                    for(int j = 0; j < iRowCount[i]; j++)
                      arrApear[i][j] = arrApearList[iSeed - 3][i][j];
            }
        }

    }
}
