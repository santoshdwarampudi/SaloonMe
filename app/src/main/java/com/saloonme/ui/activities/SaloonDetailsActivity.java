package com.saloonme.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.ISaloonDetailView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.SaloonDetailsImageResponse;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.model.response.SaloonReviewResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.SaloonDetailPresenter;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.PhotosAdapter;
import com.saloonme.ui.adapters.ReviewsAdapter;
import com.saloonme.ui.adapters.SearchAdapter;
import com.saloonme.ui.fragments.HomeFragment;
import com.saloonme.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class SaloonDetailsActivity extends BaseAppCompactActivity implements
        ReviewsAdapter.ItemListener, PhotosAdapter.ItemListener,
        OffersHorizontalAdapter.ItemListener, ISaloonDetailView {
    private ReviewsAdapter reviewsAdapter;
    private PhotosAdapter photosAdapter;
    private OffersHorizontalAdapter offersHorizontalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Timer timer;
    public int position = 0;
    private SaloonDetailPresenter saloonDetailPresenter;
    private SaloonListResponseData saloonListResponseData;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.tv_description_details)
    TextView tv_description_details;
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
    @BindView(R.id.rv_saloonImages)
    RecyclerView rv_saloonImages;
    @BindView(R.id.tv_saloon)
    TextView tv_saloon;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_openTimings)
    TextView tv_openTimings;
    @BindView(R.id.tv_visitorCount)
    TextView tv_visitorCount;


    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.tv_bookNow)
    void onBookNowClick() {
        List<String> serviceIdsList = PrefUtils.getInstance().
                getCartList(PrefUtils.getInstance().getUserId());
        if (serviceIdsList == null || serviceIdsList.size() == 0) {
            goToCategorySelectionScreen();
        } else {
            showConfirmDialogToRemove();
        }

    }

    @OnClick(R.id.iv_directions)
    void onDirectionsClick() {
        try {
            String strUri = "http://maps.google.com/maps?q=loc:" + saloonListResponseData.getLat() + ","
                    + saloonListResponseData.getLng() + " (" + "Label which you want" + ")";
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
            intent.setClassName("com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity");
            startActivity(intent);
        } catch (Exception e) {

        }
    }

    private void goToCategorySelectionScreen() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.EXTRA_DETAILS, saloonListResponseData.getStoreId());
        goToActivity(CategoryFilterActivity.class, bundle);
    }

    private void showConfirmDialogToRemove() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_confirmation);

        TextView noTv = (TextView) dialog.findViewById(R.id.noTv);
        TextView yesTv = (TextView) dialog.findViewById(R.id.yesTv);

        noTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.getInstance().saveCartDetails(new
                        ArrayList<>(), PrefUtils.getInstance().getUserId());
                dialog.dismiss();
                goToCategorySelectionScreen();
            }
        });

        dialog.show();
    }


    @Override
    public int getActivityLayout() {
        return R.layout.activity_saloon_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saloonDetailPresenter = new SaloonDetailPresenter(APIClient.getAPIService(),
                this);
        saloonListResponseData = (SaloonListResponseData)
                getIntent().getSerializableExtra(StringConstants.EXTRA_DETAILS);
        if (saloonListResponseData == null) {
            showToast("Something went wrong");
            finish();
            return;
        }
        setSaloonImagesRecyclerView();
        setReviewRecyclerview();
        setPhotosRecyclerview();
        saloonDetailPresenter.getSaloonImages(saloonListResponseData.getStoreId());
        saloonDetailPresenter.getSaloonReviews(saloonListResponseData.getStoreId());
        tv_heading.setText(saloonListResponseData.getStoreName());
        tv_saloon.setText(saloonListResponseData.getStoreName());
        tv_location.setText(saloonListResponseData.getAddress());
        tv_description_details.setText(saloonListResponseData.getLongBio());
        setUpTabs();


    }

    private void setSaloonImagesRecyclerView() {
        offersHorizontalAdapter = new OffersHorizontalAdapter(this, null,
                this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        rv_saloonImages.setLayoutManager(linearLayoutManager);
        rv_saloonImages.setItemAnimator(new DefaultItemAnimator());
        rv_saloonImages.setAdapter(offersHorizontalAdapter);
        rv_saloonImages.setNestedScrollingEnabled(false);
        setSnapHelper();
        if (timer == null)
            timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 2000, 2000);
    }

    @Override
    public void onHorizontalItemClick() {
        showImage("");
    }

    @Override
    public void getSaloonImageSuccess(SaloonDetailsImageResponse saloonDetailsImageResponse) {
        if (saloonDetailsImageResponse == null) {
            showToast("Failed to get the store images");
            offersHorizontalAdapter.setData(null);
            photosAdapter.setData(null);
            return;
        }
        if (saloonDetailsImageResponse.getStatus().equalsIgnoreCase("failed")) {
            showToast(saloonDetailsImageResponse.getMessage());
            offersHorizontalAdapter.setData(null);
            photosAdapter.setData(null);
            return;
        }
        if (saloonDetailsImageResponse.getData() == null || saloonDetailsImageResponse.getData().size() == 0) {
            showToast(saloonDetailsImageResponse.getMessage());
            offersHorizontalAdapter.setData(null);
            photosAdapter.setData(null);
            return;
        }
        offersHorizontalAdapter.setData(saloonDetailsImageResponse.getData());
        photosAdapter.setData(saloonDetailsImageResponse.getData());
    }

    @Override
    public void getSaloonImageFailed() {
        showToast("Failed to get the store images");
        offersHorizontalAdapter.setData(null);
        photosAdapter.setData(null);
    }

    @Override
    public void getSaloonReviewSuccess(SaloonReviewResponse saloonReviewResponse) {
        if (saloonReviewResponse == null) {
            showToast("Failed to get the store reviews");
            reviewsAdapter.setData(null);
            return;
        }
        if (saloonReviewResponse.getStatus().equalsIgnoreCase("failed")) {
            showToast(saloonReviewResponse.getMessage());
            reviewsAdapter.setData(null);
            return;
        }
        if (saloonReviewResponse.getData() == null || saloonReviewResponse.getData().size() == 0) {
            showToast(saloonReviewResponse.getMessage());
            reviewsAdapter.setData(null);
            return;
        }
        reviewsAdapter.setData(saloonReviewResponse.getData());
    }

    @Override
    public void getSaloonReviewFailed() {
        showToast("Failed to get the store reviews");
        reviewsAdapter.setData(null);
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (getContext() != null) {
                runOnUiThread(new Runnable() {
                    public void run() {

                        if (position == 3) {
                            position = 0;
                        } else {
                            position++;


                        }
                        if (position == 3) {
                            position = 0;
                        }
                        if (getContext() != null) {
                            RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {
                                @Override
                                protected int getVerticalSnapPreference() {
                                    return LinearSmoothScroller.SNAP_TO_START;
                                }
                            };
                            smoothScroller.setTargetPosition(position);
                            linearLayoutManager.startSmoothScroll(smoothScroller);
                        }


                    }
                });
            }

        }
    }

    private void setSnapHelper() {
        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(rv_saloonImages);
        rv_saloonImages.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                position = linearLayoutManager.findFirstVisibleItemPosition();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setPhotosRecyclerview() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        photosAdapter = new PhotosAdapter(getContext(), this);
        rv_photos.setLayoutManager(layoutManager);
        rv_photos.setAdapter(photosAdapter);
        rv_photos.setNestedScrollingEnabled(false);
    }

    private void setReviewRecyclerview() {
        reviewsAdapter = new ReviewsAdapter(getContext(), this, false);
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

    public void showImage(String imageUri) {
        final Dialog nagDialog = new Dialog(this, R.style.Dialog);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(true);
        nagDialog.setCanceledOnTouchOutside(true);
        nagDialog.setContentView(R.layout.imgmsgpopup);
        Button btnClose = (Button) nagDialog.findViewById(R.id.btnIvClose);
        ImageView ivPreview = (ImageView) nagDialog.findViewById(R.id.iv_preview_image);
        Glide.with(this).load(imageUri)
                .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder).
                        error(R.drawable.ic_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(ivPreview);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                nagDialog.dismiss();
            }
        });
        nagDialog.show();


    }
}