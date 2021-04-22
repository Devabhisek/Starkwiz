package com.starkwiz.starkwiz.Activities.Quiz_Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.LinkingClass.AndroidMultiPartEntity;
import com.starkwiz.starkwiz.LinkingClass.HttpsTrustManager;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Music_Activity extends AppCompatActivity {

    Button btn_music_upload;
    TextView txt_fixture;
    int SELECT_VIDEO_REQUEST=100;
    String selectedVideoPath,filemanagerstring,videoname,Userid,responseString;
    int REQUEST_TAKE_GALLERY_VIDEO =1;
    Uri selectedImageUri,returnUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_);
        btn_music_upload = findViewById(R.id.btn_music_upload);
        txt_fixture = findViewById(R.id.txt_fixture);
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month = month_date.format(cal.getTime());
        Userid = SharedPrefManager.getInstance(Music_Activity.this).getUser().getId();


        txt_fixture.setText("FIXTURE: "+month);
        btn_music_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Music_Activity.this); ;
                dialog.setContentView(R.layout.alert_upload_music);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Button txt_music_browsefile,txt_music_done,txt_music_cancel;
                TextView txt_music_filename;

                txt_music_browsefile = dialog.findViewById(R.id.txt_music_browsefile);
                txt_music_done = dialog.findViewById(R.id.txt_music_done);
                txt_music_cancel = dialog.findViewById(R.id.txt_music_cancel);
                txt_music_filename = dialog.findViewById(R.id.txt_music_filename);

                if (filemanagerstring!=null){
                    txt_music_filename.setText(filemanagerstring);
                }else {
                    txt_music_filename.setText("File Name");
                }



                txt_music_browsefile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectVideoFromGallery();
                    }
                });

                txt_music_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        UploadVideo();
                   /*   UploadVideoToServer uploadVideoToServer = new UploadVideoToServer();
                       uploadVideoToServer.execute();*/
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
                                    JSONObject obj = new JSONObject(responseString);
                                    JSONObject res = obj.getJSONObject("response");
                                    String mStatus = res.getString("status");
                                    Log.e("Json status", mStatus);
                                    if (mStatus.equalsIgnoreCase("1")) {
                                        Toast.makeText(Music_Activity.this, "Video Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            } else {
                                responseString = "Error occurred! Http Status Code: "
                                        + statusCode;
                                Toast.makeText(Music_Activity.this, responseString, Toast.LENGTH_SHORT).show();
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

    private void UploadVideo(){

        ProgressDialog progressDialog = new ProgressDialog(Music_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final Map<String, String> params = new HashMap();

        params.put("user_id", Userid);
        params.put("topic", "topic1");
        params.put("format", "mp4");
        params.put("category", "Music");


        params.put("file_name", String.valueOf(selectedImageUri));



        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/createshowcase", parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();


            }
        });


        Volley.newRequestQueue(Music_Activity.this).add(jsonRequest);



    }

//    private void Upload(){
//        VolleyMultipartRequest  multipartRequest = new VolleyMultipartRequest(Request.Method.POST,
//                "https://rentopool.com/starkwiz/api/auth/createshowcase", new Response.Listener<NetworkResponse>() {
//            @Override
//            public void onResponse(NetworkResponse response) {
//                String resultResponse = new String(response.data);
//                try {
//                    JSONObject result = new JSONObject(resultResponse);
//
//                    Log.d("results",result.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                NetworkResponse networkResponse = error.networkResponse;
//                String errorMessage = "Unknown error";
//                if (networkResponse == null) {
//                    if (error.getClass().equals(TimeoutError.class)) {
//                        errorMessage = "Request timeout";
//                    } else if (error.getClass().equals(NoConnectionError.class)) {
//                        errorMessage = "Failed to connect server";
//                    }
//                } else {
//                    String result = new String(networkResponse.data);
//                    try {
//                        JSONObject response = new JSONObject(result);
//                        String status = response.getString("status");
//                        String message = response.getString("message");
//
//                        Log.e("Error Status", status);
//                        Log.e("Error Message", message);
//
//                        if (networkResponse.statusCode == 404) {
//                            errorMessage = "Resource not found";
//                        } else if (networkResponse.statusCode == 401) {
//                            errorMessage = message+" Please login again";
//                        } else if (networkResponse.statusCode == 400) {
//                            errorMessage = message+ " Check your inputs";
//                        } else if (networkResponse.statusCode == 500) {
//                            errorMessage = message+" Something is getting wrong";
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.i("Error", errorMessage);
//                error.printStackTrace();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("user_id", Userid);
//                params.put("topic", "topic1");
//                params.put("format", "mp4");
//                params.put("category", "Music");
//
//                return params;
//            }
//
//            @Override
//            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
//
//                Map<String, VolleyMultipartRequest.DataPart> params = new HashMap<>();
//
//                params.put("file_name", new VolleyMultipartRequest.DataPart(filemanagerstring,selectedImageUri,"mp4"));
//
//                params.put("uploaded_file_sig", new VolleyMultipartRequest.DataPart("Signature", AppHelper.getFileDataFromDrawable(getBaseContext(), imageview2.getDrawable()), "image/jpeg"));
//
//                return params;
//            }
//        };
//
//        MySingleton.getInstance(getBaseContext()).addToRequestque(multipartRequest);
//    }

//    private void Upload() throws IOException {
////        FileInputStream fileInputStream = new FileInputStream(selectedFile);
//        URL url = new URL("https://rentopool.com/starkwiz/api/auth/createshowcase");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setDoInput(true);//Allow Inputs
//        connection.setDoOutput(true);//Allow Outputs
//        connection.setUseCaches(false);//Don't use a cached Copy
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Connection", "Keep-Alive");
//        connection.setRequestProperty("ENCTYPE", "multipart/form-data");
//        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//        connection.setRequestProperty("uploaded_file", selectedFilePath);
//
//        //creating new dataoutputstream
//        dataOutputStream = new DataOutputStream(connection.getOutputStream());
//
//        //writing bytes to data outputstream
//        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
//        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
//                + selectedFilePath + "\"" + lineEnd);
//
//        dataOutputStream.writeBytes(lineEnd);
//
//        //returns no. of bytes present in fileInputStream
//        bytesAvailable = fileInputStream.available();
//        //selecting the buffer size as minimum of available bytes or 1 MB
//        bufferSize = Math.min(bytesAvailable, maxBufferSize);
//        //setting the buffer as byte array of size of bufferSize
//        buffer = new byte[bufferSize];
//
//        //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
//        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//        //loop repeats till bytesRead = -1, i.e., no bytes are left to read
//        while (bytesRead > 0) {
//            //write the bytes read from inputstream
//            dataOutputStream.write(buffer, 0, bufferSize);
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//        }
//
//        dataOutputStream.writeBytes(lineEnd);
//        dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//        serverResponseCode = connection.getResponseCode();
//        String serverResponseMessage = connection.getResponseMessage();
//
//        Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);
//
//        //response code of 200 indicates the server status OK
//        if (serverResponseCode == 200) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/" + fileName);
//                }
//            });
//        }
//
//        //closing the input and output streams
//        fileInputStream.close();
//        dataOutputStream.flush();
//        dataOutputStream.close();
//    }

    private class UploadVideoToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {

            try {


                HttpsTrustManager.allowAllSSL();

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
                    if (statusCode == 200) {
                        // Server response
                        responseString = EntityUtils.toString(r_entity);
                        try {
                            JSONObject obj = new JSONObject(responseString);
                            JSONObject res = obj.getJSONObject("response");
                            String mStatus = res.getString("status");
                            Log.e("Json status", mStatus);
                            if (mStatus.equalsIgnoreCase("1")) {
                                Toast.makeText(Music_Activity.this, "Video Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else {
                        responseString = "Error occurred! Http Status Code: "
                                + statusCode;
                        Toast.makeText(Music_Activity.this, responseString, Toast.LENGTH_SHORT).show();
                    }


                } catch (ClientProtocolException e) {
                    responseString = e.toString();
                } catch (IOException e) {
                    responseString = e.toString();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("Response for video", "Response from server: " + result);



            // showing the server response in an alert dialog
// showAlert(result);

            super.onPostExecute(result);
        }

    }
}