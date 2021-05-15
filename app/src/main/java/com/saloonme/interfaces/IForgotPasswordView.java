package com.saloonme.interfaces;

import com.saloonme.model.response.RemoveCartResponse;

public interface IForgotPasswordView extends IActivityBaseView {
    void forgotPasswordSetSuccess(RemoveCartResponse removeCartResponse);

    void forgotPasswordSetFailed();
}
