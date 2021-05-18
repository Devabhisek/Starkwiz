package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.starkwiz.starkwiz.R;

public class Signup_Parent_AboutYou_Activity extends AppCompatActivity {

    Button btn_verfication_proceed;
    EditText etqualification,et_signup_profession;
    String FirstName,LastName,Dob,PhoneNo,Gender,Role,newaccount,Qualification,Profession,kids;
    RadioButton check_one,check_two,check_three,check_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__parent__about_you_);

        try {

            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            Dob = getIntent().getStringExtra("Dob");
            PhoneNo = getIntent().getStringExtra("PhoneNo");
            Role = getIntent().getStringExtra("Role");
            Gender = getIntent().getStringExtra("Gender");
            newaccount = getIntent().getStringExtra("newaccount");


        }catch (Exception e){
            e.printStackTrace();
        }

        btn_verfication_proceed = findViewById(R.id.btn_verfication_proceed);
        etqualification = findViewById(R.id.etqualification);
        et_signup_profession = findViewById(R.id.et_signup_profession);
        check_one = findViewById(R.id.check_one);
        check_two = findViewById(R.id.check_two);
        check_three = findViewById(R.id.check_three);
        check_four = findViewById(R.id.check_four);

        check_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kids = "1";
            }
        });

        check_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kids = "2";
            }
        });

        check_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kids = "3";
            }
        });

        check_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kids = "4";
            }
        });


//        etqualification.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (etqualification.getText().toString().trim().length()>0 &&
//                        et_signup_profession.getText().toString().trim().length()>0){
//                    btn_verfication_proceed.setEnabled(true);
//                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.rounded_button));
//                }else {
//                    btn_verfication_proceed.setEnabled(false);
//                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.round_textview));
//                }
//
//            }
//        });



        btn_verfication_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qualification = etqualification.getText().toString().trim();
                Profession = et_signup_profession.getText().toString().trim();
                Intent intent = new Intent(Signup_Parent_AboutYou_Activity.this, Signup_Email_Verification_Activity.class);
                intent.putExtra("FirstName",FirstName);
                intent.putExtra("LastName",LastName);
                intent.putExtra("Dob",Dob);
                intent.putExtra("PhoneNo",PhoneNo);
                intent.putExtra("Role",Role);
                intent.putExtra("Gender",Gender);
                intent.putExtra("newaccount",newaccount);
                intent.putExtra("Qualification",Qualification);
                intent.putExtra("Profession",Profession);
                intent.putExtra("kids",kids);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });



    }
}