package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IRatingView;
import com.saloonme.model.request.ReviewRequest;
import com.saloonme.model.response.RemoveCartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewPresenter {
    private ApiInterface apiInterface;
    private IRatingView iRatingView;

    public ReviewPresenter(ApiInterface apiInterface, IRatingView iRatingView) {
        this.apiInterface = apiInterface;
        this.iRatingView = iRatingView;
    }

    public void addReview(ReviewRequest reviewRequest) {
        iRatingView.showProgressDialog("Adding your review...");
        Call<RemoveCartResponse> reviewCall = apiInterface.addReview(reviewRequest);
        reviewCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iRatingView.dismissProgress();
                iRatingView.onAddReviewSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iRatingView.dismissProgress();
                iRatingView.onAddReviewFailed();
            }
        });
    }
}

