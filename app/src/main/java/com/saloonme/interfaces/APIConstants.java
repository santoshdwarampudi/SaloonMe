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
    String SALOON_LIST_BASEDON_CATEGORY = "SaloonsDisplay/" + "{lat}" + "/" + "{logni}" + "/" +
            "{category_id}";
    String SALOON_LIST_HOME_SERVICES = "SalonsDisplayHomeServiceWise/" + "{home_service}" + "/"
            + "{lat}" + "/" + "{logni}" + "/" +
            "{category_id}";
    String SALOON_DETAILS_IMAGES = "SalonDetailImages/" + "{saloon_id}";
    String SALOON_DETAILS_REVIEWS = "SalonDetailReview/" + "{saloon_id}";
    String PROFILE = "UserProfileDisplay/" + "{user_id}" + "/" + "{token}";
    String SUB_SERVICES = "SalonDetailServices/" + "{saloon_id}" + "/" + "{service_id}";
    String MAIN_SERVICES = "MainServices";
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
    String USER_REVIEWSLIST = "UserReviewslist/" + "{user_id}";
    String EDIT_PROFILE = "EditProfile";
}
