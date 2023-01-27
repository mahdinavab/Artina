package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.radmanpooya.artina.R;

public class VerifyActivity extends AppCompatActivity {

    CardView loginToApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        findViews();
        onClicks();

    }

    private void findViews(){
        loginToApp = findViewById(R.id.login_to_app);

    }

    private void onClicks(){
        loginToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerifyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}