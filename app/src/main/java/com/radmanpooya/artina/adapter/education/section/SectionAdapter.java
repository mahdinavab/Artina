package com.radmanpooya.artina.adapter.education.section;

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
import com.radmanpooya.artina.activity.LessonListActivity;
import com.radmanpooya.artina.activity.SectionActivity;
import com.radmanpooya.artina.adapter.education.category.CategoryAdapter;
import com.radmanpooya.artina.model.education.section.response.SectionResponseItem;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    Context context;
    List<SectionResponseItem> sectionResponseItems;

    public SectionAdapter(Context context, List<SectionResponseItem> sectionResponseItems) {
        this.context = context;
        this.sectionResponseItems = sectionResponseItems;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SectionAdapter.SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_section_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            holder.sectionTitle.setText(sectionResponseItems.get(position).getTitle());
            holder.numberLessons.setText(sectionResponseItems.get(position).getNumLesson()+" بخش");
        }catch (Exception e){


        }

        holder.sectionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LessonListActivity.class);
                intent.putExtra("lesson_id",sectionResponseItems.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionResponseItems.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder{

        TextView sectionTitle;
        TextView numberLessons;
        ConstraintLayout sectionLayout;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            sectionTitle = itemView.findViewById(R.id.section_title);
            numberLessons = itemView.findViewById(R.id.number_lessons);
            sectionLayout = itemView.findViewById(R.id.section_layout);


        }
    }

}
