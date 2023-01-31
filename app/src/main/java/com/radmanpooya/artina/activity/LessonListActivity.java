package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.adapter.education.section.SectionAdapter;

public class LessonListActivity extends AppCompatActivity {


    TextView lessonTitleTextView;
    int lessonId;
    RecyclerView sectionRecycler;
    SectionAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        findViews();
        initialize();

    }

    private void findViews() {

        lessonTitleTextView = findViewById(R.id.lesson_title_text_view);
        sectionRecycler = findViewById(R.id.section_recycler);

    }

    private void initialize() {
        lessonId = getIntent().getIntExtra("lesson_id", 1);
    }

}