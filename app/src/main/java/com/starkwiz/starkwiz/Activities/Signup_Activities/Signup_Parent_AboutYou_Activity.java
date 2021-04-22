package com.starkwiz.starkwiz.Activities.Signup_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.starkwiz.starkwiz.R;

public class Signup_Parent_AboutYou_Activity extends AppCompatActivity {

    Button btn_verfication_proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__parent__about_you_);

        btn_verfication_proceed = findViewById(R.id.btn_verfication_proceed);

        btn_verfication_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_Parent_AboutYou_Activity.this, Signup_Email_Verification_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });


    }
}