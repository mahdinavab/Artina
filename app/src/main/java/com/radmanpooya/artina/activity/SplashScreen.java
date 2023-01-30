package com.radmanpooya.artina.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.util.UserInfoManager;

public class SplashScreen extends AppCompatActivity {

    TextView artinText;
    ImageView companyImage;
    UserInfoManager userInfoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SplashScreen.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // edited here
            SplashScreen.this.getWindow().setStatusBarColor(Color.parseColor("#F2664F"));
        }

        userInfoManager=new UserInfoManager(SplashScreen.this);

        artinText=findViewById(R.id.artin_text);

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        artinText.startAnimation(animFadeIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (userInfoManager.getAccessToken().equals("")) {
                    Intent i = new Intent(SplashScreen.this,SendOtpActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }else{
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                finish();


            }
        }, 3000);


    }
}