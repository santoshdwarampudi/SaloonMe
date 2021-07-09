package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchResponseData implements Serializable {
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("store_number")
    @Expose
    private String storeNumber;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("website_url")
    @Expose
    private Object websiteUrl;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("shop_established")
    @Expose
    private Object shopEstablished;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("owner_email")
    @Expose
    private String ownerEmail;
    @SerializedName("owner_number")
    @Expose
    private String ownerNumber;
    @SerializedName("owner_gender")
    @Expose
    private Object ownerGender;
    @SerializedName("google_pay")
    @Expose
    private Object googlePay;
    @SerializedName("pancard_no")
    @Expose
    private Object pancardNo;
    @SerializedName("bank_accountno")
    @Expose
    private Object bankAccountno;
    @SerializedName("account_type")
    @Expose
    private Object accountType;
    @SerializedName("ifsc_code")
    @Expose
    private Object ifscCode;
    @SerializedName("account_holder")
    @Expose
    private Object accountHolder;
    @SerializedName("bank_name")
    @Expose
    private Object bankName;
    @SerializedName("aadhar_card")
    @Expose
    private Object aadharCard;
    @SerializedName("aadhar_cardimg")
    @Expose
    private Object aadharCardimg;
    @SerializedName("long_bio")
    @Expose
    private Object longBio;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("short_bio")
    @Expose
    private Object shortBio;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private Object token;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("store_img")
    @Expose
    private String storeImg;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("salon_status")
    @Expose
    private String salonStatus;
    @SerializedName("salon_send_accept_confirmation")
    @Expose
    private String salonSendAcceptConfirmation;
    @SerializedName("passbook_file")
    @Expose
    private String passbookFile;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("saloon_url")
    @Expose
    private String saloonUrl;
    @SerializedName("home_service")
    @Expose
    private String homeService;
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;
    @SerializedName("trending")
    @Expose
    private String trending;
    @SerializedName("new_salon_time_slot")
    @Expose
    private String newSalonTimeSlot;
    @SerializedName("gst")
    @Expose
    private String gst;
    private final static long serialVersionUID = -4381812547970848477L;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(Object websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getShopEstablished() {
        return shopEstablished;
    }

    public void setShopEstablished(Object shopEstablished) {
        this.shopEstablished = shopEstablished;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public Object getOwnerGender() {
        return ownerGender;
    }

    public void setOwnerGender(Object ownerGender) {
        this.ownerGender = ownerGender;
    }

    public Object getGooglePay() {
        return googlePay;
    }

    public void setGooglePay(Object googlePay) {
        this.googlePay = googlePay;
    }

    public Object getPancardNo() {
        return pancardNo;
    }

    public void setPancardNo(Object pancardNo) {
        this.pancardNo = pancardNo;
    }

    public Object getBankAccountno() {
        return bankAccountno;
    }

    public void setBankAccountno(Object bankAccountno) {
        this.bankAccountno = bankAccountno;
    }

    public Object getAccountType() {
        return accountType;
    }

    public void setAccountType(Object accountType) {
        this.accountType = accountType;
    }

    public Object getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(Object ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Object getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Object accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public Object getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(Object aadharCard) {
        this.aadharCard = aadharCard;
    }

    public Object getAadharCardimg() {
        return aadharCardimg;
    }

    public void setAadharCardimg(Object aadharCardimg) {
        this.aadharCardimg = aadharCardimg;
    }

    public Object getLongBio() {
        return longBio;
    }

    public void setLongBio(Object longBio) {
        this.longBio = longBio;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getShortBio() {
        return shortBio;
    }

    public void setShortBio(Object shortBio) {
        this.shortBio = shortBio;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSalonStatus() {
        return salonStatus;
    }

    public void setSalonStatus(String salonStatus) {
        this.salonStatus = salonStatus;
    }

    public String getSalonSendAcceptConfirmation() {
        return salonSendAcceptConfirmation;
    }

    public void setSalonSendAcceptConfirmation(String salonSendAcceptConfirmation) {
        this.salonSendAcceptConfirmation = salonSendAcceptConfirmation;
    }

    public String getPassbookFile() {
        return passbookFile;
    }

    public void setPassbookFile(String passbookFile) {
        this.passbookFile = passbookFile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSaloonUrl() {
        return saloonUrl;
    }

    public void setSaloonUrl(String saloonUrl) {
        this.saloonUrl = saloonUrl;
    }

    public String getHomeService() {
        return homeService;
    }

    public void setHomeService(String homeService) {
        this.homeService = homeService;
    }

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

    public String getTrending() {
        return trending;
    }

    public void setTrending(String trending) {
        this.trending = trending;
    }

    public String getNewSalonTimeSlot() {
        return newSalonTimeSlot;
    }

    public void setNewSalonTimeSlot(String newSalonTimeSlot) {
        this.newSalonTimeSlot = newSalonTimeSlot;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

}
