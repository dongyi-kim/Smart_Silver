package src.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

import cdmst.smartsilver.R;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0104 extends FrameActivity {

    ImageView imgDiscription;
    GridLayout layDrawfield;
    ImageButton ibtnNumber[] = new ImageButton[20];
    boolean bIsOdd;
    int iNumCount;
    int iNumSet[] = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_4);

        imgDiscription = (ImageView)findViewById(R.id.text_discription);
        layDrawfield = (GridLayout)findViewById(R.id.drwafield);
        Random rand = new Random();
        bIsOdd = rand.nextBoolean();
        iNumCount = rand.nextInt(20);

        if(bIsOdd) imgDiscription.setImageResource(R.drawable.discription_01_04_odd);
/*
        for(int i = 0; i < iNumCount; i++) {
            iNumSet[i] = rand.nextInt(10);
            ibtnNumber[i].setBackgroundResource(R.drawable.round_number_1);
            ibtnNumber[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.round_number_1));
            ibtnNumber[i].setLayoutParams(new ActionBar.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layDrawfield.addView(ibtnNumber[i]);

            ibtnNumber[i].setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                }});
        }
*/
    }
}
