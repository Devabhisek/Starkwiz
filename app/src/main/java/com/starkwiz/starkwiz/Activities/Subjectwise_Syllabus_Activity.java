package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

public class Subjectwise_Syllabus_Activity extends AppCompatActivity {
    TextView txt_appear,txt_subject,txtduration,txt_fixture,txt_totalmark;
    RecyclerView list_test;
    Button btn_schedule;
    ArrayList<GetTestList_ModelClass>lis_gettests;
    String selected_module,selected_testid,is_active,selected_hour,selected_minutes,totalmark,
            test_id,user_id,subject_id,module_id,hour,minutes,subject,module,Getsubject,selected_subjectid;
    LinearLayout linearaction;
    private TourGuide mTourGuideHandler;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectwise__syllabus_);
        Getsubject = getIntent().getStringExtra("subject");
        txt_appear = findViewById(R.id.txt_appear);
        list_test = findViewById(R.id.list_test);
        txt_subject = findViewById(R.id.txt_subject);
        txtduration = findViewById(R.id.txtduration);
        txt_fixture = findViewById(R.id.txt_fixture);
        linearaction = findViewById(R.id.linearaction);
        btn_schedule = findViewById(R.id.btn_schedule);
        txt_totalmark = findViewById(R.id.txt_totalmark);
        lis_gettests = new ArrayList<>();
        list_test.setHasFixedSize(true);
        list_test.setLayoutManager(new LinearLayoutManager(Subjectwise_Syllabus_Activity.this));

        //GetTestList();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month = month_date.format(cal.getTime());

        txt_fixture.setText("FIXTURE: "+month);



        txt_appear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selected_testid!=null){
                    Intent intent = new Intent(Subjectwise_Syllabus_Activity.this,Student_Quiz_Activity.class);
                    intent.putExtra("selected_testid",selected_testid);
                    intent.putExtra("selected_module",selected_module);
                    intent.putExtra("selected_hour",selected_hour);
                    intent.putExtra("selected_minutes",selected_minutes);
                    intent.putExtra("selected_subject",txt_subject.getText().toString().trim());
                    intent.putExtra("selected_subjectid",selected_subjectid);
                    intent.putExtra("selected_totalmark",totalmark);
                    startActivity(intent);
                }
                else {
                    AlertBoxClasses.SimpleAlertBox(Subjectwise_Syllabus_Activity.this,"Please select a chapter");
                }
            }
        });

        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_id = SharedPrefManager.getInstance(Subjectwise_Syllabus_Activity.this).getUser().getId();
                if (selected_testid!=null) {
                    Intent intent = new Intent(Subjectwise_Syllabus_Activity.this, Subject_Schedule_Detail_Activity.class);
                    intent.putExtra("newschedule", "new");
                    intent.putExtra("test_id", test_id);
                    intent.putExtra("subject_id", subject_id);
                    intent.putExtra("module_id", module_id);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("month", month);
                    intent.putExtra("hour", hour);
                    intent.putExtra("minutes", minutes);
                    intent.putExtra("subject", subject);
                    intent.putExtra("module", module);
                    //intent.putExtra("date",module);
                    intent.putExtra("totalmark", totalmark);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
             selected_hour = intent.getStringExtra("selected_hour");
             selected_minutes = intent.getStringExtra("selected_minutes");
             totalmark = intent.getStringExtra("totalmark");
            selected_subjectid = intent.getStringExtra("selected_subjectid");

//            Log.d("selected_hour",selected_hour);

            txtduration.setText("Duration\n"+selected_hour+" : "+selected_minutes);
            txt_totalmark.setText("Marks \n"+totalmark);
        }
    };

    private void GetTestList(){

        lis_gettests.clear();
        ProgressDialog progressDialog = new ProgressDialog(Subjectwise_Syllabus_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        String cls =SharedPrefManager.getInstance(Subjectwise_Syllabus_Activity.this).getUser().getCls();
        String board =SharedPrefManager.getInstance(Subjectwise_Syllabus_Activity.this).getUser().getSchool_board();
        String id =SharedPrefManager.getInstance(Subjectwise_Syllabus_Activity.this).getUser().getId();

        final Map<String, String> params = new HashMap();

        params.put("class", cls);
        params.put("subject", Getsubject);
        params.put("board", board);

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
                        subject = jsonObject.getString("subject_name");
                        is_active = jsonObject.getString("is_active");
                    }

                    if (is_active==null){
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Subjectwise_Syllabus_Activity.this)
                                .setMessage("No Tests are availabel")
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        startActivity(new Intent(Subjectwise_Syllabus_Activity.this,Dasboard_Activity.class));
                                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                    }
                                });
                        AlertDialog alert11 = alertDialog.create();
                        alert11.show();
                    }
                    else if (!is_active.equals("NO")){


                        JSONArray modulearray = object.getJSONArray("test_module_list");

                        String array = modulearray.toString();

                        if (!array.equals("[]")){
                            for (int i= 0 ; i<modulearray.length() ; i++){



                                JSONObject module_bject = modulearray.getJSONObject(i);

                                test_id = module_bject.getString("test_id");
                                subject_id = module_bject.getString("subject_id");
                                module_id = module_bject.getString("module_id");
                                hour = module_bject.getString("hour");
                                minutes = module_bject.getString("minutes");
                                module = module_bject.getString("module_name");

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

                        }else {
                            linearaction.setVisibility(View.GONE);
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Subjectwise_Syllabus_Activity.this)
                                    .setMessage("No Tests are availabel")
                                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                            startActivity(new Intent(Subjectwise_Syllabus_Activity.this,Dasboard_Activity.class));
                                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                        }
                                    });
                            AlertDialog alert11 = alertDialog.create();
                            alert11.show();
                        }




                        GetList_Adapter adapter = new GetList_Adapter(lis_gettests,Subjectwise_Syllabus_Activity.this);
                        list_test.setAdapter(adapter);
                        linearaction.setVisibility(View.VISIBLE);
                        txt_appear.setEnabled(true);

                    }else {

                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Subjectwise_Syllabus_Activity.this)
                                .setMessage("No tests are availabel for this subject")
                                .setCancelable(false)
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
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Subjectwise_Syllabus_Activity.this)
                            .setMessage("No tests are availabel for this subject")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alert11 = alertDialog.create();
                    alert11.show();
                    linearaction.setVisibility(View.GONE);
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Subjectwise_Syllabus_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
                linearaction.setVisibility(View.GONE);
            }
        });


        Volley.newRequestQueue(Subjectwise_Syllabus_Activity.this).add(jsonRequest);


    }



    @Override
    protected void onStart() {
        super.onStart();
        GetTestList();
//        sharedPreferences = getSharedPreferences("USER", 0);
//        String strtext = sharedPreferences.getString("First_Subject","");
//
//        if (strtext.isEmpty()) {
//
//            Firsttime_Guide();
//
//        }
    }

//    private void Firsttime_Guide(){
//        mTourGuideHandler = TourGuide.init( Subjectwise_Syllabus_Activity.this  ).with(TourGuide.Technique.Click)
//                .setPointer(new Pointer())
//                .setToolTip( new ToolTip()
//                        .setTitle("Click on any Module")
//                        .setDescription("Then Click on Appear")
//                        .setBackgroundColor(Color.parseColor("#88D5F0"))
//                        .setShadow(true)
//                        .setGravity(Gravity.BOTTOM | Gravity.RIGHT))
//                .setOverlay(new Overlay()) .playOn(txt_appear);
//
//        SharedPreferences sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE) ;
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("First_Subject","Firsttime_" );
//        editor.commit();
//
//    }
}