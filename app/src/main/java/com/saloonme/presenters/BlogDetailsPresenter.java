package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IBlogDetailsView;
import com.saloonme.model.response.BlogDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogDetailsPresenter {
    private ApiInterface apiInterface;
    private IBlogDetailsView iBlogDetailsView;

    public BlogDetailsPresenter(ApiInterface apiInterface, IBlogDetailsView iBlogDetailsView) {
        this.apiInterface = apiInterface;
        this.iBlogDetailsView = iBlogDetailsView;
    }

    public void getBlogDetails() {
        iBlogDetailsView.showProgressDialog("Loading...");
        Call<BlogDetailsResponse> blogDetailsResponseCall = apiInterface.getBlogDetails();
        blogDetailsResponseCall.enqueue(new Callback<BlogDetailsResponse>() {
            @Override
            public void onResponse(Call<BlogDetailsResponse> call, Response<BlogDetailsResponse> response) {
                iBlogDetailsView.dismissProgress();
                iBlogDetailsView.onBlogDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BlogDetailsResponse> call, Throwable t) {
                iBlogDetailsView.dismissProgress();
                iBlogDetailsView.onBlogDetailsFailed();
            }
        });
    }
}
