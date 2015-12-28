package src.activities.Step01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.Utility;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;


/**
 * Created by waps12b on 15. 3. 15..
 */
public class ActStep0101 extends StageActivity implements View.OnClickListener {

    private TextView txtQuestion;
    private TextView txtAnswer;

    private StringBuffer buffAnswer;

    private int iRetry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01_1);


        ((Button)findViewById(R.id.btn_ok)).setOnClickListener(this);

        txtQuestion = (TextView)findViewById(R.id.txt_question);
        txtAnswer = (TextView)findViewById(R.id.txt_answer);
        iStage = 1;
        TableLayout table = (TableLayout)findViewById(R.id.table_number);
        for(int r=0;r<4;r++)
        {
            TableRow row = (TableRow)table.getChildAt(r);
            for(int c=0; c<3; c++)
            {
                Button col = (Button)row.getChildAt(c);
                col.setOnClickListener(this);
            }
        }
        setQuestion(false,null);
    }

    public synchronized void setQuestion( boolean isRetry, Object object)
    {
        String strQuest;
        if(iStage == 1)
        {
            strQuest = "123456789";
        }
        else if(isRetry)
        {
            strQuest = txtQuestion.getText().toString();
        }else
        {
            iRetry = 0;
            Random rnd = new Random();
            char[] arrStr = new char[9];

            for(int i=0; i<9; i++)
            {
                arrStr[i] = Integer.toString(rnd.nextInt(10)).charAt(0);
            }
            strQuest = new String(arrStr);
            //vNumberPad.shuffleNumber();
        }

        if(! Utility.isNumber(strQuest))
            return;

        txtQuestion.setText(strQuest);
        txtAnswer.setText("");
        buffAnswer = new StringBuffer("");
        if(!isRetry)
            super.StartRecording();
    }

    public synchronized void checkAnswer(Object object)
    {
        boolean isAnswer = txtAnswer.getText().equals(txtQuestion.getText());

        DlgResultMark dlg = new DlgResultMark(this, isAnswer);
        dlg.show();
        if(isAnswer == false)
            iRetry ++;

        if(isAnswer || iRetry >= 3 )
        {
            iStage ++;
            super.StopRecording(isAnswer);
            if(iStage > NUM_OF_STAGE)
            {   // next level
                goNext();
            }else
            {   //go next stage
                setQuestion(false);
            }
            return;
        }


        setQuestion(true);
    }
    public synchronized void goNext(Object object)
    {
        Intent intent = new Intent(this, ActStep0102.class);
        startActivity(intent);
    }

    public void modifyAnswer()
    {
        if(txtAnswer.length() == 0)
            return;

        buffAnswer.deleteCharAt(buffAnswer.length()-1);
        txtAnswer.setText(buffAnswer);
    }

    public void catAnswer(int iNum)
    {
        if(txtAnswer.length() >= txtQuestion.length())
            return;

        buffAnswer.append(iNum);
        txtAnswer.setText(buffAnswer);
    }

    @Override
    public void onClick(View vSender)
    {
        if(vSender == findViewById(R.id.btn_number_0))
            catAnswer(0);
        else if(vSender == findViewById(R.id.btn_number_1))
            catAnswer(1);
        else if(vSender == findViewById(R.id.btn_number_2))
            catAnswer(2);
        else if(vSender == findViewById(R.id.btn_number_3))
            catAnswer(3);
        else if(vSender == findViewById(R.id.btn_number_4))
            catAnswer(4);
        else if(vSender == findViewById(R.id.btn_number_5))
            catAnswer(5);
        else if(vSender == findViewById(R.id.btn_number_6))
            catAnswer(6);
        else if(vSender == findViewById(R.id.btn_number_7))
            catAnswer(7);
        else if(vSender == findViewById(R.id.btn_number_8))
            catAnswer(8);
        else if(vSender == findViewById(R.id.btn_number_9))
            catAnswer(9);
        else if(vSender == findViewById(R.id.btn_number_ok) || vSender == findViewById(R.id.btn_ok))
            checkAnswer(null);
        else if(vSender == findViewById(R.id.btn_number_erase))
            modifyAnswer();
    }
}
