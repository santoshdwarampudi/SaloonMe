package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IBlogDetailsView;
import com.saloonme.model.response.BlogDetailsResponse;
import com.saloonme.model.response.TrendingHairStyleResponse;

import org.jetbrains.annotations.NotNull;

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
            public void onResponse(@NotNull Call<BlogDetailsResponse> call, @NotNull Response<BlogDetailsResponse> response) {
                iBlogDetailsView.dismissProgress();
                iBlogDetailsView.onBlogDetailsSuccess(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<BlogDetailsResponse> call, @NotNull Throwable t) {
                iBlogDetailsView.dismissProgress();
                iBlogDetailsView.onBlogDetailsFailed();
            }
        });
    }

    public void getTrendingHairStyles() {
        iBlogDetailsView.showProgressDialog("Loading...");
        Call<TrendingHairStyleResponse> trendingHairStyleResponseCall = apiInterface.getTrendingHairStyles();
        trendingHairStyleResponseCall.enqueue(new Callback<TrendingHairStyleResponse>() {
            @Override
            public void onResponse(Call<TrendingHairStyleResponse> call, Response<TrendingHairStyleResponse> response) {
                iBlogDetailsView.dismissProgress();
                iBlogDetailsView.getTrendingHairStyleSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TrendingHairStyleResponse> call, Throwable t) {
                iBlogDetailsView.dismissProgress();
                iBlogDetailsView.getTrendingHairStyleFailed();
            }
        });
    }
}
