package com.starkwiz.starkwiz.Adapter.GridAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.starkwiz.starkwiz.Activities.Quiz_Activities.Arts_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Dance_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Debate_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Declamation;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Elocution_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Events_Subjects_Activity;
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Music_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.ModelClass.Event_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Event_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.List;

public class Getevents_GridViewAdapter extends BaseAdapter {

    public Context context;

    private List<Event_ModelClass> listitems;

    public Getevents_GridViewAdapter(List<Event_ModelClass> listitems, Context context) {
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
            grid = inflater.inflate(R.layout.custom_events, parent, false);

        }
        else {
            grid =(View) convertView;
        }

            final Event_ModelClass Event_ModelClass = listitems.get(position);





            TextView txt_subjectname = grid.findViewById(R.id.txt_subjectname);
            TextView txt_duration = grid.findViewById(R.id.txt_duration);
            CardView card_events = grid.findViewById(R.id.card_events);

            txt_subjectname.setText(Event_ModelClass.getEvent_name());
            txt_duration.setText("Event starts From "+Event_ModelClass.getEvent_start_date()+
                    " to "+Event_ModelClass.getEvent_end_date());

            card_events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                Intent intent = new Intent(context, Events_Subjects_Activity.class);
                intent.putExtra("event_id",Event_ModelClass.getId());
                v.getContext().startActivity(intent);

                }
            });


        return grid;
    }


}