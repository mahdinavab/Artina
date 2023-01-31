package com.radmanpooya.artina.adapter.education.section;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radmanpooya.artina.R;
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
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        try {
            holder.sectionTitle.setText(sectionResponseItems.get(position).getTitle());
            holder.numberLessons.setText(sectionResponseItems.get(position).getNumLesson()+" بخش");
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return sectionResponseItems.size();
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