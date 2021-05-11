package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BlogDetailsResponseData implements Serializable {
    @SerializedName("blog_id")
    @Expose
    private String blogId;
    @SerializedName("blog_name")
    @Expose
    private String blogName;
    @SerializedName("blog_title")
    @Expose
    private String blogTitle;
    @SerializedName("blog_img")
    @Expose
    private String blogImg;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("blog_des")
    @Expose
    private String blogDes;
    @SerializedName("auth_img")
    @Expose
    private Object authImg;
    @SerializedName("auth_about")
    @Expose
    private Object authAbout;
    @SerializedName("blog_meta_title")
    @Expose
    private String blogMetaTitle;
    @SerializedName("blog_url_link")
    @Expose
    private String blogUrlLink;
    @SerializedName("blog_meta_des")
    @Expose
    private String blogMetaDes;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;
    @SerializedName("blog_url")
    @Expose
    private String blogUrl;
    @SerializedName("blog_image")
    @Expose
    private String blogImage;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBlogDes() {
        return blogDes;
    }

    public void setBlogDes(String blogDes) {
        this.blogDes = blogDes;
    }

    public Object getAuthImg() {
        return authImg;
    }

    public void setAuthImg(Object authImg) {
        this.authImg = authImg;
    }

    public Object getAuthAbout() {
        return authAbout;
    }

    public void setAuthAbout(Object authAbout) {
        this.authAbout = authAbout;
    }

    public String getBlogMetaTitle() {
        return blogMetaTitle;
    }

    public void setBlogMetaTitle(String blogMetaTitle) {
        this.blogMetaTitle = blogMetaTitle;
    }

    public String getBlogUrlLink() {
        return blogUrlLink;
    }

    public void setBlogUrlLink(String blogUrlLink) {
        this.blogUrlLink = blogUrlLink;
    }

    public String getBlogMetaDes() {
        return blogMetaDes;
    }

    public void setBlogMetaDes(String blogMetaDes) {
        this.blogMetaDes = blogMetaDes;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }
}
