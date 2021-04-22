package com.starkwiz.starkwiz.Activities.Starting_Pages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.starkwiz.starkwiz.R;

public class MainActivity extends AppCompatActivity {

    ImageView SpalshImage;
    private Animation animation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullDisplay();
        setContentView(R.layout.activity_main);

        SpalshImage = findViewById(R.id.spalshimg);


        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        SpalshImage.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                animation = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.fade_out);
                SpalshImage.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation arg0) {

                        Intent intent = new Intent(getApplicationContext(),
                                WelcomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }

                    @Override
                    public void onAnimationStart(Animation arg0) {
                    }
                });
            }
        }, 5000);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void FullDisplay() {

        Window window = MainActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}