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

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class SalonsFragment
        extends BaseFragment
        implements SaloonListAdapter.ItemListener {

    private View view;
    @BindView(R.id.rv_menuItems)
    RecyclerView rv_menuItems;

    @BindView(R.id.sort_by)
    TextView sort_by;

    @BindView(R.id.filter_by)
    TextView filter_by;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    private SaloonListAdapter saloonListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Dialog sort, filter;

    public SalonsFragment() {
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
        setUpTabs();
        return view;
    }

    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Mens"));
        tabLayout.addTab(tabLayout.newTab().setText("Womens"));
        tabLayout.addTab(tabLayout.newTab().setText("Kids"));
    }

    private void initRecyclerview() {


        saloonListAdapter = new SaloonListAdapter(getContext(), this,true);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_menuItems.setLayoutManager(saloonListManager);
        rv_menuItems.setAdapter(saloonListAdapter);
        rv_menuItems.setNestedScrollingEnabled(false);
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
    public void onItemClick() {

    }

    @Override
    public void onBookNowClick() {

    }


}