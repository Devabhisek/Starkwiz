package com.starkwiz.starkwiz.Fragments.ProfileFragments.HubProfile;

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


public class Info_HubProfile_Fragment extends Fragment {

    TextView txt_hub_userid,txt_hub_city,txt_hub_state,txt_hub_location,txt_hub_contact,txt_hub_website,
            txt_hub_persona,txt_hub_established,txt_hub_aboutus,txt_hub_locationaddress,txt_hub_address,
            txt_hub_socialmedia,txt_hub_fblink,txt_hub_insta,txt_save_persona,txt_hubedit_established,txt_save_location,
            txt_save_social,txt_edithub_id,txt_generalinfo,txt_editgeneralinfo;
    ImageView img_hub_one,img_hub_two,img_hub_three,img_hub_four,img_hub_five,img_hub_six,
            img_hubedit_one,img_hubedit_two,img_hubedit_three,img_hubedit_four,img_hubedit_five,img_hubedit_six;
    RecyclerView lv_interest,lv_editinterest;
    LinearLayout linear_personalinfo,linear_editpersonalinfo;
    EditText et_hubedit_city,et_edithub_state,et_hub_edit_location,et_hub_edit_contact,et_hubedit_website,
            et_hubedit_aboutus,et_hubedit_interest,et_hubedit_address,et_hubedit_fblink,et_hubedit_insta;

    String date,strtext,hub_typee,image,image1,image2,image3,image4,image5,image6,Interest,
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
        View view = inflater.inflate(R.layout.fragment_info__hub_profile_, container, false);

        txt_hub_userid = view.findViewById(R.id.txt_hub_userid);
        txt_hub_city = view.findViewById(R.id.txt_hub_city);
        txt_hub_state = view.findViewById(R.id.txt_hub_state);
        txt_hub_location = view.findViewById(R.id.txt_hub_location);
        txt_hub_contact = view.findViewById(R.id.txt_hub_contact);
        txt_hub_website = view.findViewById(R.id.txt_hub_website);
        txt_hub_persona = view.findViewById(R.id.txt_hub_persona);
        txt_hub_established = view.findViewById(R.id.txt_hub_established);
        txt_hub_aboutus = view.findViewById(R.id.txt_hub_aboutus);
        txt_hub_locationaddress = view.findViewById(R.id.txt_hub_locationaddress);
        txt_hub_address = view.findViewById(R.id.txt_hub_address);
        txt_hub_socialmedia = view.findViewById(R.id.txt_hub_socialmedia);
        txt_hub_fblink = view.findViewById(R.id.txt_hub_fblink);
        txt_hub_insta = view.findViewById(R.id.txt_hub_insta);
        txt_save_persona = view.findViewById(R.id.txt_save_persona);
        txt_hubedit_established = view.findViewById(R.id.txt_hubedit_established);
        txt_save_location = view.findViewById(R.id.txt_save_location);
        txt_save_social = view.findViewById(R.id.txt_save_social);
        txt_edithub_id = view.findViewById(R.id.txt_edithub_id);
        txt_generalinfo = view.findViewById(R.id.txt_generalinfo);
        txt_editgeneralinfo = view.findViewById(R.id.txt_editgeneralinfo);

        img_hub_one = view.findViewById(R.id.img_hub_one);
        img_hub_two = view.findViewById(R.id.img_hub_two);
        img_hub_three = view.findViewById(R.id.img_hub_two);
        img_hub_four = view.findViewById(R.id.img_hub_four);
        img_hub_five = view.findViewById(R.id.img_hub_five);
        img_hub_six = view.findViewById(R.id.img_hub_six);

        img_hubedit_one = view.findViewById(R.id.img_hubedit_one);
        img_hubedit_two = view.findViewById(R.id.img_hubedit_two);
        img_hubedit_three = view.findViewById(R.id.img_hubedit_three);
        img_hubedit_four = view.findViewById(R.id.img_hubedit_four);
        img_hubedit_five = view.findViewById(R.id.img_hubedit_five);
        img_hubedit_six = view.findViewById(R.id.img_hubedit_six);

        lv_interest = view.findViewById(R.id.lv_interest);
        lv_editinterest = view.findViewById(R.id.lv_editinterest);

        linear_personalinfo = view.findViewById(R.id.linear_personalinfo);
        linear_editpersonalinfo = view.findViewById(R.id.linear_editpersonalinfo);

        et_hubedit_city = view.findViewById(R.id.et_hubedit_city);
        et_edithub_state = view.findViewById(R.id.et_edithub_state);
        et_hub_edit_location = view.findViewById(R.id.et_hub_edit_location);
        et_hub_edit_contact = view.findViewById(R.id.et_hub_edit_contact);
        et_hubedit_website = view.findViewById(R.id.et_hubedit_website);
        et_hubedit_aboutus = view.findViewById(R.id.et_hubedit_aboutus);
        et_hubedit_interest = view.findViewById(R.id.et_hubedit_interest);
        et_hubedit_address = view.findViewById(R.id.et_hubedit_address);
        et_hubedit_fblink = view.findViewById(R.id.et_hubedit_fblink);
        et_hubedit_insta = view.findViewById(R.id.et_hubedit_insta);

        strtext = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        img_hubedit_one.setOnClickListener(new View.OnClickListener() {
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

        img_hubedit_two.setOnClickListener(new View.OnClickListener() {
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

        img_hubedit_three.setOnClickListener(new View.OnClickListener() {
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

        img_hubedit_four.setOnClickListener(new View.OnClickListener() {
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

        img_hubedit_five.setOnClickListener(new View.OnClickListener() {
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


        img_hubedit_six.setOnClickListener(new View.OnClickListener() {
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

        txt_hub_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });



        txt_hub_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });

        txt_hub_socialmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_editpersonalinfo.setVisibility(View.VISIBLE);
                linear_personalinfo.setVisibility(View.GONE);
            }
        });

        txt_editgeneralinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_hubedit_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        txt_hubedit_established.getText().toString().trim(),
                        et_hub_edit_location.getText().toString().trim(),
                        et_hub_edit_contact.getText().toString().trim(),
                        et_hubedit_website.getText().toString().trim(),
                        et_hubedit_aboutus.getText().toString().trim(),
                        intrst,
                        et_hubedit_fblink.getText().toString().trim(),
                        et_hubedit_insta.getText().toString().trim(),
                        et_hubedit_address.getText().toString().trim(),
                        et_hubedit_city.getText().toString().trim(),
                        et_edithub_state.getText().toString().trim()
                );
            }
        });

        txt_save_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_hubedit_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        txt_hubedit_established.getText().toString().trim(),
                        et_hub_edit_location.getText().toString().trim(),
                        et_hub_edit_contact.getText().toString().trim(),
                        et_hubedit_website.getText().toString().trim(),
                        et_hubedit_aboutus.getText().toString().trim(),
                        intrst,
                        et_hubedit_fblink.getText().toString().trim(),
                        et_hubedit_insta.getText().toString().trim(),
                        et_hubedit_address.getText().toString().trim(),
                        et_hubedit_city.getText().toString().trim(),
                        et_edithub_state.getText().toString().trim()
                );
            }
        });

        txt_save_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_hubedit_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        txt_hubedit_established.getText().toString().trim(),
                        et_hub_edit_location.getText().toString().trim(),
                        et_hub_edit_contact.getText().toString().trim(),
                        et_hubedit_website.getText().toString().trim(),
                        et_hubedit_aboutus.getText().toString().trim(),
                        intrst,
                        et_hubedit_fblink.getText().toString().trim(),
                        et_hubedit_insta.getText().toString().trim(),
                        et_hubedit_address.getText().toString().trim(),
                        et_hubedit_city.getText().toString().trim(),
                        et_edithub_state.getText().toString().trim()
                );
            }
        });

        txt_save_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intrst = Interest+et_hubedit_interest.getText().toString()+",";
                EditProfile(strtext,
                        SharedPrefManager.getInstance(getActivity()).getUser().getFirst_name(),
                        SharedPrefManager.getInstance(getActivity()).getUser().getLast_name(),
                        txt_hubedit_established.getText().toString().trim(),
                        et_hub_edit_location.getText().toString().trim(),
                        et_hub_edit_contact.getText().toString().trim(),
                        et_hubedit_website.getText().toString().trim(),
                        et_hubedit_aboutus.getText().toString().trim(),
                        intrst,
                        et_hubedit_fblink.getText().toString().trim(),
                        et_hubedit_insta.getText().toString().trim(),
                        et_hubedit_address.getText().toString().trim(),
                        et_hubedit_city.getText().toString().trim(),
                        et_edithub_state.getText().toString().trim()
                );
            }
        });



        lv_interest = view.findViewById(R.id.lv_interest);
        lv_editinterest = view.findViewById(R.id.lv_editinterest);

        txt_hubedit_established.setOnClickListener(new View.OnClickListener() {
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
                txt_hubedit_established.setText(year + "/" + month + "/" + day);
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
                    img_hubedit_one.setImageBitmap(bitmap_one);
                    Image_One = Base64.encodeToString(img, Base64.DEFAULT);
                }
                else if (profile_image_two.equals("imagetwo")){
                    img_hubedit_two.setImageBitmap(bitmap_one);
                    Image_Two = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_three.equals("imagethree")){
                    img_hubedit_three.setImageBitmap(bitmap_one);
                    Image_Three = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_four.equals("imagefour")){
                    img_hubedit_four.setImageBitmap(bitmap_one);
                    Image_Four = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_five.equals("imagefive")){
                    img_hubedit_five.setImageBitmap(bitmap_one);
                    Image_Five = Base64.encodeToString(img, Base64.DEFAULT);
                }else if (profile_image_six.equals("imagesix")) {
                    img_hubedit_six.setImageBitmap(bitmap_one);
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

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.gethubprofilebyid, parameters, new Response.Listener<JSONObject>() {
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

                            txt_hub_userid.setText(strtext);
                            txt_edithub_id.setText(strtext);
                            //txt_profile_status.setText(object.getString("status"));

                            Website = object.getString("hub_website");
                            Contact = object.getString("mobile_number");
                            city = object.getString("city");
                            state = object.getString("state");
                            location = object.getString("hub_location");
                            date_of_birth = object.getString("date_of_birth");
                            about_me = object.getString("bio");
                            address = object.getString("address");
                            profile_facebook_link = object.getString("facebook_link");
                            insta_link = object.getString("instagram_link");
                            hub_typee = object.getString("hub_typee");

                            if (Website.equals("null")){
                                txt_hub_website.setText(" ");
                                et_hubedit_website.setHint("Enter your website");
                            }else {
                                txt_hub_website.setText(Website);
                                et_hubedit_website.setText(Website);
                            }if (Contact.equals("null")){
                                txt_hub_contact.setText(" ");
                                et_hub_edit_contact.setHint("Enter your website");
                            }else {
                                txt_hub_contact.setText(Website);
                                et_hub_edit_contact.setText(Website);
                            }if (city.equals("null")){
                                txt_hub_city.setText(" ");
                                et_hubedit_city.setHint("Enter your city");
                            }else {
                                txt_hub_city.setText(city);
                                et_hubedit_city.setText(city);
                            }
                            if (state.equals("null")){
                                txt_hub_state.setText(" ");
                                et_edithub_state.setText("Enter your state");
                            }else {
                                txt_hub_state.setText(state);
                                et_edithub_state.setText(state);
                            }

                            if (location.equals("null")){
                                txt_hub_location.setText(" ");
                                et_hub_edit_location.setHint("Enter location");
                            }else {
                                txt_hub_location.setText(location);
                                et_hub_edit_location.setText(location);
                            }
                            if (date_of_birth.equals("null")){
                                txt_hub_established.setText(" ");
                                txt_hubedit_established.setHint("Enter date of birth");
                            }else {
                                txt_hub_established.setText(date_of_birth);
                                txt_hubedit_established.setText(date_of_birth);
                            }
                            if (about_me.equals("null")){
                                txt_hub_aboutus.setText(" ");
                                et_hubedit_aboutus.setHint("Enter about you");
                            }else {
                                txt_hub_aboutus.setText(about_me);
                                et_hubedit_aboutus.setText(about_me);
                            }
                            if (address.equals("null")){
                                txt_hub_address.setText(" ");
                                et_hubedit_address.setHint("Enter your address");
                            }else {
                                txt_hub_address.setText(address);
                                et_hubedit_address.setText(address);
                            }
                            if (profile_facebook_link.equals("null")){
                                txt_hub_fblink.setText(" ");
                                et_hubedit_fblink.setHint("Enter Facebook Link");
                            }else {
                                txt_hub_fblink.setText(profile_facebook_link);
                                et_hubedit_fblink.setText(profile_facebook_link);
                            }if (insta_link.equals("null")){
                                txt_hub_insta.setText(" ");
                                et_hubedit_insta.setHint("Enter Instagram Link");
                            }else {
                                txt_hub_insta.setText(insta_link);
                                et_hubedit_insta.setText(insta_link);
                            }



                            Interest = object.getString("interest");

                            if (Interest.equals("null")){
                                Interest = Interest.replace("null","");
                                et_hubedit_interest.setHint("Enter your interest");
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
                                img_hub_one.setVisibility(View.VISIBLE);
                                image1 = image1.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_hub_one.setImageBitmap(decodedByte);
                                img_hubedit_one.setImageBitmap(decodedByte);
                            }else {
                                img_hub_one.setVisibility(View.VISIBLE);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_hub_one);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_hubedit_one);
                            }



                            if (!image2.equals("null")){
                                img_hub_two.setVisibility(View.VISIBLE);
                                image2 = image2.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image2, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_hub_two.setImageBitmap(decodedByte);
                                img_hubedit_two.setImageBitmap(decodedByte);
                            }else {
                                img_hub_two.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_hubedit_two);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_hub_two);
                            }

                            if (!image3.equals("null")){
                                img_hub_three.setVisibility(View.VISIBLE);
                                image3 = image3.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image3, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_hub_three.setImageBitmap(decodedByte);
                                img_hubedit_three.setImageBitmap(decodedByte);
                            }else {
                                img_hub_three.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_hubedit_three);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_hub_three);
                            }




                            if (!image4.equals("null")){
                                img_hub_four.setVisibility(View.VISIBLE);
                                image4 = image4.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image4, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_hub_four.setImageBitmap(decodedByte);
                                img_hubedit_four.setImageBitmap(decodedByte);
                            }else {
                                img_hub_four.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_hubedit_four);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_hub_four);
                            }

                            if (!image5.equals("null")){
                                img_hub_five.setVisibility(View.VISIBLE);
                                image5 = image5.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image5, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

//                                float degrees = 90; //rotation degree
//                                Matrix matrix = new Matrix();
//                                matrix.setRotate(degrees);
//                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_hub_five.setImageBitmap(decodedByte);
                                img_hubedit_five.setImageBitmap(decodedByte);
                            }else {
                                img_hub_five.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_hubedit_five);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_hub_five);
                            }
                            if (!image6.equals("null")){
                                img_hub_six.setVisibility(View.VISIBLE);
                                image6 = image6.replace("data:image/png;base64,","");
                                byte[] decodedString = Base64.decode(image6, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                float degrees = 90; //rotation degree
                                Matrix matrix = new Matrix();
                                matrix.setRotate(degrees);
                                decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                img_hub_six.setImageBitmap(decodedByte);
                                img_hubedit_six.setImageBitmap(decodedByte);
                            }else {
                                img_hub_six.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity())
                                        .load(R.mipmap.addphoto)
                                        .into(img_hubedit_six);

                                Picasso.with(getActivity())
                                        .load(R.mipmap.nophoto)
                                        .into(img_hub_six);

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

    private void EditProfile( String user_id,String first_name,String last_name, String date_of_birth, String location,
                             String mobile_number, String website, String bio, String Interest,String facebook_link, String instagram_link,
                              String address, String city, String state){


        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();


        final Map<String, String> params = new HashMap();

        params.put("user_id", user_id);
        params.put("first_name", first_name);
        params.put("last_name", last_name);
        params.put("hub_establishment", date_of_birth);
        params.put("hub_location", location);
        params.put("mobile_number", mobile_number);
        params.put("website", website);
        params.put("bio", bio);
        params.put("interest", Interest);
        params.put("facebook_link", facebook_link);
        params.put("instagram_link", instagram_link);
        params.put("hub_typee", hub_typee);
        params.put("address", address);
        params.put("hub_type", hub_typee);
        params.put("hub_website", website);
        params.put("city", city);
        params.put("state", state);

        params.put("hub_profile_image", Image_One);

        try {
            if (profile_image_one.equals("imageone")) {
                params.put("hub_image1", Image_One);
            } else {
                params.put("hub_image1", image1);
            }
            if (profile_image_two.equals("imagetwo")) {
                params.put("hub_image2", Image_Two);
            } else {
                params.put("hub_image2", image2);
            }
            if (profile_image_three.equals("imagethree")) {
                params.put("hub_image3", Image_Three);
            } else {
                params.put("hub_image3", image3);
            }
            if (profile_image_four.equals("imagefour")) {
                params.put("hub_image4", Image_Four);
            } else {
                params.put("hub_image4", image4);
            }
            if (profile_image_five.equals("imagefive")) {
                params.put("hub_image5", Image_Five);
            } else {
                params.put("hub_image5", image5);
            }
            if (profile_image_six.equals("imagesix")) {
                params.put("hub_image6", Image_Six);
            } else {
                params.put("hub_image6", image6);
            }

        }catch (Exception e){
            e.printStackTrace();
        }




        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.edithub, parameters, new Response.Listener<JSONObject>() {
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