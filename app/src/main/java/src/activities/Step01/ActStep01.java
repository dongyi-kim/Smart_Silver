package src.activities.Step01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.Utility;
import src.activities.FrameActivity;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import src.viewes.ViewNumberPad;


/**
 * Created by waps12b on 15. 3. 15..
 */
public class ActStep01 extends StageActivity {

    private TextView txtDescrib;
    private TextView txtQuestion;
    private TextView txtAnswer;
    private ViewNumberPad vNumberPad;

    private StringBuffer buffAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01);

        txtDescrib = (TextView)findViewById(R.id.txt_description);
        txtQuestion = (TextView)findViewById(R.id.txt_question);
        txtAnswer = (TextView)findViewById(R.id.txt_answer);
        vNumberPad = (ViewNumberPad)findViewById(R.id.view_number_pad);
        iStage = 1;
        setQuestion(false,"123456789");
    }

    public void setQuestion( boolean isRetry, Object object)
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
            Random rnd = new Random();
            char[] arrStr = new char[9];

            for(int i=0; i<9; i++)
            {
                arrStr[i] = Integer.toString(rnd.nextInt(10)).charAt(0);
            }
            strQuest = new String(arrStr);
            vNumberPad.shuffleNumber();
        }

        if(! Utility.isNumber(strQuest))
            return;

        txtQuestion.setText(strQuest);
        txtAnswer.setText("");
        buffAnswer = new StringBuffer("");
    }

    public void checkAnswer(Object object)
    {
        boolean isAnswer = txtAnswer.getText().equals(txtQuestion.getText());
        DlgResultMark dlg = new DlgResultMark(this, isAnswer);
        dlg.show();

        if( ++ iStage > NUM_OF_STAGE)
        {   // go next level
            goNext();
        }else
        {   //go next level
            setQuestion(!isAnswer);
        }
    }
    public void goNext(Object object)
    {
        Intent intent = new Intent(this, ActStep0103.class);
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
    public void onGetEvent(Object vSender, Object obj)
    {
        if(vSender == vNumberPad)
        {
            int iCode = (int)obj;
            switch (iCode)
            {
                case ViewNumberPad.CODE_MODIFY :
                    modifyAnswer();
                    break;

                case ViewNumberPad.CODE_OKAY :
                    checkAnswer();
                    break;

                default:
                    catAnswer(iCode);
            }
        }
    }
}
