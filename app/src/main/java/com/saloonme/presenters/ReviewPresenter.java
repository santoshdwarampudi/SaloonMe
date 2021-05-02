package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IRatingView;
import com.saloonme.model.request.ReviewRequest;

public class ReviewPresenter {
    private ApiInterface apiInterface;
    private IRatingView iRatingView;

    public ReviewPresenter(ApiInterface apiInterface, IRatingView iRatingView) {
        this.apiInterface = apiInterface;
        this.iRatingView = iRatingView;
    }

    public void addReview(ReviewRequest reviewRequest) {
        iRatingView.showProgressDialog("Adding your review...");
    }
}

