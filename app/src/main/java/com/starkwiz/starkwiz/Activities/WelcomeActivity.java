package com.starkwiz.starkwiz.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Adapter.TermCondition_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.TermCondition_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btn_getstarted;
    private TextView txt_welcome_termsserives;
    CheckBox welcome_checkbox;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    RecyclerView lv_termcondition;
    ArrayList<TermCondition_ModelClass>list_term;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btn_getstarted = findViewById(R.id.btn_getstarted);
        txt_welcome_termsserives = findViewById(R.id.txt_welcome_termsserives);
        welcome_checkbox = findViewById(R.id.welcome_checkbox);

        list_term= new ArrayList<>();

        layouts = new int[]{
                R.layout.getstarted_layout_two,
                R.layout.getstarted_layout_one};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == layouts.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        if (welcome_checkbox.isChecked()){
            SharedPreferences sp = getSharedPreferences("key", 0);
            SharedPreferences.Editor sedt = sp.edit();
            sedt.putString("checked", "checked");
            sedt.commit();
        }

        btn_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (welcome_checkbox.isChecked()){
                    SharedPreferences sp = getSharedPreferences("key", 0);
                    SharedPreferences.Editor sedt = sp.edit();
                    sedt.putString("checked", "checked");
                    sedt.commit();
                    startActivity(new Intent(WelcomeActivity.this,UserSelection_Activity.class));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
                else {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(WelcomeActivity.this)
                            .setMessage("Please Accept Term & Condition")
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    welcome_checkbox.setChecked(true);
                                    SharedPreferences sp = getSharedPreferences("key", 0);
                                    SharedPreferences.Editor sedt = sp.edit();
                                    sedt.putString("checked", "checked");
                                    sedt.commit();
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alert11 = alertDialog.create();
                    alert11.show();
                }
            }
        });

        txt_welcome_termsserives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(WelcomeActivity.this);
                dialog.setContentView(R.layout.alert_terms_services);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Button alert_yesgotit,alert_nothanks;
                alert_yesgotit = dialog.findViewById(R.id.alert_yesgotit);
                alert_nothanks = dialog.findViewById(R.id.alert_nothanks);
                lv_termcondition = dialog.findViewById(R.id.lv_termcondition);
                lv_termcondition.setHasFixedSize(true);
                lv_termcondition.setLayoutManager(new LinearLayoutManager(WelcomeActivity.this));


                GetTermCondition();

                alert_yesgotit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        welcome_checkbox.setChecked(true);
                        SharedPreferences sp = getSharedPreferences("key", 0);
                        SharedPreferences.Editor sedt = sp.edit();
                        sedt.putString("checked", "checked");
                        sedt.commit();
                        dialog.cancel();
                    }
                });
                alert_nothanks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });


                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("key", 0);
        String strtext = sp.getString("checked","");

        if (!strtext.isEmpty()){
            welcome_checkbox.setChecked(true);
            startActivity(new Intent(WelcomeActivity.this,UserSelection_Activity.class));
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
    }

    public void GetTermCondition(){
        list_term.clear();
        ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, URLS.getcondition, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();

                        // display response
                        for (int j=0 ; j<response.length() ; j++){

                            try {
                                JSONObject object = response.getJSONObject(j);

                                TermCondition_ModelClass modelClass = new TermCondition_ModelClass(
                                        object.getString("terms_and_conditions")
                                );

                                list_term.add(modelClass);
                                TermCondition_Adapter adapter = new TermCondition_Adapter(list_term,WelcomeActivity.this);
                                lv_termcondition.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        Volley.newRequestQueue(WelcomeActivity.this).add(getRequest);

    }
}