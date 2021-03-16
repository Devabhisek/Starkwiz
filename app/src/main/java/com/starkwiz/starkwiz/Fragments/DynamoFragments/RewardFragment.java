package com.starkwiz.starkwiz.Fragments.DynamoFragments;

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
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.R;

import org.json.JSONException;
import org.json.JSONObject;


public class RewardFragment extends Fragment {

    TextView  txt_fixture_reward_points,txt_fixture_reward_totalpoints,txt_fixturerewards_details;
    String User_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        txt_fixture_reward_points = view.findViewById(R.id.txt_fixture_reward_points);
        txt_fixture_reward_totalpoints = view.findViewById(R.id.txt_fixture_reward_totalpoints);
        txt_fixturerewards_details = view.findViewById(R.id.txt_fixturerewards_details);
        User_ID = SharedPrefManager.getInstance(getActivity()).getUser().getId();

        GetFixtureRewards();
        return view;
    }

    public void GetFixtureRewards(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getsubscribedsubjects?user_id=" + User_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                    }
                    else {

                        int points_left = Integer.parseInt(pointmark)-Integer.parseInt(userpoints);

                        txt_fixturerewards_details.setText("You need"+String.valueOf(points_left)+"points more\n to unlock your First Reward !");
                    }

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