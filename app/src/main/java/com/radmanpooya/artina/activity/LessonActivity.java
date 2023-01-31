package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.radmanpooya.artina.R;

public class LessonActivity extends AppCompatActivity {

    ImageView dddImageClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        dddImageClick=findViewById(R.id.ddd_image_click);

        dddImageClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LessonActivity.this,MiddleActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}