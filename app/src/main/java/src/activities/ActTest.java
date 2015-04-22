package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cdmst.smartsilver.R;

import src.activities.Step01.*;
import src.activities.Step02.*;
import src.activities.Step03.ActStep0301;

/**
 * Created by waps12b on 15. 4. 15..
 */
public class ActTest extends FrameActivity {

    Button btnStep1_1;
    Button btnStep1_3;
    Button btnStep1_4;
    Button btnStep1_5;
    Button btnStep2_1;
    Button btnStep2_4;
    Button btnStep3_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_test);
        //.setOnClickListener(clickBtnStep
        btnStep1_1 = (Button)findViewById(R.id.btn_step_1_1);
        btnStep1_1.setOnClickListener(clickBtnStep);

        btnStep1_3 = (Button)findViewById(R.id.btn_step_1_3);
        btnStep1_3.setOnClickListener(clickBtnStep);

        btnStep1_4 = (Button)findViewById(R.id.btn_step_1_4);
        btnStep1_4.setOnClickListener(clickBtnStep);

        btnStep1_5 = (Button)findViewById(R.id.btn_step_1_5);
        btnStep1_5.setOnClickListener(clickBtnStep);

        btnStep2_1 = (Button)findViewById(R.id.btn_step_2_1);
        btnStep2_1.setOnClickListener(clickBtnStep);

        btnStep2_4 = (Button)findViewById(R.id.btn_step_2_4);
        btnStep2_4.setOnClickListener(clickBtnStep);

        btnStep3_1 = (Button)findViewById(R.id.btn_step_3_1);
        btnStep3_1.setOnClickListener(clickBtnStep);
    }

    View.OnClickListener clickBtnStep = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            if(v == btnStep1_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep01.class);
                startActivity(intent);
            }else if(v == btnStep1_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0103.class);
                startActivity(intent);
            }else if(v == btnStep1_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0104.class);
                startActivity(intent);
            }else if(v == btnStep1_5)
            {
                Intent intent = new Intent(v.getContext(), ActStep0105.class);
                startActivity(intent);
            }
            else if(v == btnStep2_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0201.class);
                startActivity(intent);
            }
            else if(v == btnStep2_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0204.class);
                startActivity(intent);
            }
            else if(v == btnStep3_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0301.class);
                startActivity(intent);
            }
        }
    };
}
