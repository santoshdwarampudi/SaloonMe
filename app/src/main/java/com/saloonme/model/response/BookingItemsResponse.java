package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookingItemsResponse implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<BookingItemsResponseData> data = null;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("overall_discount")
    @Expose
    private String overallDiscount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingItemsResponseData> getData() {
        return data;
    }

    public void setData(List<BookingItemsResponseData> data) {
        this.data = data;
    }

    public String getOverallDiscount() {
        return overallDiscount;
    }

    public void setOverallDiscount(String overallDiscount) {
        this.overallDiscount = overallDiscount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
