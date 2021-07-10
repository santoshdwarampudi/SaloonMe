package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrendingHairStyleResponseData implements Serializable {
    @SerializedName("tren_hair_sno")
    @Expose
    private String trenHairSno;
    @SerializedName("tren_hair_name")
    @Expose
    private String trenHairName;
    @SerializedName("tren_hair_desc")
    @Expose
    private String trenHairDesc;
    @SerializedName("tren_hair_date_time")
    @Expose
    private String trenHairDateTime;
    @SerializedName("tren_hair_imgs")
    @Expose
    private String trenHairImgs;
    @SerializedName("tren_hair_status")
    @Expose
    private String trenHairStatus;
    @SerializedName("trend_hair_delete")
    @Expose
    private String trendHairDelete;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("type")
    @Expose
    private String type;

    public String getTrenHairSno() {
        return trenHairSno;
    }

    public void setTrenHairSno(String trenHairSno) {
        this.trenHairSno = trenHairSno;
    }

    public String getTrenHairName() {
        return trenHairName;
    }

    public void setTrenHairName(String trenHairName) {
        this.trenHairName = trenHairName;
    }

    public String getTrenHairDesc() {
        return trenHairDesc;
    }

    public void setTrenHairDesc(String trenHairDesc) {
        this.trenHairDesc = trenHairDesc;
    }

    public String getTrenHairDateTime() {
        return trenHairDateTime;
    }

    public void setTrenHairDateTime(String trenHairDateTime) {
        this.trenHairDateTime = trenHairDateTime;
    }

    public String getTrenHairImgs() {
        return trenHairImgs;
    }

    public void setTrenHairImgs(String trenHairImgs) {
        this.trenHairImgs = trenHairImgs;
    }

    public String getTrenHairStatus() {
        return trenHairStatus;
    }

    public void setTrenHairStatus(String trenHairStatus) {
        this.trenHairStatus = trenHairStatus;
    }

    public String getTrendHairDelete() {
        return trendHairDelete;
    }

    public void setTrendHairDelete(String trendHairDelete) {
        this.trendHairDelete = trendHairDelete;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
