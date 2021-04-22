package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Topics_Modelclass;
import com.starkwiz.starkwiz.ModelClass.Topics_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Topic_Adapter extends RecyclerView.Adapter<Topic_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<Topics_Modelclass> listitems;
    private int mCheckedPostion = -1;
    String totalmark;


    public Topic_Adapter(ArrayList<Topics_Modelclass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_topic, parent, false);
        return new Topic_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        try {
            final Topics_Modelclass Topics_Modelclass = listitems.get(position);

            holder.txt_topics.setText(String.valueOf(position+1)+". "+Topics_Modelclass.getTopic());

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

        TextView txt_topics;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt_topics = itemView.findViewById(R.id.txt_topics);

        }
    }





}
