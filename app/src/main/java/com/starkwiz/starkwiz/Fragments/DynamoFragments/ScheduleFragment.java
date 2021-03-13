package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.HubListsActivity;
import com.starkwiz.starkwiz.Activities.Signup_Parent_AboutYou_Activity;
import com.starkwiz.starkwiz.Activities.Signup_Personal_Activity;
import com.starkwiz.starkwiz.Activities.Signup_teaching_Activity;
import com.starkwiz.starkwiz.Activities.Sigup_Studying_Activity;
import com.starkwiz.starkwiz.Activities.Subject_Schedule_Detail_Activity;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.ScheduledList_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Scheduled_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class        ScheduleFragment extends Fragment {

    //CardView cardview_math;
    String Userid;
    ArrayList<Scheduled_ModelClass>list_schedule;
    RecyclerView lv_schedule;
    TextView txt_fixturemonth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);


        //cardview_math = view.findViewById(R.id.cardview_math);
        lv_schedule = view.findViewById(R.id.lv_schedule);
        txt_fixturemonth = view.findViewById(R.id.txt_fixturemonth);
        list_schedule = new ArrayList<>();
        lv_schedule.setHasFixedSize(true);
        lv_schedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        Userid = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month = month_date.format(cal.getTime());

        txt_fixturemonth.setText("FIXTURE: "+month);


        return view;
    }

    public void GetSchedule(){

        list_schedule.clear();
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("user_id", Userid);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.getfixture, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String Information = response.getString("Information");

                    JSONArray array = new JSONArray(Information);

                    for (int i = 0 ; i <array.length() ; i++){

                        JSONObject object = array.getJSONObject(i);

                        Scheduled_ModelClass modelClass = new Scheduled_ModelClass(

                                object.getString("id"),
                                object.getString("test_id"),
                                object.getString("subject_id"),
                                object.getString("module_id"),
                                object.getString("date"),
                                object.getString("time"),
                                object.getString("month"),
                                object.getString("time_type"),
                                ""
                        );

                        list_schedule.add(modelClass);

                    }

                    ScheduledList_Adapter adapter = new ScheduledList_Adapter(list_schedule,getActivity());
                    lv_schedule.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    lv_schedule.setVisibility(View.GONE);

                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();


            }
        });


        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    @Override
    public void onStart() {
        super.onStart();
        GetSchedule();
    }
}