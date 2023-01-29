package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.radmanpooya.artina.R;

public class VerifyActivity extends AppCompatActivity {

    CardView loginToApp;
    CountDownTimer countDownTimer;
    TextView timerText;
    CardView timerCardView;
    Animation zoomInAnimation;
    CardView editMobileCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        findViews();
        initialize();
        onClicks();
        setUpTimer();
    }

    private void findViews(){
        loginToApp = findViewById(R.id.login_to_app);
        timerText = findViewById(R.id.timer_text);
        timerCardView = findViewById(R.id.timer_card_view);
        editMobileCardView = findViewById(R.id.edit_mobile_card_view);

    }

    private void onClicks(){
        loginToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerifyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        editMobileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerifyActivity.this,SendOtpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUpTimer(){
        countDownTimer=new CountDownTimer(90000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText((millisUntilFinished / 60000)+":"+(millisUntilFinished % 60000 / 1000));
                if(millisUntilFinished<1000){
                    setSendAgainLayout();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void setSendAgainLayout(){
        timerText.setText("ارسال مجدد");
        timerText.setTextColor(Color.parseColor("#FFFFFF"));
        timerCardView.setClickable(true);
        timerCardView.setCardBackgroundColor(Color.parseColor("#456173"));
        timerCardView.startAnimation(zoomInAnimation);
    }

    private void initialize(){
       zoomInAnimation = AnimationUtils.loadAnimation(VerifyActivity.this, R.anim.zoom_in_two);
    }

}