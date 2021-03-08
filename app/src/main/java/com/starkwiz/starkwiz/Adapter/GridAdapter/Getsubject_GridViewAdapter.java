package com.starkwiz.starkwiz.Adapter.GridAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.ModelClass.GetSubjects_ModelClass;
import com.starkwiz.starkwiz.R;


import java.util.List;

public class Getsubject_GridViewAdapter extends BaseAdapter {

    public Context context;

    private List<GetSubjects_ModelClass> listitems;

    public Getsubject_GridViewAdapter(List<GetSubjects_ModelClass> listitems, Context context) {
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

            final GetSubjects_ModelClass getSubjects_modelClass = listitems.get(position);
            TextView txt_subjectname = (TextView) grid.findViewById(R.id.txt_subjectname);
            //TextView txt_subjectduration = (TextView) grid.findViewById(R.id.txt_subjectduration);

            RelativeLayout card_subject = grid.findViewById(R.id.card_subject);

            txt_subjectname.setText(getSubjects_modelClass.getSubject_name());

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
                    Intent intent = new Intent(view.getContext(), Subjectwise_Syllabus_Activity.class);
                    intent.putExtra("subject",getSubjects_modelClass.getSubject_name());
                    view.getContext().startActivity(intent);

                }
            });


        return grid;
    }


}