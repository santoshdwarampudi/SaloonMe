package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IRegisterView;
import com.saloonme.model.request.RegisterRequest;
import com.saloonme.model.response.BaseResponse;
import com.saloonme.model.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private ApiInterface apiInterface;
    private IRegisterView iRegisterView;

    public RegisterPresenter(ApiInterface apiInterface, IRegisterView iRegisterView) {
        this.apiInterface = apiInterface;
        this.iRegisterView = iRegisterView;
    }

    public void registerUser(String firstname, String lastName, String pwd, String email,
                             String mobile, String gender) {
        iRegisterView.showProgressDialog("Registering user....");
        Call<RegisterResponse> registerResponseCall = apiInterface.registerUser
                (firstname, pwd, lastName, email, mobile, gender);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                iRegisterView.dismissProgress();
                iRegisterView.onRegisterSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                iRegisterView.dismissProgress();
                iRegisterView.onRegisterFailed();
            }
        });
    }

    public void getRegisterOtp(String mobileNumber) {
        iRegisterView.showProgressDialog("Loading....");
        Call<BaseResponse> registerOtpCall = apiInterface.registerOtp(mobileNumber);
        registerOtpCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                iRegisterView.dismissProgress();
                iRegisterView.getOtpSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                iRegisterView.dismissProgress();
                iRegisterView.getOtpFailed();
            }
        });
    }
}
