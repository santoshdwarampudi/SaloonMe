package com.saloonme.interfaces;


import com.saloonme.model.request.AddCartRequest;
import com.saloonme.model.request.CancelRequest;
import com.saloonme.model.request.PlaceOrderRequest;
import com.saloonme.model.request.ReviewRequest;
import com.saloonme.model.response.AddCartResponse;
import com.saloonme.model.response.AddCommentResponse;
import com.saloonme.model.response.BaseResponse;
import com.saloonme.model.response.BlogDetailsResponse;
import com.saloonme.model.response.BookingDetailsResponse;
import com.saloonme.model.response.BookingItemsResponse;
import com.saloonme.model.response.CartResponse;
import com.saloonme.model.response.CheckCouponResponse;
import com.saloonme.model.response.CitiesResponse;
import com.saloonme.model.response.CommentListResponse;
import com.saloonme.model.response.CommentsResponse;
import com.saloonme.model.response.CountriesResponse;
import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedResponse;
import com.saloonme.model.response.FeedUploadResponse;
import com.saloonme.model.response.LoginResponse;
import com.saloonme.model.response.PlaceOrderResponse;
import com.saloonme.model.response.ProductViewResponse;
import com.saloonme.model.response.ProductsResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.PromotionsResponse;
import com.saloonme.model.response.RegisterResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonDetailsImageResponse;
import com.saloonme.model.response.SaloonListResponse;
import com.saloonme.model.response.SaloonReviewResponse;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;
import com.saloonme.model.response.SearchResponse;
import com.saloonme.model.response.SliderResponse;
import com.saloonme.model.response.StatesResponse;
import com.saloonme.model.response.TrendingHairStyleResponse;
import com.saloonme.model.response.UserBookingDetailsResponse;
import com.saloonme.model.response.UserFeedPhotsResponse;
import com.saloonme.model.response.UserOrderDetailsResponse;
import com.saloonme.model.response.UserReviewsResponse;
import com.saloonme.model.response.ViewCartResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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
                                        @Field("mobile_number") String mobile_number,
                                        @Field("gender") String gender
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
    Call<SaloonListResponse> getSaloonListBasedOnCategory(@Path("category_id") String categoryId,
                                                          @Path("lat") String lat,
                                                          @Path("logni") String logni,
                                                          @Path("sort") String sort);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.PROMOTIONS)
    Call<PromotionsResponse> getPromotions();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SALOON_LIST_HOME_SERVICES)
    Call<SaloonListResponse> getHomeServices(@Path("category_id") String categoryId,
                                             @Path("home_service") String homeService,
                                             @Path("lat") String lat,
                                             @Path("logni") String logni);

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
    Call<SaloonServiceResponse> getSaloonServices(@Path("saloon_id") String saloonId);


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
    Call<RemoveCartResponse> cancelBooking(@Body CancelRequest cancelRequest);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.USER_BOOKINGS)
    Call<UserBookingDetailsResponse> getUserBookings(@Path("user_id") String userId);

    @Headers({"Accept: application/json"})
    @Multipart
    @POST(APIConstants.FEED_FORM)
    Call<FeedUploadResponse> uploadForm(@Part("feed_name") String feed_name,
                                        @Part("feed_link") String feed_link,
                                        @Part("description") String description,
                                        @Part("user_id") String user_id,
                                        @Part MultipartBody.Part timeLineImg,
                                        @Part("type_of_access") String type_of_access);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.RESCHEDULE_BOOKING)
    Call<RemoveCartResponse> rescheduleBooking(@Field("booking_id") String bookingId,
                                               @Field("service_date") String serviceDate,
                                               @Field("service_time") String serviceTime);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.VIEW_BOOKING_DETAILS)
    Call<BookingDetailsResponse> getBookingDetails(@Path("booking_id") String bookingId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.BLOG_DETAILS)
    Call<BlogDetailsResponse> getBlogDetails();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.PRODUCT_LIST)
    Call<ProductsResponse> getProductList();

    @Headers({"Accept: application/json"})
    @GET(APIConstants.FEED_LIST)
    Call<FeedResponse> getFeedList(@Path("user_id") String userId);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.USER_REVIEWSLIST)
    Call<UserReviewsResponse> getUserReviews(@Path("user_id") String userId);

    @Multipart
    @Headers({"Accept: application/json"})
    @POST(APIConstants.EDIT_PROFILE)
    Call<RemoveCartResponse> editProfile(@Part("user_id") String userId,
                                         @Part("first_name") String firstName,
                                         @Part("last_name") String lastName,
                                         @Part("mobile_number") String mobileNumber,
                                         @Part("email_address") String emailAddress,
                                         @Part MultipartBody.Part filePart);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.ADD_FEED_FAVOURITE)
    Call<FavouriteResponse> addFavourite(@Field("feed_sno") String feed_sno,
                                         @Field("user_id") String user_id);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.POST_FEED_COMMENT)
    Call<AddCommentResponse> addComment(@Field("feed_sno") String feed_sno,
                                        @Field("feed_comment") String feedComment,
                                        @Field("comment_replay_id") String commentReplayId,
                                        @Field("user_id") String user_id);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.FEED_COMMENT_LIST)
    Call<CommentsResponse> getUserCommentss(@Path("feed_sno") String feed_sno);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.FORGOT_PASSWORD)
    Call<RemoveCartResponse> forgotPassword(@Field("email_address") String email_address);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.CHECK_COUPON)
    Call<CheckCouponResponse> checkCoupon(@Field("coupon_code") String coupon_code,
                                          @Field("salon_id") String salon_id);

    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.PRODUCT_VIEW)
    Call<ProductViewResponse> getProductView(@Path("product_id") String product_id);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.VIEW_ORDER_DETAILS)
    Call<ViewCartResponse> getCartDetails(@Path("order_id") String order_id);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.USER_ORDER_DETAILS)
    Call<UserOrderDetailsResponse> getOrderDetails(@Path("user_id") String user_id);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.USER_FEED_PHOTOS)
    Call<UserFeedPhotsResponse> getFeedPhotos(@Path("user_id") String user_id);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.PRODUCT_ADD_TO_CART)
    Call<CartResponse> addToCart(@Field("user_id") String user_id,
                                 @Field("prod_id") String prod_id);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.PRODUCT_PLACE_ORDER)
    Call<BaseResponse> producPlaceOrder(@Field("user_id") String user_id,
                                        @Field("grand_total") String prod_id);

    @Headers({"Accept: application/json"})
    @GET(APIConstants.SEARCH)
    Call<SearchResponse> getSaloonBasedOnSearch(@Path("cityId") String cityId,
                                                @Path("search") String search
    );

    @Headers({"Accept: application/json"})
    @GET(APIConstants.TRENDING_HAIR_STYLES)
    Call<TrendingHairStyleResponse> getTrendingHairStyles();

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST(APIConstants.REGISTER_OTP)
    Call<BaseResponse> registerOtp(@Field("mobile_number") String mobileNumber);


}
