package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.R;

public class UserSelection_Activity extends AppCompatActivity {

    TextView txt_userselection_knowmore,txt_userselection_student,txt_userselection_parent,txt_userselection_teacher,txt_userselection_hub;
    Button btn_userselection;
    String Student, Parent, Teacher, Hub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection_);

        txt_userselection_knowmore = findViewById(R.id.txt_userselection_knowmore);
        btn_userselection = findViewById(R.id.btn_userselection);
        txt_userselection_student = findViewById(R.id.txt_userselection_student);
        txt_userselection_parent = findViewById(R.id.txt_userselection_parent);
        txt_userselection_teacher = findViewById(R.id.txt_userselection_teacher);
        txt_userselection_hub = findViewById(R.id.txt_userselection_hub);

        btn_userselection.setEnabled(false);


        txt_userselection_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_userselection_student.setBackground(getResources().getDrawable(R.drawable.round_textview_clicked));
                txt_userselection_parent.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_teacher.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_hub.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_student.setTextColor(getResources().getColor(R.color.white));
                txt_userselection_teacher.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_parent.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_hub.setTextColor(getResources().getColor(R.color.darkgrey));

                Student="1";
                Parent="null";
                Teacher="null";
                Hub="null";

                btn_userselection.setEnabled(true);

            }
        });

        txt_userselection_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_userselection_teacher.setBackground(getResources().getDrawable(R.drawable.round_textview_clicked));
                txt_userselection_student.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_parent.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_hub.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_teacher.setTextColor(getResources().getColor(R.color.white));
                txt_userselection_student.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_parent.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_hub.setTextColor(getResources().getColor(R.color.darkgrey));

                Teacher = "2";
                Parent="null";
                Student="null";
                Hub="null";

                btn_userselection.setEnabled(true);

            }
        });

        txt_userselection_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_userselection_parent.setBackground(getResources().getDrawable(R.drawable.round_textview_clicked));
                txt_userselection_student.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_teacher.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_hub.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_parent.setTextColor(getResources().getColor(R.color.white));
                txt_userselection_student.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_teacher.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_hub.setTextColor(getResources().getColor(R.color.darkgrey));

                Parent="3";
                Student="null";
                Teacher="null";
                Hub="null";

                btn_userselection.setEnabled(true);

            }
        });

        txt_userselection_hub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_userselection_hub.setBackground(getResources().getDrawable(R.drawable.round_textview_clicked));
                txt_userselection_student.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_teacher.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_parent.setBackground(getResources().getDrawable(R.drawable.round_textview));
                txt_userselection_hub.setTextColor(getResources().getColor(R.color.white));
                txt_userselection_student.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_teacher.setTextColor(getResources().getColor(R.color.darkgrey));
                txt_userselection_parent.setTextColor(getResources().getColor(R.color.darkgrey));

                Hub="4";
                Student="null";
                Parent="null";
                Teacher="null";

                btn_userselection.setEnabled(true);
            }
        });

        btn_userselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Student.equals("1") && Parent.equals("null") && Teacher.equals("null") && Hub.equals("null")){
                    Intent Student_intent = new Intent(UserSelection_Activity.this, Signup_Personal_Activity.class);
                    Student_intent.putExtra("Student_intent","Student_intent");
                    startActivity(Student_intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else if(Parent.equals("3")&& Student.equals("null")&& Teacher.equals("null")&& Hub.equals("null")){

                    Intent Parent_intent = new Intent(UserSelection_Activity.this,Signup_Personal_Activity.class);
                    Parent_intent.putExtra("Parent_intent","Parent_intent");
                    startActivity(Parent_intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else if(Teacher.equals("2")&& Student.equals("null") && Parent.equals("null") &&Hub.equals("null")){
                    Intent Teacher_intent = new Intent(UserSelection_Activity.this,Signup_Personal_Activity.class);
                    Teacher_intent.putExtra("Teacher_intent","Teacher_intent");
                    startActivity(Teacher_intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else if (Hub.equals("4")&& Student.equals("null")&& Teacher.equals("null")&& Parent.equals("null")){


                     Intent Hub_intent = new Intent(UserSelection_Activity.this, Signup_Personal_Activity.class);
                    Hub_intent.putExtra("Hub_intent", "Hub_intent");
                    startActivity(Hub_intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                }else {

                    Toast.makeText(UserSelection_Activity.this, "Please Choose One of these", Toast.LENGTH_SHORT).show();
                }


            }
        });

        txt_userselection_knowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(UserSelection_Activity.this);
                dialog.setContentView(R.layout.alert_user_type_info);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                LinearLayout lineartype,linear_studenttype,linear_parenttype,linear_teachertype,linear_hubtype;
                TextView txt_studenttype,txt_parentype,txt_teachertype,txt_hubtype;
                Button btn_userselection;

                lineartype = dialog.findViewById(R.id.lineartype);
                linear_studenttype = dialog.findViewById(R.id.linear_studenttype);
                linear_parenttype = dialog.findViewById(R.id.linear_parenttype);
                linear_teachertype = dialog.findViewById(R.id.linear_teachertype);
                linear_hubtype = dialog.findViewById(R.id.linear_hubtype);

                txt_studenttype = dialog.findViewById(R.id.txt_studenttype);
                txt_parentype = dialog.findViewById(R.id.txt_parentype);
                txt_teachertype = dialog.findViewById(R.id.txt_teachertype);
                txt_hubtype = dialog.findViewById(R.id.txt_hubtype);

                btn_userselection = dialog.findViewById(R.id.btn_userselection);

                LayoutInflater inflater = LayoutInflater
                        .from(getApplicationContext());
                View layview = inflater.inflate(R.layout.student_type, null);
                lineartype.addView(layview);
                TextView txt_contiue;
                LinearLayout linearcontiue;
                txt_contiue = layview.findViewById(R.id.txt_contiue);
                linearcontiue = layview.findViewById(R.id.linearcontiue);

                btn_userselection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                txt_contiue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        txt_contiue.setVisibility(View.GONE);
                        linearcontiue.setVisibility(View.VISIBLE);

                    }
                });



                txt_studenttype.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linear_studenttype.setVisibility(View.VISIBLE);
                        linear_parenttype.setVisibility(View.GONE);
                        linear_teachertype.setVisibility(View.GONE);
                        linear_hubtype.setVisibility(View.GONE);
                        lineartype.removeAllViews();
                        LayoutInflater inflater = LayoutInflater
                                .from(getApplicationContext());
                        View layview = inflater.inflate(R.layout.student_type, null);
                        lineartype.addView(layview);
                        TextView txt_contiue;
                        LinearLayout linearcontiue;
                        txt_contiue = layview.findViewById(R.id.txt_contiue);
                        linearcontiue = layview.findViewById(R.id.linearcontiue);

                        txt_contiue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                txt_contiue.setVisibility(View.GONE);
                                linearcontiue.setVisibility(View.VISIBLE);

                            }
                        });


                    }
                });

                txt_parentype.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lineartype.removeAllViews();
                        LayoutInflater inflater = LayoutInflater
                                .from(getApplicationContext());
                        View layview = inflater.inflate(R.layout.parent_type, null);
                        lineartype.addView(layview);
                        linear_parenttype.setVisibility(View.VISIBLE);
                        linear_studenttype.setVisibility(View.GONE);
                        linear_teachertype.setVisibility(View.GONE);
                        linear_hubtype.setVisibility(View.GONE);


                    }
                });

                txt_teachertype.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lineartype.removeAllViews();
                        LayoutInflater inflater = LayoutInflater
                                .from(getApplicationContext());
                        View layview = inflater.inflate(R.layout.teacher_type, null);
                        lineartype.addView(layview);
                        linear_teachertype.setVisibility(View.VISIBLE);
                        linear_studenttype.setVisibility(View.GONE);
                        linear_parenttype.setVisibility(View.GONE);
                        linear_hubtype.setVisibility(View.GONE);


                    }
                });

                txt_hubtype.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lineartype.removeAllViews();
                        LayoutInflater inflater = LayoutInflater
                                .from(getApplicationContext());
                        View layview = inflater.inflate(R.layout.hub_type, null);
                        lineartype.addView(layview);
                        linear_hubtype.setVisibility(View.VISIBLE);
                        linear_studenttype.setVisibility(View.GONE);
                        linear_parenttype.setVisibility(View.GONE);
                        linear_teachertype.setVisibility(View.GONE);


                    }
                });



                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            String strtext = SharedPrefManager.getInstance(UserSelection_Activity.this).getUser().getAccess_token();
            Log.d("accesstoken", strtext);

            if (!strtext.equals("")) {

                startActivity(new Intent(UserSelection_Activity.this, Dasboard_Activity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}