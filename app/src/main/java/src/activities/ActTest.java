package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cdmst.smartsilver.R;

import src.activities.Step01.*;
import src.activities.Step02.*;
import src.activities.Step03.*;
import src.activities.Step04.*;
import src.activities.Step05.*;
import src.activities.Step06.*;
import src.activities.Step07.*;
import src.activities.Step10.*;


/**
 * Created by waps12b on 15. 4. 15..
 */
public class ActTest extends FrameActivity {

    Button btnStep1_1;
    Button btnStep1_2;
    Button btnStep1_3;
    Button btnStep1_4;
    Button btnStep1_5;
    Button btnStep2_1;
    Button btnStep2_2;
    Button btnStep2_3;
    Button btnStep2_4;
    Button btnStep2_5;
    Button btnStep3_1;
    Button btnStep3_2;
    Button btnStep3_3;
    Button btnStep3_4;
    Button btnStep3_5;
    Button btnStep4_1;
    Button btnStep4_2;
    Button btnStep4_3;
    Button btnStep4_4;
    Button btnStep4_5;
    Button btnStep5_2;
    Button btnStep5_3;
    Button btnStep5_4;
    Button btnStep5_5;
    Button btnStep6_1;
    Button btnStep6_2;
    Button btnStep6_3;
    Button btnStep6_4;
    Button btnStep6_5;
    Button btnStep7_1;
    Button btnStep7_2;
    Button btnStep7_3;
    Button btnStep7_4;
    Button btnStep7_5;
    Button btnStep10;
    Button btnSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_test);
        //.setOnClickListener(clickBtnStep
        btnStep1_1 = (Button)findViewById(R.id.btn_step_1_1);
        btnStep1_1.setOnClickListener(clickBtnStep);

        btnStep1_2 = (Button)findViewById(R.id.btn_step_1_2);
        btnStep1_2.setOnClickListener(clickBtnStep);

        btnStep1_3 = (Button)findViewById(R.id.btn_step_1_3);
        btnStep1_3.setOnClickListener(clickBtnStep);

        btnStep1_4 = (Button)findViewById(R.id.btn_step_1_4);
        btnStep1_4.setOnClickListener(clickBtnStep);

        btnStep1_5 = (Button)findViewById(R.id.btn_step_1_5);
        btnStep1_5.setOnClickListener(clickBtnStep);

        btnStep2_1 = (Button)findViewById(R.id.btn_step_2_1);
        btnStep2_1.setOnClickListener(clickBtnStep);

        btnStep2_1 = (Button)findViewById(R.id.btn_step_2_1);
        btnStep2_1.setOnClickListener(clickBtnStep);

        btnStep2_2 = (Button)findViewById(R.id.btn_step_2_2);
        btnStep2_2.setOnClickListener(clickBtnStep);

        btnStep2_3 = (Button)findViewById(R.id.btn_step_2_3);
        btnStep2_3.setOnClickListener(clickBtnStep);

        btnStep2_4 = (Button)findViewById(R.id.btn_step_2_4);
        btnStep2_4.setOnClickListener(clickBtnStep);

        btnStep2_5 = (Button)findViewById(R.id.btn_step_2_5);
        btnStep2_5.setOnClickListener(clickBtnStep);

        btnStep3_1 = (Button)findViewById(R.id.btn_step_3_1);
        btnStep3_1.setOnClickListener(clickBtnStep);

        btnStep3_2 = (Button)findViewById(R.id.btn_step_3_2);
        btnStep3_2.setOnClickListener(clickBtnStep);

        btnStep3_3 = (Button)findViewById(R.id.btn_step_3_3);
        btnStep3_3.setOnClickListener(clickBtnStep);

        btnStep3_4 = (Button)findViewById(R.id.btn_step_3_4);
        btnStep3_4.setOnClickListener(clickBtnStep);

        btnStep3_5 = (Button)findViewById(R.id.btn_step_3_5);
        btnStep3_5.setOnClickListener(clickBtnStep);

        btnStep4_1 = (Button)findViewById(R.id.btn_step_4_1);
        btnStep4_1.setOnClickListener(clickBtnStep);

        btnStep4_2 = (Button)findViewById(R.id.btn_step_4_2);
        btnStep4_2.setOnClickListener(clickBtnStep);

        btnStep4_3 = (Button)findViewById(R.id.btn_step_4_3);
        btnStep4_3.setOnClickListener(clickBtnStep);

        btnStep4_4 = (Button)findViewById(R.id.btn_step_4_4);
        btnStep4_4.setOnClickListener(clickBtnStep);

        btnStep4_5 = (Button)findViewById(R.id.btn_step_4_5);
        btnStep4_5.setOnClickListener(clickBtnStep);

        btnStep5_2 = (Button)findViewById(R.id.btn_step_5_2);
        btnStep5_2.setOnClickListener(clickBtnStep);

        btnStep5_3 = (Button)findViewById(R.id.btn_step_5_3);
        btnStep5_3.setOnClickListener(clickBtnStep);

        btnStep5_4 = (Button)findViewById(R.id.btn_step_5_4);
        btnStep5_4.setOnClickListener(clickBtnStep);

        btnStep5_5 = (Button)findViewById(R.id.btn_step_5_5);
        btnStep5_5.setOnClickListener(clickBtnStep);

        btnStep6_1 = (Button)findViewById(R.id.btn_step_6_1);
        btnStep6_1.setOnClickListener(clickBtnStep);

        btnStep6_2 = (Button)findViewById(R.id.btn_step_6_2);
        btnStep6_2.setOnClickListener(clickBtnStep);

        btnStep6_3 = (Button)findViewById(R.id.btn_step_6_3);
        btnStep6_3.setOnClickListener(clickBtnStep);

        btnStep6_4 = (Button)findViewById(R.id.btn_step_6_4);
        btnStep6_4.setOnClickListener(clickBtnStep);

        btnStep6_5 = (Button)findViewById(R.id.btn_step_6_5);
        btnStep6_5.setOnClickListener(clickBtnStep);

        btnStep7_1 = (Button)findViewById(R.id.btn_step_7_1);
        btnStep7_1.setOnClickListener(clickBtnStep);

        btnStep7_2 = (Button)findViewById(R.id.btn_step_7_2);
        btnStep7_2.setOnClickListener(clickBtnStep);

        btnStep7_3 = (Button)findViewById(R.id.btn_step_7_3);
        btnStep7_3.setOnClickListener(clickBtnStep);

        btnStep7_4 = (Button)findViewById(R.id.btn_step_7_4);
        btnStep7_4.setOnClickListener(clickBtnStep);

        btnStep7_5 = (Button)findViewById(R.id.btn_step_7_5);
        btnStep7_5.setOnClickListener(clickBtnStep);

        btnStep10 = (Button)findViewById(R.id.btn_step_10);
        btnStep10.setOnClickListener(clickBtnStep);

        btnSelector = (Button)findViewById(R.id.btn_step_selector);
        btnSelector.setOnClickListener(clickBtnStep);

    }

    View.OnClickListener clickBtnStep = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            if(v == btnStep1_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0101.class);
                startActivity(intent);
            }else if(v == btnStep1_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0102.class);
                startActivity(intent);
            }else if(v == btnStep1_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0103.class);
                startActivity(intent);
            }else if(v == btnStep1_4)
            {
                Intent intent = new Intent(v.getContext(), ActStep0104.class);
                startActivity(intent);
            }
            else if(v == btnStep1_5)
            {
                Intent intent = new Intent(v.getContext(), ActStep0105.class);
                startActivity(intent);
            }
            else if(v == btnStep2_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0201.class);
                startActivity(intent);
            }else if(v == btnStep2_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0202.class);
                startActivity(intent);
            }else if(v == btnStep2_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0203.class);
                startActivity(intent);
            }else if(v == btnStep2_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0204.class);
                startActivity(intent);
            }
            else if(v == btnStep2_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0202.class);
                startActivity(intent);
            }
            else if(v == btnStep2_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0203.class);
                startActivity(intent);
            }
            else if(v == btnStep2_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0204.class);
                startActivity(intent);
            }
            else if(v == btnStep2_5)
            {

                Intent intent = new Intent(v.getContext(), ActStep0205.class);
                startActivity(intent);
            }
            else if(v == btnStep3_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0301.class);
                startActivity(intent);
            }

            else if(v == btnStep3_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0302.class);
                startActivity(intent);
            }
            else if(v == btnStep3_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0303.class);
                startActivity(intent);
            }
            else if(v == btnStep3_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0304.class);
                startActivity(intent);
            }
            else if(v == btnStep3_5) {

                Intent intent = new Intent(v.getContext(), ActStep0305.class);
                startActivity(intent);
            }
            else if(v == btnStep4_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0401.class);
                startActivity(intent);
            }
            else if(v == btnStep4_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0402.class);
                startActivity(intent);
            }
            else if(v == btnStep4_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0403.class);
                startActivity(intent);
            }
            else if(v == btnStep4_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0404.class);
                startActivity(intent);
            }
            else if(v == btnStep4_5)
            {

                Intent intent = new Intent(v.getContext(), ActStep0405.class);
                startActivity(intent);
            }

            else if(v == btnStep5_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0502.class);
                startActivity(intent);
            }
            else if(v == btnStep5_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0503.class);
                startActivity(intent);
            }
            else if(v == btnStep5_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0504.class);
                startActivity(intent);
            }
            else if(v == btnStep5_5)
            {

                Intent intent = new Intent(v.getContext(), ActStep0505.class);
                startActivity(intent);
            }
            else if(v == btnStep6_1) {

                Intent intent = new Intent(v.getContext(), ActStep0601.class);
                startActivity(intent);
            }
            else if(v == btnStep6_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0602.class);
                startActivity(intent);
            }
            else if(v == btnStep6_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0603.class);
                startActivity(intent);
            }
            else if(v == btnStep6_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0604.class);
                startActivity(intent);
            }

            else if(v == btnStep6_5)
            {
                Intent intent = new Intent(v.getContext(), ActStep0605.class);
                startActivity(intent);
            }

            else if(v == btnStep7_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0701.class);
                startActivity(intent);
            }
            else if(v == btnStep7_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0702.class);
                startActivity(intent);
            }
            else if(v == btnStep7_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0703.class);
                startActivity(intent);
            }
            else if(v == btnStep7_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0704.class);
                startActivity(intent);
            }
            else if(v == btnStep7_5)
            {

                Intent intent = new Intent(v.getContext(), ActStep0705.class);
                startActivity(intent);
            }
            else if(v == btnStep10)
            {

                Intent intent = new Intent(v.getContext(), ActStep10.class);
                startActivity(intent);
            }

            else if(v == findViewById(R.id.btn_step_selector)){
                    Intent intent = new Intent(v.getContext(), ActStartLearning.class);
                    startActivity(intent);
                    return;
            }
            else if(v == btnSelector){
                Intent intent = new Intent(v.getContext(), ActStartLearning.class);
                startActivity(intent);
            }
        }
    };
}
