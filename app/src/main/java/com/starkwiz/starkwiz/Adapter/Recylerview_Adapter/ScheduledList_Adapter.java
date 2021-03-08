package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.starkwiz.starkwiz.Activities.Subject_Schedule_Detail_Activity;
import com.starkwiz.starkwiz.Activities.Subjectwise_Syllabus_Activity;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.Scheduled_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Scheduled_ModelClass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ScheduledList_Adapter extends RecyclerView.Adapter<ScheduledList_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<Scheduled_ModelClass> listitems;
    private int mCheckedPostion = -1;
    String UserId;


    public ScheduledList_Adapter(ArrayList<Scheduled_ModelClass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_scheduled_list, parent, false);
        return new ScheduledList_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        try {
            final Scheduled_ModelClass Scheduled_ModelClass = listitems.get(position);

            holder.txt_scheduled_date.setText("Date\n"+Scheduled_ModelClass.getDate());
            holder.txt_scheduled_time.setText("Time\n"+Scheduled_ModelClass.getTime()+" "+Scheduled_ModelClass.getTime_type());

            final Map<String, String> params = new HashMap();

            params.put("module_id", Scheduled_ModelClass.getModule_id());


            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLS.get_moduleByID, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {



                    try {
                        String Information = response.getString("1");

                        JSONArray array = new JSONArray(Information);

                        for (int i = 0 ; i <array.length() ; i++){

                            JSONObject object = array.getJSONObject(i);

                           String hour = object.getString("hour");
                           String mins = object.getString("minutes");

                           int minutes = (Integer.parseInt(hour)*60)+Integer.parseInt(mins);

                            holder.txt_scheduled_modulename.setText(object.getString("module_name"));
                            holder.txt_schdl_mark.setText(object.getString("totalmark"));
                            holder.txt_scheduled_duration.setText(String.valueOf(minutes+" mins"));
                            holder.txt_scheduled_subjectname.setText(object.getString("subjectname"));

                            UserId = SharedPrefManager.getInstance(context).getUser().getId();
                            Calendar cal=Calendar.getInstance();
                            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                            String month = month_date.format(cal.getTime());

                            holder.linear_schedule.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(view.getContext(), Subject_Schedule_Detail_Activity.class);
                                    intent.putExtra("test_id",Scheduled_ModelClass.getTest_id());
                                    intent.putExtra("subject_id",Scheduled_ModelClass.getSubject_id());
                                    intent.putExtra("module_id",Scheduled_ModelClass.getModule_id());
                                    intent.putExtra("user_id",UserId);
                                    intent.putExtra("month",month);
                                    intent.putExtra("hour",hour);
                                    intent.putExtra("minutes",minutes);
                                    intent.putExtra("subject",holder.txt_scheduled_subjectname.getText().toString().trim());
                                    intent.putExtra("module",holder.txt_scheduled_modulename.getText().toString().trim());
                                    intent.putExtra("date",Scheduled_ModelClass.getDate());
                                    intent.putExtra("id",Scheduled_ModelClass.getId());
                                    view.getContext().startActivity(intent);
                                }
                            });


                            if (holder.txt_scheduled_subjectname.getText().toString().equals("English")){

                                holder.linear_schedule.setBackgroundResource(R.mipmap.english);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Mathematics")){

                                holder.linear_schedule.setBackgroundResource(R.mipmap.mathematics);

                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Science and Technology")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.science);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Art")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.art);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Biology")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.biology);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Chemistry")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.chemistry);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Computer Science")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.computer);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Dance")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.dancing);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Debate")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.debate);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Declamation")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.declamation);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Elocution")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.elocution);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Environmental Science")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.environmentalscience);
                            } else if (holder.txt_scheduled_subjectname.getText().toString().equals("General Knowledge")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.generalknowledge);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Geography")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.geography);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("History")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.history);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Music")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.music);
                            }else if (holder.txt_scheduled_subjectname.getText().toString().equals("Physics")){
                                holder.linear_schedule.setBackgroundResource(R.mipmap.physics);
                            }else  {
                                holder.linear_schedule.setBackgroundResource(R.mipmap.socialscience);
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();



                }
            });


            Volley.newRequestQueue(context).add(jsonRequest);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardview_math;
        TextView txt_scheduled_date,txt_scheduled_time,txt_scheduled_subjectname,
                txt_scheduled_modulename,txt_scheduled_duration,txt_schdl_mark;
        LinearLayout linear_schedule;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            cardview_math = itemView.findViewById(R.id.cardview_math);
            txt_scheduled_date = itemView.findViewById(R.id.txt_scheduled_date);
            txt_scheduled_time = itemView.findViewById(R.id.txt_scheduled_time);
            txt_scheduled_subjectname = itemView.findViewById(R.id.txt_scheduled_subjectname);
            txt_scheduled_modulename = itemView.findViewById(R.id.txt_scheduled_modulename);
            txt_scheduled_duration = itemView.findViewById(R.id.txt_scheduled_duration);
            linear_schedule = itemView.findViewById(R.id.linear_schedule);
            txt_schdl_mark = itemView.findViewById(R.id.txt_schdl_mark);



        }
    }

}
