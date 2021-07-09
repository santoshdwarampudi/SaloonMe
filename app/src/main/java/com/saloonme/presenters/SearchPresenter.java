package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ISearchView;
import com.saloonme.model.response.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    private ApiInterface apiInterface;
    private ISearchView iSearchView;

    public SearchPresenter(ApiInterface apiInterface, ISearchView iSearchView) {
        this.apiInterface = apiInterface;
        this.iSearchView = iSearchView;
    }

    public void searchSaloon(String cityId, String searchWord) {
        // iSearchView.showProgressDialog("Loading...");
        Call<SearchResponse> searchResponseCall = apiInterface.getSaloonBasedOnSearch(cityId, searchWord);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                iSearchView.dismissProgress();
                iSearchView.searchSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                iSearchView.dismissProgress();
                iSearchView.searchFailed();
            }
        });
    }
}
