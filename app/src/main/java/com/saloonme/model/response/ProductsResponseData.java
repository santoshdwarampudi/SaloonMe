package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductsResponseData implements Serializable {
    @SerializedName("prod_id")
    @Expose
    private String prodId;
    @SerializedName("prod_name")
    @Expose
    private String prodName;
    @SerializedName("prod_service_id")
    @Expose
    private String prodServiceId;
    @SerializedName("prod_price")
    @Expose
    private Integer prodPrice;
    @SerializedName("prod_discount")
    @Expose
    private String prodDiscount;
    @SerializedName("prod_dis_price")
    @Expose
    private Integer prodDisPrice;
    @SerializedName("prod_stock")
    @Expose
    private Object prodStock;
    @SerializedName("prod_stock_rem")
    @Expose
    private Object prodStockRem;
    @SerializedName("prod_des")
    @Expose
    private String prodDes;
    @SerializedName("prod_status")
    @Expose
    private String prodStatus;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("prod_meta_key")
    @Expose
    private String prodMetaKey;
    @SerializedName("prod_meta_desc")
    @Expose
    private String prodMetaDesc;
    @SerializedName("prod_meta_title")
    @Expose
    private String prodMetaTitle;
    @SerializedName("prod_overview")
    @Expose
    private String prodOverview;
    @SerializedName("prod_specifications")
    @Expose
    private String prodSpecifications;
    @SerializedName("prod_quality_info")
    @Expose
    private String prodQualityInfo;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;
    @SerializedName("prod_brand")
    @Expose
    private String prodBrand;
    @SerializedName("prod_img")
    @Expose
    private String prodImg;
    @SerializedName("product_brand_id")
    @Expose
    private String productBrandId;
    @SerializedName("product_brand_name")
    @Expose
    private String productBrandName;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdServiceId() {
        return prodServiceId;
    }

    public void setProdServiceId(String prodServiceId) {
        this.prodServiceId = prodServiceId;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdDiscount() {
        return prodDiscount;
    }

    public void setProdDiscount(String prodDiscount) {
        this.prodDiscount = prodDiscount;
    }

    public Integer getProdDisPrice() {
        return prodDisPrice;
    }

    public void setProdDisPrice(Integer prodDisPrice) {
        this.prodDisPrice = prodDisPrice;
    }

    public Object getProdStock() {
        return prodStock;
    }

    public void setProdStock(Object prodStock) {
        this.prodStock = prodStock;
    }

    public Object getProdStockRem() {
        return prodStockRem;
    }

    public void setProdStockRem(Object prodStockRem) {
        this.prodStockRem = prodStockRem;
    }

    public String getProdDes() {
        return prodDes;
    }

    public void setProdDes(String prodDes) {
        this.prodDes = prodDes;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProdMetaKey() {
        return prodMetaKey;
    }

    public void setProdMetaKey(String prodMetaKey) {
        this.prodMetaKey = prodMetaKey;
    }

    public String getProdMetaDesc() {
        return prodMetaDesc;
    }

    public void setProdMetaDesc(String prodMetaDesc) {
        this.prodMetaDesc = prodMetaDesc;
    }

    public String getProdMetaTitle() {
        return prodMetaTitle;
    }

    public void setProdMetaTitle(String prodMetaTitle) {
        this.prodMetaTitle = prodMetaTitle;
    }

    public String getProdOverview() {
        return prodOverview;
    }

    public void setProdOverview(String prodOverview) {
        this.prodOverview = prodOverview;
    }

    public String getProdSpecifications() {
        return prodSpecifications;
    }

    public void setProdSpecifications(String prodSpecifications) {
        this.prodSpecifications = prodSpecifications;
    }

    public String getProdQualityInfo() {
        return prodQualityInfo;
    }

    public void setProdQualityInfo(String prodQualityInfo) {
        this.prodQualityInfo = prodQualityInfo;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(String productBrandId) {
        this.productBrandId = productBrandId;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

}
