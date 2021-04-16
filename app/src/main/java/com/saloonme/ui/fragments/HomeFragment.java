package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements OffersHorizontalAdapter.ItemListener,
        SaloonListAdapter.ItemListener {
    @BindView(R.id.rv_horizontalOffers)
    RecyclerView rv_horizontalOffers;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.rv_menuItems)
    RecyclerView rv_menuItems;

    private View view;
    private Timer timer;
    public int position = 0;
    private SaloonListAdapter saloonListAdapter;
    private OffersHorizontalAdapter offersHorizontalAdapter;
    private LinearLayoutManager linearLayoutManager;

    @OnClick(R.id.search_layout)
    void onSearchClick() {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerview();
        setUpTabs();
        return view;
    }

    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Mens"));
        tabLayout.addTab(tabLayout.newTab().setText("Womens"));
        tabLayout.addTab(tabLayout.newTab().setText("Kids"));
    }

    private void initRecyclerview() {
        offersHorizontalAdapter = new OffersHorizontalAdapter(getActivity(), null,
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

        saloonListAdapter = new SaloonListAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_menuItems.setLayoutManager(saloonListManager);
        rv_menuItems.setAdapter(saloonListAdapter);
        rv_menuItems.setNestedScrollingEnabled(false);
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