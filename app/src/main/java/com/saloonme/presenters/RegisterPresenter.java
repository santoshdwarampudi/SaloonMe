package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IRegisterView;
import com.saloonme.model.request.RegisterRequest;
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

    public void registerUser(RegisterRequest registerRequest) {
        iRegisterView.showProgressDialog("Registering user....");
        Call<RegisterResponse> registerResponseCall = apiInterface.registerUser(registerRequest);
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
}
