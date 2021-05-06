package com.saloonme.ui.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ProfilePresenter;
import com.saloonme.ui.activities.AddReviewActivity;
import com.saloonme.ui.adapters.HistoryAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.util.List;

import butterknife.BindView;


public class ProfileFragment extends BaseFragment implements HistoryAdapter.ItemListener, IProfileView {

    private HistoryAdapter historyAdapter, upComingBookingsAdapter;
    private ProfilePresenter profilePresenter;
    private Dialog cancelBooking;
    @BindView(R.id.bookingsTabs)
    TabLayout bookingsTabs;
    @BindView(R.id.rv_upcoming_bookings)
    RecyclerView rv_bookings;
    @BindView(R.id.rv_history)
    RecyclerView rv_history;
    private View view;
    @BindView(R.id.iv_profile)
    ImageView iv_profile;
    @BindView(R.id.tv_userName)
    TextView tv_userName;
    @BindView(R.id.tv_dob)
    TextView tv_dob;
    @BindView(R.id.tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.tv_email)
    TextView tv_email;

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
        profilePresenter = new ProfilePresenter(APIClient.getAPIService(), this);
        setUpTabs();
        setUpRecyclerviewForUpcoming(false);
        setUpRecyclerviewForHistory(true);
        profilePresenter.getProfileDetails(PrefUtils.getInstance().getUserId(),
                PrefUtils.getInstance().geToken());
        profilePresenter.getUserBookingDetails(PrefUtils.getInstance().getUserId());
        return view;
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
        ProfileResponseData profileResponseData = data.get(0);
        if (profileResponseData != null) {
            Glide.with(getActivity()).load(APIConstants.IMAGE_BASE_URL +
                    profileResponseData.getProfilePic())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_profile);
            tv_userName.setText(profileResponseData.getFirstName() + " " + profileResponseData.getLastName());
            tv_mobile.setText("Mobile : " + profileResponseData.getMobileNumber());
            tv_email.setText("Email : " + profileResponseData.getEmailAddress());
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
        }
        if (userBookingDetailsResponse.getData().getUpcomingDetails() != null &&
                userBookingDetailsResponse.getData().getUpcomingDetails().size() > 0) {
            upComingBookingsAdapter.setDataToUpComing(userBookingDetailsResponse.getData().
                    getUpcomingDetails());
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
    public void onAddReviewClick(CompletedDetail completedDetail) {
        Bundle bundle=new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS,completedDetail);
        goToActivity(AddReviewActivity.class,bundle);
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
    public void onReschduleClick(UpcomingDetail upcomingDetail) {

    }

    @Override
    public void onViewBookingClick(UpcomingDetail upcomingDetail) {

    }
}