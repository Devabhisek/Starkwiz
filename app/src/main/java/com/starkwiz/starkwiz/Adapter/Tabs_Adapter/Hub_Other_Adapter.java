package com.starkwiz.starkwiz.Adapter.Tabs_Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.starkwiz.starkwiz.Fragments.HubFragment.AllHub_Fragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.BookStore_Hub_Fragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.FavouriteHub_Fragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.School_HubFragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.HubProfile.Info_HubProfile_Fragment;

public class Hub_Other_Adapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public Hub_Other_Adapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllHub_Fragment allHub_fragment = new AllHub_Fragment();
                return allHub_fragment;

            case 1:
                FavouriteHub_Fragment favouriteHub_fragment = new FavouriteHub_Fragment();
                return favouriteHub_fragment;

            case 2:
                School_HubFragment school_hubFragment = new School_HubFragment();
                return school_hubFragment;

             case 3:
                BookStore_Hub_Fragment bookStore_hub_fragment = new BookStore_Hub_Fragment();
                return bookStore_hub_fragment;


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
