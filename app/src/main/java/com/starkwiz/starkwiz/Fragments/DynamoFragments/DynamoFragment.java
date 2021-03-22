package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.starkwiz.starkwiz.Adapter.DynamoAdapter;
import com.starkwiz.starkwiz.Adapter.ProfileAdapter;
import com.starkwiz.starkwiz.R;


public class DynamoFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnselectsubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamo, container, false);

        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        btnselectsubject = view.findViewById(R.id.btnselectsubject);

        tabLayout.addTab(tabLayout.newTab().setText("Subjects"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        //tabLayout.addTab(tabLayout.newTab().setText("Score Cards"));
        tabLayout.addTab(tabLayout.newTab().setText("Rewards"));
        
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        btnselectsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        final DynamoAdapter adapter = new DynamoAdapter(getActivity(),getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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