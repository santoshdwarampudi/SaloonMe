package com.saloonme.interfaces;

import com.saloonme.model.response.BookingItemsResponse;
import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonListResponse;

public interface IBookView extends IActivityBaseView {
    void getBarbersSuccess(ExpertsListResponse expertsListResponse);

    void getBarbersFailed();

    void getSaloonDetailsSuccess(SaloonListResponse saloonListResponse);

    void getSaloonDetailsFailed();

    void getProfileDetailsSuccess(ProfileResponse profileResponse);

    void getProfileDetailsFailed();

    void getProductDetailsSuccess(BookingItemsResponse bookingItemsResponse);

    void getProductDetailsFailed();

    void removeCartSuccess(RemoveCartResponse removeCartResponse);

    void removeCartFailed();

}
