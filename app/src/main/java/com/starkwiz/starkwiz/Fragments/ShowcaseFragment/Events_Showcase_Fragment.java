package com.starkwiz.starkwiz.Fragments.ShowcaseFragment;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Adapter.GridAdapter.Getevents_GridViewAdapter;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Event_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Events_Showcase_Fragment extends Fragment {

    GridView lv_events;
    ArrayList<Event_ModelClass>list_events;
    String UserID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events__showcase_, container, false);
            lv_events = view.findViewById(R.id.lv_events);
            list_events = new ArrayList<>();
            UserID = SharedPrefManager.getInstance(getActivity()).getUser().getId();
            GetEvents();
        return view;
    }

    private void GetEvents(){
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://rentopool.com/starkwiz/api/auth/geteventname",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0 ; i <array.length() ; i++){
                                JSONObject object = array.getJSONObject(i);

                                String Status = object.getString("is_active");

                                if (Status.equals("YES")){

                                    Event_ModelClass modelClass = new Event_ModelClass(

                                            object.getString("id"),
                                            object.getString("event_name"),
                                            object.getString("event_start_date"),
                                            object.getString("event_end_date"),
                                            object.getString("is_active")
                                    );

                                    list_events.add(modelClass);
                                }


                            }

                            Getevents_GridViewAdapter getevents_gridViewAdapter = new Getevents_GridViewAdapter(list_events,getActivity());
                            lv_events.setAdapter(getevents_gridViewAdapter);
                            CreateNotification(UserID,"Events are started. Join in events. ");
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
        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }

    public void CreateNotification(String user_id, String notification_text){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                        //notificationDialog();
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

                Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });


        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

//    private void notificationDialog() {
//        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        String NOTIFICATION_CHANNEL_ID = "tutorialspoint_01";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
//            // Configure the notification channel.
//            notificationChannel.setDescription("Sample Channel description");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//            notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity(), NOTIFICATION_CHANNEL_ID);
//        notificationBuilder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.logo)
//                .setTicker("Tutorialspoint")
//                .setPriority(Notification.PRIORITY_MAX)
//                .setContentTitle("Starkwiz")
//                .setContentText("Events are started. Join in events. ")
//                .setContentInfo("Information");
//        notificationManager.notify(1, notificationBuilder.build());
//    }
}