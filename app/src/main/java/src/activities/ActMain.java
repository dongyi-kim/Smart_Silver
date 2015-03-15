package src.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cdmst.smartsilver.R;


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
        //Listner : 이벤트
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
                ((Button)v).setText("2 click");

            }else if(v== btnStep03)
            {
                ((Button)v).setText("3 click");

            }
        }
    };



}
