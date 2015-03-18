package src.viewes;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import cdmst.smartsilver.R;
import src.Utility;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 3. 18..
 */
public class ViewNumberPad extends FrameView
{
    public static final int CODE_MODIFY = -123456;
    public static final int CODE_OKAY = -2390284;


    public final Button btnModify;
    public final Button btnOkay;
    public final Button[] arrBtnNum;

    public String strBuffer;

    public ViewNumberPad(Context context)
    {
        super(context, R.layout.view_number_pad);

        arrBtnNum = new Button[10];
        arrBtnNum[0] = (Button)findViewById(R.id.btn_pad_r4_c2);
        arrBtnNum[1] = (Button)findViewById(R.id.btn_pad_r1_c1);
        arrBtnNum[2] = (Button)findViewById(R.id.btn_pad_r1_c2);
        arrBtnNum[3] = (Button)findViewById(R.id.btn_pad_r1_c3);
        arrBtnNum[4] = (Button)findViewById(R.id.btn_pad_r2_c1);
        arrBtnNum[5] = (Button)findViewById(R.id.btn_pad_r2_c2);
        arrBtnNum[6] = (Button)findViewById(R.id.btn_pad_r2_c3);
        arrBtnNum[7] = (Button)findViewById(R.id.btn_pad_r3_c1);
        arrBtnNum[8] = (Button)findViewById(R.id.btn_pad_r3_c2);
        arrBtnNum[9] = (Button)findViewById(R.id.btn_pad_r3_c3);

        btnModify = (Button)findViewById(R.id.btn_pad_r4_c1_back);
        btnOkay = (Button)findViewById(R.id.btn_pad_r4_c3_success);

        btnModify.setOnClickListener(listenNumberTouch);
        btnOkay.setOnClickListener(listenNumberTouch);


        for(int i=0; i<10; i++)
            arrBtnNum[i].setOnClickListener(listenNumberTouch);

    }

    public void setNumbers(int[] arrNum)
    {
        for(int i=0; i<10; i++)
            arrBtnNum[i].setText(Integer.toString(arrNum[i]));
    }

    public void shuffleNumber()
    {
        int[] arrNum = {0,1,2,3,4,5,6,7,8,9};

        Random rnd = new Random();
        for(int i=0; i<100; i++)
        {
            int a = rnd.nextInt(10);
            int b = rnd.nextInt(10);
            int t  = arrNum[a];

            arrNum[a] = arrNum[b];
            arrNum[b] = t;
        }
        setNumbers(arrNum);
    }

    public void numberClicked(int iCode)
    {
        ((FrameActivity)getContext()).onGetEvent(this, iCode);
    }

    View.OnClickListener listenNumberTouch = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if( v == btnModify )
                numberClicked(CODE_MODIFY);
            else if( v == btnOkay)
                numberClicked(CODE_OKAY);
            else
                numberClicked(Integer.parseInt(((Button) v).getText().toString()));
        }
    };

}
