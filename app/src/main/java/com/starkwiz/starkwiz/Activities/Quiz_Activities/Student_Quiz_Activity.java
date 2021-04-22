package com.starkwiz.starkwiz.Activities.Quiz_Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.animation.Animator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.Dasboard_Activity;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Quiz_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Student_Quiz_Activity extends AppCompatActivity {

    String selected_testid, selected_module,selected_subject,selected_hour,selected_minutes,test_id,selected_subjectid,
            module_id,mark,user_id,total_question,total_marks_obtained,total_points_obtained,selected_totalmark,
            total_time,total_acquired_time,total_correct_answer,GP,selected_moduleid;
    TextView txt_chapter,txt_noofqn,txt_qn,txt_quiz_subject,txt_qn_hint,txtfixture,txt_otptimer,txt_quiz_mark;
    Button optionone,optiontwo,optionthree,optionfour;
    Button btn_next,btn_skip;
    LinearLayout optionContainer;
    private int count=0;
    ArrayList<Quiz_Modelclass>list_quiz;
    ArrayList<String>list_gp;
    int position = 0;
    int score=0,markobtain=0;
    ImageView img_qn;
    int minutes,millisecond;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    Chronometer timer;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__quiz_);

        txt_chapter=findViewById(R.id.txt_chapter);
        txt_noofqn=findViewById(R.id.txt_noofqn);
        txt_qn=findViewById(R.id.txt_qn);
        optionone=findViewById(R.id.optionone);
        optiontwo=findViewById(R.id.optiontwo);
        optionthree=findViewById(R.id.optionthree);
        optionfour=findViewById(R.id.optionfour);
        btn_next=findViewById(R.id.btn_next);
        btn_skip=findViewById(R.id.btn_skip);
        img_qn=findViewById(R.id.img_qn);
        optionContainer=findViewById(R.id.optionContainer);
        txt_quiz_subject=findViewById(R.id.txt_quiz_subject);
        txt_qn_hint=findViewById(R.id.txt_qn_hint);
        txtfixture=findViewById(R.id.txtfixture);
        txt_otptimer=findViewById(R.id.txt_otptimer);
        timer=findViewById(R.id.timer);
        txt_quiz_mark=findViewById(R.id.txt_quiz_mark);
        list_quiz=new ArrayList<>();
        list_gp=new ArrayList<>();
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        timer.stop();
        timer.setVisibility(View.GONE);
        user_id = SharedPrefManager.getInstance(Student_Quiz_Activity.this).getUser().getId();

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month = month_date.format(cal.getTime());

        txtfixture.setText("FIXTURE: "+month);

        try {

            selected_testid = getIntent().getStringExtra("selected_testid");
            selected_module = getIntent().getStringExtra("selected_module");
            selected_subject = getIntent().getStringExtra("selected_subject");
            selected_hour = getIntent().getStringExtra("selected_hour");
            selected_minutes = getIntent().getStringExtra("selected_minutes");
            selected_totalmark = getIntent().getStringExtra("selected_totalmark");
            selected_subjectid = getIntent().getStringExtra("selected_subjectid");
            selected_moduleid = getIntent().getStringExtra("selected_moduleid");

            minutes = (Integer.parseInt(selected_hour)*60)+Integer.parseInt(selected_minutes);

            millisecond = minutes*60000;

            Log.d("timer", String.valueOf(millisecond));



            new CountDownTimer(millisecond, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timer.stop();
                    String v = String.format("%02d", millisUntilFinished / 60000);
                    int va = (int) ((millisUntilFinished % 60000) / 1000);
                    txt_otptimer.setText(v + ":" + String.valueOf( va));
                }

                @Override
                public void onFinish() {
                    txt_otptimer.setText("Times up");
                    Vibrator vibrator = (Vibrator) Student_Quiz_Activity.this
                            .getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(2000);

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Student_Quiz_Activity.this)
                            .setMessage("Times up. Would you like to")
                            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    timer.start();
                                    timer.setVisibility(View.VISIBLE);

                                    Log.d("timer",String.valueOf(timer.getFormat()));
                                    dialogInterface.cancel();
                                }
                            })
                            .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert11 = alertDialog.create();
                    alert11.show();


                }
            }.start();



            txt_chapter.setText(selected_module);
            txt_quiz_subject.setText(selected_subject);



        } catch (Exception e){
            e.printStackTrace();
        }

        try {

        ProgressDialog progressDialog = new ProgressDialog(Student_Quiz_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("module_id", selected_testid);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.GetQuestion, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    if (response.equals("[]")){
                        AlertBoxClasses.SimpleAlertBox(Student_Quiz_Activity.this,"No Question found.");
                    }else {

                        JSONArray array = response.getJSONArray("question");


                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object = array.getJSONObject(i);

                            String mark = object.getString("mark");

                            if (mark.equals("null")) {
                                mark = "0";
                            } else {
                                mark = object.getString("mark");
                            }


                            Quiz_Modelclass modelClass = new Quiz_Modelclass(
                                    object.getString("question_id"),
                                    object.getString("question"),
                                    object.getString("correct_answer"),
                                    object.getString("wrong_answer_1"),
                                    object.getString("wrong_answer_2"),
                                    object.getString("wrong_answer_3"),
                                    object.getString("wrong_answer_4"),
                                    object.getString("image"),
                                    object.getString("hint"),
                                    object.getString("explanation"),
                                    mark
                            );


                            list_quiz.add(modelClass);

                            test_id = object.getString("test_id");
                            module_id = object.getString("module_id");


                        }

                        PlayAnim(txt_qn, 0, list_quiz.get(position).getQuestion());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Toast.makeText(Student_Quiz_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(Student_Quiz_Activity.this).add(jsonRequest);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position+1==list_quiz.size()){
                    total_question = String.valueOf(list_quiz.size());
                    AlertBoxClasses.SimpleAlertBox(Student_Quiz_Activity.this,"You have attend all questions");
                    Vibrator vibrator = (Vibrator) Student_Quiz_Activity.this
                            .getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                    //Score(user_id,test_id,module_id,selected_subject,total_question,mark);
                }else {
                    btn_next.setEnabled(false);
                    btn_next.setAlpha(0.7f);
                    enableoption(true);
                    position++;
                    if (position == list_quiz.size()) {
                        return;
                    }
                    count = 0;
                    PlayAnim(txt_qn,0,list_quiz.get(position).getQuestion());

                }

            }
        });


        for (int j = 0; j<4;j++){
            optionContainer.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((Button) view);
                }
            });
        }

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position+1==list_quiz.size()){

                    AlertBoxClasses.SimpleAlertBox(Student_Quiz_Activity.this,"You have attend all questions");
                }else {
                    enableoption(true);
                    count = 0;
                    position++;
                    PlayAnim(txt_qn,0,list_quiz.get(position).getQuestion());

                }
            }
        });



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void PlayAnim(View view, int value,String data){

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0 && count<4){
                    String option="";
                    if (count == 0){
                        option = list_quiz.get(position).getWrong_answer_1();
                        mark = list_quiz.get(position).getMark();
                    }else if (count==1){
                        option = list_quiz.get(position).getWrong_answer_2();
                    }else if (count==2){
                        option = list_quiz.get(position).getWrong_answer_3();
                    }else if (count==3){
                        option = list_quiz.get(position).getWrong_answer_4();
                    }
                    PlayAnim(optionContainer.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if (value == 0 ){
                    try {
                        ((TextView)view).setText(data);
                        txt_noofqn.setText(position+1+"/"+list_quiz.size());
                    }catch (Exception e){
                        ((Button)view).setText(data);
                    }
                   view.setTag(data);
                    txt_qn_hint.setText("Hint: "+list_quiz.get(position).getTxt_qn_hint());

                    txt_quiz_mark.setText("Mark\n"+list_quiz.get(position).getMark());

                    if (!list_quiz.get(position).getImage().equals("null")) {
                        String image = list_quiz.get(position).getImage();
                        img_qn.setVisibility(View.VISIBLE);
                        image = image.replace("data:image/png;base64,","");
                        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        img_qn.setImageBitmap(decodedByte);

                        img_qn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final Dialog dialog = new Dialog(Student_Quiz_Activity.this);
                                dialog.setContentView(R.layout.custom_fullimage);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                ImageView img_fullview;

                                img_fullview = dialog.findViewById(R.id.img_fullview);

                                img_fullview.setImageBitmap(decodedByte);

                                dialog.show();
                                Window window = dialog.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            }
                        });


                    }else {
                        img_qn.setVisibility(View.GONE);
                    }
                    PlayAnim(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void checkAnswer(Button selected_option){

        try {


            enableoption(false);
            btn_next.setEnabled(true);
            //btn_next.setAlpha(1);
            if (selected_option.getText().toString().equals(list_quiz.get(position).getCorrect_answer())) {
                //correct
                String time = txt_otptimer.getText().toString().trim();

                String[] separated = time.split(":");
                time = separated[0];
                time = String.valueOf(Integer.parseInt(time)+Integer.parseInt("1"));

                String question = String.valueOf(list_quiz.size());


                score++;

                total_points_obtained = String.valueOf(((Integer.parseInt(mark)*score))+Integer.parseInt(time)*3);
                Log.d("time",total_points_obtained);
                markobtain= Integer.parseInt(mark)+markobtain;

                total_marks_obtained =String.valueOf(markobtain);
                GP = String.valueOf((Integer.parseInt(total_marks_obtained)*10));
                Log.d("totalmark",total_marks_obtained);
                total_points_obtained = String.valueOf((Integer.parseInt(GP)+(Integer.parseInt(time)*3)));
                Log.d("logcat",total_points_obtained);

                Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.correct);
                selected_option.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);


                Score(user_id,
                        test_id,
                        module_id,
                        selected_subjectid,
                        String.valueOf(Integer.parseInt(GP)+minutes),
                        selected_subject,
                        question,
                        selected_totalmark,
                        total_marks_obtained,
                        total_points_obtained,
                        String.valueOf(minutes),
                        GP,
                        time,
                        String.valueOf(score));


            } else {
                //incorrect

                Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.remove);
                selected_option.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                //selected_option.setBackground(drawable);
                Button correctoption = (Button) optionContainer.findViewWithTag(list_quiz.get(position).getCorrect_answer());


                Drawable crtimg = getApplicationContext().getResources().getDrawable(R.drawable.correct);
                correctoption.setCompoundDrawablesWithIntrinsicBounds(crtimg, null, null, null);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void  enableoption(boolean enable){

        try {
            for (int j = 0; j<4;j++){

                optionContainer.getChildAt(j).setEnabled(enable);

                if (enable){
                    Drawable drawable = new ColorDrawable(Color.parseColor("#ffffff"));
                    // Wrap the drawable so that future tinting calls work
                    // on pre-v21 devices. Always use the returned drawable.
                    drawable = DrawableCompat.wrap(drawable);

                    optionContainer.getChildAt(j).setBackground(drawable);

                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.checkbox);
                    optionone.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    optiontwo.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    optionthree.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    optionfour.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                }
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            img_qn.setScaleX(mScaleFactor);
            img_qn.setScaleY(mScaleFactor);
            return true;
        }
    }

    public void Score(String user_id, String test_id, String module_id, String subject_id,String total_points,
                      String subject_name, String total_question, String total_marks, String total_marks_obtained,
                      String total_points_obtained, String total_time,String gp, String total_acquired_time, String total_correct_answer){

        ProgressDialog progressDialog = new ProgressDialog(Student_Quiz_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("user_id", user_id);
        params.put("test_id", test_id);
        params.put("module_id", module_id);
        params.put("subject_id", subject_id);
        params.put("subject_name", subject_name);
        params.put("total_question", total_question);
        params.put("total_marks", total_marks);
        params.put("total_marks_obtained", total_marks_obtained);
        params.put("total_points_obtained", total_points_obtained);
        params.put("total_time", total_time);
        params.put("total_gp", gp);
        params.put("total_acquired_time", total_acquired_time);
        params.put("total_correct_answer", total_correct_answer);
        params.put("total_points", total_points);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createscore, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                Log.d("success","success");


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();

                Log.d("error","error");

                Toast.makeText(Student_Quiz_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(Student_Quiz_Activity.this).add(jsonRequest);
    }

    @Override
    public void onBackPressed() {

        final androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Student_Quiz_Activity.this)
                .setMessage("Are you sure you want to Leave Quiz?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Student_Quiz_Activity.this, Dasboard_Activity.class));
                        finish();
                    }
                });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert11 = alertDialog.create();
        alert11.show();
    }
}