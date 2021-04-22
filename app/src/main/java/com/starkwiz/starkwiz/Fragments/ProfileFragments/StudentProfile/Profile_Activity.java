package com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.Adapter.Tabs_Adapter.ProfileAdapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Activity extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txt_profile_name,txt_profile_address,txt_class,txt_profile_board,
            txt_profile_status,txt_editprofile,txt_editprofile_save,txt_profile_folowers;
    LinearLayout linearedit,linear_profile;
    String strtext,status,city,state,school,location,board,birthday,about_me,interest,address,
            facebook_link,insta_link,cls,last_name,first_name,encodedImage,image,User_ID;
    EditText et_profile_address,et_class,et_profile_firstname,et_profile_lastname;
    CircleImageView profileimg;
    public static final int PICK_IMAGE = 1;
    Bitmap selectedImage;
    String image1,image2,image3,image4,image5,image6;
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
        profileimg= view.findViewById(R.id.profileimg);
        txt_profile_folowers= view.findViewById(R.id.txt_profile_folowers);

        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Achievement"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        User_ID = SharedPrefManager.getInstance(getActivity()).getUser().getId();
        GetProfile();
        Getrank(User_ID);
        GetFollowers(User_ID);

        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });

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



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage = Bitmap.createScaledBitmap(selectedImage, 500, 750, true);
                selectedImage.compress(Bitmap.CompressFormat.PNG, 80, baos); //bm is the bitmap object
                byte[] img = baos.toByteArray();

                encodedImage = Base64.encodeToString(img, Base64.DEFAULT);


                float degrees = 90; //rotation degree
                Matrix matrix = new Matrix();
                matrix.setRotate(degrees);
                selectedImage = Bitmap.createBitmap(selectedImage, 0, 0, selectedImage.getWidth(), selectedImage.getHeight(), matrix, true);

                //imagedata = baos.toByteArray();
                profileimg.setImageBitmap(selectedImage);

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
                                et_profile_address.setText(object.getString("city"));

                                 image = object.getString("profile_image");
                                if (!image.equals("null")){
                                    image = image.replace("data:image/png;base64,","");
                                    byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                    float degrees = 90; //rotation degree
                                    Matrix matrix = new Matrix();
                                    matrix.setRotate(degrees);
                                    decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                    profileimg.setImageBitmap(decodedByte);
                                }else {
                                    Picasso.with(getActivity())
                                            .load(R.mipmap.nophoto)
                                            .into(profileimg);
                                }

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

                            //txt_profile_status.setText("status");

                            if (object.getString("board").equals("CBSE") || object.getString("board").equals("cbse")){
                                txt_profile_board.setText("CBSE");
                            }else {
                                txt_profile_board.setText("ICSE");
                            }

                           // txt_profile_status.setText(object.getString("status"));


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

                            image1 = object.getString("profile_image_1");
                            image2 = object.getString("profile_image_2");
                            image3 = object.getString("profile_image_3");
                            image4 = object.getString("profile_image_4");
                            image5 = object.getString("profile_image_5");
                            image6 = object.getString("profile_image_6");

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
            if (encodedImage==null){
                params.put("profile_image", image);
            }else {
                params.put("profile_image", encodedImage);
            }
            params.put("profile_image", encodedImage);
            params.put("active_status", "active");
             params.put("profile_image_1",image1);
             params.put("profile_image_2",image2);
             params.put("profile_image_3",image3);
             params.put("profile_image_4",image4);
             params.put("profile_image_5",image5);
             params.put( "profile_image_6",image6);

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


                    if (error.equals("com.android.volley.TimeoutError")){
                        GetProfile();
                    }else {
                        Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            Volley.newRequestQueue(getActivity()).add(jsonRequest);



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

                            status = jsonObject.getString("rank_name");

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

    private void GetFollowers(String Id){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getfollowers?user_id=" + Id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);

                    txt_profile_folowers.setText(object.getString("myfollowercount"));
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