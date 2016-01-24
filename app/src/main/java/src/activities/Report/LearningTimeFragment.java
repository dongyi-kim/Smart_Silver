package src.activities.Report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;
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
        ResultData[] dataAll = DB.getResultData(String.format("SELECT * FROM %s ORDER BY timestamp;", DB.TABLE_RESULT));
        if(dataAll == null || dataAll.length == 0)
            return;


        String stampFirst = dataAll[0].timestamp.substring(0,10);
        String stampLast = dataAll[ dataAll.length-1  ].timestamp.substring(0, 10);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date start, end;

        try{
            start = sdf.parse(stampFirst);
            end = sdf.parse(stampLast);
        }catch (Exception ex) {
            return;
        }

        HashMap<String, Long> datemap = new HashMap<>();
        GregorianCalendar gcal = new GregorianCalendar();
        for(gcal.setTime(start); !gcal.getTime().after(end); gcal.add(Calendar.DAY_OF_YEAR, 1))
        {
            datemap.put( sdf.format(gcal.getTime()) , (long)0);
        }

        for(ResultData data : dataAll)
        {
            String date = data.timestamp.substring(0,10);
            long msec = datemap.get(date);
            msec += data.millisec;
            datemap.put(date, msec);
        }

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();
        int index = 0;
        for(String timestamp : datemap.keySet())
        {
            long msec = datemap.get(timestamp);

            String strDate = timestamp.substring(5,10);
            xVals.add(strDate);

            float minute = msec / 60000f;
            yVals.add( new Entry(minute, index ++) );
        }

        LineDataSet set1 = new LineDataSet(yVals, "학습시간");

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(xVals, dataSets);

        chart.setData(data);


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
