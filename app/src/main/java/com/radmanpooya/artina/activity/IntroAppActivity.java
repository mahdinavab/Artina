package com.radmanpooya.artina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.adapter.intro.IntroViewPagerAdapter;
import com.radmanpooya.artina.model.intro.IntroItem;
import com.radmanpooya.artina.util.UserInfoManager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class IntroAppActivity extends AppCompatActivity {

    DotsIndicator dotsIndicator;
    ViewPager2 viewPagerIntro;
    IntroViewPagerAdapter introViewPagerAdapter;
    CardView nextIntro;
    CardView backIntro;
    int localPosition=0;
    boolean isForward = true;
    UserInfoManager userInfoManager;
    TextView nextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_app);

        dotsIndicator = findViewById(R.id.dots_indicator);
        viewPagerIntro = findViewById(R.id.view_pager_intro);
        nextIntro = findViewById(R.id.next_intro);
        backIntro = findViewById(R.id.back_intro);
        nextTextView = findViewById(R.id.next_text_view);

        userInfoManager = new UserInfoManager(IntroAppActivity.this);
        userInfoManager.setFirstLogin("yes");

        List<IntroItem> introItemList;
        introItemList = new ArrayList<>();
        IntroItem introItemOne = new IntroItem(R.drawable.welcomeimage,"سلام","به سامانه آموزشی آرتینا خوش آمدید");
        IntroItem introItemTwo = new IntroItem(R.drawable.introimageone,"خرید و فروش","با ضمانت");
        IntroItem introItemThree = new IntroItem(R.drawable.introimageone,"آموزش با کیفیت","با بهترین اساتید");
        IntroItem introItemFour = new IntroItem(R.drawable.introimagethree,"کسب درآمد","از تولید محتوای خود");

        introItemList.add(introItemOne);
        introItemList.add(introItemTwo);
        introItemList.add(introItemThree);
        introItemList.add(introItemFour);

        introViewPagerAdapter=new IntroViewPagerAdapter(introItemList,viewPagerIntro,IntroAppActivity.this);
        viewPagerIntro.setAdapter(introViewPagerAdapter);
        dotsIndicator.attachTo(viewPagerIntro);

        nextIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(localPosition == introItemList.size()-1){
                    Intent i = new Intent(IntroAppActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                viewPagerIntro.setCurrentItem(viewPagerIntro.getCurrentItem()+1);
            }
        });
        backIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerIntro.setCurrentItem(viewPagerIntro.getCurrentItem()-1);
            }
        });
        Animation aniFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_two);
        Animation aniFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out_two);

        viewPagerIntro.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                localPosition = position;
                if(position == 0){
                    backIntro.setVisibility(View.INVISIBLE);
                    backIntro.startAnimation(aniFadeOut);
                    isForward = true;
                }else if(position == 1){
                    backIntro.setVisibility(View.VISIBLE);
                    if(isForward){
                        backIntro.startAnimation(aniFadeIn);
                    }else{

                    }
                    isForward = true;
                }else{
                    backIntro.setVisibility(View.VISIBLE);
                    isForward = false;
                }

                if(position == 1){

                }
                if (position == introItemList.size()-1) {
                    nextTextView.setText("شروع");
                    nextIntro.startAnimation(aniFadeIn);
                } else {
                }
            }
        });

    }
}