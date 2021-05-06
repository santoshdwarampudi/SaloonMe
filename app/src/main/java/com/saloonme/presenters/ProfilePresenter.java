package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IProfileView;
import com.saloonme.model.request.CancelRequest;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.UserBookingDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private ApiInterface apiInterface;
    private IProfileView iProfileView;

    public ProfilePresenter(ApiInterface apiInterface, IProfileView iProfileView) {
        this.apiInterface = apiInterface;
        this.iProfileView = iProfileView;
    }

    public void getProfileDetails(String userId, String token) {
        iProfileView.showProgressDialog("Getting Profile details....");
        Call<ProfileResponse> profileResponseCall = apiInterface.getProfile(userId, token);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getProfileDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getProfileDetailsFailed();
            }
        });
    }

    public void getUserBookingDetails(String userId) {
        iProfileView.showProgressDialog("Getting Booking details....");
        Call<UserBookingDetailsResponse> userBookingDetailsResponseCall = apiInterface.
                getUserBookings(userId);
        userBookingDetailsResponseCall.enqueue(new Callback<UserBookingDetailsResponse>() {
            @Override
            public void onResponse(Call<UserBookingDetailsResponse> call, Response<UserBookingDetailsResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getUserBookingDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserBookingDetailsResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getUserBookingDetailsFailed();
            }
        });
    }

    public void cancelBooking(CancelRequest cancelRequest) {
        iProfileView.showProgressDialog("Cacelling Booking....");
        Call<RemoveCartResponse> cancelBookingCall = apiInterface.cancelBooking(cancelRequest);
        cancelBookingCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.cancelBookingSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.cancelBookingFailed();
            }
        });
    }
}
