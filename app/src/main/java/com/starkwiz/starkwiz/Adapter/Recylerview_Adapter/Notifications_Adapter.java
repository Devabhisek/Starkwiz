package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

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
import com.starkwiz.starkwiz.ModelClass.Core_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Notification_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Notification_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;
import java.util.HashSet;

import tourguide.tourguide.TourGuide;

import static android.content.Context.MODE_PRIVATE;

public class Notifications_Adapter extends RecyclerView.Adapter<Notifications_Adapter.ViewHolder> {

    public Context context;
    private ArrayList<Notification_ModelClass> listitems;



    public Notifications_Adapter(ArrayList<Notification_ModelClass> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public Notifications_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notification, parent, false);
        return new Notifications_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Notifications_Adapter.ViewHolder holder, final int position) {

        try {

            final Notification_ModelClass Notification_ModelClass = listitems.get(position);

            holder.txt_notifaction.setText(Notification_ModelClass.getNotification_text());


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

        TextView txt_notifaction;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt_notifaction = itemView.findViewById(R.id.txt_notifaction);


        }
    }

}

