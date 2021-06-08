package com.saloonme.interfaces;

import com.saloonme.model.response.CartResponseData;
import com.saloonme.model.response.FeedUploadResponse;
import com.saloonme.model.response.ProductViewResponse;
import com.saloonme.model.response.ProductViewResponseData;

import java.util.List;

public interface IProductView extends IActivityBaseView {
    void onProductViewSuccess(List<ProductViewResponseData> productViewResponse);

    void onProductViewFailed();

    void onAddToCartSuccess(List<CartResponseData> cartResponseData);


    void onAddToCartFailed();
}
