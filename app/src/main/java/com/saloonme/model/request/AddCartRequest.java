package com.saloonme.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddCartRequest implements Serializable {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("sub_service_id")
    @Expose
    private List<String> subServiceId = null;
    @SerializedName("salon_id")
    @Expose
    private String salonId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(List<String> subServiceId) {
        this.subServiceId = subServiceId;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

}
