package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IProdcutListView;
import com.saloonme.model.response.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsListPresenter {
    private ApiInterface apiInterface;
    private IProdcutListView iProdcutListView;

    public ProductsListPresenter(ApiInterface apiInterface, IProdcutListView iProdcutListView) {
        this.apiInterface = apiInterface;
        this.iProdcutListView = iProdcutListView;
    }

    public void getProductList() {
        iProdcutListView.showProgressDialog("Loading...");
        Call<ProductsResponse> productsResponseCall = apiInterface.getProductList();
        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                iProdcutListView.dismissProgress();
                iProdcutListView.getProductsListSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                iProdcutListView.dismissProgress();
                iProdcutListView.getProductsListFailed();
            }
        });
    }
}
