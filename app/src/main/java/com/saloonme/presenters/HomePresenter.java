package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IHomeView;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SliderResponse;

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

    public void getSaloonListBasedOnCategory(String categoryId, String lat, String longnitude) {
        iHomeView.showProgressDialog("Getting saloons....");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.
                getSaloonListBasedOnCategory(categoryId, lat, longnitude);
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

    public void getHomeServices(String categoryId, String homeServices, String lat, String logni) {
        iHomeView.showProgressDialog("Getting promotions....");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.getHomeServices
                (categoryId, homeServices, lat, logni);
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

    public void getPopularPlaces(String lat, String logni) {
        iHomeView.showProgressDialog("Getting Popular Places....");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.getPopularPlaces
                (lat, logni);
        saloonListResponseCall.enqueue(new Callback<SaloonListResponse>() {
            @Override
            public void onResponse(Call<SaloonListResponse> call, Response<SaloonListResponse> response) {
                iHomeView.dismissProgress();
                iHomeView.pouplarListFetchedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonListResponse> call, Throwable t) {
                iHomeView.dismissProgress();
                iHomeView.pouplarServiceListFetchedFailed();
            }
        });
    }

    public void getSliders() {
        iHomeView.showProgressDialog("Getting offers....");
        Call<SliderResponse> sliderResponseCall = apiInterface.getSliders();
        sliderResponseCall.enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                iHomeView.dismissProgress();
                iHomeView.sliderSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {
                iHomeView.dismissProgress();
                iHomeView.sliderFailed();
            }
        });
    }


}
