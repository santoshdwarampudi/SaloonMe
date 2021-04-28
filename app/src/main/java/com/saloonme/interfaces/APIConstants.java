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
    String SALOON_LIST_BASEDON_CATEGORY = "SalonsDisplayCategoryWise/" + "{category_id}";
    String SALOON_LIST_HOME_SERVICES = "SalonsDisplayHomeServiceWise/" + "{category_id}"
            + "/" + "{home_service}";
    String SALOON_DETAILS_IMAGES = "SalonDetailImages/" + "{saloon_id}";
    String SALOON_DETAILS_REVIEWS = "SalonDetailReview/" + "{saloon_id}";
    String PROFILE = "UserProfileDisplay/" + "{user_id}" + "/" + "{token}";
}
