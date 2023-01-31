package com.radmanpooya.artina.adapter.market.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.activity.SectionActivity;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.market.product.response.ProductResponseItem;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<ProductResponseItem> productResponseItems;

    public ProductAdapter(Context context, List<ProductResponseItem> productResponseItems) {
        this.context = context;
        this.productResponseItems = productResponseItems;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_product_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            if(productResponseItems.get(position).getImages().size() == 0){
                holder.productImage.setImageResource(R.drawable.noimage);
            }else{
                Log.i("UHYH",Link.BASE_URL_IMAGE+productResponseItems.get(position).getImages().get(position).getImage());
                Glide.with(context).load(Link.BASE_URL_IMAGE+productResponseItems.get(position).getImages().get(position).getImage()).into(holder.productImage);
            }
            holder.productTitle.setText(productResponseItems.get(position).getTitle());
            holder.productCategoryName.setText(productResponseItems.get(position).getCategoryName());
            holder.productPrice.setText("قیمت : "+productResponseItems.get(position).getPrice()+" تومان");
            holder.productTypeText.setText("نوع محصول : "+productResponseItems.get(position).getNameType());
            holder.productWeightText.setText("وزن : "+productResponseItems.get(position).getWeight()+" کیلوگرم");
            holder.daysLeftText.setText((int)(Float.parseFloat(productResponseItems.get(position).getDaysLeft().toString()))+" روز باقی مانده");

        }catch (Exception e){
            Log.i("PPOOO",e.getMessage());
        }

        holder.productItemMainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SectionActivity.class);

                intent.putExtra("product_id",productResponseItems.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productResponseItems.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productTitle;
        TextView productCategoryName;
        TextView productPrice;
        TextView productTypeText;
        TextView productWeightText;
        TextView daysLeftText;
        CardView productItemMainCard;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productCategoryName = itemView.findViewById(R.id.product_category_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productTypeText = itemView.findViewById(R.id.product_type_text);
            productWeightText = itemView.findViewById(R.id.product_weight_text);
            daysLeftText = itemView.findViewById(R.id.days_left_text);
            productItemMainCard = itemView.findViewById(R.id.product_item_main_card);

        }
    }

}
