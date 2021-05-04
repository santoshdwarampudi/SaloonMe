package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserBookingDetailsResponseData implements Serializable {
    @SerializedName("upcoming_details")
    @Expose
    private List<UpcomingDetail> upcomingDetails = null;
    @SerializedName("completed_details")
    @Expose
    private List<CompletedDetail> completedDetails = null;
    @SerializedName("cancel_details")
    @Expose
    private List<CancelDetail> cancelDetails = null;

    public List<UpcomingDetail> getUpcomingDetails() {
        return upcomingDetails;
    }

    public void setUpcomingDetails(List<UpcomingDetail> upcomingDetails) {
        this.upcomingDetails = upcomingDetails;
    }

    public List<CompletedDetail> getCompletedDetails() {
        return completedDetails;
    }

    public void setCompletedDetails(List<CompletedDetail> completedDetails) {
        this.completedDetails = completedDetails;
    }

    public List<CancelDetail> getCancelDetails() {
        return cancelDetails;
    }

    public void setCancelDetails(List<CancelDetail> cancelDetails) {
        this.cancelDetails = cancelDetails;
    }

}
