package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CommentsResponse implements Serializable
{


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private CommentListResponse data;

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

    public CommentListResponse getData() {
    return data;
}

    public void setData(CommentListResponse data) {
    this.data = data;
}

}
