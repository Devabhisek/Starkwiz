package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.starkwiz.starkwiz.R;

public class Signup_teaching_Activity extends AppCompatActivity {

    Button btn_signup_teaching;
    RadioGroup linear_studying_class,linear_studying_classes;
    RadioButton radio_teaching_icse,radio_teaching_cbse,radio_teahing_four,radio_teahing_five,radio_teahing_six,
            radio_teahing_seven,radio_teahing_eight,radio_teahing_nine,radio_teahing_ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teaching_);

        Initialzation();

        btn_signup_teaching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_teaching_Activity.this, Signup_Qualification_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        radio_teahing_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_teahing_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_teahing_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_teahing_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_teahing_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_class.clearCheck();
            }
        });

        radio_teahing_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_class.clearCheck();
            }
        });

        radio_teahing_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_class.clearCheck();
            }
        });


    }

    private void Initialzation() {
        btn_signup_teaching = findViewById(R.id.btn_signup_teaching);
        linear_studying_class = findViewById(R.id.linear_studying_class);
        linear_studying_classes = findViewById(R.id.linear_studying_classes);
        radio_teaching_icse = findViewById(R.id.radio_teaching_icse);
        radio_teaching_cbse = findViewById(R.id.radio_teaching_cbse);
        radio_teahing_four = findViewById(R.id.radio_teahing_four);
        radio_teahing_five = findViewById(R.id.radio_teahing_five);
        radio_teahing_six = findViewById(R.id.radio_teahing_six);
        radio_teahing_seven = findViewById(R.id.radio_teahing_seven);
        radio_teahing_eight = findViewById(R.id.radio_teahing_eight);
        radio_teahing_nine = findViewById(R.id.radio_teahing_nine);
        radio_teahing_ten = findViewById(R.id.radio_teahing_ten);
    }
}