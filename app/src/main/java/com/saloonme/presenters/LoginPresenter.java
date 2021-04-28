package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ILoginView;
import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class LoginPresenter {
    private ILoginView iLoginView;
    private ApiInterface apiInterface;

    public LoginPresenter(ILoginView iLoginView, ApiInterface apiInterface) {
        this.iLoginView = iLoginView;
        this.apiInterface = apiInterface;
    }

    public void loginUser(String userId, String pwd) {
        iLoginView.showProgressDialog("Login user....");
        Call<LoginResponse> loginResponseCall = apiInterface.loginUser(userId, pwd);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                iLoginView.dismissProgress();
                iLoginView.onLoginSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iLoginView.dismissProgress();
                iLoginView.onLoginFailed();
            }
        });
    }
}
