package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.Dasboard_Activity;
import com.starkwiz.starkwiz.Activities.HubListsActivity;
import com.starkwiz.starkwiz.Activities.Starting_Pages.WelcomeActivity;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import in.aabhasjindal.otptextview.OtpTextView;

public class Signup_Personal_Activity extends AppCompatActivity {

    Button btn_signup_personal;
    String Student_intent,Parent_intent,Teacher_intent,Hub_intent,Gender,newaccount,
            FirstName,LastName,Dob,PhoneNo,date,User_ID,token,verificationkey;
    RadioButton check_gender_male,check_gender_female;
    EditText et_personal_firstname,et_personal_lastname,et_personal_phnno;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "MainActivity";
    TextView et_personal_dob;
    ArrayList<Login_ModelClass>login_modelClasses;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__personal_);

        Initialize();

        try {
            Student_intent = getIntent().getExtras().getString("Student_intent");
            Parent_intent = getIntent().getExtras().getString("Parent_intent");
            Teacher_intent = getIntent().getExtras().getString("Teacher_intent");
            Hub_intent = getIntent().getExtras().getString("Hub_intent");
            newaccount = getIntent().getExtras().getString("newaccount");

            et_personal_dob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            Signup_Personal_Activity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year,month,day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);

                     date = year + "/" + "0"+month + "/" + day;
                    et_personal_dob.setText(day + "/" + month + "/" + year);
                }
            };

            et_personal_phnno.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    if (et_personal_phnno.getText().toString().trim().length()==10 &&
                        et_personal_firstname.getText().toString()!=null &&
                        et_personal_lastname.getText().toString()!=null &&
                        et_personal_dob.getText().toString()!=null &&
                        et_personal_phnno.getText().toString()!=null){
                        et_personal_phnno.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24, 0);
                        btn_signup_personal.setBackgroundResource(R.drawable.rounded_button);
                        btn_signup_personal.setEnabled(true);
                    }
                    else {
                        btn_signup_personal.setBackgroundResource(R.drawable.round_textview);
                        btn_signup_personal.setEnabled(false);
                    }
                }
            });





            btn_signup_personal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FirstName = et_personal_firstname.getText().toString().trim();
                    LastName =  et_personal_lastname.getText().toString().trim();
                    PhoneNo =   "+91"+et_personal_phnno.getText().toString().trim();
                    Dob=        et_personal_dob.getText().toString().trim();

                    if (check_gender_male.isChecked()){
                        Gender = "male";
                        Log.d("gender",Gender);
                    }else {
                        Gender = "female";
                        Log.d("gender",Gender);
                    }

                    if (FirstName.isEmpty()){
                        et_personal_firstname.setError("Please Enter First Name !");
                    }else if (LastName.isEmpty()){
                        et_personal_lastname.setError("Please Enter Last Name !");
                    }else if (Dob.isEmpty()){
                        et_personal_dob.setError("Please Enter Your Date Of Birth !");
                    }else if (PhoneNo.isEmpty() || PhoneNo.length()>13 || PhoneNo.length()<13){
                        et_personal_phnno.setError("Please Enter Valid Number");
                    }else if (Gender.isEmpty()){
                        Toast.makeText(Signup_Personal_Activity.this, "Please Click On Gender", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        btn_signup_personal.setEnabled(true);
                        et_personal_phnno.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24, 0);
                        SendOTPLogin(PhoneNo);

                        final Dialog dialog = new Dialog(Signup_Personal_Activity.this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        dialog.setContentView(R.layout.alert_otp_page);
                        dialog.setTitle("Title");



                        Button btn_alert_otpdone, btn_alert_editphnno;
                        TextView txt_otptimer,txtresendotp;
                        OtpTextView otp_view;
                        btn_alert_otpdone = dialog.findViewById(R.id.btn_alert_otpdone);
                        btn_alert_editphnno = dialog.findViewById(R.id.btn_alert_editphnno);
                        txt_otptimer = dialog.findViewById(R.id.txt_otptimer);
                        txtresendotp = dialog.findViewById(R.id.txtresendotp);
                        otp_view = dialog.findViewById(R.id.otp_view);


                        txtresendotp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new CountDownTimer(60000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        txtresendotp.setEnabled(false);
                                        String v = String.format("%02d", millisUntilFinished / 60000);
                                        int va = (int) ((millisUntilFinished % 60000) / 1000);
                                        txt_otptimer.setText(v + ":" + String.format("%02d", va));
                                    }

                                    @Override
                                    public void onFinish() {
                                        txt_otptimer.setText("Click on resend otp");
                                        txtresendotp.setEnabled(true);
                                    }
                                }.start();
                                SendOTPLogin(PhoneNo);
                            }
                        });


                        new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                txtresendotp.setEnabled(false);
                                String v = String.format("%02d", millisUntilFinished / 60000);
                                int va = (int) ((millisUntilFinished % 60000) / 1000);
                                txt_otptimer.setText(v + ":" + String.format("%02d", va));

                            }

                            @Override
                            public void onFinish() {
                                txt_otptimer.setText("Click on resend otp");
                                txtresendotp.setEnabled(true);
                            }
                        }.start();

                        btn_alert_editphnno.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });

                        btn_alert_otpdone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String Number = otp_view.getOTP();

                                if (Number!=null){
                                    btn_alert_otpdone.setEnabled(true);
                                }else {
                                    AlertBoxClasses.SimpleAlertBox(Signup_Personal_Activity.this,"Enter OTP");
                                    btn_alert_otpdone.setEnabled(true);
                                }

                                ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
                                progressDialog.setMessage("Loading...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                //HttpsTrustManager.allowAllSSL();


                                final Map<String, String> params = new HashMap();

                                params.put("mobile_number", PhoneNo);
                                params.put("otp_code", Number);

                                JSONObject parameters = new JSONObject(params);

                                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.VerifyOTP, parameters, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        progressDialog.dismiss();

                                        try {
                                            if (response.getString("success").matches("OTP has been verified")) {


                                                if (Student_intent != null && Parent_intent == null && Teacher_intent == null && Hub_intent == null) {
                                                    SharedPreferences sp = getSharedPreferences("key", 0);
                                                    SharedPreferences.Editor sedt = sp.edit();
                                                    sedt.putString("type", Student_intent);
                                                    sedt.commit();
                                                    Intent StudentIntent = new Intent(Signup_Personal_Activity.this, Sigup_Studying_Activity.class);
                                                    StudentIntent.putExtra( "FirstName", FirstName);
                                                    StudentIntent.putExtra("LastName", LastName);
                                                    StudentIntent.putExtra("Dob", date);
                                                    StudentIntent.putExtra("PhoneNo", PhoneNo);
                                                    StudentIntent.putExtra("Gender", Gender);
                                                    StudentIntent.putExtra("Role", "Student");
                                                    StudentIntent.putExtra("newaccount", newaccount);
                                                    startActivity(StudentIntent);
                                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                                } else if (Student_intent == null && Parent_intent != null && Teacher_intent == null && Hub_intent == null) {
                                                    SharedPreferences sp = getSharedPreferences("key", 0);
                                                    SharedPreferences.Editor sedt = sp.edit();
                                                    sedt.putString("type", Parent_intent);
                                                    sedt.commit();
                                                    Intent StudentIntent = new Intent(Signup_Personal_Activity.this, Signup_Parent_AboutYou_Activity.class);
                                                    StudentIntent.putExtra( "FirstName", FirstName);
                                                    StudentIntent.putExtra("LastName", LastName);
                                                    StudentIntent.putExtra("Dob", date);
                                                    StudentIntent.putExtra("PhoneNo", PhoneNo);
                                                    StudentIntent.putExtra("Gender", Gender);
                                                    StudentIntent.putExtra("Role", "Parent");
                                                    StudentIntent.putExtra("newaccount", newaccount);
                                                    startActivity(StudentIntent);
                                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                                } else if (Student_intent == null && Parent_intent == null && Teacher_intent != null && Hub_intent == null) {
                                                    SharedPreferences sp = getSharedPreferences("key", 0);
                                                    SharedPreferences.Editor sedt = sp.edit();
                                                    sedt.putString("type", Teacher_intent);
                                                    sedt.commit();
                                                    Intent StudentIntent = new Intent(Signup_Personal_Activity.this, Signup_teaching_Activity.class);
                                                    StudentIntent.putExtra( "FirstName", FirstName);
                                                    StudentIntent.putExtra("LastName", LastName);
                                                    StudentIntent.putExtra("Dob", date);
                                                    StudentIntent.putExtra("PhoneNo", PhoneNo);
                                                    StudentIntent.putExtra("Gender", Gender);
                                                    StudentIntent.putExtra("Role", "Teacher");
                                                    StudentIntent.putExtra("newaccount", newaccount);
                                                    startActivity(StudentIntent);
                                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                                } else {
                                                    SharedPreferences sp = getSharedPreferences("key", 0);
                                                    SharedPreferences.Editor sedt = sp.edit();
                                                    sedt.putString("type", Hub_intent);
                                                    sedt.commit();
                                                    Intent StudentIntent = new Intent(Signup_Personal_Activity.this, HubListsActivity.class);
                                                    StudentIntent.putExtra( "FirstName", FirstName);
                                                    StudentIntent.putExtra("LastName", LastName);
                                                    StudentIntent.putExtra("Dob", date);
                                                    StudentIntent.putExtra("PhoneNo", PhoneNo);
                                                    StudentIntent.putExtra("Gender", Gender);
                                                    StudentIntent.putExtra("Role", "Hub");
                                                    StudentIntent.putExtra("newaccount", newaccount);
                                                    startActivity(StudentIntent);
                                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                                }
                                            }else {
                                                AlertBoxClasses.SimpleAlertBox(Signup_Personal_Activity.this,"Wrong OTP Entered");
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

                                        Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


                            }
                        });


                        dialog.show();
                        Window window = dialog.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

//        txt_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(Signup_Personal_Activity.this);
//                dialog.setContentView(R.layout.alert_add_account);
//
//                Window window = dialog.getWindow();
//                dialog.show();
//
//                EditText et_login_user,et_password;
//                Button btn_create_account,btn_login,btn_alert_cancel;
//                TextView txt_forgotpassword;
//
//
//                et_login_user = dialog.findViewById(R.id.et_login_user);
//                et_password = dialog.findViewById(R.id.et_password);
//                btn_create_account = dialog.findViewById(R.id.btn_create_account);
//                btn_login = dialog.findViewById(R.id.btn_login);
//                btn_alert_cancel = dialog.findViewById(R.id.btn_alert_cancel);
//                txt_forgotpassword = dialog.findViewById(R.id.txt_forgotpassword);
//
//                txt_forgotpassword.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final Dialog dialog = new Dialog(Signup_Personal_Activity.this);
//                        dialog.setContentView(R.layout.alert_forgot_passwprd);
//                        Window window = dialog.getWindow();
//                        dialog.show();
//                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//                        EditText et_email,et_new_password,et_reenterpassword,et_otp;
//                        Button btn_change_pswd_confirm,btn_change_pswd_cancel,btn_change_pswd_otp;
//                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                        LinearLayout txt_otp;
//
//                        et_email = dialog.findViewById(R.id.et_email);
//                        et_new_password = dialog.findViewById(R.id.et_new_password);
//                        et_reenterpassword = dialog.findViewById(R.id.et_reenterpassword);
//                        btn_change_pswd_confirm = dialog.findViewById(R.id.btn_change_pswd_confirm);
//                        btn_change_pswd_cancel = dialog.findViewById(R.id.btn_change_pswd_cancel);
//                        txt_otp = dialog.findViewById(R.id.txt_otp);
//                        et_otp = dialog.findViewById(R.id.et_otp);
//                        btn_change_pswd_otp = dialog.findViewById(R.id.btn_change_pswd_otp);
//
//                        et_email.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable editable) {
//
//                                String Email = et_email.getText().toString().trim();
//                                if (!Email.isEmpty() || Email.matches(emailPattern)) {
//                                    btn_change_pswd_otp.setVisibility(View.VISIBLE);
//
//                                } else {
//
//                                    btn_change_pswd_otp.setVisibility(View.GONE);
//
//                                }
//                            }
//                        });
//
//                        btn_change_pswd_otp.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                 Email = et_email.getText().toString().trim();
//                                if (Email.isEmpty() || !Email.matches(emailPattern)){
//                                    et_email.setError("Please Enter Valid Email");
//                                }else {
//
//                                    Forgot_Password_Email(Email, txt_otp, et_otp);
//                                }
//                            }
//                        });
//
//
//
//                        et_otp.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable editable) {
//
//                                String otp = et_otp.getText().toString().trim();
//
//                                if (otp.length()<6 && !otp.equals(token)){
//                                    Toast.makeText(Signup_Personal_Activity.this, "Enter valid otp", Toast.LENGTH_SHORT).show();
//                                }
//                                else {
//                                    et_new_password.setEnabled(true);
//                                    et_reenterpassword.setEnabled(true);
//                                    Forgot_Password_OTP(Email,btn_change_pswd_otp,et_new_password);
//                                }
//                            }
//                        });
//
//
//                        btn_change_pswd_confirm.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                String NewPassword = et_new_password.getText().toString().trim();
//                                String ConfirmPassword = et_reenterpassword.getText().toString().trim();
//
//                                if (NewPassword.equals(ConfirmPassword)){
//                                    Forgot_Password(Email,NewPassword);
//                                }else {
//                                    AlertBoxClasses.SimpleAlertBox(Signup_Personal_Activity.this,"Password not matched");
//                                }
//                            }
//                        });
//
//                        btn_change_pswd_cancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                            }
//                        });
//
//
//
//
//                    }
//                });
//
//
//                btn_login.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String Login_user,Password;
//                        Login_user = et_login_user.getText().toString().trim();
//                        Password = et_password.getText().toString().trim();
//                        if (Login_user.isEmpty()){
//                            et_login_user.setError("Please Enter your Email/Phone Number");
//                        }else if (Password.isEmpty()){
//                            et_password.setError("Please Enter Password");
//                        }
//                        else {
//                            Login(Login_user,Password);
//                        }
//                    }
//                });
//
//                btn_alert_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.cancel();
//                    }
//                });
//
//                btn_create_account.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.cancel();
//                    }
//                });
//
//
//                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            }
//        });

    }

    private void SendOTP(String Number){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
        progressDialog.setMessage("Sending Otp Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("mobile_number", Number);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.SendOTP, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                Toast.makeText(Signup_Personal_Activity.this, "Otp has sent to this "+Number, Toast.LENGTH_SHORT).show();


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


    }

    private void SendOTPLogin(String Number){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
        progressDialog.setMessage("Sending Otp Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("mobile_number", Number);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.sendOTPlogin, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    if (!response.getString("error").equals("mobilenumber not register")){
                        AlertBoxClasses.SimpleAlertBox(Signup_Personal_Activity.this,"Number is already exists");
                        et_personal_phnno.setError("Number Exists");
                        btn_signup_personal.setEnabled(false);


                    }else {
                        SendOTP(Number);
                        Toast.makeText(Signup_Personal_Activity.this, "Otp has sent to this "+Number, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertBoxClasses.SimpleAlertBox(Signup_Personal_Activity.this,"Number is already exists");
                    et_personal_phnno.setError("Number Exists");
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


    }

    private void Login(String Email,String Password){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
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
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(modelClass);
                    Toast.makeText(Signup_Personal_Activity.this, "Welcome "+obj.getString("first_name")+" "+obj.getString("last_name"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup_Personal_Activity.this, Dasboard_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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

                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Signup_Personal_Activity.this)
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

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


    }

    public void activate(String UserId){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
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

                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Signup_Personal_Activity.this)
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

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);

    }

    private void Forgot_Password_Email(String Email,View view,View Editview){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
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
                        Toast.makeText(Signup_Personal_Activity.this, "Otp has sent to "+object.getString("email"), Toast.LENGTH_SHORT).show();
                        token=object.getString("token");
                        view.setVisibility(View.VISIBLE);
                        Editview.setFocusable(true);
                    }else {

                        Toast.makeText(Signup_Personal_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


    }

    private void Forgot_Password_OTP(String Email,View view,View newview){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
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
                        Toast.makeText(Signup_Personal_Activity.this, "Enter new password", Toast.LENGTH_SHORT).show();


                    }else {

                        Toast.makeText(Signup_Personal_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


    }

    private void Forgot_Password(String Email,String Password){

        ProgressDialog progressDialog = new ProgressDialog(Signup_Personal_Activity.this);
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
                        AlertBoxClasses.SimpleAlertBox(Signup_Personal_Activity.this,"Password Updated");

                    }else {

                        Toast.makeText(Signup_Personal_Activity.this, "Try Again", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Signup_Personal_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Signup_Personal_Activity.this).add(jsonRequest);


    }

    private void Initialize() {

        btn_signup_personal =findViewById(R.id.btn_signup_personal);
        check_gender_male =findViewById(R.id.check_gender_male);
        check_gender_female =findViewById(R.id.check_gender_female);
        et_personal_firstname =findViewById(R.id.et_personal_firstname);
        et_personal_lastname =findViewById(R.id.et_personal_lastname);
        et_personal_dob =findViewById(R.id.et_personal_dob);
        et_personal_phnno =findViewById(R.id.et_personal_phnno);
        //txt_signin =findViewById(R.id.txt_signin);
        login_modelClasses=new ArrayList<>();
//        btn_signup_personal.setEnabled(false);
        check_gender_male.setChecked(true);


    }
    @Override
    protected void onStart() {
        super.onStart();

        try {
            String strtext =SharedPrefManager.getInstance(Signup_Personal_Activity.this).getUser().getAccess_token();
            Log.d("accesstoken",strtext);

            if (newaccount.equals("newaccount")){
                startActivity(new Intent(Signup_Personal_Activity.this,Signup_Personal_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }

           else if (!strtext.equals("")){

                startActivity(new Intent(Signup_Personal_Activity.this,Dasboard_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

           startActivity(new Intent(Signup_Personal_Activity.this, WelcomeActivity.class));
           overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }
}