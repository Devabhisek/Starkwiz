package com.starkwiz.starkwiz.Fragments.ProfileFragments.TeacherProfile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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


public class Info_Teacher_Fragment extends Fragment {

    TextView txt_teacher_id,txt_teacher_city,txt_teacher_state,txt_teacher_school,txt_teacher_location,
            txt_teacher_contact,txt_teacher_website,txt_teacher_dob,txt_teacher_about,txt_teacher_address,
            txt_teacher_fblink,txt_teacher_instalink,txt_teacher_edit_userid,et_teacher_editdob,txt_persona_edit,
            txt_location_edit,txt_socialmedia_edit,txt_generalinfo,txt_editgeneralinfo,txt_persona_save,txt_location_save,txt_social_save;

    ImageView img_teacher_one,img_teacher_two,img_teacher_three,img_teacher_four,img_teacher_five,img_teacher_six,
            img_parent_editone,img_parent_edittwo,img_parent_editthree,img_parent_editfour,img_parent_editfive,img_parent_editsix;

    EditText et_parent_editcity,et_parent_editstate,et_parent_editlocation,et_parent_editwebsite,et_parent_editcontact,
            et_teacher_editabout,et_teacher_interest,et_teacher_address,et_teacher_fb,et_teacher_insta,et_teacher_editschool;

    RecyclerView lv_interest,lv_editinterest;

    LinearLayout linear_personalinfo,linear_editpersonalinfo;

    String date,strtext,Board,image,image1,image2,image3,image4,image5,image6,Interest,
            profile_image_one,profile_image_two,profile_image_three,profile_image_four,profile_image_five,profile_image_six,
            Image_One,Image_Two,Image_Three,Image_Four,Image_Five,Image_Six,city,state,school,location,date_of_birth,
            about_me,address,profile_facebook_link,insta_link,Website,Contact;

    ArrayList<InterestModelClass> list_interest;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "InfoFragment";
    public static final int PICK_IMAGE = 1;
    Bitmap bitmap_one;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info__teacher_, container, false);
        txt_teacher_id = view.findViewById(R.id.txt_teacher_id);
        txt_teacher_city = view.findViewById(R.id.txt_teacher_city);
        txt_teacher_state = view.findViewById(R.id.txt_teacher_state);
        txt_teacher_school = view.findViewById(R.id.txt_teacher_school);
        txt_teacher_location = view.findViewById(R.id.txt_teacher_location);
        txt_teacher_contact = view.findViewById(R.id.txt_teacher_contact);
        txt_teacher_website = view.findViewById(R.id.txt_teacher_website);
        txt_teacher_dob = view.findViewById(R.id.txt_teacher_dob);
        txt_teacher_about = view.findViewById(R.id.txt_teacher_about);
        txt_teacher_address = view.findViewById(R.id.txt_teacher_address);
        txt_teacher_fblink = view.findViewById(R.id.txt_teacher_fblink);
        txt_teacher_instalink = view.findViewById(R.id.txt_teacher_instalink);
        txt_teacher_edit_userid = view.findViewById(R.id.txt_teacher_edit_userid);
        txt_persona_edit = view.findViewById(R.id.txt_persona_edit);
        txt_location_edit = view.findViewById(R.id.txt_location_edit);
        txt_socialmedia_edit = view.findViewById(R.id.txt_socialmedia_edit);
        txt_generalinfo = view.findViewById(R.id.txt_generalinfo);
        txt_editgeneralinfo = view.findViewById(R.id.txt_editgeneralinfo);
        txt_persona_save = view.findViewById(R.id.txt_persona_save);
        txt_location_save = view.findViewById(R.id.txt_location_save);
        txt_social_save = view.findViewById(R.id.txt_social_save);


        img_teacher_one = view.findViewById(R.id.img_teacher_one);
        img_teacher_two = view.findViewById(R.id.img_teacher_two);
        img_teacher_three = view.findViewById(R.id.img_teacher_three);
        img_teacher_four = view.findViewById(R.id.img_teacher_four);
        img_teacher_five = view.findViewById(R.id.img_teacher_five);
        img_teacher_six = view.findViewById(R.id.img_teacher_six);
        img_parent_editone = view.findViewById(R.id.img_parent_editone);
        img_parent_edittwo = view.findViewById(R.id.img_parent_edittwo);
        img_parent_editthree = view.findViewById(R.id.img_parent_editthree);
        img_parent_editfour = view.findViewById(R.id.img_parent_editfour);
        img_parent_editfive = view.findViewById(R.id.img_parent_editfive);
        img_parent_editsix = view.findViewById(R.id.img_parent_editsix);


        et_parent_editcity = view.findViewById(R.id.et_parent_editcity);
        et_parent_editstate = view.findViewById(R.id.et_parent_editstate);
        et_parent_editlocation = view.findViewById(R.id.et_parent_editlocation);
        et_parent_editcontact = view.findViewById(R.id.et_parent_editcontact);
        et_parent_editwebsite = view.findViewById(R.id.et_parent_editwebsite);
        et_teacher_editdob = view.findViewById(R.id.et_teacher_editdob);
        et_teacher_editabout = view.findViewById(R.id.et_teacher_editabout);
        et_teacher_interest = view.findViewById(R.id.et_teacher_interest);
        et_teacher_address = view.findViewById(R.id.et_teacher_address);
        et_teacher_fb = view.findViewById(R.id.et_teacher_fb);
        et_teacher_insta = view.findViewById(R.id.et_teacher_insta);
        et_teacher_editschool = view.findViewById(R.id.et_teacher_editschool);


        linear_personalinfo = view.findViewById(R.id.linear_personalinfo);
        linear_editpersonalinfo = view.findViewById(R.id.linear_editpersonalinfo);

        strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        img_parent_editone.setOnClickListener(new View.OnClickListener() {
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

        img_parent_edittwo.setOnClickListener(new View.OnClickListener() {
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

        img_parent_editthree.setOnClickListener(new View.OnClickListener() {
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

        img_parent_editfour.setOnClickListener(new View.OnClickListener() {
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

        img_parent_editfive.setOnClickListener(new View.OnClickListener() {
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


        img_parent_editsix.setOnClickListener(new View.OnClickListener() {
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


        txt_generalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });

        txt_persona_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });

        txt_location_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });

        txt_socialmedia_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });

        txt_editgeneralinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_teacher_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        et_teacher_editdob.getText().toString().trim(),
                        Contact,
                        et_parent_editcontact.getText().toString().trim(),
                        et_parent_editstate.getText().toString().trim(),
                        et_parent_editcity.getText().toString().trim(),
                        et_teacher_editschool.getText().toString().trim(),
                        et_teacher_address.getText().toString().trim(),
                        et_teacher_fb.getText().toString().trim(),
                        et_teacher_insta.getText().toString().trim(),
                        et_teacher_editabout.getText().toString().trim(),
                        intrst,
                        et_parent_editwebsite.getText().toString().trim(),
                        et_parent_editwebsite.getText().toString().trim()
                );
            }
        });

        txt_persona_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_teacher_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        et_teacher_editdob.getText().toString().trim(),
                        Contact,
                        et_parent_editcontact.getText().toString().trim(),
                        et_parent_editstate.getText().toString().trim(),
                        et_parent_editcity.getText().toString().trim(),
                        et_teacher_editschool.getText().toString().trim(),
                        et_teacher_address.getText().toString().trim(),
                        et_teacher_fb.getText().toString().trim(),
                        et_teacher_insta.getText().toString().trim(),
                        et_teacher_editabout.getText().toString().trim(),
                        intrst,
                        et_parent_editwebsite.getText().toString().trim(),
                        et_parent_editwebsite.getText().toString().trim()
                );
            }
        });

        txt_location_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_teacher_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        et_teacher_editdob.getText().toString().trim(),
                        Contact,
                        et_parent_editcontact.getText().toString().trim(),
                        et_parent_editstate.getText().toString().trim(),
                        et_parent_editcity.getText().toString().trim(),
                        et_teacher_editschool.getText().toString().trim(),
                        et_teacher_address.getText().toString().trim(),
                        et_teacher_fb.getText().toString().trim(),
                        et_teacher_insta.getText().toString().trim(),
                        et_teacher_editabout.getText().toString().trim(),
                        intrst,
                        et_parent_editwebsite.getText().toString().trim(),
                        et_parent_editwebsite.getText().toString().trim()
                );
            }
        });

        txt_social_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_teacher_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        et_teacher_editdob.getText().toString().trim(),
                        Contact,
                        et_parent_editcontact.getText().toString().trim(),
                        et_parent_editstate.getText().toString().trim(),
                        et_parent_editcity.getText().toString().trim(),
                        et_teacher_editschool.getText().toString().trim(),
                        et_teacher_address.getText().toString().trim(),
                        et_teacher_fb.getText().toString().trim(),
                        et_teacher_insta.getText().toString().trim(),
                        et_teacher_editabout.getText().toString().trim(),
                        intrst,
                        et_parent_editwebsite.getText().toString().trim(),
                        et_parent_editwebsite.getText().toString().trim()
                );
            }
        });



        lv_interest = view.findViewById(R.id.lv_interest);
        lv_editinterest = view.findViewById(R.id.lv_editinterest);

        et_teacher_editdob.setOnClickListener(new View.OnClickListener() {
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
                et_teacher_editdob.setText(year + "/" + month + "/" + day);
            }
        };

        lv_interest.setHasFixedSize(true);
        lv_interest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        lv_editinterest.setHasFixedSize(true);
        lv_editinterest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        list_interest = new ArrayList<>();

        GetProfile();
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
                    img_parent_editone.setImageBitmap(bitmap_one);
                    Image_One = Base64.encodeToString(img, Base64.DEFAULT);
                }
                else if (profile_image_two.equals("imagetwo")){
                    img_parent_edittwo.setImageBitmap(bitmap_one);
                    Image_Two = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_three.equals("imagethree")){
                    img_parent_editthree.setImageBitmap(bitmap_one);
                    Image_Three = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_four.equals("imagefour")){
                    img_parent_editfour.setImageBitmap(bitmap_one);
                    Image_Four = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_five.equals("imagefive")){
                    img_parent_editfive.setImageBitmap(bitmap_one);
                    Image_Five = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_six.equals("imagesix")) {
                    img_parent_editsix.setImageBitmap(bitmap_one);
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

                        for (int i = 0 ; i<array.length();i++){
                            JSONObject object = array.getJSONObject(i);

                            txt_teacher_id.setText(strtext);
                            //txt_profile_status.setText(object.getString("status"));

                            Website = object.getString("website");
                            Contact = object.getString("mobile_number");
                            city = object.getString("city");
                            state = object.getString("state");
                            school = object.getString("school");
                            location = object.getString("district");
                            date_of_birth = object.getString("date_of_birth");
                            about_me = object.getString("bio");
                            address = object.getString("address");
                            profile_facebook_link = object.getString("facebook_link");
                            insta_link = object.getString("instagram_link");

                            if (Website.equals("null")){
                                txt_teacher_website.setText(" ");
                                et_parent_editwebsite.setHint("Enter your website");
                            }else {
                                txt_teacher_website.setText(Website);
                                et_parent_editwebsite.setText(Website);
                            }if (Contact.equals("null")){
                                txt_teacher_contact.setText(" ");
                                et_parent_editcontact.setHint("Enter your website");
                            }else {
                                txt_teacher_contact.setText(Website);
                                et_parent_editcontact.setText(Website);
                            }if (city.equals("null")){
                                txt_teacher_city.setText(" ");
                                et_parent_editcity.setHint("Enter your city");
                            }else {
                                txt_teacher_city.setText(city);
                                et_parent_editcity.setText(city);
                            }
                            if (state.equals("null")){
                                txt_teacher_state.setText(" ");
                                et_parent_editstate.setText("Enter your state");
                            }else {
                                txt_teacher_state.setText(state);
                                et_parent_editstate.setText(state);
                            }
                            if (school.equals("null")){
                                txt_teacher_school.setText(" ");
                                et_teacher_editschool.setHint("Enter your school");
                            }else {
                                txt_teacher_school.setText(school);
                                et_teacher_editschool.setText(school);
                            }
                            if (location.equals("null")){
                                txt_teacher_location.setText(" ");
                                et_parent_editlocation.setHint("Enter location");
                            }else {
                                txt_teacher_location.setText(location);
                                et_parent_editlocation.setText(location);
                            }
                            if (date_of_birth.equals("null")){
                                txt_teacher_dob.setText(" ");
                                et_teacher_editdob.setHint("Enter date of birth");
                            }else {
                                txt_teacher_dob.setText(date_of_birth);
                                et_teacher_editdob.setText(date_of_birth);
                            }
                            if (about_me.equals("null")){
                                txt_teacher_about.setText(" ");
                                et_teacher_editabout.setHint("Enter about you");
                            }else {
                                txt_teacher_about.setText(about_me);
                                et_teacher_editabout.setText(about_me);
                            }
                            if (address.equals("null")){
                                txt_teacher_address.setText(" ");
                                et_teacher_address.setHint("Enter your address");
                            }else {
                                txt_teacher_address.setText(address);
                                et_teacher_address.setText(address);
                            }
                            if (profile_facebook_link.equals("null")){
                                txt_teacher_fblink.setText(" ");
                                et_teacher_fb.setHint("Enter Facebook Link");
                            }else {
                                txt_teacher_fblink.setText(profile_facebook_link);
                                et_teacher_fb.setText(profile_facebook_link);
                            }if (insta_link.equals("null")){
                                txt_teacher_instalink.setText(" ");
                                et_teacher_insta.setHint("Enter Instagram Link");
                            }else {
                                txt_teacher_instalink.setText(insta_link);
                                et_teacher_insta.setText(insta_link);
                            }

                            txt_teacher_edit_userid.setText(strtext);


                            Interest = object.getString("interest");

                            if (Interest.equals("null")){
                                Interest = Interest.replace("null","");
                                et_teacher_interest.setHint("Enter your interest");
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

                           // image=object.getString("profile_image");
                            image1 = object.getString("teacher_image1");
                            image2 = object.getString("teacher_image2");
                            image3 = object.getString("teacher_image3");
                            image4 = object.getString("teacher_image4");
                            image5 = object.getString("teacher_image5");
                            image6 = object.getString("teacher_image6");



                            if (!image1.equals("null")){
                                img_teacher_one.setVisibility(View.VISIBLE);
                                image1 = image1.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_teacher_one.setImageBitmap(decodedByte);
                                img_parent_editone.setImageBitmap(decodedByte);
                            }else {
                                img_teacher_two.setVisibility(View.VISIBLE);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_parent_editone);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_teacher_one);
                            }



                            if (!image2.equals("null")){
                                img_teacher_two.setVisibility(View.VISIBLE);
                                image2 = image2.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image2, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_teacher_two.setImageBitmap(decodedByte);
                                img_parent_edittwo.setImageBitmap(decodedByte);
                            }else {
                                img_teacher_two.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_parent_edittwo);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_teacher_two);
                            }

                            if (!image3.equals("null")){
                                img_teacher_three.setVisibility(View.VISIBLE);
                                image3 = image3.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image3, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_teacher_three.setImageBitmap(decodedByte);
                                img_parent_editthree.setImageBitmap(decodedByte);
                            }else {
                                img_teacher_three.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_parent_editthree);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_teacher_three);
                            }




                            if (!image4.equals("null")){
                                img_teacher_four.setVisibility(View.VISIBLE);
                                image4 = image4.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image4, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_teacher_four.setImageBitmap(decodedByte);
                                img_parent_editfour.setImageBitmap(decodedByte);
                            }else {
                                img_teacher_four.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_parent_editfour);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_teacher_four);
                            }

                            if (!image5.equals("null")){
                                img_teacher_five.setVisibility(View.VISIBLE);
                                image5 = image5.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image5, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_teacher_five.setImageBitmap(decodedByte);
                                img_parent_editfive.setImageBitmap(decodedByte);
                            }else {
                                img_teacher_five.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_parent_editfive);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_teacher_five);
                            }
                            if (!image6.equals("null")){
                                img_teacher_six.setVisibility(View.VISIBLE);
                                image6 = image6.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image6, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_teacher_six.setImageBitmap(decodedByte);
                                img_parent_editsix.setImageBitmap(decodedByte);
                            }else {
                                img_teacher_six.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_parent_editsix);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_teacher_six);

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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private void EditProfile(String user_id, String first_name, String last_name, String date_of_birth,
                             String mobile_number, String gender, String state, String city,
                              String school, String address, String facebook_link, String instagram_link, String bio,String Interest,
                             String website,String teacher_profile_image){


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
        params.put("state", state);
        params.put("city", city);
        params.put("district", "district");
        params.put("school", school);
        params.put("school_board", "school_board");
        params.put("address", address);
        params.put("facebook_link", facebook_link);
        params.put("instagram_link", instagram_link);
        params.put("bio", bio);
        params.put("interest", Interest);
        params.put("website", website);
        params.put("teacher_profile_image", teacher_profile_image);

        try {
            if (profile_image_one.equals("imageone")) {
                params.put("teacher_image1", Image_One);
            } else {
                params.put("teacher_image1", image1);
            }
            if (profile_image_two.equals("imagetwo")) {
                params.put("teacher_image2", Image_Two);
            } else {
                params.put("teacher_image2", image2);
            }
            if (profile_image_three.equals("imagethree")) {
                params.put("teacher_image3", Image_Three);
            } else {
                params.put("teacher_image3", image3);
            }
            if (profile_image_four.equals("imagefour")) {
                params.put("teacher_image4", Image_Four);
            } else {
                params.put("teacher_image4", image4);
            }
            if (profile_image_five.equals("imagefive")) {
                params.put("teacher_image5", Image_Five);
            } else {
                params.put("teacher_image5", image5);
            }
            if (profile_image_six.equals("imagesix")) {
                params.put("teacher_image6", Image_Six);
            } else {
                params.put("teacher_image6", image6);
            }

        }catch (Exception e){
            e.printStackTrace();
        }




        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.updateteacherprofile, parameters, new Response.Listener<JSONObject>() {
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