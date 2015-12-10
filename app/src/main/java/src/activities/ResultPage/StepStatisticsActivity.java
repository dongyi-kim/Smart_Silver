package src.activities.ResultPage;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import cdmst.smartsilver.R;
import src.Setting;
import src.data.DB;
import src.data.ResultData;
import src.activities.FrameActivity;
import src.data.StatisticsData;

/**
 * Created by waps12b on 15. 6. 23..
 */
public class StepStatisticsActivity extends FrameActivity {

    private int iStep = 1;
    private CombinedChart mChart;
    private StatisticsData[] mData;
    private String[] mAxisName;

    private void initChart(){

        mChart = (CombinedChart) findViewById(R.id.chart1);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setAxisMaxValue(110.0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaxValue(110.0f);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setLabelRotationAngle(10f);
        xAxis.setLabelsToSkip(0);
        mChart.setScaleEnabled(false);


        mChart.invalidate();
    }

    private void setData() {
        LineData lineData = new LineData();
        BarData barData = new BarData();
        ArrayList<Entry> lineEntries = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        int index = 0;
        int globalSuccess = 0;
        int globalCount = 0;
        long globalSeconds = 0;
        for(StatisticsData data : mData){
            int totalSuccess = data.success;
            int totalCount = data.success + data.fail;
            long totalSeconds = data.seconds;

            if(totalCount == 0)
            {   //null data set
                totalCount ++;
            }else{
                globalCount += totalCount;
                globalSuccess += totalSuccess;
                globalSeconds += totalSeconds;
            }

            entries.add(new BarEntry((float)totalSuccess/totalCount * 100f ,  index));
            lineEntries.add(new Entry((float)totalSeconds/totalCount, index));
            index ++;

        }

        //average
        if(globalCount == 0)
            globalCount ++;
        entries.add(new BarEntry( (float)globalSuccess/globalCount * 100f, index));
        lineEntries.add(new Entry((float)globalSeconds/globalCount, index));

        BarDataSet barDataSet = new BarDataSet(entries, "정답률");
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "걸린시간");

        barDataSet.setColor(Color.rgb(60, 220, 78));
        barDataSet.setValueTextColor(Color.rgb(60, 220, 78));
        barDataSet.setValueTextSize(10f);
        barData.addDataSet(barDataSet);

        lineDataSet.setColor(Color.rgb(240, 238, 70));
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleColor(Color.rgb(240, 238, 70));
        lineDataSet.setCircleSize(5f);
        lineDataSet.setFillColor(Color.rgb(240, 238, 70));
        lineDataSet.setDrawCubic(true);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(Color.rgb(240, 238, 70));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setDrawCircles(true);
        lineData.addDataSet(lineDataSet);


        CombinedData data = new CombinedData(mAxisName);
        data.setData(barData);
        data.setData(lineData);


        mChart.setData(data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statistics_step);
        iStep = getIntent().getExtras().getInt("iStep");
        ((TextView)findViewById(R.id.txt_title)).setText(iStep + "단계 학습 결과표");

        mChart = (CombinedChart)findViewById(R.id.chart1);

        mData = DB.getStatisticsData(String.format("SELECT * FROM %s WHERE step = '%d' ; ", DB.TABLE_STATISTICS, iStep ));
        if(mData == null || mData.length == 0)
            return;
        mAxisName = new String[mData.length + 1];
        mAxisName[mAxisName.length - 1] = "평균";
        for(int i = 0 ; i < mData.length; i++){
            mAxisName[i] = String.format("#%d", i+1 );
        }

        initChart();
        setData();


    }
}
