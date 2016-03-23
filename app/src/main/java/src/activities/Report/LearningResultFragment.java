package src.activities.Report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;
import src.data.DB;
import src.data.ResultData;

/**
 * Created by waps12b on 16. 1. 24..
 */
public class LearningResultFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    public static final String[] SPINNER_STEP_ITEM = {"전체", "스탭1","스탭2","스탭3","스탭4","스탭5","스탭6","스탭7","스탭8","스탭9","스탭10"};
    public static final String[] SPINNER_DURATION_ITEM = {"전체", "오늘"};

    public static final int ITEM_ALL_STEP = 0;

    private ResultData[] dataAll = null;
    private ResultData[] dataToday = null;
    private ResultData[] dataSelected = null;

    @Bind(R.id.spinner_step) Spinner spinnerStep;
    @Bind(R.id.combined_chart) CombinedChart resultCombinedChart;
    @Bind(R.id.chart_radar) RadarChart resultRadarChart;
    @Bind(R.id.spinner_range) Spinner spinnerRange;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_result, container, false);
        ButterKnife.bind(this, view);
        String strToday = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        dataAll = DB.getResultData(String.format("SELECT * FROM %s ORDER BY timestamp;", DB.TABLE_RESULT));
        dataToday = DB.getResultData(String.format("SELECT * FROM %s WHERE timestamp >= \""+strToday+"\" ORDER BY timestamp;", DB.TABLE_RESULT));

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),R.layout.spinner_simple_item, SPINNER_STEP_ITEM);
        spinnerStep.setAdapter(adapter1);
        spinnerStep.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),R.layout.spinner_simple_item, SPINNER_DURATION_ITEM);
        spinnerRange.setAdapter(adapter2);
        spinnerRange.setOnItemSelectedListener(this);


        initRadarChartOption();
        initCombinedChartOption();



        spinnerStep.setSelection(0);
        spinnerRange.setSelection(0);
        return view;

    }

    private void initCombinedChartOption(){

        YAxis left = resultCombinedChart.getAxisLeft();
        YAxis right = resultCombinedChart.getAxisRight();
        left.setDrawLabels(false);
        right.setDrawLabels(false);
        left.setAxisMaxValue(1.1f);
        right.setAxisMaxValue(1.1f);
        left.setShowOnlyMinMax(true);
        right.setShowOnlyMinMax(true);

        resultCombinedChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        resultCombinedChart.setScaleYEnabled(false);
        resultCombinedChart.invalidate();

    }
    private void initRadarChartOption(){

    }


    private float getChartValue(float value, float min, float max){
        return (value - min) / (max - min);
    }
    private void drawCombinedChart(ResultData[] datas){

//        ResultData dataLast = null;
//        ArrayList<Integer> listSuccess = new ArrayList<>();
//        ArrayList<Integer> listFailed = new ArrayList<>();
//        ArrayList<Float> listTime = new ArrayList<>();

//        String stampFirst = dataAll[0].timestamp.substring(0,10);
//        String stampLast = dataAll[ dataAll.length-1  ].timestamp.substring(0, 10);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date start, end;
//        end = Calendar.getInstance().getTime();
//        GregorianCalendar gcal = new GregorianCalendar();
//        gcal.setTime(end);
//        gcal.add(Calendar.DAY_OF_YEAR, -14);
//
//        try{
//            start = sdf.parse(stampFirst);
//            if(gcal.getTime().before(start))
//                start = gcal.getTime();
//        }catch (Exception ex) {
//            return;
//        }

        HashMap<String, Integer> mapSuccess = new HashMap<>();

        HashMap<String, Integer> mapFail = new HashMap<>();

        HashMap<String, Long> mapTime = new HashMap<>();

        HashMap<String, Integer> mapTry = new HashMap<>();


        if(datas == null || datas.length==0)
            return;

        for(ResultData data: datas )
        {
            String date = data.timestamp.substring(0, 10);
            mapSuccess.put(date,0);
            mapFail.put(date,0);
            mapTime.put(date,0L);
            mapTry.put(date,0);
        }


        long globalTime=0;
        int globalSuccess=0;
        int globalFail=0;
        int globalTry=0;


        for(ResultData data : datas)
        {
            String date = data.timestamp.substring(0, 10);
            if(data.isSuccess){
                mapSuccess.put(date, mapSuccess.get(date)+1 );
            }else{
                mapFail.put(date,mapFail.get(date)+1);
            }
            mapTry.put(date, mapTry.get(date)+data.tryCount);
            mapTime.put(date, mapTime.get(date)+data.millisec);


            globalTime += data.millisec;
            if(data.isSuccess){
                globalSuccess++;
            }else{
                globalFail++;
            }
            globalTry+=data.tryCount;
        }

        float globalCount = globalFail + globalSuccess;
        float mTime = globalTime / globalCount;
        float mTry = globalTry / globalCount;
        float mRatio =  globalSuccess / globalCount;

        float mmTime = mTime;
        float mmTry = mTry;
        float mmRatio = mRatio;
        for(String key : mapTime.keySet()){
            int cnt = mapSuccess.get(key) + mapFail.get(key);
            mTime = Math.max(mTime, mapTime.get(key)/cnt);
            mTry = Math.max(mTry, mapTry.get(key)/cnt);
            mRatio = Math.max(mRatio, (float)(mapSuccess.get(key))/cnt);
            mmTime = Math.min(mmTime, mapTime.get(key)/cnt);
            mmTry = Math.min(mmTry, mapTry.get(key)/cnt);
            mmRatio = Math.min(mmRatio, (float)(mapSuccess.get(key))/cnt);
        }

        final float maxTime = mTime*1.1f;
        final float maxTry = mTry*1.1f;
        final float maxRatio = mRatio*1.1f;
        final float minTime = mmTime;
        final float minTry = mmTry;
        final float minRatio = mmRatio;

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<BarEntry> yValsBar = new ArrayList<>();
        ArrayList<Entry> yValsTry = new ArrayList<>();
        ArrayList<Entry> yValsTime = new ArrayList<>();
        int index = 0;
        Object[] times = mapTime.keySet().toArray();
        Arrays.sort(times);

        int iValidCount = 0;
        float iValidSum = 0;


        for(Object timestamp : times)
        {
            long msec = mapTime.get((String) timestamp);
            int success = mapSuccess.get((String) timestamp);
            int fail = mapFail.get((String) timestamp);
            int count = success + fail;
            float ratio = (float)(success)/count;
            int tryCount = mapTry.get((String) timestamp);

            yValsBar.add(new BarEntry(ratio, index));
            yValsTry.add(new Entry(((float)tryCount/count)/maxTry, index));
            yValsTime.add(new Entry( ((float)msec/count)/maxTime, index));

            String date = ((String)timestamp).substring(5,10).replace("-","/");
//            if(date.substring(date.length()-2).equals("31") || !date.substring(date.length()-1).equals("1")){
//                date = "";
//            }else
            {
                if(date.charAt(0) =='0'){
                    date = date.substring(1);
                }
            }
            index ++;
            xVals.add(date);
        }
        xVals.add("평균");
        yValsBar.add(new BarEntry( ((float)globalSuccess / globalCount) , index));
        yValsTry.add(new Entry(  getChartValue((float)globalTry/globalCount, minTry,maxTry), index));
        yValsTime.add(new Entry(getChartValue((float)globalTime/globalCount, minTime, maxTime), index));

        BarDataSet barDataSet = new BarDataSet(yValsBar, "정답률");
        LineDataSet lineDataSet = new LineDataSet(yValsTry, "시도횟수");
        LineDataSet lineDataSet1 = new LineDataSet(yValsTime,"시간");


        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.format("%.1f", value * (maxTry -minTry) + minTry);
            }
        });
        lineDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.format("%.1f", (value * (maxTime-minTime) + minTime )/1000f );
            }
        });
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.format("%.1f", value * 100f);
            }
        });


        lineDataSet.setColor(Color.rgb(0, 163, 0));
        lineDataSet1.setColor(Color.rgb(0, 171, 169));
        barDataSet.setColor(Color.rgb(159, 0, 167));

        LineData lineData = new LineData(xVals);
        lineData.addDataSet(lineDataSet);
        lineData.addDataSet(lineDataSet1);
        BarData barData = new BarData(xVals);
        barData.addDataSet(barDataSet);

        CombinedData combinedData = new CombinedData(xVals);
        combinedData.setData(barData);
        combinedData.setData(lineData);
        resultCombinedChart.setData(combinedData);


        resultCombinedChart.invalidate();
    }

    private void drawRadarChart(ResultData[] datas){

        resultRadarChart.invalidate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idxStep = spinnerStep.getSelectedItemPosition();
        int idxDuration = spinnerRange.getSelectedItemPosition();
        if( idxStep < 0 || idxDuration < 0 ) {
            return;
        }
        ResultData[] resultDatas = null;

        if(idxDuration == 0){
            resultDatas = dataAll;
        }else if(idxDuration == 1){
            resultDatas = dataToday;
        }

        if(resultDatas == null){
            return;
        }

        if(position!=ITEM_ALL_STEP){
            ArrayList<ResultData> arrayList =new ArrayList<>();
            for(ResultData data : resultDatas){
                if(data.iStep == position){
                    arrayList.add(data);
                }
            }
            resultDatas = new ResultData[arrayList.size()];
            for(int i = 0 ; i < arrayList.size(); i++){
                resultDatas[i] = arrayList.get(i);
            }
        }
        drawCombinedChart(resultDatas);
        drawRadarChart(resultDatas);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
