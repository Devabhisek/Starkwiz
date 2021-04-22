package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.DynamoFragments.RewardFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScheduleFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScoreCard_Fragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.SubjectFragment;

public class DynamoAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public DynamoAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SubjectFragment subjectFragment = new SubjectFragment();
                return subjectFragment;
            case 1:
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                return scheduleFragment;
//            case 2:
//                ScoreCard_Fragment scoreCard_fragment = new ScoreCard_Fragment();
//                return scoreCard_fragment;
            case 2:
                RewardFragment rewardFragment = new RewardFragment();
                return rewardFragment;


            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
