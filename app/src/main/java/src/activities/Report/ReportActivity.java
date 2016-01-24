package src.activities.Report;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import cdmst.smartsilver.R;

/**
 * Created by waps12b on 16. 1. 24..
 */

public class ReportActivity extends FragmentActivity {

    @Bind(R.id.pager)
    ViewPager viewPager;

    @Bind(R.id.pager_stip)
    PagerTabStrip pagerStrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        viewPager.setAdapter(new CustomPagerAdapter(this.getSupportFragmentManager()));

    }





    public class CustomPagerAdapter extends FragmentStatePagerAdapter {

        public CustomPagerAdapter(android.support.v4.app.FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position)
            {
                case 0:
                    fragment = new LearningTimeFragment();
                    break;
                case 1:
                    fragment = new LearningProgressFragment();
                    break;
                case 2:
                    fragment = new LearningResultFragment();
                    break;
                default:
                    return null;
            }
            fragment.setArguments(new Bundle());
            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }


}
