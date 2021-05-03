package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookingItemsResponseData implements Serializable {
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("ser_img")
    @Expose
    private String serviceImg;
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("book_status")
    @Expose
    private String bookStatus;
    @SerializedName("booking_service_id")
    @Expose
    private String bookingServiceId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_price")
    @Expose
    private String servicePrice;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("expert_id")
    @Expose
    private String expertId;
    @SerializedName("user_id")
    @Expose
    private String userId;
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
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("shop_established")
    @Expose
    private String shopEstablished;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("owner_email")
    @Expose
    private String ownerEmail;
    @SerializedName("owner_number")
    @Expose
    private String ownerNumber;
    @SerializedName("owner_gender")
    @Expose
    private String ownerGender;
    @SerializedName("google_pay")
    @Expose
    private String googlePay;
    @SerializedName("pancard_no")
    @Expose
    private String pancardNo;
    @SerializedName("bank_accountno")
    @Expose
    private String bankAccountno;
    @SerializedName("account_type")
    @Expose
    private String accountType;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("account_holder")
    @Expose
    private String accountHolder;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("aadhar_card")
    @Expose
    private String aadharCard;
    @SerializedName("aadhar_cardimg")
    @Expose
    private String aadharCardimg;
    @SerializedName("long_bio")
    @Expose
    private Object longBio;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("short_bio")
    @Expose
    private String shortBio;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookingServiceId() {
        return bookingServiceId;
    }

    public void setBookingServiceId(String bookingServiceId) {
        this.bookingServiceId = bookingServiceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getShopEstablished() {
        return shopEstablished;
    }

    public void setShopEstablished(String shopEstablished) {
        this.shopEstablished = shopEstablished;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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

    public String getOwnerGender() {
        return ownerGender;
    }

    public void setOwnerGender(String ownerGender) {
        this.ownerGender = ownerGender;
    }

    public String getGooglePay() {
        return googlePay;
    }

    public void setGooglePay(String googlePay) {
        this.googlePay = googlePay;
    }

    public String getPancardNo() {
        return pancardNo;
    }

    public void setPancardNo(String pancardNo) {
        this.pancardNo = pancardNo;
    }

    public String getBankAccountno() {
        return bankAccountno;
    }

    public void setBankAccountno(String bankAccountno) {
        this.bankAccountno = bankAccountno;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getAadharCardimg() {
        return aadharCardimg;
    }

    public void setAadharCardimg(String aadharCardimg) {
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

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }
}
