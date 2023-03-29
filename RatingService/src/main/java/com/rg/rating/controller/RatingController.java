package com.rg.rating.controller;

import com.rg.rating.entity.Rating;
import com.rg.rating.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingServiceImpl ratingService;
    @PostMapping
    public ResponseEntity<Rating> createHotel(@RequestBody Rating rating){
        Rating rating1 = ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return  ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }
    @GetMapping()
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> ratings = ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }
}
