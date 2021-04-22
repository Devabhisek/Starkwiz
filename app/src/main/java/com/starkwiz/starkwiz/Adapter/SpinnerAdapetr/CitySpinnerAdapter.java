package com.starkwiz.starkwiz.Adapter.SpinnerAdapetr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.starkwiz.starkwiz.ModelClass.District_ModelClass;
import com.starkwiz.starkwiz.ModelClass.District_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;

public class DistrictSpinnerAdapter extends ArrayAdapter<District_ModelClass> {

    private ArrayList<District_ModelClass> myarrayList;

    public DistrictSpinnerAdapter(Context context, int textViewResourceId, ArrayList<District_ModelClass> modelArrayList) {
        super(context, textViewResourceId, modelArrayList);
        this.myarrayList = modelArrayList;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Nullable
    @Override
    public District_ModelClass getItem(int position) {
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
        District_ModelClass model = getItem(position);

        View spinnerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_textview, parent, false);

        TextView label = spinnerRow.findViewById(R.id.spinner_text);
        label.setText(String.format("%s", model != null ? model.getDistrict_name() : ""));
        

        return spinnerRow;
    }
}
