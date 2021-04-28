package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SaloonReviewResponseData implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("review_comments")
    @Expose
    private String reviewComments;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review_date")
    @Expose
    private String reviewDate;
    @SerializedName("salon_id")
    @Expose
    private String salonId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
