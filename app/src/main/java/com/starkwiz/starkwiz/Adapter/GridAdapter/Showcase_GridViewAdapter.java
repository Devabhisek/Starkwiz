package com.starkwiz.starkwiz.Adapter.GridAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.khizar1556.mkvideoplayer.MKPlayer;
import com.potyvideo.library.AndExoPlayerView;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Arts_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Dance_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Debate_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Declamation;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Elocution_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Music_Activity;
import com.starkwiz.starkwiz.Activities.Signup_Activities.Signup_Personal_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.LinkingClass.ShowCase_ModelClass;
import com.starkwiz.starkwiz.R;

import java.io.File;
import java.util.List;

public class Showcase_GridViewAdapter extends BaseAdapter {

    public Activity context;

    private List<ShowCase_ModelClass> listitems;

    public Showcase_GridViewAdapter(List<ShowCase_ModelClass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;
    }



    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creating a linear layout
        View grid;


        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.custom_allshowcase, parent, false);

        }
        else {
            grid =(View) convertView;
        }

            final ShowCase_ModelClass ShowCase_ModelClass = listitems.get(position);

            AndExoPlayerView andExoPlayerView = grid.findViewById(R.id.andExoPlayerView);
            CardView card_showcase = grid.findViewById(R.id.card_showcase);


        String video ="https://rentopool.com/starkwiz/"+ShowCase_ModelClass.getFile_name();

        andExoPlayerView.setSource(video);

        card_showcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.alert_video_showcase);

                String video ="https://rentopool.com/starkwiz/"+ShowCase_ModelClass.getFile_name();

                AndExoPlayerView andExoPlayerView = dialog.findViewById(R.id.andExoPlayerView);

                andExoPlayerView.setSource(video);

                Window window = dialog.getWindow();
                dialog.show();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        });


        return grid;
    }


}