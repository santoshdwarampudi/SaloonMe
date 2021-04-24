package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.athbk.ultimatetablayout.OnClickTabListener;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.FragmentAdapterDemo;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryFilterActivity extends BaseAppCompactActivity {

    UltimateTabLayout tabLayout;
    ViewPager viewPager;

    FragmentAdapterDemo adapter;

    @BindView(R.id.tv_heading)
    TextView tv_heading;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_category_filter;
    }

    @OnClick(R.id.continue_btn)
    void onContinueClick() {
        goToActivity(BookActivity.class);
    }

    @OnClick(R.id.iv_menu)
    void onBackClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Select Service");
        tabLayout = (UltimateTabLayout) findViewById(R.id.verticalTabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new FragmentAdapterDemo(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

//        options. if you override onClickTabListener.
        tabLayout.setOnClickTabListener(new OnClickTabListener() {
            @Override
            public void onClickTab(int currentPos) {
                Log.e("LOG", "OnClickTab " + currentPos);
                viewPager.setCurrentItem(currentPos);
                if (currentPos == 1) {
                    tabLayout.setNumberBadge(currentPos, 0);
                } else {
                    tabLayout.setNumberBadge(currentPos, 1);
                }
            }
        });

        tabLayout.setViewPager(viewPager, adapter);

    }
}