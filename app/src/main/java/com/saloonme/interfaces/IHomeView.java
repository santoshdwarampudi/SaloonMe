package com.saloonme.interfaces;

import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SliderResponse;

public interface IHomeView extends IActivityBaseView {
    void saloonListFetchedSuccess(SaloonListResponse saloonListResponse);

    void saloonListFetchedFailed();

    void promotionsFetchedSuccess(PromotionsResponse promotionsResponse);

    void promotionsFetchedFailed();

    void homeServiceListFetchedSuccess(SaloonListResponse saloonListResponse);

    void homeServiceListFetchedFailed();

    void pouplarListFetchedSuccess(SaloonListResponse saloonListResponse);

    void pouplarServiceListFetchedFailed();

    void sliderSuccess(SliderResponse sliderResponse);

    void sliderFailed();

    void getProfileDetailsSuccess(ProfileResponse profileResponse);

    void getProfileDetailsFailed();


}
