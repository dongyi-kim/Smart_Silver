package src.activities.Report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;
import src.Setting;
import src.Utility;
import src.data.DB;
import src.data.ResultData;

/**
 * Created by waps12b on 16. 1. 24..
 */
public class LearningProgressFragment extends Fragment {
    private ResultData[] dataAll = null;
    private ResultData[] dataToday = null;

    @Bind(R.id.linear_table) LinearLayout linearTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_progress, container, false);
        ButterKnife.bind(this, view);

        String strToday = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        dataAll = DB.getResultData(String.format("SELECT * FROM %s ORDER BY timestamp;", DB.TABLE_RESULT));
        dataToday = DB.getResultData(String.format("SELECT * FROM %s WHERE timestamp >= \""+strToday+"\" ORDER BY timestamp;", DB.TABLE_RESULT));


        drawTable();

        return view;
    }

    private void drawTable(){


        int[][] successed = new int[11][];
        int[][] failed = new int[11][];
        for(int i = 1 ; i <= 10 ; i++){
            successed[i] = new int[Utility.getNumberOfLevel(i)+1];
            failed[i] = new int[Utility.getNumberOfLevel(i)+1];
        }

        if (dataAll != null) {
            for(ResultData data : dataAll){
                LinearLayout row = (LinearLayout)linearTable.getChildAt(data.iStep);
                TextView textView = (TextView)row.getChildAt(data.iLevel);
                textView.setBackgroundColor(Color.rgb(250,248,132));
                if(data.isSuccess)
                    successed[data.iStep][data.iLevel] ++;
                else
                    failed[data.iStage][data.iLevel]++;
            }
        }


        if(dataToday != null)
        {
            for(ResultData data : dataToday){
                LinearLayout row = (LinearLayout)linearTable.getChildAt(data.iStep);
                TextView textView = (TextView)row.getChildAt(data.iLevel);
                textView.setBackgroundColor(Color.rgb(186,237,145));
            }
        }


        for(int step = 1; step <= 10; step ++)
        {
            LinearLayout row = (LinearLayout)linearTable.getChildAt(step);
            for(int level = 1; level <= Utility.getNumberOfLevel(step); level++)
            {
                TextView textView = (TextView)row.getChildAt(level);
//                textView.append(String.format("\n\n+%d / -%d", successed[step][level], failed[step][level]));
            }
        }

    }
}
