package src.activities.ResultPage;

import android.os.Bundle;
import android.os.PersistableBundle;

import cdmst.smartsilver.R;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 7. 9..
 */
public class StepAllStatisticsActivity extends FrameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.act_statistics_step_all);
    }
}
