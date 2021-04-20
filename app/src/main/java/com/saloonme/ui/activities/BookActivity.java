package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.ui.adapters.PhotosAdapter;
import com.saloonme.ui.adapters.ReviewsAdapter;
import com.saloonme.ui.adapters.SeatBookingAdapter;
import com.saloonme.ui.adapters.SelectBarbersAdapter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class BookActivity extends BaseAppCompactActivity implements
        SelectBarbersAdapter.ItemListener, SeatBookingAdapter.ItemListener {
    private SelectBarbersAdapter selectBarbersAdapter;
    private SeatBookingAdapter seatBookingAdapter;
    private int tabPosition = 0;
    private int mYear, mMonth, mDay, mHour, mMinute;
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
    @BindView(R.id.rv_seats)
    RecyclerView rv_seats;
    @BindView(R.id.tv_select_date)
    TextView tv_select_date;
    @BindView(R.id.tv_select_time)
    TextView tv_select_time;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.next)
    void onNextClick() {
        if (next.getText().toString().equalsIgnoreCase(getString(R.string.next))) {
            tabPosition++;
        } else {
            goToActivity(CheckOutActivity.class);
        }
        if (tabPosition > 0) {
            previous.setVisibility(View.VISIBLE);
        } else {
            previous.setVisibility(View.GONE);
        }
        if (tabPosition == 2) {
            next.setText(getString(R.string.submit_pay));
        } else {
            next.setText(getString(R.string.next));
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
        if (tabPosition == 2) {
            next.setText(getString(R.string.submit_pay));
        } else {
            next.setText(getString(R.string.next));
        }
        if (tabPosition > 0) {
            previous.setVisibility(View.VISIBLE);
        } else {
            previous.setVisibility(View.GONE);
        }
        TabLayout.Tab tab = tabLayout.getTabAt(tabPosition);
        tab.select();
    }

    @OnClick(R.id.tv_select_date)
    void onDateSelect() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_select_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.tv_select_time)
    void onTimeSelect() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        tv_select_time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
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
        setSeatSelectionRecycleView();
    }

    private void setSeatSelectionRecycleView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        seatBookingAdapter = new SeatBookingAdapter(getContext(), this);
        rv_seats.setLayoutManager(layoutManager);
        rv_seats.setAdapter(seatBookingAdapter);
        rv_seats.setNestedScrollingEnabled(false);
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
                if (tabPosition > 0) {
                    previous.setVisibility(View.VISIBLE);
                } else {
                    previous.setVisibility(View.GONE);
                }
                if (tabPosition == 2) {
                    next.setText(getString(R.string.submit_pay));
                } else {
                    next.setText(getString(R.string.next));
                }
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