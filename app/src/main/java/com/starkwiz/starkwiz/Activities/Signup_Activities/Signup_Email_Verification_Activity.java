package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup_Email_Verification_Activity extends AppCompatActivity {

    Button btn_verfication_proceed,btn_cnfrm_email;
    String FirstName,LastName,Dob,PhoneNo,Gender,State,City,District,SchoolName,kids,Experience,
            Class,Board,Email,Role,BlockNo,isvalid,newaccount,Qualification,Profession,
            Hub_Name,Hub_Address,Hub_YearOfEstablishment,Hub_type;
    EditText etemail,et_token;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView txt_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__email__verification_);

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
            Role = getIntent().getStringExtra("Role");
            Board = getIntent().getStringExtra("Board");
            BlockNo = getIntent().getStringExtra("BlockNo");
            newaccount = getIntent().getStringExtra("newaccount");
            Qualification = getIntent().getStringExtra("Qualification");
            Profession = getIntent().getStringExtra("Profession");
            kids = getIntent().getStringExtra("kids");
            Experience = getIntent().getStringExtra("Experience");
            Hub_Name = getIntent().getStringExtra("Hub_Name");
            Hub_Address = getIntent().getStringExtra("Hub_Address");
            Hub_YearOfEstablishment = getIntent().getStringExtra("Hub_YearOfEstablishment");
            Hub_type = getIntent().getStringExtra("Hub_type");


        }catch (Exception e){
            e.printStackTrace();
        }

        btn_verfication_proceed = findViewById(R.id.btn_verfication_proceed);
        btn_cnfrm_email = findViewById(R.id.btn_cnfrm_email);
        etemail = findViewById(R.id.etemail);
        et_token = findViewById(R.id.et_token);
        txt_text = findViewById(R.id.txt_text);

        String text = "<font color=#000000>We will send an email with a verification \n code on the above address. Please</font> <font color=#88D5F0>Confirm</font> <font color=#000000>and be patient for its neccessary for a </font><font color=#88D5F0>Safe</font><font color=#000000> user experience</font>";

        txt_text.setText(Html.fromHtml(text));

        etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (etemail.getText().toString().length()>5){
                    btn_cnfrm_email.setEnabled(true);
                }else {
                    btn_verfication_proceed.setEnabled(false);
                }
            }
        });

        et_token.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if (et_token.getText().toString().trim().length()>0){
                    btn_verfication_proceed.setEnabled(true);
                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.rounded_button));
                }else {
                    btn_verfication_proceed.setEnabled(false);
                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.round_textview));
                }
            }
        });


        btn_cnfrm_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etemail.getText().toString().trim()!=null && et_token.getText().toString().trim()!=null){

                    CheckEmail(etemail.getText().toString().trim());

                }else {
                    AlertBoxClasses.SimpleAlertBox(Signup_Email_Verification_Activity.this,"Check all fields.");
                }

            }
        });

        btn_verfication_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email = etemail.getText().toString().trim();

                if (Email.isEmpty() || !Email.matches(emailPattern)){
                    etemail.setError("Please Enter Valid Email");
                }else {

                    VerifyOtpEmail(etemail.getText().toString().trim(),et_token.getText().toString().trim(),isvalid);

                }
            }
        });
    }

    public void SendOtpEmail(String Email){

        ProgressDialog dialog = new ProgressDialog(Signup_Email_Verification_Activity.this);
        dialog.setMessage("Loading...");
        dialog.show();
        final Map<String, String> params = new HashMap();

        params.put("email", Email);
        params.put("name", FirstName);


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.send_mail_trap, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            dialog.dismiss();

                try {
                    String storetoken = response.getString("storetoken");

                    JSONObject object = new JSONObject(storetoken);

                    isvalid = object.getString("is_valid");

                    if (isvalid.equals("YES")){

                        AlertBoxClasses.SimpleAlertBox(Signup_Email_Verification_Activity.this,"Otp has been sent to "+object.getString("email"));
                    }else {
                        Toast.makeText(Signup_Email_Verification_Activity.this, "Try again", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(Signup_Email_Verification_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


        Volley.newRequestQueue(Signup_Email_Verification_Activity.this).add(jsonRequest);

    }

    public void VerifyOtpEmail(String Email,String token,String isvalid){

        ProgressDialog dialog = new ProgressDialog(Signup_Email_Verification_Activity.this);
        dialog.setMessage("Loading...");
        dialog.show();
        final Map<String, String> params = new HashMap();

        params.put("email", Email);
        params.put("token", token);
        params.put("is_valid", isvalid);


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.verifyemail, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();

                try {
                    String message = response.getString("message");

                    if (message.equals("Email Verified")){

                        Intent intent = new Intent(Signup_Email_Verification_Activity.this, Signup_SetupPassword_Activity.class);
                        intent.putExtra("FirstName",FirstName);
                        intent.putExtra("LastName",LastName);
                        intent.putExtra("Dob",Dob);
                        intent.putExtra("PhoneNo",PhoneNo);
                        intent.putExtra("Gender",Gender);
                        intent.putExtra("State",State);
                        intent.putExtra("City",City);
                        intent.putExtra("District",District);
                        intent.putExtra("SchoolName",SchoolName);
                        intent.putExtra("Class",Class);
                        intent.putExtra("Board",Board);
                        intent.putExtra("Email",Email);
                        intent.putExtra("Role",Role);
                        intent.putExtra("BlockNo",BlockNo);
                        intent.putExtra("newaccount",newaccount);
                        intent.putExtra("Qualification",Qualification);
                        intent.putExtra("Profession",Profession);
                        intent.putExtra("kids",kids);
                        intent.putExtra("Experience",Experience);
                        intent.putExtra("Hub_Name",Hub_Name);
                        intent.putExtra("Hub_Address",Hub_Address);
                        intent.putExtra("Hub_YearOfEstablishment",Hub_YearOfEstablishment);
                        intent.putExtra("Hub_type",Hub_type);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }
                    else {
                        Toast.makeText(Signup_Email_Verification_Activity.this, "Check again", Toast.LENGTH_SHORT).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(Signup_Email_Verification_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


        Volley.newRequestQueue(Signup_Email_Verification_Activity.this).add(jsonRequest);

    }

    private void CheckEmail(String Email){

        ProgressDialog dialog = new ProgressDialog(Signup_Email_Verification_Activity.this);
        dialog.setMessage("Checking Mobile Number...");
        dialog.setCancelable(false);
        dialog.show();
        final Map<String, String> params = new HashMap();


        params.put("email", Email);



        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.checkemail, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();

                try {
                    String email = response.getString("email");

                    if (email.equals("1")){
                        AlertBoxClasses.SimpleAlertBox(Signup_Email_Verification_Activity.this,"Email already exists,\nPlease try another Emaild id");
                    }else {
                        SendOtpEmail(etemail.getText().toString().trim());
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();


                AlertBoxClasses.SimpleAlertBox(Signup_Email_Verification_Activity.this,"Try Again");

            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Email_Verification_Activity.this).add(jsonRequest);

    }
}