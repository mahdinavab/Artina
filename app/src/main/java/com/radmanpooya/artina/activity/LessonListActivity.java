package com.radmanpooya.artina.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.radmanpooya.artina.adapter.education.lesson.LessonAdapter;
import com.radmanpooya.artina.adapter.education.section.SectionAdapter;
import com.radmanpooya.artina.api.Api;
import com.radmanpooya.artina.api.Link;
import com.radmanpooya.artina.model.education.section.response.SectionResponseItem;
import com.radmanpooya.artina.model.lesson.LessonListResponseItem;
import com.radmanpooya.artina.util.NullStringToEmptyAdapterFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonListActivity extends AppCompatActivity {


    TextView lessonTitleTextView;
    int sectionId;
    RecyclerView sectionRecycler;
    LessonAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        findViews();
        initialize();

    }

    private void findViews() {

        lessonTitleTextView = findViewById(R.id.lesson_title_text_view);
        sectionRecycler = findViewById(R.id.section_recycler);

    }

    private void initialize() {
        sectionId = getIntent().getIntExtra("section_id", 1);

        getLessonData(sectionId+"");
    }

    public void getLessonData(String sectionId){

        final StringRequest sectionReq = new StringRequest(Request.Method.GET, Link.GET_LESSONS+sectionId, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {

                try {
                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                    List<LessonListResponseItem> lessonListResponseItems = gson.fromJson(response, new TypeToken<List<LessonListResponseItem>>(){}.getType());
                    adapter=new LessonAdapter(LessonListActivity.this,lessonListResponseItems);
                    sectionRecycler.setAdapter(adapter);
                    layoutManager=new LinearLayoutManager(LessonListActivity.this);
                    sectionRecycler.setLayoutManager(layoutManager);
                    sectionRecycler.setHasFixedSize(true);
                    lessonTitleTextView.setText(lessonListResponseItems.get(0).getSection().getTitle());

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    CookieBar.Builder c = CookieBar.build(LessonListActivity.this);
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