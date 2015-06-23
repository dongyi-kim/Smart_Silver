package src.activities.ResultPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.DB;
import src.ResultData;
import src.Utility;
import src.activities.ActTest;
import src.activities.FrameActivity;
import ui.RippleView;

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

        //


    }

    @Override
    public void onGetEvent(Object vSender, Object obj) {
        Intent intent = new Intent(this, StepStatisticsActivity.class);

        if(vSender == findViewById(R.id.btn_result_step01)) {
            intent.putExtra("iStep",1);
        }else if(vSender == findViewById(R.id.btn_result_step02))
        {
            intent.putExtra("iStep",2);
        }else if(vSender == findViewById(R.id.btn_result_step03))
        {
            intent.putExtra("iStep",3);
        }else if(vSender == findViewById(R.id.btn_result_step04))
        {
            intent.putExtra("iStep",4);
        }else if(vSender == findViewById(R.id.btn_result_step05))
        {
            intent.putExtra("iStep",5);
        }else if(vSender == findViewById(R.id.btn_result_step06))
        {
            intent.putExtra("iStep",6);
        }else if(vSender == findViewById(R.id.btn_result_step07))
        {
            intent.putExtra("iStep",7);
        }else if(vSender == findViewById(R.id.btn_result_step08))
        {
            intent.putExtra("iStep",8);
        }else if(vSender == findViewById(R.id.btn_result_step09))
        {
            intent.putExtra("iStep",9);
        }else if(vSender == findViewById(R.id.btn_result_step10))
        {
            intent.putExtra("iStep",10);
        }
        startActivity(intent);

    }



}
