package com.saloonme.interfaces;

import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.UserBookingDetailsResponse;
import com.saloonme.model.response.UserFeedPhotsResponse;
import com.saloonme.model.response.UserOrderDetailsResponse;
import com.saloonme.model.response.UserReviewsResponse;

public interface IProfileView extends IActivityBaseView {
    void getProfileDetailsSuccess(ProfileResponse profileResponse);

    void getProfileDetailsFailed();

    void getUserBookingDetailsSuccess(UserBookingDetailsResponse userBookingDetailsResponse);

    void getUserBookingDetailsFailed();

    void cancelBookingSuccess(RemoveCartResponse removeCartResponse);

    void cancelBookingFailed();

    void reschduleBookingSuccess(RemoveCartResponse removeCartResponse);

    void reschduleBookingFailed();

    void getUserReviewsSuccess(UserReviewsResponse userReviewsResponse);

    void getUserReviewsFailed();
    void getUserFeedPhotsSuccess(UserFeedPhotsResponse userFeedPhotsResponse);

    void getUserFeedPhotsFailed();
    void getUserOrderDetailSuccess(UserOrderDetailsResponse userFeedPhotsResponse);

    void getUserOrderDetailFailed();
}
