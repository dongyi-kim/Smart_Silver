package src.activities;

import android.content.Intent;
import android.os.Bundle;

import cdmst.smartsilver.R;
import src.activities.Report.ReportActivity;
import src.data.DB;
import src.activities.ResultPage.StatisticsActivity;
import ui.RippleView;


public class ActMain extends FrameActivity {


    RippleView rippleLearn;
    RippleView rippleMyResult;
    RippleView rippleDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB.INIT(getApplicationContext());
        setContentView(R.layout.act_main);

        rippleLearn = (RippleView) findViewById(R.id.ripple_learning);
        rippleMyResult = (RippleView) findViewById(R.id.ripple_my_result);
        rippleDeveloper = (RippleView) findViewById(R.id.ripple_developer);
    }


    @Override
    public void onGetEvent(Object vSender, Object obj) {
        if (vSender == rippleLearn) {
            Intent intent = new Intent(this, ActStartLearning.class);
            startActivity(intent);
            return;
        } else if (vSender == rippleMyResult) {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        } else if (vSender == rippleDeveloper) {
            Intent intent = new Intent(this, ActTest.class);
            startActivity(intent);
        }
    }

}
