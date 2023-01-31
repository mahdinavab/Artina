package com.radmanpooya.artina.adapter.market.sort;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.model.market.sort.SortMarketModel;

import java.util.List;

public class SortMarketAdapter extends RecyclerView.Adapter<SortMarketAdapter.SortMarketViewHolder> {

    Activity activity;
    Context context;
    List<SortMarketModel> sortMarketModelList;
    IOnClickSort iOnClickSort;
    int selectedId;

    public SortMarketAdapter(Activity activity, Context context, List<SortMarketModel> sortMarketModelList, IOnClickSort iOnClickSort, int selectedId) {
        this.activity = activity;
        this.context = context;
        this.sortMarketModelList = sortMarketModelList;
        this.iOnClickSort = iOnClickSort;
        this.selectedId = selectedId;
    }

    @NonNull
    @Override
    public SortMarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortMarketAdapter.SortMarketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_sort_market_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SortMarketViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.iconResource.setImageResource(sortMarketModelList.get(position).getIconResource());
        holder.title.setText(sortMarketModelList.get(position).getTitle());

        holder.sortMarketCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.sortMarketCardView.setCardBackgroundColor(Color.parseColor("#FFF3F3"));
                holder.title.setTextColor(Color.parseColor("#F46953"));
                iOnClickSort.onClickSort(sortMarketModelList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return sortMarketModelList.size();
    }

    public static class SortMarketViewHolder extends RecyclerView.ViewHolder{

        ImageView iconResource;
        TextView title;
        CardView sortMarketCardView;

        public SortMarketViewHolder(@NonNull View itemView) {
            super(itemView);
            iconResource = itemView.findViewById(R.id.icon_resource);
            title = itemView.findViewById(R.id.title);
            sortMarketCardView = itemView.findViewById(R.id.sort_market_card_view);


        }
    }

    public interface IOnClickSort {
        void onClickSort(int id);
    }

}
