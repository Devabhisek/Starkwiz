package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.R;

public class SettingActivity extends AppCompatActivity {

    TextView txtsetting_account,txtsetting_notification,txtsetting_privacyanddata,txtsetting_plandetail,
            txt_setting_email,txt_setting_changepassword,txt_changeregion,txt_deactivate_account,txt_closeaccount,
            txtsetting_supportnotification,txtsetting_supportprivacy,txt_disconnect_knowmore,txtsetting_logout;
    LinearLayout linear_setting_account,linear_setting_notification,linear_setting_privacydata,linear_setting_plandetails,linear_switch_account;
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

                btn_change_email_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(SettingActivity.this);
                        dialog.setContentView(R.layout.alert_email_verification);
                        InsetDrawable background =
                                (InsetDrawable) dialog.getWindow().getDecorView().getBackground();
                        background.setAlpha(0);
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

        txt_setting_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup viewGroup = findViewById(android.R.id.content);

                //then we will inflate the custom alert dialog xml that we created
                View dialogView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.alert_change_passwprd, viewGroup, false);


                //Now we need an AlertDialog.Builder object
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

                //setting the view of the builder to our custom view that we already inflated
                builder.setView(dialogView);

                //finally creating the alert dialog and displaying it
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
}