package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ICartDetails;
import com.saloonme.model.response.BaseResponse;
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

    public void placeOrder(String user_id,String grand_total
    ) {
        iCartDetails.showProgressDialog( "Placing order....");
        Call<BaseResponse> cartResponseCall = apiInterface.producPlaceOrder(
                user_id,grand_total  );
        cartResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                iCartDetails.dismissProgress();
                iCartDetails.onPlaceOrderSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                iCartDetails.dismissProgress();
                iCartDetails.onPlaceOrderFailed();
            }
        });
    }
}
