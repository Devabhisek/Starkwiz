package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

import com.starkwiz.starkwiz.R;

import java.util.Calendar;

public class Subject_Schedule_Detail_Activity extends AppCompatActivity {

    CalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__schedule__detail_);
        cal = findViewById(R.id.cal);
        Calendar calendar = Calendar.getInstance();
        CalendarView cal = new CalendarView(Subject_Schedule_Detail_Activity.this);
        cal.setDate(System.currentTimeMillis(),false,true);
    }
}