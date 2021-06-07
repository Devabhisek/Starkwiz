package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.starkwiz.starkwiz.Activities.Payment_Activity;
import com.starkwiz.starkwiz.Adapter.GridAdapter.Getsubject_GridViewAdapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.CoreSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.ExtraSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.FeaturedSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Tabs_Adapter.DynamoAdapter;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Core_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Core_Subjectbyplans_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Extra_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Featured_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.ModelClass.GetSubjects_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Selected_Subject_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DynamoFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnselectsubject;
    LinearLayout lineartype,basic,linear_basictype,standard,linear_standardtype,premium,lineardisciunt,
            linear_premiumtype,core,extra,feature,linear_coretype,linear_extratype,linear_featuretype;
    TextView txtplantype,txtplanprice,txtplanvalidity,txt_perprice,txt_disciuntprice,txt_disciuntpricemonth,
            txtplantype_subject,txtplanprice_subject,txtplanvalidity_subject,txt_perprice_subject,txt_fixturemonth,txt_studenttype;
    String PlanType,PlanPrice,PlanPerMonth,PlanDuration,PlanDuarationType,discounted_price,Cls,User_Id,testCount,Amount,json;
    Button btn_plans,btn_subjectproceed;
    int discount_month;
    RecyclerView lv_subjectsplan,lv_subjectsplan_extra,lv_subjectsplan_feature;
    ImageView img_cross_subjects;
    ArrayList<Core_Subjectbyplans_ModelClass>list_coresubjects;
    ArrayList<Extra_Subjectplan_ModelClass>list_extrasubjects;
    ArrayList<Featured_Subjectplan_ModelClass>list_featuresubjects;
    ArrayList<GetSubjects_ModelClass> totalSubjects_modelClasses = new ArrayList<>();
    ArrayList<Selected_Subject_Modelclass>list_subjects;
    CoreSubjects_Adapter adapter;
    ExtraSubjects_Adapter extraSubjects_adapter;
    FeaturedSubjects_Adapter featuredSubjects_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamo, container, false);

        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        btnselectsubject = view.findViewById(R.id.btnselectsubject);

        tabLayout.addTab(tabLayout.newTab().setText("Subjects"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        //tabLayout.addTab(tabLayout.newTab().setText("Score Cards"));
        tabLayout.addTab(tabLayout.newTab().setText("Rewards"));
        
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        list_coresubjects =new ArrayList<>();
        list_extrasubjects =new ArrayList<>();
        list_featuresubjects =new ArrayList<>();
        list_subjects =new ArrayList<>();
        adapter = new CoreSubjects_Adapter(list_coresubjects,getActivity());
        extraSubjects_adapter = new ExtraSubjects_Adapter(list_extrasubjects,getActivity());
        featuredSubjects_adapter = new FeaturedSubjects_Adapter(list_featuresubjects,getActivity());

        Cls = SharedPrefManager.getInstance(getActivity()).getUser().getCls();

        User_Id = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        CheckFirstRegister(User_Id);

        btnselectsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list_coresubjects.clear();
                list_featuresubjects.clear();
                list_extrasubjects.clear();
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.activity_plans);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ImageView img_cross = dialog.findViewById(R.id.img_cross);
                lineartype = dialog.findViewById(R.id.lineartype);
                basic = dialog.findViewById(R.id.basic);
                linear_basictype = dialog.findViewById(R.id.linear_basictype);
                standard = dialog.findViewById(R.id.standard);
                linear_standardtype = dialog.findViewById(R.id.linear_standardtype);
                lineardisciunt = dialog.findViewById(R.id.lineardisciunt);
                premium = dialog.findViewById(R.id.premium);
                txt_disciuntprice = dialog.findViewById(R.id.txt_disciuntprice);
                txt_disciuntpricemonth = dialog.findViewById(R.id.txt_disciuntpricemonth);
                linear_premiumtype = dialog.findViewById(R.id.linear_premiumtype);
                txtplantype = dialog.findViewById(R.id.txtplantype);
                txtplanprice = dialog.findViewById(R.id.txtplanprice);
                txtplanvalidity = dialog.findViewById(R.id.txtplanvalidity);
                txt_perprice = dialog.findViewById(R.id.txt_perprice);
                btn_plans = dialog.findViewById(R.id.btn_plans);
                txt_studenttype = dialog.findViewById(R.id.txt_studenttype);
                btn_plans.setEnabled(false);

                img_cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                if (testCount.equals("0")){
                    txt_studenttype.setText("Your first Dynamo is free.");
                }else {
                    txt_studenttype.setText("Choose a suitable plan. \n Welcome to Starkwiz !");
                }

                LayoutInflater inflater = LayoutInflater
                        .from(getActivity());
                View layview = inflater.inflate(R.layout.custom_basic_plans, null);
                lineartype.addView(layview);

                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                //HttpsTrustManager.allowAllSSL();

                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.GetPlan, null,
                        new Response.Listener<JSONArray>()
                        {
                            @Override
                            public void onResponse(JSONArray response) {
                                progressDialog.dismiss();

                                // display response
                                for (int j=0 ; j<response.length() ; j++){

                                    try {
                                        JSONObject object = response.getJSONObject(j);

                                        if (object.getString("plan_type").equals("Basic") || object.getString("plan_type").equals("basic")){
                                            txt_perprice.setText("Rs "+object.getString("plan_price_month")+" / per month");
                                            txtplanprice.setText(object.getString("plan_price"));
                                            txtplantype.setText(object.getString("plan_type"));
                                            txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                            PlanType = "Basic";
                                            PlanPrice = object.getString("plan_price");
                                            PlanPerMonth = object.getString("plan_price_month");
                                            PlanDuration = object.getString("plan_duration");
                                            PlanDuarationType = object.getString("plan_duration_type");

                                            discounted_price = object.getString("discounted_price");

                                            Intent intent = new Intent("custom-message");
                                            intent.putExtra("planid",object.getString("plan_id"));
                                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

                                            if (!discounted_price.equals("0")){

                                                lineardisciunt.setVisibility(View.VISIBLE);
                                                txtplanprice.setPaintFlags(txtplanprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                                txt_disciuntprice.setText(discounted_price);

                                                discount_month = Integer.parseInt(discounted_price)/12;

                                                txt_disciuntpricemonth.setText(String.valueOf(discount_month));
                                            }
                                            else {
                                                lineardisciunt.setVisibility(View.GONE);
                                            }


                                            btn_plans.setEnabled(true);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Log.d("Error.Response", error.toString());
                            }
                        }
                );

                getRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(getActivity()).add(getRequest);

                basic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lineartype.removeAllViews();
                        linear_basictype.setVisibility(View.VISIBLE);
                        linear_standardtype.setVisibility(View.GONE);
                        linear_premiumtype.setVisibility(View.GONE);

                        btn_plans.setEnabled(false);

                        PlanType = "Basic";

                        Log.d("plantype",PlanType);

                        LayoutInflater inflater = LayoutInflater
                                .from(getActivity());
                        View layview = inflater.inflate(R.layout.custom_basic_plans, null);
                        lineartype.addView(layview);

                        ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        //HttpsTrustManager.allowAllSSL();

                        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.GetPlan, null,
                                new Response.Listener<JSONArray>()
                                {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        progressDialog.dismiss();
                                        // display response
                                        for (int j=0 ; j<response.length() ; j++){

                                            try {
                                                JSONObject object = response.getJSONObject(j);

                                                if (object.getString("plan_type").equals("Basic") || object.getString("plan_type").equals("basic")){
                                                    txt_perprice.setText("Rs "+object.getString("plan_price_month")+"/ per month");
                                                    txtplanprice.setText(object.getString("plan_price"));
                                                    txtplantype.setText(object.getString("plan_type"));
                                                    txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                                    PlanPrice = object.getString("plan_price");
                                                    PlanPerMonth = object.getString("plan_duration");

                                                    discounted_price = object.getString("discounted_price");

                                                    if (!discounted_price.equals("0")){

                                                        lineardisciunt.setVisibility(View.VISIBLE);
                                                        txtplanprice.setPaintFlags(txtplanprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                                        txt_disciuntprice.setText(discounted_price);

                                                        discount_month = Integer.parseInt(discounted_price)/12;

                                                        txt_disciuntpricemonth.setText(String.valueOf(discount_month));
                                                    }
                                                    else {
                                                        lineardisciunt.setVisibility(View.GONE);
                                                    }

                                                    btn_plans.setEnabled(true);
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Log.d("Error.Response", error.toString());
                                    }
                                }
                        );

                        getRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        Volley.newRequestQueue(getActivity()).add(getRequest);


                    }
                });

                standard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lineartype.removeAllViews();
                        linear_basictype.setVisibility(View.GONE);
                        linear_standardtype.setVisibility(View.VISIBLE);
                        linear_premiumtype.setVisibility(View.GONE);

                        btn_plans.setEnabled(false);

                        PlanType = "Standard";
                        Log.d("plantype",PlanType);

                        txt_perprice.setText("");
                        txtplanprice.setText("");
                        txtplantype.setText("");
                        txtplanvalidity.setText("");

                        LayoutInflater inflater = LayoutInflater
                                .from(getActivity());
                        View layview = inflater.inflate(R.layout.custom_standard_plans, null);
                        lineartype.addView(layview);

                        ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        //HttpsTrustManager.allowAllSSL();

                        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.GetPlan, null,
                                new Response.Listener<JSONArray>()
                                {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        progressDialog.dismiss();
                                        // display response
                                        for (int j=0 ; j<response.length() ; j++){

                                            try {
                                                JSONObject object = response.getJSONObject(j);

                                                if (object.getString("plan_type").equals("Standard") || object.getString("plan_type").equals("standard")){
                                                    txt_perprice.setText("Rs "+object.getString("plan_price_month")+" / per month");
                                                    txtplanprice.setText(object.getString("plan_price"));
                                                    txtplantype.setText(object.getString("plan_type"));
                                                    txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                                    PlanPrice = object.getString("plan_price");
                                                    PlanPerMonth = object.getString("plan_duration");

                                                    discounted_price = object.getString("discounted_price");

                                                    if (!discounted_price.equals("0")){

                                                        lineardisciunt.setVisibility(View.VISIBLE);

                                                        txtplanprice.setPaintFlags(txtplanprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                                                        txt_disciuntprice.setText(discounted_price);

                                                        discount_month = Integer.parseInt(discounted_price)/12;

                                                        txt_disciuntpricemonth.setText(String.valueOf(discount_month));
                                                    }
                                                    else {
                                                        lineardisciunt.setVisibility(View.GONE);
                                                    }


                                                    btn_plans.setEnabled(true);
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Log.d("Error.Response", error.toString());
                                    }
                                }
                        );

                        getRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        Volley.newRequestQueue(getActivity()).add(getRequest);



                    }
                });

                premium.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lineartype.removeAllViews();
                        linear_basictype.setVisibility(View.GONE);
                        linear_standardtype.setVisibility(View.GONE);
                        linear_premiumtype.setVisibility(View.VISIBLE);
                        btn_plans.setEnabled(false);

                        PlanType = "Premium";
                        Log.d("plantype",PlanType);

                        LayoutInflater inflater = LayoutInflater
                                .from(getActivity());
                        View layview = inflater.inflate(R.layout.custom_premium_plans, null);
                        lineartype.addView(layview);

                        ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        //HttpsTrustManager.allowAllSSL();

                        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.GetPlan, null,
                                new Response.Listener<JSONArray>()
                                {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        progressDialog.dismiss();
                                        // display response
                                        for (int j=0 ; j<response.length() ; j++){

                                            try {
                                                JSONObject object = response.getJSONObject(j);

                                                if (object.getString("plan_type").equals("Premium") || object.getString("plan_type").equals("premium")){
                                                    txt_perprice.setText("Rs "+object.getString("plan_price_month")+"/ per month");
                                                    txtplanprice.setText(object.getString("plan_price"));
                                                    txtplantype.setText(object.getString("plan_type"));
                                                    txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                                    PlanPrice = object.getString("plan_price");
                                                    PlanPerMonth = object.getString("plan_duration");

                                                    discounted_price = object.getString("discounted_price");

                                                    if (!discounted_price.equals("0")){

                                                        lineardisciunt.setVisibility(View.VISIBLE);

                                                        txtplanprice.setPaintFlags(txtplanprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                                        txt_disciuntprice.setText(discounted_price);

                                                        discount_month = Integer.parseInt(discounted_price)/12;

                                                        txt_disciuntpricemonth.setText(String.valueOf(discount_month));
                                                    }
                                                    else {
                                                        lineardisciunt.setVisibility(View.GONE);
                                                    }


                                                    btn_plans.setEnabled(true);
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Log.d("Error.Response", error.toString());
                                    }
                                }
                        );

                        getRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        Volley.newRequestQueue(getActivity()).add(getRequest);
                    }
                });


                btn_plans.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.alert_subjectplan);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        TextView txt_disciuntprice,txt_disciuntpricemonth,txt_studenttype;

                        txtplantype_subject = dialog.findViewById(R.id.txtplantype);
                        txtplanprice_subject = dialog.findViewById(R.id.txtplanprice);
                        txtplanvalidity_subject = dialog.findViewById(R.id.txtplanvalidity);
                        txt_perprice_subject = dialog.findViewById(R.id.txt_perprice);
                        txt_disciuntprice = dialog.findViewById(R.id.txt_disciuntprice);
                        txt_disciuntpricemonth = dialog.findViewById(R.id.txt_disciuntpricemonth);
                        lv_subjectsplan = dialog.findViewById(R.id.lv_subjectsplan);
                        lv_subjectsplan_extra = dialog.findViewById(R.id.lv_subjectsplan_extra);
                        lv_subjectsplan_feature = dialog.findViewById(R.id.lv_subjectsplan_feature);
                        linear_extratype = dialog.findViewById(R.id.linear_extratype);
                        btn_subjectproceed = dialog.findViewById(R.id.btn_subjectproceed);
                        core = dialog.findViewById(R.id.core);
                        extra = dialog.findViewById(R.id.extra);
                        feature = dialog.findViewById(R.id.feature);
                        linear_coretype = dialog.findViewById(R.id.linear_coretype);
                        linear_featuretype = dialog.findViewById(R.id.linear_featuretype);
                        img_cross_subjects = dialog.findViewById(R.id.img_cross_subjects);
                        txt_studenttype = dialog.findViewById(R.id.txt_studenttype);

                        if (testCount.equals("0")){
                            btn_subjectproceed.setText("Free");
                        }

                        if (PlanType.equals("Basic")){

                            txt_studenttype.setText("Choose any 6 subjects of your choice \nTry to select minimum 1 form each section \n Starkwiz !");
                        }else if (PlanType.equals("Standard")){
                            txt_studenttype.setText("Choose any 9 subjects of your choice \nTry to select minimum 1 form each section \n Starkwiz !");
                        }else {
                            txt_studenttype.setText("Choose any 12 subjects of your choice \nTry to select minimum 1 form each section \n Starkwiz !");
                        }



                        if (!discounted_price.equals("0")){

                            lineardisciunt.setVisibility(View.VISIBLE);

                            txtplanprice.setPaintFlags(txtplanprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            txt_disciuntprice.setText(discounted_price);

                            Amount=discounted_price;

                            discount_month = Integer.parseInt(discounted_price)/12;

                            txt_disciuntpricemonth.setText(String.valueOf(discount_month));
                        }
                        else {
                            lineardisciunt.setVisibility(View.GONE);
                        }

                        img_cross_subjects.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        core.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lv_subjectsplan.setVisibility(View.VISIBLE);
                                lv_subjectsplan_extra.setVisibility(View.GONE);
                                lv_subjectsplan_feature.setVisibility(View.GONE);

                                linear_coretype.setVisibility(View.VISIBLE);
                                linear_extratype.setVisibility(View.GONE);
                                linear_featuretype.setVisibility(View.GONE);
                            }
                        });

                        extra.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lv_subjectsplan.setVisibility(View.GONE);
                                lv_subjectsplan_extra.setVisibility(View.VISIBLE);
                                lv_subjectsplan_feature.setVisibility(View.GONE);

                                linear_coretype.setVisibility(View.GONE);
                                linear_extratype.setVisibility(View.VISIBLE);
                                linear_featuretype.setVisibility(View.GONE);
                            }
                        });

                        feature.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lv_subjectsplan.setVisibility(View.GONE);
                                lv_subjectsplan_extra.setVisibility(View.GONE);
                                lv_subjectsplan_feature.setVisibility(View.VISIBLE);

                                linear_coretype.setVisibility(View.GONE);
                                linear_extratype.setVisibility(View.GONE);
                                linear_featuretype.setVisibility(View.VISIBLE);
                            }
                        });

                        lv_subjectsplan.setHasFixedSize(true);
                        lv_subjectsplan.setLayoutManager(new LinearLayoutManager(getActivity()));
                        lv_subjectsplan_extra.setHasFixedSize(true);
                        lv_subjectsplan_extra.setLayoutManager(new LinearLayoutManager(getActivity()));
                        lv_subjectsplan_feature.setHasFixedSize(true);
                        lv_subjectsplan_feature.setLayoutManager(new LinearLayoutManager(getActivity()));

                        if (String.valueOf(discounted_price)!=null){
                            txtplanprice_subject.setText(String.valueOf(discounted_price));
                        }else {
                            txtplanprice_subject.setText(PlanPrice);
                        }

                        txtplantype_subject.setText(PlanType);
                        txtplanvalidity_subject.setText(PlanDuration+" "+PlanDuarationType);
                        txt_perprice_subject.setText("Rs. "+PlanPerMonth+" / per month");
                        //txtplanprice_subject.setText(PlanPrice);



                        list_coresubjects.clear();
                        list_extrasubjects.clear();
                        list_featuresubjects.clear();

                        ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        //HttpsTrustManager.allowAllSSL();



                        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                "https://www.rentopool.com/starkwiz/api/auth/subjectbyplan?class=" + Cls, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    progressDialog.dismiss();
                                    JSONArray subject_array = new JSONArray(response);
                                    for (int k=0;k<subject_array.length();k++){
                                        JSONObject subject_object = subject_array.getJSONObject(k);

                                        String plan = subject_object.getString("subject_type");

                                        if (plan.equals("core")){

                                            Core_Subjectbyplans_ModelClass core_subjectbyplans_modelClass = new Core_Subjectbyplans_ModelClass(


                                                    subject_object.getString("subject_id"),
                                                    "",
                                                    subject_object.getString("subject_name"),
                                                    subject_object.getString("subject_type"),
                                                    ""
                                            );

                                            list_coresubjects.add(core_subjectbyplans_modelClass);

                                            lv_subjectsplan.setAdapter(adapter);
                                            btn_subjectproceed.setEnabled(true);

                                        }else if (plan.equals("extra")){

                                            Extra_Subjectplan_ModelClass extra_subjectplan_modelClass = new Extra_Subjectplan_ModelClass(

                                                    subject_object.getString("subject_id"),
                                                    "",
                                                    subject_object.getString("subject_name"),
                                                    subject_object.getString("subject_type"),
                                                    ""

                                            );
                                            list_extrasubjects.add(extra_subjectplan_modelClass);

                                            lv_subjectsplan_extra.setAdapter(extraSubjects_adapter);
                                            btn_subjectproceed.setEnabled(true);

                                        }else {
                                            Featured_Subjectplan_ModelClass featured_subjectplan_modelClass = new Featured_Subjectplan_ModelClass(

                                                    subject_object.getString("subject_id"),
                                                    "",
                                                    subject_object.getString("subject_name"),
                                                    subject_object.getString("subject_type"),
                                                    ""

                                            );
                                            list_featuresubjects.add(featured_subjectplan_modelClass);

                                            lv_subjectsplan_feature.setAdapter(featuredSubjects_adapter);
                                            btn_subjectproceed.setEnabled(true);
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);


                        btn_subjectproceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                ArrayList<Core_ModelClass> CoreSubject = adapter.getArrayList_Core();
                                ArrayList<Core_ModelClass> ExtraSubject = extraSubjects_adapter.getExtraArrayList();
                                ArrayList<Core_ModelClass> FeatureSubject = featuredSubjects_adapter.getFeatureArrayList();


                                int total = CoreSubject.size()+ExtraSubject.size()+FeatureSubject.size();

                                Log.d("total",CoreSubject.toString());



                                if (PlanType.equals("Basic") && total==6){

                                    Log.d("coresub", String.valueOf(CoreSubject));
                                    Log.d("extrasub", String.valueOf(ExtraSubject));
                                    Log.d("featuresub", String.valueOf(FeatureSubject));

                                    for ( int k = 0 ; k<CoreSubject.size();k++){

                                        String CoreSub_Id = CoreSubject.get(k).getSubjectId();
                                        String CoreSub_name = CoreSubject.get(k).getSubjectname();
                                        String CoreSub_type = CoreSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                CoreSub_Id,
                                                CoreSub_name,
                                                CoreSub_type,
                                                "1",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Core_selectedvalue",list_subjects.toString());


                                    for ( int k = 0 ; k<FeatureSubject.size();k++){

                                        String FeatureSub_Id = FeatureSubject.get(k).getSubjectId();
                                        String FeatureSub_name = FeatureSubject.get(k).getSubjectname();
                                        String FeatureSub_type = FeatureSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                FeatureSub_Id,
                                                FeatureSub_name,
                                                FeatureSub_type,
                                                "3",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Featured_selectedvalue",list_subjects.toString());

                                    for ( int k = 0 ; k<ExtraSubject.size();k++){

                                        String ExtraSub_Id = ExtraSubject.get(k).getSubjectId();
                                        String ExtraSub_name = ExtraSubject.get(k).getSubjectname();
                                        String ExtraSub_type = ExtraSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                ExtraSub_Id,
                                                ExtraSub_name,
                                                ExtraSub_type,
                                                "2",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Extra_selectedvalue",list_subjects.toString());

                                    Gson gson = new Gson();
                                    json = gson.toJson(list_subjects);
                                    Log.d("extra_js",json);

                                    if (testCount.equals("0")){
                                        InsertSubjects(json);
                                    }else {
                                        Intent intent = new Intent(getActivity(), Payment_Activity.class);
                                        intent.putExtra("json",json);
                                        intent.putExtra("Amount",Amount);
                                        intent.putExtra("plan_type",PlanType);
                                        intent.putExtra("duration",PlanDuration+" "+PlanDuarationType);
                                        startActivity(intent);

                                    }


                                    dialog.dismiss();

                                }
                                else if (PlanType.equals("Standard") && total==9){

                                    Log.d("coresub", String.valueOf(CoreSubject));
                                    Log.d("extrasub", String.valueOf(ExtraSubject));
                                    Log.d("featuresub", String.valueOf(FeatureSubject));

                                    for ( int k = 0 ; k<CoreSubject.size();k++){

                                        String CoreSub_Id = CoreSubject.get(k).getSubjectId();
                                        String CoreSub_name = CoreSubject.get(k).getSubjectname();
                                        String CoreSub_type = CoreSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                CoreSub_Id,
                                                CoreSub_name,
                                                CoreSub_type,
                                                "1",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Core_selectedvalue",list_subjects.toString());


                                    for ( int k = 0 ; k<FeatureSubject.size();k++){

                                        String FeatureSub_Id = FeatureSubject.get(k).getSubjectId();
                                        String FeatureSub_name = FeatureSubject.get(k).getSubjectname();
                                        String FeatureSub_type = FeatureSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                FeatureSub_Id,
                                                FeatureSub_name,
                                                FeatureSub_type,
                                                "3",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Featured_selectedvalue",list_subjects.toString());

                                    for ( int k = 0 ; k<ExtraSubject.size();k++){

                                        String ExtraSub_Id   = ExtraSubject.get(k).getSubjectId();
                                        String ExtraSub_name = ExtraSubject.get(k).getSubjectname();
                                        String ExtraSub_type = ExtraSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                ExtraSub_Id,
                                                ExtraSub_name,
                                                ExtraSub_type,
                                                "2",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Extra_selectedvalue",list_subjects.toString());

                                    Gson gson = new Gson();
                                    json = gson.toJson(list_subjects);
                                    Log.d("extra_js",json);

                                    if (testCount.equals("0")){
                                        InsertSubjects(json);
                                    }else {
                                        Intent intent = new Intent(getActivity(), Payment_Activity.class);
                                        intent.putExtra("json",json);
                                        intent.putExtra("Amount",Amount);
                                        intent.putExtra("plan_type",PlanType);
                                        intent.putExtra("duration",PlanDuration+" "+PlanDuarationType);
                                        startActivity(intent);

                                    }


                                    dialog.dismiss();
                                }else if (PlanType.equals("Premium") && total==12){

                                    Log.d("coresub", String.valueOf(CoreSubject));
                                    Log.d("extrasub", String.valueOf(ExtraSubject));
                                    Log.d("featuresub", String.valueOf(FeatureSubject));

                                    for ( int k = 0 ; k<CoreSubject.size();k++){

                                        String CoreSub_Id = CoreSubject.get(k).getSubjectId();
                                        String CoreSub_name = CoreSubject.get(k).getSubjectname();
                                        String CoreSub_type = CoreSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                CoreSub_Id,
                                                CoreSub_name,
                                                CoreSub_type,
                                                "1",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Core_selectedvalue",list_subjects.toString());


                                    for ( int k = 0 ; k<FeatureSubject.size();k++){

                                        String FeatureSub_Id = FeatureSubject.get(k).getSubjectId();
                                        String FeatureSub_name = FeatureSubject.get(k).getSubjectname();
                                        String FeatureSub_type = FeatureSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                FeatureSub_Id,
                                                FeatureSub_name,
                                                FeatureSub_type,
                                                "3",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Featured_selectedvalue",list_subjects.toString());

                                    for ( int k = 0 ; k<ExtraSubject.size();k++){

                                        String ExtraSub_Id = ExtraSubject.get(k).getSubjectId();
                                        String ExtraSub_name = ExtraSubject.get(k).getSubjectname();
                                        String ExtraSub_type = ExtraSubject.get(k).getSubjecttype();

                                        Selected_Subject_Modelclass selected_subject_modelclasses=new Selected_Subject_Modelclass(
                                                ExtraSub_Id,
                                                ExtraSub_name,
                                                ExtraSub_type,
                                                "2",
                                                User_Id);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Extra_selectedvalue",list_subjects.toString());

                                    Gson gson = new Gson();
                                    json = gson.toJson(list_subjects);
                                    Log.d("extra_js",json);

                                    if (testCount.equals("0")){
                                        InsertSubjects(json);
                                    }else {
                                        Intent intent = new Intent(getActivity(), Payment_Activity.class);
                                        intent.putExtra("json",json);
                                        intent.putExtra("Amount",Amount);
                                        intent.putExtra("plan_type",PlanType);
                                        intent.putExtra("duration",PlanDuration+" "+PlanDuarationType);
                                        startActivity(intent);

                                    }



                                    dialog.dismiss();
                                }

                                else {

                                    if (PlanType.equals("Basic") && list_subjects.size()<6 || list_subjects.size()>6){
                                        Toast.makeText(getActivity(), "Please Choose 6 Subjects", Toast.LENGTH_SHORT).show();
                                    }else if (PlanType.equals("Standard") && list_subjects.size()<9 || list_subjects.size()>9){
                                        Toast.makeText(getActivity(), "Please Choose 9 Subjects", Toast.LENGTH_SHORT).show();
                                    }else if (PlanType.equals("Premium") && list_subjects.size()<12 || list_subjects.size()>12){
                                        Toast.makeText(getActivity(), "Please Choose 12 Subjects", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        });


                        dialog.show();
                        Window window = dialog.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    }
                });


                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        });


        final DynamoAdapter adapter = new DynamoAdapter(getActivity(),getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
    public void InsertSubjects(String data){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("post_data", data);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.insertsubject, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String status = response.getString("status");

                    if (status.equals("success")){

                        list_subjects.clear();
                        Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                        CreateNotification(User_Id,"Your Dynamo has been created.");

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SubjectFragment()).commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    private void notificationDialog() {
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "tutorialspoint_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity(), NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.logo)
                .setTicker("Tutorialspoint")
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Starkwiz")
                .setContentText("Your Dynamo has been created.")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
    }

    public void CreateNotification(String user_id, String notification_text){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("user_id", user_id);
        params.put("notification_text", notification_text);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createnotification, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String message= response.getString("message");
                    if (message.equals("notification created")){
                        Log.d("success",message);
                        notificationDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Log.d("error","error");

                Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    private void CheckFirstRegister(String Userid){

        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/countuser?user_id=" + User_Id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);

                            testCount = object.getString("testCount");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();

            }
        });

        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }

}