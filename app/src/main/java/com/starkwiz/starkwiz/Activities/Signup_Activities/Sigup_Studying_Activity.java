package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.R;

public class Sigup_Studying_Activity extends AppCompatActivity {

    Button btn_signup_personal;
    String FirstName,LastName,Dob,PhoneNo,Gender,State,City,District,SchoolName,Class,Board,Role,BlockNo;
    EditText et_studying_state,et_studying_city,et_studying_district,et_personal_schoolname,et_studying_block;
    RadioButton radio_class_four,radio_class_five,radio_class_six,radio_class_seven,radio_class_eight,radio_class_nine,radio_class_ten,radio_class_icse,radio_class_cbse;
    RadioGroup linear_studying_class,linear_studying_classes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup__studying_);

        Initialize();

        try {

            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            Dob = getIntent().getStringExtra("Dob");
            PhoneNo = getIntent().getStringExtra("PhoneNo");
            Role = getIntent().getStringExtra("Role");
            Gender = getIntent().getStringExtra("Gender");


        }catch (Exception e){
            e.printStackTrace();
        }

        radio_class_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_classes.clearCheck();
            }
        });

        radio_class_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_class.clearCheck();
            }
        });

        radio_class_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_class.clearCheck();
            }
        });

        radio_class_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_studying_class.clearCheck();
            }
        });


        btn_signup_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                State = et_studying_state.getText().toString().trim();
                City = et_studying_city.getText().toString().trim();
                District = et_studying_district.getText().toString().trim();
                SchoolName = et_personal_schoolname.getText().toString().trim();
                BlockNo=et_studying_block.getText().toString().trim();

                if (radio_class_four.isChecked()) {
                    Class = "4";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();

                } else if (radio_class_five.isChecked()) {
                    Class = "5";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();
                } else if (radio_class_six.isChecked()) {
                    Class = "6";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();
                } else if (radio_class_seven.isChecked()) {
                    Class = "7";
                    Log.d("class",Class);
                    linear_studying_classes.clearCheck();
                } else if (radio_class_eight.isChecked()) {
                    Class = "8";
                    Log.d("class",Class);
                    linear_studying_class.clearCheck();
                } else if (radio_class_nine.isChecked()) {
                    Class = "9";
                    Log.d("class",Class);
                    linear_studying_class.clearCheck();
                } else if (radio_class_ten.isChecked()) {
                    Class = "10";
                    Log.d("class",Class);
                    linear_studying_class.clearCheck();
                } else {
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select Your Class");
                }

                if (radio_class_cbse.isChecked()) {
                    Board = "CBSE";
                    Log.d("board",Board);
                } else if (radio_class_icse.isChecked()) {
                    Board = "ICSE";
                    Log.d("board",Board);
                } else {
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select School Board");
                }

                if (State.isEmpty()) {
                    et_studying_state.setError("Please Enter State");
                } else if ((City.isEmpty() && BlockNo.isEmpty())) {
                   AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this,"Please check fields");
                }else if (District.isEmpty()) {
                    et_studying_district.setError("Please Enter District");
                } else if (SchoolName.isEmpty()) {
                    et_personal_schoolname.setError("Please Enter School Name");
                }else if (Class==null){
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select Your Class");

                }else if (Board==null){
                    AlertBoxClasses.SimpleAlertBox(Sigup_Studying_Activity.this, "Please Select School Board");

                }else {
                    Intent intent = new Intent(Sigup_Studying_Activity.this, Signup_Email_Verification_Activity.class);
                    intent.putExtra("FirstName", FirstName);
                    intent.putExtra("LastName", LastName);
                    intent.putExtra("Dob", Dob);
                    intent.putExtra("PhoneNo", PhoneNo);
                    intent.putExtra("Gender", Gender);
                    intent.putExtra("State", State);
                    intent.putExtra("City", City);
                    intent.putExtra("BlockNo", BlockNo);
                    intent.putExtra("District", District);
                    intent.putExtra("SchoolName", SchoolName);
                    intent.putExtra("Class", Class);
                    intent.putExtra("Board", Board);
                    intent.putExtra("Role", Role);
                    startActivity(intent);
                }
            }
        });
    }

    private void Initialize() {

        btn_signup_personal = findViewById(R.id.btn_signup_personal);
        et_studying_state = findViewById(R.id.et_studying_state);
        et_studying_city = findViewById(R.id.et_studying_city);
        et_studying_district = findViewById(R.id.et_studying_district);
        et_personal_schoolname = findViewById(R.id.et_personal_schoolname);
        radio_class_four = findViewById(R.id.radio_class_four);
        radio_class_five = findViewById(R.id.radio_class_five);
        radio_class_six = findViewById(R.id.radio_class_six);
        radio_class_seven = findViewById(R.id.radio_class_seven);
        radio_class_eight = findViewById(R.id.radio_class_eight);
        radio_class_nine = findViewById(R.id.radio_class_nine);
        radio_class_ten = findViewById(R.id.radio_class_ten);
        radio_class_icse = findViewById(R.id.radio_class_icse);
        radio_class_cbse = findViewById(R.id.radio_class_cbse);
        linear_studying_class = findViewById(R.id.linear_studying_class);
        linear_studying_classes = findViewById(R.id.linear_studying_classes);
        et_studying_block = findViewById(R.id.et_studying_block);

        radio_class_four.setChecked(true);
        radio_class_icse.setChecked(true);
    }
}