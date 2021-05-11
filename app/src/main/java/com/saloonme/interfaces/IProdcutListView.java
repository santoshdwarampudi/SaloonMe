package com.saloonme.interfaces;

import com.saloonme.model.response.ProductsResponse;

public interface IProdcutListView extends IActivityBaseView {
    void getProductsListSuccess(ProductsResponse productsResponse);

    void getProductsListFailed();
}
