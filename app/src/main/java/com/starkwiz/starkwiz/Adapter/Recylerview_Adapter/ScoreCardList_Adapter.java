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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
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
            holder.txt_scorecard_class.setText(score_modelclass.getCls());
            holder.txt_scorecard_name.setText(score_modelclass.getName()+" "+score_modelclass.getLastname());
            holder.txt_scorecard_location.setText(score_modelclass.getDistrict()+", "+score_modelclass.getState());

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

        TextView txt_scorecard_subjectname,txt_scorecard_modulename,
                txt_scorecard_class,txt_scorecard_name,txt_scorecard_location;
        CircleImageView img_scorecard_profile;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txt_scorecard_subjectname = itemView.findViewById(R.id.txt_scorecard_subjectname);
            txt_scorecard_modulename = itemView.findViewById(R.id.txt_scorecard_modulename);
            txt_scorecard_class = itemView.findViewById(R.id.txt_scorecard_class);
            txt_scorecard_name = itemView.findViewById(R.id.txt_scorecard_name);
            txt_scorecard_location = itemView.findViewById(R.id.txt_scorecard_location);
            img_scorecard_profile = itemView.findViewById(R.id.img_scorecard_profile);



        }
    }





}
