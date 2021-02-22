package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.starkwiz.starkwiz.ModelClass.Core_Subjectbyplans_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Extra_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;

public class ExtraSubjects_Adapter extends RecyclerView.Adapter<ExtraSubjects_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<Extra_Subjectplan_ModelClass> listitems;
    private int mCheckedPostion = -1;


    public ExtraSubjects_Adapter(ArrayList<Extra_Subjectplan_ModelClass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;

    }

    @NonNull
    @Override
    public ExtraSubjects_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_subjectplans, parent, false);
        return new ExtraSubjects_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExtraSubjects_Adapter.ViewHolder holder, final int position) {

        try {
            final Extra_Subjectplan_ModelClass Extra_Subjectplan_ModelClass = listitems.get(position);

            holder.chk_subject.setText(Extra_Subjectplan_ModelClass.getSubject_name());


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

        CheckBox chk_subject;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            chk_subject = itemView.findViewById(R.id.chk_subject);


        }
    }
}

