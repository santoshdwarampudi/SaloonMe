package com.saloonme.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReviewRequest implements Serializable {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("booking_id")
    @Expose
    private String bookingId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

}
