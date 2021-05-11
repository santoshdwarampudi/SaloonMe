package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookingDetailsResponseData implements Serializable {
    @SerializedName("booking_services_list")
    @Expose
    private List<BookingServices> bookingServicesList = null;
    @SerializedName("get_user_details")
    @Expose
    private GetUserDetails getUserDetails;

    public List<BookingServices> getBookingServicesList() {
        return bookingServicesList;
    }

    public void setBookingServicesList(List<BookingServices> bookingServicesList) {
        this.bookingServicesList = bookingServicesList;
    }

    public GetUserDetails getGetUserDetails() {
        return getUserDetails;
    }

    public void setGetUserDetails(GetUserDetails getUserDetails) {
        this.getUserDetails = getUserDetails;
    }

}
