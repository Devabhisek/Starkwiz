package com.starkwiz.starkwiz.Activities.Quiz_Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;
import com.starkwiz.starkwiz.Activities.Dasboard_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.GetList_Adapter;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.Topic_Adapter;
import com.starkwiz.starkwiz.Adapter.SpinnerAdapetr.Topic_SpinnerAdapter;
import com.starkwiz.starkwiz.LinkingClass.AndroidMultiPartEntity;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Topics_Modelclass;
import com.starkwiz.starkwiz.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Dance_Activity extends AppCompatActivity {

    Button btn_music_upload;
    TextView txt_fixture;
    int SELECT_VIDEO_REQUEST=100;
    String selectedVideoPath,filemanagerstring,Category,Userid,responseString;
    int REQUEST_TAKE_GALLERY_VIDEO =1;
    Uri selectedImageUri,returnUri;
    RecyclerView lv_tpopics;
    ArrayList<Topics_Modelclass> list_topics;
    RoundedHorizontalProgressBar progress_bar_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_);

        btn_music_upload = findViewById(R.id.btn_dance_upload);
        txt_fixture = findViewById(R.id.txtfixture);
        lv_tpopics = findViewById(R.id.lv_tpopics);
        lv_tpopics.setHasFixedSize(true);
        lv_tpopics.setLayoutManager(new LinearLayoutManager(Dance_Activity.this));
        list_topics = new ArrayList<>();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month = month_date.format(cal.getTime());
        Userid = SharedPrefManager.getInstance(Dance_Activity.this).getUser().getId();



        txt_fixture.setText("FIXTURE: "+month);
        btn_music_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Dance_Activity.this); ;
                dialog.setContentView(R.layout.alert_upload_music);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Button txt_music_browsefile,txt_music_done,txt_music_cancel;
                TextView txt_music_filename;
                Spinner spinner_topic;

                txt_music_browsefile = dialog.findViewById(R.id.txt_music_browsefile);
                txt_music_done = dialog.findViewById(R.id.txt_music_done);
                txt_music_cancel = dialog.findViewById(R.id.txt_music_cancel);
                txt_music_filename = dialog.findViewById(R.id.txt_music_filename);
                spinner_topic = dialog.findViewById(R.id.spinner_topic);
                progress_bar_1 = dialog.findViewById(R.id.progress_bar_1);

                if (filemanagerstring!=null){
                    txt_music_filename.setText(filemanagerstring);
                }else {
                    txt_music_filename.setText("File Name");
                }

                GetTopics();
                Topic_SpinnerAdapter adapter = new Topic_SpinnerAdapter(Dance_Activity.this, android.R.layout.simple_spinner_dropdown_item, list_topics);
                spinner_topic.setAdapter(adapter);

                spinner_topic.setSelection(0,true);
                spinner_topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        try {

                            Topics_Modelclass myModel=(Topics_Modelclass) parent.getSelectedItem();

                            Category = myModel.getTopic();


                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });



                txt_music_browsefile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectVideoFromGallery();
                    }
                });

                txt_music_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        progress_bar_1.animateProgress(2000, 0, 5); // (animationDuration, oldProgress, newProgress)

                        Toast.makeText(Dance_Activity.this, "Uploading Please wait..", Toast.LENGTH_SHORT).show();

                        if (android.os.Build.VERSION.SDK_INT > 9) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }
                        responseString = null;

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("https://rentopool.com/starkwiz/api/auth/createshowcase");

                        try {
                            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                                    new AndroidMultiPartEntity.ProgressListener() {

                                        @Override
                                        public void transferred(long num) {
// publishProgress((int) ((num / (float) totalSize) * 100));
                                        }
                                    });


                            File sourceFile = new File(selectedVideoPath);

                            // Adding file data to http body
                            entity.addPart("file_name", new FileBody(sourceFile));
                            // Extra parameters if you want to pass to server
                            entity.addPart("user_id", new StringBody(Userid));
                            entity.addPart("topic", new StringBody("topic1"));
                            entity.addPart("format", new StringBody("mp4"));
                            entity.addPart("category", new StringBody("Music"));

// totalSize = entity.getContentLength();
                            httppost.setEntity(entity);

                            // Making server call
                            HttpResponse response = httpclient.execute(httppost);
                            HttpEntity r_entity = response.getEntity();
                            int statusCode = response.getStatusLine().getStatusCode();
                            if (statusCode == 201) {
                                // Server response
                                responseString = EntityUtils.toString(r_entity);
                                try {
                                    JSONObject object = new JSONObject(responseString);
                                    String Status = object.getString("message");
                                    if (Status.equals("showcase created")){
                                        progress_bar_1.animateProgress(2000, 0, 100); // (animationDuration, oldProgress, newProgress)
                                        btn_music_upload.setText("Uploaded");
                                        btn_music_upload.setEnabled(false);
                                        CreateNotification(Userid,"Your video is uploaded");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            } else {
                                responseString = "Error occurred! Http Status Code: "
                                        + statusCode;
                                Toast.makeText(Dance_Activity.this, responseString, Toast.LENGTH_SHORT).show();
                            }


                        } catch (ClientProtocolException e) {
                            responseString = e.toString();
                        } catch (IOException e) {
                            responseString = e.toString();
                        }
                    }
                });
            }
        });

        GetTopics();
    }

    public void selectVideoFromGallery()
    {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {

                returnUri = data.getData();
                selectedVideoPath = getPath(returnUri);
                Cursor returnCursor =
                        getContentResolver().query(returnUri, null, null, null, null);
                //selectedVideoPath = getPath(returnUri);

                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                returnCursor.moveToFirst();

                filemanagerstring = returnCursor.getString(nameIndex);

                if (filemanagerstring != null) {

                    selectedImageUri = Uri.parse(filemanagerstring);

                    Log.d("selectedVideoPath", String.valueOf(selectedImageUri));
                }

            }
        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }

    private void GetTopics(){
        String cls = SharedPrefManager.getInstance(Dance_Activity.this).getUser().getCls();
        ProgressDialog dialog = new ProgressDialog(Dance_Activity.this);
        dialog.setMessage("Loading...");
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/gettopicbycategory?category=Dance&class="+cls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            String Information = object.getString("Information");

                            JSONArray array = new JSONArray(Information);

                            for (int i = 0 ; i<array.length(); i++){

                                JSONObject object1 = array.getJSONObject(i);

                                String Category = object1.getString("category");
                                if (Category.equals("Music")){

                                    Topics_Modelclass modelclass = new Topics_Modelclass(
                                            object1.getString("id"),
                                            object1.getString("category"),
                                            object1.getString("topic"),
                                            object1.getString("class")
                                    );

                                    list_topics.add(modelclass);
                                }

                                Topic_Adapter adapter = new Topic_Adapter(list_topics,Dance_Activity.this);
                                lv_tpopics.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
            }
        });

        MySingleton.getInstance(Dance_Activity.this).addToRequestque(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        cameraOpertions();
    }

    public void cameraOpertions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                //Permission not enabled request it
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                // show popup to request permission

                requestPermissions(permission, 100);

            } else {
            }
        } else {

        }

    }

    public void CreateNotification(String user_id, String notification_text){

        ProgressDialog progressDialog = new ProgressDialog(Dance_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //HttpsTrustManager.allowAllSSL();

        final Map<String, String> params = new HashMap();

        params.put("user_id", user_id);
        params.put("notification_text", notification_text);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.createnotification, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String message= response.getString("message");
                    if (message.equals("notification created")){
                        Log.d("success",message);
                        notificationDialog();
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

                Log.d("error","error");

                Toast.makeText(Dance_Activity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(Dance_Activity.this).add(jsonRequest);
    }
    private void notificationDialog() {
        NotificationManager notificationManager = (NotificationManager)       getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "tutorialspoint_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.logo)
                .setTicker("Tutorialspoint")
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Starkwiz")
                .setContentText("Your video is uploaded")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
    }
}