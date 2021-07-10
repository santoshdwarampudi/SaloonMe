package com.saloonme.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
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
import com.saloonme.model.response.TrendingHairStyleResponse;
import com.saloonme.model.response.TrendingHairStyleResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.BlogDetailsPresenter;
import com.saloonme.presenters.HomePresenter;
import com.saloonme.ui.activities.SaloonDetailsActivity;
import com.saloonme.ui.adapters.BlogsAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.ui.adapters.TrendingHairStylesAdapter;
import com.saloonme.ui.adapters.TrendingListAdapter;

import butterknife.BindView;


public class BlogsFragment
        extends BaseFragment implements IBlogDetailsView, BlogsAdapter.BlogItemListener,
        TrendingListAdapter.TrendingItemListener, IHomeView, SaloonListAdapter.ItemListener,
        TrendingHairStylesAdapter.ItemListener {
    private SaloonListAdapter poupularListAdapter;
    private BlogsAdapter blogsAdapter;
    private BlogDetailsPresenter blogDetailsPresenter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_blogItems)
    RecyclerView rv_blogItems;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_trending)
    TextView tv_trending;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_trendingItems)
    RecyclerView rv_trendingItems;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_popularItems)
    RecyclerView rv_popularItems;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_trending_hair_styles)
    RecyclerView rv_trending_hair_styles;
    private TrendingListAdapter trendingListAdapter;
    private TrendingHairStylesAdapter trendingHairStylesAdapter;
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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerview();
        blogDetailsPresenter = new BlogDetailsPresenter(APIClient.getAPIService(), this);
        homePresenter = new HomePresenter(APIClient.getAPIService(), this);
        blogDetailsPresenter.getBlogDetails();
        blogDetailsPresenter.getTrendingHairStyles();
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

    @Override
    public void getTrendingHairStyleSuccess(TrendingHairStyleResponse trendingHairStyleResponse) {
        if (trendingHairStyleResponse == null) {
            showToast("Failed to get the trending hair styles");
            return;
        }
        if (trendingHairStyleResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(trendingHairStyleResponse.getMessage());
            return;
        }
        if (trendingHairStyleResponse.getData() == null || trendingHairStyleResponse.getData().size() == 0) {
            showToast(trendingHairStyleResponse.getMessage());
            return;
        }
        trendingHairStylesAdapter.setData(trendingHairStyleResponse.getData());
    }

    @Override
    public void getTrendingHairStyleFailed() {
        showToast("Failed to get the trending hair styles");
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

        trendingHairStylesAdapter = new TrendingHairStylesAdapter(getContext(), this,
                null);
        LinearLayoutManager trendingHairStylesManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_trending_hair_styles.setLayoutManager(trendingHairStylesManager);
        rv_trending_hair_styles.setAdapter(trendingHairStylesAdapter);
        rv_trending_hair_styles.setNestedScrollingEnabled(false);
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

    @Override
    public void onTrendingHairStyleItemClick(TrendingHairStyleResponseData trendingHairStyleResponseData) {

    }
}