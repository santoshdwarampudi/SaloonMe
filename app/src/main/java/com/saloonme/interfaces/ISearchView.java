package com.saloonme.interfaces;

import com.saloonme.model.response.SearchResponse;

public interface ISearchView extends IActivityBaseView{
    void searchSuccess(SearchResponse searchResponse);

    void searchFailed();
}
