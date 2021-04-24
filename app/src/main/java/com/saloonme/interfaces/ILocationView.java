package com.saloonme.interfaces;

import com.saloonme.model.response.CitiesResponse;
import com.saloonme.model.response.CountriesResponse;
import com.saloonme.model.response.StatesResponse;

public interface ILocationView extends IActivityBaseView {
    void countriesSuccess(CountriesResponse countriesResponse);

    void countriesFailed();

    void statesSuccess(StatesResponse statesResponse);

    void statesFailed();

    void citiesSuccess(CitiesResponse citiesResponse);

    void citiesFailed();
}
