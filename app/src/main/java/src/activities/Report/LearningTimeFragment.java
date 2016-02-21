package src.activities.Report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

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

    @Bind(R.id.chart) BarChart chart;
    @Bind(R.id.piechart_learning_today) PieChart pieToday;
    @Bind(R.id.piechart_learning_total) PieChart pieTotal;

    public static final int[] WEEKLY_COLORS = new int[]{
            Color.rgb(250,205,205),
            Color.rgb(248,184,139),
            Color.rgb(248,250,205),
            Color.rgb(210,250,205),
            Color.rgb(205,205,236),
            Color.rgb(178,206,254),
            Color.rgb(236,205,250)
    };

    public static final int[] STEP_COLORS = new int[]{
            Color.rgb(244, 154, 194),
            Color.rgb(174, 198, 207),
            Color.rgb(119, 190, 119),
            Color.rgb(207, 207, 196),
            Color.rgb(179, 158, 181),
            Color.rgb(255, 179, 71),
            Color.rgb(255, 105, 97),
            Color.rgb(253, 253, 150),
            Color.rgb(130, 105, 83),
            Color.rgb(119, 158, 203),
    };

    private ResultData[] dataAll = null;
    private ResultData[] dataToday = null;

    private void setBarChartOption()
    {
        chart.getLegend().setEnabled(false);
//        chart.setDescription("최근 학습 시간");
//        chart.setDescriptionTextSize(30);

//        chart.setBorderColor(Color.WHITE);
        chart.setGridBackgroundColor(Color.TRANSPARENT);
//        chart.setDescriptionColor(Color.CYAN);
        chart.setBackgroundColor(Color.TRANSPARENT);
//        chart.setDrawBarShadow(true);
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
        float maxMinute = Math.max(1.0f, set1.getYMax() ) ;
        int intMinute = (int)maxMinute;
        maxMinute = intMinute+1;// (intMinue / 5)*5;
        set1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                int minute = (int) value;
                int second = (int) ((value - minute) * 60f);
                if(minute == 0){
                    if(second > 0)
                    {
                        return String.format("%d초",second);
                    }
                    return "";
                }
                return String.format("%d분", minute);
            }
        });

        chart.getAxisLeft().setAxisMaxValue(maxMinute);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setShowOnlyMinMax(true);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawAxisLine(true);

        chart.getAxisRight().setAxisMaxValue(maxMinute);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisRight().setShowOnlyMinMax(true);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawAxisLine(true);

        chart.setScaleYEnabled(false);



        YAxisValueFormatter f = new YAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                return String.format("%d분",(int)value);
            }
        };
        chart.getAxisLeft().setValueFormatter(f);
        chart.getAxisRight().setValueFormatter(f);
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);


        chart.invalidate();

    }

    private void initBarChart()
    {
        if(dataAll == null || dataAll.length == 0)
            return;


        String stampFirst = dataAll[0].timestamp.substring(0,10);
        String stampLast = dataAll[ dataAll.length-1  ].timestamp.substring(0, 10);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date start, end;
        end = Calendar.getInstance().getTime();
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(end);
        gcal.add(Calendar.DAY_OF_YEAR, -14);

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
            String date = data.timestamp.substring(0, 10);

            long msec = datemap.get(date);
            msec += data.millisec;
            datemap.put(date, msec);
        }

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<BarEntry> yVals = new ArrayList<>();
        int index = 0;
        Object[] times = datemap.keySet().toArray();
        Arrays.sort(times);

        int iValidCount = 0;
        float iValidSum = 0;
        for(Object timestamp : times)
        {
            long msec = datemap.get((String) timestamp);

            String date = ((String)timestamp).substring(5,10).replace("-","/");
//            if(date.substring(date.length()-2).equals("31") || !date.substring(date.length()-1).equals("1")){
//                date = "";
//            }else
            {
                if(date.charAt(0) =='0'){
                    date = date.substring(1);
                }
            }

            xVals.add(date);

            float minute = msec / 60000f;
            yVals.add( new BarEntry(minute, index ++) );
            if(minute>0){
                iValidSum += minute;
                iValidCount ++;
            }
        }
        if(iValidCount == 0){
            iValidCount ++;
        }
        xVals.add("평균");
        yVals.add(new BarEntry(iValidSum / iValidCount, index++));

        BarDataSet set1 = new BarDataSet(yVals, "학습시간");
        set1.setColors(WEEKLY_COLORS);


        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        chart.setData(data);

        setBarChartOption();
    }

    private void initPieTodayChart(){
        if(dataToday == null || dataToday.length == 0)
            return;
        Date dateNow = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        float[] timeStep = new float[10];
        float totalSeconds = 0;
        for(ResultData data : dataToday)
        {
            timeStep[data.iStep-1] += data.millisec/1000f;
            totalSeconds += data.millisec/1000f;
        }
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();

        for(int i = 1 ; i <= 10 ; i++){
            String xString = "";
            if(timeStep[i-1] > 0){
                xString = String.format("%d단원", i);
            }
            xVals.add(String.format(xString, i));
            yVals.add(new Entry(timeStep[i-1], i-1));

        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        dataSet.setColors(STEP_COLORS);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if (value <= 0)
                    return "";
                if (value < 60)
                    return String.format("%d초", (int) value);
                return String.format("%d분", (int) value / 60);
            }
        });


        PieData pieData = new PieData(xVals);
        pieData.setDataSet(dataSet);
        pieToday.setData(pieData);
        pieToday.setCenterText(String.format("%d분 %d초", (int) totalSeconds / 60, ((int) totalSeconds) % 60));
        pieToday.invalidate();
        pieToday.setCenterTextSize(30);

        pieToday.setDescription("");
        pieToday.getLegend().setEnabled(false);
    }

    private void initPieTotalChart()
    {
        if(dataAll == null || dataAll.length == 0)
            return;
        Date dateNow = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        float[] timeStep = new float[10];
        float totalSeconds = 0;
        for(ResultData data : dataAll)
        {
            timeStep[data.iStep-1] += data.millisec/1000f;
            totalSeconds += data.millisec/1000f;
        }
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();

        for(int i = 1 ; i <= 10 ; i++){
            String xString = "";
            if(timeStep[i-1] > 0){
                xString = String.format("%d단원", i);
            }
            xVals.add(String.format(xString, i));
            yVals.add(new Entry(timeStep[i-1], i-1));

        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        dataSet.setColors(STEP_COLORS);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if (value <= 0)
                    return "";
                if (value < 60)
                    return String.format("%d초", (int) value);
                return String.format("%d분", (int) value/60);
            }
        });


        PieData pieData = new PieData(xVals);
        pieData.setDataSet(dataSet);
        pieTotal.setData(pieData);
        pieTotal.setCenterText(String.format("%d분 %d초",(int)totalSeconds/60, ((int) totalSeconds)%60));
        pieTotal.invalidate();
        pieTotal.setDescription("");
        pieTotal.getLegend().setEnabled(false);
        pieTotal.setCenterTextSize(30);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_time, container, false);


        ButterKnife.bind(this, view);

        String strToday = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        dataAll = DB.getResultData(String.format("SELECT * FROM %s ORDER BY timestamp;", DB.TABLE_RESULT));
        dataToday = DB.getResultData(String.format("SELECT * FROM %s WHERE timestamp >= \""+strToday+"\" ORDER BY timestamp;", DB.TABLE_RESULT));
        initBarChart();
        initPieTodayChart();
        initPieTotalChart();
        // TODO Use fields...
        return view;
    }
}
