package com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class InfoFragment extends Fragment {

   LinearLayout linear_personalinfo,linear_editpersonalinfo;
   TextView txt_editgeneralinfo,txt_generalinfo,txt_profile_status,txt_profile_userid,txt_profile_city,txt_profile_state,
           txt_profile_school,txt_profile_board,txt_profile_dob,txt_profile_aboutme,txt_profile_address,et_profile_dob,
           txt_profile_fblink,txt_profile_instalink,txt_profile_location,txt_edit_location,txt_edit_social
           ,txt_profile_icse,txt_profile_cbse,txt_userid,txt_edit_persona;
    String date,strtext,Board;
    EditText et_profile_city,et_profile_state,et_profile_school,et_profile_location,et_profile_about,et_profile_address,
            et_profile_fblink,et_profile_instalink,et_profile_interest;
    View view;
    private static final String TAG = "InfoFragment";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    RadioButton radio_profile_icse,radio_profile_cbse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_info, container, false);

        Initialize();


         strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();

         GetProfile();

       radio_profile_cbse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   radio_profile_cbse.setTextColor(getResources().getColor(R.color.theme_blue));
                   radio_profile_icse.setTextColor(getResources().getColor(R.color.black));
                   Board = "CBSE";

           }
       });

       radio_profile_icse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               radio_profile_icse.setTextColor(getResources().getColor(R.color.theme_blue));
               radio_profile_cbse.setTextColor(getResources().getColor(R.color.black));
               Board = "ICSE";
           }
       });

       et_profile_dob.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Calendar cal = Calendar.getInstance();
               int year = cal.get(Calendar.YEAR);
               int month = cal.get(Calendar.MONTH);
               int day = cal.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog dialog = new DatePickerDialog(
                       getActivity(),
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
                et_profile_dob.setText(year + "/" + month + "/" + day);
            }
        };

        txt_generalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });



        txt_edit_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_edit_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });



        txt_editgeneralinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_editpersonalinfo.setVisibility(View.GONE);
                linear_personalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_edit_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_editgeneralinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (et_profile_city.getText().toString().trim().isEmpty()) {
                    et_profile_city.setError("Enter City");
                } else if (et_profile_state.getText().toString().trim().isEmpty()) {
                    et_profile_state.setError("Enter State");
                } else if (et_profile_school.getText().toString().trim().isEmpty()) {
                    et_profile_school.setError("Enter School");
                } else if (et_profile_location.getText().toString().trim().isEmpty()) {
                    et_profile_location.setError("Enter Location");
                } else if (et_profile_interest.getText().toString().trim().isEmpty()) {
                    et_profile_interest.setError("Enter Interest");
                } else if (et_profile_about.getText().toString().trim().isEmpty()) {
                    et_profile_about.setError("Tell something about yourself");
                } else if (et_profile_address.getText().toString().trim().isEmpty()) {
                    et_profile_address.setError("Enter Address");
                } else if (et_profile_fblink.getText().toString().trim().isEmpty()) {
                    et_profile_fblink.setError("Enter your Facebook Profile Link");
                } else if (et_profile_instalink.getText().toString().trim().isEmpty()) {
                    et_profile_instalink.setError("Enter your Instagram Profile Link");
                } else {

                    if (radio_profile_icse.isChecked()) {
                        Board = "ICSE";
                    } else {
                        Board = "CBSE";
                    }
                    EditProfile(strtext,
                            txt_profile_status.getText().toString().trim(),
                            et_profile_city.getText().toString().trim(),
                            et_profile_state.getText().toString().trim(),
                            et_profile_school.getText().toString().trim(),
                            et_profile_location.getText().toString().trim(),
                            et_profile_interest.getText().toString(),
                            Board,
                            et_profile_dob.getText().toString(),
                            et_profile_about.getText().toString().trim(),
                            et_profile_address.getText().toString().trim(),
                            et_profile_fblink.getText().toString().trim(),
                            et_profile_instalink.getText().toString().trim(),
                            SharedPrefManager.getInstance(getActivity()).getUser().getCls(),
                            SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                            SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name()
                    );

                }
            }
        });

        return view;
    }

    private void Initialize() {
        linear_personalinfo = view.findViewById(R.id.linear_personalinfo);
        linear_editpersonalinfo = view.findViewById(R.id.linear_editpersonalinfo);
        txt_editgeneralinfo = view.findViewById(R.id.txt_editgeneralinfo);
        txt_generalinfo = view.findViewById(R.id.txt_generalinfo);
        txt_profile_status = view.findViewById(R.id.txt_profile_status);
        txt_profile_userid = view.findViewById(R.id.txt_profile_userid);
        txt_profile_city = view.findViewById(R.id.txt_profile_city);
        txt_profile_state = view.findViewById(R.id.txt_profile_state);
        txt_profile_school = view.findViewById(R.id.txt_profile_school);
        txt_profile_board = view.findViewById(R.id.txt_profile_board);
        txt_profile_dob = view.findViewById(R.id.txt_profile_dob);
        txt_profile_aboutme = view.findViewById(R.id.txt_profile_aboutme);
        txt_profile_address = view.findViewById(R.id.txt_profile_address);
        txt_profile_fblink = view.findViewById(R.id.txt_profile_fblink);
        txt_profile_instalink = view.findViewById(R.id.txt_profile_instalink);
        txt_profile_location = view.findViewById(R.id.txt_profile_location);
        et_profile_city = view.findViewById(R.id.et_profile_city);
        et_profile_state = view.findViewById(R.id.et_profile_state);
        et_profile_school = view.findViewById(R.id.et_profile_school);
        et_profile_location = view.findViewById(R.id.et_profile_location);
        et_profile_dob = view.findViewById(R.id.et_profile_dob);
        et_profile_about = view.findViewById(R.id.et_profile_about);
        et_profile_address = view.findViewById(R.id.et_profile_address);
        et_profile_fblink = view.findViewById(R.id.et_profile_fblink);
        et_profile_instalink = view.findViewById(R.id.et_profile_instalink);
        txt_edit_location = view.findViewById(R.id.txt_edit_location);
        txt_edit_social = view.findViewById(R.id.txt_edit_social);
        radio_profile_icse = view.findViewById(R.id.radio_profile_icse);
        radio_profile_cbse = view.findViewById(R.id.radio_profile_cbse);
        et_profile_interest = view.findViewById(R.id.et_profile_interest);
        txt_userid = view.findViewById(R.id.txt_userid);
        txt_edit_persona = view.findViewById(R.id.txt_edit_persona);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        txt_profile_icse = view.findViewById(R.id.txt_profile_icse);
//        txt_profile_cbse = view.findViewById(R.id.txt_profile_cbse);
    }

    private void GetProfile(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("userid", strtext);


        JSONObject parameters = new JSONObject(params);




        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.Getprofile+strtext, parameters, new Response.Listener<JSONObject>() {
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

                            if (object.getString("board").equals("cbse")){
                                radio_profile_cbse.setChecked(true);
                                radio_profile_cbse.setTextColor(getResources().getColor(R.color.theme_blue));
                                radio_profile_icse.setTextColor(getResources().getColor(R.color.black));
                                txt_profile_board.setText("CBSE");
                            }else {
                                radio_profile_icse.setChecked(true);
                                radio_profile_icse.setTextColor(getResources().getColor(R.color.theme_blue));
                                radio_profile_cbse.setTextColor(getResources().getColor(R.color.black));
                                txt_profile_board.setText("ICSE");
                            }

                            txt_profile_userid.setText(object.getString("userid"));
                            txt_profile_status.setText(object.getString("profile_status"));
                            txt_profile_city.setText(object.getString("profile_city"));
                            txt_profile_state.setText(object.getString("profile_state"));
                            txt_profile_school.setText(object.getString("profile_school"));
                            txt_profile_location.setText(object.getString("location"));
                            //txt_profile_board.setText(object.getString("board"));
                            txt_profile_dob.setText(object.getString("birthday"));
                            txt_profile_aboutme.setText(object.getString("about_me"));
                            txt_profile_address.setText(object.getString("address"));
                            txt_profile_fblink.setText(object.getString("profile_facebook_link"));
                            txt_profile_instalink.setText(object.getString("insta_link"));

                            et_profile_city.setText(object.getString("profile_city"));
                            et_profile_state.setText(object.getString("profile_state"));
                            et_profile_school.setText(object.getString("profile_school"));
                            et_profile_location.setText(object.getString("location"));
                            et_profile_dob.setText(object.getString("birthday"));
                            et_profile_about.setText(object.getString("about_me"));
                            et_profile_address.setText(object.getString("address"));
                            et_profile_fblink.setText(object.getString("profile_facebook_link"));
                            et_profile_instalink.setText(object.getString("insta_link"));
                            txt_userid.setText(object.getString("userid"));

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

    private void EditProfile(String UserId, String Status,String City, String State, String School, String Location,String interest,
                             String Board,String DOB, String About, String Address, String Fblink, String Instalink,
                             String cls, String last_name, String first_name){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();



        final Map<String, String> params = new HashMap();

        params.put("userid", UserId);
        params.put("status", Status);
        params.put("city", City);
        params.put("state", State);
        params.put("school", School);
        params.put("location", Location);
        params.put("board", Board);
        params.put("birthday", DOB);
        params.put("about_me", About);
        params.put("address", Address);
        params.put("interest", Address);
        params.put("facebook_link", Fblink);
        params.put("insta_link", Instalink);
        params.put("class", cls);
        params.put("last_name", last_name);
        params.put("first_name",first_name );


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.StoreProfile, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();


                    Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                    linear_editpersonalinfo.setVisibility(View.GONE);
                    linear_personalinfo.setVisibility(View.VISIBLE);
                   GetProfile();

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
}