package com.starkwiz.starkwiz.Fragments.ShowcaseFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.starkwiz.starkwiz.Adapter.Tabs_Adapter.ShowcaseAdapter;
import com.starkwiz.starkwiz.R;


public class ShowCaseFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_case, container, false);
        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ShowcaseAdapter adapter = new ShowcaseAdapter(getActivity(),getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}