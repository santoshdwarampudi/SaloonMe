package com.saloonme.interfaces;

import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedListResponse;
import com.saloonme.model.response.FeedResponse;

import java.util.List;

public interface IClickAndShareView  extends IActivityBaseView{
    void feedListFetchedSuccess(FeedResponse feedResponse);

    void feedListFetchedFailed();

    void addFavouriteSuccess(FavouriteResponse favouriteResponse);

    void addFavouriteFailed();
}
