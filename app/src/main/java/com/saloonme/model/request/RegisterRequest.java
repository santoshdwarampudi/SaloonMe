package com.saloonme.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRequest implements Serializable {
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email_address")
    @Expose
    private String emailAddress;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
