package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.starkwiz.starkwiz.R;

public class HubInfo_Activity extends AppCompatActivity {

    Button btn_hubinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_info_);
        btn_hubinfo = findViewById(R.id.btn_hubinfo);

        btn_hubinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HubInfo_Activity.this,Dasboard_Activity.class));
            }
        });
    }
}