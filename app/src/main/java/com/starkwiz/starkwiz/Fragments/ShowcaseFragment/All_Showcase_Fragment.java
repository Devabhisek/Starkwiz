package com.starkwiz.starkwiz.Fragments.ShowcaseFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.starkwiz.starkwiz.Activities.Achivement_Showcase_Activity;
import com.starkwiz.starkwiz.Activities.ShowCase_Scoreboard_Activity;
import com.starkwiz.starkwiz.R;


public class All_Showcase_Fragment extends Fragment {

    CardView card_friend,card_video;
    //RelativeLayout rl_scoreboard,rl_achievement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__showcase_, container, false);
        card_friend = view.findViewById(R.id.card_friend);
        card_video = view.findViewById(R.id.card_video);
        //rl_scoreboard = view.findViewById(R.id.rl_scoreboard);
        //rl_achievement = view.findViewById(R.id.rl_achievement);

        card_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.alert_music_showcase);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        });

        card_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.alert_video_showcase);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        });
       // rl_scoreboard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), ShowCase_Scoreboard_Activity.class));
//                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//            }
//        });
//
//        rl_achievement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), Achivement_Showcase_Activity.class));
//                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//            }
//        });
//
        return view;
    }
}