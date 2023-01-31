package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.radmanpooya.artina.adapter.market.attributes.AttributeAdapter;
import com.radmanpooya.artina.adapter.market.product.ProductAdapter;
import com.radmanpooya.artina.adapter.market.relatedlearn.RelatedLearnAdapter;
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.market.product.response.ProductDetailsResponse;
import com.radmanpooya.artina.model.market.product.response.ProductResponseItem;
import com.radmanpooya.artina.model.market.product.send.ProductDetailsRequestModel;
import com.radmanpooya.artina.model.market.product.send.ProductRequestModel;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    Animation fadeOut;
    TextView titleTextView;
    TextView categoryTitle;
    ImageView productImageView;
    TextView daysLeftText;
    TextView productPrice;
    TextView brandName;
    TextView productWeight;
    TextView descriptionTextView;
    
    int productId;
    RecyclerView attributeRecycler;
    AttributeAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView relatedLearnsRecycler;
    RelatedLearnAdapter relatedLearnsAdapter;
    RecyclerView.LayoutManager relatedLearnsLayoutManager;

    ConstraintLayout loading;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        
        findViews();
        initialize();
    }
    
    private void findViews(){
        titleTextView = findViewById(R.id.title_text_view);
        categoryTitle = findViewById(R.id.category_title);
        productImageView = findViewById(R.id.product_image_view);
        daysLeftText = findViewById(R.id.days_left_text);
        productPrice = findViewById(R.id.product_price);
        brandName = findViewById(R.id.brand_name);
        productWeight = findViewById(R.id.product_weight);
        descriptionTextView = findViewById(R.id.description_text_view);
        attributeRecycler = findViewById(R.id.attribute_recycler);
        relatedLearnsRecycler = findViewById(R.id.related_learns_recycler);
        loading = findViewById(R.id.loading);
    }

    private void initialize(){
        fadeOut = AnimationUtils.loadAnimation(ProductActivity.this, R.anim.fade_out);
        productId = getIntent().getIntExtra("product_id",1);
        getProductDetails(productId);

    }

    public void getProductDetails(final int productId) {
        final StringRequest productReq = new StringRequest(Request.Method.POST, Link.PRODUCT_DETAILS, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                try {
                    loading.setVisibility(View.INVISIBLE);
                    loading.startAnimation(fadeOut);

                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                    ProductDetailsResponse productDetailsResponse = gson.fromJson(response,ProductDetailsResponse.class);

                    titleTextView.setText(productDetailsResponse.getTitle());
                    categoryTitle.setText(productDetailsResponse.getCategoryName());
                    daysLeftText.setText((int)(Float.parseFloat(productDetailsResponse.getDaysLeft().toString()))+" روز باقی مانده");
                    productPrice.setText(productDetailsResponse.getPrice()+" تومان");
                    brandName.setText(productDetailsResponse.getBrandName());
                    productWeight.setText(productDetailsResponse.getWeight()+"");
                    descriptionTextView.setText(productDetailsResponse.getDescription());
                    Log.i("QQWQWQWQW",productDetailsResponse.toString());

                    if(productDetailsResponse.getImages().size() == 0){
                        productImageView.setImageResource(R.drawable.noimage);
                    }else{
                        Glide.with(ProductActivity.this).load(Link.BASE_URL_IMAGE+productDetailsResponse.getImages().get(0).getImage()).into(productImageView);
                    }

                    adapter=new AttributeAdapter(ProductActivity.this,productDetailsResponse.getAttrValue());
                    attributeRecycler.setAdapter(adapter);
                    layoutManager=new LinearLayoutManager(ProductActivity.this);
                    attributeRecycler.setLayoutManager(layoutManager);
                    attributeRecycler.setHasFixedSize(true);

                    relatedLearnsAdapter=new RelatedLearnAdapter(ProductActivity.this,productDetailsResponse.getLearn());
                    relatedLearnsRecycler.setAdapter(relatedLearnsAdapter);
                    relatedLearnsLayoutManager=new LinearLayoutManager(ProductActivity.this,RecyclerView.HORIZONTAL,true);
                    relatedLearnsRecycler.setLayoutManager(relatedLearnsLayoutManager);
                    relatedLearnsRecycler.setHasFixedSize(true);

                }catch (Exception e){
                    Log.i("POIU",e.getMessage()+"  "+ e.getCause());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    CookieBar.Builder c = CookieBar.build(ProductActivity.this);
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

                ProductDetailsRequestModel productDetailsRequestModel = new ProductDetailsRequestModel();
                productDetailsRequestModel.setId(productId);
                String json = new Gson().toJson(productDetailsRequestModel);

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

}