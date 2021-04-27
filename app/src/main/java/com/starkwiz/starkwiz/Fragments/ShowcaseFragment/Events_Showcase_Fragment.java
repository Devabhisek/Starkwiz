package com.starkwiz.starkwiz.Fragments.ShowcaseFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.starkwiz.starkwiz.Adapter.GridAdapter.Getevents_GridViewAdapter;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.ModelClass.Event_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Events_Showcase_Fragment extends Fragment {

    GridView lv_events;
    ArrayList<Event_ModelClass>list_events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events__showcase_, container, false);
            lv_events = view.findViewById(R.id.lv_events);
            list_events = new ArrayList<>();

            GetEvents();
        return view;
    }

    private void GetEvents(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://rentopool.com/starkwiz/api/auth/geteventname",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0 ; i <array.length() ; i++){
                                JSONObject object = array.getJSONObject(i);

                                String Status = object.getString("is_active");

                                if (Status.equals("true")){

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