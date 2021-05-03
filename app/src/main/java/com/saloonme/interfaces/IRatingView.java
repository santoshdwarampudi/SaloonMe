package com.saloonme.interfaces;

import com.saloonme.model.response.RemoveCartResponse;

public interface IRatingView extends IActivityBaseView {
    void onAddReviewSuccess(RemoveCartResponse removeCartResponse);

    void onAddReviewFailed();
}
