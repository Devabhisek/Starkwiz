package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.DynamoFragments.RewardFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScheduleFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScoreCard_Fragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.SubjectFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.All_FollowingFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.Friends_FollowingFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.Hub_FollowingFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.ParentsFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.TeachersFragment;

public class FollowingAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public FollowingAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                All_FollowingFragment all_followingFragment = new All_FollowingFragment();
                return all_followingFragment;
            case 1:
                Friends_FollowingFragment friends_followingFragment = new Friends_FollowingFragment();
                return friends_followingFragment;
            case 2:
                ParentsFragment parentsFragment = new ParentsFragment();
                return parentsFragment;

             case 3:
                TeachersFragment teachersFragment = new TeachersFragment();
                return teachersFragment;
            case 4:
                Hub_FollowingFragment hub_followingFragment = new Hub_FollowingFragment();
                return hub_followingFragment;


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
