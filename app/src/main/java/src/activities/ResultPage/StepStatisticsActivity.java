package src.activities.ResultPage;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import cdmst.smartsilver.R;
import src.DB;
import src.ResultData;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 6. 23..
 */
public class StepStatisticsActivity extends FrameActivity {

    private int iStep = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statitics_step);
        iStep = getIntent().getExtras().getInt("iStep");
        ((TextView)findViewById(R.id.txt_title)).setText(iStep + "단계 학습 결과표");

        TableLayout tableLayout = (TableLayout)findViewById(R.id.table_result);
        for(int iLevel=1; iLevel<=5; iLevel++)
        {
            TableRow row = (TableRow)tableLayout.getChildAt(iLevel);
            for(int iStage=1; iStage<=5; iStage++)
            {
                TextView view = (TextView)row.getChildAt(iStage);
                String query = String.format("SELECT * FROM RESULT_DATA WHERE step = %d and level = %d and stage = %d ORDER BY _id DESC LIMIT 1",iStep, iLevel, iStage);
                ResultData[] data = DB.SELECT(query);
                if(data == null || data.length == 0)
                    return;

                view.setText( String.format("%.1f 초", data[0].getMilliTime()/1000.0));
                if(data[0].isSuccess)
                {
                    view.setBackgroundColor( getResources().getColor( R.color.result_success));
                }else
                {
                    view.setBackgroundColor( getResources().getColor( R.color.result_fail));
                }
            }
        }
    }
}
