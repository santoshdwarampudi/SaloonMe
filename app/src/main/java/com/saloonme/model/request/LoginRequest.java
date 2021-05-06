package com.saloonme.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("password")
    @Expose
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
