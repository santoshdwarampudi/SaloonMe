package com.saloonme.interfaces;

import com.saloonme.model.response.BaseResponse;
import com.saloonme.model.response.RegisterResponse;

public interface IRegisterView extends IActivityBaseView {
    void onRegisterSuccess(RegisterResponse registerResponse);

    void onRegisterFailed();

    void getOtpSuccess(BaseResponse baseResponse);

    void getOtpFailed();

}
