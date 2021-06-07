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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetList_Adapter extends RecyclerView.Adapter<GetList_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<GetTestList_ModelClass> listitems;
    private int mCheckedPostion = -1;
    String totalmark;


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

            final Map<String, String> params = new HashMap();

            params.put("module_id", GetTestList_ModelClass.getModule_id());


            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.get_moduleByID, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {



                    try {
                        String Information = response.getString("1");

                        JSONArray array = new JSONArray(Information);

                        for (int i = 0 ; i <array.length() ; i++){

                            JSONObject object = array.getJSONObject(i);

                             totalmark = object.getString("totalmark");



                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();



                }
            });


            Volley.newRequestQueue(context).add(jsonRequest);



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
                        intent.putExtra("selected_subjectid",GetTestList_ModelClass.getSubject_id());
                        intent.putExtra("selected_testid",selected_testid);
                        intent.putExtra("selected_hour",GetTestList_ModelClass.getHour());
                        intent.putExtra("selected_minutes",GetTestList_ModelClass.getMinutes());
                        intent.putExtra("totalmark",totalmark);
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
        RecyclerView lv_submodule;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            chk_getlist = itemView.findViewById(R.id.chk_getlist);
            lv_submodule = itemView.findViewById(R.id.lv_submodule);
        }
    }





}
