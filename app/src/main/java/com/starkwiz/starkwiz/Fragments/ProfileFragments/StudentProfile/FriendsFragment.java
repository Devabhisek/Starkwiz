package com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.R;


public class FriendsFragment extends Fragment {

    RecyclerView lv_followers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        lv_followers = view.findViewById(R.id.lv_followers);
        lv_followers.setHasFixedSize(true);
        lv_followers.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }

    public void GetFollowers(String UserId){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://rentopool.com/starkwiz/api/auth/getfollowers?user_id=" + UserId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getActivity()).addToRequestque(stringRequest);
    }
}