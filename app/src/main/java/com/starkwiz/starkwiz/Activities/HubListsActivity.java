package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.starkwiz.starkwiz.R;

public class HubListsActivity extends AppCompatActivity {

    Button btn_hublists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_lists);

        btn_hublists = findViewById(R.id.btn_hublists);

        btn_hublists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HubListsActivity.this,HubInfo_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
}