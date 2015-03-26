package src;

import java.io.Serializable;

/**
 * Created by waps12b on 15. 3. 25..
 */
public class ResultData implements Serializable {
    boolean isRecording;
    boolean isSuccess; //test result
    long lTime;

    long startTime ;
    public ResultData()
    {
        isRecording = false;
        lTime = 0;
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
