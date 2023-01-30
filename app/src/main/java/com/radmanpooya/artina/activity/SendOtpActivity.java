package com.radmanpooya.artina.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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
import com.radmanpooya.artina.model.user.send.sendotp.SendOtpRequestModel;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.HashMap;
import java.util.Map;

public class SendOtpActivity extends AppCompatActivity {

    CardView sendCode;
    EditText mobileEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        findViews();
        listeners();

    }

    private void findViews(){
        sendCode = findViewById(R.id.send_code);
        mobileEditText = findViewById(R.id.mobile_edit_text);

    }

    private void listeners(){
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputMobile=mobileEditText.getText().toString();
                String regexStr = "^[0-9]*$";
                if (inputMobile.startsWith("0") && inputMobile.matches(regexStr) && inputMobile.length()==11){
                    Intent intent = new Intent(SendOtpActivity.this,VerifyActivity.class);
                    intent.putExtra("mobile",inputMobile);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();

                    sendMessage(inputMobile);

                }else{
                    CookieBar.Builder c = CookieBar.build(SendOtpActivity.this);
                    c.setTitle("لطفا درستی شماره موبایل خود را بررسی نمایید");
                    c.setSwipeToDismiss(true);
                    c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                    ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                }


            }
        });

        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mobileEditText.getText().toString().length()==11){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void sendMessage(final String mobile){

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
                CookieBar.Builder c = CookieBar.build(SendOtpActivity.this);
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

}