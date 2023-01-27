package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.radmanpooya.artina.R;

public class SendOtpActivity extends AppCompatActivity {

    CardView sendCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        findViews();
        onClicks();
    }

    private void findViews(){
        sendCode = findViewById(R.id.send_code);

    }

    private void onClicks(){
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendOtpActivity.this,VerifyActivity.class);
                startActivity(intent);
            }
        });
    }
}