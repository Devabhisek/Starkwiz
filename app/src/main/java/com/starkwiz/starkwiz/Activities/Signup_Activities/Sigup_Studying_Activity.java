package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
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
import android.widget.Toast;

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

public class Sigup_Studying_Activity extends AppCompatActivity {

    Button btn_signup_personal;
    String FirstName,LastName,Dob,PhoneNo,Gender,State,City,District,SchoolName,Class,Board,Role,BlockNo,newaccount,
            stateid,districtid,cityid,schoolid,state,district,city,School;
    EditText et_studying_block;
    RadioButton radio_class_four,radio_class_five,radio_class_six,radio_class_seven,radio_class_eight,radio_class_nine,radio_class_ten,radio_class_icse,radio_class_cbse;
    RadioGroup linear_studying_class,linear_studying_classes;
    ArrayList<State_ModelClass>listStates;
    ArrayList<District_ModelClass>listDistricts;
    ArrayList<City_ModelClass>listCity;
    ArrayList<School_ModelClass>listSchool;
    Spinner et_studying_state,et_studying_district,et_studying_city,et_personal_schoolname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup__studying_);

        Initialize();

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




        radio_class_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_four.setTextColor(getResources().getColor(R.color.black));
                radio_class_five.setTextColor(getResources().getColor(R.color.gray));
                radio_class_six.setTextColor(getResources().getColor(R.color.gray));
                radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_class_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_five.setTextColor(getResources().getColor(R.color.black));
                radio_class_four.setTextColor(getResources().getColor(R.color.gray));
                radio_class_six.setTextColor(getResources().getColor(R.color.gray));
                radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_class_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_six.setTextColor(getResources().getColor(R.color.black));
                radio_class_four.setTextColor(getResources().getColor(R.color.gray));
                radio_class_five.setTextColor(getResources().getColor(R.color.gray));
                radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_class_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_seven.setTextColor(getResources().getColor(R.color.black));
                radio_class_four.setTextColor(getResources().getColor(R.color.gray));
                radio_class_five.setTextColor(getResources().getColor(R.color.gray));
                radio_class_six.setTextColor(getResources().getColor(R.color.gray));
                radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_class_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_eight.setTextColor(getResources().getColor(R.color.black));
                radio_class_four.setTextColor(getResources().getColor(R.color.gray));
                radio_class_five.setTextColor(getResources().getColor(R.color.gray));
                radio_class_six.setTextColor(getResources().getColor(R.color.gray));
                radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
                radio_class_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_class.clearCheck();
            }
        });

        radio_class_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_nine.setTextColor(getResources().getColor(R.color.black));
                radio_class_four.setTextColor(getResources().getColor(R.color.gray));
                radio_class_five.setTextColor(getResources().getColor(R.color.gray));
                radio_class_six.setTextColor(getResources().getColor(R.color.gray));
                radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_class_ten.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_class.clearCheck();
            }
        });

        radio_class_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_class_ten.setTextColor(getResources().getColor(R.color.black));
                radio_class_four.setTextColor(getResources().getColor(R.color.gray));
                radio_class_five.setTextColor(getResources().getColor(R.color.gray));
                radio_class_six.setTextColor(getResources().getColor(R.color.gray));
                radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
                radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
                radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
                linear_studying_class.clearCheck();
            }
        });

        radio_class_cbse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_class_icse.setTextColor(getResources().getColor(R.color.gray));
                radio_class_cbse.setTextColor(getResources().getColor(R.color.black));
            }
        });
        radio_class_icse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_class_cbse.setTextColor(getResources().getColor(R.color.gray));
                radio_class_icse.setTextColor(getResources().getColor(R.color.black));
            }
        });



        btn_signup_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //State = et_studying_state.getText().toString().trim();
               // City = et_studying_city.getText().toString().trim();
                //District = et_studying_district.getText().toString().trim();
                //SchoolName = et_personal_schoolname.getText().toString().trim();
                BlockNo=et_studying_block.getText().toString().trim();

                if (radio_class_four.isChecked()) {
                    Class = "4";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();

                } else if (radio_class_five.isChecked()) {
                    Class = "5";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();
                } else if (radio_class_six.isChecked()) {
                    Class = "6";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();
                } else if (radio_class_seven.isChecked()) {
                    Class = "7";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();
                } else if (radio_class_eight.isChecked()) {
                    Class = "8";
                    Log.d("class",Class);
                    linear_studying_class.clearCheck();
                } else if (radio_class_nine.isChecked()) {
                    Class = "9";
                    Log.d("class",Class);
                    linear_studying_class.clearCheck();
                } else if (radio_class_ten.isChecked()) {
                    Class = "10";
                    Log.d("class",Class);
                    linear_studying_class.clearCheck();
                } else {
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select Your Class");
                }

                if (radio_class_cbse.isChecked()) {
                    Board = "CBSE";
                    Log.d("board",Board);

                } else if (radio_class_icse.isChecked()) {
                    Board = "ICSE";
                    Log.d("board",Board);
                } else {
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select School Board");
                }

                if (Class==null){
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select Your Class");

                }else if (Board==null){
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select School Board");

                }else {
                    Intent intent = new Intent(Sigup_Studying_Activity.this, Signup_Email_Verification_Activity.class);
                    intent.putExtra("FirstName", FirstName);
                    intent.putExtra("LastName", LastName);
                    intent.putExtra("Dob", Dob);
                    intent.putExtra("PhoneNo", PhoneNo);
                    intent.putExtra("Gender", Gender);
                    intent.putExtra("State", state);
                    intent.putExtra("City", city);
                    intent.putExtra("BlockNo", BlockNo);
                    intent.putExtra("District", district);
                    intent.putExtra("SchoolName", School);
                    intent.putExtra("Class", Class);
                    intent.putExtra("Board", Board);
                    intent.putExtra("Role", Role);
                    startActivity(intent);
                }
            }
        });
    }

    private void Initialize() {

        btn_signup_personal = findViewById(R.id.btn_signup_personal);
        et_studying_state = findViewById(R.id.et_studying_state);
        et_studying_city = findViewById(R.id.et_studying_city);
        et_studying_district = findViewById(R.id.et_studying_district);
        et_personal_schoolname = findViewById(R.id.et_personal_schoolname);
        radio_class_four = findViewById(R.id.radio_class_four);
        radio_class_five = findViewById(R.id.radio_class_five);
        radio_class_six = findViewById(R.id.radio_class_six);
        radio_class_seven = findViewById(R.id.radio_class_seven);
        radio_class_eight = findViewById(R.id.radio_class_eight);
        radio_class_nine = findViewById(R.id.radio_class_nine);
        radio_class_ten = findViewById(R.id.radio_class_ten);
        radio_class_icse = findViewById(R.id.radio_class_icse);
        radio_class_cbse = findViewById(R.id.radio_class_cbse);
        linear_studying_class = findViewById(R.id.linear_studying_class);
        linear_studying_classes = findViewById(R.id.linear_studying_classes);
        et_studying_block = findViewById(R.id.et_studying_block);

        radio_class_four.setChecked(true);
        radio_class_icse.setChecked(true);

        radio_class_nine.setTextColor(getResources().getColor(R.color.gray));
        radio_class_five.setTextColor(getResources().getColor(R.color.gray));
        radio_class_six.setTextColor(getResources().getColor(R.color.gray));
        radio_class_seven.setTextColor(getResources().getColor(R.color.gray));
        radio_class_eight.setTextColor(getResources().getColor(R.color.gray));
        radio_class_ten.setTextColor(getResources().getColor(R.color.gray));

        radio_class_icse.setTextColor(getResources().getColor(R.color.black));
        radio_class_cbse.setTextColor(getResources().getColor(R.color.gray));

        listStates = new ArrayList<>();
        listDistricts = new ArrayList<>();
        listCity = new ArrayList<>();
        listSchool = new ArrayList<>();


    }

    private void GetStates(){
        ProgressDialog progressDialog = new ProgressDialog(Sigup_Studying_Activity.this);
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

                                StateSpinnerAdapter adapter = new StateSpinnerAdapter(Sigup_Studying_Activity.this,R.layout.spinner_textview,listStates);
                                et_studying_state.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            et_studying_state.setSelection(-1,true);

                            et_studying_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {

                                    try {

                                        State_ModelClass mystate=(State_ModelClass) parent.getSelectedItem();

                                        stateid = mystate.getId();

                                        state = mystate.getState_name();

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
        Volley.newRequestQueue(Sigup_Studying_Activity.this).add(getRequest);


    }

    private void GetDistrict(String StateId){
        listDistricts.clear();
        ProgressDialog progressDialog = new ProgressDialog(Sigup_Studying_Activity.this);
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
                        DistrictSpinnerAdapter adapter = new DistrictSpinnerAdapter(Sigup_Studying_Activity.this,R.layout.spinner_textview,listDistricts);
                        et_studying_district.setAdapter(adapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                et_studying_district.setSelection(-1,true);

                et_studying_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        MySingleton.getInstance(Sigup_Studying_Activity.this).addToRequestque(stringRequest);
    }

    private void GetCity(String DistrictId){
        listCity.clear();
        ProgressDialog progressDialog = new ProgressDialog(Sigup_Studying_Activity.this);
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
                        CitySpinnerAdapter adapter = new CitySpinnerAdapter(Sigup_Studying_Activity.this,
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

                            city=mystate.getCity_name();

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

        Volley.newRequestQueue(Sigup_Studying_Activity.this).add(stringRequest);


    }

    private void GetSchool(String CityId){
        listSchool.clear();
        ProgressDialog progressDialog = new ProgressDialog(Sigup_Studying_Activity.this);
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
                        School_SpinnerAdapter adapter = new School_SpinnerAdapter(Sigup_Studying_Activity.this,
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
                                btn_signup_personal.setEnabled(true);
                                btn_signup_personal.setBackgroundResource(R.drawable.rounded_button);
                            }else {
                                btn_signup_personal.setEnabled(false);
                                btn_signup_personal.setBackgroundResource(R.drawable.round_textview_grey);
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

        Volley.newRequestQueue(Sigup_Studying_Activity.this).add(jsonObjectRequest);
    }

}