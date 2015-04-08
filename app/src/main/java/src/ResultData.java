package src;

import java.io.Serializable;

/**
 * Created by waps12b on 15. 3. 25..
 */
public class ResultData implements Serializable {
    private boolean isRecording;
    private boolean isSuccess; //test result
    private long lTime;
    private long startTime ;

    public ResultData()
    {
        isRecording = false;
        lTime = 0;
    }

    public long getMilliTime()
    {
        return lTime;
    }

    public void Start()
    {
        isRecording = true;
        startTime = System.currentTimeMillis();
    }

    public void Stop(boolean bResult)
    {
        isRecording = false;
        isSuccess = bResult;
        lTime += System.currentTimeMillis() - startTime;
    }

}
