package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpcomingDetail implements Serializable {
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("salon_id")
    @Expose
    private String salonId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("booking_time")
    @Expose
    private String bookingTime;
    @SerializedName("service_date")
    @Expose
    private String serviceDate;
    @SerializedName("service_time")
    @Expose
    private String serviceTime;
    @SerializedName("reason")
    @Expose
    private Object reason;
    @SerializedName("cancel_date_time")
    @Expose
    private Object cancelDateTime;
    @SerializedName("booking_no")
    @Expose
    private String bookingNo;
    @SerializedName("service_end_time")
    @Expose
    private String serviceEndTime;
    @SerializedName("service_start_time")
    @Expose
    private String serviceStartTime;
    @SerializedName("user_instruction")
    @Expose
    private String userInstruction;
    @SerializedName("barber_id")
    @Expose
    private String barberId;
    @SerializedName("booking_otp")
    @Expose
    private String bookingOtp;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("salon_name")
    @Expose
    private String salonName;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public Object getCancelDateTime() {
        return cancelDateTime;
    }

    public void setCancelDateTime(Object cancelDateTime) {
        this.cancelDateTime = cancelDateTime;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getUserInstruction() {
        return userInstruction;
    }

    public void setUserInstruction(String userInstruction) {
        this.userInstruction = userInstruction;
    }

    public String getBarberId() {
        return barberId;
    }

    public void setBarberId(String barberId) {
        this.barberId = barberId;
    }

    public String getBookingOtp() {
        return bookingOtp;
    }

    public void setBookingOtp(String bookingOtp) {
        this.bookingOtp = bookingOtp;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }
}
