package src.activities.ResultPage;

import android.os.Bundle;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.DB;
import src.ResultData;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 5. 17..
 */
public class StatisticsActivity extends FrameActivity {

    private int get_minute(String query)
    {
        long millisec = 0;
        ResultData[] data = DB.SELECT(query);
        if(data == null)
            return 0;

        for(int i=0; i<data.length; i++)
        {
            millisec += data[i].millisec;
        }
        return (int)(millisec / 60000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statistics_main);

        //set play time
        TextView txtToday =  (TextView)findViewById(R.id.txt_playtime_today);
        txtToday.setText( get_minute("SELECT * FROM RESULT_DATA WHERE timestamp >= date('now');") + "분");

        TextView txtTotal =  (TextView)findViewById(R.id.txt_playtime_total);
        txtTotal.setText( get_minute("SELECT * FROM RESULT_DATA;") + "분");

    }


}
