package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FeedComment implements Serializable
{

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("feed_comments_sno")
    @Expose
    private String feedCommentsSno;
    @SerializedName("feed_comment")
    @Expose
    private String feedComment;
    @SerializedName("feed_user_id")
    @Expose
    private String feedUserId;
    @SerializedName("feed_created_date")
    @Expose
    private String feedCreatedDate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("comment_replay_id")
    @Expose
    private Object commentReplayId;
    @SerializedName("feed_id")
    @Expose
    private String feedId;
    @SerializedName("feed_comments_replay")
    @Expose
    private List<Object> feedCommentsReplay = null;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getFeedCommentsSno() {
        return feedCommentsSno;
    }

    public void setFeedCommentsSno(String feedCommentsSno) {
        this.feedCommentsSno = feedCommentsSno;
    }

    public String getFeedComment() {
        return feedComment;
    }

    public void setFeedComment(String feedComment) {
        this.feedComment = feedComment;
    }

    public String getFeedUserId() {
        return feedUserId;
    }

    public void setFeedUserId(String feedUserId) {
        this.feedUserId = feedUserId;
    }

    public String getFeedCreatedDate() {
        return feedCreatedDate;
    }

    public void setFeedCreatedDate(String feedCreatedDate) {
        this.feedCreatedDate = feedCreatedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCommentReplayId() {
        return commentReplayId;
    }

    public void setCommentReplayId(Object commentReplayId) {
        this.commentReplayId = commentReplayId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public List<Object> getFeedCommentsReplay() {
        return feedCommentsReplay;
    }

    public void setFeedCommentsReplay(List<Object> feedCommentsReplay) {
        this.feedCommentsReplay = feedCommentsReplay;
    }

}
