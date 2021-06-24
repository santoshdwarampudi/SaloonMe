package com.saloonme.interfaces;

import com.saloonme.model.response.BaseResponse;
import com.saloonme.model.response.ProductViewResponseData;
import com.saloonme.model.response.ViewCartResponseData;

import java.util.List;

public interface ICartDetails extends IActivityBaseView {
    void onCartViewSuccess(ViewCartResponseData productViewResponse);

    void onCartViewFailed();

    void onPlaceOrderSuccess(BaseResponse baseResponse);

    void onPlaceOrderFailed();
}
