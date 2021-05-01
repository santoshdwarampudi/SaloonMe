package com.saloonme.interfaces;

import com.saloonme.model.response.AddCartResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;

public interface ISaloonServiceView extends IActivityBaseView {
    void saloonServiceSuccess(SaloonServiceResponse saloonServiceResponse);

    void saloonServiceFailed();

    void saloonSubServiceSuccess(SaloonSubServiceResponse saloonSubServiceResponse);

    void saloonSubServiceFailed();

    void addToCartSuccess(AddCartResponse addCartResponse);

    void addToCartFailed();

    void removeCartSuccess(RemoveCartResponse removeCartResponse);

    void removeCartFailed();
}
