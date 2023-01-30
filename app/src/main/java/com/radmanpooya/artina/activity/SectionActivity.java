package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.radmanpooya.artina.R;

public class SectionActivity extends AppCompatActivity {

    TextView learnTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        findViews();

    }

    private void findViews(){
        learnTitleTextView = findViewById(R.id.learn_title_text_view);


    }

}