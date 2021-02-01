package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.starkwiz.starkwiz.R;

public class Signup_Email_Verification_Activity extends AppCompatActivity {

    Button btn_verfication_proceed;
    String FirstName,LastName,Dob,PhoneNo,Gender,State,City,District,SchoolName,Class,Board,Email,Role,BlockNo;
    EditText etemail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__email__verification_);

        try {
            FirstName = getIntent().getStringExtra("FirstName");
            LastName = getIntent().getStringExtra("LastName");
            Dob = getIntent().getStringExtra("Dob");
            PhoneNo = getIntent().getStringExtra("PhoneNo");
            Gender = getIntent().getStringExtra("Gender");
            State = getIntent().getStringExtra("State");
            City = getIntent().getStringExtra("City");
            District = getIntent().getStringExtra("District");
            SchoolName = getIntent().getStringExtra("SchoolName");
            Class = getIntent().getStringExtra("Class");
            Role = getIntent().getStringExtra("Role");
            Board = getIntent().getStringExtra("Board");
            BlockNo = getIntent().getStringExtra("BlockNo");


        }catch (Exception e){
            e.printStackTrace();
        }

        btn_verfication_proceed = findViewById(R.id.btn_verfication_proceed);
        etemail = findViewById(R.id.etemail);

        btn_verfication_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email = etemail.getText().toString().trim();

                if (Email.isEmpty() || !Email.matches(emailPattern)){
                    etemail.setError("Please Enter Valid Email");
                }else {
                    Intent intent = new Intent(Signup_Email_Verification_Activity.this,Signup_SetupPassword_Activity.class);
                    intent.putExtra("FirstName",FirstName);
                    intent.putExtra("LastName",LastName);
                    intent.putExtra("Dob",Dob);
                    intent.putExtra("PhoneNo",PhoneNo);
                    intent.putExtra("Gender",Gender);
                    intent.putExtra("State",State);
                    intent.putExtra("City",City);
                    intent.putExtra("District",District);
                    intent.putExtra("SchoolName",SchoolName);
                    intent.putExtra("Class",Class);
                    intent.putExtra("Board",Board);
                    intent.putExtra("Email",Email);
                    intent.putExtra("Role",Role);
                    intent.putExtra("BlockNo",BlockNo);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }
        });
    }
}