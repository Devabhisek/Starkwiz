package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.starkwiz.starkwiz.Activities.Signup_Activities.Signup_Personal_Activity;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.R;

import java.util.Calendar;

public class HubInfo_Activity extends AppCompatActivity {

    Button btn_hubinfo;
    EditText et_hubinfo_name,et_hubinfo_address,et_hubinfo_mobileno;
    String Name,Address,YearOfEstablishment,Mobileno,date;
    TextView et_hubinfo_yearestablishment;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "HubInfo_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_info_);
        btn_hubinfo = findViewById(R.id.btn_hubinfo);

        et_hubinfo_name = findViewById(R.id.et_hubinfo_name);
        et_hubinfo_address = findViewById(R.id.et_hubinfo_address);
        et_hubinfo_yearestablishment = findViewById(R.id.et_hubinfo_yearestablishment);
        et_hubinfo_mobileno = findViewById(R.id.et_hubinfo_mobileno);

        et_hubinfo_yearestablishment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        HubInfo_Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);

                date = year + "/" + "0"+month + "/" + day;
                et_hubinfo_yearestablishment.setText(day + "/" + month + "/" + year);
            }
        };
        et_hubinfo_mobileno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (et_hubinfo_mobileno.getText().toString().trim().length()==10 &&
                        et_hubinfo_name.getText().toString()!=null &&
                        et_hubinfo_address.getText().toString()!=null &&
                        et_hubinfo_yearestablishment.getText().toString()!=null){
                    et_hubinfo_mobileno.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24, 0);
                    btn_hubinfo.setBackgroundResource(R.drawable.rounded_button);
                    btn_hubinfo.setEnabled(true);
                }
                else {
                    btn_hubinfo.setBackgroundResource(R.drawable.round_textview);
                    btn_hubinfo.setEnabled(false);
                }
            }
        });

        btn_hubinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = et_hubinfo_name.getText().toString().trim();
                Address = et_hubinfo_address.getText().toString().trim();
                YearOfEstablishment = et_hubinfo_yearestablishment.getText().toString().trim();
                Mobileno = et_hubinfo_mobileno.getText().toString().trim();

                if (Name!=null && Address!=null && YearOfEstablishment!=null && Mobileno!=null){
                    Intent intent = new Intent(HubInfo_Activity.this,HubListsActivity.class);
                    intent.putExtra("Hub_Name",Name);
                    intent.putExtra("Hub_Address",Address);
                    intent.putExtra("Hub_YearOfEstablishment",YearOfEstablishment);
                    intent.putExtra("PhoneNo",Mobileno);
                    startActivity(intent);
                }else {
                    AlertBoxClasses.SimpleAlertBox(HubInfo_Activity.this,"Check all fields");
                }

            }
        });
    }


}