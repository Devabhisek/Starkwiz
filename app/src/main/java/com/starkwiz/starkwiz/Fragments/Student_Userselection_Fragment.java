package com.starkwiz.starkwiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starkwiz.starkwiz.R;


public class Student_Userselection_Fragment extends Fragment {

    public Student_Userselection_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student__userselection_, container, false);
        return view;
    }
}