package com.radmanpooya.artina.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.radmanpooya.artina.R;
import com.radmanpooya.artina.fragment.ChatFragment;
import com.radmanpooya.artina.fragment.EducationFragment;
import com.radmanpooya.artina.fragment.MarketFragment;
import com.radmanpooya.artina.fragment.ProfileFragment;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            MarketFragment marketFragment = new MarketFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main, marketFragment)
                    .commit();
        }
        animatedBottomBar = findViewById(R.id.animated_bottom_bar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId()) {
                    case R.id.market_bottom_navigation:
                        fragment = new MarketFragment();
                        break;
                    case R.id.education_bottom_navigation:
                        fragment = new EducationFragment();
                        break;
                    case R.id.chat_bottom_navigation:
                        fragment = new ChatFragment();
                        break;
                    case R.id.profile_bottom_navigation:
                        fragment = new ProfileFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container_main, fragment).commit();
                }

            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });

    }
}