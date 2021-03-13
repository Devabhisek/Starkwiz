package com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.starkwiz.starkwiz.Activities.Signup_Personal_Activity;
import com.starkwiz.starkwiz.Adapter.Recylerview_Adapter.ScoreCardList_Adapter;
import com.starkwiz.starkwiz.LinkingClass.AlertBoxClasses;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.ModelClass.Score_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AchievementFragment extends Fragment {

    LinearLayout linear_performance,linear_achievement;
    String UserId;
    ArrayList<Score_Modelclass>list_scorecard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);
        linear_performance=view.findViewById(R.id.linear_performance);
        linear_achievement=view.findViewById(R.id.linear_achievement);

        UserId = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        linear_performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.alert_showcase);
                Window window = dialog.getWindow();
                dialog.show();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                RecyclerView lv_showcase = dialog.findViewById(R.id.lv_showcase);
                list_scorecard = new ArrayList<>();
                lv_showcase.setHasFixedSize(true);
                lv_showcase.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

                GetScore(lv_showcase);

            }
        });

        linear_achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_achievement);
                Window window = dialog.getWindow();
                dialog.show();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        });


        return view;
    }

    public void GetScore(RecyclerView view){

        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading");
        dialog.show();
        dialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getscoredetails?user_id=45" ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();

                        if (response.equals("\n[]")){

                            AlertBoxClasses.SimpleAlertBox(getActivity(),"No Scoreboard found");
                        }else {

                            try {
                                JSONArray array = new JSONArray(response);

                                for (int i =0; i<array.length() ; i++) {
                                    JSONObject object = array.getJSONObject(i);

                                    Score_Modelclass modelclass = new Score_Modelclass(

                                            object.getString("id"),
                                            object.getString("user_id"),
                                            object.getString("test_id"),
                                            object.getString("test_id"),
                                            object.getString("module_id"),
                                            object.getString("subject_name"),
                                            object.getString("total_question"),
                                            object.getString("total_marks"),
                                            object.getString("total_marks_obtained"),
                                            object.getString("total_points_obtained"),
                                            object.getString("subject_id"),
                                            object.getString("total_time"),
                                            object.getString("total_acquired_time"),
                                            object.getString("total_correct_answer"),
                                            object.getString("module_name"),
                                            object.getString("first_name"),
                                            object.getString("last_name"),
                                            object.getString("class"),
                                            object.getString("district"),
                                            object.getString("state")
                                    );

                                    list_scorecard.add(modelclass);
                                }

                                ScoreCardList_Adapter adapter = new ScoreCardList_Adapter(list_scorecard,getActivity());
                                view.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


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