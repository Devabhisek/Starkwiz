package com.starkwiz.starkwiz.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.DynamoFragments.RewardFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScheduleFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScoreCard_Fragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.SubjectFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.Friends_FollowingFragment;
import com.starkwiz.starkwiz.Fragments.ShowcaseFragment.All_Showcase_Fragment;
import com.starkwiz.starkwiz.Fragments.ShowcaseFragment.Events_Showcase_Fragment;
import com.starkwiz.starkwiz.Fragments.ShowcaseFragment.Following_Showcase_Fragment;
import com.starkwiz.starkwiz.Fragments.ShowcaseFragment.Friends_Showcase_Fragment;

public class ShowcaseAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public ShowcaseAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                All_Showcase_Fragment all_showcase_fragment = new All_Showcase_Fragment();
                return all_showcase_fragment;

            case 1:
                Events_Showcase_Fragment events_showcase_fragment = new Events_Showcase_Fragment();
                return events_showcase_fragment;

            case 2:
                Friends_Showcase_Fragment friends_showcase_fragment = new Friends_Showcase_Fragment();
                return friends_showcase_fragment;

            case 3:
                Following_Showcase_Fragment following_showcase_fragment = new Following_Showcase_Fragment();
                return following_showcase_fragment;


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
