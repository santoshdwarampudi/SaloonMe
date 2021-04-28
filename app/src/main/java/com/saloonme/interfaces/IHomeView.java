package com.saloonme.interfaces;

import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.SaloonListResponse;

public interface IHomeView extends IActivityBaseView {
    void saloonListFetchedSuccess(SaloonListResponse saloonListResponse);

    void saloonListFetchedFailed();

    void promotionsFetchedSuccess(PromotionsResponse promotionsResponse);

    void promotionsFetchedFailed();

    void homeServiceListFetchedSuccess(SaloonListResponse saloonListResponse);

    void homeServiceListFetchedFailed();


}
