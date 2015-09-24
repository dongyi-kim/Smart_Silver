package src.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import src.DB;
import src.ResultData;
import src.Utility;

/**
 * Created by waps12b on 15. 3. 25..
 */
public abstract class StageActivity extends FrameActivity {
    public final int NUM_OF_STAGE = Utility.getNumOfStage(this.getClass());
    public int iStep;
    public int iLevel;
    public int iStage = 1;
    public ResultData dataNow = null;

    public static Toast nowToast = null;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        iStep  = Utility.getStep(this.getClass());
        iLevel = Utility.getLevel(this.getClass());
        iStage = 1;
    }

    public synchronized void StartRecording()
    {
        if(dataNow!=null){
            Log.d("Start Recoding","but not null");
            return;
        }
        dataNow = new ResultData( iStep, iLevel,iStage);
        dataNow.Start();
    }

    public synchronized void StopRecording(boolean bResult)
    {
        if(dataNow == null){
            Log.d("Stop Recoding","but null");
            return;
        }
        dataNow.Stop(bResult);


        //toast text
        StringBuffer strbuff = new StringBuffer("");

        //result
        if(bResult)
            strbuff.append("[정답] ");
        else
            strbuff.append("[오답] ");

        //time stamp;
        strbuff.append((dataNow.getMilliTime()/1000) + "초 걸렸어요!");

        //save data into db

        if(nowToast != null){
            nowToast.cancel();
            nowToast = null;
        }

        nowToast = Toast.makeText(this, strbuff.toString(), Toast.LENGTH_SHORT);
        nowToast.show();

        DB.INSERT(dataNow);
        dataNow = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(dataNow != null)
        {
            dataNow.Stop(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dataNow != null)
        {
            dataNow.Start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dataNow!=null)
        {
            dataNow = null;
        }
        iStage = 1;
    }

    //copy & paste
    // public void setQuestion( boolean isRetry, Object object);
    // public void checkAnswer(Object object);
    // public void goNext(Object object);


    /* void setQustion(boolean isRetry, Object object)
     * - object는 옵션사항. 필요할 시 사용
     * - 각 Stage의 문제에 대한 초기화를 진행
     * - 문제와 보기를 초기화
     * - isRetry가 false면 답 입력창을 초기화한 후 return,
     * - isRetry가 true면 문제와 보기를 새롭게 설정
     * - 새로운 보기 설정 시 Listener를 통해 checkAnswer()를 호출
     */
    public abstract void setQuestion( boolean isRetry, Object object);
    public final void setQuestion(boolean isRetry) { setQuestion(isRetry, null); }


    /* void checkAnswer(Object object)
     * - object는 선택사항 필요할 시 사용.
     * - iStage를 1 증가시켜줌, 이 때 iStage > NUM_OF_STAGE면 goNext();
     * - DlgResultMark로 결과 통보
     * - 정답이면 setQuestion()을 호출하여 새로운 문제 준비
     * -
     */
    public abstract void checkAnswer(Object object);
    public final void checkAnswer() { checkAnswer(null); }

    /* void goNext()
     * - iStage > NUM_OF_STAGE 가 될 시 checkAnswer()에 의해 호출 됨.
     * - 다음 Level혹은 Step을 호출
     */
    public abstract void goNext(Object object);
    public final void goNext() { goNext(null); };


}
