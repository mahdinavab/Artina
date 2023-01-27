package com.radmanpooya.artina.adapter.market.sort;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.model.market.sort.SortMarketModel;

import java.util.List;

public class SortMarketAdapter extends RecyclerView.Adapter<SortMarketAdapter.SortMarketViewHolder> {

    Activity activity;
    Context context;
    List<SortMarketModel> sortMarketModelList;

    public SortMarketAdapter(Activity activity, Context context, List<SortMarketModel> sortMarketModelList) {
        this.activity = activity;
        this.context = context;
        this.sortMarketModelList = sortMarketModelList;
    }

    @NonNull
    @Override
    public SortMarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortMarketAdapter.SortMarketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_sort_market_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SortMarketViewHolder holder, int position) {
        holder.iconResource.setImageResource(sortMarketModelList.get(position).getIconResource());
        holder.title.setText(sortMarketModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return sortMarketModelList.size();
    }

    public static class SortMarketViewHolder extends RecyclerView.ViewHolder{

        ImageView iconResource;
        TextView title;
        public SortMarketViewHolder(@NonNull View itemView) {
            super(itemView);
            iconResource = itemView.findViewById(R.id.icon_resource);
            title = itemView.findViewById(R.id.title);


        }
    }

}
