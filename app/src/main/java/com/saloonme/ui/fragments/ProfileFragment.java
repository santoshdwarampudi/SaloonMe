package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.HistoryAdapter;

import butterknife.BindView;


public class ProfileFragment extends BaseFragment implements HistoryAdapter.ItemListener {

    private HistoryAdapter historyAdapter;
    @BindView(R.id.bookingsTabs)
    TabLayout bookingsTabs;
    @BindView(R.id.rv_bookings)
    RecyclerView rv_bookings;
    private View view;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        setUpTabs();
        setUpRecyclerview(false);
        return view;
    }

    private void setUpRecyclerview(boolean isHistory) {
        historyAdapter = new HistoryAdapter(getActivity(), this, isHistory);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_bookings.setLayoutManager(saloonListManager);
        rv_bookings.setAdapter(historyAdapter);
        rv_bookings.setNestedScrollingEnabled(false);
    }

    private void setUpTabs() {
        bookingsTabs.addTab(bookingsTabs.newTab().setText("UpComing Bookings"));
        bookingsTabs.addTab(bookingsTabs.newTab().setText("History"));
        bookingsTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setUpRecyclerview(false);
                        break;
                    case 1:
                        setUpRecyclerview(true);
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