package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ICartDetails;
import com.saloonme.model.response.ProductViewResponse;
import com.saloonme.model.response.ViewCartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDetailsPresenter {

    private ApiInterface apiInterface;
    private ICartDetails iCartDetails;

    public CartDetailsPresenter(ApiInterface apiInterface, ICartDetails iCartDetails) {
        this.apiInterface = apiInterface;
        this.iCartDetails = iCartDetails;
    }

    public void cartData(String order_id
    ) {
        iCartDetails.showProgressDialog("Fetching data....");
        Call<ViewCartResponse> cartResponseCall = apiInterface.getCartDetails(
                order_id  );
        cartResponseCall.enqueue(new Callback<ViewCartResponse>() {
            @Override
            public void onResponse(Call<ViewCartResponse> call, Response<ViewCartResponse> response) {
                iCartDetails.dismissProgress();
                iCartDetails.onCartViewSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ViewCartResponse> call, Throwable t) {
                iCartDetails.dismissProgress();
                iCartDetails.onCartViewFailed();
            }
        });
    }
}
