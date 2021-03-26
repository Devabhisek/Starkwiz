package com.starkwiz.starkwiz.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.starkwiz.starkwiz.ModelClass.TermCondition_ModelClass;
import com.starkwiz.starkwiz.ModelClass.TermCondition_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;
import java.util.HashSet;

import tourguide.tourguide.TourGuide;

import static android.content.Context.MODE_PRIVATE;

public class TermCondition_Adapter extends RecyclerView.Adapter<TermCondition_Adapter.ViewHolder> {

    public Context context;
    private ArrayList<TermCondition_ModelClass> listitems;
    
    public TermCondition_Adapter(ArrayList<TermCondition_ModelClass> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;

    }

    @NonNull
    @Override
    public TermCondition_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_termcondition, parent, false);
        return new TermCondition_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final TermCondition_Adapter.ViewHolder holder, final int position) {

        try {

            final TermCondition_ModelClass TermCondition_ModelClass = listitems.get(position);

            holder.txt_termcondition.setText((position+1)+". "+TermCondition_ModelClass.getTerms_and_conditions()+".");


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_termcondition;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt_termcondition = itemView.findViewById(R.id.txt_termcondition);


        }
    }


}

