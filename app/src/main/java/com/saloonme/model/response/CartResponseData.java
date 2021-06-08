package com.saloonme.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartResponseData implements Serializable
{

    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("quanity")
    @Expose
    private String quanity;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cart_order_status")
    @Expose
    private String cartOrderStatus;
    @SerializedName("product_sub_total")
    @Expose
    private String productSubTotal;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("color")
    @Expose
    private Object color;
    @SerializedName("cart_id")
    @Expose
    private String cartId;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuanity() {
        return quanity;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartOrderStatus() {
        return cartOrderStatus;
    }

    public void setCartOrderStatus(String cartOrderStatus) {
        this.cartOrderStatus = cartOrderStatus;
    }

    public String getProductSubTotal() {
        return productSubTotal;
    }

    public void setProductSubTotal(String productSubTotal) {
        this.productSubTotal = productSubTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
