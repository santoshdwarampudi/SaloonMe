package com.saloonme.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaceOrderRequest implements Serializable {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("salon_id")
    @Expose
    private String salonId;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("barber_id")
    @Expose
    private String barberId;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("coupon_code")
    @Expose
    private String coupon_code;
    @SerializedName("coupon_discount_price")
    @Expose
    private String coupon_discount_price;
    @SerializedName("user_instruction")
    @Expose
    private String userInstruction;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
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

    public String getBarberId() {
        return barberId;
    }

    public void setBarberId(String barberId) {
        this.barberId = barberId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserInstruction() {
        return userInstruction;
    }

    public void setUserInstruction(String userInstruction) {
        this.userInstruction = userInstruction;
    }

    public String getCoupon_discount_price() {
        return coupon_discount_price;
    }

    public void setCoupon_discount_price(String coupon_discount_price) {
        this.coupon_discount_price = coupon_discount_price;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }
}
