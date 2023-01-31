package com.radmanpooya.artina.adapter.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.model.intro.IntroItem;

import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.IntroViewPagerViewHolder> {

    private List<IntroItem> introItemArrayList;
    private ViewPager2 viewPagerIntro;
    private Context context;

    public IntroViewPagerAdapter(List<IntroItem> introItemArrayList, ViewPager2 viewPagerIntro, Context context) {
        this.introItemArrayList = introItemArrayList;
        this.viewPagerIntro = viewPagerIntro;
        this.context = context;
    }

    @NonNull
    @Override
    public IntroViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IntroViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.intro_view_pager_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewPagerViewHolder holder, int position) {

        holder.introImage.setImageResource(introItemArrayList.get(position).getIntroImage());
        holder.primaryText.setText(introItemArrayList.get(position).getPrimaryText());
        holder.secondaryText.setText(introItemArrayList.get(position).getSecondaryText());

    }

    @Override
    public int getItemCount() {
        return introItemArrayList.size();
    }

    public static class IntroViewPagerViewHolder extends RecyclerView.ViewHolder{

        ImageView introImage;
        TextView primaryText;
        TextView secondaryText;

        public IntroViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);

            introImage = itemView.findViewById(R.id.intro_image);
            primaryText = itemView.findViewById(R.id.primary_text);
            secondaryText = itemView.findViewById(R.id.secondary_text);

        }
    }

}
