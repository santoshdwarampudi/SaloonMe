package com.saloonme.interfaces;


import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.request.RegisterRequest;
import com.saloonme.model.response.CitiesResponse;
import com.saloonme.model.response.CountriesResponse;
import com.saloonme.model.response.LoginResponse;
import com.saloonme.model.response.RegisterResponse;
import com.saloonme.model.response.StatesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Accept: application/json"})
    @POST(APIConstants.LOGIN)
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @Headers({"Accept: application/json"})
    @POST(APIConstants.REGISTER)
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.COUNTRIES)
    Call<CountriesResponse> getCountries();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.STATES)
    Call<StatesResponse> getStates();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.CITIES)
    Call<CitiesResponse> getCities();


}
