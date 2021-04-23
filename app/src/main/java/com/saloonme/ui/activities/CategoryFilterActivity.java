package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.athbk.ultimatetablayout.OnClickTabListener;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.FragmentAdapterDemo;

public class CategoryFilterActivity extends AppCompatActivity {

    UltimateTabLayout tabLayout;
    ViewPager viewPager;

    FragmentAdapterDemo adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_filter);

        tabLayout = (UltimateTabLayout)findViewById(R.id.verticalTabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        adapter = new FragmentAdapterDemo(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

//        options. if you override onClickTabListener.
        tabLayout.setOnClickTabListener(new OnClickTabListener() {
            @Override
            public void onClickTab(int currentPos) {
                Log.e("LOG", "OnClickTab " + currentPos);
                viewPager.setCurrentItem(currentPos);
                if (currentPos == 1){
                    tabLayout.setNumberBadge(currentPos, 0);
                }
                else {
                    tabLayout.setNumberBadge(currentPos, 1);
                }
            }
        });

        tabLayout.setViewPager(viewPager, adapter);

    }
}