package com.saloonme.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.interfaces.IProfileView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.request.CancelRequest;
import com.saloonme.model.response.CompletedDetail;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.ProfileResponseData;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.UpcomingDetail;
import com.saloonme.model.response.UserBookingDetailsResponse;
import com.saloonme.model.response.UserFeedPhotsResponse;
import com.saloonme.model.response.UserOrderDetailsResponse;
import com.saloonme.model.response.UserReviewsResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ProfilePresenter;
import com.saloonme.ui.activities.AddReviewActivity;
import com.saloonme.ui.activities.BookingDetailsActivity;
import com.saloonme.ui.activities.ChangePasswordActivity;
import com.saloonme.ui.activities.EditProfileActivity;
import com.saloonme.ui.adapters.HistoryAdapter;
import com.saloonme.ui.adapters.OrdersAdapter;
import com.saloonme.ui.adapters.ReviewsAdapter;
import com.saloonme.ui.adapters.UserFeedPhotosAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ProfileFragment extends BaseFragment implements HistoryAdapter.ItemListener,
        IProfileView, ReviewsAdapter.ItemListener {

    private HistoryAdapter historyAdapter, upComingBookingsAdapter;
    private ProfilePresenter profilePresenter;
    private Dialog cancelBooking, rescheduleBooking;
    private ReviewsAdapter reviewsAdapter;
    private UserFeedPhotosAdapter userFeedPhotosAdapter;
    private OrdersAdapter ordersAdapter;
    private ProfileResponseData profileResponseData;
    @BindView(R.id.bookingsTabs)
    TabLayout bookingsTabs;
    @BindView(R.id.rv_upcoming_bookings)
    RecyclerView rv_bookings;
    @BindView(R.id.rv_history)
    RecyclerView rv_history;
    @BindView(R.id.rv_user_feed_photos)
    RecyclerView rv_user_feed_photos;
    @BindView(R.id.rv_user_orders)
    RecyclerView rv_user_orders;
    private View view;
    @BindView(R.id.iv_profile)
    ImageView iv_profile;
    @BindView(R.id.tv_userName)
    TextView tv_userName;
    @BindView(R.id.tv_dob)
    TextView tv_dob;
    @BindView(R.id.rv_user_reviews)
    RecyclerView rv_user_reviews;
    @BindView(R.id.tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.profileTabs)
    TabLayout profileTabs;
    @BindView(R.id.tv_gender)
    TextView tv_gender;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.tv_edit)
    void onEditClick() {
        if (profileResponseData == null) {
            showToast("Something went wrong");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, profileResponseData);
        goToActivity(com.saloonme.ui.activities.EditProfileActivity.class, bundle);
    }

    @OnClick(R.id.tv_change_password)
    void onChangePasswordClick() {
        if (profileResponseData == null) {
            showToast("Something went wrong");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, profileResponseData);
        goToActivity(ChangePasswordActivity.class, bundle);
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        profilePresenter = new ProfilePresenter(APIClient.getAPIService(), this);
        setUpProfileTab();
        setUpTabs();
        setUpRecyclerviewForUpcoming(false);
        setUpRecyclerviewForHistory(true);
        setUpRecyclerViewForReviews();
        setUpRecyclerViewForFeedPhotos();
        setUpRecyclerViewForOrders();
        profilePresenter.getProfileDetails(PrefUtils.getInstance().getUserId(),
                PrefUtils.getInstance().geToken());
        profilePresenter.getUserBookingDetails(PrefUtils.getInstance().getUserId());
        profilePresenter.getUserReviews(PrefUtils.getInstance().getUserId());
        profilePresenter.getUserFeedPhotos(PrefUtils.getInstance().getUserId());
        profilePresenter.getOrderDetails(PrefUtils.getInstance().getUserId());
        return view;
    }


    private void setUpProfileTab() {
        profileTabs.addTab(profileTabs.newTab().setText("Photos"));
        profileTabs.addTab(profileTabs.newTab().setText("Bookings"));
        profileTabs.addTab(profileTabs.newTab().setText("Orders"));
        profileTabs.addTab(profileTabs.newTab().setText("My Reviews"));
        profileTabs.addTab(profileTabs.newTab().setText("Feed Profile Form"));
        profileTabs.selectTab(profileTabs.getTabAt(0));
        rv_user_feed_photos.setVisibility(View.VISIBLE);
        // bookingsTabs.setVisibility(View.VISIBLE);
        //rv_bookings.setVisibility(View.VISIBLE);
        rv_history.setVisibility(View.GONE);
        profileTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        bookingsTabs.setVisibility(View.GONE);
                        rv_bookings.setVisibility(View.GONE);
                        rv_history.setVisibility(View.GONE);
                        rv_user_feed_photos.setVisibility(View.VISIBLE);
                        rv_user_orders.setVisibility(View.GONE);
                        break;
                    case 1:
                        bookingsTabs.setVisibility(View.VISIBLE);
                        rv_bookings.setVisibility(View.VISIBLE);
                        rv_history.setVisibility(View.GONE);
                        rv_user_feed_photos.setVisibility(View.GONE);
                        rv_user_orders.setVisibility(View.GONE);
                        break;
                    case 2:
                        bookingsTabs.setVisibility(View.GONE);
                        rv_bookings.setVisibility(View.GONE);
                        rv_history.setVisibility(View.GONE);
                        rv_user_feed_photos.setVisibility(View.GONE);
                        rv_user_orders.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        bookingsTabs.setVisibility(View.GONE);
                        rv_bookings.setVisibility(View.GONE);
                        rv_history.setVisibility(View.GONE);
                        rv_user_feed_photos.setVisibility(View.GONE);
                        rv_user_orders.setVisibility(View.GONE);
                        break;
                    case 4:
                        bookingsTabs.setVisibility(View.GONE);
                        rv_bookings.setVisibility(View.GONE);
                        rv_history.setVisibility(View.GONE);
                        rv_user_feed_photos.setVisibility(View.GONE);
                        rv_user_orders.setVisibility(View.GONE);
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

    private void setUpRecyclerviewForHistory(boolean isHistory) {

        historyAdapter = new HistoryAdapter(getActivity(), this, isHistory);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_history.setLayoutManager(saloonListManager);
        rv_history.setAdapter(historyAdapter);
        rv_history.setNestedScrollingEnabled(false);
    }

    private void setUpRecyclerviewForUpcoming(boolean isHistory) {

        upComingBookingsAdapter = new HistoryAdapter(getActivity(), this, isHistory);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_bookings.setLayoutManager(saloonListManager);
        rv_bookings.setAdapter(upComingBookingsAdapter);
        rv_bookings.setNestedScrollingEnabled(false);
    }

    private void setUpRecyclerViewForReviews() {
        reviewsAdapter = new ReviewsAdapter(getActivity(), this, true);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_user_reviews.setLayoutManager(saloonListManager);
        rv_user_reviews.setAdapter(reviewsAdapter);
        rv_user_reviews.setNestedScrollingEnabled(false);
    }

    private void setUpRecyclerViewForFeedPhotos() {
        userFeedPhotosAdapter = new UserFeedPhotosAdapter(getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rv_user_feed_photos.setLayoutManager(layoutManager);
        rv_user_feed_photos.setAdapter(userFeedPhotosAdapter);
        rv_user_feed_photos.setNestedScrollingEnabled(false);
    }

    private void setUpRecyclerViewForOrders() {
        ordersAdapter = new OrdersAdapter(getActivity());
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_user_orders.setLayoutManager(saloonListManager);
        rv_user_orders.setAdapter(ordersAdapter);
        rv_user_orders.setNestedScrollingEnabled(false);
    }


    private void setUpTabs() {
        bookingsTabs.addTab(bookingsTabs.newTab().setText("UpComing Bookings"));
        bookingsTabs.addTab(bookingsTabs.newTab().setText("History"));
        bookingsTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        rv_bookings.setVisibility(View.VISIBLE);
                        rv_history.setVisibility(View.GONE);
                        break;
                    case 1:
                        rv_history.setVisibility(View.VISIBLE);
                        rv_bookings.setVisibility(View.GONE);
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
        setData(profileResponse.getData());

    }

    private void setData(List<ProfileResponseData> data) {
        profileResponseData = data.get(0);
        if (profileResponseData != null) {
            Glide.with(getActivity()).load(
                    profileResponseData.getProfilePic())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .error(R.drawable.ic_person)
                    .into(iv_profile);
            tv_userName.setText(profileResponseData.getFirstName() + " " + profileResponseData.getLastName());
            tv_mobile.setText("Mobile : " + profileResponseData.getMobileNumber());
            tv_email.setText("Email : " + profileResponseData.getEmailAddress());
            if (profileResponseData.getGender().equals("1")) {
                tv_gender.setText("Gender : Male");
            } else if (profileResponseData.getGender().equals("2")) {
                tv_gender.setText("Gender : FeMale");
            } else {
                tv_gender.setText("Gender : Male");
            }

        }
    }

    @Override
    public void getProfileDetailsFailed() {
        showToast("Failed to get the profile details");
    }

    @Override
    public void getUserBookingDetailsSuccess(UserBookingDetailsResponse userBookingDetailsResponse) {
        if (userBookingDetailsResponse == null) {
            showToast("No Bookings found");
            return;
        }
        if (userBookingDetailsResponse.getStatus().contains("fail")) {
            showToast(userBookingDetailsResponse.getMessage());
            return;
        }
        if (userBookingDetailsResponse.getData() == null) {
            showToast(userBookingDetailsResponse.getMessage());
            return;
        }
        if (userBookingDetailsResponse.getData().getCompletedDetails() != null &&
                userBookingDetailsResponse.getData().getCompletedDetails().size() > 0) {
            historyAdapter.setDataToHistory(userBookingDetailsResponse.getData().
                    getCompletedDetails());
        } else {
            historyAdapter.setDataToHistory(null);
        }
        if (userBookingDetailsResponse.getData().getUpcomingDetails() != null &&
                userBookingDetailsResponse.getData().getUpcomingDetails().size() > 0) {
            upComingBookingsAdapter.setDataToUpComing(userBookingDetailsResponse.getData().
                    getUpcomingDetails());
        } else {
            upComingBookingsAdapter.setDataToUpComing(null);
        }

    }

    @Override
    public void getUserBookingDetailsFailed() {
        showToast("No Booking found");
    }

    @Override
    public void cancelBookingSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("Failed to cancel the order");
            return;
        }
        if (removeCartResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(removeCartResponse.getMessage());
            return;
        }
        showToast(removeCartResponse.getMessage());
        profilePresenter.getUserBookingDetails(PrefUtils.getInstance().getUserId());

    }

    @Override
    public void cancelBookingFailed() {
        showToast("Failed to cancel the order");
    }

    @Override
    public void reschduleBookingSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("Failed to reschedule the order");
            return;
        }
        if (removeCartResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(removeCartResponse.getMessage());
            return;
        }
        showToast(removeCartResponse.getMessage());
        profilePresenter.getUserBookingDetails(PrefUtils.getInstance().getUserId());
    }

    @Override
    public void reschduleBookingFailed() {
        showToast("Failed to reschedule the order");
    }

    @Override
    public void getUserReviewsSuccess(UserReviewsResponse userReviewsResponse) {
        if (userReviewsResponse == null) {
            return;
        }
        if (userReviewsResponse.getStatus().toLowerCase().contains("fail")) {
            return;
        }
        if (userReviewsResponse.getData() == null || userReviewsResponse.getData().size() == 0) {
            return;
        }
        reviewsAdapter.setDataToUserReviews(userReviewsResponse.getData());
    }

    @Override
    public void getUserReviewsFailed() {

    }

    @Override
    public void getUserFeedPhotsSuccess(UserFeedPhotsResponse userFeedPhotsResponse) {
        if (userFeedPhotsResponse == null) {
            return;
        }
        if (userFeedPhotsResponse.getStatus().toLowerCase().contains("fail")) {
            return;
        }
        if (userFeedPhotsResponse.getData() == null || userFeedPhotsResponse.getData().size() == 0) {
            return;
        }
        userFeedPhotosAdapter.setData(userFeedPhotsResponse.getData());
    }

    @Override
    public void getUserFeedPhotsFailed() {

    }

    @Override
    public void getUserOrderDetailSuccess(UserOrderDetailsResponse userFeedPhotsResponse) {
        if (userFeedPhotsResponse == null) {
            return;
        }
        if (userFeedPhotsResponse.getStatus().toLowerCase().contains("fail")) {
            return;
        }
        if (userFeedPhotsResponse.getData() == null || userFeedPhotsResponse.getData().size() == 0) {
            return;
        }
        ordersAdapter.setDataToOrders(userFeedPhotsResponse.getData());
    }

    @Override
    public void getUserOrderDetailFailed() {

    }

    @Override
    public void onAddReviewClick(CompletedDetail completedDetail) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, completedDetail);
        goToActivity(AddReviewActivity.class, bundle);
    }

    @Override
    public void onCancelClick(UpcomingDetail upcomingDetail) {
        cancelBooking = new Dialog(getActivity());
        cancelBooking.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cancelBooking.setCancelable(true);
        cancelBooking.setContentView(R.layout.dialog_cancel_booking);
        cancelBooking.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        EditText et_cancel_reason = cancelBooking.findViewById(R.id.et_cancel_reason);
        TextView tv_booking_amount = cancelBooking.findViewById(R.id.tv_booking_amount);
        TextView tv_booking_time_date = cancelBooking.findViewById(R.id.tv_booking_time_date);
        tv_booking_time_date.setText("Booking Date Time : " + upcomingDetail.getServiceDate() + " "
                + upcomingDetail.getServiceTime());
        tv_booking_amount.setText("Refund Booking Amount Rs." + upcomingDetail.getTotalPrice());
        cancelBooking.findViewById(R.id.tv_cancel_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationUtil.isNullOrEmpty(et_cancel_reason.getText().toString())) {
                    showToast("Please enter reason for cancel");
                    return;
                }
                CancelRequest cancelRequest = new CancelRequest();
                cancelRequest.setBookingId(upcomingDetail.getBookingId());
                cancelRequest.setCancelReason(et_cancel_reason.getText().toString());
                profilePresenter.cancelBooking(cancelRequest);
                cancelBooking.dismiss();
            }
        });
        cancelBooking.findViewById(R.id.tv_clsoe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelBooking.dismiss();
            }
        });
        cancelBooking.show();
    }

    @Override
    public void onRescheduleClick(UpcomingDetail upcomingDetail) {
        rescheduleBooking = new Dialog(getActivity());
        rescheduleBooking.requestWindowFeature(Window.FEATURE_NO_TITLE);
        rescheduleBooking.setCancelable(true);
        rescheduleBooking.setContentView(R.layout.dialog_reschdule);
        rescheduleBooking.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView tv_booking_time_date = rescheduleBooking.findViewById(R.id.tv_booking_time_date);
        EditText tv_select_date = rescheduleBooking.findViewById(R.id.tv_select_date);
        EditText tv_select_time = rescheduleBooking.findViewById(R.id.tv_select_time);
        tv_booking_time_date.setText("Booking Date Time : " + upcomingDetail.getServiceDate() + " "
                + upcomingDetail.getServiceTime());
        rescheduleBooking.findViewById(R.id.tv_clsoe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rescheduleBooking.dismiss();
            }
        });
        rescheduleBooking.findViewById(R.id.tv_reschdule_order).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ValidationUtil.isNullOrEmpty(tv_select_date.getText().toString())) {
                            showToast("Please select date");
                            return;
                        }
                        if (ValidationUtil.isNullOrEmpty(tv_select_time.getText().toString())) {
                            showToast("Please select time");
                            return;
                        }
                        rescheduleBooking.dismiss();
                        profilePresenter.rescheduleBooking(upcomingDetail.getBookingId(),
                                tv_select_date.getText().toString(),
                                tv_select_time.getText().toString());
                    }
                });
        tv_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tv_select_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        tv_select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tv_select_time.setText(hourOfDay + ":" + minute + ":00");
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        rescheduleBooking.show();
    }

    @Override
    public void onViewBookingClick(UpcomingDetail upcomingDetail) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, upcomingDetail);
        goToActivity(BookingDetailsActivity.class, bundle);
    }
}