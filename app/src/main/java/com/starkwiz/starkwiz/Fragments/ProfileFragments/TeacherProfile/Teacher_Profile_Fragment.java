package com.starkwiz.starkwiz.Fragments.ProfileFragments.TeacherProfile;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.Interest_Adapter;
import com.starkwiz.starkwiz.Adapter.Tabs_Adapter.TeacherAdapter;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.InterestModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Teacher_Profile_Fragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    String strtext,status,city,state,school,location,board,birthday,about_me,interest,address,
            facebook_link,insta_link,cls,last_name,first_name,encodedImage,image,User_ID;
    TextView txt_teacher_profilename,txt_teacher_profileaddress,txt_teacher_profileprofession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher__profile_, container, false);
        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);

        txt_teacher_profilename = view.findViewById(R.id.txt_teacher_profilename);
        txt_teacher_profileaddress = view.findViewById(R.id.txt_teacher_profileaddress);
        txt_teacher_profileprofession = view.findViewById(R.id.txt_teacher_profileprofession);

        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TeacherAdapter adapter = new TeacherAdapter(getActivity(),getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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
        GetProfile();
        return view;
    }

    private void GetProfile(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("user_id", strtext);


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.getteacherprofile, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {


                    String Status=response.getString("message");

                    if (Status.matches("Success")){

                        String allProfile=response.getString("allProfile");
                        JSONArray array = new JSONArray(allProfile);

                        for (int i = 0 ; i<array.length();i++) {
                            JSONObject object = array.getJSONObject(i);


                            txt_teacher_profilename.setText(object.getString("first_name")+" "+object.getString("last_name"));
                            txt_teacher_profileaddress.setText(object.getString("district")+", "+object.getString("state"));
                            txt_teacher_profileprofession.setText(object.getString("qualification_degree"));
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
}