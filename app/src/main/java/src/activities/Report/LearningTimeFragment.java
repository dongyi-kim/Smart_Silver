package src.activities.Report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.LineDataProvider;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    public BarChart chart;


    private void setChartOption()
    {
        chart.getLegend().setEnabled(false);
        chart.setDescription("");

//        chart.setBorderColor(Color.WHITE);
        chart.setGridBackgroundColor(Color.TRANSPARENT);
//        chart.setDescriptionColor(Color.CYAN);
        chart.setBackgroundColor(Color.TRANSPARENT);
//
//        chart.getAxisLeft().setGridColor(Color.WHITE);
//        chart.getAxisLeft().setTextColor(Color.WHITE);
//        chart.getAxisLeft().setAxisLineColor(Color.WHITE);
//
//        chart.getAxisRight().setGridColor(Color.WHITE);
//        chart.getAxisRight().setTextColor(Color.WHITE);
//        chart.getAxisRight().setAxisLineColor(Color.WHITE);
//
//        chart.getXAxis().setGridColor(Color.WHITE);
//        chart.getXAxis().setTextColor(Color.WHITE);
//        chart.getXAxis().setAxisLineColor(Color.WHITE);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setTextSize(15);
        chart.getXAxis().setLabelsToSkip(0);
        chart.getXAxis().setDrawGridLines(false);


        BarDataSet set1 = chart.getData().getDataSetByIndex(0);
        float maxMinute = Math.max(1.0f, set1.getYMax() ) + 5.0f;
        int intMinue = (int)maxMinute;
        maxMinute = (intMinue / 5)*5;
        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int minute = (int)value;
                int second = (int) ((value - minute) * 60f);
                return String.format("%d분 %d초",minute, second);
            }
        });

        chart.getAxisLeft().setAxisMaxValue(maxMinute);
        chart.getAxisLeft().setShowOnlyMinMax(true);

        chart.getAxisRight().setAxisMaxValue(maxMinute);
        chart.getAxisRight().setShowOnlyMinMax(true);

        YAxisValueFormatter f = new YAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                return String.format("%d분",(int)value);
            }
        };
        chart.getAxisLeft().setValueFormatter(f);
        chart.getAxisRight().setValueFormatter(f);



    }

    private void initChart()
    {
        ResultData[] dataAll = DB.getResultData(String.format("SELECT * FROM %s ORDER BY timestamp;", DB.TABLE_RESULT));
        if(dataAll == null || dataAll.length == 0)
            return;


        String stampFirst = dataAll[0].timestamp.substring(0,10);
        String stampLast = dataAll[ dataAll.length-1  ].timestamp.substring(0, 10);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date start, end;
        end = Calendar.getInstance().getTime();
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(end);
        gcal.add(Calendar.DAY_OF_YEAR, -6);

        try{
            start = sdf.parse(stampFirst);
            if(gcal.getTime().before(start))
                start = gcal.getTime();
        }catch (Exception ex) {
            return;
        }

        HashMap<String, Long> datemap = new HashMap<>();

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
        ArrayList<BarEntry> yVals = new ArrayList<>();
        int index = 0;
        Object[] times = datemap.keySet().toArray();
        Arrays.sort(times);

        for(Object timestamp : times)
        {
            long msec = datemap.get((String) timestamp);

            String strDate = ((String)timestamp).substring(5,10);
            xVals.add(strDate);

            float minute = msec / 60000f;
            yVals.add( new BarEntry(minute, index ++) );
        }

        BarDataSet set1 = new BarDataSet(yVals, "학습시간");


        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        chart.setData(data);

        setChartOption();
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
