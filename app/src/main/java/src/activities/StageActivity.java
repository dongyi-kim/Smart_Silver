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

    abstract void setQuestion();

    abstract void requireAnswer();

    abstract void checkAnswer();

    abstract void goNext();

}
