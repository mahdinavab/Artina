package com.radmanpooya.artina.adapter.education.learn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.activity.LearnListActivity;
import com.radmanpooya.artina.activity.SectionActivity;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.education.learn.LearnResponseItem;

import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.LearnViewHolder> {

    Context context;
    List<LearnResponseItem> learnResponseItemList;
    int categoryId;
    String categoryTitle;

    public LearnAdapter(Context context, List<LearnResponseItem> learnResponseItemList, int categoryId, String categoryTitle) {
        this.context = context;
        this.learnResponseItemList = learnResponseItemList;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    @NonNull
    @Override
    public LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearnAdapter.LearnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_learn_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LearnViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            Glide.with(context).load(Link.BASE_URL+learnResponseItemList.get(position).getImage()).into(holder.learnImageView);
            holder.learnTitle.setText(learnResponseItemList.get(position).getTitle());
            holder.learnPrice.setText(learnResponseItemList.get(position).getPrice()+" تومان");
            holder.learnAuther.setText(learnResponseItemList.get(position).getAuther());
        }catch (Exception e){

        }
        holder.mainLearnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SectionActivity.class);

                intent.putExtra("learn_id",learnResponseItemList.get(position).getId());
                intent.putExtra("category_id",categoryId);
                intent.putExtra("category_title",categoryTitle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return learnResponseItemList.size();
    }

    public static class LearnViewHolder extends RecyclerView.ViewHolder{


        ImageView learnImageView;
        TextView learnTitle;
        TextView learnPrice;
        TextView learnAuther;
        ConstraintLayout mainLearnItem;

        public LearnViewHolder(@NonNull View itemView) {
            super(itemView);

            learnImageView = itemView.findViewById(R.id.learn_image_view);
            learnTitle = itemView.findViewById(R.id.learn_title);
            learnPrice = itemView.findViewById(R.id.learn_price);
            learnAuther = itemView.findViewById(R.id.learn_auther);
            mainLearnItem = itemView.findViewById(R.id.main_learn_item);
            
        }
    }

}
