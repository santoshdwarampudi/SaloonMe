package com.saloonme.interfaces;

import com.saloonme.model.response.LoginResponse;

public interface ILoginView extends IActivityBaseView {
    void onLoginSuccess(LoginResponse loginResponse);

    void onLoginFailed();
}
