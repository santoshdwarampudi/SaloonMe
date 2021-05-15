package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IEditProfileView;
import com.saloonme.model.response.RemoveCartResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter {
    private ApiInterface apiInterface;
    private IEditProfileView iEditProfileView;

    public EditProfilePresenter(ApiInterface apiInterface, IEditProfileView iEditProfileView) {
        this.apiInterface = apiInterface;
        this.iEditProfileView = iEditProfileView;
    }

    public void updateProfile(String firstName, String lastName, String email, String phoneNumber,
                              String userId, MultipartBody.Part img) {
        iEditProfileView.showProgressDialog("Updating...");
        Call<RemoveCartResponse> updateProfileCall = apiInterface.editProfile(userId, firstName,
                lastName, phoneNumber, email, img);
        updateProfileCall.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                iEditProfileView.dismissProgress();
                iEditProfileView.onEditProfileSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                iEditProfileView.dismissProgress();
                iEditProfileView.onEditProfileFailed();
            }
        });
    }
}
