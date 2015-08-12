package src.activities.ResultPage;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.DB;
import src.ResultData;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 8. 7..
 */
public class RatioPerStepActivity extends FrameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statistics_ratio_per_step);

        TableLayout tableResult = (TableLayout)findViewById(R.id.table_result);
        for(int iStep=1; iStep<=10; iStep ++)
        {
            TableRow row = (TableRow) tableResult.getChildAt(iStep);
            LinearLayout block = (LinearLayout) row.getChildAt(1);

            LinearLayout linear_percent = (LinearLayout) block.getChildAt(0);
            TextView txt_percent = (TextView) block.getChildAt(1);

            float percent = 0.0f;

            ResultData[] res = DB.SELECT("SELECT * FROM RESULT_DATA WHERE step = '"+iStep+"'");
            if(res!=null && res.length > 0)
            {
                int iAll = 0;
                int iOK  = 0;
                for(int i=0;i<res.length;i++)
                {
                    iAll ++;
                    if(res[i].isSuccess)
                        iOK ++;
                }
                percent = (float)iOK / iAll;
            }

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)linear_percent.getLayoutParams();
            linear_percent.setLayoutParams(new LinearLayout.LayoutParams(0, params.height, percent * 10.0f));
            txt_percent.setText( String.format("%d%%",(int)(percent*100f)) );
        }

    }
}
