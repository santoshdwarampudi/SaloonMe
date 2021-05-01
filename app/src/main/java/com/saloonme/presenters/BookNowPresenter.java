package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IBookView;
import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.SaloonListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookNowPresenter {
    private ApiInterface apiInterface;
    private IBookView iBookView;

    public BookNowPresenter(ApiInterface apiInterface, IBookView iBookView) {
        this.apiInterface = apiInterface;
        this.iBookView = iBookView;
    }

    public void getBarbersData(String saloonId) {
        iBookView.showProgressDialog("Getting experts...");
        Call<ExpertsListResponse> expertsListResponseCall = apiInterface.getExperts(saloonId);
        expertsListResponseCall.enqueue(new Callback<ExpertsListResponse>() {
            @Override
            public void onResponse(Call<ExpertsListResponse> call, Response<ExpertsListResponse> response) {
                iBookView.dismissProgress();
                iBookView.getBarbersSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ExpertsListResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.getBarbersFailed();
            }
        });
    }

    public void getSaloonDetails(String saloonId) {
        iBookView.showProgressDialog("Getting experts...");
        Call<SaloonListResponse> saloonListResponseCall = apiInterface.getSaloonDetails(saloonId);
        saloonListResponseCall.enqueue(new Callback<SaloonListResponse>() {
            @Override
            public void onResponse(Call<SaloonListResponse> call, Response<SaloonListResponse> response) {
                iBookView.dismissProgress();
                iBookView.getSaloonDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonListResponse> call, Throwable t) {
                iBookView.dismissProgress();
                iBookView.getSaloonDetailsFailed();
            }
        });
    }
}
