package com.saloonme.ui.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.interfaces.IBookView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.request.PlaceOrderRequest;
import com.saloonme.model.response.BookingItemsResponse;
import com.saloonme.model.response.BookingItemsResponseData;
import com.saloonme.model.response.CheckCouponResponse;
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
import com.saloonme.ui.adapters.ProductsAdapter;
import com.saloonme.ui.adapters.SeatBookingAdapter;
import com.saloonme.ui.adapters.SelectBarbersAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookActivity extends BaseAppCompactActivity implements
        SelectBarbersAdapter.ItemListener, SeatBookingAdapter.ItemListener,
        ProductsAdapter.ItemListener, IBookView, PaymentResultListener {
    private static final String TAG = "BookActivity";
    private SeatBookingAdapter seatBookingAdapter;
    private int tabPosition = 0, serviceDuration = 0;
    private ProductsAdapter productsAdapter;
    private BookNowPresenter bookNowPresenter;
    private String saloonId;
    private String bookingId;
    private String discount_price;
    private ExpertsListResponseData expertsListResponseData;
    private List<ExpertsListResponseData> expertsListResponseDataList;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bookTab)
    TabLayout tabLayout;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.book1)
    NestedScrollView book1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.book2)
    ConstraintLayout book2;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.book3)
    ConstraintLayout book3;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_barbers)
    RecyclerView rv_barbers;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.previous)
    TextView previous;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.next)
    TextView next;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_products)
    RecyclerView rv_products;
    /*@BindView(R.id.rv_seats)
    RecyclerView rv_seats;*/
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_select_date)
    TextView tv_select_date;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_select_time)
    TextView tv_select_time;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_saloon)
    ImageView iv_saloon;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_saloon)
    TextView tv_saloon;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_location)
    TextView tv_location;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_barber)
    ImageView iv_barber;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_barberName)
    TextView tv_barberName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.barber_rating)
    RatingBar barber_rating;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_name)
    EditText et_name;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_email)
    EditText et_email;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_phone)
    EditText et_phone;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_address)
    EditText et_address;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_discount_value)
    TextView tv_discount_value;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_total_payment_value)
    TextView tv_total_payment_value;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_payment_value)
    TextView tv_payment_value;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_booking_slot_value)
    TextView tv_booking_slot_value;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.instructions_et)
    EditText instructions_et;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_booking_slot)
    TextView tv_booking_slot;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.apply_btn)
    TextView apply_btn;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_coupon_code)
    EditText et_coupon_code;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_booking_date_time)
    TextView tv_booking_date_time;

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.apply_btn)
    void onCouponButtonClick() {
        if (ValidationUtil.isNullOrEmpty(et_coupon_code.getText().toString())) {
            showToast("Please enter coupon code");
            return;
        }
        bookNowPresenter.applyCoupon(saloonId, et_coupon_code.getText().toString());
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @SuppressLint("NonConstantResourceId")
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
            startPayment();
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

    private void startPayment() {
        final Activity activity = this;

        final Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "SaloonMe");
            options.put("description", "Payment");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            double amount = Double.parseDouble(tv_total_payment_value.getText().toString());
            options.put("amount", amount*100);

            JSONObject preFill = new JSONObject();
            preFill.put("email", PrefUtils.getInstance().geEmailId());
            preFill.put("contact", PrefUtils.getInstance().getMobileNumber());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
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

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_select_date)
    void onDateSelect() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {

                    tv_select_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    checkDateAndTimeSelected();
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_select_time)
    void onTimeSelect() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {

                    tv_select_time.setText(hourOfDay + ":" + minute + ":00");
                    checkDateAndTimeSelected();
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
        tv_heading.setText(R.string.book_now);
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

        Checkout.preload(getApplicationContext());
    }

    private void checkDateAndTimeSelected() {
        if (!tv_select_date.getText().toString().equalsIgnoreCase(getString(R.string.select_date)) &&
                !tv_select_time.getText().toString().equalsIgnoreCase(getString(R.string.select_time))) {
            showDuration();
            tv_booking_date_time.setText("Booking Date and time : " +
                    tv_select_date.getText().toString() + "  " +
                    tv_select_time.getText().toString());
        }
    }

    private void showDuration() {
        try {
            String selectedDate = tv_select_date.getText().toString() + " " +
                    tv_select_time.getText().toString();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date date1 = df.parse(selectedDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            cal.add(Calendar.MINUTE, serviceDuration);
            String newTime = df.format(cal.getTime());
            tv_booking_slot_value.setText(selectedDate + " to " + newTime);
            tv_booking_slot.setVisibility(View.VISIBLE);
            tv_booking_slot_value.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
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
       /* if (expertsListResponseData == null) {
            showToast("Please select barber");
            return false;
        }*/
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

    private void setBarbersRecyclerView() {
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
                    if (!PrefUtils.getInstance().isLogin()) {
                        showToast("Login to book the order");
                        goToActivity(LoginActivity.class);
                        return;
                    }
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
            return;
        }
        if (!ValidationUtil.isNullOrEmpty(expertsListResponse.getStatus()) &&
                expertsListResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(expertsListResponse.getMessage());
            seatBookingAdapter.setData(null);
            return;
        }
        if (expertsListResponse.getData() == null || expertsListResponse.getData().size() == 0) {
            showToast(expertsListResponse.getMessage());
            seatBookingAdapter.setData(null);
            return;
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
        bookingId = bookingItemsResponse.getData().get(0).getBookingId();
        productsAdapter.setData(bookingItemsResponse.getData());
        getDurationInMinutes(bookingItemsResponse.getData());
        tv_payment_value.setText(bookingItemsResponse.getTotalPrice());
        tv_discount_value.setText(bookingItemsResponse.getOverallDiscount());
        double totalPayment = Double.parseDouble(bookingItemsResponse.getTotalPrice()) -
                Double.parseDouble(bookingItemsResponse.getOverallDiscount());
        tv_total_payment_value.setText(totalPayment + "");
    }

    private void getDurationInMinutes(List<BookingItemsResponseData> data) {
        for (BookingItemsResponseData bookingItemsResponseData : data) {
            serviceDuration = serviceDuration + Integer.
                    parseInt(bookingItemsResponseData.getServiceDuration());
        }
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
        finishAffinity();
        goToActivity(MainActivity.class);
    }

    @Override
    public void placeOrderFailed() {
        showToast("Failed to palce order,try again later");
    }

    @Override
    public void applyCouponSuccess(CheckCouponResponse checkCouponResponse) {
        if (checkCouponResponse == null) {
            showToast("Invalid coupon code,try again later");
            et_coupon_code.setText("");
            return;
        }
        if (!ValidationUtil.isNullOrEmpty(checkCouponResponse.getStatus()) &&
                checkCouponResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(checkCouponResponse.getMessage());
            et_coupon_code.setText("");
            return;
        }
        discount_price = checkCouponResponse.getDiscountPrice();
    }

    @Override
    public void applyCouponFailed() {
        showToast("Invalid coupon code,try again later");
        et_coupon_code.setText("");
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

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " +
                    razorpayPaymentID, Toast.LENGTH_SHORT).show();
            PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
            if (expertsListResponseData != null)
                placeOrderRequest.setBarberId(expertsListResponseData.getBarId());
            else
                placeOrderRequest.setBarberId("");
            placeOrderRequest.setBookingDate(tv_select_date.getText().toString());
            placeOrderRequest.setBookingId(bookingId);
            placeOrderRequest.setSalonId(saloonId);
            placeOrderRequest.setTime(tv_select_time.getText().toString());
            placeOrderRequest.setUserId(PrefUtils.getInstance().getUserId());
            placeOrderRequest.setTotalPrice(tv_total_payment_value.getText().toString());
            placeOrderRequest.setUserInstruction(instructions_et.getText().toString());
            placeOrderRequest.setCoupon_code(et_coupon_code.getText().toString());
            placeOrderRequest.setCoupon_discount_price(discount_price);
            bookNowPresenter.placeOrder(placeOrderRequest);
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " +
                    response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}