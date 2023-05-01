package com.example.lutemongameht;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.lutemongameht.lutemons.Black;
import com.example.lutemongameht.lutemons.Green;
import com.example.lutemongameht.lutemons.Orange;
import com.example.lutemongameht.lutemons.Pink;
import com.example.lutemongameht.lutemons.White;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 fragmentArea;
    private TabPagerAdapter tabPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);


        Storage s = Storage.getInstance();
        s.loadLutemons(this);


        setupTabLayOut();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentArea.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    private void setupTabLayOut() {
        tabLayout = findViewById(R.id.tabLayout);
        fragmentArea = findViewById(R.id.fragmentArea);
        tabPagerAdapter = new TabPagerAdapter(this);
        fragmentArea.setAdapter(tabPagerAdapter);
    }
}