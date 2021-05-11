package com.saloonme.interfaces;

import com.saloonme.model.response.BookingDetailsResponse;

public interface IBookingDetailsView extends IActivityBaseView {
    void onBookingDetailsSuccess(BookingDetailsResponse bookingDetailsResponse);

    void onBookingDetailsFailed();
}
