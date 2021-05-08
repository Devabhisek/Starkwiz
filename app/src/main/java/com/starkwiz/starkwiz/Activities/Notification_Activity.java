package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Adapter.GridAdapter.Getsubject_GridViewAdapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.Notifications_Adapter;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.GetSubjects_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Notification_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notification_Activity extends AppCompatActivity {

    String User_Id;
    RecyclerView lv_interest;
    ArrayList<Notification_ModelClass>listnotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_);
        lv_interest= findViewById(R.id.lv_interest);
        User_Id = SharedPrefManager.getInstance(Notification_Activity.this).getUser().getId();
        lv_interest.setHasFixedSize(true);
        lv_interest.setLayoutManager(new LinearLayoutManager(Notification_Activity.this));
        listnotification = new ArrayList<>();
        GetNotification();
    }
    
    private void GetNotification(){

        ProgressDialog progressDialog = new ProgressDialog(Notification_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("user_id", User_Id);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.getnotification, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String allsubject = response.getString("Information");

                    JSONArray array = new JSONArray(allsubject);

                    for (int i = 0 ; i < array.length() ; i++){

                        JSONObject object = array.getJSONObject(i);

                        Notification_ModelClass modelClass = new Notification_ModelClass(

                                object.getString("id"),
                                object.getString("notification_text")
                        );

                        listnotification.add(modelClass);

                    }


                        Notifications_Adapter adapter = new Notifications_Adapter(listnotification,Notification_Activity.this);
                        lv_interest.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Notification_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(Notification_Activity.this).add(jsonRequest);
    }
}