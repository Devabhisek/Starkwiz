package com.starkwiz.starkwiz.Adapter.Recylerview_Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.starkwiz.starkwiz.ModelClass.Core_ModelClass;
import com.starkwiz.starkwiz.ModelClass.Core_Subjectbyplans_ModelClass;
import com.starkwiz.starkwiz.R;

import java.util.ArrayList;
import java.util.HashSet;

import tourguide.tourguide.TourGuide;

import static android.content.Context.MODE_PRIVATE;

public class CoreSubjects_Adapter extends RecyclerView.Adapter<CoreSubjects_Adapter.ViewHolder> {

    public Context context;
    private ArrayList<Core_Subjectbyplans_ModelClass> listitems;
    final ArrayList<Core_ModelClass> arrPackage_Core=new ArrayList<>();
    SharedPreferences sharedPreferences;
//    private CoreSubjectAdapterListener listener;
    private TourGuide mTourGuideHandler;


    public CoreSubjects_Adapter(ArrayList<Core_Subjectbyplans_ModelClass> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public CoreSubjects_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View latest_v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_subjectplans, parent, false);
        return new CoreSubjects_Adapter.ViewHolder(latest_v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CoreSubjects_Adapter.ViewHolder holder, final int position) {

        try {

            final Core_Subjectbyplans_ModelClass Core_Subjectbyplans_ModelClass = listitems.get(position);

            holder.chk_subject.setText(Core_Subjectbyplans_ModelClass.getSubject_name());

            holder.chk_subject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()){

                        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.clear();
                        editor1.apply();

                        Core_ModelClass core_modelClass= new Core_ModelClass(Core_Subjectbyplans_ModelClass.getId(),
                                Core_Subjectbyplans_ModelClass.getSubject_name(),
                                Core_Subjectbyplans_ModelClass.getSubject_type());

                        arrPackage_Core.add(core_modelClass);

                        HashSet hs = new HashSet();

                        hs.addAll(arrPackage_Core); // demoArrayList= name of arrayList from which u want to remove duplicates

                        arrPackage_Core.clear();
                        arrPackage_Core.addAll(hs);

                        Gson gson = new Gson();
                        String json = gson.toJson(arrPackage_Core);
                        Log.d("json",json);

                        sharedPreferences=context.getSharedPreferences("USER",MODE_PRIVATE) ;
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Set",json );
                        editor.commit();

                    }else if (!compoundButton.isChecked()){
                        for (int i =0;i<arrPackage_Core.size();i++){

                            if (arrPackage_Core.get(i).getSubjectname()==Core_Subjectbyplans_ModelClass.getSubject_name()){

                                arrPackage_Core.remove(i);
                            }
                        }

                        Gson gson = new Gson();
                        String json = gson.toJson(arrPackage_Core);
                        Log.d("js",json);

                        sharedPreferences=context.getSharedPreferences("USER",MODE_PRIVATE) ;
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Set",json );
                        editor.commit();
                    }
                }
            });


//            holder.chk_subject.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (holder.chk_subject.isChecked()){
//
//                        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
//                        editor1.clear();
//                        editor1.apply();
//
//                        Core_ModelClass core_modelClass= new Core_ModelClass(Core_Subjectbyplans_ModelClass.getId(),
//                                Core_Subjectbyplans_ModelClass.getSubject_name(),
//                                Core_Subjectbyplans_ModelClass.getSubject_type());
//
//                        arrPackage_Core.add(core_modelClass);
//
//                        HashSet hs = new HashSet();
//
//                        hs.addAll(arrPackage_Core); // demoArrayList= name of arrayList from which u want to remove duplicates
//
//                        arrPackage_Core.clear();
//                        arrPackage_Core.addAll(hs);
//
//                        Gson gson = new Gson();
//                        String json = gson.toJson(arrPackage_Core);
//                        Log.d("json",json);
//
//                        sharedPreferences=context.getSharedPreferences("USER",MODE_PRIVATE) ;
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("Set",json );
//                        editor.commit();
//
//                    }else {
//
//                        arrPackage_Core.remove(Core_Subjectbyplans_ModelClass.getSubject_name());
//
//                        Gson gson = new Gson();
//                        String json = gson.toJson(arrPackage_Core);
//                        Log.d("js",json);
//
//                        sharedPreferences=context.getSharedPreferences("USER",MODE_PRIVATE) ;
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("Set",json );
//                        editor.commit();
//                    }
//
//
//
//
//                }
//            });



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

        CheckBox chk_subject;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            chk_subject = itemView.findViewById(R.id.chk_subject);


        }
    }

    public ArrayList<Core_ModelClass> getArrayList_Core(){
        return arrPackage_Core;
    }



    public interface CoreSubjectAdapterListener {
        void onCoreSubjectSelected(Core_Subjectbyplans_ModelClass contact);

    }
}

