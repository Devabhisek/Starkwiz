package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.starkwiz.starkwiz.R;

public class Signup_Qualification_Activity extends AppCompatActivity {

    Button btn_verfication_proceed;
    CheckBox chk_first,chk_two,chk_three,chk_four,chk_five,chk_six,chk_sixplus;
    EditText et_major,et_qualification_degree;
    String FirstName,LastName,Dob,PhoneNo,Gender,Role,newaccount,cls,board,Experience,State,District,City,BlockNo,SchoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__qualification_);

        try {

            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            Dob = getIntent().getStringExtra("Dob");
            PhoneNo = getIntent().getStringExtra("PhoneNo");
            Role = getIntent().getStringExtra("Role");
            Gender = getIntent().getStringExtra("Gender");
            newaccount = getIntent().getStringExtra("newaccount");
            cls = getIntent().getStringExtra("Class");
            board = getIntent().getStringExtra("Board");
            State = getIntent().getStringExtra("State");
            District = getIntent().getStringExtra("District");
            City = getIntent().getStringExtra("City");
            BlockNo = getIntent().getStringExtra("BlockNo");
            SchoolName = getIntent().getStringExtra("SchoolName");


        }catch (Exception e){
            e.printStackTrace();
        }

        btn_verfication_proceed = findViewById(R.id.btn_verfication_proceed);
        chk_first = findViewById(R.id.chk_first);
        chk_two = findViewById(R.id.chk_two);
        chk_three = findViewById(R.id.chk_three);
        chk_four = findViewById(R.id.chk_four);
        chk_five = findViewById(R.id.chk_five);
        chk_six = findViewById(R.id.chk_six);
        chk_sixplus = findViewById(R.id.chk_sixplus);
        et_major = findViewById(R.id.et_major);
        et_qualification_degree = findViewById(R.id.et_qualification_degree);

        chk_first.setChecked(true);
        Experience="1";

        chk_first.setTextColor(getResources().getColor(R.color.black));
        chk_two.setTextColor(getResources().getColor(R.color.gray));
        chk_three.setTextColor(getResources().getColor(R.color.gray));
        chk_four.setTextColor(getResources().getColor(R.color.gray));
        chk_five.setTextColor(getResources().getColor(R.color.gray));
        chk_six.setTextColor(getResources().getColor(R.color.gray));
        chk_sixplus.setTextColor(getResources().getColor(R.color.gray));

        chk_two.setChecked(false);
        chk_three.setChecked(false);
        chk_four.setChecked(false);
        chk_five.setChecked(false);
        chk_six.setChecked(false);
        chk_sixplus.setChecked(false);

        chk_first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.black));
                chk_two.setTextColor(getResources().getColor(R.color.gray));
                chk_three.setTextColor(getResources().getColor(R.color.gray));
                chk_four.setTextColor(getResources().getColor(R.color.gray));
                chk_five.setTextColor(getResources().getColor(R.color.gray));
                chk_six.setTextColor(getResources().getColor(R.color.gray));
                chk_sixplus.setTextColor(getResources().getColor(R.color.gray));


                if (isChecked){
                    chk_two.setChecked(false);
                    chk_three.setChecked(false);
                    chk_four.setChecked(false);
                    chk_five.setChecked(false);
                    chk_six.setChecked(false);
                    chk_sixplus.setChecked(false);
                }


                Experience = "1";


            }
        });

        chk_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.gray));
                chk_two.setTextColor(getResources().getColor(R.color.black));
                chk_three.setTextColor(getResources().getColor(R.color.gray));
                chk_four.setTextColor(getResources().getColor(R.color.gray));
                chk_five.setTextColor(getResources().getColor(R.color.gray));
                chk_six.setTextColor(getResources().getColor(R.color.gray));
                chk_sixplus.setTextColor(getResources().getColor(R.color.gray));

                if (isChecked){
                    chk_first.setChecked(false);
                    chk_three.setChecked(false);
                    chk_four.setChecked(false);
                    chk_five.setChecked(false);
                    chk_six.setChecked(false);
                    chk_sixplus.setChecked(false);
                }

                Experience = "2";

            }
        });

        chk_three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.gray));
                chk_two.setTextColor(getResources().getColor(R.color.gray));
                chk_three.setTextColor(getResources().getColor(R.color.black));
                chk_four.setTextColor(getResources().getColor(R.color.gray));
                chk_five.setTextColor(getResources().getColor(R.color.gray));
                chk_six.setTextColor(getResources().getColor(R.color.gray));
                chk_sixplus.setTextColor(getResources().getColor(R.color.gray));

                if (isChecked){
                    chk_first.setChecked(false);
                    chk_two.setChecked(false);
                    chk_four.setChecked(false);
                    chk_five.setChecked(false);
                    chk_six.setChecked(false);
                    chk_sixplus.setChecked(false);
                }

                Experience = "3";

            }
        });

        chk_four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.gray));
                chk_two.setTextColor(getResources().getColor(R.color.gray));
                chk_three.setTextColor(getResources().getColor(R.color.gray));
                chk_four.setTextColor(getResources().getColor(R.color.black));
                chk_five.setTextColor(getResources().getColor(R.color.gray));
                chk_six.setTextColor(getResources().getColor(R.color.gray));
                chk_sixplus.setTextColor(getResources().getColor(R.color.gray));

                if (isChecked){
                    chk_first.setChecked(false);
                    chk_two.setChecked(false);
                    chk_three.setChecked(false);
                    chk_five.setChecked(false);
                    chk_six.setChecked(false);
                    chk_sixplus.setChecked(false);
                }

                Experience = "4";

            }
        });

        chk_five.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.gray));
                chk_two.setTextColor(getResources().getColor(R.color.gray));
                chk_three.setTextColor(getResources().getColor(R.color.gray));
                chk_four.setTextColor(getResources().getColor(R.color.gray));
                chk_five.setTextColor(getResources().getColor(R.color.black));
                chk_six.setTextColor(getResources().getColor(R.color.gray));
                chk_sixplus.setTextColor(getResources().getColor(R.color.gray));

                if (isChecked){
                    chk_first.setChecked(false);
                    chk_two.setChecked(false);
                    chk_three.setChecked(false);
                    chk_four.setChecked(false);
                    chk_six.setChecked(false);
                    chk_sixplus.setChecked(false);
                }

                Experience = "5";

            }
        });

        chk_six.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.gray));
                chk_two.setTextColor(getResources().getColor(R.color.gray));
                chk_three.setTextColor(getResources().getColor(R.color.gray));
                chk_four.setTextColor(getResources().getColor(R.color.gray));
                chk_five.setTextColor(getResources().getColor(R.color.gray));
                chk_six.setTextColor(getResources().getColor(R.color.black));
                chk_sixplus.setTextColor(getResources().getColor(R.color.gray));

                if (isChecked){
                    chk_first.setChecked(false);
                    chk_two.setChecked(false);
                    chk_three.setChecked(false);
                    chk_four.setChecked(false);
                    chk_five.setChecked(false);
                    chk_sixplus.setChecked(false);
                }

                Experience = "6";

            }
        });

        chk_sixplus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chk_first.setTextColor(getResources().getColor(R.color.gray));
                chk_two.setTextColor(getResources().getColor(R.color.gray));
                chk_three.setTextColor(getResources().getColor(R.color.gray));
                chk_four.setTextColor(getResources().getColor(R.color.gray));
                chk_five.setTextColor(getResources().getColor(R.color.gray));
                chk_six.setTextColor(getResources().getColor(R.color.gray));
                chk_sixplus.setTextColor(getResources().getColor(R.color.black));

                if (isChecked){

                    chk_first.setChecked(false);
                    chk_two.setChecked(false);
                    chk_three.setChecked(false);
                    chk_four.setChecked(false);
                    chk_five.setChecked(false);
                    chk_six.setChecked(false);
                }

                Experience = "6 plus";

            }
        });

//        et_major.addTextChangedListener(new TextWatcher() {
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
//                if (et_major.getText().toString().trim().length()>0 &&
//                        et_qualification_degree.getText().toString().trim().length()>0){
//                    btn_verfication_proceed.setEnabled(true);
//                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.rounded_button));
//                }else {
//                    btn_verfication_proceed.setEnabled(false);
//                    btn_verfication_proceed.setBackground(getResources().getDrawable(R.drawable.round_textview));
//                }
//            }
//        });

        btn_verfication_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Signup_Qualification_Activity.this, Signup_Email_Verification_Activity.class);
                intent.putExtra("FirstName",FirstName);
                intent.putExtra("LastName",LastName);
                intent.putExtra("Dob",Dob);
                intent.putExtra("PhoneNo",PhoneNo);
                intent.putExtra("Role",Role);
                intent.putExtra("Gender",Gender);
                intent.putExtra("newaccount",newaccount);
                intent.putExtra("Class",cls);
                intent.putExtra("Board",board);
                intent.putExtra("Experience",Experience);
                intent.putExtra("State",State);
                intent.putExtra("District",District);
                intent.putExtra("City",City);
                intent.putExtra("BlockNo",BlockNo);
                intent.putExtra("SchoolName",SchoolName);
                intent.putExtra("Qualification",et_qualification_degree.getText().toString().trim());
                intent.putExtra("Profession",et_major.getText().toString().trim());
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });


    }
}