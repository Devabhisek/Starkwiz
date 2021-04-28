package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.Signup_Activities.Signup_SetupPassword_Activity;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.Hublist_Adapter;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Hublist_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HubListsActivity extends AppCompatActivity {

    Button btn_hublists;
    String Name,Address,YearOfEstablishment,Mobileno,selected_hubtype;
    RecyclerView lv_hublist;
    ArrayList<Hublist_ModelClass>list_hub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_lists);

        btn_hublists = findViewById(R.id.btn_hublists);
        lv_hublist = findViewById(R.id.lv_hublist);
        lv_hublist.setHasFixedSize(true);
        lv_hublist.setLayoutManager(new LinearLayoutManager(HubListsActivity.this));
        list_hub= new ArrayList<>();
        GetHubList();

        try {

            Name = getIntent().getExtras().getString("Name");
            Address = getIntent().getExtras().getString("Address");
            YearOfEstablishment = getIntent().getExtras().getString("YearOfEstablishment");
            Mobileno = getIntent().getExtras().getString("Mobileno");

        }catch (Exception e){
            e.printStackTrace();
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));


        btn_hublists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selected_hubtype!=null){
                    RegisterHub();
                }else {
                    Toast.makeText(HubListsActivity.this, "Choose any one of these", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            selected_hubtype = intent.getStringExtra("selected_hubtype");

            if (selected_hubtype!=null){
                btn_hublists.setBackgroundResource(R.drawable.rounded_button);
                btn_hublists.setEnabled(true);
            }
            else {
                btn_hublists.setBackgroundResource(R.drawable.round_textview);
                btn_hublists.setEnabled(false);
            }
        }
    };

    private void GetHubList(){
        ProgressDialog progressDialog = new ProgressDialog(HubListsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.gethubtype, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();

                        // display response
                        for (int j=0 ; j<response.length() ; j++){

                            try {
                                JSONObject object = response.getJSONObject(j);


                                Hublist_ModelClass modelClass = new Hublist_ModelClass(
                                  object.getString("id"),
                                  object.getString("hub_type")
                                );

                                list_hub.add(modelClass);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        Hublist_Adapter adapter = new Hublist_Adapter(list_hub,HubListsActivity.this);
                        lv_hublist.setAdapter(adapter);
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
        Volley.newRequestQueue(HubListsActivity.this).add(getRequest);
    }

    private void RegisterHub(){
        ProgressDialog progressDialog = new ProgressDialog(HubListsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();


        params.put("hub_name", Name);
        params.put("address", Address);
        params.put("hub_establishment", YearOfEstablishment);
        params.put("mobile_number", Mobileno);
        params.put("role", "Hub");
        params.put("hub_typee", selected_hubtype);

        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.registerhub, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                Toast.makeText(HubListsActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(HubListsActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(HubListsActivity.this).add(jsonRequest);

    }
}