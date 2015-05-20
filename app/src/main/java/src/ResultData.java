package src;

import android.app.UiAutomation;
import android.os.Environment;
import android.text.format.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by waps12b on 15. 3. 25..
 */
public class ResultData implements Serializable {

    private boolean isRecording;
    private long startTime ;

    public boolean isSuccess; //test result
    public long millisec;
    public final int iStep;
    public final int iLevel;
    public final int iStage;

    public ResultData(int step, int level, int stage)
    {
        this.iStep = step;
        this.iStage = stage;
        this.iLevel = level;

        isRecording = false;
        millisec = 0;
    }

    public long getMilliTime()
    {
        return millisec;
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
        millisec += System.currentTimeMillis() - startTime;
    }

}
