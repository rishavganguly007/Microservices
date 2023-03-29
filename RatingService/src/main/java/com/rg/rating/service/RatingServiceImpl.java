package com.rg.rating.service;

import com.rg.rating.entity.Rating;
import com.rg.rating.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements IRatingService {
    @Autowired
    private RatingRepo ratingRepo;
    @Override
    public Rating create(Rating rating) {
        String randomID = UUID.randomUUID().toString();
        rating.setRatingId(randomID);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepo.findAll();
    }
}
