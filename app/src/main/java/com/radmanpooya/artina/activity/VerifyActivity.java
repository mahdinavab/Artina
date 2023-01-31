package com.radmanpooya.artina.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.user.response.sendotp.SendOtpResponse;
import com.radmanpooya.artina.model.user.response.verify.VerifyCodeResponse;
import com.radmanpooya.artina.model.user.send.sendotp.SendOtpRequestModel;
import com.radmanpooya.artina.model.user.send.verify.VerifyCodeRequestModel;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;
import com.radmanpooya.artina.util.UserInfoManager;

import org.aviran.cookiebar2.CookieBar;

import java.util.HashMap;
import java.util.Map;

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
    String mobilePhone;
    TextView mobileV;
    TextView editMobileText;
    String inputCode;

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
        mobileV=findViewById(R.id.mobile_v);
        editMobileText=findViewById(R.id.edit_mobile_text);

    }

    private void listeners(){
        loginToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }catch (Exception e){

                }

                if(regNumber1.getText().toString().matches("") || regNumber2.getText().toString().matches("") || regNumber3.getText().toString().matches("") || regNumber4.getText().toString().matches("")){
                    CookieBar.Builder c = CookieBar.build(VerifyActivity.this);
                    c.setTitle(" لطفا کد را کامل وارد نمایید");
                    c.setSwipeToDismiss(true);
                    c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                    ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                }else{

                    inputCode=regNumber1.getText().toString()+regNumber2.getText().toString()+regNumber3.getText().toString()+regNumber4.getText().toString();
                    verifyCode(inputCode,mobilePhone);
                }
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

                        inputCode=regNumber1.getText().toString()+regNumber2.getText().toString()+regNumber3.getText().toString()+regNumber4.getText().toString();
                        verifyCode(inputCode,mobilePhone);
                    }

                }else if(regNumber4.getText().toString().length()==0){
                    regNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        timerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageAgain(mobilePhone);
                setUpTimer();
            }
        });


    }

    private void setUpTimer(){
        timerText.setTextColor(Color.parseColor("#456173"));
        timerCardView.setClickable(true);
        timerCardView.setCardBackgroundColor(Color.parseColor("#F5F5F5"));
        timerCardView.startAnimation(zoomInAnimation);
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
        mobilePhone = getIntent().getStringExtra("mobile");
        mobileV.setText(mobilePhone);
        editMobileText.setText(mobilePhone);
        zoomInAnimation = AnimationUtils.loadAnimation(VerifyActivity.this, R.anim.zoom_in_two);
    }

    public void sendMessageAgain(final String mobile){

        final StringRequest sendMsgReq = new StringRequest(Request.Method.POST, Link.SEND_OTP, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                SendOtpResponse sendOtpResponse = gson.fromJson(response, SendOtpResponse.class);

                Log.i("AADD",sendOtpResponse.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("OKKKKK",error.getMessage());
                CookieBar.Builder c = CookieBar.build(VerifyActivity.this);
                c.setTitle(" لطفا اتصال اینترنت خود را بررسی فرمایید");
                c.setSwipeToDismiss(true);
                c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);

            }
        }
        ) {
            public byte[] getBody() throws AuthFailureError {

                SendOtpRequestModel sendOtpRequestModel = new SendOtpRequestModel();
                sendOtpRequestModel.setMobile(mobile);
                String json = new Gson().toJson(sendOtpRequestModel);

                return json.getBytes();

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }

        };

        sendMsgReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Api.getInstance().addToRequestQueue(sendMsgReq, "SENDOTP");

    }

    public void verifyCode(final String code, final String mobileNumber) {
        final StringRequest verifyCodeReq = new StringRequest(Request.Method.POST, Link.VERIFY_CODE, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                VerifyCodeResponse verifyCodeResponse = gson.fromJson(response, VerifyCodeResponse.class);
                if(verifyCodeResponse.getStatus().equals("ok")){
                    UserInfoManager userInfoManager = new UserInfoManager(VerifyActivity.this);
                    userInfoManager.setRefreshToken(verifyCodeResponse.getRefreshToken());
                    userInfoManager.setAccessToken(verifyCodeResponse.getAccessToken());
                    Intent intent = new Intent(VerifyActivity.this,IntroAppActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    CookieBar.Builder c = CookieBar.build(VerifyActivity.this);
                    c.setTitle(" لطفا درستی کد تایید خود را بررسی فرمایید");
                    c.setSwipeToDismiss(true);
                    c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                    ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                }
                Log.i("AADD","VERIFY ::::: "+verifyCodeResponse.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CookieBar.Builder c = CookieBar.build(VerifyActivity.this);
                c.setTitle(" لطفا درستی کد تایید و یا اتصال اینترنت خود را بررسی فرمایید");
                c.setSwipeToDismiss(true);
                c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
            }
        }
        ) {
            public byte[] getBody() throws AuthFailureError {

                VerifyCodeRequestModel verifyCodeRequestModel = new VerifyCodeRequestModel();
                verifyCodeRequestModel.setMobile(mobileNumber);
                verifyCodeRequestModel.setCode(code);
                String json = new Gson().toJson(verifyCodeRequestModel);

                return json.getBytes();

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }
        };

        verifyCodeReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Api.getInstance().addToRequestQueue(verifyCodeReq, "CONFIRMCODEREQ");

    }

}