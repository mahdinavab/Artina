package com.radmanpooya.artina.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.adapter.market.sort.SortMarketAdapter;
import com.radmanpooya.artina.model.market.sort.SortMarketModel;

import java.util.ArrayList;
import java.util.List;

public class MarketFragment extends Fragment {

    ConstraintLayout sellerConstraint;
    ConstraintLayout buyerConstraint;
    String selectedTab = "buyer";

    ImageView buyerIcon;
    ImageView cardTabBuyer;
    TextView buyerText;

    ImageView sellerIcon;
    ImageView cardTabSeller;
    TextView sellerText;

    RecyclerView sortMarketRecycler;
    SortMarketAdapter sortMarketAdapter;
    RecyclerView.LayoutManager sortMarketAdapterLayoutManager;

    public MarketFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market, container, false);

        findViews(view);
        onClicks();
        setSortItems();

        return view;
    }

    private void findViews(View view){
        buyerConstraint = view.findViewById(R.id.buyer_constraint);
        sellerConstraint = view.findViewById(R.id.seller_constraint);
        buyerIcon = view.findViewById(R.id.buyer_icon);
        cardTabBuyer = view.findViewById(R.id.card_tab_buyer);
        buyerText = view.findViewById(R.id.buyer_text);
        sellerIcon = view.findViewById(R.id.seller_icon);
        cardTabSeller = view.findViewById(R.id.card_tab_seller);
        sellerText = view.findViewById(R.id.seller_text);
        sortMarketRecycler = view.findViewById(R.id.sort_market_recycler);
    }

    private void onClicks(){
        sellerConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab.equals("seller")){
                    //nothing
                }else {
                    buyerIcon.setImageResource(R.drawable.buyerdisableicon);
                    cardTabBuyer.setImageResource(R.drawable.cardtabdisable);
                    buyerText.setTextColor(Color.parseColor("#FDE8DE"));
                    sellerIcon.setImageResource(R.drawable.sellerenableicon);
                    cardTabSeller.setImageResource(R.drawable.cardtabenable);
                    sellerText.setTextColor(Color.parseColor("#FFFFFF"));
                    selectedTab = "seller";
                }
            }
        });
        buyerConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab.equals("buyer")) {
                    //nothing
                } else {
                    sellerIcon.setImageResource(R.drawable.sellerdisableicon);
                    cardTabSeller.setImageResource(R.drawable.cardtabdisable);
                    sellerText.setTextColor(Color.parseColor("#FDE8DE"));
                    buyerIcon.setImageResource(R.drawable.buyerenableicon);
                    cardTabBuyer.setImageResource(R.drawable.cardtabenable);
                    buyerText.setTextColor(Color.parseColor("#FFFFFF"));
                    selectedTab = "buyer";
                }
            }
        });
    }
    
    private void setSortItems(){
        List<SortMarketModel> sortMarketModelList=new ArrayList<>();
        sortMarketModelList.add(new SortMarketModel(0,"newer",R.drawable.newersorticon,"???????? ????????"));
        sortMarketModelList.add(new SortMarketModel(1,"highestweight",R.drawable.highestweightsorticon,"???????? ???????? ??????"));
        sortMarketModelList.add(new SortMarketModel(2,"lowestweight",R.drawable.lowestweightsorticon,"?????????? ???????? ??????"));

        sortMarketAdapter = new SortMarketAdapter(getActivity(),getActivity().getApplicationContext(),sortMarketModelList);
        sortMarketRecycler.setAdapter(sortMarketAdapter);
        sortMarketAdapterLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,true);
        sortMarketRecycler.setLayoutManager(sortMarketAdapterLayoutManager);
        sortMarketRecycler.setHasFixedSize(true);
    }

}