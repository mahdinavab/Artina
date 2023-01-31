package com.radmanpooya.artina.adapter.market.attributes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.adapter.market.product.ProductAdapter;
import com.radmanpooya.artina.model.market.product.response.AttrValueItem;
import com.radmanpooya.artina.model.market.product.response.ProductResponseItem;

import java.util.List;

public class AttributeAdapter extends RecyclerView.Adapter<AttributeAdapter.AttributeViewHolder> {

    Context context;
    List<AttrValueItem> attrValueItems;

    public AttributeAdapter(Context context, List<AttrValueItem> attrValueItems) {
        this.context = context;
        this.attrValueItems = attrValueItems;
    }

    @NonNull
    @Override
    public AttributeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttributeAdapter.AttributeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_attribute_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AttributeViewHolder holder, int position) {

        holder.keyTextView.setText(attrValueItems.get(position).getKey());
        holder.valueTextView.setText(attrValueItems.get(position).getValue());

    }

    @Override
    public int getItemCount() {
        return attrValueItems.size();
    }

    public static class AttributeViewHolder extends RecyclerView.ViewHolder{

        TextView keyTextView;
        TextView valueTextView;

        public AttributeViewHolder(@NonNull View itemView) {
            super(itemView);

            keyTextView = itemView.findViewById(R.id.key_text_view);
            valueTextView = itemView.findViewById(R.id.value_text_view);

        }
    }

}
