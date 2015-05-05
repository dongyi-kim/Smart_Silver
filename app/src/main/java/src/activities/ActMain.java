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
    RippleView rippleDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        rippleLearn = (RippleView)findViewById(R.id.ripple_learning);
        rippleMyResult = (RippleView)findViewById(R.id.ripple_my_result);
        rippleDeveloper = (RippleView)findViewById(R.id.ripple_developer);
    }


    @Override
    public void onGetEvent(Object vSender, Object obj) {
        if(vSender == rippleLearn)
        {
            Intent intent = new Intent(this, ActStep01.class);
            startActivity(intent);
        }else if(vSender == rippleMyResult)
        {

        }else if(vSender == rippleDeveloper)
        {
            Intent intent = new Intent(this, ActTest.class);
            startActivity(intent);
        }

    }

}
