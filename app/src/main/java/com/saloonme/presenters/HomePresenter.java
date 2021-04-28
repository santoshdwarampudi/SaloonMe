package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IHomeView;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.SaloonListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private ApiInterface apiInterface;
    private IHomeView iHomeView;

    public HomePresenter(ApiInterface apiInterface, IHomeView iHomeView) {
        this.apiInterface = apiInterface;
        this.iHomeView = iHomeView;
    }

    public void getSaloonListBasedOnCategory(String categoryId) {
        iHomeView.showProgressDialog("Getting saloons....");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.
                getSaloonListBasedOnCategory(categoryId);
        saloonListResponseCall.enqueue(new Callback<SaloonListResponse>() {
            @Override
            public void onResponse(Call<SaloonListResponse> call, Response<SaloonListResponse> response) {
                iHomeView.dismissProgress();
                iHomeView.saloonListFetchedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonListResponse> call, Throwable t) {
                iHomeView.dismissProgress();
                iHomeView.saloonListFetchedFailed();
            }
        });
    }

    public void getPromotions() {
        iHomeView.showProgressDialog("Getting promotions....");
        Call<PromotionsResponse> promotionsResponseCall = apiInterface.getPromotions();
        promotionsResponseCall.enqueue(new Callback<PromotionsResponse>() {
            @Override
            public void onResponse(Call<PromotionsResponse> call, Response<PromotionsResponse> response) {
                iHomeView.dismissProgress();
                iHomeView.promotionsFetchedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PromotionsResponse> call, Throwable t) {
                iHomeView.dismissProgress();
                iHomeView.promotionsFetchedFailed();
            }
        });
    }

    public void getHomeServices(String categoryId, String homeServices) {
        iHomeView.showProgressDialog("Getting promotions....");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.getHomeServices
                (categoryId, homeServices);
        saloonListResponseCall.enqueue(new Callback<SaloonListResponse>() {
            @Override
            public void onResponse(Call<SaloonListResponse> call, Response<SaloonListResponse> response) {
                iHomeView.dismissProgress();
                iHomeView.homeServiceListFetchedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonListResponse> call, Throwable t) {
                iHomeView.dismissProgress();
                iHomeView.homeServiceListFetchedFailed();
            }
        });
    }


}
