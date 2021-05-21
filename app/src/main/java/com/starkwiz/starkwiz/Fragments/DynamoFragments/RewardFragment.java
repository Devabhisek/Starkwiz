package com.starkwiz.starkwiz.Fragments.DynamoFragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RewardFragment extends Fragment {

    TextView  txt_fixture_reward_points,txt_fixture_reward_totalpoints,txt_fixturerewards_details,
            txt_consecutive,txt_totalconsecutive,txt_consecutive_details,txt_talentreward_detail,
            txt_highest_talentreward,txt_highest_talentname,txt_current_talentpoints,txt_current_talentname;
    String User_ID, Highestrank,Currentrank;
    RoundedHorizontalProgressBar progress_bar_1,progress_bar_2,progress_bar_3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        txt_fixture_reward_points = view.findViewById(R.id.txt_fixture_reward_points);
        txt_fixture_reward_totalpoints = view.findViewById(R.id.txt_fixture_reward_totalpoints);
        txt_fixturerewards_details = view.findViewById(R.id.txt_fixturerewards_details);
        txt_consecutive = view.findViewById(R.id.txt_consecutive);
        txt_totalconsecutive = view.findViewById(R.id.txt_totalconsecutive);
        txt_consecutive_details = view.findViewById(R.id.txt_consecutive_details);
        txt_talentreward_detail = view.findViewById(R.id.txt_talentreward_detail);
        txt_highest_talentreward = view.findViewById(R.id.txt_highest_talentreward);
        txt_highest_talentname = view.findViewById(R.id.txt_highest_talentname);
        txt_current_talentpoints = view.findViewById(R.id.txt_current_talentpoints);
        txt_current_talentname = view.findViewById(R.id.txt_current_talentname);
        progress_bar_1 = view.findViewById(R.id.progress_bar_1);
        progress_bar_2 = view.findViewById(R.id.progress_bar_2);
        progress_bar_3 = view.findViewById(R.id.progress_bar_3);
        User_ID = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        GetFixtureRewards();
        GetConsecutiveRewards();
        GetTalentReward();
        return view;
    }

    public void GetFixtureRewards(){

        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getsubscribedsubjects?user_id=" + User_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    String pointmark = object.getString("pointmark");
                    String userpoints = object.getString("userpoints");

                    txt_fixture_reward_points.setText(userpoints);
                    txt_fixture_reward_totalpoints.setText(" / "+pointmark);

                    if (Integer.parseInt(userpoints)==Integer.parseInt(pointmark)
                            || Integer.parseInt(userpoints)>Integer.parseInt(pointmark)){

                        txt_fixturerewards_details.setText("Completed");
                        txt_fixturerewards_details.setTextColor(getResources().getColor(R.color.theme_blue));
                        progress_bar_1.animateProgress(2000, 0, 100); // (animationDuration, oldProgress, newProgress)
                    }
                    else {

                        int points_left = Integer.parseInt(pointmark)-Integer.parseInt(userpoints);

                        txt_fixturerewards_details.setText("You need"+String.valueOf(points_left)+"points more\n to unlock your First Reward !");

                        int points = Integer.parseInt(pointmark)/2;


                        if (Integer.parseInt(userpoints)==points){
                            progress_bar_1.animateProgress(2000, 0, 50); // (animationDuration, oldProgress, newProgress)
                        }else{
                            progress_bar_1.animateProgress(2000, 0, 22); // (animationDuration, oldProgress, newProgress)
                        }
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

        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }

    public void GetConsecutiveRewards(){
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/consecutiverewards?user_id=" + User_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    String consuqutive_reeards_count = object.getString("consuqutive_reeards_count");
                    String consecutivetotal = txt_totalconsecutive.getText().toString();
                    consecutivetotal = consecutivetotal.replace("/","");

                    if (consuqutive_reeards_count.equals(consecutivetotal)){
                        txt_consecutive_details.setText("Completed");
                    }else {
                        String consecutive_remain = String.valueOf(Integer.parseInt(consecutivetotal)-Integer.parseInt(consuqutive_reeards_count));
                        txt_consecutive_details.setText("You need to win Silver "+ consecutive_remain +" more \n time to unlock your Reward !");
                    }

                    txt_consecutive.setText(consuqutive_reeards_count);

                    if (consuqutive_reeards_count.equals(consecutivetotal)){

                        progress_bar_2.animateProgress(2000, 0, 100); // (animationDuration, oldProgress, newProgress)
                    }
                    else if (Integer.parseInt(consuqutive_reeards_count)
                            > 1){
                        progress_bar_2.animateProgress(2000, 0, 50); // (animationDuration, oldProgress, newProgress)
                    }
                    else if (Integer.parseInt(consuqutive_reeards_count)==0){
                        progress_bar_2.animateProgress(2000, 0, 5); // (animationDuration, oldProgress, newProgress)
                    }else {
                        progress_bar_2.animateProgress(2000, 0, 0); // (animationDuration, oldProgress, newProgress)
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

        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }

    public void GetTalentReward(){
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getsubscribedsubjects?user_id=" + User_ID,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    String talentrewards = object.getString("talent_rewards");

                    JSONArray array = new JSONArray(talentrewards);

                    for (int i = 0 ; i<array.length() ; i++){

                        JSONObject jsonObject = array.getJSONObject(i);

                        if (i==0){
                            txt_highest_talentreward.setText(jsonObject.getString("user_point"));
                            txt_highest_talentname.setText(jsonObject.getString("rank_name"));
                            txt_talentreward_detail.setText("Last Starkwiz talent reward was \n worth " +jsonObject.getString("user_point")+" points !");

                            Highestrank = jsonObject.getString("user_point");



                        }else {
                            txt_current_talentpoints.setText(jsonObject.getString("user_point"));
                            txt_current_talentname.setText(jsonObject.getString("rank_name"));

                            Currentrank = jsonObject.getString("user_point");


                        }

                        if (Currentrank==null){
                            Currentrank = "0";
                        }


                        if (Integer.parseInt(Currentrank)==Integer.parseInt(Highestrank)){
                            progress_bar_3.animateProgress(2000, 0, 100); // (animationDuration, oldProgress, newProgress)
                        }else {
                            progress_bar_3.animateProgress(2000, 0, 5); // (animationDuration, oldProgress, newProgress)
                        }
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

        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }

}