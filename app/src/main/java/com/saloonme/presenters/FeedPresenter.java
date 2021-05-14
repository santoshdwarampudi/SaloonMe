package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IFeedUploadView;
import com.saloonme.model.response.FeedUploadResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPresenter {

    private ApiInterface apiInterface;
    private IFeedUploadView iFeedUploadView;

    public FeedPresenter(ApiInterface apiInterface, IFeedUploadView iFeedUploadView) {
        this.apiInterface = apiInterface;
        this.iFeedUploadView = iFeedUploadView;
    }

    public void uploadFeedData(String feedName, String feed_link, String description,String user_id,
                               MultipartBody.Part img,String type_of_access) {
        iFeedUploadView.showProgressDialog("Uploding data....");
        Call<FeedUploadResponse> registerResponseCall = apiInterface.uploadForm(
                feedName, feed_link,description, user_id,img,type_of_access);
        registerResponseCall.enqueue(new Callback<FeedUploadResponse>() {
            @Override
            public void onResponse(Call<FeedUploadResponse> call, Response<FeedUploadResponse> response) {
                iFeedUploadView.dismissProgress();
                iFeedUploadView.onFeedUploadSuccess(response.body());
            }

            @Override
            public void onFailure(Call<FeedUploadResponse> call, Throwable t) {
                iFeedUploadView.dismissProgress();
                iFeedUploadView.onFeedUploadFailed();
            }
        });
    }

}
