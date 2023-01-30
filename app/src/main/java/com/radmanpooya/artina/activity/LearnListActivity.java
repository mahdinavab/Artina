package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.radmanpooya.artina.adapter.education.category.CategoryAdapter;
import com.radmanpooya.artina.adapter.education.learn.LearnAdapter;
import com.radmanpooya.artina.adapter.market.sort.SortMarketAdapter;
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.education.category.CategoryResponseItem;
import com.radmanpooya.artina.model.education.learn.LearnResponseItem;
import com.radmanpooya.artina.model.market.sort.SortMarketModel;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearnListActivity extends AppCompatActivity {

    int categoryId;
    String categoryTitle;

    RecyclerView learnRecycler;
    LearnAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ConstraintLayout toolbar;
    TextView toolbarTitle;
    CardView backCardView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_list);

        try {
            findViews();
            initialize();
            onClicks();
        }catch (Exception e){

        }

    }

    private void findViews(){
        learnRecycler = findViewById(R.id.learn_recycler);
        toolbar = findViewById(R.id.main_toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        backCardView = toolbar.findViewById(R.id.back_card_view);
    }

    private void initialize(){
        categoryId = getIntent().getIntExtra("category_id",1);
        categoryTitle = getIntent().getStringExtra("category_title");
        toolbarTitle.setText(categoryTitle);
        getLearnList(categoryId+"");
    }

    private void onClicks(){
        backCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getLearnList(String catId){
        Log.i("PPPPP",Link.LEARN_LIST+catId);

        final StringRequest learnReq = new StringRequest(Request.Method.GET, Link.LEARN_LIST+catId, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                Log.i("PPPPP",response);
                try {
                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                    List<LearnResponseItem> learnResponseItems = gson.fromJson(response, new TypeToken<List<LearnResponseItem>>(){}.getType());
                    adapter=new LearnAdapter(LearnListActivity.this,learnResponseItems,categoryId,categoryTitle);
                    learnRecycler.setAdapter(adapter);
                    layoutManager=new LinearLayoutManager(LearnListActivity.this);
                    learnRecycler.setLayoutManager(layoutManager);
                    learnRecycler.setHasFixedSize(true);

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    CookieBar.Builder c = CookieBar.build(LearnListActivity.this);
                    c.setTitle(" لطفا اتصال اینترنت خود را بررسی فرمایید");
                    c.setSwipeToDismiss(true);
                    c.setCookiePosition(CookieBar.BOTTOM); // Cookie will be displayed at the bottom
                    ViewCompat.setLayoutDirection(c.show().getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                }catch (Exception e) {

                }
            }
        }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }

        };

        learnReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Api.getInstance().addToRequestQueue(learnReq, "LEARNLIST");

    }

}