package com.saloonme.interfaces;

import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.UserBookingDetailsResponse;

public interface IProfileView extends IActivityBaseView {
    void getProfileDetailsSuccess(ProfileResponse profileResponse);

    void getProfileDetailsFailed();

    void getUserBookingDetailsSuccess(UserBookingDetailsResponse userBookingDetailsResponse);

    void getUserBookingDetailsFailed();

    void cancelBookingSuccess(RemoveCartResponse removeCartResponse);

    void cancelBookingFailed();

    void reschduleBookingSuccess(RemoveCartResponse removeCartResponse);

    void reschduleBookingFailed();
}
