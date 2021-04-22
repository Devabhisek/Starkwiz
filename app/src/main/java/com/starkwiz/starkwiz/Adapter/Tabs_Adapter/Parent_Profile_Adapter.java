package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.ProfileFragments.ParentProfile.Friends_Parent_Fragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.ParentProfile.Info_Parent_Fragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.AchievementFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.FriendsFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.InfoFragment;

public class Parent_Profile_Adapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public Parent_Profile_Adapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Info_Parent_Fragment info_parent_fragment = new Info_Parent_Fragment();
                return info_parent_fragment;
            case 1:
                Friends_Parent_Fragment friends_parent_fragment = new Friends_Parent_Fragment();
                return friends_parent_fragment;


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
