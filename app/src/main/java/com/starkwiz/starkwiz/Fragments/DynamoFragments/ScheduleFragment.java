package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starkwiz.starkwiz.Activities.Subject_Schedule_Detail_Activity;
import com.starkwiz.starkwiz.R;


public class ScheduleFragment extends Fragment {

    CardView cardview_math;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        cardview_math = view.findViewById(R.id.cardview_math);

        cardview_math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Subject_Schedule_Detail_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        return view;
    }
}