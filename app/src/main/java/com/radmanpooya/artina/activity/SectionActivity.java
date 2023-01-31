package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.radmanpooya.artina.R;
import com.radmanpooya.artina.adapter.education.learn.LearnAdapter;
import com.radmanpooya.artina.adapter.education.section.SectionAdapter;
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.education.learn.LearnResponseItem;
import com.radmanpooya.artina.model.education.section.response.SectionResponseItem;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionActivity extends AppCompatActivity {

    ImageView learnImageView;
    TextView learnTitleTextView;
    TextView sectionNumber;
    TextView authorText;
    TextView priceText;
    CardView backCardView;
    int learnId;
    int categoryId;
    String categoryTitle;
    RecyclerView sectionRecycler;
    SectionAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        findViews();
        initialize();
        onClicks();
        

    }

    private void findViews(){
        learnImageView = findViewById(R.id.learn_image_view);
        learnTitleTextView = findViewById(R.id.learn_title_text_view);
        sectionNumber = findViewById(R.id.section_number);
        authorText = findViewById(R.id.author_text);
        priceText = findViewById(R.id.price_text);
        backCardView = findViewById(R.id.back_card_view);
        sectionRecycler = findViewById(R.id.section_recycler);

    }

    private void initialize(){
        learnId = getIntent().getIntExtra("learn_id",1);

        if(getIntent().getStringExtra("intent_type").equals("fromMarket")){

        }else{
            categoryId = getIntent().getIntExtra("category_id",1);
            categoryTitle = getIntent().getStringExtra("category_title");
        }

        getSectionData(learnId+"");
    }

    private void onClicks(){
        backCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionActivity.this,LearnListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getSectionData(String learnId){

        final StringRequest sectionReq = new StringRequest(Request.Method.GET, Link.LSECTION_LIST+learnId, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                Log.i("PPPPP",response);
                try {
                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                    List<SectionResponseItem> sectionResponseItems = gson.fromJson(response, new TypeToken<List<SectionResponseItem>>(){}.getType());
                    adapter=new SectionAdapter(SectionActivity.this,sectionResponseItems);
                    sectionRecycler.setAdapter(adapter);
                    layoutManager=new LinearLayoutManager(SectionActivity.this);
                    sectionRecycler.setLayoutManager(layoutManager);
                    sectionRecycler.setHasFixedSize(true);

                    Glide.with(SectionActivity.this).load(Link.BASE_URL+sectionResponseItems.get(0).getLearn().getImage()).into(learnImageView);
                    learnTitleTextView.setText(sectionResponseItems.get(0).getLearn().getTitle());
                    sectionNumber.setText(sectionResponseItems.get(0).getLearn().getNumSection()+"");
                    authorText.setText(sectionResponseItems.get(0).getLearn().getAuther());
                    priceText.setText(sectionResponseItems.get(0).getLearn().getPrice()+" تومان");

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    CookieBar.Builder c = CookieBar.build(SectionActivity.this);
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

        sectionReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Api.getInstance().addToRequestQueue(sectionReq, "LEARNLIST");

    }


}