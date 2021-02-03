package com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.starkwiz.starkwiz.Adapter.ProfileAdapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile_Activity extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txt_profile_name,txt_profile_address,txt_class,txt_profile_board,
            txt_profile_status,txt_editprofile,txt_editprofile_save;
    LinearLayout linearedit,linear_profile;
    String strtext,status,city,state,school,location,board,birthday,about_me,interest,address,
            facebook_link,insta_link,cls,last_name,first_name;
    EditText et_profile_address,et_class,et_profile_firstname,et_profile_lastname;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_,container,false);
        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        txt_profile_name= view.findViewById(R.id.txt_profile_name);
        txt_profile_address= view.findViewById(R.id.txt_profile_address);
        txt_class= view.findViewById(R.id.txt_class);
        txt_profile_board= view.findViewById(R.id.txt_profile_board);
        txt_profile_status= view.findViewById(R.id.txt_profile_status);
        txt_editprofile= view.findViewById(R.id.txt_editprofile);
        linearedit= view.findViewById(R.id.linearedit);
        linear_profile= view.findViewById(R.id.linear_profile);
        txt_editprofile_save= view.findViewById(R.id.txt_editprofile_save);
        et_profile_firstname= view.findViewById(R.id.et_profile_firstname);
        et_profile_lastname= view.findViewById(R.id.et_profile_lastname);
        et_profile_address= view.findViewById(R.id.et_profile_address);
        et_class= view.findViewById(R.id.et_class);

        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Achievement"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        txt_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_profile.setVisibility(View.GONE);
                linearedit.setVisibility(View.VISIBLE);

            }
        });

        txt_editprofile_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String clss,fname,lname,adrs;

                clss = et_class.getText().toString().trim();
                fname = et_profile_firstname.getText().toString().trim();
                lname = et_profile_lastname.getText().toString().trim();
                adrs = et_profile_address.getText().toString().trim();



                if (clss!=null || fname!=null || lname!=null || adrs!=null || !clss.equals("null") || !fname.equals("null") || !lname.equals("null") || !adrs.equals("null") ){
                    EditProfile(strtext,status,city,state,school,location,board,birthday,about_me,interest,et_profile_address.getText().toString().trim(),facebook_link,insta_link,clss,lname,fname);
                }else {
                    AlertBoxClasses.SimpleAlertBox(getActivity(),"Please fill all fields.");
                }


            }
        });

        final ProfileAdapter adapter = new ProfileAdapter(getActivity(),getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private void GetProfile(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


         strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.Getprofile+strtext, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String Status=response.getString("success");

                    if (Status.matches("profile found")){

                        String allProfile=response.getString("allProfile");
                        JSONArray array = new JSONArray(allProfile);

                        for (int i = 0 ; i<array.length();i++){
                            JSONObject object = array.getJSONObject(i);



                            if (object.getString("first_name").equals("null") || object.getString("last_name").equals("null") ){

                                txt_profile_name.setText("Edit your proflie");

                            }else{
                                txt_profile_name.setText(object.getString("first_name")+" "+object.getString("last_name"));
                                et_profile_firstname.setText(object.getString("first_name"));
                                et_profile_lastname.setText(object.getString("last_name"));

                            }



                            if (object.getString("address").equals("null")){
                                txt_profile_address.setText(object.getString("city")+", "+object.getString("state"));
                            }else{
                                    txt_profile_address.setText(object.getString("address"));
                                    et_profile_address.setText(object.getString("address"));

                            }


                            if (object.getString("class").equals("null")){
                                txt_class.setText("Edit your proflie");
                            }
                            else {


                                txt_class.setText("Class "+object.getString("class"));
                                et_class.setText(object.getString("class"));
                            }

                            txt_profile_status.setText("status");

                            if (object.getString("board").equals("CBSE")){
                                txt_profile_board.setText("CBSE");
                            }else {
                                txt_profile_board.setText("ICSE");
                            }

                            txt_profile_status.setText(object.getString("status"));

                            status = object.getString("status");
                            city = object.getString("city");
                            state = object.getString("state");
                            school = object.getString("school");
                            location = object.getString("location");
                            board = object.getString("board");
                            birthday = object.getString("date_of_birth");
                            about_me = object.getString("about_me");
                            interest = object.getString("profile_interest");
                            address = object.getString("address");
                            facebook_link = object.getString("profile_facebook_link");
                            insta_link = object.getString("insta_link");
                            cls = object.getString("class");
                            last_name = object.getString("last_name");
                            first_name = object.getString("first_name");





                        }

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

                Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(getActivity()).add(jsonRequest);

    }

    private void EditProfile(String strtext, String status, String city,String state, String school, String location,
                             String board, String birthday, String about_me, String interest, String address,
                             String facebook_link, String insta_link, String cls, String last_name, String first_name){


            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //HttpsTrustManager.allowAllSSL();

            final Map<String, String> params = new HashMap();

            params.put("userid", strtext);
            params.put("profile_status", status);
            params.put("profile_city", city);
            params.put("profile_state", state);
            params.put("profile_school", school);
            params.put("location", location);
            params.put("board", board);
            params.put("birthday", birthday);
            params.put("about_me", about_me);
            params.put("profile_interest", interest);
            params.put("address", address);
            params.put("profile_facebook_link", facebook_link);
            params.put("insta_link", insta_link);
            params.put("class", cls);
            params.put("last_name", last_name);
            params.put("first_name", first_name);


            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.StoreProfile, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    progressDialog.dismiss();
                    try {
                        if (response.getString("message").equals("Success")) {

                            AlertBoxClasses.SimpleAlertBox(getActivity(), "Saved Successfully");
                            linear_profile.setVisibility(View.VISIBLE);
                            linearedit.setVisibility(View.GONE);
                            GetProfile();
                        } else {
                            Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                }
            });


            Volley.newRequestQueue(getActivity()).add(jsonRequest);



    }

    @Override
    public void onStart() {
        super.onStart();
        GetProfile();
    }
}