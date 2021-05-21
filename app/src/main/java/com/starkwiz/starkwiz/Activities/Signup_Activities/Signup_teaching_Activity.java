package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.CitySpinnerAdapter;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.DistrictSpinnerAdapter;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.School_SpinnerAdapter;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.StateSpinnerAdapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.City_ModelClass;
import com.starkwiz.starkwiz.ModelClass.District_ModelClass;
import com.starkwiz.starkwiz.ModelClass.School_ModelClass;
import com.starkwiz.starkwiz.ModelClass.State_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Signup_teaching_Activity extends AppCompatActivity {

    Button btn_signup_teaching;
    RadioGroup linear_studying_class,linear_studying_classes;
    RadioButton radio_teaching_icse,radio_teaching_cbse,radio_teahing_four,radio_teahing_five,radio_teahing_six,
            radio_teahing_seven,radio_teahing_eight,radio_teahing_nine,radio_teahing_ten;
    //EditText et_studying_block;
    String FirstName,LastName,Dob,PhoneNo,Gender,Role,newaccount,cls,board,State,District,City,stateid,
            districtid,district,schoolid,School,cityid;
    ArrayList<State_ModelClass> listStates;
    ArrayList<District_ModelClass>listDistricts;
    ArrayList<City_ModelClass>listCity;
    ArrayList<School_ModelClass>listSchool;
    Spinner et_personal_firstname,et_personal_lastname,et_studying_city,et_personal_schoolname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teaching_);

        Initialzation();
        try {

            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            Dob = getIntent().getStringExtra("Dob");
            PhoneNo = getIntent().getStringExtra("PhoneNo");
            Role = getIntent().getStringExtra("Role");
            Gender = getIntent().getStringExtra("Gender");
            newaccount = getIntent().getStringExtra("newaccount");


        }catch (Exception e){
            e.printStackTrace();
        }

        GetStates();

        btn_signup_teaching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Signup_teaching_Activity.this, Signup_Qualification_Activity.class);
                    intent.putExtra("FirstName",FirstName);
                    intent.putExtra("LastName",LastName);
                    intent.putExtra("Dob",Dob);
                    intent.putExtra("PhoneNo",PhoneNo);
                    intent.putExtra("Role",Role);
                    intent.putExtra("Gender",Gender);
                    intent.putExtra("newaccount",newaccount);
                    intent.putExtra("Class",cls);
                    intent.putExtra("Board",board);
                    intent.putExtra("State",State);
                    intent.putExtra("District",district);
                    intent.putExtra("City",City);
                    intent.putExtra("BlockNo","block no");
                    intent.putExtra("SchoolName",School);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }
        });

        radio_teahing_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.black));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
                cls = "4";
            }
        });

        radio_teahing_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.black));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
                cls = "5";
            }
        });

        radio_teahing_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.black));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
                cls = "6";
            }
        });

        radio_teahing_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.black));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
                cls = "7";
            }
        });

        radio_teahing_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_class.clearCheck();
                cls = "8";
            }
        });

        radio_teahing_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.black));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_class.clearCheck();
                cls = "9";
            }
        });

        radio_teahing_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_teahing_four.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_teahing_ten.setTextColor(getResources().getColor(R.color.black));
                linear_studying_class.clearCheck();
                cls = "10";
            }
        });

        radio_teaching_cbse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radio_teaching_icse.setTextColor(getResources().getColor(R.color.gray));
                radio_teaching_cbse.setTextColor(getResources().getColor(R.color.black));
                board = "CBSE";
            }
        });

        radio_teaching_icse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radio_teaching_icse.setTextColor(getResources().getColor(R.color.black));
                radio_teaching_cbse.setTextColor(getResources().getColor(R.color.gray));
                board = "ICSE";
            }
        });

    }

    private void Initialzation() {
        btn_signup_teaching = findViewById(R.id.btn_signup_teaching);
        linear_studying_class = findViewById(R.id.linear_studying_class);
        linear_studying_classes = findViewById(R.id.linear_studying_classes);
        radio_teaching_icse = findViewById(R.id.radio_teaching_icse);
        radio_teaching_cbse = findViewById(R.id.radio_teaching_cbse);
        radio_teahing_four = findViewById(R.id.radio_teahing_four);
        radio_teahing_five = findViewById(R.id.radio_teahing_five);
        radio_teahing_six = findViewById(R.id.radio_teahing_six);
        radio_teahing_seven = findViewById(R.id.radio_teahing_seven);
        radio_teahing_eight = findViewById(R.id.radio_teahing_eight);
        radio_teahing_nine = findViewById(R.id.radio_teahing_nine);
        radio_teahing_ten = findViewById(R.id.radio_teahing_ten);

        et_personal_schoolname = findViewById(R.id.et_personal_schoolname);
        et_personal_firstname = findViewById(R.id.et_personal_firstname);
        et_personal_lastname = findViewById(R.id.et_personal_lastname);
        et_studying_city = findViewById(R.id.et_studying_city);
        //et_studying_block = findViewById(R.id.et_studying_block);

        radio_teahing_four.setChecked(true);
        radio_teaching_icse.setChecked(true);

        cls="4";
        board="ICSE";

        radio_teahing_four.setTextColor(getResources().getColor(R.color.black));
        radio_teahing_five.setTextColor(getResources().getColor(R.color.gray));
        radio_teahing_six.setTextColor(getResources().getColor(R.color.gray));
        radio_teahing_seven.setTextColor(getResources().getColor(R.color.gray));
        radio_teahing_eight.setTextColor(getResources().getColor(R.color.gray));
        radio_teahing_nine.setTextColor(getResources().getColor(R.color.gray));
        radio_teahing_ten.setTextColor(getResources().getColor(R.color.gray));

        radio_teaching_icse.setTextColor(getResources().getColor(R.color.black));
        radio_teaching_cbse.setTextColor(getResources().getColor(R.color.gray));

        listStates = new ArrayList<>();
        listDistricts = new ArrayList<>();
        listCity = new ArrayList<>();
        listSchool = new ArrayList<>();
    }

    private void GetStates(){
        ProgressDialog progressDialog = new ProgressDialog(Signup_teaching_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.getstate, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();

                        // display response
                        for (int j=0 ; j<response.length() ; j++){

                            try {
                                JSONObject object = response.getJSONObject(j);

                                State_ModelClass modelClass =  new State_ModelClass(
                                        object.getString("id"),
                                        object.getString("state_name"),
                                        object.getString("state_code")
                                );

                                listStates.add(modelClass);
                                StateSpinnerAdapter adapter = new StateSpinnerAdapter(Signup_teaching_Activity.this,R.layout.spinner_textview,listStates);
                                et_personal_firstname.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            et_personal_firstname.setSelection(-1,true);

                            et_personal_firstname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {

                                    try {

                                        State_ModelClass mystate=(State_ModelClass) parent.getSelectedItem();

                                        stateid = mystate.getId();
                                        State = mystate.getState_name();



                                        GetDistrict(stateid);



                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                } // to close the onItemSelected
                                public void onNothingSelected(AdapterView<?> parent)
                                {

                                }
                            });
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
        Volley.newRequestQueue(Signup_teaching_Activity.this).add(getRequest);


    }

    private void GetDistrict(String StateId){
        listDistricts.clear();
        ProgressDialog progressDialog = new ProgressDialog(Signup_teaching_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final Map<String,String> params = new HashMap<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getdistrictbystate?state_id="+StateId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    //String district = response.getString("district");

                    JSONArray array = new JSONArray(response);
                    for (int i = 0 ; i<array.length() ; i++){

                        JSONObject object = array.getJSONObject(i);

                        District_ModelClass modelClass = new District_ModelClass(
                                object.getString("id"),
                                object.getString("district_name"),
                                object.getString("state_id")
                        );

                        listDistricts.add(modelClass);
                        DistrictSpinnerAdapter adapter = new DistrictSpinnerAdapter(Signup_teaching_Activity.this,R.layout.spinner_textview,listDistricts);
                        et_personal_lastname.setAdapter(adapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                et_personal_lastname.setSelection(-1,true);

                et_personal_lastname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        try {

                            District_ModelClass mystate=(District_ModelClass) parent.getSelectedItem();

                            districtid = mystate.getId();
                            district=mystate.getDistrict_name();
                            GetCity(stateid);



                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(Signup_teaching_Activity.this).addToRequestque(stringRequest);
    }

    private void GetCity(String District){
        listCity.clear();
        ProgressDialog progressDialog = new ProgressDialog(Signup_teaching_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getcitybydistrict?district_id="+districtid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();

                    JSONArray array = new JSONArray(response);
                    for (int i = 0 ; i<array.length() ; i++){

                        JSONObject object = array.getJSONObject(i);

                        City_ModelClass modelClass = new City_ModelClass(
                                object.getString("id"),
                                object.getString("city_name"),
                                object.getString("state_id"),
                                object.getString("district_id")
                        );

                        listCity.add(modelClass);
                        CitySpinnerAdapter adapter = new CitySpinnerAdapter(Signup_teaching_Activity.this,
                                R.layout.spinner_textview,listCity);
                        et_studying_city.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                et_studying_city.setSelection(-1,true);

                et_studying_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        try {

                            City_ModelClass mystate=(City_ModelClass) parent.getSelectedItem();

                            cityid = mystate.getId();

                            City=mystate.getCity_name();

                            GetSchool(cityid);


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });

        Volley.newRequestQueue(Signup_teaching_Activity.this).add(stringRequest);
    }

    private void GetSchool(String CityId){
        listSchool.clear();
        ProgressDialog progressDialog = new ProgressDialog(Signup_teaching_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final Map<String,String> params = new HashMap<>();

        params.put("city_id",CityId);
        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URLS.getschool, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {

                    String district = response.getString("school");

                    JSONArray array = new JSONArray(district);
                    for (int i = 0 ; i<array.length() ; i++){

                        JSONObject object = array.getJSONObject(i);

                        School_ModelClass modelClass = new School_ModelClass(
                                object.getString("id"),
                                object.getString("school_name")
                        );

                        listSchool.add(modelClass);
                        School_SpinnerAdapter adapter = new School_SpinnerAdapter(Signup_teaching_Activity.this,
                                R.layout.spinner_textview,listSchool);
                        et_personal_schoolname.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                et_personal_schoolname.setSelection(-1,true);

                et_personal_schoolname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        try {

                            School_ModelClass mystate=(School_ModelClass) parent.getSelectedItem();

                            schoolid = mystate.getId();

                            School=mystate.getSchool_name();

                            if (School!=null){
                                btn_signup_teaching.setEnabled(true);
                                btn_signup_teaching.setBackgroundResource(R.drawable.rounded_button);
                            }else {
                                btn_signup_teaching.setEnabled(false);
                                btn_signup_teaching.setBackgroundResource(R.drawable.round_textview_grey);
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });

        Volley.newRequestQueue(Signup_teaching_Activity.this).add(jsonObjectRequest);
    }

}