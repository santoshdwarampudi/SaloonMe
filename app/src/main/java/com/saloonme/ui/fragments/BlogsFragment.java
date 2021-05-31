package com.saloonme.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.IBlogDetailsView;
import com.saloonme.interfaces.IHomeView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.BlogDetailsResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.model.response.SliderResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.BlogDetailsPresenter;
import com.saloonme.presenters.HomePresenter;
import com.saloonme.ui.activities.SaloonDetailsActivity;
import com.saloonme.ui.adapters.BlogsAdapter;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.ui.adapters.TrendingListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;
import com.saloonme.util.PrefUtils;

import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class BlogsFragment
        extends BaseFragment implements IBlogDetailsView, BlogsAdapter.BlogItemListener,
        TrendingListAdapter.TrendingItemListener, IHomeView, SaloonListAdapter.ItemListener {
    private SaloonListAdapter poupularListAdapter;
    private View view;
    private BlogsAdapter blogsAdapter;
    private BlogDetailsPresenter blogDetailsPresenter;
    @BindView(R.id.rv_blogItems)
    RecyclerView rv_blogItems;
    @BindView(R.id.tv_trending)
    TextView tv_trending;
    @BindView(R.id.rv_trendingItems)
    RecyclerView rv_trendingItems;
    @BindView(R.id.rv_popularItems)
    RecyclerView rv_popularItems;
    private TrendingListAdapter trendingListAdapter;
    HomePresenter homePresenter;

    public BlogsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_salons;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerview();
        blogDetailsPresenter = new BlogDetailsPresenter(APIClient.getAPIService(), this);
        homePresenter = new HomePresenter(APIClient.getAPIService(), this);
        blogDetailsPresenter.getBlogDetails();
        return view;
    }

    @Override
    public void onBlogDetailsSuccess(BlogDetailsResponse blogDetailsResponse) {
        homePresenter.getPromotions();
        if (blogDetailsResponse == null) {
            showToast("Failed to get the blogs");
            return;
        }
        if (blogDetailsResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(blogDetailsResponse.getMessage());
            return;
        }
        if (blogDetailsResponse.getData() == null || blogDetailsResponse.getData().size() == 0) {
            showToast(blogDetailsResponse.getMessage());
            return;
        }
        blogsAdapter.setData(blogDetailsResponse.getData());
    }

    @Override
    public void onBlogDetailsFailed() {
        showToast("Failed to get the blogs");
    }

    private void initRecyclerview() {
        blogsAdapter = new BlogsAdapter(getContext(), null,
                this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_blogItems.setLayoutManager(saloonListManager);
        rv_blogItems.setAdapter(blogsAdapter);
        rv_blogItems.setNestedScrollingEnabled(false);

        trendingListAdapter = new TrendingListAdapter(getContext(), this);
        LinearLayoutManager trendingListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_trendingItems.setLayoutManager(trendingListManager);
        rv_trendingItems.setAdapter(trendingListAdapter);
        rv_trendingItems.setNestedScrollingEnabled(false);

        poupularListAdapter = new SaloonListAdapter(getContext(), this,
                false);
        LinearLayoutManager pouplarListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_popularItems.setLayoutManager(pouplarListManager);
        rv_popularItems.setAdapter(poupularListAdapter);
        rv_popularItems.setNestedScrollingEnabled(false);
    }


    @Override
    public void onBlogClick() {

    }

    @Override
    public void onTrendingClick() {

    }

    @Override
    public void saloonListFetchedSuccess(SaloonListResponse saloonListResponse) {

    }

    @Override
    public void saloonListFetchedFailed() {

    }

    @Override
    public void promotionsFetchedSuccess(PromotionsResponse promotionsResponse) {
        if (promotionsResponse != null) {
            if (promotionsResponse.getStatus().equalsIgnoreCase("failed")) {
                showToast(promotionsResponse.getMessage());
                trendingListAdapter.setData(null);
                return;
            }
            if (promotionsResponse.getData() == null || promotionsResponse.getData().size() == 0) {
                showToast(promotionsResponse.getMessage());
                trendingListAdapter.setData(null);
                return;
            }
            trendingListAdapter.setData(promotionsResponse.getData());
        } else {
            trendingListAdapter.setData(null);
            showToast("Failed to fetch the promotions");
        }
        //LatLng latLng = LocationSingleTon.instance().getLatLng();
        //dummy hyd values
        LatLng latLng = new LatLng(17.387140, 78.491684);
        homePresenter.getPopularPlaces(latLng.latitude + "", latLng.longitude + "");
    }

    @Override
    public void promotionsFetchedFailed() {
        trendingListAdapter.setData(null);
        showToast("Failed to fetch the promotions");
    }

    @Override
    public void homeServiceListFetchedSuccess(SaloonListResponse saloonListResponse) {

    }

    @Override
    public void homeServiceListFetchedFailed() {

    }

    @Override
    public void pouplarListFetchedSuccess(SaloonListResponse saloonListResponse) {
        if (saloonListResponse == null) {
            poupularListAdapter.setData(null);
            return;
        }
        if (saloonListResponse.getStatus().equalsIgnoreCase("failed")) {
            showToast(saloonListResponse.getMessage());
            poupularListAdapter.setData(null);
            return;
        }
        if (saloonListResponse.getData() == null || saloonListResponse.getData().size() == 0) {
            showToast(saloonListResponse.getMessage());
            poupularListAdapter.setData(null);
            return;
        }
        poupularListAdapter.setData(saloonListResponse.getData());
    }

    @Override
    public void pouplarServiceListFetchedFailed() {
        showToast("Failed to fetch the popular places ");
        poupularListAdapter.setData(null);
    }

    @Override
    public void sliderSuccess(SliderResponse sliderResponse) {

    }

    @Override
    public void sliderFailed() {

    }

    @Override
    public void getProfileDetailsSuccess(ProfileResponse profileResponse) {

    }

    @Override
    public void getProfileDetailsFailed() {

    }

    @Override
    public void onItemClick(SaloonListResponseData saloonListResponseData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, saloonListResponseData);
        goToActivity(SaloonDetailsActivity.class, bundle);
    }

    @Override
    public void onBookNowClick(SaloonListResponseData saloonListResponseData) {

    }
}