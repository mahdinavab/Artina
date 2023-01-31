package com.radmanpooya.artina.adapter.education.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radmanpooya.artina.R;
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
        return new LessonAdapter.SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_section_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        try {
            holder.sectionTitle.setText(lessonResponseItems.get(position).getTitle());
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return lessonResponseItems.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder{

        TextView sectionTitle;
        TextView numberLessons;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            sectionTitle = itemView.findViewById(R.id.section_title);
            numberLessons = itemView.findViewById(R.id.number_lessons);


        }
    }

}
