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
import com.starkwiz.starkwiz.Activities.Quiz_Activities.Music_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.ModelClass.Event_Subject_Modelclass;
import com.starkwiz.starkwiz.ModelClass.Event_Subject_Modelclass;
import com.starkwiz.starkwiz.R;

import java.util.List;

public class Getevents_Subjects_Adapter extends BaseAdapter {

    public Context context;

    private List<Event_Subject_Modelclass> listitems;

    public Getevents_Subjects_Adapter(List<Event_Subject_Modelclass> listitems, Context context) {
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
            grid = inflater.inflate(R.layout.custom_getsubjects, parent, false);

        }
        else {
            grid =(View) convertView;
        }

        final Event_Subject_Modelclass Event_Subject_Modelclass = listitems.get(position);
        TextView txt_subjectname = (TextView) grid.findViewById(R.id.txt_subjectname);
        //TextView txt_subjectduration = (TextView) grid.findViewById(R.id.txt_subjectduration);

        RelativeLayout card_subject = grid.findViewById(R.id.card_subject);

        txt_subjectname.setText(Event_Subject_Modelclass.getSubject_name());

        if (txt_subjectname.getText().toString().equals("English")){

            card_subject.setBackgroundResource(R.mipmap.english);
        }else if (txt_subjectname.getText().toString().equals("Mathematics")){

            card_subject.setBackgroundResource(R.mipmap.mathematics);

        }else if (txt_subjectname.getText().toString().equals("Science and Technology")){
            card_subject.setBackgroundResource(R.mipmap.science);
        }else if (txt_subjectname.getText().toString().equals("Art")){
            card_subject.setBackgroundResource(R.mipmap.art);
        }else if (txt_subjectname.getText().toString().equals("Biology")){
            card_subject.setBackgroundResource(R.mipmap.biology);
        }else if (txt_subjectname.getText().toString().equals("Chemistry")){
            card_subject.setBackgroundResource(R.mipmap.chemistry);
        }else if (txt_subjectname.getText().toString().equals("Computer Science")){
            card_subject.setBackgroundResource(R.mipmap.computer);
        }else if (txt_subjectname.getText().toString().equals("Dance")){
            card_subject.setBackgroundResource(R.mipmap.dancing);
        }else if (txt_subjectname.getText().toString().equals("Debate")){
            card_subject.setBackgroundResource(R.mipmap.debate);
        }else if (txt_subjectname.getText().toString().equals("Declamation")){
            card_subject.setBackgroundResource(R.mipmap.declamation);
        }else if (txt_subjectname.getText().toString().equals("Elocution")){
            card_subject.setBackgroundResource(R.mipmap.elocution);
        }else if (txt_subjectname.getText().toString().equals("Environmental Science")){
            card_subject.setBackgroundResource(R.mipmap.environmentalscience);
        } else if (txt_subjectname.getText().toString().equals("General Knowledge")){
            card_subject.setBackgroundResource(R.mipmap.generalknowledge);
        }else if (txt_subjectname.getText().toString().equals("Geography")){
            card_subject.setBackgroundResource(R.mipmap.geography);
        }else if (txt_subjectname.getText().toString().equals("History")){
            card_subject.setBackgroundResource(R.mipmap.history);
        }else if (txt_subjectname.getText().toString().equals("Music")){
            card_subject.setBackgroundResource(R.mipmap.music);
        }else if (txt_subjectname.getText().toString().equals("Physics")){
            card_subject.setBackgroundResource(R.mipmap.physics);
        }else  {
            card_subject.setBackgroundResource(R.mipmap.socialscience);
        }


        card_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Event_Subject_Modelclass.getSubject_name().equals("Art")){
                    Intent intent = new Intent(view.getContext(), Arts_Activity.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);

                }else if (Event_Subject_Modelclass.getSubject_name().equals("Dance")){
                    Intent intent = new Intent(view.getContext(), Dance_Activity.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);
                }else if (Event_Subject_Modelclass.getSubject_name().equals("Debate")){
                    Intent intent = new Intent(view.getContext(), Debate_Activity.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);
                }else if (Event_Subject_Modelclass.getSubject_name().equals("Declamation")){
                    Intent intent = new Intent(view.getContext(), Declamation.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);
                }else if (Event_Subject_Modelclass.getSubject_name().equals("Elocution")){
                    Intent intent = new Intent(view.getContext(), Elocution_Activity.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);
                }else if (Event_Subject_Modelclass.getSubject_name().equals("Music")){
                    Intent intent = new Intent(view.getContext(), Music_Activity.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);
                }
                else {
                    Intent intent = new Intent(view.getContext(), Subjectwise_Syllabus_Activity.class);
                    intent.putExtra("subject",Event_Subject_Modelclass.getSubject_name());
                    view.getContext().startActivity(intent);

                }

            }
        });



        return grid;
    }


}