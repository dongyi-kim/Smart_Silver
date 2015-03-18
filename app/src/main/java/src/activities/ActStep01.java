package src.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.Utility;
import src.viewes.FrameView;
import src.viewes.ViewNumberPad;


/**
 * Created by waps12b on 15. 3. 15..
 */
public class ActStep01 extends FrameActivity {

    public static final int NUM_OF_STAGE = 10;

    private TextView txtDescrib;
    private TextView txtQuestion;
    private TextView txtAnswer;
    private FrameLayout frameNumberPad;
    private ViewNumberPad vNumberPad;

    private StringBuffer buffAnswer;

    private int iStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_01);

        txtDescrib = (TextView)findViewById(R.id.txt_description);
        txtQuestion = (TextView)findViewById(R.id.txt_question);
        txtAnswer = (TextView)findViewById(R.id.txt_answer);
        //vNumberPad = (ViewNumberPad)findViewById(R.id.view_number_pad);

        vNumberPad = new ViewNumberPad(this);

        frameNumberPad = (FrameLayout)findViewById(R.id.frame_view_number_pad);
        frameNumberPad.removeAllViews();
        frameNumberPad.addView(vNumberPad);

        iStage = 1;

        setQuestion("123456789");
    }



    public void setQuestion(String strQuest)
    {
        if(! Utility.isNumber(strQuest))
            return;

        txtQuestion.setText(strQuest);
        txtAnswer.setText("");
        buffAnswer = new StringBuffer("");
    }

    public void checkAnswer()
    {


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
    public void onGetEvent(View vSender, Object obj)
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
