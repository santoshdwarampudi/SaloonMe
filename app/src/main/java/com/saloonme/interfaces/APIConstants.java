package com.saloonme.interfaces;


public interface APIConstants {
    String BASE_URL = "http://salooonme.com/demo_new/api/";
    String IMAGE_BASE_URL = "https://salooonme.com/demo_new/assets/store_image/";

    String LOGIN = "UserLogin";
    String REGISTER = "UserRegister";
    String COUNTRIES = "CountryDisplay";
    String STATES = "StateDisplay";
    String CITIES = "CityDisplay";
    String PROMOTIONS = "SalonPromotions";
    String SALOON_LIST_BASEDON_CATEGORY = "SalonsDisplayCategoryWise/" + "{category_id}" + "/" + "{lat}" + "/" +
            "{logni}" + "/" + "{sort}";
    String SALOON_LIST_HOME_SERVICES = "SalonsDisplayHomeServiceWise/" + "{home_service}" + "/"
            + "{lat}" + "/" + "{logni}" + "/" +
            "{category_id}";
    String SALOON_DETAILS_IMAGES = "SalonDetailImages/" + "{saloon_id}";
    String SALOON_DETAILS_REVIEWS = "SalonDetailReview/" + "{saloon_id}";
    String PROFILE = "UserProfileDisplay/" + "{user_id}" + "/" + "{token}";
    String SUB_SERVICES = "SalonDetailServices/" + "{saloon_id}" + "/" + "{service_id}";
    String MAIN_SERVICES = "MainServices/" + "{saloon_id}";
    String SLIDERS = "ServiceSlider";
    String ADD_CART = "Addtocart";
    String DELETE_CART = "Deletecart/" + "{service_id}" + "/" + "{user_id}";
    String POUPULARPLACES = "SalonsDisplayPopular/1/" + "{lat}" + "/" + "{logni}";
    String SHOW_BOOKING_ITEMS = "ShowBookingcartitems/" + "{user_id}";
    String GET_EXPERTS = "GetExpertslist/" + "{saloon_id}";
    String SALOON_DETAILS = "SalonDetails/" + "{saloon_id}";
    String PLACE_ORDER = "PlaceOrder";
    String ADD_REVIEW = "AddReview";
    String CANCEL_BOOKING = "CancelBooking";
    String USER_BOOKINGS = "BookingDetails/" + "{user_id}";
    String FEED_FORM = "FeedForm";
    String RESCHEDULE_BOOKING = "RescheduleBooking";
    String VIEW_BOOKING_DETAILS = "ViewBookingDetails/" + "{booking_id}";
    String BLOG_DETAILS = "BlogDetails";
    String PRODUCT_LIST = "ProductsList";
    String FEED_LIST = "Feedlist/" + "{user_id}";
    String ADD_FEED_FAVOURITE = "AddFeedFavourite";
    String POST_FEED_COMMENT = "PostFeedComment";
    String USER_REVIEWSLIST = "UserReviewslist/" + "{user_id}";
    String FEED_COMMENT_LIST = "Feedview/" + "{feed_sno}";
    String EDIT_PROFILE = "EditProfile";
    String FORGOT_PASSWORD = "ForgotPassword";
    String CHECK_COUPON = "CheckCoupon";
    String PRODUCT_ADD_TO_CART = "ProductAddCart";
    String PRODUCT_VIEW = "ProductView/" + "{product_id}";
    String VIEW_ORDER_DETAILS = "ViewOrderDetails/" + "{order_id}";
    String PRODUCT_DELETE_CART = "ProductDeleteCart/" + "{user_id}" + "/" + "{product_id}";
    String USER_ORDER_DETAILS = "UserOrderDetails/" + "{user_id}";
    String USER_FEED_PHOTOS = "User_Feed_Photos/" + "{user_id}";
    String PRODUCT_PLACE_ORDER = "PlaceOrderProducts";
    String TRENDING_HAIR_STYLES = "Trending_Hair_Styles";
    String REGISTER_OTP = "Registerotp";
    String SEARCH = "SearchSaloonsDisplayCityWise/" + "{cityId}" + "/" + "{search}";
}
