package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {

    TextView txtsetting_account,txtsetting_notification,txtsetting_privacyanddata,txtsetting_plandetail,txt_username,
            txt_setting_email,txt_setting_changepassword,txt_changeregion,txt_deactivate_account,txt_closeaccount,
            txtsetting_supportnotification,txtsetting_supportprivacy,txt_disconnect_knowmore,txtsetting_logout;
    LinearLayout linear_setting_account,linear_setting_notification,linear_setting_privacydata,linear_setting_plandetails,linear_switch_account;
    String UserId,Email_Id,NewEmail,Password,OldPassword,NewPassword,ConfirmPassword,strtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        txtsetting_account = findViewById(R.id.txtsetting_account);
        txtsetting_notification = findViewById(R.id.txtsetting_notification);
        txtsetting_privacyanddata = findViewById(R.id.txtsetting_privacyanddata);
        txtsetting_plandetail = findViewById(R.id.txtsetting_plandetail);
        linear_setting_account = findViewById(R.id.linear_setting_account);
        linear_setting_notification = findViewById(R.id.linear_setting_notification);
        linear_setting_privacydata = findViewById(R.id.linear_setting_privacydata);
        linear_setting_plandetails = findViewById(R.id.linear_setting_plandetails);
        txt_setting_email = findViewById(R.id.txt_setting_email);
        txt_setting_changepassword = findViewById(R.id.txt_setting_changepassword);
        txt_changeregion = findViewById(R.id.txt_changeregion);
        txt_deactivate_account = findViewById(R.id.txt_deactivate_account);
        txt_closeaccount = findViewById(R.id.txt_closeaccount);
        txtsetting_supportnotification = findViewById(R.id.txtsetting_supportnotification);
        txtsetting_supportprivacy = findViewById(R.id.txtsetting_supportprivacy);
        linear_switch_account = findViewById(R.id.linear_switch_account);
        txt_disconnect_knowmore = findViewById(R.id.txt_disconnect_knowmore);
        txtsetting_logout = findViewById(R.id.txtsetting_logout);
        txt_username = findViewById(R.id.txt_username);

        UserId = SharedPrefManager.getInstance(SettingActivity.this).getUser().getId();
        Email_Id = SharedPrefManager.getInstance(SettingActivity.this).getUser().getEmail();


        txtsetting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this)
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPrefManager.getInstance(SettingActivity.this).logout();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert11 = alertDialog.create();
                alert11.show();

            }
        });

        txtsetting_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (linear_setting_account.getVisibility()==View.VISIBLE){
                    linear_setting_account.setVisibility(View.GONE);
                }else {
                    linear_setting_account.setVisibility(View.VISIBLE);
                }
            }
        });

        txtsetting_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (linear_setting_notification.getVisibility()==View.VISIBLE){
                    linear_setting_notification.setVisibility(View.GONE);
                }else {
                    linear_setting_notification.setVisibility(View.VISIBLE);
                }
            }
        });

        txtsetting_privacyanddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (linear_setting_privacydata.getVisibility()==View.VISIBLE){
                    linear_setting_privacydata.setVisibility(View.GONE);
                }else {
                    linear_setting_privacydata.setVisibility(View.VISIBLE);
                }
            }
        });

        txtsetting_plandetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (linear_setting_plandetails.getVisibility()==View.VISIBLE){
                    linear_setting_plandetails.setVisibility(View.GONE);
                }else {
                    linear_setting_plandetails.setVisibility(View.VISIBLE);
                }
            }
        });

        txt_setting_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_change_email);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Button btn_change_email_done = dialog.findViewById(R.id.btn_change_email_done);
                EditText et_change_email = dialog.findViewById(R.id.et_change_email);
                EditText et_change_pswd = dialog.findViewById(R.id.et_change_pswd);



                btn_change_email_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NewEmail = et_change_email.getText().toString().trim();
                        Password = et_change_pswd.getText().toString().trim();
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_email_verification);
                        InsetDrawable background =
                                (InsetDrawable) dialog.getWindow().getDecorView().getBackground();
                        background.setAlpha(0);
                        dialog.show();
                        Button btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);

                        btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ChangeEmail(NewEmail,Password);
                            }
                        });
                        Window window = dialog.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        txt_setting_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_change_passwprd);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                EditText et_current_password,et_new_password,et_reenterpassword;
                Button btn_change_email_confirm;
                et_current_password = dialog.findViewById(R.id.et_current_password);
                et_new_password = dialog.findViewById(R.id.et_new_password);
                et_reenterpassword = dialog.findViewById(R.id.et_reenterpassword);
                btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);

                btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OldPassword = et_current_password.getText().toString().trim();
                        NewPassword = et_new_password.getText().toString().trim();
                        ConfirmPassword = et_reenterpassword.getText().toString().trim();

                        if (NewPassword.equals(ConfirmPassword)){
                            ChangePassword(OldPassword,NewPassword);
                        }else {
                            AlertBoxClasses.SimpleAlertBox(SettingActivity.this,"Password didn't match");
                        }


                    }
                });


                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        txt_changeregion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_change_region);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        txt_deactivate_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_deactivate_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Button btn_deactive = (Button)dialog.findViewById(R.id.btn_deactive);

                btn_deactive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Deactivate(UserId);
                    }
                });

                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        txt_closeaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_change_close_account);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        txtsetting_supportnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_notification_setting);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Window window = dialog.getWindow();
               window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        txtsetting_supportprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_privacy_data_setting);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Button btn_alert_clearcache = dialog.findViewById(R.id.btn_alert_clearcache);
                btn_alert_clearcache.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_clear_cache);
                        dialog.show();
                        Window window = dialog.getWindow();
                       window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
               window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        linear_switch_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_switch_profile);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                TextView txt_disconnect_knowmore = dialog.findViewById(R.id.txt_disconnect_knowmore);
                Button btn_switch_account = dialog.findViewById(R.id.btn_switch_account);
                txt_disconnect_knowmore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_disconnet_knowmore);
                        dialog.show();
                        Window window = dialog.getWindow();
                       window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                });

                btn_switch_account.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_add_account);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        Window window = dialog.getWindow();
                       window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
               window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });
    }

    public void Deactivate(String UserId){

        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
        progressDialog.setMessage("Signing In..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("id", UserId);
        params.put("active_status", "deactive");

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updatestatus, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String msg = response.getString("0");

                    if (msg.equals("success")){

                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this)
                                .setMessage("Your account is Deactived.")
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        dialogInterface.cancel();
                                        SharedPrefManager.getInstance(SettingActivity.this).logout();
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

                Toast.makeText(SettingActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(SettingActivity.this).add(jsonRequest);

    }


    public void Close(String UserId){

        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
        progressDialog.setMessage("Signing In..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("id", UserId);
        params.put("active_status", "close");

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updatestatus, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String msg = response.getString("0");

                    if (msg.equals("success")){

                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this)
                                .setMessage("Your account is Deactived.")
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        dialogInterface.cancel();
                                        SharedPrefManager.getInstance(SettingActivity.this).logout();
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

                Toast.makeText(SettingActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(SettingActivity.this).add(jsonRequest);

    }

    public void ChangeEmail(String NewEmail, String Password){

        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
        progressDialog.setMessage("Signing In..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("oldemail", Email_Id);
        params.put("newemail", NewEmail);
        params.put("password", Password);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updateemail, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String msg = response.getString("message");

                    if (msg.equals("Password updated")){

                        Toast.makeText(SettingActivity.this, "Email Changed Successfully.", Toast.LENGTH_SHORT).show();

                         SharedPrefManager.getInstance(SettingActivity.this).logout();

                    }else if (msg.equals("Incorrect Password")){
                        Toast.makeText(SettingActivity.this, "Incorrect Password.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SettingActivity.this, "Email already exists.", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(SettingActivity.this).add(jsonRequest);
    }

    public void ChangePassword(String oldpassword, String newpassword){

        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
        progressDialog.setMessage("Signing In..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("email", Email_Id);
        params.put("oldpassword", oldpassword);
        params.put("newpassword", newpassword);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updatepassword, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String msg = response.getString("message");

                    if (msg.equals("Password updated")){

                        Toast.makeText(SettingActivity.this, "Password Changed Successfully.", Toast.LENGTH_SHORT).show();

                        SharedPrefManager.getInstance(SettingActivity.this).logout();

                    }else if (msg.equals("Incorrect Password")){
                        Toast.makeText(SettingActivity.this, "Please check and try again.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SettingActivity.this, "Email already exists.", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(SettingActivity.this).add(jsonRequest);
    }

    private void GetProfile(){
        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        strtext = SharedPrefManager.getInstance(SettingActivity.this).getUser().getId();


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.Getprofile+strtext, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String Status=response.getString("success");

                    if (Status.matches("profile found")){

                        String allProfile=response.getString("allProfile");
                        JSONArray array = new JSONArray(allProfile);

                        for (int i = 0 ; i<array.length();i++){
                            JSONObject object = array.getJSONObject(i);

                            txt_setting_email.setText(object.getString("email"));
                            txt_username.setText(object.getString("first_name")+" "+object.getString("last_name"));

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

                Toast.makeText(SettingActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(SettingActivity.this).add(jsonRequest);

    }

    @Override
    protected void onStart() {
        super.onStart();
        GetProfile();

    }
}