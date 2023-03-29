package com.rg.rating.service;

import com.rg.rating.entity.Rating;

import java.util.List;

public interface IRatingService {
    Rating create(Rating rating);
    List<Rating> getRatingsByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
    List<Rating> getRatings();


}
