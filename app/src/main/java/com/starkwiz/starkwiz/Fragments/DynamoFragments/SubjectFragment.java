package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starkwiz.starkwiz.Activities.Dance_Activity;
import com.starkwiz.starkwiz.Activities.Declamation;
import com.starkwiz.starkwiz.Activities.Music_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.R;


public class SubjectFragment extends Fragment {

    CardView card_subject,carddance,card_declamation,card_music;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        card_subject = view.findViewById(R.id.card_subject);
        carddance = view.findViewById(R.id.carddance);
        card_declamation = view.findViewById(R.id.card_declamation);
        card_music = view.findViewById(R.id.card_music);



        card_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Subjectwise_Syllabus_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        carddance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Dance_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        card_declamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Declamation.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        card_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Music_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        return view;
    }
}