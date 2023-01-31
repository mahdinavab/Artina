package com.radmanpooya.artina.adapter.market.relatedlearn;

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
import com.radmanpooya.artina.activity.SectionActivity;
import com.radmanpooya.artina.adapter.market.product.ProductAdapter;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.market.product.response.LearnItem;

import java.util.List;

public class RelatedLearnAdapter extends RecyclerView.Adapter<RelatedLearnAdapter.RelatedLearnViewHolder> {

    Context context;
    List<LearnItem> learnItemList;

    public RelatedLearnAdapter(Context context, List<LearnItem> learnItemList) {
        this.context = context;
        this.learnItemList = learnItemList;
    }

    @NonNull
    @Override
    public RelatedLearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RelatedLearnAdapter.RelatedLearnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_related_learn_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedLearnViewHolder holder, @SuppressLint("RecyclerView") int position) {

        try {
            Glide.with(context).load(Link.BASE_URL+learnItemList.get(position).getImage()).into(holder.learnImageView);
            holder.learnTitle.setText(learnItemList.get(position).getTitle());
            holder.learnPrice.setText(learnItemList.get(position).getPrice()+" تومان");
            holder.learnAuthor.setText(learnItemList.get(position).getAuther());
        }catch (Exception e){

        }

        holder.constraintLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SectionActivity.class);
                intent.putExtra("learn_id",learnItemList.get(position).getId());
                intent.putExtra("intent_type","fromMarket");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return learnItemList.size();
    }

    public static class RelatedLearnViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLearn;
        ImageView learnImageView;
        TextView learnTitle;
        TextView learnPrice;
        TextView learnAuthor;

        public RelatedLearnViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLearn = itemView.findViewById(R.id.constraint_learn);
            learnImageView = itemView.findViewById(R.id.learn_image_view);
            learnTitle = itemView.findViewById(R.id.learn_title);
            learnPrice = itemView.findViewById(R.id.learn_price);
            learnAuthor = itemView.findViewById(R.id.learn_author);

        }
    }

}
