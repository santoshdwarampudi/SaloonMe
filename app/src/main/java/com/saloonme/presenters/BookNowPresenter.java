package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IBookView;
import com.saloonme.model.response.BookingItemsResponse;
import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookNowPresenter {
    private ApiInterface apiInterface;
    private IBookView iBookView;

    public BookNowPresenter(ApiInterface apiInterface, IBookView iBookView) {
        this.apiInterface = apiInterface;
        this.iBookView = iBookView;
    }

    public void getBarbersData(String saloonId) {
        iBookView.showProgressDialog("Getting experts...");
        Call<ExpertsListResponse> expertsListResponseCall = apiInterface.getExperts(saloonId);
        expertsListResponseCall.enqueue(new Callback<ExpertsListResponse>() {
            @Override
            public void onResponse(Call<ExpertsListResponse> call, Response<ExpertsListResponse> response) {
                iBookView.dismissProgress();
                iBookView.getBarbersSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ExpertsListResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.getBarbersFailed();
            }
        });
    }

    public void getSaloonDetails(String saloonId) {
        iBookView.showProgressDialog("Getting experts...");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.getSaloonDetails(saloonId);
        saloonListResponseCall.enqueue(new Callback<SaloonListResponse>() {
            @Override
            public void onResponse(Call<SaloonListResponse> call, Response<SaloonListResponse> response) {
                iBookView.dismissProgress();
                iBookView.getSaloonDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonListResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.getSaloonDetailsFailed();
            }
        });
    }

    public void getProfileDetails(String userId, String token) {
        iBookView.showProgressDialog("Getting Profile details....");
        Call<ProfileResponse> profileResponseCall = apiInterface.getProfile(userId, token);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                iBookView.dismissProgress();
                iBookView.getProfileDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.getProfileDetailsFailed();
            }
        });
    }

    public void getProductDetails(String userId) {
        iBookView.showProgressDialog("Getting Product details....");
        Call<BookingItemsResponse> bookingItemsResponseCall = apiInterface.getBookingItems(userId);
        bookingItemsResponseCall.enqueue(new Callback<BookingItemsResponse>() {
            @Override
            public void onResponse(Call<BookingItemsResponse> call, Response<BookingItemsResponse> response) {
                iBookView.dismissProgress();
                iBookView.getProductDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookingItemsResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.getProductDetailsFailed();
            }
        });
    }

    public void removeCart(String userId, String serviceId) {
        iBookView.showProgressDialog("Removing from cart...");
        Call<RemoveCartResponse> removeCartResponseCall = apiInterface.deleteCartItem
                (serviceId, userId);
        removeCartResponseCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iBookView.dismissProgress();
                iBookView.removeCartSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.removeCartFailed();
            }
        });
    }
}
