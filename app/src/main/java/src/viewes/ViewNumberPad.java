package src.viewes;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 3. 18..
 */
public class ViewNumberPad
{
    /*
        It is not custom view

     */
    public static final int CODE_MODIFY = -123456;
    public static final int CODE_OKAY = -2390284;

    public static final String KEY_NORMAL_SET = "0123456789";

    private final View view;
    public final Context context;

    public Button btnModify;
    public Button btnOkay;
    public Button[] arrBtnNum;

    public ViewNumberPad(View viewNumberPad)
    {//becaus this view has a number of constructor
        this.view = viewNumberPad;
        context = view.getContext();

        arrBtnNum = new Button[10];
        arrBtnNum[0] = (Button)view.findViewById(R.id.btn_pad_r4_c2);
        arrBtnNum[1] = (Button)view.findViewById(R.id.btn_pad_r1_c1);
        arrBtnNum[2] = (Button)view.findViewById(R.id.btn_pad_r1_c2);
        arrBtnNum[3] = (Button)view.findViewById(R.id.btn_pad_r1_c3);
        arrBtnNum[4] = (Button)view.findViewById(R.id.btn_pad_r2_c1);
        arrBtnNum[5] = (Button)view.findViewById(R.id.btn_pad_r2_c2);
        arrBtnNum[6] = (Button)view.findViewById(R.id.btn_pad_r2_c3);
        arrBtnNum[7] = (Button)view.findViewById(R.id.btn_pad_r3_c1);
        arrBtnNum[8] = (Button)view.findViewById(R.id.btn_pad_r3_c2);
        arrBtnNum[9] = (Button)view.findViewById(R.id.btn_pad_r3_c3);

        btnModify = (Button)view.findViewById(R.id.btn_pad_r4_c1_back);
        btnOkay = (Button)view.findViewById(R.id.btn_pad_r4_c3_success);

        btnModify.setOnClickListener(listenNumberTouch);
        btnOkay.setOnClickListener(listenNumberTouch);


        for(int i=0; i<10; i++)
            arrBtnNum[i].setOnClickListener(listenNumberTouch);

        setNumbers(KEY_NORMAL_SET);
    }


    public void setNumbers(String strNumPad)
    {
        for(int i=0; i<10; i++)
            arrBtnNum[i].setText(Character.toString(strNumPad.charAt(i)));
    }

    public void shuffleNumber()
    {
        char[] arrNum = {'0','1','2','3','4','5','6','7','8','9'};

        Random rnd = new Random();
        for(int i=0; i<100; i++)
        {
            int a = rnd.nextInt(10);
            int b = rnd.nextInt(10);
            char t  = arrNum[a];

            arrNum[a] = arrNum[b];
            arrNum[b] = t;
        }
        setNumbers(new String(arrNum));
    }

    public void numberClicked(int iCode)
    {
        ((FrameActivity)context).onGetEvent(view, iCode);
    }

    View.OnClickListener listenNumberTouch = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if( v == btnModify )
                numberClicked(CODE_MODIFY);
            else if( v == btnOkay)
                numberClicked(CODE_OKAY);
            else
                numberClicked(Integer.parseInt(((Button)v).getText().toString()));
        }
    };

}
