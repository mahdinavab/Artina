package com.radmanpooya.artina.adapter.education.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.education.learn.LearnResponseItem;

import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.LearnViewHolder> {

    Context context;
    List<LearnResponseItem> learnResponseItemList;

    public LearnAdapter(Context context, List<LearnResponseItem> learnResponseItemList) {
        this.context = context;
        this.learnResponseItemList = learnResponseItemList;
    }

    @NonNull
    @Override
    public LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearnAdapter.LearnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_learn_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LearnViewHolder holder, int position) {
        try {
            Glide.with(context).load(Link.BASE_URL+learnResponseItemList.get(position).getImage()).into(holder.learnImageView);
            holder.learnTitle.setText(learnResponseItemList.get(position).getTitle());
            holder.learnPrice.setText(learnResponseItemList.get(position).getPrice()+" تومان");
            holder.learnAuther.setText(learnResponseItemList.get(position).getAuther());
        }catch (Exception e){

        }
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

        public LearnViewHolder(@NonNull View itemView) {
            super(itemView);

            learnImageView = itemView.findViewById(R.id.learn_image_view);
            learnTitle = itemView.findViewById(R.id.learn_title);
            learnPrice = itemView.findViewById(R.id.learn_price);
            learnAuther = itemView.findViewById(R.id.learn_auther);


        }
    }

}
