package com.starkwiz.starkwiz.Fragments.ProfileFragments.ParentProfile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.Interest_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.InterestModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Info_Parent_Fragment extends Fragment {

    LinearLayout linear_personalinfo,linear_editpersonalinfo;
    TextView txt_editgeneralinfo,txt_generalinfo,txt_parent_usertid,txt_parent_state,txt_parent_city,txt_edit_userid,et_parent_dob,
            txt_parent_education,txt_parent_dob,txt_parent_aboutme,txt_parent_address,txt_parent_facebook,txt_parent_instagram,
            txt_photo_edit,txt_location_edit,txt_social_edit,txt_persona_save,txt_location_save,txt_social_save;
    String date,strtext,Board,image,image1,image2,image3,image4,image5,image6,Interest,mobile_number,gender,intrst,
            profile_image_one,profile_image_two,profile_image_three,profile_image_four,profile_image_five,profile_image_six,
            Image_One,Image_Two,Image_Three,Image_Four,Image_Five,Image_Six,city,state,school,location,date_of_birth,profession,
            about_me,address,profile_facebook_link,insta_link,firstname,lastnamme,no_of_children;
    EditText et_parent_city,et_parent_state,et_parent_education,et_parent_aboutme,et_parent_address,
            et_parent_fb,et_parent_insta,et_parent_interest;
    ImageView img_edit_one,img_edit_two,img_edit_three,img_edit_four,img_edit_five,img_edit_six,
            img_profile_one,img_profile_two,img_profile_three,img_profile_four,img_profile_five,img_profile_six;
    ArrayList<InterestModelClass>list_interest;
    RecyclerView lv_interest,lv_editinterest;
    public static final int PICK_IMAGE = 1;
    Bitmap bitmap_one;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "InfoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info__parent_, container, false);

        linear_personalinfo = view.findViewById(R.id.linear_personalinfo);
        linear_editpersonalinfo = view.findViewById(R.id.linear_editpersonalinfo);
        txt_editgeneralinfo = view.findViewById(R.id.txt_editgeneralinfo);
        txt_generalinfo = view.findViewById(R.id.txt_generalinfo);
        txt_parent_usertid = view.findViewById(R.id.txt_parent_usertid);
        txt_parent_state = view.findViewById(R.id.txt_parent_state);
        txt_parent_city = view.findViewById(R.id.txt_parent_city);
        txt_parent_education = view.findViewById(R.id.txt_parent_education);
        txt_parent_dob = view.findViewById(R.id.txt_parent_dob);
        txt_parent_aboutme = view.findViewById(R.id.txt_parent_aboutme);
        txt_parent_address = view.findViewById(R.id.txt_parent_address);
        txt_parent_facebook = view.findViewById(R.id.txt_parent_facebook);
        txt_parent_instagram = view.findViewById(R.id.txt_parent_instagram);
        txt_edit_userid = view.findViewById(R.id.txt_edit_userid);
        txt_photo_edit = view.findViewById(R.id.txt_photo_edit);
        txt_location_edit = view.findViewById(R.id.txt_location_edit);
        txt_social_edit = view.findViewById(R.id.txt_social_edit);
        txt_persona_save = view.findViewById(R.id.txt_persona_save);
        txt_location_save = view.findViewById(R.id.txt_location_save);
        txt_social_save = view.findViewById(R.id.txt_social_save);


        et_parent_city = view.findViewById(R.id.et_parent_city);
        et_parent_state = view.findViewById(R.id.et_parent_state);
        et_parent_dob = view.findViewById(R.id.et_parent_dob);
        et_parent_aboutme = view.findViewById(R.id.et_parent_aboutme);
        et_parent_address = view.findViewById(R.id.et_parent_address);
        et_parent_fb = view.findViewById(R.id.et_parent_fb);
        et_parent_insta = view.findViewById(R.id.et_parent_insta);
        et_parent_interest = view.findViewById(R.id.et_parent_interest);
        et_parent_education = view.findViewById(R.id.et_parent_education);

        img_edit_one = view.findViewById(R.id.img_edit_one);
        img_edit_two = view.findViewById(R.id.img_edit_two);
        img_edit_three = view.findViewById(R.id.img_edit_three);
        img_edit_four = view.findViewById(R.id.img_edit_four);
        img_edit_five = view.findViewById(R.id.img_edit_five);
        img_edit_six = view.findViewById(R.id.img_edit_six);

        img_profile_one = view.findViewById(R.id.img_profile_one);
        img_profile_two = view.findViewById(R.id.img_profile_two);
        img_profile_three = view.findViewById(R.id.img_profile_three);
        img_profile_four = view.findViewById(R.id.img_profile_four);
        img_profile_five = view.findViewById(R.id.img_profile_five);
        img_profile_six = view.findViewById(R.id.img_profile_six);

        lv_interest = view.findViewById(R.id.lv_interest);
        lv_editinterest = view.findViewById(R.id.lv_editinterest);

        list_interest = new ArrayList<>();

        lv_interest.setHasFixedSize(true);
        lv_interest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        lv_editinterest.setHasFixedSize(true);
        lv_editinterest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        GetProfile();

        et_parent_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                et_parent_dob.setText(year + "/" + month + "/" + day);
            }
        };

        txt_generalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_photo_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_location_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_social_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_personalinfo.setVisibility(View.GONE);
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
            }
        });

        txt_editgeneralinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_parent_city.getText().toString().trim().isEmpty()) {
                    et_parent_city.setError("Enter City");
                } else if (et_parent_state.getText().toString().trim().isEmpty()) {
                    et_parent_state.setError("Enter State");
                }  else if (et_parent_aboutme.getText().toString().trim().isEmpty()) {
                    et_parent_aboutme.setError("Tell something about yourself");
                } else if (et_parent_address.getText().toString().trim().isEmpty()) {
                    et_parent_address.setError("Enter Address");
                } else if (et_parent_fb.getText().toString().trim().isEmpty()) {
                    et_parent_fb.setError("Enter your Facebook Profile Link");
                } else if (et_parent_insta.getText().toString().trim().isEmpty()) {
                    et_parent_insta.setError("Enter your Instagram Profile Link");
                }else {

                     intrst = Interest+et_parent_interest.getText().toString()+",";

                    EditProfile(strtext,
                            firstname,
                            lastnamme,
                            et_parent_dob.getText().toString().trim(),
                            mobile_number,
                            gender,
                            profession,
                            et_parent_education.getText().toString().trim(),
                            no_of_children,
                            intrst,
                            et_parent_fb.getText().toString().trim(),
                            et_parent_insta.getText().toString().trim(),
                            et_parent_aboutme.getText().toString().trim(),
                            et_parent_address.getText().toString().trim(),
                            et_parent_city.getText().toString().trim(),
                            et_parent_state.getText().toString().trim());
                }
            }
        });

        txt_persona_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (et_parent_city.getText().toString().trim().isEmpty()) {
                    et_parent_city.setError("Enter City");
                } else if (et_parent_state.getText().toString().trim().isEmpty()) {
                    et_parent_state.setError("Enter State");
                }  else if (et_parent_aboutme.getText().toString().trim().isEmpty()) {
                    et_parent_aboutme.setError("Tell something about yourself");
                } else if (et_parent_address.getText().toString().trim().isEmpty()) {
                    et_parent_address.setError("Enter Address");
                } else if (et_parent_fb.getText().toString().trim().isEmpty()) {
                    et_parent_fb.setError("Enter your Facebook Profile Link");
                } else if (et_parent_insta.getText().toString().trim().isEmpty()) {
                    et_parent_insta.setError("Enter your Instagram Profile Link");
                }else {

                     intrst = Interest+et_parent_interest.getText().toString()+",";

                    EditProfile(strtext,firstname,lastnamme,et_parent_dob.getText().toString().trim(),mobile_number,
                            gender,profession,et_parent_education.getText().toString().trim(),
                            no_of_children,intrst,et_parent_fb.getText().toString().trim(),et_parent_insta.getText().toString().trim(),
                            et_parent_aboutme.getText().toString().trim(),et_parent_address.getText().toString().trim(),
                            et_parent_city.getText().toString().trim(), et_parent_state.getText().toString().trim());
                }
            }
        });

        txt_location_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_parent_city.getText().toString().trim().isEmpty()) {
                    et_parent_city.setError("Enter City");
                } else if (et_parent_state.getText().toString().trim().isEmpty()) {
                    et_parent_state.setError("Enter State");
                }  else if (et_parent_aboutme.getText().toString().trim().isEmpty()) {
                    et_parent_aboutme.setError("Tell something about yourself");
                } else if (et_parent_address.getText().toString().trim().isEmpty()) {
                    et_parent_address.setError("Enter Address");
                } else if (et_parent_fb.getText().toString().trim().isEmpty()) {
                    et_parent_fb.setError("Enter your Facebook Profile Link");
                } else if (et_parent_insta.getText().toString().trim().isEmpty()) {
                    et_parent_insta.setError("Enter your Instagram Profile Link");
                }else {

                     intrst = Interest+et_parent_interest.getText().toString()+",";

                    EditProfile(strtext,firstname,lastnamme,et_parent_dob.getText().toString().trim(),mobile_number,
                            gender,profession,et_parent_education.getText().toString().trim(),
                            no_of_children,intrst,et_parent_fb.getText().toString().trim(),et_parent_insta.getText().toString().trim(),
                            et_parent_aboutme.getText().toString().trim(),et_parent_address.getText().toString().trim(),
                            et_parent_city.getText().toString().trim(), et_parent_state.getText().toString().trim());
                }
            }
        });
        txt_social_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_parent_city.getText().toString().trim().isEmpty()) {
                    et_parent_city.setError("Enter City");
                } else if (et_parent_state.getText().toString().trim().isEmpty()) {
                    et_parent_state.setError("Enter State");
                }  else if (et_parent_aboutme.getText().toString().trim().isEmpty()) {
                    et_parent_aboutme.setError("Tell something about yourself");
                } else if (et_parent_address.getText().toString().trim().isEmpty()) {
                    et_parent_address.setError("Enter Address");
                } else if (et_parent_fb.getText().toString().trim().isEmpty()) {
                    et_parent_fb.setError("Enter your Facebook Profile Link");
                } else if (et_parent_insta.getText().toString().trim().isEmpty()) {
                    et_parent_insta.setError("Enter your Instagram Profile Link");
                }else {

                     intrst = Interest+et_parent_interest.getText().toString()+",";

                    EditProfile(strtext,firstname,lastnamme,et_parent_dob.getText().toString().trim(),mobile_number,
                            gender,profession,et_parent_education.getText().toString().trim(),
                            no_of_children,intrst,et_parent_fb.getText().toString().trim(),et_parent_insta.getText().toString().trim(),
                            et_parent_aboutme.getText().toString().trim(),et_parent_address.getText().toString().trim(),
                            et_parent_city.getText().toString().trim(), et_parent_state.getText().toString().trim());
                }
            }
        });

        img_edit_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_image_one = "imageone";
                profile_image_two = "null";
                profile_image_three = "null";
                profile_image_four = "null";
                profile_image_five = "null";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        img_edit_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_image_one = "null";
                profile_image_two = "imagetwo";
                profile_image_three = "null";
                profile_image_four = "null";
                profile_image_five = "null";

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        img_edit_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_image_one = "null";
                profile_image_two = "null";
                profile_image_three = "imagethree";
                profile_image_four = "null";
                profile_image_five = "null";

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        img_edit_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_image_one = "null";
                profile_image_two = "null";
                profile_image_three = "null";
                profile_image_four = "imagefour";
                profile_image_five = "null";

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        img_edit_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_image_one = "null";
                profile_image_two = "null";
                profile_image_three = "null";
                profile_image_four = "null";
                profile_image_five = "imagefive";

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        img_edit_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_image_one = "null";
                profile_image_two = "null";
                profile_image_three = "null";
                profile_image_four = "null";
                profile_image_five = "null";
                profile_image_six = "imagesix";

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                bitmap_one = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap_one = Bitmap.createScaledBitmap(bitmap_one, 500, 750, true);
                bitmap_one.compress(Bitmap.CompressFormat.PNG, 80, baos); //bm is the bitmap object
                byte[] img = baos.toByteArray();




                float degrees = 90; //rotation degree
                Matrix matrix = new Matrix();
                matrix.setRotate(degrees);
                bitmap_one = Bitmap.createBitmap(bitmap_one, 0, 0, bitmap_one.getWidth(), bitmap_one.getHeight(), matrix, true);


                if (profile_image_one.equals("imageone")){
                    img_edit_one.setImageBitmap(bitmap_one);
                    Image_One = Base64.encodeToString(img, Base64.DEFAULT);
                }
                else if (profile_image_two.equals("imagetwo")){
                    img_edit_two.setImageBitmap(bitmap_one);
                    Image_Two = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_three.equals("imagethree")){
                    img_edit_three.setImageBitmap(bitmap_one);
                    Image_Three = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_four.equals("imagefour")){
                    img_edit_four.setImageBitmap(bitmap_one);
                    Image_Four = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_five.equals("imagefive")){
                    img_edit_five.setImageBitmap(bitmap_one);
                    Image_Five = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_six.equals("imagesix")) {
                    img_edit_six.setImageBitmap(bitmap_one);
                    Image_Six = Base64.encodeToString(img, Base64.DEFAULT);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private void GetProfile(){
        list_interest.clear();
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("user_id", strtext);


        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.getparentprofile, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {


                    String Status=response.getString("message");

                    if (Status.matches("Success")){

                        String allProfile=response.getString("allProfile");


                        JSONArray array = new JSONArray(allProfile);

                        for (int i = 0 ; i<array.length();i++){
                            JSONObject object = array.getJSONObject(i);

                            txt_parent_usertid.setText(strtext);
                            //txt_profile_status.setText(object.getString("status"));
                            txt_parent_education.setText(object.getString("qualification"));
                            et_parent_education.setText(object.getString("qualification"));

                            profession=object.getString("profession");
                            no_of_children=object.getString("no_of_children");
                            mobile_number=object.getString("mobile_number");
                            gender=object.getString("gender");
                            Interest=object.getString("interest");


                            city = object.getString("city");
                            state = object.getString("state");
                            date_of_birth = object.getString("date_of_birth");
                            about_me = object.getString("bio");
                            address = object.getString("address");
                            profile_facebook_link = object.getString("facebook_link");
                            insta_link = object.getString("instagram_link");

                           firstname = object.getString("first_name");
                           lastnamme = object.getString("last_name");

                            if (city.equals("null")){
                                txt_parent_city.setText(" ");
                                txt_parent_city.setText("Enter your city");
                                et_parent_city.setText("Enter your city");
                            }else {
                                txt_parent_city.setText(city);
                                et_parent_city.setText(city);
                            }
                            if (state.equals("null")){
                                txt_parent_state.setText(" ");
                                txt_parent_state.setText("Enter your state");
                                et_parent_state.setText("Enter your state");
                            }else {
                                txt_parent_state.setText(state);
                                et_parent_state.setText(state);
                            }
                            if (date_of_birth.equals("null")){
                                txt_parent_dob.setText(" ");
                                txt_parent_dob.setText("Enter date of birth");
                                et_parent_dob.setText("Enter date of birth");
                            }else {
                                txt_parent_dob.setText(date_of_birth);
                                et_parent_dob.setText(date_of_birth);
                            }
                            if (about_me.equals("null")){
                                txt_parent_aboutme.setText(" ");
                                txt_parent_aboutme.setText("Enter about you");
                                et_parent_aboutme.setText("Enter about you");
                            }else {
                                txt_parent_aboutme.setText(about_me);
                                et_parent_aboutme.setText(about_me);
                            }
                            if (address.equals("null")){
                                txt_parent_address.setText(" ");
                                txt_parent_address.setText("Enter your address");
                                et_parent_address.setText("Enter your address");
                            }else {
                                txt_parent_address.setText(address);
                                et_parent_address.setText(address);
                            }
                            if (profile_facebook_link.equals("null")){
                                txt_parent_facebook.setText(" ");
                                txt_parent_facebook.setText("Enter Facebook Link");
                                et_parent_fb.setText("Enter Facebook Link");
                            }else {
                                txt_parent_facebook.setText(profile_facebook_link);
                                et_parent_fb.setText(profile_facebook_link);
                            }if (insta_link.equals("null")){
                                txt_parent_instagram.setText(" ");
                                txt_parent_instagram.setText("Enter Instagram Link");
                                et_parent_insta.setText("Enter Instagram Link");
                            }else {
                                txt_parent_instagram.setText(insta_link);
                                et_parent_insta.setText(insta_link);
                            }

                            txt_edit_userid.setText(strtext);



                            Interest = object.getString("interest");

                            if (Interest.equals("null")){
                                Interest = Interest.replace("null","");
                                et_parent_interest.setText("Enter your interest");
                            }else {
                                String parts[] = Interest.split(",");

                                ArrayList<String> list_interests = new ArrayList<>();

                                for (int j = 0 ; j<parts.length;j++){
                                    list_interests.add(parts[j]);

                                }

                                for (int k = 0 ; k<list_interests.size();k++){

                                    String intertest = list_interests.get(k);

                                    InterestModelClass modelClass = new InterestModelClass(intertest);
                                    list_interest.add(modelClass);
                                    Log.d("interest",list_interest.toString());
                                }

                                Interest_Adapter adapter = new Interest_Adapter(list_interest,getActivity());
                                lv_interest.setAdapter(adapter);
                                lv_editinterest.setAdapter(adapter);

                            }

                            //image=object.getString("profile_image");
                            image1 = object.getString("parent_image1");
                            image2 = object.getString("parent_image2");
                            image3 = object.getString("parent_image3");
                            image4 = object.getString("parent_image4");
                            image5 = object.getString("parent_image5");
                            image6 = object.getString("parent_image6");



                            if (!image1.equals("null")){
                                img_profile_one.setVisibility(View.VISIBLE);
                                image1 = image1.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_edit_one.setImageBitmap(decodedByte);
                                img_profile_one.setImageBitmap(decodedByte);
                            }else {
                                img_profile_one.setVisibility(View.VISIBLE);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_one);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_edit_one);
                            }



                            if (!image2.equals("null")){
                                img_profile_two.setVisibility(View.VISIBLE);
                                image2 = image2.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image2, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_edit_two.setImageBitmap(decodedByte);
                                img_profile_two.setImageBitmap(decodedByte);
                            }else {
                                img_profile_two.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_two);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_edit_two);
                            }

                            if (!image3.equals("null")){
                                img_profile_three.setVisibility(View.VISIBLE);
                                image3 = image3.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image3, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_edit_three.setImageBitmap(decodedByte);
                                img_profile_three.setImageBitmap(decodedByte);
                            }else {
                                img_profile_three.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_three);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_edit_three);
                            }



                            if (!image4.equals("null")){
                                img_profile_four.setVisibility(View.VISIBLE);
                                image4 = image4.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image4, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_edit_four.setImageBitmap(decodedByte);
                                img_profile_four.setImageBitmap(decodedByte);
                            }else {
                                img_profile_four.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_four);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_edit_four);
                            }

                            if (!image5.equals("null")){
                                img_profile_five.setVisibility(View.VISIBLE);
                                image5 = image5.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image5, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_profile_five.setImageBitmap(decodedByte);
                                img_edit_five.setImageBitmap(decodedByte);
                            }else {
                                img_profile_five.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_five);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_edit_five);
                            }
                            if (!image6.equals("null")){
                                img_profile_six.setVisibility(View.VISIBLE);
                                image6 = image6.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image6, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_edit_six.setImageBitmap(decodedByte);
                                img_profile_six.setImageBitmap(decodedByte);
                            }else {
                                img_profile_six.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_six);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_edit_six);

                            }

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

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(jsonRequest);


    }


    private void EditProfile(String user_id, String first_name, String last_name, String date_of_birth,
                             String mobile_number, String gender, String profession, String qualification,
                             String no_of_children, String Interest, String facebook_link, String instagram_link,
                             String bio, String address, String city, String state){


            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //HttpsTrustManager.allowAllSSL();


            final Map<String, String> params = new HashMap();

            params.put("user_id", user_id);
            params.put("first_name", first_name);
            params.put("last_name", last_name);
            params.put("date_of_birth", date_of_birth);
            params.put("mobile_number", mobile_number);
            params.put("gender", gender);
            params.put("profession", profession);
            params.put("qualification", qualification);
            params.put("no_of_children", no_of_children);
            params.put("interest", Interest);
            params.put("profile_interest", Interest);
            params.put("facebook_link", facebook_link);
            params.put("instagram_link", instagram_link);
            params.put("bio", bio);
            params.put("address", address);
            params.put("city", city);
            params.put("state", state);

            try {
                if (profile_image_one.equals("imageone")) {
                    params.put("parent_image1", Image_One);
                } else {
                    params.put("parent_image1", image1);
                }
                if (profile_image_two.equals("imagetwo")) {
                    params.put("parent_image2", Image_Two);
                } else {
                    params.put("parent_image2", image2);
                }
                if (profile_image_three.equals("imagethree")) {
                    params.put("parent_image3", Image_Three);
                } else {
                    params.put("parent_image3", image3);
                }
                if (profile_image_four.equals("imagefour")) {
                    params.put("parent_image4", Image_Four);
                } else {
                    params.put("parent_image4", image4);
                }
                if (profile_image_five.equals("imagefive")) {
                    params.put("parent_image5", Image_Five);
                } else {
                    params.put("parent_image5", image5);
                }
                if (profile_image_six.equals("imagesix")) {
                    params.put("parent_image6", Image_Six);
                } else {
                    params.put("parent_image6", image6);
                }

            }catch (Exception e){
                e.printStackTrace();
            }




            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updateparentprofile, parameters, new Response.Listener<JSONObject>() {
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
                    linear_editpersonalinfo.setVisibility(View.GONE);
                    linear_personalinfo.setVisibility(View.VISIBLE);
                    GetProfile();
                }
            });

            jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(getActivity()).add(jsonRequest);


    }




}