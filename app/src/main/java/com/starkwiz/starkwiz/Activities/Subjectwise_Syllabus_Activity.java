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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.GetList_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subjectwise_Syllabus_Activity extends AppCompatActivity {
    TextView txt_appear,txt_subject;
    RecyclerView list_test;
    ArrayList<GetTestList_ModelClass>lis_gettests;
    String selected_module,selected_testid,is_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectwise__syllabus_);
        txt_appear = findViewById(R.id.txt_appear);
        list_test = findViewById(R.id.list_test);
        txt_subject = findViewById(R.id.txt_subject);
        lis_gettests = new ArrayList<>();
        list_test.setHasFixedSize(true);
        list_test.setLayoutManager(new LinearLayoutManager(Subjectwise_Syllabus_Activity.this));
        GetTestList();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        txt_appear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_testid!=null){
                    Intent intent = new Intent(Subjectwise_Syllabus_Activity.this,Student_Quiz_Activity.class);
                    intent.putExtra("selected_testid",selected_testid);
                    intent.putExtra("selected_module",selected_module);
                    intent.putExtra("selected_subject",txt_subject.getText().toString().trim());
                    startActivity(intent);
                }
                else {
                    AlertBoxClasses.SimpleAlertBox(Subjectwise_Syllabus_Activity.this,"Please select a chapter");
                }
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
             selected_module = intent.getStringExtra("selected_module");
             selected_testid = intent.getStringExtra("selected_testid");

        }
    };

    private void GetTestList(){


        ProgressDialog progressDialog = new ProgressDialog(Subjectwise_Syllabus_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("class", "4");
        params.put("subject", "English");
        params.put("board", "icse");

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.GetTestList, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {


                    JSONObject object = response.getJSONObject("testdeatils");

                    JSONArray test_details = object.getJSONArray("test_subject_details");

                    for (int j = 0 ; j<test_details.length() ; j++){
                        JSONObject jsonObject = test_details.getJSONObject(j);
                        txt_subject.setText(jsonObject.getString("subject_name"));
                        is_active = jsonObject.getString("is_active");
                    }

                    JSONArray modulearray = object.getJSONArray("test_module_list");

                    for (int i= 0 ; i<modulearray.length() ; i++){

                        JSONObject module_bject = modulearray.getJSONObject(i);

                        GetTestList_ModelClass modelClass = new GetTestList_ModelClass(
                                module_bject.getString("module_id"),
                                module_bject.getString("subject_id"),
                                module_bject.getString("test_id"),
                                module_bject.getString("module_number"),
                                module_bject.getString("module_name"),
                                module_bject.getString("hour"),
                                module_bject.getString("minutes"),
                                "",
                                module_bject.getString("no_of_questions")
                        );

                        lis_gettests.add(modelClass);
                    }

                    GetList_Adapter adapter = new GetList_Adapter(lis_gettests,Subjectwise_Syllabus_Activity.this);
                    list_test.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Subjectwise_Syllabus_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(Subjectwise_Syllabus_Activity.this).add(jsonRequest);


    }


}