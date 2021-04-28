package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IProfileView;
import com.saloonme.model.response.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private ApiInterface apiInterface;
    private IProfileView iProfileView;

    public ProfilePresenter(ApiInterface apiInterface, IProfileView iProfileView) {
        this.apiInterface = apiInterface;
        this.iProfileView = iProfileView;
    }

    public void getProfileDetails(String userId, String token) {
        iProfileView.showProgressDialog("Getting Profile details....");
        Call<ProfileResponse> profileResponseCall = apiInterface.getProfile(userId, token);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                iProfileView.dismissProgress();
                iProfileView.getProfileDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                iProfileView.dismissProgress();
                iProfileView.getProfileDetailsFailed();
            }
        });
    }
}
