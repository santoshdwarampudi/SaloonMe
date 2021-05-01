package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddCartResponseData implements Serializable {
    @SerializedName("booking_id")
    @Expose
    private List<String> bookingId = null;
    @SerializedName("booking_service_id")
    @Expose
    private List<Integer> bookingServiceId = null;

    public List<String> getBookingId() {
        return bookingId;
    }

    public void setBookingId(List<String> bookingId) {
        this.bookingId = bookingId;
    }

    public List<Integer> getBookingServiceId() {
        return bookingServiceId;
    }

    public void setBookingServiceId(List<Integer> bookingServiceId) {
        this.bookingServiceId = bookingServiceId;
    }
}
