package com.radmanpooya.artina.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.adapter.market.product.ProductAdapter;
import com.radmanpooya.artina.adapter.market.sort.SortMarketAdapter;
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.market.product.response.ProductResponseItem;
import com.radmanpooya.artina.model.market.product.send.ProductRequestModel;
import com.radmanpooya.artina.model.market.sort.SortMarketModel;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    RecyclerView productRecycler;
    ProductAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    SortMarketAdapter.IOnClickSort iOnClickSort;

    int idSelected;

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
        initialize();
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
        productRecycler = view.findViewById(R.id.product_recycler);

    }

    private void initialize(){
        getProducts("newest","buy");
        idSelected = -1;
        iOnClickSort = new SortMarketAdapter.IOnClickSort() {
            @Override
            public void onClickSort(int id) {
                idSelected =id;
            }
        };
    }

    public void getProducts(final String sortBy, final String type) {
        final StringRequest productReq = new StringRequest(Request.Method.POST, Link.GET_PRODUCTS, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                Log.i("POIU",response);

                try {

                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                    List<ProductResponseItem> productResponseItems = gson.fromJson(response, new TypeToken<List<ProductResponseItem>>(){}.getType());
                    Log.i("POIU","toooo "+productResponseItems.toString());
                    adapter=new ProductAdapter(getActivity().getApplicationContext(),productResponseItems);
                    productRecycler.setAdapter(adapter);
                    layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
                    productRecycler.setLayoutManager(layoutManager);
                    productRecycler.setHasFixedSize(true);

                }catch (Exception e){
                    Log.i("POIU",e.getMessage()+"  "+ e.getCause());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {

                    CookieBar.Builder c = CookieBar.build(getActivity());
                    c.setTitle(" خطا در اتصال به سرور");
                    c.setSwipeToDismiss(true);
                    c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                    ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                }catch (Exception e){
                    Log.i("POIU","errrrrrrrrro "+e.getMessage()+"  "+ e.getCause());
                }
            }
        }
        ) {
            public byte[] getBody() throws AuthFailureError {

                ProductRequestModel productRequestModel = new ProductRequestModel();
                productRequestModel.setSortby(sortBy);
                productRequestModel.setType(type);
                String json = new Gson().toJson(productRequestModel);

                return json.getBytes();

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }
        };

        productReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Api.getInstance().addToRequestQueue(productReq, "CONFIRMCODEREQ");

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
                    getProducts("newest","sell");
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
                    getProducts("newest","buy");
                }
            }
        });
    }
    
    private void setSortItems(){
        List<SortMarketModel> sortMarketModelList=new ArrayList<>();
        sortMarketModelList.add(new SortMarketModel(0,"newer",R.drawable.newersorticon,"جدید ترین"));
        sortMarketModelList.add(new SortMarketModel(1,"highestweight",R.drawable.highestweightsorticon,"بالا ترین وزن"));
        sortMarketModelList.add(new SortMarketModel(2,"lowestweight",R.drawable.lowestweightsorticon,"پایین ترین وزن"));

        sortMarketAdapter = new SortMarketAdapter(getActivity(),getActivity().getApplicationContext(),sortMarketModelList,iOnClickSort,idSelected);
        sortMarketRecycler.setAdapter(sortMarketAdapter);
        sortMarketAdapterLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,true);
        sortMarketRecycler.setLayoutManager(sortMarketAdapterLayoutManager);
        sortMarketRecycler.setHasFixedSize(true);
    }

}