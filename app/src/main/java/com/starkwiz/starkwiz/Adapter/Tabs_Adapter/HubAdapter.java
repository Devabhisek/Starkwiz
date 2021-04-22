package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.DynamoFragments.RewardFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScheduleFragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.ScoreCard_Fragment;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.SubjectFragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.AllHub_Fragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.BookStore_Hub_Fragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.FavouriteHub_Fragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.School_HubFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.HubProfile.Info_HubProfile_Fragment;

public class HubAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public HubAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Info_HubProfile_Fragment info_hubProfile_fragment = new Info_HubProfile_Fragment();
                return info_hubProfile_fragment;
            case 1:
                FavouriteHub_Fragment favouriteHub_fragment = new FavouriteHub_Fragment();
                return favouriteHub_fragment;


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
