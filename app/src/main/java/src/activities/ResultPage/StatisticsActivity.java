package src.activities.ResultPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.data.DB;
import src.data.ResultData;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 5. 17..
 */
public class StatisticsActivity extends FrameActivity {

    private int get_minute(String query)
    {
        ResultData[] datas = DB.getResultData(query);
        if(datas == null || datas.length == 0)
            return 0;

        long millisec = 0;
        for(ResultData data : datas){
            millisec += data.millisec;
        }
        return (int)(millisec / 60000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statistics_main);

        //set play time
        TextView txtToday =  (TextView)findViewById(R.id.txt_playtime_today);
        int minuteToday = get_minute(String.format("SELECT * FROM %s WHERE timestamp >= date('now'); ", DB.TABLE_RESULT));
        txtToday.setText( minuteToday + "분" );

        TextView txtTotal =  (TextView)findViewById(R.id.txt_playtime_total);
        int minuteTotal = get_minute(String.format("SELECT * FROM %s ; ", DB.TABLE_RESULT));
        txtTotal.setText( minuteTotal + "분" );
    }

    @Override
    public void onGetEvent(Object vSender, Object obj) {
        Intent intent = null;
        if(vSender == findViewById(R.id.btn_result_step01)) {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",1);
        }else if(vSender == findViewById(R.id.btn_result_step02))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",2);
        }else if(vSender == findViewById(R.id.btn_result_step03))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",3);
        }else if(vSender == findViewById(R.id.btn_result_step04))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",4);
        }else if(vSender == findViewById(R.id.btn_result_step05))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",5);
        }else if(vSender == findViewById(R.id.btn_result_step06))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",6);
        }else if(vSender == findViewById(R.id.btn_result_step07))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",7);
        }else if(vSender == findViewById(R.id.btn_result_step08))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",8);
        }else if(vSender == findViewById(R.id.btn_result_step09))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",9);
        }else if(vSender == findViewById(R.id.btn_result_step10))
        {
            intent = new Intent(this, StepStatisticsActivity.class);
            intent.putExtra("iStep",10);
        }else if(vSender == findViewById(R.id.btn_statistics_step_all))
        {
            intent = new Intent(this, StepAllStatisticsActivity.class);
        }else if(vSender == findViewById(R.id.btn_statistics_ratio_per_step))
        {
            intent = new Intent(this, RatioPerStepActivity.class);
        }
        if(intent != null)
            startActivity(intent);

    }



}
