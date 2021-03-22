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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.Interest_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
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
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class InfoFragment extends Fragment {

   LinearLayout linear_personalinfo,linear_editpersonalinfo,linear_lastimage;
   TextView txt_editgeneralinfo,txt_generalinfo,txt_profile_status,txt_profile_userid,txt_profile_city,txt_profile_state,
           txt_profile_school,txt_profile_board,txt_profile_dob,txt_profile_aboutme,txt_profile_address,et_profile_dob,
           txt_profile_fblink,txt_profile_instalink,txt_profile_location,txt_edit_location,txt_edit_social
           ,txt_profile_icse,txt_profile_cbse,txt_userid,txt_edit_persona,txt_edit_board;
    String date,strtext,Board,image,image1,image2,image3,image4,image5,image6,Interest,
            profile_image_one,profile_image_two,profile_image_three,profile_image_four,profile_image_five,profile_image_six,
            Image_One,Image_Two,Image_Three,Image_Four,Image_Five,Image_Six,city,state,school,location,date_of_birth,
            about_me,address,profile_facebook_link,insta_link;
    EditText et_profile_city,et_profile_state,et_profile_school,et_profile_location,et_profile_about,et_profile_address,
            et_profile_fblink,et_profile_instalink,et_profile_interest;
    View view;
    private static final String TAG = "InfoFragment";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public static final int PICK_IMAGE = 1;
    ArrayList<InterestModelClass>list_interest;
    SharedPreferences sharedPreferences;
    RecyclerView lv_interest,lv_editinterest;
    ImageView profile_img_one,profile_img_two,profile_img_three,profile_img_four,profile_img_five,profile_img_six,
            img_profile_one,img_profile_two,img_profile_three,img_profile_four,img_profile_five,img_profile_six;
    Bitmap bitmap_one;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_info, container, false);

        Initialize();


         strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();

         GetProfile();
         Getrank(strtext);


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
                } else if (et_profile_about.getText().toString().trim().isEmpty()) {
                    et_profile_about.setError("Tell something about yourself");
                } else if (et_profile_address.getText().toString().trim().isEmpty()) {
                    et_profile_address.setError("Enter Address");
                } else if (et_profile_fblink.getText().toString().trim().isEmpty()) {
                    et_profile_fblink.setError("Enter your Facebook Profile Link");
                } else if (et_profile_instalink.getText().toString().trim().isEmpty()) {
                    et_profile_instalink.setError("Enter your Instagram Profile Link");
                } else {

                    String intrst = Interest+et_profile_interest.getText().toString()+",";

                    EditProfile(strtext,
                            "Bubble",
                            et_profile_city.getText().toString().trim(),
                            et_profile_state.getText().toString().trim(),
                            et_profile_school.getText().toString().trim(),
                            et_profile_location.getText().toString().trim(),
                            txt_edit_board.getText().toString().trim(),
                            et_profile_dob.getText().toString(),
                            et_profile_about.getText().toString().trim(),
                            et_profile_address.getText().toString().trim(),
                            intrst,
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

        profile_img_two.setOnClickListener(new View.OnClickListener() {
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

        profile_img_three.setOnClickListener(new View.OnClickListener() {
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

        profile_img_four.setOnClickListener(new View.OnClickListener() {
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

        profile_img_five.setOnClickListener(new View.OnClickListener() {
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


        profile_img_six.setOnClickListener(new View.OnClickListener() {
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
                    profile_img_one.setImageBitmap(bitmap_one);
                    Image_One = Base64.encodeToString(img, Base64.DEFAULT);
                }
                else if (profile_image_two.equals("imagetwo")){
                    profile_img_two.setImageBitmap(bitmap_one);
                    Image_Two = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_three.equals("imagethree")){
                    profile_img_three.setImageBitmap(bitmap_one);
                    Image_Three = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_four.equals("imagefour")){
                    profile_img_four.setImageBitmap(bitmap_one);
                    Image_Four = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_five.equals("imagefive")){
                    profile_img_five.setImageBitmap(bitmap_one);
                    Image_Five = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_six.equals("imagesix")) {
                    profile_img_six.setImageBitmap(bitmap_one);
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
        lv_interest = view.findViewById(R.id.lv_interest);
        lv_editinterest = view.findViewById(R.id.lv_editinterest);
        img_profile_one = view.findViewById(R.id.img_profile_one);
        img_profile_two = view.findViewById(R.id.img_profile_two);
        img_profile_three = view.findViewById(R.id.img_profile_three);
        img_profile_four = view.findViewById(R.id.img_profile_four);
        img_profile_five = view.findViewById(R.id.img_profile_five);
        img_profile_six = view.findViewById(R.id.img_profile_six);
        list_interest=new ArrayList<>();
        lv_interest.setHasFixedSize(true);
        lv_interest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        lv_editinterest.setHasFixedSize(true);
        lv_editinterest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

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
                            //txt_profile_status.setText(object.getString("status"));

                            city = object.getString("city");
                            state = object.getString("state");
                            school = object.getString("school");
                            location = object.getString("location");
                            date_of_birth = object.getString("date_of_birth");
                            about_me = object.getString("about_me");
                            address = object.getString("address");
                            profile_facebook_link = object.getString("profile_facebook_link");
                            insta_link = object.getString("insta_link");

                            if (city.equals("null")){
                                txt_profile_city.setText(" ");
                                et_profile_city.setHint("Enter your city");
                            }else {
                                txt_profile_city.setText(city);
                                et_profile_city.setText(city);
                            }
                            if (state.equals("null")){
                                txt_profile_state.setText(" ");
                                et_profile_state.setText("Enter your state");
                            }else {
                                txt_profile_state.setText(state);
                                et_profile_state.setText(state);
                            }
                            if (school.equals("null")){
                                txt_profile_school.setText(" ");
                                et_profile_school.setHint("Enter your school");
                            }else {
                                txt_profile_school.setText(school);
                                et_profile_school.setText(school);
                            }
                            if (location.equals("null")){
                                txt_profile_location.setText(" ");
                                et_profile_location.setHint("Enter location");
                            }else {
                                txt_profile_location.setText(location);
                                et_profile_location.setText(location);
                            }
                            if (date_of_birth.equals("null")){
                                txt_profile_dob.setText(" ");
                                et_profile_dob.setHint("Enter date of birth");
                            }else {
                                txt_profile_dob.setText(date_of_birth);
                                et_profile_dob.setText(date_of_birth);
                            }
                            if (about_me.equals("null")){
                                txt_profile_aboutme.setText(" ");
                                et_profile_about.setHint("Enter about you");
                            }else {
                                txt_profile_aboutme.setText(about_me);
                                et_profile_about.setText(about_me);
                            }
                            if (address.equals("null")){
                                txt_profile_address.setText(" ");
                                et_profile_address.setHint("Enter your address");
                            }else {
                                txt_profile_address.setText(address);
                                et_profile_address.setText(address);
                            }
                            if (profile_facebook_link.equals("null")){
                                txt_profile_fblink.setText(" ");
                                et_profile_fblink.setHint("Enter Facebook Link");
                            }else {
                                txt_profile_fblink.setText(profile_facebook_link);
                                et_profile_fblink.setText(profile_facebook_link);
                            }if (insta_link.equals("null")){
                                txt_profile_instalink.setText(" ");
                                et_profile_instalink.setHint("Enter Instagram Link");
                            }else {
                                txt_profile_instalink.setText(insta_link);
                                et_profile_instalink.setText(insta_link);
                            }

                            txt_userid.setText(strtext);

                            if (object.getString("board").equals("CBSE") || object.getString("board").equals("cbse")){
                                txt_edit_board.setText("CBSE");
                                txt_profile_board.setText("CBSE");
                            }else {
                                txt_edit_board.setText("ICSE");
                                txt_profile_board.setText("ICSE");
                            }




                            txt_profile_instalink.setText(object.getString("insta_link"));

                            Interest = object.getString("profile_interest");

                            if (Interest.equals("null")){
                                Interest = Interest.replace("null","");
                                et_profile_interest.setHint("Enter your interest");
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

                             image=object.getString("profile_image");
                             image1 = object.getString("profile_image_1");
                             image2 = object.getString("profile_image_2");
                             image3 = object.getString("profile_image_3");
                             image4 = object.getString("profile_image_4");
                             image5 = object.getString("profile_image_5");
                             image6 = object.getString("profile_image_6");



                             if (!image1.equals("null")){
                                profile_img_one.setVisibility(View.VISIBLE);
                                 image1 = image1.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_one.setImageBitmap(decodedByte);
                                img_profile_one.setImageBitmap(decodedByte);
                            }else {
                                profile_img_one.setVisibility(View.VISIBLE);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_one);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_one);
                            }



                            if (!image2.equals("null")){
                                profile_img_two.setVisibility(View.VISIBLE);
                                image2 = image2.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image2, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_two.setImageBitmap(decodedByte);
                                img_profile_two.setImageBitmap(decodedByte);
                            }else {
                                profile_img_two.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_two);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_two);
                            }

                            if (!image3.equals("null")){
                                profile_img_three.setVisibility(View.VISIBLE);
                                image3 = image3.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image3, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_three.setImageBitmap(decodedByte);
                                img_profile_three.setImageBitmap(decodedByte);
                            }else {
                                profile_img_three.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_three);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_three);
                            }




                            if (!image4.equals("null")){
                                profile_img_four.setVisibility(View.VISIBLE);
                                image4 = image4.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image4, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_four.setImageBitmap(decodedByte);
                                img_profile_four.setImageBitmap(decodedByte);
                            }else {
                                profile_img_four.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_four);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_four);
                            }

                            if (!image5.equals("null")){
                                profile_img_five.setVisibility(View.VISIBLE);
                                image5 = image5.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image5, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_five.setImageBitmap(decodedByte);
                                img_profile_five.setImageBitmap(decodedByte);
                            }else {
                                profile_img_five.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_five);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_five);
                            }
                            if (!image6.equals("null")){
                                profile_img_six.setVisibility(View.VISIBLE);
                                image6 = image6.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image5, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                profile_img_six.setImageBitmap(decodedByte);
                                img_profile_six.setImageBitmap(decodedByte);
                            }else {
                                profile_img_six.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(profile_img_six);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_profile_six);

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

    private void EditProfile(String UserId, String Status,String City, String State, String School, String Location,
                             String Board,String DOB, String About, String Address,String Interest, String Fblink, String Instalink,
                             String cls, String last_name, String first_name){

        if (DOB.equals("null")){
            AlertBoxClasses.SimpleAlertBox(getActivity(),"Please enter your date of birth.");
        }else {
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
            params.put("profile_interest", Interest);
            params.put("profile_facebook_link", Fblink);
            params.put("insta_link", Instalink);
            params.put("class", cls);
            params.put("last_name", last_name);
            params.put("first_name",first_name );
            params.put("profile_image", image);
            params.put("active_status", "active");



            if (profile_image_one.equals("imageone")){
                params.put("profile_image_1",Image_One);
            }
            else {
                params.put("profile_image_1",image1);
            }
             if (profile_image_two.equals("imagetwo")){
                params.put("profile_image_2",Image_Two);
            }
             else {
                 params.put("profile_image_2",image2);
             }
             if (profile_image_three.equals("imagethree")){
                params.put("profile_image_3",Image_Three);
            }else {
                 params.put("profile_image_3",image3);
             }
             if (profile_image_four.equals("imagefour")){
                params.put("profile_image_4",Image_Four);
            }else {
                 params.put("profile_image_4",image4);
             } if (profile_image_five.equals("imagefive")){
                params.put("profile_image_5",Image_Five);
            }else {
                params.put("profile_image_5",image5);
            }
            if (profile_image_six.equals("imagesix")){
                params.put("profile_image_6",Image_Six);
            }else {
                params.put("profile_image_6",image6);
            }




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
                    linear_editpersonalinfo.setVisibility(View.GONE);
                    linear_personalinfo.setVisibility(View.VISIBLE);
                    GetProfile();
                }
            });


            Volley.newRequestQueue(getActivity()).add(jsonRequest);
        }




    }


    private void Getrank(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getrank?user_id=" + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);

                            String Information = object.getString("Information");

                            JSONObject jsonObject = new JSONObject(Information);

                            txt_profile_status.setText(jsonObject.getString("rank_name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }
}