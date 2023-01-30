package com.radmanpooya.artina.adapter.education.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.transition.CircularPropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.activity.LearnListActivity;
import com.radmanpooya.artina.model.education.category.CategoryResponseItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    Context context;
    List<CategoryResponseItem> categoryResponseList;
    OnClickCategoryMain onClickCategoryMain;

    public CategoryAdapter(Context context, List<CategoryResponseItem> categoryResponseList, OnClickCategoryMain onClickCategoryMain) {
        this.context = context;
        this.categoryResponseList = categoryResponseList;
        this.onClickCategoryMain = onClickCategoryMain;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_education_category, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(position % 2 == 0){
            holder.leftConstraintLayout.setVisibility(View.GONE);
            holder.rightConstraintLayout.setVisibility(View.VISIBLE);
            holder.assessment1Progress.setProgressWithAnimation(70f,1100l);
            holder.assessment2Progress.setProgressWithAnimation(34f,1100l);
            holder.categoryTitleRight.setText(categoryResponseList.get(position).getTitle());
            holder.numberOfLearnsRight.setText(categoryResponseList.get(position).getNumLearn() + " آموزش");
        }else{
            holder.leftConstraintLayout.setVisibility(View.VISIBLE);
            holder.rightConstraintLayout.setVisibility(View.GONE);
            holder.assessment1Progress2.setProgressWithAnimation(70f,1100l);
            holder.assessment2Progress2.setProgressWithAnimation(34f,1100l);
            holder.categoryTitleLeft.setText(categoryResponseList.get(position).getTitle());
            holder.numberOfLearnsLeft.setText(categoryResponseList.get(position).getNumLearn()+ " آموزش");
        }

        holder.leftConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToSecCat=new Intent(context, LearnListActivity.class);
                intentToSecCat.putExtra("category_id",categoryResponseList.get(position).getId());
                intentToSecCat.putExtra("category_title",categoryResponseList.get(position).getTitle());
                intentToSecCat.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentToSecCat);

            }
        });
        holder.rightConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToSecCat = new Intent(context, LearnListActivity.class);
                intentToSecCat.putExtra("category_id", categoryResponseList.get(position).getId());
                intentToSecCat.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentToSecCat);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryResponseList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout leftConstraintLayout;
        ConstraintLayout rightConstraintLayout;
        TextView categoryTitleLeft;
        TextView numberOfLearnsLeft;
        TextView categoryTitleRight;
        TextView numberOfLearnsRight;
        CircularProgressBar assessment1Progress;
        CircularProgressBar assessment2Progress;
        CircularProgressBar assessment1Progress2;
        CircularProgressBar assessment2Progress2;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            leftConstraintLayout = itemView.findViewById(R.id.left_constraint_layout);
            rightConstraintLayout = itemView.findViewById(R.id.right_constraint_layout);
            categoryTitleLeft = itemView.findViewById(R.id.category_title_left);
            numberOfLearnsLeft = itemView.findViewById(R.id.number_of_learns_left);
            categoryTitleRight = itemView.findViewById(R.id.category_title_right);
            numberOfLearnsRight = itemView.findViewById(R.id.number_of_learns_right);
            assessment1Progress = itemView.findViewById(R.id.assessment1_progress);
            assessment2Progress = itemView.findViewById(R.id.assessment2_progress);
            assessment1Progress2 = itemView.findViewById(R.id.assessment1_progress2);
            assessment2Progress2 = itemView.findViewById(R.id.assessment2_progress2);

        }
    }

    public interface OnClickCategoryMain {
        void onClickCategory();
    }

}
