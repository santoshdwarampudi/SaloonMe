package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IForgotPasswordView;
import com.saloonme.model.response.RemoveCartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPresenter {
    private ApiInterface apiInterface;
    private IForgotPasswordView iForgotPasswordView;

    public ForgotPasswordPresenter(ApiInterface apiInterface, IForgotPasswordView iForgotPasswordView) {
        this.apiInterface = apiInterface;
        this.iForgotPasswordView = iForgotPasswordView;
    }

    public void forgotPassword(String email) {
        iForgotPasswordView.showProgressDialog("Loading...");
        Call<RemoveCartResponse> forgotPasswordCall = apiInterface.forgotPassword(email);
        forgotPasswordCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iForgotPasswordView.dismissProgress();
                iForgotPasswordView.forgotPasswordSetSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iForgotPasswordView.dismissProgress();
                iForgotPasswordView.forgotPasswordSetFailed();
            }
        });
    }
}
