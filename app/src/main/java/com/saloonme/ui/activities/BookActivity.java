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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.interfaces.IBookView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.request.PlaceOrderRequest;
import com.saloonme.model.response.BookingItemsResponse;
import com.saloonme.model.response.BookingItemsResponseData;
import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.ExpertsListResponseData;
import com.saloonme.model.response.PlaceOrderResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.ProfileResponseData;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.BookNowPresenter;
import com.saloonme.ui.adapters.PhotosAdapter;
import com.saloonme.ui.adapters.ProductsAdapter;
import com.saloonme.ui.adapters.ReviewsAdapter;
import com.saloonme.ui.adapters.SeatBookingAdapter;
import com.saloonme.ui.adapters.SelectBarbersAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookActivity extends BaseAppCompactActivity implements
        SelectBarbersAdapter.ItemListener, SeatBookingAdapter.ItemListener,
        ProductsAdapter.ItemListener, IBookView {
    private SelectBarbersAdapter selectBarbersAdapter;
    private SeatBookingAdapter seatBookingAdapter;
    private int tabPosition = 0;
    private ProductsAdapter productsAdapter;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private BookNowPresenter bookNowPresenter;
    private String saloonId, bookingId, totalPrice, userInstruction;
    private ExpertsListResponseData expertsListResponseData;
    private List<ExpertsListResponseData> expertsListResponseDataList;
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
    @BindView(R.id.rv_products)
    RecyclerView rv_products;
    /*@BindView(R.id.rv_seats)
    RecyclerView rv_seats;*/
    @BindView(R.id.tv_select_date)
    TextView tv_select_date;
    @BindView(R.id.tv_select_time)
    TextView tv_select_time;
    @BindView(R.id.iv_saloon)
    ImageView iv_saloon;
    @BindView(R.id.tv_saloon)
    TextView tv_saloon;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.iv_barber)
    ImageView iv_barber;
    @BindView(R.id.tv_barberName)
    TextView tv_barberName;
    @BindView(R.id.barber_rating)
    RatingBar barber_rating;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_address)
    EditText et_address;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.next)
    void onNextClick() {
        if (tabPosition == 0) {
            boolean validationAtStepOne = validateDataAtFirstStep();
            if (!validationAtStepOne) {
                return;
            }

        }
        if (next.getText().toString().equalsIgnoreCase(getString(R.string.next))) {
            tabPosition++;
        } else {
            // goToActivity(CheckOutActivity.class);
            // showToast("Redirect to payment gateway");
            PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
            placeOrderRequest.setBarberId(expertsListResponseData.getBarId());
            placeOrderRequest.setBookingDate(tv_select_date.getText().toString());
            placeOrderRequest.setBookingId(bookingId);
            placeOrderRequest.setSalonId(saloonId);
            placeOrderRequest.setTime(tv_select_time.getText().toString());
            placeOrderRequest.setUserId(PrefUtils.getInstance().getUserId());
            placeOrderRequest.setTotalPrice(totalPrice);
            placeOrderRequest.setUserInstruction(userInstruction);
            bookNowPresenter.placeOrder(placeOrderRequest);
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

                        tv_select_time.setText(hourOfDay + ":" + minute + ":00");
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
        bookNowPresenter = new BookNowPresenter(APIClient.getAPIService(), this);
        saloonId = getIntent().getStringExtra(StringConstants.EXTRA_DETAILS);
        if (ValidationUtil.isNullOrEmpty(saloonId)) {
            showToast("Something went wrong");
            finish();
        }
        setUpTabs();
        setBarbersRecyclerView();
        // setSeatSelectionRecycleView();
        setUpProductsRecyclerview();
        bookNowPresenter.getBarbersData(saloonId);
        bookNowPresenter.getSaloonDetails(saloonId);
        bookNowPresenter.getProfileDetails(PrefUtils.getInstance().getUserId(),
                PrefUtils.getInstance().geToken());
        bookNowPresenter.getProductDetails(PrefUtils.getInstance().getUserId());
    }

    private boolean validateDataAtFirstStep() {
        if (ValidationUtil.isNullOrEmpty(tv_select_date.getText().toString()) ||
                tv_select_date.getText().toString().equalsIgnoreCase
                        (getResources().getString(R.string.select_date))) {
            showToast("Please select Date");
            return false;
        }
        if (ValidationUtil.isNullOrEmpty(tv_select_time.getText().toString()) ||
                tv_select_time.getText().toString().equalsIgnoreCase
                        (getResources().getString(R.string.select_time))) {
            showToast("Please select time");
            return false;
        }
        if (expertsListResponseData == null) {
            showToast("Please select barber");
            return false;
        }
        return true;
    }

    private void setUpProductsRecyclerview() {
        productsAdapter = new ProductsAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_products.setLayoutManager(saloonListManager);
        rv_products.setAdapter(productsAdapter);
        rv_products.setNestedScrollingEnabled(false);
    }

    private void setSeatSelectionRecycleView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        seatBookingAdapter = new SeatBookingAdapter(getContext(), this);
       /* rv_seats.setLayoutManager(layoutManager);
        rv_seats.setAdapter(seatBookingAdapter);
        rv_seats.setNestedScrollingEnabled(false);*/
    }

    private void setBarbersRecyclerView() {
       /* selectBarbersAdapter = new SelectBarbersAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_barbers.setLayoutManager(saloonListManager);
        rv_barbers.setAdapter(selectBarbersAdapter);
        rv_barbers.setNestedScrollingEnabled(false);*/
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        seatBookingAdapter = new SeatBookingAdapter(getContext(), this);
        rv_barbers.setLayoutManager(layoutManager);
        rv_barbers.setAdapter(seatBookingAdapter);
        rv_barbers.setNestedScrollingEnabled(false);
    }

    private void setUpTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Step1"));
        tabLayout.addTab(tabLayout.newTab().setText("Step2"));
        tabLayout.addTab(tabLayout.newTab().setText("Step3"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    //check if user selected the barber time and date
                    boolean validationAtStepOne = validateDataAtFirstStep();
                    if (!validationAtStepOne) {
                        tabLayout.getTabAt(tab.getPosition() - 1).select();
                        return;
                    }
                }
                if (tab.getPosition() == 2) {
                    //check if user selected the barber time and date
                    boolean validationAtStepOne = validateDataAtFirstStep();
                    if (!validationAtStepOne) {
                        tabLayout.getTabAt(tab.getPosition() - 1).select();
                        return;
                    }
                }
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

    @Override
    public void getBarbersSuccess(ExpertsListResponse expertsListResponse) {
        if (expertsListResponse == null) {
            showToast("Failed to get the experts");
            seatBookingAdapter.setData(null);
        }
        if (expertsListResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(expertsListResponse.getMessage());
            seatBookingAdapter.setData(null);
        }
        if (expertsListResponse.getData() == null || expertsListResponse.getData().size() == 0) {
            showToast(expertsListResponse.getMessage());
            seatBookingAdapter.setData(null);
        }
        expertsListResponseDataList = expertsListResponse.getData();
        seatBookingAdapter.setData(expertsListResponse.getData());
    }

    @Override
    public void getBarbersFailed() {
        showToast("Failed to get the experts");
        seatBookingAdapter.setData(null);
    }

    @Override
    public void getSaloonDetailsSuccess(SaloonListResponse saloonListResponse) {
        if (saloonListResponse == null) {
            showToast("Failed to get the experts");
        }
        if (saloonListResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(saloonListResponse.getMessage());
        }
        if (saloonListResponse.getData() == null || saloonListResponse.getData().size() == 0) {
            showToast(saloonListResponse.getMessage());
        }
        setData(saloonListResponse.getData().get(0));
    }

    private void setData(SaloonListResponseData saloonListResponseData) {
        tv_saloon.setText(saloonListResponseData.getStoreName());
        tv_location.setText(saloonListResponseData.getAddress());
        Glide.with(this).load(APIConstants.IMAGE_BASE_URL + saloonListResponseData.getStoreImg())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iv_saloon);
    }

    @Override
    public void getSaloonDetailsFailed() {
        showToast("Failed to get the saloon details");
    }

    @Override
    public void getProfileDetailsSuccess(ProfileResponse profileResponse) {
        if (profileResponse == null) {
            showToast("Failed to get the profile details");
            return;
        }
        if (profileResponse.getStatus().contains("fail")) {
            showToast(profileResponse.getMessage());
            return;
        }
        if (profileResponse.getData() == null || profileResponse.getData().size() == 0) {
            showToast(profileResponse.getMessage());
            return;
        }
        setDataToUserDetailsTab(profileResponse.getData());
    }

    private void setDataToUserDetailsTab(List<ProfileResponseData> data) {
        ProfileResponseData profileResponseData = data.get(0);
        et_phone.setText(profileResponseData.getMobileNumber());
        et_name.setText(profileResponseData.getFirstName() + " " + profileResponseData.getLastName());
        et_email.setText(profileResponseData.getEmailAddress());
    }

    @Override
    public void getProfileDetailsFailed() {
        showToast("Failed to get the profile details");
    }

    @Override
    public void getProductDetailsSuccess(BookingItemsResponse bookingItemsResponse) {
        if (bookingItemsResponse == null) {
            showToast("Failed to get the product details");
            productsAdapter.setData(null);
            return;
        }
        if (bookingItemsResponse.getStatus().contains("fail")) {
            showToast(bookingItemsResponse.getMessage());
            productsAdapter.setData(null);
            return;
        }
        if (bookingItemsResponse.getData() == null || bookingItemsResponse.getData().size() == 0) {
            showToast(bookingItemsResponse.getMessage());
            productsAdapter.setData(null);
            return;
        }
        productsAdapter.setData(bookingItemsResponse.getData());
    }

    @Override
    public void getProductDetailsFailed() {
        showToast("Failed to get the product details");
        productsAdapter.setData(null);
    }

    @Override
    public void removeCartSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("Failed to remove from cart");
            return;
        }
        if (removeCartResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(removeCartResponse.getMessage());
            return;
        }
        bookNowPresenter.getProductDetails(PrefUtils.getInstance().getUserId());
    }

    @Override
    public void removeCartFailed() {
        showToast("Failed to remove from cart");
    }

    @Override
    public void placeOrderSuccess(PlaceOrderResponse placeOrderResponse) {
        if (placeOrderResponse == null) {
            showToast("Failed to palce order,try again later");
            return;
        }
        if (placeOrderResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(placeOrderResponse.getMessage());
            return;
        }
        if (placeOrderResponse.getData() == null) {
            showToast(placeOrderResponse.getMessage());
            return;
        }
        showToast(placeOrderResponse.getMessage());
    }

    @Override
    public void placeOrderFailed() {
        showToast("Failed to palce order,try again later");
    }

    @Override
    public void onBarberSelect(ExpertsListResponseData expertsListResponseData, int position) {
        this.expertsListResponseData = expertsListResponseData;
        setBarberData();
        for (ExpertsListResponseData expertsListResponseData1 : expertsListResponseDataList) {
            expertsListResponseData1.setSelected(false);
        }
        expertsListResponseDataList.get(position).setSelected(true);
        seatBookingAdapter.setData(expertsListResponseDataList);
    }

    private void setBarberData() {
        Glide.with(this).load(expertsListResponseData.getProfileImage())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iv_barber);
        tv_barberName.setText(expertsListResponseData.getName());
    }

    @Override
    public void onRemoveClick(BookingItemsResponseData bookingItemsResponseData) {
        bookNowPresenter.removeCart(PrefUtils.getInstance().getUserId(),
                bookingItemsResponseData.getServiceId());
    }
}