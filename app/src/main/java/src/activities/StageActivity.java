package src.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import src.ResultData;

/**
 * Created by waps12b on 15. 3. 25..
 */
public abstract class StageActivity extends FrameActivity {
    public int NUM_OF_STAGE = 5;
    public int iStage = 1;
    public ResultData[] arrResult;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        arrResult = new ResultData[NUM_OF_STAGE];
    }

    public void StartRecording()
    {
        arrResult[iStage-1] = new ResultData();
        arrResult[iStage].Start();
    }

    public void StopRecording(boolean bResult)
    {

        arrResult[iStage-1].Stop(bResult);

        //toast text
        StringBuffer strbuff = new StringBuffer("");

        //result
        if(bResult)
            strbuff.append("[Accepted] ");
        else
            strbuff.append("[Wrong] ");

        //time stamp;

        Toast toast = Toast.makeText(getApplicationContext(),
                "토스트창에 출력될 문자", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
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
