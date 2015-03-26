package src.activities;

import android.os.Bundle;

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
        arrResult[iStage] = new ResultData();
        arrResult[iStage].Start();
    }

    public void StopRecording(boolean bResult)
    {
        arrResult[iStage].Stop(bResult);
    }

    //문제에 대한 초기화
    //완료 후 requireAnswer()를 호출
    abstract void setQuestion();

    //답변 요구
    //입력창이 있는 문제의 경우 답변칸 초기화
    abstract void requireAnswer();

    //정답 확인
    //정답이면 goNext()호출
    abstract void checkAnswer();

    //다음 레벨, 혹은 다음 스탭 호출
    abstract void goNext();

}
