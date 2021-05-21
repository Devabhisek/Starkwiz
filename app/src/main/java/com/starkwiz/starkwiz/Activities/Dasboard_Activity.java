package com.starkwiz.starkwiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
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

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

public class Dasboard_Activity extends AppCompatActivity {

    BottomNavigationView bottomnavigation;
    ImageView dash_setting,img_notification;
    String Student_intent,Parent_intent,Teacher_intent,Hub_intent;
    private TourGuide mTourGuideHandler;
    private boolean isBackFromB;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard_);

        try {
            role = SharedPrefManager.getInstance(Dasboard_Activity.this).getUser().getRole();
        }catch (Exception e){
            e.printStackTrace();
        }
        isBackFromB=false;
        bottomnavigation = findViewById(R.id.bottomnavigation);
        dash_setting = findViewById(R.id.dash_setting);
        img_notification = findViewById(R.id.img_notification);

        bottomnavigation.setOnNavigationItemSelectedListener(navListner);
        bottomnavigation.setSelectedItemId(R.id.bottom_menu_showcase);

        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dasboard_Activity.this,Notification_Activity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

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
                    if (strtext==null){
                        strtext = role;
                    }

                        if (strtext.equals("student") || strtext.equals("Student")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Activity()).commit();
                        } else if (strtext.equals("parent")||strtext.equals("Parent")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParentProfile_Fragment()).commit();
                        } else if (strtext.equals("Teacher") || strtext.equals("teacher")) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Teacher_Profile_Fragment()).commit();
                        } else {
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