package com.saloonme.ui.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.IHomeView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.model.response.SliderResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.HomePresenter;
import com.saloonme.ui.activities.BookActivity;
import com.saloonme.ui.activities.CategoryFilterActivity;
import com.saloonme.ui.activities.SaloonDetailsActivity;
import com.saloonme.ui.activities.SearchActivity;
import com.saloonme.ui.adapters.HomeServicesAdapter;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.ui.adapters.SliderAdapter;
import com.saloonme.ui.adapters.TrendingListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;
import com.saloonme.util.LocationSingleTon;
import com.saloonme.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements SliderAdapter.ItemListener,
        SaloonListAdapter.ItemListener, TrendingListAdapter.TrendingItemListener, IHomeView,
        HomeServicesAdapter.ItemListener {
    @BindView(R.id.rv_horizontalOffers)
    RecyclerView rv_horizontalOffers;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.rv_menuItems)
    RecyclerView rv_menuItems;
    @BindView(R.id.tv_popularPlaces)
    TextView tv_popularPlaces;
    @BindView(R.id.rv_popularItems)
    RecyclerView rv_popularItems;
    @BindView(R.id.tv_trending)
    TextView tv_trending;
    @BindView(R.id.rv_trendingItems)
    RecyclerView rv_trendingItems;
    @BindView(R.id.tv_home_services)
    TextView tv_home_services;
    @BindView(R.id.rv_homeServiceItems)
    RecyclerView rv_homeServiceItems;

    private View view;
    private Timer timer;
    public int position = 0;
    private SaloonListAdapter saloonListAdapter, poupularListAdapter;
    private TrendingListAdapter trendingListAdapter;
    private HomeServicesAdapter homeServicesAdapter;
    private SliderAdapter offersHorizontalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Dialog sort, filter;
    private HomePresenter homePresenter;

    @OnClick(R.id.search_layout)
    void onSearchClick() {
        goToActivity(SearchActivity.class);
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.sort_by)
    void onSortByclick() {
        sort = new Dialog(getActivity());
        sort.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sort.setCancelable(false);
        sort.setContentView(R.layout.sort_by_dialog);
        sort.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        sort.findViewById(R.id.closeDialogIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort.dismiss();
            }
        });
        sort.show();
    }

    @OnClick(R.id.filter_by)
    void onFilterByclick() {
        filter = new Dialog(getActivity());
        filter.requestWindowFeature(Window.FEATURE_NO_TITLE);
        filter.setCancelable(false);
        filter.setContentView(R.layout.filter_dialog);
        filter.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        filter.findViewById(R.id.closeDialogIv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter.dismiss();
            }
        });
        final ConstraintLayout ll = (ConstraintLayout) filter.findViewById(R.id.filter_cl);
        filter.findViewById(R.id.clear_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < ll.getChildCount(); i++) {
                    view = ll.getChildAt(i);
                    if (view instanceof CheckBox) {
                        ((CheckBox) view).setChecked(false);
                    }
                }
            }
        });
        filter.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        homePresenter = new HomePresenter(APIClient.getAPIService(), this);
        initRecyclerview();
        setUpTabs();
        LatLng latLng = LocationSingleTon.instance().getLatLng();
        homePresenter.getPopularPlaces(latLng.latitude + "", latLng.longitude + "");
        homePresenter.getSliders();
        return view;
    }


    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Mens"));
        tabLayout.addTab(tabLayout.newTab().setText("Womens"));
        tabLayout.addTab(tabLayout.newTab().setText("Kids"));
        homePresenter.getSaloonListBasedOnCategory("1");
        homePresenter.getHomeServices("1", "1");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homePresenter.getSaloonListBasedOnCategory((tab.getPosition() + 1) + "");
                homePresenter.getHomeServices((tab.getPosition() + 1) + "",
                        "1");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initRecyclerview() {
        offersHorizontalAdapter = new SliderAdapter(getActivity(), null,
                this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        rv_horizontalOffers.setLayoutManager(linearLayoutManager);
        rv_horizontalOffers.setItemAnimator(new DefaultItemAnimator());
        rv_horizontalOffers.setAdapter(offersHorizontalAdapter);
        rv_horizontalOffers.setNestedScrollingEnabled(false);
        int activeColor = getActivity().getResources().getColor(R.color.colorPrimary);
        rv_horizontalOffers.addItemDecoration(new CirclePagerIndicatorDecoration(activeColor,
                0));
        setSnapHelper();
        if (timer == null)
            timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 2000, 2000);

        saloonListAdapter = new SaloonListAdapter(getContext(), this, true);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_menuItems.setLayoutManager(saloonListManager);
        rv_menuItems.setAdapter(saloonListAdapter);
        rv_menuItems.setNestedScrollingEnabled(false);

        poupularListAdapter = new SaloonListAdapter(getContext(), this,
                false);
        LinearLayoutManager pouplarListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_popularItems.setLayoutManager(pouplarListManager);
        rv_popularItems.setAdapter(poupularListAdapter);
        rv_popularItems.setNestedScrollingEnabled(false);


        trendingListAdapter = new TrendingListAdapter(getContext(), this);
        LinearLayoutManager trendingListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_trendingItems.setLayoutManager(trendingListManager);
        rv_trendingItems.setAdapter(trendingListAdapter);
        rv_trendingItems.setNestedScrollingEnabled(false);

        homeServicesAdapter = new HomeServicesAdapter(getContext(), this);
        LinearLayoutManager homeServicesListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_homeServiceItems.setLayoutManager(homeServicesListManager);
        rv_homeServiceItems.setAdapter(homeServicesAdapter);
        rv_homeServiceItems.setNestedScrollingEnabled(false);
    }

    private void setSnapHelper() {
        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(rv_horizontalOffers);
        rv_horizontalOffers.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Override
    public void onItemClick(SaloonListResponseData saloonListResponseData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, saloonListResponseData);
        goToActivity(SaloonDetailsActivity.class, bundle);
    }

    @Override
    public void onBookNowClick(SaloonListResponseData saloonListResponseData) {
        List<String> serviceIdsList = PrefUtils.getInstance().
                getCartList(PrefUtils.getInstance().getUserId());
        if (serviceIdsList == null || serviceIdsList.size() == 0) {
            goToCategorySelectionScreen(saloonListResponseData.getStoreId());
        } else {
            showConfirmDialogToRemove(saloonListResponseData.getStoreId());
        }

    }

    private void showConfirmDialogToRemove(String storeId) {
        final Dialog dialog = new Dialog(getActivity());
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
                goToCategorySelectionScreen(storeId);
            }
        });

        dialog.show();
    }

    private void goToCategorySelectionScreen(String storeId) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.EXTRA_DETAILS, storeId);
        goToActivity(CategoryFilterActivity.class, bundle);
    }

    @Override
    public void onTrendingClick() {
        //goToActivity(CategoryFilterActivity.class);
    }

    @Override
    public void onHorizontalItemClick() {

    }

    @Override
    public void saloonListFetchedSuccess(SaloonListResponse saloonListResponse) {
        homePresenter.getPromotions();
        if (saloonListResponse == null) {
            saloonListAdapter.setData(null);
            return;
        }
        if (saloonListResponse.getStatus().equalsIgnoreCase("failed")) {
            showToast(saloonListResponse.getMessage());
            saloonListAdapter.setData(null);
            return;
        }
        if (saloonListResponse.getData() == null || saloonListResponse.getData().size() == 0) {
            showToast(saloonListResponse.getMessage());
            saloonListAdapter.setData(null);
            return;
        }
        saloonListAdapter.setData(saloonListResponse.getData());
    }

    @Override
    public void saloonListFetchedFailed() {
        showToast("Failed to fetch the saloons");
        saloonListAdapter.setData(null);
        homePresenter.getPromotions();
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
    }

    @Override
    public void promotionsFetchedFailed() {
        trendingListAdapter.setData(null);
        showToast("Failed to fetch the promotions");
    }

    @Override
    public void homeServiceListFetchedSuccess(SaloonListResponse saloonListResponse) {
        if (saloonListResponse != null) {
            if (saloonListResponse.getStatus().equalsIgnoreCase("failed")) {
                showToast(saloonListResponse.getMessage());
                homeServicesAdapter.setData(null);
                return;
            }
            if (saloonListResponse.getData() == null || saloonListResponse.getData().size() == 0) {
                showToast(saloonListResponse.getMessage());
                homeServicesAdapter.setData(null);
                return;
            }
            homeServicesAdapter.setData(saloonListResponse.getData());
        } else {
            showToast("Failed to fetch the home services");
            homeServicesAdapter.setData(null);
            return;
        }

    }

    @Override
    public void homeServiceListFetchedFailed() {
        showToast("Failed to fetch the home services");
        homeServicesAdapter.setData(null);
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
        if (sliderResponse == null) {
            offersHorizontalAdapter.setData(null);
            return;
        }
        if (sliderResponse.getStatus().equalsIgnoreCase("failed")) {
            showToast(sliderResponse.getMessage());
            offersHorizontalAdapter.setData(null);
            return;
        }
        if (sliderResponse.getData() == null || sliderResponse.getData().size() == 0) {
            showToast(sliderResponse.getMessage());
            offersHorizontalAdapter.setData(null);
            return;
        }
        offersHorizontalAdapter.setData(sliderResponse.getData());
    }

    @Override
    public void sliderFailed() {
        showToast("Failed to fetch the offers");
        offersHorizontalAdapter.setData(null);
    }

    @Override
    public void onItemClick() {

    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {

                        if (position == 3) {
                            position = 0;
                        } else {
                            position++;


                        }
                        if (position == 3) {
                            position = 0;
                        }
                        if (getActivity() != null) {
                            RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
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
}