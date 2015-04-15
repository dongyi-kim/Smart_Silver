package src.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


import cdmst.smartsilver.R;
import src.activities.Step01.*;
import src.activities.Step02.*;
import src.activities.Step03.*;
import src.viewes.ViewNumberPad;
import ui.CButton;
import ui.RippleView;


public class ActMain extends FrameActivity{


    RippleView rippleLearn;
    RippleView rippleMyResult;
    RippleView rippleMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        rippleLearn = (RippleView)findViewById(R.id.ripple_learning);
        rippleMyResult = (RippleView)findViewById(R.id.ripple_my_result);
        rippleMain = (RippleView)findViewById(R.id.ripple_main_image);

 //       rippleLearn.setOnClickListener(clickBtnStep);

//        btnStep01 = (Button)findViewById(R.id.btn_step01);
//
//        btnStep02 = (Button)findViewById(R.id.btn_step02);
//        btnStep03 = (Button)findViewById(R.id.btn_step03);
//
//        btnStep01.setOnClickListener(clickBtnStep);
//        btnStep02.setOnClickListener(clickBtnStep);
//        btnStep03.setOnClickListener(clickBtnStep);

    }

    View.OnClickListener clickBtnStep = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            if(v == rippleLearn)
            {

<<<<<<< HEAD
            }else if(v == btnStep02)
            {
                Intent intent = new Intent(v.getContext(), ActStep0203.class);
=======
                Intent intent = new Intent(v.getContext(), ActStep01.class);
>>>>>>> origin/master
                startActivity(intent);

            }
        }
    };

<<<<<<< HEAD
=======
    @Override
    public void onGetEvent(Object vSender, Object obj) {
        if(vSender == rippleLearn)
        {
            Intent intent = new Intent(this, ActStep01.class);
            startActivity(intent);
        }else if(vSender == rippleMyResult)
        {

        }else if(vSender == rippleMain)
        {
            Intent intent = new Intent(this, ActTest.class);
            startActivity(intent);
        }

    }
>>>>>>> origin/master
}
