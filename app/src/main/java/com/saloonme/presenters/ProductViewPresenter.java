package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IProductView;
import com.saloonme.model.response.CartResponse;
import com.saloonme.model.response.FeedUploadResponse;
import com.saloonme.model.response.ProductViewResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewPresenter {

    private ApiInterface apiInterface;
    private IProductView iProductView;

    public ProductViewPresenter(ApiInterface apiInterface, IProductView iProductView) {
        this.apiInterface = apiInterface;
        this.iProductView = iProductView;
    }

    public void productData(String product_id) {
        iProductView.showProgressDialog("Fetching data....");
        Call<ProductViewResponse> productViewResponseCall = apiInterface.getProductView(
                product_id  );
        productViewResponseCall.enqueue(new Callback<ProductViewResponse>() {
            @Override
            public void onResponse(Call<ProductViewResponse> call, Response<ProductViewResponse> response) {
                iProductView.dismissProgress();
                iProductView.onProductViewSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ProductViewResponse> call, Throwable t) {
                iProductView.dismissProgress();
                iProductView.onProductViewFailed();
            }
        });
    }

    public void addToCart(String user_id,String product_id
                                ) {
        iProductView.showProgressDialog("Adding to Cart....");
        Call<CartResponse> cartResponseCall = apiInterface.addToCart(user_id,
                product_id  );
        cartResponseCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                iProductView.dismissProgress();
                iProductView.onAddToCartSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                iProductView.dismissProgress();
                iProductView.onAddToCartFailed();
            }
        });
    }
}
