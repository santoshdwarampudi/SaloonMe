package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CommentListResponse implements Serializable
{

    @SerializedName("feed_details")
    @Expose
    private List<FeedDetail> feedDetails = null;
    @SerializedName("feed_comments")
    @Expose
    private List<FeedComment> feedComments = null;

    public List<FeedDetail> getFeedDetails() {
        return feedDetails;
    }

    public void setFeedDetails(List<FeedDetail> feedDetails) {
        this.feedDetails = feedDetails;
    }

    public List<FeedComment> getFeedComments() {
        return feedComments;
    }

    public void setFeedComments(List<FeedComment> feedComments) {
        this.feedComments = feedComments;
    }
}
