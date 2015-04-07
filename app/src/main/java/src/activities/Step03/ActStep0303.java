package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-29.
 */
public class ActStep0303 extends StageActivity{
    private static final int MAX_STAGE_NUMBER = 5;

    private TextView txtDiscription;
    private ImageView imgGrandma;
    private ImageView imgGrandfa;
    private ImageView imgGrandmaFoodSpace;
    private ImageView imgGrandfaFoodSpace;
    private TextView txtGrandmaFood;
    private TextView txtGrandfaFood;
    private Button btnAnswer[] = new Button[3];

    private int iStage = 1;
    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0303DataSet dataSet = new Step0303DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_3);

        txtDiscription = (TextView)findViewById(R.id.text_discription);
        imgGrandma = (ImageView)findViewById(R.id.imgGrandma);
        imgGrandfa = (ImageView)findViewById(R.id.imgGrandfa);
        imgGrandmaFoodSpace = (ImageView)findViewById(R.id.img_grandma_food_space);
        imgGrandfaFoodSpace = (ImageView)findViewById(R.id.img_grandfa_food_space);
        txtGrandmaFood = (TextView)findViewById(R.id.txt_grandma_food);
        txtGrandfaFood = (TextView)findViewById(R.id.txt_grandfa_food);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Button btnCurrentButton = (Button)v;
                    int iSelectAnswer = Integer.parseInt(btnCurrentButton.getText().toString());
                    if(iSelectAnswer == dataSet.iAnswer) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }


    public void setQuestion(boolean isRetry, Object object){
        int iRandomSeed = iStage - 1;
        dataSet.setData(iRandomSeed);

        //set problem
        txtDiscription.setText(dataSet.sDiscription);
        imgGrandma.setImageResource(dataSet.iGrandmaImage);
        imgGrandfa.setImageResource(dataSet.iGrandfaImage);
        if(iStage >= 5){
            imgGrandmaFoodSpace.setImageResource(R.drawable.empty_space_the_number_food_extend);
            imgGrandfaFoodSpace.setImageResource(R.drawable.empty_space_the_number_food_extend);
        }
        txtGrandmaFood.setText(dataSet.sGrandmaFood);
        txtGrandfaFood.setText(dataSet.sGrandfaFood);

        //set button
        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + dataSet.iBtnList[i]);
    }


    public void checkAnswer(Object object){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    Log.i("tag", "" + iStage);
                    if(iStage >= MAX_STAGE_NUMBER) goNext();
                    else {
                        iRetryCount = 0;
                        iStage++;
                        setQuestion(false);
                    }
                }
                else{
                    iRetryCount++;
                }
            }
        });
    }

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStep0301.class);
        startActivity(intent);
    }
}
