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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import java.util.Random;

import cdmst.smartsilver.R;

/**
 * Created by Acka on 2015-03-18.
 */

public class ActStep0104 extends FrameActivity {


    LinearLayout linearDrawfield;
    LinearLayout linearDrawfieldRow[] = new LinearLayout[5];
    ImageButton ibtnNumber[][] = new ImageButton[5][5];
    TextView txtNumber[][] = new TextView[5][5];
    ImageView imgDiscription;

    boolean isSelected[][] = new boolean[5][5];
    boolean isOdd;
    int iNumCount;
    int iBtnValue[][] = new int[5][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_4);

        linearDrawfield = (LinearLayout)findViewById(R.id.drawfield);
        linearDrawfieldRow[0] = (LinearLayout)findViewById(R.id.drawfield_row_1);
        linearDrawfieldRow[1] = (LinearLayout)findViewById(R.id.drawfield_row_2);
        linearDrawfieldRow[2] = (LinearLayout)findViewById(R.id.drawfield_row_3);
        linearDrawfieldRow[3] = (LinearLayout)findViewById(R.id.drawfield_row_4);
        linearDrawfieldRow[4] = (LinearLayout)findViewById(R.id.drawfield_row_5);
        for(int i = 0; i < 5; i++){
            ibtnNumber[i][0] = (ImageButton)(linearDrawfieldRow[0].findViewById(R.id.btn_col_1));
            ibtnNumber[i][1] = (ImageButton)(linearDrawfieldRow[0].findViewById(R.id.btn_col_2));
            ibtnNumber[i][2] = (ImageButton)(linearDrawfieldRow[0].findViewById(R.id.btn_col_3));
            ibtnNumber[i][3] = (ImageButton)(linearDrawfieldRow[0].findViewById(R.id.btn_col_4));
            ibtnNumber[i][4] = (ImageButton)(linearDrawfieldRow[0].findViewById(R.id.btn_col_5));

            txtNumber[i][0] = (TextView)(linearDrawfieldRow[0].findViewById(R.id.txt_col_1));
            txtNumber[i][1] = (TextView)(linearDrawfieldRow[0].findViewById(R.id.txt_col_2));
            txtNumber[i][2] = (TextView)(linearDrawfieldRow[0].findViewById(R.id.txt_col_3));
            txtNumber[i][3] = (TextView)(linearDrawfieldRow[0].findViewById(R.id.txt_col_4));
            txtNumber[i][4] = (TextView)(linearDrawfieldRow[4].findViewById(R.id.txt_col_5));
        }
        imgDiscription = (ImageView)findViewById(R.id.text_discription);
        Random rand = new Random();
        isOdd = rand.nextBoolean();
        iNumCount = rand.nextInt(8) + 1;

        if(isOdd) imgDiscription.setImageResource(R.drawable.discription_01_04_odd);

        int cnt = 0;
        while(cnt < iNumCount){
            int i = rand.nextInt(5);
            int j = rand.nextInt(5);

            if(isSelected[i][j]) continue;
            isSelected[i][j] = true;
            iBtnValue[i][j] = rand.nextInt(100);
            txtNumber[i][j].setText(Integer.toString(iBtnValue[i][j]));
            cnt++;
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(isSelected[i][j]) continue;
                ibtnNumber[i][j].setVisibility(View.INVISIBLE);
                txtNumber[i][j].setVisibility(View.INVISIBLE);
            }
        }


    }
}
