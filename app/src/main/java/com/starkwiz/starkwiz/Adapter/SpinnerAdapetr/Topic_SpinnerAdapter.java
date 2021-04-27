package com.starkwiz.starkwiz.Adapter.SpinnerAdapetr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.starkwiz.starkwiz.ModelClass.Topics_Modelclass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;

public class Topic_SpinnerAdapter extends ArrayAdapter<Topics_Modelclass> {

    private ArrayList<Topics_Modelclass> myarrayList;

    public Topic_SpinnerAdapter(Context context, int textViewResourceId, ArrayList<Topics_Modelclass> modelArrayList) {
        super(context, textViewResourceId, modelArrayList);
        this.myarrayList = modelArrayList;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }



    @Nullable
    @Override
    public Topics_Modelclass getItem(int position) {
        return myarrayList.get(position);
    }

    @Override
    public int getCount() {
        int count = myarrayList.size();
        //return count > 0 ? count - 1 : count;
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        Topics_Modelclass model = getItem(position);

        View spinnerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_topic_spinner, parent, false);

        TextView txt_spinner = spinnerRow.findViewById(R.id.txt_spinner);
        txt_spinner.setText(String.format("%s", model != null ? model.getTopic() : ""));

        return spinnerRow;
    }
}

