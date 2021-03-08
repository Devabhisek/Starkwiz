package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.HttpsTrustManager;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Subject_Schedule_Detail_Activity extends AppCompatActivity {

    CalendarView cal;
    String test_id,subject_id,module_id,user_id,month,date,time,time_type,hour,minutes,subject,module;
    Button btn_createschedule;
    TextView txt_schedule_time,txt_scheduled_date,txt_scheduld_time,txt_schedule_subjectname,
            txt_schedule_modulename,txt_schedule_timeremaining,txt_schedule_duration;
    RadioButton rb_am,rb_pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__schedule__detail_);
        try {
            test_id = getIntent().getStringExtra("test_id");
            subject_id = getIntent().getStringExtra("subject_id");
            module_id = getIntent().getStringExtra("module_id");
            user_id = getIntent().getStringExtra("user_id");
            month = getIntent().getStringExtra("month");
            hour = getIntent().getStringExtra("hour");
            minutes = getIntent().getStringExtra("minutes");
            subject = getIntent().getStringExtra("subject");
            module = getIntent().getStringExtra("module");

            minutes = String.valueOf((Integer.parseInt(hour)*60)+Integer.parseInt(minutes));




            cal = findViewById(R.id.cal);
            btn_createschedule = findViewById(R.id.btn_createschedule);
            txt_schedule_time = findViewById(R.id.txt_schedule_time);
            txt_scheduled_date = findViewById(R.id.txt_scheduled_date);
            txt_scheduld_time = findViewById(R.id.txt_scheduld_time);
            txt_schedule_subjectname = findViewById(R.id.txt_schedule_subjectname);
            txt_schedule_modulename = findViewById(R.id.txt_schedule_modulename);
            txt_schedule_timeremaining = findViewById(R.id.txt_schedule_timeremaining);
            txt_schedule_duration = findViewById(R.id.txt_schedule_duration);
            rb_am = findViewById(R.id.rb_am);
            rb_pm = findViewById(R.id.rb_pm);



        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                 date = year + "/0" + month + "/"+ dayOfMonth ;
                Log.d("TAG", "yyyy/mm/dd:" + date);


            }
        });

        txt_schedule_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Subject_Schedule_Detail_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        if (selectedHour>12){
                            selectedHour = selectedHour-12;
                            time = selectedHour + ":" + selectedMinute;
                            txt_schedule_time.setText(time);
                        }else {
                            time = selectedHour + ":" + selectedMinute;
                            txt_schedule_time.setText(time);
                        }

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        rb_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_am.setTextColor(getResources().getColor(R.color.Green));
                rb_pm.setTextColor(getResources().getColor(R.color.grey));
                time_type = "AM";
            }
        });

        rb_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_pm.setTextColor(getResources().getColor(R.color.Green));
                rb_am.setTextColor(getResources().getColor(R.color.grey));
                time_type = "PM";
            }
        });

        btn_createschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rb_am.isChecked()){
                    time_type = "AM";
                }
                else {
                    time_type = "PM";
                }
                CreateSchdule(test_id,user_id,subject_id,module_id,date,time,month,time_type);
            }
        });

        txt_scheduld_time.setText(time);
        txt_schedule_modulename.setText(subject);
        txt_schedule_modulename.setText(module);
        txt_schedule_duration.setText(minutes);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void CreateSchdule(String test_id,String user_id,String subject_id,String module_id,
                              String date,String time,String month,String time_type){

        if (date!=null && time!=null && time_type!=null) {

            HttpsTrustManager.allowAllSSL();
            ProgressDialog dialog = new ProgressDialog(Subject_Schedule_Detail_Activity.this);
            dialog.setCancelable(false);
            dialog.setMessage("Loading...");
            dialog.show();
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    "https://www.rentopool.com/starkwiz/api/auth/createfixture?test_id=" + test_id +
                            "&user_id=" + user_id +
                            "&subject_id=" + subject_id +
                            "&module_id=" + module_id +
                            "&date=" + date +
                            "&time=" + time +
                            "&time_type=" + time_type +
                            "&month=" + month,
                    null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    dialog.dismiss();

                    try {
                        String msg = response.getString("message");

                        if (msg.equals("fixture created")) {
                            Toast.makeText(Subject_Schedule_Detail_Activity.this, "Scheduled", Toast.LENGTH_SHORT).show();
                            AlertBoxClasses.SimpleAlertBox(Subject_Schedule_Detail_Activity.this, "Scheduled Successfully");
                        } else {
                            AlertBoxClasses.SimpleAlertBox(Subject_Schedule_Detail_Activity.this, "Something went wrong, Please try again.");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    dialog.dismiss();

                    AlertBoxClasses.SimpleAlertBox(Subject_Schedule_Detail_Activity.this, "Error.");

                }
            });


            Volley.newRequestQueue(Subject_Schedule_Detail_Activity.this).add(jsonRequest);

        }
        else {
            AlertBoxClasses.SimpleAlertBox(Subject_Schedule_Detail_Activity.this,"Check all fields");
        }
    }


}