package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;

public class GetList_Adapter extends RecyclerView.Adapter<GetList_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<GetTestList_ModelClass> listitems;
    private int mCheckedPostion = -1;


    public GetList_Adapter(ArrayList<GetTestList_ModelClass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_gettestlist, parent, false);
        return new GetList_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        try {
            final GetTestList_ModelClass GetTestList_ModelClass = listitems.get(position);

            holder.chk_getlist.setText(GetTestList_ModelClass.getModule_name());

            holder.chk_getlist.setChecked(position == mCheckedPostion);

            holder.chk_getlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == mCheckedPostion) {
                        holder.chk_getlist.setChecked(false);
                        mCheckedPostion = -1;
                    } else {
                        mCheckedPostion = position;
                        String selected_module = GetTestList_ModelClass.getModule_name();
                        String selected_testid = GetTestList_ModelClass.getModule_id();
                        Intent intent = new Intent("custom-message");
                        intent.putExtra("selected_module",selected_module);
                        intent.putExtra("selected_testid",selected_testid);
                        intent.putExtra("selected_hour",GetTestList_ModelClass.getHour());
                        intent.putExtra("selected_minutes",GetTestList_ModelClass.getMinutes());
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        notifyDataSetChanged();
                    }
                }
            });

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

        CheckBox chk_getlist;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            chk_getlist = itemView.findViewById(R.id.chk_getlist);



        }
    }





}
