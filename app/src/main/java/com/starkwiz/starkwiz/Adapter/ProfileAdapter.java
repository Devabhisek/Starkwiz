package com.starkwiz.starkwiz.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.AchievementFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.FriendsFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.InfoFragment;

public class ProfileAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public ProfileAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                InfoFragment infoFragment = new InfoFragment();
                return infoFragment;
            case 1:
                AchievementFragment achievementFragment = new AchievementFragment();
                return achievementFragment;
            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;

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
