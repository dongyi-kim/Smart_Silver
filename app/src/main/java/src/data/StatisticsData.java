package src.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by waps12b on 15. 12. 8..
 */
public class StatisticsData {

    private static ArrayList<ResultData> statistics = null;
    public synchronized static void addResultIntoStatistics(ResultData data){
        if(statistics == null || data == null)
            return;
        statistics.add(data);
    }
    public static void startNewStatistics(){
        statistics = new ArrayList<>();
    }
    public static void clearStatistics(){
        statistics = null;
    }
    public synchronized static void saveStatistics(){
        if(statistics == null || statistics.size() == 0)
            return;
        DB.insertStatistics(new StatisticsData(statistics));
    }

    //( id INTEGER PRIMARY KEY AUTOINCREMENT, step INTEGER, success INTEGER, fail INTEGER, second INTEGER, timestamp DATE, user_id TEXT);");

    public final int step;
    public final int success;
    public final int fail;
    public final long seconds;
    public final String userId;
    private StatisticsData(ArrayList<ResultData> datas){
        step = datas.get(0).iStep;
        if(datas.get(0).userId != null){
            this.userId = datas.get(0).userId;
        }else{
            this.userId = null;
        }

        int totalSuccess = 0;
        int totalFail = 0;
        long totalSeconds = 0;
        for(ResultData data : datas){
            if(data.isSuccess)
                totalSuccess ++;
            else
                totalFail ++;

            totalSeconds += data.millisec / 1000;
        }

        this.success = totalSuccess;
        this.fail = totalFail;
        this.seconds = totalSeconds;
    }

    public StatisticsData(int step, int success, int fail, long seconds, String userId){
        this.step = step;
        this.success = success;
        this.fail = fail;
        this.seconds = seconds;
        this.userId = userId;
    }


    @Override
    public String toString(){
        if(userId == null){
            return String.format(" (null, '%d', '%d', '%d', '%d', DATETIME('NOW'), null) ", step, success, fail, seconds );
        }else{
            return String.format(" (null, '%d', '%d', '%d', '%d', DATETIME('NOW'), '%s') ", step, success, fail, seconds, userId );
        }
    }

}
