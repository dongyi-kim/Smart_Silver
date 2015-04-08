package src.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


import cdmst.smartsilver.R;
import src.activities.Step01.*;
import src.activities.Step03.*;
import ui.CButton;


public class ActMain extends FrameActivity{


    Button btnStep01;
    Button btnStep02;
    Button btnStep03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        btnStep01 = (Button)findViewById(R.id.btn_step01);

        btnStep02 = (Button)findViewById(R.id.btn_step02);
        btnStep03 = (Button)findViewById(R.id.btn_step03);

        btnStep01.setOnClickListener(clickBtnStep);
        btnStep02.setOnClickListener(clickBtnStep);
        btnStep03.setOnClickListener(clickBtnStep);

    }

    View.OnClickListener clickBtnStep = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            if(v == btnStep01)
            {
                Intent intent = new Intent(v.getContext(), ActStep01.class);
                startActivity(intent);

            }else if(v == btnStep02)
            {
                Intent intent = new Intent(v.getContext(), ActStep0303.class);
                startActivity(intent);

            }else if(v== btnStep03)
            {
                Intent intent = new Intent(v.getContext(), ActStep0301.class);
                startActivity(intent);
            }
        }
    };



}
