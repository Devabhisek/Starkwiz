package com.starkwiz.starkwiz.Fragments.ShowcaseFragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.starkwiz.starkwiz.Activities.Achivement_Showcase_Activity;
import com.starkwiz.starkwiz.Activities.ShowCase_Scoreboard_Activity;
import com.starkwiz.starkwiz.Adapter.GridAdapter.Showcase_GridViewAdapter;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.ShowCase_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class All_Showcase_Fragment extends Fragment {

    CardView card_friend,card_video;
    ArrayList<ShowCase_ModelClass>list_modelclass;
    GridView lv_showcase;
    String Userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__showcase_, container, false);
        card_friend = view.findViewById(R.id.card_friend);
        lv_showcase = view.findViewById(R.id.sowcase_grid);
        list_modelclass = new ArrayList<>();
        Userid = SharedPrefManager.getInstance(getActivity()).getUser().getId();
        Getshowcase();

        return view;
    }

    private void Getshowcase(){

        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getshowcase?user_id="+Userid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();

                        try {
                            JSONObject object = new JSONObject(response);

                            String Information = object.getString("Information");

                            JSONArray array = new JSONArray(Information);

                            for (int i = 0 ; i<array.length(); i++){

                                JSONObject jsonObject = array.getJSONObject(i);

                                ShowCase_ModelClass showCase_modelClass = new ShowCase_ModelClass(
                                        jsonObject.getString("id"),
                                        jsonObject.getString("user_id"),
                                        jsonObject.getString("file_name"),
                                        jsonObject.getString("likes"),
                                        jsonObject.getString("views"),
                                        jsonObject.getString("topic"),
                                        jsonObject.getString("video_type"),
                                        jsonObject.getString("category")
                                );
                                list_modelclass.add(showCase_modelClass);

                            }

                            Showcase_GridViewAdapter adapter = new Showcase_GridViewAdapter(list_modelclass,getActivity());
                            lv_showcase.setAdapter(adapter);
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
}