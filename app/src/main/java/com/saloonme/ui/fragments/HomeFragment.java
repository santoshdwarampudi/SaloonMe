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

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.activities.BookActivity;
import com.saloonme.ui.activities.SaloonDetailsActivity;
import com.saloonme.ui.activities.SearchActivity;
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
    @BindView(R.id.tv_popularPlaces)
    TextView tv_popularPlaces;
    @BindView(R.id.rv_popularItems)
    RecyclerView rv_popularItems;

    private View view;
    private Timer timer;
    public int position = 0;
    private SaloonListAdapter saloonListAdapter;
    private OffersHorizontalAdapter offersHorizontalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Dialog sort, filter;

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

        saloonListAdapter = new SaloonListAdapter(getContext(), this,true);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_menuItems.setLayoutManager(saloonListManager);
        rv_menuItems.setAdapter(saloonListAdapter);
        rv_menuItems.setNestedScrollingEnabled(false);

        saloonListAdapter = new SaloonListAdapter(getContext(), this,false);
        LinearLayoutManager pouplarListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_popularItems.setLayoutManager(pouplarListManager);
        rv_popularItems.setAdapter(saloonListAdapter);
        rv_popularItems.setNestedScrollingEnabled(false);
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
    public void onItemClick() {
        goToActivity(SaloonDetailsActivity.class);
    }

    @Override
    public void onBookNowClick() {
        goToActivity(BookActivity.class);
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