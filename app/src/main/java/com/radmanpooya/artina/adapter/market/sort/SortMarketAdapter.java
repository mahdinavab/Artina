package com.radmanpooya.artina.adapter.market.sort;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SortMarketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SortMarketViewHolder extends RecyclerView.ViewHolder{

        public SortMarketViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
