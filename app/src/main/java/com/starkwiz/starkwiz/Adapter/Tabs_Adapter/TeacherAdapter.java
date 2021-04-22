package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.AchievementFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.FriendsFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.InfoFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.TeacherProfile.Friends_Teacher_Fragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.TeacherProfile.Info_Teacher_Fragment;

public class TeacherAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public TeacherAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Info_Teacher_Fragment info_teacher_fragment = new Info_Teacher_Fragment();
                return info_teacher_fragment;
            case 1:
                Friends_Teacher_Fragment friends_teacher_fragment = new Friends_Teacher_Fragment();
                return friends_teacher_fragment;


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
