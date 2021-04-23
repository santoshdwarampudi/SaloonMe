package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;
import com.saloonme.ui.adapters.CategoryFilterAdapter;
import com.saloonme.ui.adapters.HomeServicesAdapter;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.ui.adapters.TrendingListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;

import java.util.Timer;

import butterknife.BindView;

public class FragmentCategory extends BaseFragment{


    @BindView(R.id.filterRv)
    RecyclerView filterRv;
    private CategoryFilterAdapter categoryFilterAdapter;

    private View view;
    public static FragmentCategory newInstance() {
        Bundle args = new Bundle();
        FragmentCategory fragment = new FragmentCategory();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_category;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerview();
        return view;
    }

    private void initRecyclerview() {

        categoryFilterAdapter = new CategoryFilterAdapter(getContext(),  false);
        LinearLayoutManager categoryFilterListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        filterRv.setLayoutManager(categoryFilterListManager);
        filterRv.setAdapter(categoryFilterAdapter);
        filterRv.setNestedScrollingEnabled(false);
    }
}