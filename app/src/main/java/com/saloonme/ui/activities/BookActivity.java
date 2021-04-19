package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.ReviewsAdapter;
import com.saloonme.ui.adapters.SelectBarbersAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class BookActivity extends BaseAppCompactActivity implements SelectBarbersAdapter.ItemListener {
    private SelectBarbersAdapter selectBarbersAdapter;
    private int tabPosition = 0;
    @BindView(R.id.bookTab)
    TabLayout tabLayout;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.book1)
    ConstraintLayout book1;
    @BindView(R.id.book2)
    ConstraintLayout book2;
    @BindView(R.id.book3)
    ConstraintLayout book3;
    @BindView(R.id.rv_barbers)
    RecyclerView rv_barbers;
    @BindView(R.id.previous)
    TextView previous;
    @BindView(R.id.next)
    TextView next;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.next)
    void onNextClick() {
        if (next.getText().toString().equalsIgnoreCase(getString(R.string.next))) {
            tabPosition++;
        }
        TabLayout.Tab tab = tabLayout.getTabAt(tabPosition);
        tab.select();
    }

    @OnClick(R.id.previous)
    void onPreviousClick() {
        if (previous.getText().toString().equalsIgnoreCase(getString(R.string.previous))) {
            if (tabPosition != 0)
                tabPosition--;
        }
        TabLayout.Tab tab = tabLayout.getTabAt(tabPosition);
        tab.select();
    }

    @OnClick(R.id.tv_select_date)
    void onDateSelect() {

    }

    @OnClick(R.id.tv_select_time)
    void onTimeSelect() {

    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_book;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Book Now");
        setUpTabs();
        setBarbersRecyclerView();
    }

    private void setBarbersRecyclerView() {
        selectBarbersAdapter = new SelectBarbersAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_barbers.setLayoutManager(saloonListManager);
        rv_barbers.setAdapter(selectBarbersAdapter);
        rv_barbers.setNestedScrollingEnabled(false);
    }

    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Step1"));
        tabLayout.addTab(tabLayout.newTab().setText("Step2"));
        tabLayout.addTab(tabLayout.newTab().setText("Step3"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                switch (tab.getPosition()) {
                    case 0:
                        book1.setVisibility(View.VISIBLE);
                        book2.setVisibility(View.GONE);
                        book3.setVisibility(View.GONE);
                        break;
                    case 1:
                        book1.setVisibility(View.GONE);
                        book2.setVisibility(View.VISIBLE);
                        book3.setVisibility(View.GONE);
                        break;
                    case 2:
                        book1.setVisibility(View.GONE);
                        book2.setVisibility(View.GONE);
                        book3.setVisibility(View.VISIBLE);
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