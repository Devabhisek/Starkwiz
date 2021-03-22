package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.starkwiz.starkwiz.LinkingClass.MySingleton;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.LinkingClass.URLS;
import com.starkwiz.starkwiz.ModelClass.GetTestList_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Score_Modelclass;
import com.starkwiz.starkwiz.ModelClass.Score_Modelclass;
import com.starkwiz.starkwiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScoreCardList_Adapter extends RecyclerView.Adapter<ScoreCardList_Adapter.ViewHolder> {

    public Activity context;
    private ArrayList<Score_Modelclass> listitems;
    private int mCheckedPostion = -1;


    public ScoreCardList_Adapter(ArrayList<Score_Modelclass> listitems, Activity context) {
        this.listitems = listitems;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_showcase, parent, false);
        return new ScoreCardList_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        try {
            final Score_Modelclass score_modelclass = listitems.get(position);
            holder.txt_scorecard_subjectname.setText(score_modelclass.getSubject_name());
            holder.txt_scorecard_modulename.setText(score_modelclass.getModule_name());
            holder.txt_scorecard_class.setText("Class "+score_modelclass.getCls());
            holder.txt_scorecard_anspoints.setText(score_modelclass.getTotal_gp());
            holder.txt_scorecard_name.setText(score_modelclass.getName()+" "+score_modelclass.getLastname());
            holder.txt_scorecard_location.setText(score_modelclass.getDistrict()+", "+score_modelclass.getState());
            holder.txt_scored_correctanswer.setText(score_modelclass.getTotal_correct_answer()+"/ "+score_modelclass.getTotal_question());
            holder.txt_scorecard_timebounce.setText(String.valueOf(Integer.parseInt(score_modelclass.getTotal_time())-Integer.parseInt(score_modelclass.getTotal_acquired_time())));


            if (score_modelclass.getAccuracy().equals("null")){
                holder.txt_scorecard_accuracy.setText("0"+"%");
            }else {
                holder.txt_scorecard_accuracy.setText(score_modelclass.getAccuracy()+"%");
            }



            String  strtext = SharedPrefManager.getInstance(context).getUser().getId();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    URLS.Getprofile + strtext, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        String Status=response.getString("success");

                        if (Status.matches("profile found")){

                            String allProfile=response.getString("allProfile");
                            JSONArray array = new JSONArray(allProfile);

                            for (int i = 0 ; i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);


                                        String image = object.getString("profile_image");

                                        image = image.replace("data:image/png;base64,","");
                                        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                        float degrees = 90; //rotation degree
                                        Matrix matrix = new Matrix();
                                        matrix.setRotate(degrees);
                                        decodedByte = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), decodedByte.getHeight(), matrix, true);

                                        holder.img_scorecard_profile.setImageBitmap(decodedByte);


                            }

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

            Volley.newRequestQueue(context).add(jsonObjectRequest);


            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    "https://rentopool.com/starkwiz/api/auth/getrank?user_id=" + strtext,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject object = new JSONObject(response);

                                String Information = object.getString("Information");

                                JSONObject jsonObject = new JSONObject(Information);

                                holder.txt_score_rank.setText("Rank "+jsonObject.getString("rank"));
                                holder.txt_scorecard_rankname.setText(jsonObject.getString("rank_name"));

                                String rank_name =jsonObject.getString("rank_name");

                                if (rank_name.equals("Candy")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.candy_one);;
                                }else if (rank_name.equals("Popsicle")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.popsicle_one);;
                                }else if (rank_name.equals("Bubble")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.bubble_one);;
                                }else if (rank_name.equals("Caramel")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.caramel_one);;
                                }else if (rank_name.equals("Beginner")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.begineer_one);;
                                }else if (rank_name.equals("Enthusiast")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.emthusiast);;
                                }else if (rank_name.equals("Bronze")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.bronze_one);;
                                }else if (rank_name.equals("Silver")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.silver_one);;
                                }else if (rank_name.equals("Gold")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.gold_one);;
                                }else if (rank_name.equals("Star")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.star_one);;
                                }else if (rank_name.equals("Grand Master")){
                                    holder.img_score_rankname.setImageResource(R.mipmap.grandmaster_one);;
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

            MySingleton.getInstance(context).addToRequestque(stringRequest);


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

        TextView txt_scorecard_subjectname,txt_scorecard_modulename,txt_score_rank,txt_scorecard_accuracy,txt_scorecard_anspoints,
                txt_scorecard_class,txt_scorecard_name,txt_scorecard_location,txt_scorecard_rankname,txt_scored_correctanswer,
                txt_scorecard_timebounce;
        CircleImageView img_scorecard_profile;
        ImageView img_score_rankname;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt_scorecard_subjectname = itemView.findViewById(R.id.txt_scorecard_subjectname);
            txt_scorecard_modulename = itemView.findViewById(R.id.txt_scorecard_modulename);
            txt_scorecard_class = itemView.findViewById(R.id.txt_scorecard_class);
            txt_scorecard_name = itemView.findViewById(R.id.txt_scorecard_name);
            txt_scorecard_location = itemView.findViewById(R.id.txt_scorecard_location);
            img_scorecard_profile = itemView.findViewById(R.id.img_scorecard_profile);
            txt_score_rank = itemView.findViewById(R.id.txt_score_rank);
            txt_scorecard_rankname = itemView.findViewById(R.id.txt_scorecard_rankname);
            txt_scorecard_accuracy = itemView.findViewById(R.id.txt_scorecard_accuracy);
            img_score_rankname = itemView.findViewById(R.id.img_score_rankname);
            txt_scored_correctanswer = itemView.findViewById(R.id.txt_scored_correctanswer);
            txt_scorecard_anspoints = itemView.findViewById(R.id.txt_scorecard_anspoints);
            txt_scorecard_timebounce = itemView.findViewById(R.id.txt_scorecard_timebounce);

        }
    }
}
