package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starkwiz.starkwiz.Activities.Dance_Activity;
import com.starkwiz.starkwiz.Activities.Declamation;
import com.starkwiz.starkwiz.Activities.Music_Activity;
import com.starkwiz.starkwiz.Activities.Student_Quiz_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.Activities.UserSelection_Activity;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.CoreSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.ExtraSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.FeaturedSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.GetList_Adapter;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Core_Subjectbyplans_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Extra_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Featured_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Quiz_Modelclass;
import com.starkwiz.starkwiz.ModelClass.Selected_Subject_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

import static android.content.Context.MODE_PRIVATE;


public class SubjectFragment extends Fragment {

    CardView card_subject,carddance,card_declamation,card_music;
    RelativeLayout rl_addsubject;
    LinearLayout lineartype,basic,linear_basictype,standard,linear_standardtype,premium,
            linear_premiumtype,core,extra,feature,linear_coretype,linear_extratype,linear_featuretype;
    TextView txtplantype,txtplanprice,txtplanvalidity,txt_perprice,
            txtplantype_subject,txtplanprice_subject,txtplanvalidity_subject,txt_perprice_subject;
    String PlanType,PlanPrice,PlanPerMonth,PlanDuration,PlanDuarationType,json;
    Button btn_plans,btn_subjectproceed;
    ArrayList<Core_Subjectbyplans_ModelClass>list_coresubjects;
    ArrayList<Extra_Subjectplan_ModelClass>list_extrasubjects;
    ArrayList<Featured_Subjectplan_ModelClass>list_featuresubjects;
    RecyclerView lv_subjectsplan,lv_subjectsplan_extra,lv_subjectsplan_feature;
    CoreSubjects_Adapter adapter;
    ExtraSubjects_Adapter extraSubjects_adapter;
    FeaturedSubjects_Adapter featuredSubjects_adapter;
    final ArrayList<String> arrPackage=new ArrayList<>();
     SharedPreferences sharedPreferences ;
    HashSet<String> hashSet = new HashSet<String>();
    ArrayList<Selected_Subject_Modelclass>list_subjects;
    private TourGuide mTourGuideHandler;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view = inflater.inflate(R.layout.fragment_subject, container, false);
        card_subject = view.findViewById(R.id.card_subject);
        carddance = view.findViewById(R.id.carddance);
        card_declamation = view.findViewById(R.id.card_declamation);
        card_music = view.findViewById(R.id.card_music);
        rl_addsubject = view.findViewById(R.id.rl_addsubject);





        list_coresubjects =new ArrayList<>();
        list_extrasubjects =new ArrayList<>();
        list_featuresubjects =new ArrayList<>();
        list_subjects =new ArrayList<>();
        adapter = new CoreSubjects_Adapter(list_coresubjects,getActivity());
        extraSubjects_adapter = new ExtraSubjects_Adapter(list_extrasubjects,getActivity());
        featuredSubjects_adapter = new FeaturedSubjects_Adapter(list_featuresubjects,getActivity());


//        String subject = String.valueOf(featuredSubjects_adapter.getArrayList());
//
//        Log.d("Sub",subject);



        card_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Subjectwise_Syllabus_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        carddance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Dance_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        card_declamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Declamation.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        card_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Music_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        rl_addsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.activity_plans);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                lineartype = dialog.findViewById(R.id.lineartype);
                basic = dialog.findViewById(R.id.basic);
                linear_basictype = dialog.findViewById(R.id.linear_basictype);
                standard = dialog.findViewById(R.id.standard);
                linear_standardtype = dialog.findViewById(R.id.linear_standardtype);
                premium = dialog.findViewById(R.id.premium);
                linear_premiumtype = dialog.findViewById(R.id.linear_premiumtype);
                txtplantype = dialog.findViewById(R.id.txtplantype);
                txtplanprice = dialog.findViewById(R.id.txtplanprice);
                txtplanvalidity = dialog.findViewById(R.id.txtplanvalidity);
                txt_perprice = dialog.findViewById(R.id.txt_perprice);
                btn_plans = dialog.findViewById(R.id.btn_plans);
                btn_plans.setEnabled(false);


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

                                        if (object.getString("plan_type").equals("basic")){
                                            txt_perprice.setText("Rs "+object.getString("plan_price_month")+" / per month");
                                            txtplanprice.setText(object.getString("plan_price"));
                                            txtplantype.setText(object.getString("plan_type"));
                                            txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                            PlanType = "Basic";
                                            PlanPrice = object.getString("plan_price");
                                            PlanPerMonth = object.getString("plan_price_month");
                                            PlanDuration = object.getString("plan_duration");
                                            PlanDuarationType = object.getString("plan_duration_type");

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

                                                if (object.getString("plan_type").equals("basic")){
                                                    txt_perprice.setText("Rs "+object.getString("plan_price_month")+"/ per month");
                                                    txtplanprice.setText(object.getString("plan_price"));
                                                    txtplantype.setText(object.getString("plan_type"));
                                                    txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                                    PlanPrice = object.getString("plan_price");
                                                    PlanPerMonth = object.getString("plan_duration");

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

                                                if (object.getString("plan_type").equals("Standard")){
                                                    txt_perprice.setText("Rs "+object.getString("plan_price_month")+" / per month");
                                                    txtplanprice.setText(object.getString("plan_price"));
                                                    txtplantype.setText(object.getString("plan_type"));
                                                    txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                                    PlanPrice = object.getString("plan_price");
                                                    PlanPerMonth = object.getString("plan_duration");

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

                                                if (object.getString("plan_type").equals("Premium")){
                                                    txt_perprice.setText("Rs "+object.getString("plan_price_month")+"/ per month");
                                                    txtplanprice.setText(object.getString("plan_price"));
                                                    txtplantype.setText(object.getString("plan_type"));
                                                    txtplanvalidity.setText(object.getString("plan_duration")+" "+object.getString("plan_duration_type"));

                                                    PlanPrice = object.getString("plan_price");
                                                    PlanPerMonth = object.getString("plan_duration");

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

                        Volley.newRequestQueue(getActivity()).add(getRequest);
                    }
                });


                btn_plans.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.alert_subjectplan);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        txtplantype_subject = dialog.findViewById(R.id.txtplantype_subject);
                        txtplanprice_subject = dialog.findViewById(R.id.txtplanprice_subject);
                        txtplanvalidity_subject = dialog.findViewById(R.id.txtplanvalidity_subject);
                        txt_perprice_subject = dialog.findViewById(R.id.txt_perprice_subject);
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




                        txtplantype_subject.setText(PlanType);
                        txtplanprice_subject.setText(PlanPrice);
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
                                    "https://www.rentopool.com/starkwiz/api/auth/subjectbyplan?plan_type=" + PlanType, new Response.Listener<String>() {
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


                                                        "",
                                                        "",
                                                        subject_object.getString("subject_name"),
                                                        subject_object.getString("subject_type"),
                                                        subject_object.getString("plan_type")
                                                );

                                                list_coresubjects.add(core_subjectbyplans_modelClass);

                                                lv_subjectsplan.setAdapter(adapter);
                                                btn_subjectproceed.setEnabled(true);

                                            }else if (plan.equals("extra")){

                                                Extra_Subjectplan_ModelClass extra_subjectplan_modelClass = new Extra_Subjectplan_ModelClass(

                                                        "",
                                                        "",
                                                        subject_object.getString("subject_name"),
                                                        subject_object.getString("subject_type"),
                                                        subject_object.getString("plan_type")

                                                );
                                                list_extrasubjects.add(extra_subjectplan_modelClass);

                                                lv_subjectsplan_extra.setAdapter(extraSubjects_adapter);
                                                btn_subjectproceed.setEnabled(true);

                                            }else {
                                                Featured_Subjectplan_ModelClass featured_subjectplan_modelClass = new Featured_Subjectplan_ModelClass(

                                                        "",
                                                        "",
                                                        subject_object.getString("subject_name"),
                                                        subject_object.getString("subject_type"),
                                                        subject_object.getString("plan_type")

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

                            MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);


                            btn_subjectproceed.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    ArrayList<String> CoreSubject = adapter.getArrayList();
                                    ArrayList<String> ExtraSubject = extraSubjects_adapter.getExtraArrayList();
                                    ArrayList<String> FeatureSubject = featuredSubjects_adapter.getFeatureArrayList();

                                    int total = CoreSubject.size()+ExtraSubject.size()+FeatureSubject.size();

                                    if (total==6){
                                        ArrayList<ArrayList<String>> totalsubject = new ArrayList<ArrayList<String>>();

                                        totalsubject.add(CoreSubject);
                                        totalsubject.add(ExtraSubject);
                                        totalsubject.add(FeatureSubject);

                                        Log.d("coresub", String.valueOf(CoreSubject));
                                        Log.d("extrasub", String.valueOf(ExtraSubject));
                                        Log.d("featuresub", String.valueOf(FeatureSubject));
                                        Log.d("total", String.valueOf(totalsubject));

                                        for (int a =0 ;a<totalsubject.size();a++){

                                            for (int b = 0 ;b<totalsubject.get(a).size();b++){

                                                Selected_Subject_Modelclass modelclass =
                                                        new Selected_Subject_Modelclass(
                                                        totalsubject.get(a).get(b)
                                                );
                                                list_subjects.add(modelclass);
                                            }

                                            Gson gson = new Gson();
                                            String json = gson.toJson(list_subjects);
                                            Log.d("js",json);
                                        }



                                    }else {
                                        Toast.makeText(getActivity(), "Please Choose any 6 Subjects", Toast.LENGTH_SHORT).show();
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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        sharedPreferences = getActivity().getSharedPreferences("USER", 0);
        String strtext = sharedPreferences.getString("First","");

        if (strtext.isEmpty()) {

            Firsttime_Guide();

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences("USER", 0);
        String strtext = sharedPreferences.getString("First","");

        if (strtext.isEmpty()) {

            Firsttime_Guide();

        }
    }

    private void Firsttime_Guide(){

        mTourGuideHandler = TourGuide.init(getActivity()).with(TourGuide.Technique.Click)
                .setPointer(new Pointer())
                .setToolTip( new ToolTip()
                        .setTitle("Click on")
                        .setDescription("Any Subject")
                        .setBackgroundColor(Color.parseColor("#88D5F0"))
                        .setShadow(true)
                        .setGravity(Gravity.BOTTOM | Gravity.RIGHT))
                .setOverlay(new Overlay()) .playOn(card_subject);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("USER",MODE_PRIVATE) ;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("First","Firsttime" );
        editor.commit();

    }
}



//    @Override
//    public void onCoreSubjectSelected(Core_Subjectbyplans_ModelClass Subject) {
//
//
//        arrPackage.add(Subject.getSubject_name());
//
//
//    }

