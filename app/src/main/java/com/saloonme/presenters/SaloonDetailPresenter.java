package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ISaloonDetailView;
import com.saloonme.model.response.SaloonDetailsImageResponse;
import com.saloonme.model.response.SaloonReviewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaloonDetailPresenter {
    private ApiInterface apiInterface;
    private ISaloonDetailView iSaloonDetailView;

    public SaloonDetailPresenter(ApiInterface apiInterface, ISaloonDetailView iSaloonDetailView) {
        this.apiInterface = apiInterface;
        this.iSaloonDetailView = iSaloonDetailView;
    }

    public void getSaloonImages(String saloonId) {
        iSaloonDetailView.showProgressDialog("Getting saloon Images...");
        Call<SaloonDetailsImageResponse> saloonDetailsImageResponseCall = apiInterface.
                getSaloonImages(saloonId);
        saloonDetailsImageResponseCall.enqueue(new Callback<SaloonDetailsImageResponse>() {
            @Override
            public void onResponse(Call<SaloonDetailsImageResponse> call, Response<SaloonDetailsImageResponse> response) {
                iSaloonDetailView.dismissProgress();
                iSaloonDetailView.getSaloonImageSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonDetailsImageResponse> call, Throwable t) {
                iSaloonDetailView.dismissProgress();
                iSaloonDetailView.getSaloonImageFailed();
            }
        });
    }

    public void getSaloonReviews(String saloonId) {
        iSaloonDetailView.showProgressDialog("Getting saloon Reviews...");
        Call<SaloonReviewResponse> saloonReviewResponseCall = apiInterface.getSaloonReviews(saloonId);
        saloonReviewResponseCall.enqueue(new Callback<SaloonReviewResponse>() {
            @Override
            public void onResponse(Call<SaloonReviewResponse> call, Response<SaloonReviewResponse> response) {
                iSaloonDetailView.dismissProgress();
                iSaloonDetailView.getSaloonReviewSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonReviewResponse> call, Throwable t) {
                iSaloonDetailView.dismissProgress();
                iSaloonDetailView.getSaloonReviewFailed();
            }
        });
    }
}
