package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IBookingDetailsView;
import com.saloonme.model.response.BookingDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetailsPresenter {
    private ApiInterface apiInterface;
    private IBookingDetailsView iBookingDetailsView;

    public BookingDetailsPresenter(ApiInterface apiInterface, IBookingDetailsView
            iBookingDetailsView) {
        this.apiInterface = apiInterface;
        this.iBookingDetailsView = iBookingDetailsView;
    }

    public void getBookingDetails(String bookingId) {
        iBookingDetailsView.showProgressDialog("Loading...");
        Call<BookingDetailsResponse> bookingDetailsResponseCall = apiInterface.
                getBookingDetails(bookingId);
        bookingDetailsResponseCall.enqueue(new Callback<BookingDetailsResponse>() {
            @Override
            public void onResponse(Call<BookingDetailsResponse> call, Response<BookingDetailsResponse> response) {
                iBookingDetailsView.dismissProgress();
                iBookingDetailsView.onBookingDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookingDetailsResponse> call, Throwable t) {
                iBookingDetailsView.dismissProgress();
                iBookingDetailsView.onBookingDetailsFailed();
            }
        });
    }
}
