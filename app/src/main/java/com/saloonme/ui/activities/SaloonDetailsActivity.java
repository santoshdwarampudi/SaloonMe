package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.PhotosAdapter;
import com.saloonme.ui.adapters.ReviewsAdapter;
import com.saloonme.ui.adapters.SearchAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class SaloonDetailsActivity extends BaseAppCompactActivity implements
        ReviewsAdapter.ItemListener, PhotosAdapter.ItemListener {
    private ReviewsAdapter reviewsAdapter;
    private PhotosAdapter photosAdapter;

    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.saloonTabs)
    TabLayout tabLayout;
    @BindView(R.id.rv_reviews)
    RecyclerView rv_reviews;
    @BindView(R.id.booknow_layout)
    ConstraintLayout booknow_layout;
    @BindView(R.id.overview_layout)
    ConstraintLayout overview_layout;
    @BindView(R.id.photos_layout)
    ConstraintLayout photos_layout;
    @BindView(R.id.reviews_layout)
    ConstraintLayout reviews_layout;
    @BindView(R.id.rv_photos)
    RecyclerView rv_photos;


    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.tv_bookNow)
    void onBookNowClick() {
        goToActivity(BookActivity.class);
    }



    @Override
    public int getActivityLayout() {
        return R.layout.activity_saloon_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Raj Saloon");
        setUpTabs();
        setReviewRecyclerview();
        setPhotosRecyclerview();
    }

    private void setPhotosRecyclerview() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        photosAdapter = new PhotosAdapter(getContext(), this);
        rv_photos.setLayoutManager(layoutManager);
        rv_photos.setAdapter(photosAdapter);
        rv_photos.setNestedScrollingEnabled(false);
    }

    private void setReviewRecyclerview() {
        reviewsAdapter = new ReviewsAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_reviews.setLayoutManager(saloonListManager);
        rv_reviews.setAdapter(reviewsAdapter);
        rv_reviews.setNestedScrollingEnabled(false);
    }

    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("OverView"));
        //  tabLayout.addTab(tabLayout.newTab().setText("Book Now"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        overview_layout.setVisibility(View.VISIBLE);
                        booknow_layout.setVisibility(View.GONE);
                        reviews_layout.setVisibility(View.GONE);
                        photos_layout.setVisibility(View.GONE);
                        break;
                    /*case 1:
                        overview_layout.setVisibility(View.GONE);
                        booknow_layout.setVisibility(View.VISIBLE);
                        reviews_layout.setVisibility(View.GONE);
                        photos_layout.setVisibility(View.GONE);
                        break;*/
                    case 1:
                        overview_layout.setVisibility(View.GONE);
                        booknow_layout.setVisibility(View.GONE);
                        reviews_layout.setVisibility(View.VISIBLE);
                        photos_layout.setVisibility(View.GONE);
                        break;
                    case 2:
                        overview_layout.setVisibility(View.GONE);
                        booknow_layout.setVisibility(View.GONE);
                        reviews_layout.setVisibility(View.GONE);
                        photos_layout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}