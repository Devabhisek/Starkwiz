package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.starkwiz.starkwiz.Fragments.HubFragment.Hub_UserSelection_Fragment;
import com.starkwiz.starkwiz.Fragments.Parent_UserSelection_Fragment;
import com.starkwiz.starkwiz.Fragments.Student_Userselection_Fragment;
import com.starkwiz.starkwiz.Fragments.Teacher_Userselection_Fragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Student_Userselection_Fragment student_userselection_fragment = new Student_Userselection_Fragment();
                return student_userselection_fragment;
            case 1:
                Parent_UserSelection_Fragment parent_userSelection_fragment = new Parent_UserSelection_Fragment();
                return parent_userSelection_fragment;
            case 2:
                Teacher_Userselection_Fragment teacher_userselection_fragment = new Teacher_Userselection_Fragment();
                return teacher_userselection_fragment;
            case 3:
                Hub_UserSelection_Fragment hub_userSelection_fragment = new Hub_UserSelection_Fragment();
                return hub_userSelection_fragment;
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
