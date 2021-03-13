package com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class InfoFragment extends Fragment {

   LinearLayout linear_personalinfo,linear_editpersonalinfo,linear_lastimage;
   TextView txt_editgeneralinfo,txt_generalinfo,txt_profile_status,txt_profile_userid,txt_profile_city,txt_profile_state,
           txt_profile_school,txt_profile_board,txt_profile_dob,txt_profile_aboutme,txt_profile_address,et_profile_dob,
           txt_profile_fblink,txt_profile_instalink,txt_profile_location,txt_edit_location,txt_edit_social
           ,txt_profile_icse,txt_profile_cbse,txt_userid,txt_edit_persona,txt_edit_board;
    String date,strtext,Board,image,image1,image2,image3,image4,image5,
            Image_One;
    EditText et_profile_city,et_profile_state,et_profile_school,et_profile_location,et_profile_about,et_profile_address,
            et_profile_fblink,et_profile_instalink,et_profile_interest;
    View view;
    private static final String TAG = "InfoFragment";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public static final int PICK_IMAGE = 1;
    SharedPreferences sharedPreferences;

    ImageView profile_img_one,profile_img_two,profile_img_three,profile_img_four,profile_img_five,profile_img_six;
    Bitmap bitmap_one;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_info, container, false);

        Initialize();


         strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();

         GetProfile();


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

                    EditProfile(strtext,
                            "Bubble",
                            et_profile_city.getText().toString().trim(),
                            et_profile_state.getText().toString().trim(),
                            et_profile_school.getText().toString().trim(),
                            et_profile_location.getText().toString().trim(),
                            et_profile_interest.getText().toString(),
                            txt_edit_board.getText().toString().trim(),
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

        profile_img_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                Image_One = Base64.encodeToString(img, Base64.DEFAULT);


                float degrees = 90; //rotation degree
                Matrix matrix = new Matrix();
                matrix.setRotate(degrees);
                bitmap_one = Bitmap.createBitmap(bitmap_one, 0, 0, bitmap_one.getWidth(), bitmap_one.getHeight(), matrix, true);



            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
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
        txt_edit_board=view.findViewById(R.id.txt_edit_board);
        et_profile_interest = view.findViewById(R.id.et_profile_interest);
        txt_userid = view.findViewById(R.id.txt_userid);
        txt_edit_persona = view.findViewById(R.id.txt_edit_persona);
        profile_img_one = view.findViewById(R.id.profile_img_one);
        profile_img_two = view.findViewById(R.id.profile_img_two);
        profile_img_three = view.findViewById(R.id.profile_img_three);
        profile_img_four = view.findViewById(R.id.profile_img_four);
        profile_img_five = view.findViewById(R.id.profile_img_five);
        profile_img_six = view.findViewById(R.id.profile_img_six);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        txt_profile_icse = view.findViewById(R.id.txt_profile_icse);
//        txt_profile_cbse = view.findViewById(R.id.txt_profile_cbse);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER",MODE_PRIVATE);
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

                            txt_profile_userid.setText(strtext);
                            txt_profile_status.setText(object.getString("status"));
                            txt_profile_city.setText(object.getString("city"));
                            txt_profile_state.setText(object.getString("state"));
                            txt_profile_school.setText(object.getString("school"));
                            txt_profile_location.setText(object.getString("location"));

                            if (object.getString("board").equals("CBSE") || object.getString("board").equals("cbse")){
                                txt_edit_board.setText("CBSE");
                                txt_profile_board.setText("CBSE");
                            }else {
                                txt_edit_board.setText("ICSE");
                                txt_profile_board.setText("ICSE");
                            }

                            txt_profile_dob.setText(object.getString("date_of_birth"));
                            txt_profile_aboutme.setText(object.getString("about_me"));
                            txt_profile_address.setText(object.getString("address"));
                            txt_profile_fblink.setText(object.getString("profile_facebook_link"));
                            txt_profile_instalink.setText(object.getString("insta_link"));

                            et_profile_city.setText(object.getString("city"));
                            et_profile_state.setText(object.getString("state"));
                            et_profile_school.setText(object.getString("school"));
                            et_profile_location.setText(object.getString("location"));
                            et_profile_dob.setText(object.getString("date_of_birth"));
                            et_profile_about.setText(object.getString("about_me"));
                            et_profile_address.setText(object.getString("address"));
                            et_profile_fblink.setText(object.getString("profile_facebook_link"));
                            et_profile_instalink.setText(object.getString("insta_link"));
                            txt_userid.setText(strtext);

                             image = object.getString("profile_image");
                             image1 = object.getString("profile_image_1");
                             image2 = object.getString("profile_image_2");
                             image3 = object.getString("profile_image_3");
                             image4 = object.getString("profile_image_4");
                             image5 = object.getString("profile_image_5");



                             if (!image.equals("null")){
                                profile_img_one.setVisibility(View.VISIBLE);
                                image = image.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_one.setImageBitmap(decodedByte);
                            }else {
                                profile_img_one.setVisibility(View.VISIBLE);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_one);
                            }

                            if (!image1.equals("null")){
                                profile_img_two.setVisibility(View.VISIBLE);
                                image1 = image1.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_two.setImageBitmap(decodedByte);
                            }else {
                                profile_img_two.setVisibility(View.VISIBLE);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_two);
                            }

                            if (!image2.equals("null")){
                                profile_img_three.setVisibility(View.VISIBLE);
                                image2 = image2.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image2, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_three.setImageBitmap(decodedByte);
                            }else {
                                profile_img_three.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_three);
                            }

                            if (!image3.equals("null")){
                                profile_img_four.setVisibility(View.VISIBLE);
                                image3 = image3.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image3, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_four.setImageBitmap(decodedByte);
                            }else {
                                profile_img_four.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_four);
                            }




                            if (!image4.equals("null")){
                                profile_img_five.setVisibility(View.VISIBLE);
                                image4 = image4.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image4, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_five.setImageBitmap(decodedByte);
                            }else {
                                profile_img_five.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_five);
                            }

                            if (!image5.equals("null")){
                                profile_img_six.setVisibility(View.VISIBLE);
                                image5 = image5.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image5, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_six.setImageBitmap(decodedByte);
                            }else {
                                profile_img_six.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_six);
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
        params.put("status", "Bubble");
        params.put("city", City);
        params.put("state", State);
        params.put("school", School);
        params.put("location", Location);
        params.put("board", Board);
        params.put("date_of_birth", DOB);
        params.put("about_me", About);
        params.put("address", Address);
        params.put("interest", Address);
        params.put("profile_facebook_link", Fblink);
        params.put("insta_link", Instalink);
        params.put("class", cls);
        params.put("last_name", last_name);
        params.put("first_name",first_name );
        params.put("profile_image", image);
        params.put("active_status", "active");



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