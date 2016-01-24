package src.activities.Report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import src.data.DB;
import src.data.ResultData;

/**
 * Created by waps12b on 16. 1. 24..
 */

public class LearningTimeFragment extends Fragment {

    @Bind(R.id.chart)
    public LineChart chart;



    private void initChart()
    {
        ResultData[] dataAll = DB.getResultData( String.format("SELECT * FROM %s ORDER BY timestamp;", DB.TABLE_RESULT) );
        if(dataAll == null || dataAll.length == 0)
            return;

        String stampFrist = dataAll[0].timestamp;
        String stampLast = dataAll[ dataAll.length-1  ].timestamp;

        HashMap<String, Integer> datemap = new HashMap<>();

        for(ResultData data : dataAll)
        {
            String date = data.timestamp.substring(0,9);
            if(!datemap.containsKey(date))
            {
                datemap.put(date, 0);
            }
            int msec = datemap.get(date);
            msec += data.millisec;
            datemap.put(date, msec);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_time, container, false);
        ButterKnife.bind(this, view);
        initChart();
        // TODO Use fields...
        return view;
    }
}
