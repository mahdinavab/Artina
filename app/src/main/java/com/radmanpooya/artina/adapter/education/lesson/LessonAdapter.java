package com.radmanpooya.artina.adapter.education.lesson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.activity.LessonActivity;
import com.radmanpooya.artina.activity.LessonListActivity;
import com.radmanpooya.artina.model.education.section.response.SectionResponseItem;
import com.radmanpooya.artina.model.lesson.LessonListResponseItem;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.SectionViewHolder> {

    Context context;
    List<LessonListResponseItem> lessonResponseItems;

    public LessonAdapter(Context context, List<LessonListResponseItem> lessonResponseItems) {
        this.context = context;
        this.lessonResponseItems = lessonResponseItems;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LessonAdapter.SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_lesson_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            holder.sectionTitle.setText(lessonResponseItems.get(position).getTitle());
        }catch (Exception e){

        }
        holder.lessonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LessonActivity.class);
                intent.putExtra("lesson_id",lessonResponseItems.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonResponseItems.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout lessonLayout;
        TextView sectionTitle;
        TextView numberLessons;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            lessonLayout = itemView.findViewById(R.id.lesson_layout);
            sectionTitle = itemView.findViewById(R.id.section_title);
            numberLessons = itemView.findViewById(R.id.number_lessons);


        }
    }

}
