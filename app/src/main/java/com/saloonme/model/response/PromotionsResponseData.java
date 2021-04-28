package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PromotionsResponseData implements Serializable {
    @SerializedName("promo_id")
    @Expose
    private String promoId;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("promo_title")
    @Expose
    private String promoTitle;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("no_of_times")
    @Expose
    private String noOfTimes;
    @SerializedName("promo_type")
    @Expose
    private String promoType;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("promo_image")
    @Expose
    private String promoImage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("our_commission")
    @Expose
    private String ourCommission;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("updated_by")
    @Expose
    private Object updatedBy;
    @SerializedName("updated_date")
    @Expose
    private Object updatedDate;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;
    @SerializedName("status")
    @Expose
    private String status;

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPromoTitle() {
        return promoTitle;
    }

    public void setPromoTitle(String promoTitle) {
        this.promoTitle = promoTitle;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNoOfTimes() {
        return noOfTimes;
    }

    public void setNoOfTimes(String noOfTimes) {
        this.noOfTimes = noOfTimes;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPromoImage() {
        return promoImage;
    }

    public void setPromoImage(String promoImage) {
        this.promoImage = promoImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOurCommission() {
        return ourCommission;
    }

    public void setOurCommission(String ourCommission) {
        this.ourCommission = ourCommission;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
