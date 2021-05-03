package com.saloonme.interfaces;


import com.saloonme.model.request.AddCartRequest;
import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.request.PlaceOrderRequest;
import com.saloonme.model.request.RegisterRequest;
import com.saloonme.model.request.ReviewRequest;
import com.saloonme.model.response.AddCartResponse;
import com.saloonme.model.response.BookingItemsResponse;
import com.saloonme.model.response.CitiesResponse;
import com.saloonme.model.response.CountriesResponse;
import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.LoginResponse;
import com.saloonme.model.response.PlaceOrderResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.RegisterResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonDetailsImageResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SaloonReviewResponse;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;
import com.saloonme.model.response.SliderResponse;
import com.saloonme.model.response.StatesResponse;

import java.net.CacheRequest;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @Headers({"Accept: application/json"})
    @GET(APIConstants.MAIN_SERVICES)
    Call<SaloonServiceResponse> getSaloonServices();


    @Headers({"Accept: application/json"})
    @GET(APIConstants.SUB_SERVICES)
    Call<SaloonSubServiceResponse> getSubServices(@Path("saloon_id") String saloonId,
                                                  @Path("service_id") String serviceId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.POUPULARPLACES)
    Call<SaloonListResponse> getPopularPlaces(@Path("lat") String lat,
                                              @Path("logni") String logni);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SLIDERS)
    Call<SliderResponse> getSliders();


    @Headers({"Accept: application/json"})
    @POST(APIConstants.ADD_CART)
    Call<AddCartResponse> addToCart(@Body AddCartRequest addCartRequest);

    @Headers({"Accept: application/json"})
    @DELETE(APIConstants.DELETE_CART)
    Call<RemoveCartResponse> deleteCartItem(@Path("service_id") String serviceId,
                                            @Path("user_id") String userId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SHOW_BOOKING_ITEMS)
    Call<BookingItemsResponse> getBookingItems(@Path("user_id") String userId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.GET_EXPERTS)
    Call<ExpertsListResponse> getExperts(@Path("saloon_id") String saloonId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SALOON_DETAILS)
    Call<SaloonListResponse> getSaloonDetails(@Path("saloon_id") String saloonId);

    @Headers({"Accept: application/json"})
    @POST(APIConstants.PLACE_ORDER)
    Call<PlaceOrderResponse> placeOrder(@Body PlaceOrderRequest placeOrderRequest);

    @Headers({"Accept: application/json"})
    @POST(APIConstants.ADD_REVIEW)
    Call<RemoveCartResponse> addReview(@Body ReviewRequest reviewRequest);

    @Headers({"Accept: application/json"})
    @POST(APIConstants.CANCEL_BOOKING)
    Call<RemoveCartResponse> cancelBooking(@Body CacheRequest cacheRequest);

}
