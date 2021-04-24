package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ILocationView;

public class LocationPresenter {
    private ApiInterface apiInterface;
    private ILocationView iLocationView;

    public LocationPresenter(ApiInterface apiInterface, ILocationView iLocationView) {
        this.apiInterface = apiInterface;
        this.iLocationView = iLocationView;
    }

    public void getCountries() {

    }

    public void getStates() {

    }

    public void getCities() {

    }
}
