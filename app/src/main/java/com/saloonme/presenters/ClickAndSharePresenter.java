package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IClickAndShareView;
import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClickAndSharePresenter {

    private ApiInterface apiInterface;
    private IClickAndShareView iClickAndShareView;

    public ClickAndSharePresenter(ApiInterface apiInterface, IClickAndShareView iClickAndShareView) {
        this.apiInterface = apiInterface;
        this.iClickAndShareView = iClickAndShareView;
    }

    public void getFeeds(String id) {
        iClickAndShareView.showProgressDialog("Getting Feeds....");
        Call<FeedResponse> feedResponseCall = apiInterface.getFeedList(id);
        feedResponseCall.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                iClickAndShareView.dismissProgress();
                iClickAndShareView.feedListFetchedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                iClickAndShareView.dismissProgress();
                iClickAndShareView.feedListFetchedFailed();
            }
        });
    }

    public void addFavourite(String feedSno,String userId) {
        iClickAndShareView.showProgressDialog("Adding Favrouite....");
        Call<FavouriteResponse> favouriteResponseCall = apiInterface.addFavourite(feedSno, userId);
        favouriteResponseCall.enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                iClickAndShareView.dismissProgress();
                iClickAndShareView.addFavouriteSuccess(response.body());
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                iClickAndShareView.dismissProgress();
                iClickAndShareView.addFavouriteFailed();
            }
        });
    }
}
