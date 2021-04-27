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
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.CitySpinnerAdapter;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.DistrictSpinnerAdapter;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.StateSpinnerAdapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.City_ModelClass;
import com.starkwiz.starkwiz.ModelClass.District_ModelClass;
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
    EditText et_personal_schoolname,et_studying_block;
    String FirstName,LastName,Dob,PhoneNo,Gender,Role,newaccount,cls,board,State,District,City,stateid,districtid;
    ArrayList<State_ModelClass> listStates;
    ArrayList<District_ModelClass>listDistricts;
    ArrayList<City_ModelClass>listCity;
    Spinner et_personal_firstname,et_personal_lastname,et_studying_city;
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
                if (et_studying_block.getText().toString().trim().length()<0 ){
                    AlertBoxClasses.SimpleAlertBox(Signup_teaching_Activity.this,"Check all fields");
                }else {
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
                    intent.putExtra("District",District);
                    intent.putExtra("City",City);
                    intent.putExtra("BlockNo",et_studying_block.getText().toString());
                    intent.putExtra("SchoolName",et_personal_schoolname.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }

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

        et_personal_schoolname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (et_personal_schoolname.getText().toString().trim().length()>0){
                    btn_signup_teaching.setEnabled(true);
                    btn_signup_teaching.setBackground(getResources().getDrawable(R.drawable.rounded_button));
                }else {
                    btn_signup_teaching.setEnabled(false);
                    btn_signup_teaching.setBackground(getResources().getDrawable(R.drawable.round_textview));
                }

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
        et_studying_block = findViewById(R.id.et_studying_block);

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

        params.put("state_id",StateId);
        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URLS.getdistrictbystate, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {

                    String district = response.getString("district");

                    JSONArray array = new JSONArray(district);
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
                            District=mystate.getDistrict_name();

                            GetCity(districtid);



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

    private void GetCity(String District){
        listCity.clear();
        ProgressDialog progressDialog = new ProgressDialog(Signup_teaching_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final Map<String,String> params = new HashMap<>();

        params.put("district_id",District);
        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URLS.getcitybydistrict, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {

                    String district = response.getString("city");

                    JSONArray array = new JSONArray(district);
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
                et_personal_lastname.setSelection(-1,true);

                et_personal_lastname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        try {

                            City_ModelClass mystate=(City_ModelClass) parent.getSelectedItem();


                            City = mystate.getCity_name();




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