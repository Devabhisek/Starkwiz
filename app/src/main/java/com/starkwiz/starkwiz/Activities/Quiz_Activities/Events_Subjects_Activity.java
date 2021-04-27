package com.starkwiz.starkwiz.Activities.Quiz_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.starkwiz.starkwiz.Adapter.GridAdapter.Getevents_Subjects_Adapter;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.ModelClass.Event_Subject_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Events_Subjects_Activity extends AppCompatActivity {

    GridView subject_grid;
    String clss,event_id;
    ArrayList<Event_Subject_Modelclass>list_event_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__subjects_);
        subject_grid = findViewById(R.id.subject_grid);
        list_event_subject = new ArrayList<>();

        event_id = getIntent().getStringExtra("event_id");

         clss = SharedPrefManager.getInstance(Events_Subjects_Activity.this).getUser().getCls();

         GetEventSubjects();
    }

    private void GetEventSubjects(){

        ProgressDialog dialog = new ProgressDialog(Events_Subjects_Activity.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getsubjectforevent?class=4&event_id=" + event_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String eventInformation = jsonObject.getString("eventInformation");

                            JSONArray array = new JSONArray(eventInformation);

                            for (int i = 0 ; i<array.length() ; i++){

                                JSONObject object = array.getJSONObject(i);

                                String Status = object.getString("is_active");

                                if (Status.equals("YES")){

                                    Event_Subject_Modelclass event_subject_modelclass = new Event_Subject_Modelclass(

                                            object.getString("id"),
                                            object.getString("event_id"),
                                            object.getString("class"),
                                            object.getString("subject_name"),
                                            object.getString("test"),
                                            object.getString("test_id"),
                                            object.getString("subject_id"),
                                            object.getString("module_id"),
                                            object.getString("module_name"),
                                            object.getString("is_active")

                                    );

                                    list_event_subject.add(event_subject_modelclass);

                                }

                            }

                            Getevents_Subjects_Adapter adapter = new Getevents_Subjects_Adapter(list_event_subject,Events_Subjects_Activity.this);
                            subject_grid.setAdapter(adapter);
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

        MySingleton.getInstance(Events_Subjects_Activity.this).addToRequestque(stringRequest);
    }


}