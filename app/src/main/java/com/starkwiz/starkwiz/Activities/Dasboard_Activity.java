package com.starkwiz.starkwiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.starkwiz.starkwiz.Fragments.DynamoFragments.DynamoFragment;
import com.starkwiz.starkwiz.Fragments.FollowingFragment.FollowingFragment;
import com.starkwiz.starkwiz.Fragments.HubFragment.Hub_UserSelection_Fragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.HubProfile.HubProfile_Fragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.ParentProfile.ParentProfile_Fragment;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.StudentProfile.Profile_Activity;
import com.starkwiz.starkwiz.Fragments.ProfileFragments.TeacherProfile.Teacher_Profile_Fragment;
import com.starkwiz.starkwiz.Fragments.ShowcaseFragment.ShowCaseFragment;
import com.starkwiz.starkwiz.LinkingClass.SharedPrefManager;
import com.starkwiz.starkwiz.R;

public class Dasboard_Activity extends AppCompatActivity {

    BottomNavigationView bottomnavigation;
    ImageView dash_setting;
    String Student_intent,Parent_intent,Teacher_intent,Hub_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard_);

        bottomnavigation = findViewById(R.id.bottomnavigation);
        dash_setting = findViewById(R.id.dash_setting);

        bottomnavigation.setOnNavigationItemSelectedListener(navListner);
        bottomnavigation.setSelectedItemId(R.id.bottom_menu_dynamo);

        dash_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dasboard_Activity.this,SettingActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            //bottomnavigation.getMenu().getItem(0).setIcon(R.mipmap.homeselect);
            String strtext = SharedPrefManager.getInstance(Dasboard_Activity.this).getUser().getRole();

            switch (menuItem.getItemId()) {


                case R.id.bottom_menu_profile:
                    // selectedFragment = new HomeFragment();
                   // menuItem.setIcon(R.mipmap.homeselected);

                    if (strtext.equals("student") || strtext.equals("Student")){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Activity()).commit();
                    }else if (strtext.equals("Parent_intent")){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParentProfile_Fragment()).commit();
                    }else if(strtext.equals("Teacher_intent")){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Teacher_Profile_Fragment()).commit();
                    }else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HubProfile_Fragment()).commit();
                    }

                    //getSupportActionBar().setTitle("Home");
                    break;

                case R.id.bottom_menu_dynamo:
                    // selectedFragment = new HomeFragment();
                    // menuItem.setIcon(R.mipmap.homeselected);
                    menuItem = bottomnavigation.getMenu().getItem(0);
                    menuItem.setEnabled(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DynamoFragment()).commit();
                    //getSupportActionBar().setTitle("Home");
                    break;

                case R.id.bottom_menu_following:
                    // selectedFragment = new HomeFragment();
                    // menuItem.setIcon(R.mipmap.homeselected);
                    menuItem = bottomnavigation.getMenu().getItem(0);
                    menuItem.setEnabled(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FollowingFragment()).commit();
                    //getSupportActionBar().setTitle("Home");
                    break;

                case R.id.bottom_menu_hubs:
                    // selectedFragment = new HomeFragment();
                    // menuItem.setIcon(R.mipmap.homeselected);
                    menuItem = bottomnavigation.getMenu().getItem(0);
                    menuItem.setEnabled(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Hub_UserSelection_Fragment()).commit();
                    //getSupportActionBar().setTitle("Home");
                    break;

                case R.id.bottom_menu_showcase:
                    // selectedFragment = new HomeFragment();
                    // menuItem.setIcon(R.mipmap.homeselected);
                    menuItem = bottomnavigation.getMenu().getItem(0);
                    menuItem.setEnabled(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShowCaseFragment()).commit();
                    //getSupportActionBar().setTitle("Home");
                    break;



            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {

                final androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Dasboard_Activity.this)
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert11 = alertDialog.create();
        alert11.show();
    }
}