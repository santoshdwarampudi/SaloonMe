package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ISaloonServiceView;
import com.saloonme.model.request.AddCartRequest;
import com.saloonme.model.response.AddCartResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaloonServicePresenter {
    private ApiInterface apiInterface;
    private ISaloonServiceView iSaloonServiceView;

    public SaloonServicePresenter(ApiInterface apiInterface, ISaloonServiceView iSaloonServiceView) {
        this.apiInterface = apiInterface;
        this.iSaloonServiceView = iSaloonServiceView;
    }

    public void getSaloonMainServices() {
        iSaloonServiceView.showProgressDialog("Getting Services...");
        Call<SaloonServiceResponse> saloonServiceResponseCall = apiInterface.getSaloonServices();
        saloonServiceResponseCall.enqueue(new Callback<SaloonServiceResponse>() {
            @Override
            public void onResponse(Call<SaloonServiceResponse> call, Response<SaloonServiceResponse> response) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonServiceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonServiceResponse> call, Throwable t) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonServiceFailed();
            }
        });
    }

    public void getSaloonSubServices(String saloonId, String serviceId) {
        iSaloonServiceView.showProgressDialog("Getting Sub Services...");
        Call<SaloonSubServiceResponse> saloonSubServiceResponseCall = apiInterface.
                getSubServices(saloonId, serviceId);
        saloonSubServiceResponseCall.enqueue(new Callback<SaloonSubServiceResponse>() {
            @Override
            public void onResponse(Call<SaloonSubServiceResponse> call, Response<SaloonSubServiceResponse> response) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonSubServiceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonSubServiceResponse> call, Throwable t) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonSubServiceFailed();
            }
        });
    }

    public void addToCart(AddCartRequest addCartRequest) {
        iSaloonServiceView.showProgressDialog("Adding to cart...");
        Call<AddCartResponse> addCartResponseCall = apiInterface.addToCart(addCartRequest);
        addCartResponseCall.enqueue(new Callback<AddCartResponse>() {
            @Override
            public void onResponse(Call<AddCartResponse> call, Response<AddCartResponse> response) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.addToCartSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AddCartResponse> call, Throwable t) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.addToCartFailed();
            }
        });
    }

    public void removeCart(String userId, String serviceId) {
        iSaloonServiceView.showProgressDialog("Removing from cart...");
        Call<RemoveCartResponse> removeCartResponseCall = apiInterface.deleteCartItem
                (serviceId, userId);
        removeCartResponseCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.removeCartSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.removeCartFailed();
            }
        });
    }
}
