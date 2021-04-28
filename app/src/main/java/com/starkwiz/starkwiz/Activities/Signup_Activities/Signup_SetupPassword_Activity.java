package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.Dasboard_Activity;
import com.starkwiz.starkwiz.Activities.SettingActivity;
import com.starkwiz.starkwiz.Activities.Starting_Pages.Login_Activity;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Signup_SetupPassword_Activity extends AppCompatActivity {

    Button btn_verfication_proceed;
    SeekBar seekbar_pswd;
    EditText et_password,et_cnfrm_password;
    TextView txt_strength;
    String FirstName,LastName,Dob,PhoneNo,Gender,State,City,District,SchoolName,Class,
            Board,Email,Role,BlockNo,newaccount,Qualification,Profession,kids,user_id,Experience;
   ArrayList<Login_ModelClass> login_modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__setup_password_);

        btn_verfication_proceed = findViewById(R.id.btn_verfication_proceed);
        seekbar_pswd = findViewById(R.id.seekbar_pswd);
        et_password = findViewById(R.id.et_password);
        txt_strength = findViewById(R.id.txt_strength);
        et_cnfrm_password = findViewById(R.id.et_cnfrm_password);
        login_modelClass=new ArrayList<>();


        try {
            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            Dob = getIntent().getStringExtra("Dob");
            PhoneNo = getIntent().getStringExtra("PhoneNo");
            Gender = getIntent().getStringExtra("Gender");
            State = getIntent().getStringExtra("State");
            City = getIntent().getStringExtra("City");
            District = getIntent().getStringExtra("District");
            SchoolName = getIntent().getStringExtra("SchoolName");
            Class = getIntent().getStringExtra("Class");
            Board = getIntent().getStringExtra("Board");
            Role = getIntent().getStringExtra("Role");
            Email = getIntent().getStringExtra("Email");
            BlockNo = getIntent().getStringExtra("BlockNo");
            newaccount = getIntent().getStringExtra("newaccount");
            Qualification = getIntent().getStringExtra("Qualification");
            Profession = getIntent().getStringExtra("Profession");
            kids = getIntent().getStringExtra("kids");
            Experience = getIntent().getStringExtra("Experience");



        }catch (Exception e){
            e.printStackTrace();
        }

        et_cnfrm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_cnfrm_password.getText().toString().trim().length()>0){
                    btn_verfication_proceed.setEnabled(true);
                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.rounded_button));
                }else {
                    btn_verfication_proceed.setEnabled(false);
                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.round_textview));
                }
            }
        });

        btn_verfication_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (et_password.getText().toString().trim().matches(et_cnfrm_password.getText().toString().trim())){

                    if (Role.equals("Student")){
                        Signup();
                    }else if(Role.equals("Parent")){
                        Signup_Parent();
                    }else if(Role.equals("Teacher")){
                        Signup_Teacher();
                    }

                }
                else {
                    AlertBoxClasses.SimpleAlertBox(Signup_SetupPassword_Activity.this,"Password did not matched");
                }


            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length()==0) {
                    seekbar_pswd.setProgress(0);
                    seekbar_pswd.setBackgroundColor(getResources().getColor(R.color.orange));
                    txt_strength.setText("Weak");
                    txt_strength.setTextColor(getResources().getColor(R.color.orange));
                } else if(s.length()<2) {
                    seekbar_pswd.setProgress(49);
                    seekbar_pswd.setBackgroundColor(getResources().getColor(R.color.orange));
                    txt_strength.setText("Weak");
                    txt_strength.setTextColor(getResources().getColor(R.color.orange));
                }else if(s.length()<4) {
                    seekbar_pswd.setProgress(60);
                    seekbar_pswd.setBackgroundColor(getResources().getColor(R.color.teal_700));
                    txt_strength.setText("Medium");
                    txt_strength.setTextColor(getResources().getColor(R.color.teal_700));
                }else if(s.length()>5 || s.length()==6) {
                    seekbar_pswd.setProgress(80);
                    seekbar_pswd.setBackgroundColor(getResources().getColor(R.color.teal_200));
                    txt_strength.setText("Strong");
                    txt_strength.setTextColor(getResources().getColor(R.color.teal_200));
                }else {
                    seekbar_pswd.setProgress(100);
                    seekbar_pswd.setBackgroundColor(getResources().getColor(R.color.teal_200));
                    txt_strength.setText("Strong");
                    txt_strength.setTextColor(getResources().getColor(R.color.teal_200));
                }
            }
        });


    }

    private void Signup() {

        ProgressDialog progressDialog = new ProgressDialog(Signup_SetupPassword_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("first_name", FirstName);
        params.put("last_name", LastName);
        params.put("date_of_birth", Dob);
        params.put("mobile_number", PhoneNo);
        params.put("gender", Gender);
        params.put("state", State);
        params.put("city", City);
        params.put("district", District);
        params.put("school", SchoolName);
        params.put("class", Class);
        params.put("school_board", Board);
        params.put("bio", "");
        params.put("address", "");
        params.put("facebook_link", "");
        params.put("instagram_link", "");
        params.put("interest", "");
        params.put("status", "");
        params.put("image", "");
        params.put("role", Role);
        params.put("has_child", "");
        params.put("email", Email);
        params.put("email_verified_at", "");
        params.put("password", et_password.getText().toString().trim());
        params.put("password_confirmation", et_password.getText().toString().trim());
        params.put("block_number", BlockNo);


        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.Register, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    String Id = response.getString("user");

                    JSONObject object = new JSONObject(Id);

                    if (!object.equals(" ")){

                        Login_ModelClass modelClass = new Login_ModelClass(
                                "",
                                object.getString("id"),
                                object.getString("first_name"),
                                object.getString("last_name"),
                                object.getString("mobile_number"),
                                object.getString("class"),
                                object.getString("school_board"),
                                object.getString("role"),
                                object.getString("email")
                        );
                        login_modelClass.add(modelClass);
                        SharedPrefManager.getInstance(Signup_SetupPassword_Activity.this).userLogin(modelClass);

                        if (Role.equals("Parent")){
                                Parent_Profile(object.getString("id"),Profession,Qualification,kids);
                        }else {
                            Profile(object.getString("id"));
                        }
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
//                Intent intent = new Intent(Signup_SetupPassword_Activity.this, Login_Activity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Toast.makeText(Signup_SetupPassword_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();

            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_SetupPassword_Activity.this).add(jsonRequest);

    }

    private void Signup_Parent() {

        ProgressDialog progressDialog = new ProgressDialog(Signup_SetupPassword_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("first_name", FirstName);
        params.put("last_name", LastName);
        params.put("date_of_birth", Dob);
        params.put("mobile_number", PhoneNo);
        params.put("gender", Gender);
        params.put("role", Role);
        params.put("email", Email);
        params.put("password", et_password.getText().toString().trim());
        params.put("password_confirmation", et_password.getText().toString().trim());


        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.registerparent, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    String Id = response.getString("user");

                    String message = response.getString("message");

                    if (message.equals("Parent successfully registered")){

                         user_id = response.getString("user_id");

                        Parent_Profile(user_id,Profession,Qualification,kids);
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
                Toast.makeText(Signup_SetupPassword_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_SetupPassword_Activity.this).add(jsonRequest);

    }

    private void Signup_Teacher() {

        ProgressDialog progressDialog = new ProgressDialog(Signup_SetupPassword_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("first_name", FirstName);
        params.put("last_name", LastName);
        params.put("date_of_birth", Dob);
        params.put("mobile_number", PhoneNo);
        params.put("gender", Gender);
        params.put("role", Role);
        params.put("email", Email);
        params.put("password", et_password.getText().toString().trim());
        params.put("password_confirmation", et_password.getText().toString().trim());
        params.put("school_board", Board);
        params.put("teacher_block", BlockNo);
        params.put("state", State);
        params.put("district", District);
        params.put("city", City);
        params.put("school", SchoolName);



        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.registerteacher, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();


                try {
                    String message = response.getString("message");
                    if (message.equals("User successfully registered")){

                        user_id = response.getString("user_id");

                        Teacher_Profile(user_id,Class,Qualification,Profession,Experience);
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
                Toast.makeText(Signup_SetupPassword_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_SetupPassword_Activity.this).add(jsonRequest);

    }

    private void Profile(String id) {

        ProgressDialog progressDialog = new ProgressDialog(Signup_SetupPassword_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("userid", id);



        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.Profile, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    String created = response.getString("message");

                   if (created.equals("profile created")){

                       if (newaccount!=null){
                           startActivity(new Intent(Signup_SetupPassword_Activity.this,SettingActivity.class));
                           overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                       }
                       else {
                           Intent intent = new Intent(Signup_SetupPassword_Activity.this, Login_Activity.class);
                           startActivity(intent);
                           overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                       }
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
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_SetupPassword_Activity.this).add(jsonRequest);

    }

    private void Parent_Profile(String id,String profession,String qualification, String no_of_children) {

        ProgressDialog progressDialog = new ProgressDialog(Signup_SetupPassword_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("user_id", id);
        params.put("profession", profession);
        params.put("qualification", qualification);
        params.put("no_of_children", no_of_children);



        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createparentprofile, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    String created = response.getString("message");

                    if (created.equals("Success")){
                        Toast.makeText(Signup_SetupPassword_Activity.this, "Profile Created", Toast.LENGTH_SHORT).show();

                        if (newaccount!=null){
                            startActivity(new Intent(Signup_SetupPassword_Activity.this, SettingActivity.class));
                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Signup_SetupPassword_Activity.this, Login_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_SetupPassword_Activity.this).add(jsonRequest);

    }

    private void Teacher_Profile(String user_id,String current_teaching_class,
                                 String qualification_degree, String qualification_major_subject, String year_of_experience) {

        ProgressDialog progressDialog = new ProgressDialog(Signup_SetupPassword_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("user_id", user_id);
        params.put("current_teaching_class", current_teaching_class);
        params.put("qualification_degree", qualification_degree);
        params.put("qualification_major_subject", qualification_major_subject);
        params.put("year_of_experience", year_of_experience);



        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createteacherprofile, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    String created = response.getString("message");

                    if (created.equals("Success")){
                        Toast.makeText(Signup_SetupPassword_Activity.this, "Profile Created", Toast.LENGTH_SHORT).show();

                        if (newaccount!=null){
                            startActivity(new Intent(Signup_SetupPassword_Activity.this, SettingActivity.class));
                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Signup_SetupPassword_Activity.this, Login_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_SetupPassword_Activity.this).add(jsonRequest);

    }





}