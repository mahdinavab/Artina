package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.radmanpooya.artina.R;

public class VerifyActivity extends AppCompatActivity {

    CardView loginToApp;
    CountDownTimer countDownTimer;
    TextView timerText;
    CardView timerCardView;
    Animation zoomInAnimation;
    CardView editMobileCardView;
    EditText regNumber1;
    EditText regNumber2;
    EditText regNumber3;
    EditText regNumber4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        findViews();
        initialize();
        listeners();
        setUpTimer();
    }

    private void findViews(){
        loginToApp = findViewById(R.id.login_to_app);
        timerText = findViewById(R.id.timer_text);
        timerCardView = findViewById(R.id.timer_card_view);
        editMobileCardView = findViewById(R.id.edit_mobile_card_view);
        regNumber1=findViewById(R.id.reg_number1);
        regNumber2=findViewById(R.id.reg_number2);
        regNumber3=findViewById(R.id.reg_number3);
        regNumber4=findViewById(R.id.reg_number4);

    }

    private void listeners(){
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

        regNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(regNumber1.getText().toString().length()==1){
                    regNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        regNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(regNumber2.getText().toString().length()==1){
                    regNumber3.requestFocus();
                }else if(regNumber2.getText().toString().length()==0){
                    regNumber1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        regNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(regNumber3.getText().toString().length()==1){
                    regNumber4.requestFocus();
                }else if(regNumber3.getText().toString().length()==0){
                    regNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        regNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(regNumber4.getText().toString().length()==1){

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                    if(regNumber1.getText().toString().matches("") || regNumber2.getText().toString().matches("") || regNumber3.getText().toString().matches("") || regNumber4.getText().toString().matches("")){

                    }else{
                        /*inputCode=regNumber1.getText().toString()+regNumber2.getText().toString()+regNumber3.getText().toString()+regNumber4.getText().toString();
                        loginToKavinooTextView.setVisibility(View.INVISIBLE);
                        progressBarLogin.setVisibility(View.VISIBLE);
                        sendConfirm(inputCode,mobile);*/
                    }

                }else if(regNumber4.getText().toString().length()==0){
                    regNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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