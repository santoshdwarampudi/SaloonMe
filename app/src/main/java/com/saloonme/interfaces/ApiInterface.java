package com.saloonme.interfaces;


import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.request.RegisterRequest;
import com.saloonme.model.response.CitiesResponse;
import com.saloonme.model.response.CountriesResponse;
import com.saloonme.model.response.LoginResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.RegisterResponse;
import com.saloonme.model.response.SaloonDetailsImageResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SaloonReviewResponse;
import com.saloonme.model.response.StatesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.LOGIN)
    Call<LoginResponse> loginUser(@Field("user_id") String user_id,
                                  @Field("password") String password);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.REGISTER)
    Call<RegisterResponse> registerUser(@Field("first_name") String first_name,
                                        @Field("password") String password,
                                        @Field("last_name") String last_name,
                                        @Field("email_address") String email_address,
                                        @Field("mobile_number") String mobile_number
    );

    @Headers({"Accept: application/json"})
    @GET(APIConstants.COUNTRIES)
    Call<CountriesResponse> getCountries();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.STATES)
    Call<StatesResponse> getStates();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.CITIES)
    Call<CitiesResponse> getCities();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SALOON_LIST_BASEDON_CATEGORY)
    Call<SaloonListResponse> getSaloonListBasedOnCategory(@Path("category_id") String categoryId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.PROMOTIONS)
    Call<PromotionsResponse> getPromotions();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SALOON_LIST_HOME_SERVICES)
    Call<SaloonListResponse> getHomeServices(@Path("category_id") String categoryId,
                                             @Path("home_service") String homeService);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SALOON_DETAILS_IMAGES)
    Call<SaloonDetailsImageResponse> getSaloonImages(@Path("saloon_id") String saloonId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SALOON_DETAILS_REVIEWS)
    Call<SaloonReviewResponse> getSaloonReviews(@Path("saloon_id") String saloonId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.PROFILE)
    Call<ProfileResponse> getProfile(@Path("user_id") String useId,
                                     @Path("token") String token);


}
