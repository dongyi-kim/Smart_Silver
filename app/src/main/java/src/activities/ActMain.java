package src.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cdmst.smartsilver.R;


public class ActMain extends FrameActivity{

    Button btnStage01;
    Button btnStage02;
    Button btnStage03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        btnStage01 = (Button)findViewById(R.id.btn_stage01);
        btnStage02 = (Button)findViewById(R.id.btn_stage02);
        btnStage03 = (Button)findViewById(R.id.btn_stage03);
        //Listner : 이벤트
        btnStage01.setOnClickListener(clickBtnStage);
        btnStage02.setOnClickListener(clickBtnStage);
        btnStage03.setOnClickListener(clickBtnStage);

    }

    View.OnClickListener clickBtnStage = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            if(v == btnStage01)
            {
                ((Button)v).setText("1 click");

            }else if(v == btnStage02)
            {
                ((Button)v).setText("2 click");

            }else if(v== btnStage03)
            {
                ((Button)v).setText("3 click");

            }
        }
    };

}
