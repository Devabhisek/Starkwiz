package com.starkwiz.starkwiz.Activities.Starting_Pages;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.Dasboard_Activity;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    CheckBox chk_keepsignin;
    TextView txt_notamember,txt_forgot_password;
    ArrayList<Login_ModelClass>login_modelClasses;
    String User_ID,token,verificationkey,Email,Student_intent,Parent_intent,Teacher_intent,Hub_intent;
    Button btn_signup_personal,btn_signup;
    EditText et_personal_firstname,et_personal_lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        chk_keepsignin=findViewById(R.id.chk_keepsignin);
        txt_notamember=findViewById(R.id.txt_notamember);
        btn_signup_personal=findViewById(R.id.btn_signup_personal);
        et_personal_firstname=findViewById(R.id.et_personal_firstname);
        et_personal_lastname=findViewById(R.id.et_personal_lastname);
        btn_signup=findViewById(R.id.btn_signup);
        txt_forgot_password=findViewById(R.id.txt_forgot_password);
        login_modelClasses = new ArrayList<>();

//        et_personal_lastname.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        et_personal_lastname.setSelection(et_personal_lastname.getText().length());


        String text = "<font color=#000000>Keep me </font> <font color=#88D5F0>signed in </font><font color=#000000>with <br> this device</font> ";

        chk_keepsignin.setText(Html.fromHtml(text));

        String text_notmember = "<font color=#000000>Not a member yet? </font> <font color=#88D5F0>Sign up </font><font color=#000000>here !</font>";

        txt_notamember.setText(Html.fromHtml(text_notmember));

        et_personal_lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s!=null ){

                    btn_signup_personal.setBackgroundResource(R.drawable.round_textview_clicked);
                    btn_signup_personal.setEnabled(true);
                }else {

                    btn_signup_personal.setBackgroundResource(R.drawable.round_textview);
                    btn_signup_personal.setEnabled(false);

                }

            }
        });

        btn_signup_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chk_keepsignin.isChecked()){
                    Login(et_personal_firstname.getText().toString(),et_personal_lastname.getText().toString(),chk_keepsignin);
                }else {
                   AlertBoxClasses.SimpleAlertBox(Login_Activity.this,"Please check on the Keep me signed in");
                }

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,UserSelection_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Login_Activity.this);
                        dialog.setContentView(R.layout.alert_forgot_passwprd);
                        Window window = dialog.getWindow();
                        dialog.show();
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        EditText et_email,et_new_password,et_reenterpassword,et_otp;
                        Button btn_change_pswd_confirm,btn_change_pswd_cancel,btn_change_pswd_otp;
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        LinearLayout txt_otp;

                        et_email = dialog.findViewById(R.id.et_email);
                        et_new_password = dialog.findViewById(R.id.et_new_password);
                        et_reenterpassword = dialog.findViewById(R.id.et_reenterpassword);
                        btn_change_pswd_confirm = dialog.findViewById(R.id.btn_change_pswd_confirm);
                        btn_change_pswd_cancel = dialog.findViewById(R.id.btn_change_pswd_cancel);
                        txt_otp = dialog.findViewById(R.id.txt_otp);
                        et_otp = dialog.findViewById(R.id.et_otp);
                        btn_change_pswd_otp = dialog.findViewById(R.id.btn_change_pswd_otp);



                        et_email.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                                String Email = et_email.getText().toString().trim();
                                if (!Email.isEmpty() || Email.matches(emailPattern)) {
                                    btn_change_pswd_otp.setVisibility(View.VISIBLE);

                                } else {

                                    btn_change_pswd_otp.setVisibility(View.GONE);

                                }
                            }
                        });

                        btn_change_pswd_otp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                 Email = et_email.getText().toString().trim();
                                if (Email.isEmpty() || !Email.matches(emailPattern)){
                                    et_email.setError("Please Enter Valid Email");
                                }else {

                                    Forgot_Password_Email(Email, txt_otp, et_otp);
                                }
                            }
                        });



                        et_otp.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                                String otp = et_otp.getText().toString().trim();

                                if (otp.length()<6 && !otp.equals(token)){
                                    Toast.makeText(Login_Activity.this, "Enter valid otp", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    et_new_password.setEnabled(true);
                                    et_reenterpassword.setEnabled(true);
                                    Forgot_Password_OTP(Email,btn_change_pswd_otp,et_new_password);
                                }
                            }
                        });


                        btn_change_pswd_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String NewPassword = et_new_password.getText().toString().trim();
                                String ConfirmPassword = et_reenterpassword.getText().toString().trim();

                                if (NewPassword.equals(ConfirmPassword)){
                                    Forgot_Password(Email,NewPassword);
                                }else {
                                    AlertBoxClasses.SimpleAlertBox(Login_Activity.this,"Password not matched");
                                }
                            }
                        });

                        btn_change_pswd_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

            }
        });


    }

    private void Login(String Email,String Password,CheckBox checkBox){

        ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("Signing In..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("email", Email);
        params.put("password", Password);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.Login, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String token_details = response.getString("token_details");

                    JSONObject object = new JSONObject(token_details);

                    String original = object.getString("original");

                    JSONObject jsonObject = new JSONObject(original);

                    String user = jsonObject.getString("user");

                    JSONObject obj = new JSONObject(user);

                    Login_ModelClass modelClass=new Login_ModelClass(
                            jsonObject.getString("access_token"),
                            obj.getString("id"),
                            obj.getString("first_name"),
                            obj.getString("last_name"),
                            obj.getString("mobile_number"),
                            obj.getString("class"),
                            obj.getString("school_board"),
                            obj.getString("role"),
                            obj.getString("email")
                    );
                    login_modelClasses.add(modelClass);
                    if (checkBox.isChecked()){
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(modelClass);
                        Toast.makeText(Login_Activity.this, "Welcome "+obj.getString("first_name")+" "+obj.getString("last_name"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login_Activity.this, Dasboard_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }else {
                        Intent intent = new Intent(Login_Activity.this, Dasboard_Activity.class);
                        intent.putExtra("role",obj.getString("role"));
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    try {
                        String msg =  response.getString("message");

                        String all_profile = response.getString("all_profile");

                        JSONArray array = new JSONArray(all_profile);

                        for (int i = 0 ; i<array.length();i++){

                            JSONObject object = array.getJSONObject(i);

                            User_ID = object.getString("userid");

                        }

                        if (msg.equals("user is not active")){

                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login_Activity.this)
                                    .setMessage("Your account is Deactived / Closed.\n Would you like to active your account?")
                                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            activate(User_ID);
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog alert11 = alertDialog.create();
                            alert11.show();
                        }
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Login_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Login_Activity.this).add(jsonRequest);


    }

    private void Forgot_Password_Email(String Email, View view, View Editview){

        ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("Sending Otp Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("email", Email);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.send_otp_forgotpassword, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String storetoken = response.getString("storetoken");

                    JSONObject object = new JSONObject(storetoken);

                    String is_valid =object.getString("is_valid");

                    if (is_valid.equals("YES")){
                        Toast.makeText(Login_Activity.this, "Otp has sent to "+object.getString("email"), Toast.LENGTH_SHORT).show();
                        token=object.getString("token");
                        view.setVisibility(View.VISIBLE);
                        Editview.setFocusable(true);
                    }else {

                        Toast.makeText(Login_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Login_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Login_Activity.this).add(jsonRequest);


    }

    private void Forgot_Password_OTP(String Email,View view,View newview){

        ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("Verifying Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("email", Email);
        params.put("token", token);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.verifyemail_forgot_password, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String message = response.getString("message");

                    verificationkey =response.getString("verificationkey");

                    if (message.equals("Email Verified")){
                        view.setVisibility(View.VISIBLE);
                        newview.setFocusable(true);
                        Toast.makeText(Login_Activity.this, "Enter new password", Toast.LENGTH_SHORT).show();


                    }else {

                        Toast.makeText(Login_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Login_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Login_Activity.this).add(jsonRequest);


    }

    private void Forgot_Password(String Email,String Password){

        ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("Updating Password Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("email", Email);
        params.put("verification_key", verificationkey);
        params.put("password", Password);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.forgot_password, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String message = response.getString("message");



                    if (message.equals("password updated")){
                        AlertBoxClasses.SimpleAlertBox(Login_Activity.this,"Password Updated");

                    }else {

                        Toast.makeText(Login_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Login_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Login_Activity.this).add(jsonRequest);


    }

    public void activate(String UserId){

        ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("Signing In..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("id", UserId);
        params.put("active_status", "active");

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updatestatus, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String msg = response.getString("0");

                    if (msg.equals("success")){

                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login_Activity.this)
                                .setMessage("Your account is actived successfully.")
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {


                                        dialogInterface.cancel();

                                    }
                                });
                        AlertDialog alert11 = alertDialog.create();
                        alert11.show();
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

                Toast.makeText(Login_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Login_Activity.this).add(jsonRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Login_Activity.this,WelcomeActivity.class));
    }
}