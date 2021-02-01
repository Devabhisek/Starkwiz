package com.starkwiz.starkwiz.Fragments.ProfileFragments.ParentProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starkwiz.starkwiz.R;


public class Info_Parent_Fragment extends Fragment {

    LinearLayout linear_personalinfo,linear_editpersonalinfo;
    TextView txt_editgeneralinfo,txt_generalinfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info__parent_, container, false);

        linear_personalinfo = view.findViewById(R.id.linear_personalinfo);
        linear_editpersonalinfo = view.findViewById(R.id.linear_editpersonalinfo);
        txt_editgeneralinfo = view.findViewById(R.id.txt_editgeneralinfo);
        txt_generalinfo = view.findViewById(R.id.txt_generalinfo);

        txt_generalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_editgeneralinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_editpersonalinfo.setVisibility(View.GONE);
                linear_personalinfo.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}