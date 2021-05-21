package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.gson.Gson;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.Activities.Starting_Pages.UserSelection_Activity;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.CoreSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.ExtraSubjects_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.FeaturedSubjects_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Core_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Core_Subjectbyplans_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Extra_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Featured_Subjectplan_ModelClass;
import com.starkwiz.starkwiz.ModelClass.GetSubjects_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Selected_Subject_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {

    TextView txtsetting_account,txtsetting_notification,txtsetting_privacyanddata,txtsetting_plandetail,txt_username,
            txt_setting_email,txt_setting_changepassword,txt_changeregion,txt_deactivate_account,txt_closeaccount,
            txtsetting_supportnotification,txtsetting_supportprivacy,txt_disconnect_knowmore,txtsetting_logout,
            txt_loginoption,txt_connectedoption,txt_setting_plantype,txt_setting_planyear,txt_setting_planprice,
            txt_setting_permonthprice,txt_plan_update,txtplantype,txtplanprice,txtplanvalidity,txt_perprice,txt_disciuntprice,txt_disciuntpricemonth,
            txtplantype_subject,txtplanprice_subject,txtplanvalidity_subject,txt_perprice_subject,txt_fixturemonth,
            txt_privacy_clearcahce;
    LinearLayout linear_setting_account,linear_setting_notification,linear_setting_privacydata,
            linear_setting_plandetails,linear_switch_account,linear_loginoption,linear_connectedaccount,
            lineartype,basic,linear_basictype,standard,linear_standardtype,premium,lineardisciunt,
            linear_premiumtype,core,extra,feature,linear_coretype,linear_extratype,linear_featuretype;
    String UserId,Email_Id,NewEmail,Password,OldPassword,NewPassword,ConfirmPassword,strtext,image,Name,
    PlanType,PlanYear,PlanPrice,PlanPerMonth,PlanDuration,PlanDuarationType,discounted_price,Cls;
    int PlanPerMonthPrice =0,discount_month;
    ImageView img_profileone;
    RoundedHorizontalProgressBar progress_bar_3;
    ArrayList<Core_Subjectbyplans_ModelClass>list_coresubjects;
    ArrayList<Extra_Subjectplan_ModelClass>list_extrasubjects;
    ArrayList<Featured_Subjectplan_ModelClass>list_featuresubjects;
    ArrayList<Selected_Subject_Modelclass>list_subjects;
    ArrayList<GetSubjects_ModelClass> totalSubjects_modelClasses = new ArrayList<>();
    Button btn_plans,btn_subjectproceed;
    RecyclerView lv_subjectsplan,lv_subjectsplan_extra,lv_subjectsplan_feature;
    ImageView img_cross_subjects;
    CoreSubjects_Adapter adapter;
    ExtraSubjects_Adapter extraSubjects_adapter;
    FeaturedSubjects_Adapter featuredSubjects_adapter;

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
        linear_loginoption = findViewById(R.id.linear_loginoption);
        linear_connectedaccount = findViewById(R.id.linear_connectedaccount);
        txt_loginoption = findViewById(R.id.txt_loginoption);
        txt_connectedoption = findViewById(R.id.txt_connectedoption);
        img_profileone = findViewById(R.id.img_profileone);
        txt_setting_plantype = findViewById(R.id.txt_setting_plantype);
        txt_setting_planyear = findViewById(R.id.txt_setting_planyear);
        txt_setting_planprice = findViewById(R.id.txt_setting_planprice);
        txt_setting_permonthprice = findViewById(R.id.txt_setting_permonthprice);
        txt_plan_update = findViewById(R.id.txt_plan_update);
        txt_privacy_clearcahce = findViewById(R.id.txt_privacy_clearcahce);

        list_coresubjects =new ArrayList<>();
        list_extrasubjects =new ArrayList<>();
        list_featuresubjects =new ArrayList<>();
        list_subjects =new ArrayList<>();

        UserId = SharedPrefManager.getInstance(SettingActivity.this).getUser().getId();
        Email_Id = SharedPrefManager.getInstance(SettingActivity.this).getUser().getEmail();
        Cls = SharedPrefManager.getInstance(SettingActivity.this).getUser().getCls();

        adapter = new CoreSubjects_Adapter(list_coresubjects,SettingActivity.this);
        extraSubjects_adapter = new ExtraSubjects_Adapter(list_extrasubjects,SettingActivity.this);
        featuredSubjects_adapter = new FeaturedSubjects_Adapter(list_featuresubjects,SettingActivity.this);

        GetPlanByUser(UserId);

        txt_loginoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linear_loginoption.getVisibility()==View.VISIBLE){
                    linear_loginoption.setVisibility(View.GONE);
                    txt_loginoption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0);
                }else {
                    linear_loginoption.setVisibility(View.VISIBLE);
                    txt_loginoption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0);
                }
            }
        });

        txt_connectedoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linear_connectedaccount.getVisibility()==View.VISIBLE){
                    linear_connectedaccount.setVisibility(View.GONE);
                    txt_connectedoption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0);
                }else {
                    linear_connectedaccount.setVisibility(View.VISIBLE);
                    txt_connectedoption.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0);
                }
            }
        });


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
                Button btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);
                btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Close(UserId);
                    }
                });
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

               Button btn_change_email_cancel = dialog.findViewById(R.id.btn_change_email_cancel);
               Button btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);

                btn_change_email_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        txtsetting_supportprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_privacy_data_setting);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Button btn_alert_clearcache = dialog.findViewById(R.id.btn_alert_clearcache);
                Button btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);
                Button btn_change_email_cancel = dialog.findViewById(R.id.btn_change_email_cancel);

                btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btn_change_email_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btn_alert_clearcache.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_clear_cache);
                        dialog.show();
                        Button btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);
                        progress_bar_3 = dialog.findViewById(R.id.progress_bar_3);
                        deleteCache(SettingActivity.this);
                        progress_bar_3.animateProgress(2000, 0, 100); // (animationDuration, oldProgress, newProgress)
                        btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
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

        txt_privacy_clearcahce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.alert_clear_cache);
                dialog.show();
                Button btn_change_email_confirm = dialog.findViewById(R.id.btn_change_email_confirm);
                progress_bar_3 = dialog.findViewById(R.id.progress_bar_3);
                deleteCache(SettingActivity.this);
                progress_bar_3.animateProgress(2000, 0, 100);
                // (animationDuration, oldProgress, newProgress)
                btn_change_email_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        linear_switch_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SettingActivity.this, UserSelection_Activity.class);
               intent.putExtra("newaccount","newaccount");
               startActivity(intent);
            }
        });

        txt_plan_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
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
                btn_plans.setEnabled(false);

                img_cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                LayoutInflater inflater = LayoutInflater
                        .from(SettingActivity.this);
                View layview = inflater.inflate(R.layout.custom_basic_plans, null);
                lineartype.addView(layview);

                ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
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
                                            LocalBroadcastManager.getInstance(SettingActivity.this).sendBroadcast(intent);

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
                Volley.newRequestQueue(SettingActivity.this).add(getRequest);

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
                                .from(SettingActivity.this);
                        View layview = inflater.inflate(R.layout.custom_basic_plans, null);
                        lineartype.addView(layview);

                        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
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

                                                if (object.getString("plan_type").equals("Basic")){
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
                        Volley.newRequestQueue(SettingActivity.this).add(getRequest);


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
                                .from(SettingActivity.this);
                        View layview = inflater.inflate(R.layout.custom_standard_plans, null);
                        lineartype.addView(layview);

                        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
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
                        Volley.newRequestQueue(SettingActivity.this).add(getRequest);



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
                                .from(SettingActivity.this);
                        View layview = inflater.inflate(R.layout.custom_premium_plans, null);
                        lineartype.addView(layview);

                        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
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
                        Volley.newRequestQueue(SettingActivity.this).add(getRequest);
                    }
                });


                btn_plans.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_subjectplan);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        TextView txt_disciuntprice,txt_disciuntpricemonth;

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
                        lv_subjectsplan.setLayoutManager(new LinearLayoutManager(SettingActivity.this));
                        lv_subjectsplan_extra.setHasFixedSize(true);
                        lv_subjectsplan_extra.setLayoutManager(new LinearLayoutManager(SettingActivity.this));
                        lv_subjectsplan_feature.setHasFixedSize(true);
                        lv_subjectsplan_feature.setLayoutManager(new LinearLayoutManager(SettingActivity.this));

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

                        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
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
                                Toast.makeText(SettingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        MySingleton.getInstance(SettingActivity.this).addToRequestque(stringRequest);


                        btn_subjectproceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                ArrayList<Core_ModelClass> CoreSubject = adapter.getArrayList_Core();
                                ArrayList<Core_ModelClass> ExtraSubject = extraSubjects_adapter.getExtraArrayList();
                                ArrayList<Core_ModelClass> FeatureSubject = featuredSubjects_adapter.getFeatureArrayList();


                                int total = CoreSubject.size()+ExtraSubject.size()+FeatureSubject.size();



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
                                                UserId);

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
                                                UserId);

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
                                                UserId);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Extra_selectedvalue",list_subjects.toString());

                                    Gson gson = new Gson();
                                    String json = gson.toJson(list_subjects);
                                    Log.d("extra_js",json);

                                    InsertSubjects(json);
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
                                                UserId);

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
                                                UserId);

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
                                                UserId);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Extra_selectedvalue",list_subjects.toString());

                                    Gson gson = new Gson();
                                    String json = gson.toJson(list_subjects);
                                    Log.d("extra_js",json);

                                    InsertSubjects(json);
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
                                                UserId);

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
                                                UserId);

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
                                                UserId);

                                        list_subjects.add(selected_subject_modelclasses);

                                    };

                                    Log.d("Extra_selectedvalue",list_subjects.toString());

                                    Gson gson = new Gson();
                                    String json = gson.toJson(list_subjects);
                                    Log.d("extra_js",json);

                                    InsertSubjects(json);
                                    dialog.dismiss();
                                }

                                else {

                                    if (PlanType.equals("Basic")){
                                        Toast.makeText(SettingActivity.this, "Please Choose 6 Subjects", Toast.LENGTH_SHORT).show();
                                    }else if (PlanType.equals("Standard")){
                                        Toast.makeText(SettingActivity.this, "Please Choose 9 Subjects", Toast.LENGTH_SHORT).show();
                                    }else if (PlanType.equals("Premium")){
                                        Toast.makeText(SettingActivity.this, "Please Choose 12 Subjects", Toast.LENGTH_SHORT).show();
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
        
    }

    public void InsertSubjects(String data){
        ProgressDialog progressDialog = new ProgressDialog(SettingActivity.this);
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
                    String status = response.getString("success");

                    if (status.equals("success")){

                        Toast.makeText(SettingActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                        
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


        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(SettingActivity.this).add(jsonRequest);
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
                            Name=object.getString("first_name")+"\n "+object.getString("last_name");
                            image = object.getString("profile_image");
                            if (!image.equals("null")){

                                try {
                                    image = image.replace("data:image/png;base64,","");
                                    byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                    float degrees = 90; //rotation degree
                                    Matrix matrix = new Matrix();
                                    matrix.setRotate(degrees);
                                    decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                    img_profileone.setImageBitmap(decodedByte);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }else {
                                Picasso.with(SettingActivity.this)
                                        .load(R.mipmap.nophoto)
                                        .into(img_profileone);
                            }
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

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    private void GetPlanByUser(String Userid){

        ProgressDialog dialog = new ProgressDialog(SettingActivity.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getplanbyuser?user_id=" + Userid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String allplan = jsonObject.getString("allplan");

                    JSONArray array = new JSONArray(allplan);

                    for (int i = 0; i<array.length() ; i++){
                        JSONObject object = array.getJSONObject(i);

                        PlanType = object.getString("plan_type");
                        PlanPrice = object.getString("plan_price");
                        PlanYear = object.getString("plan_duration")+" Year "+object.getString("plan_price_month")+" Months";
                        PlanPerMonthPrice = Integer.parseInt(PlanPrice) / 12;
                    }

                    txt_setting_plantype.setText(PlanType);
                    txt_setting_planyear.setText(PlanYear);
                    txt_setting_planprice.setText(PlanPrice);
                    txt_setting_permonthprice.setText(String.valueOf(PlanPerMonthPrice)+" / per month");
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
        MySingleton.getInstance(SettingActivity.this).addToRequestque(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetProfile();

    }
}


//    final Dialog dialog = new Dialog(SettingActivity.this);
//                dialog.setContentView(R.layout.alert_switch_profile);
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                        TextView txt_disconnect_knowmore = dialog.findViewById(R.id.txt_disconnect_knowmore);
//                        TextView txt_disconnect_account = dialog.findViewById(R.id.txt_disconnect_account);
//                        TextView txt_switch_name = dialog.findViewById(R.id.txt_switch_name);
//                        ImageView img_switchprofile = dialog.findViewById(R.id.img_switchprofile);
//                        Button btn_switch_account = dialog.findViewById(R.id.btn_switch_account);
//
//                        txt_switch_name.setText(Name);
//
//                        if (!image.equals("null")){
//
//                        try {
//                        image = image.replace("data:image/png;base64,","");
//                        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
//                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//                        float degrees = 90; //rotation degree
//                        Matrix matrix = new Matrix();
//                        matrix.setRotate(degrees);
//                        decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);
//
//                        img_switchprofile.setImageBitmap(decodedByte);
//                        }catch (Exception e){
//                        e.printStackTrace();
//                        }
//
//                        }else {
//                        Picasso.with(SettingActivity.this)
//                        .load(R.mipmap.nophoto)
//                        .into(img_switchprofile);
//                        }
//
//                        txt_disconnect_knowmore.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//final Dialog dialog = new Dialog(SettingActivity.this);
//        dialog.setContentView(R.layout.alert_disconnet_knowmore);
//        dialog.show();
//        Button btn_change_email_close = dialog.findViewById(R.id.btn_change_email_close);
//
//        txt_disconnect_account.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this)
//        .setMessage("Are you sure you want to disconnect your account?")
//        .setPositiveButton("Disconnect", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        SharedPrefManager.getInstance(SettingActivity.this).logout();
//        }
//        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        dialogInterface.cancel();
//        }
//        });
//        AlertDialog alert11 = alertDialog.create();
//        alert11.show();
//        }
//        });
//
//        btn_change_email_close.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        dialog.dismiss();
//        }
//        });
//        Window window = dialog.getWindow();
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        }
//        });
//
//        btn_switch_account.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//final Dialog dialog = new Dialog(SettingActivity.this);
//        dialog.setContentView(R.layout.alert_add_account);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        }
//        });
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);