package com.waymart.waymart.Customer.Adapter.GridAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;
import com.waymart.waymart.Customer.Fragments.CategoryFragments.Fragment_All_Items;
import com.waymart.waymart.Customer.Fragments.CategoryFragments.Fragment_Particular_Items_Lists;
import com.waymart.waymart.Customer.ModelClass.GetCategoriesModelClass;
import com.waymart.waymart.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GridViewAdapter extends BaseAdapter {

    public Context context;

    private List<GetCategoriesModelClass> listitems;

    public GridViewAdapter(List<GetCategoriesModelClass> listitems, Context context) {
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
            grid = inflater.inflate(R.layout.custom_getcategory_gridview, parent, false);

        }
        else {
            grid =(View) convertView;
        }

            final GetCategoriesModelClass getCategoriesModelClass = listitems.get(position);
            TextView textView = (TextView) grid.findViewById(R.id.txt_custom_homegrid_getcategory);
            //ImageView imageView = (ImageView) grid.findViewById(R.id.Img_custom_homegrid_getcategory);
            CardView card_custom_getcategory = (CardView) grid.findViewById(R.id.card_custom_getcategory);

            textView.setText(getCategoriesModelClass.getCatName());
//            Picasso.with(context)
//                    .load(getTopServicesForHomepage_modelClass.getFullPath())
//                    // .placeholder(getActivity().getResources().getDrawable(R.drawable.ic_person_black_24dp))
//                    .into(imageView);

            card_custom_getcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sp = context.getSharedPreferences("key", 0);
                    SharedPreferences.Editor sedt = sp.edit();
                    sedt.putString("CategoryName",getCategoriesModelClass.getCatName());
                    sedt.commit();

                    Fragment newFragment = new Fragment_All_Items();
                    FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });



        return grid;
    }


}