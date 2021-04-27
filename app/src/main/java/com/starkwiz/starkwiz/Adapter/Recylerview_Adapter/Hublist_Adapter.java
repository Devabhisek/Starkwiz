package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.starkwiz.starkwiz.ModelClass.Hublist_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Hublist_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;

public class Hublist_Adapter extends RecyclerView.Adapter<Hublist_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<Hublist_ModelClass> listitems;
    private int mCheckedPostion = -1;
    String totalmark;



    public Hublist_Adapter(ArrayList<Hublist_ModelClass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hublist, parent, false);
        return new Hublist_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        try {
            final Hublist_ModelClass Hublist_ModelClass = listitems.get(position);

            holder.txt_hublist.setText(Hublist_ModelClass.getHub_type());
            holder.txt_hublist.setChecked(position == mCheckedPostion);

            holder.txt_hublist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == mCheckedPostion) {
                        holder.txt_hublist.setChecked(false);
                        mCheckedPostion = -1;
                    } else {
                        mCheckedPostion = position;

                        //Toast.makeText(context, Hublist_ModelClass.getHub_type(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("custom-message");
                            intent.putExtra("selected_hubtype",Hublist_ModelClass.getHub_type());
//
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                       notifyDataSetChanged();
                       // holder.txt_hublist.setBackgroundColor(context.getResources().getColor(R.color.theme_blue));
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

        CheckBox txt_hublist;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt_hublist = itemView.findViewById(R.id.txt_hublist);

        }
    }





}
