package com.saloonme.interfaces;

import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedListResponse;

import java.util.List;

public interface IClickAndShareView  extends IActivityBaseView{
    void feedListFetchedSuccess(List<FeedListResponse> saloonListResponse);

    void feedListFetchedFailed();

    void addFavouriteSuccess(FavouriteResponse favouriteResponse);

    void addFavouriteFailed();
}
