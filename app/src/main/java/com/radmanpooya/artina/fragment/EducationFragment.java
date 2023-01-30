package com.radmanpooya.artina.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.education.category.CategoryResponseItem;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EducationFragment extends Fragment {

    RecyclerView recyclerCategory;
    CategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CategoryAdapter.OnClickCategoryMain onClickCategoryMain;

    public EducationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education, container, false);

        findViews(view);
        initialize();

        return view;
    }


    private void findViews(View view){
        recyclerCategory = view.findViewById(R.id.recycler_category);
    }

    private void initialize(){

        getCategory();

        onClickCategoryMain=new CategoryAdapter.OnClickCategoryMain() {
            @Override
            public void onClickCategory() {

            }
        };

    }
    public void getCategory(){

        final StringRequest categoryReq = new StringRequest(Request.Method.GET, Link.GET_CATEGORY, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                try {

                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                    List<CategoryResponseItem> categoryResponseItemList = gson.fromJson(response, new TypeToken<List<CategoryResponseItem>>(){}.getType());

                    Log.i("AADD222",categoryResponseItemList.toString());
                    adapter=new CategoryAdapter(getActivity().getApplicationContext(),categoryResponseItemList,onClickCategoryMain);
                    recyclerCategory.setAdapter(adapter);
                    layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerCategory.setLayoutManager(layoutManager);
                    recyclerCategory.setHasFixedSize(true);

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    CookieBar.Builder c = CookieBar.build(getActivity());
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

        categoryReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Api.getInstance().addToRequestQueue(categoryReq, "GETCAT");

    }

}