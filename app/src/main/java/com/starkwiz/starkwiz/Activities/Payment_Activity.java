package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment_Activity extends AppCompatActivity implements PaymentResultListener {

    String json,Amount,User_Id,plan_type,duration;
    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        
        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

        json = getIntent().getExtras().getString("json");
        Amount = getIntent().getExtras().getString("Amount");
        plan_type = getIntent().getExtras().getString("plan_type");
        duration = getIntent().getExtras().getString("duration");
        User_Id = SharedPrefManager.getInstance(Payment_Activity.this).getUser().getId();
        amount = Integer.parseInt(Amount)*100;

        startPayment();


    }

    @Override
    public void onPaymentSuccess(String s) {

        InsertSubjects(json);

        Toast.makeText(Payment_Activity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

    }

    public void InsertSubjects(String data){
        ProgressDialog progressDialog = new ProgressDialog(Payment_Activity.this);
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
                    String status = response.getString("status");

                    if (status.equals("success")){

                       
                        Toast.makeText(Payment_Activity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                        
                        CreateNotification(User_Id,"Your Dynamo has been created.");

                        CreateTransation();



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

                Toast.makeText(Payment_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Payment_Activity.this).add(jsonRequest);
    }

    public void CreateNotification(String user_id, String notification_text){

        ProgressDialog progressDialog = new ProgressDialog(Payment_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("user_id", user_id);
        params.put("notification_text", notification_text);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createnotification, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String message= response.getString("message");
                    if (message.equals("notification created")){
                        Log.d("success",message);
                        notificationDialog();
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

                Log.d("error","error");

                Toast.makeText(Payment_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(Payment_Activity.this).add(jsonRequest);
    }

    private void notificationDialog() {
        NotificationManager notificationManager = (NotificationManager) Payment_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "tutorialspoint_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Payment_Activity.this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.logo)
                .setTicker("Tutorialspoint")
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Starkwiz")
                .setContentText("Your Dynamo has been created.")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */



        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Starkwiz");
            options.put("description", "The Battle of the Bests");
            //You can omit the image option to fetch the image from dashboard
            // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(amount));

            JSONObject preFill = new JSONObject();
            preFill.put("email", SharedPrefManager.getInstance(Payment_Activity.this).getUser().getEmail());
            preFill.put("contact", SharedPrefManager.getInstance(Payment_Activity.this).getUser().getMobile_number());

            options.put("prefill", preFill);

            co.open(Payment_Activity.this, options);
        } catch (Exception e) {
            Toast.makeText(Payment_Activity.this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    public void CreateTransation(){
        ProgressDialog progressDialog = new ProgressDialog(Payment_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("user_id", User_Id);
        params.put("first_name", SharedPrefManager.getInstance(Payment_Activity.this).getUser().getFirst_name());
        params.put("last_name", SharedPrefManager.getInstance(Payment_Activity.this).getUser().getLast_name());
        params.put("email", SharedPrefManager.getInstance(Payment_Activity.this).getUser().getEmail());
        params.put("mobile_number", SharedPrefManager.getInstance(Payment_Activity.this).getUser().getMobile_number());
        params.put("plan_type", plan_type);
        params.put("duration", duration);
        params.put("amount", String.valueOf(amount));


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createtransaction, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String message = response.getString("message");
                    if (message.equals("Transaction created")){

                        Intent intent = new Intent(Payment_Activity.this,Dasboard_Activity.class);
                        startActivity(intent);
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

                Toast.makeText(Payment_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Payment_Activity.this).add(jsonRequest);
    }
}