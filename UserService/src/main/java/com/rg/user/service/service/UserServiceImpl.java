package com.rg.user.service.service;

import com.rg.user.service.entity.Hotel;
import com.rg.user.service.entity.Rating;
import com.rg.user.service.entity.User;
import com.rg.user.service.exception.ResourceNotFoundException;
import com.rg.user.service.repository.UserRepository;
import org.hibernate.loader.entity.plan.EntityLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {
        String randomID = UUID.randomUUID().toString();
        user.setUserId(randomID);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
        String ratingUrl = "http://RATING-SERVICE/rating/user/"+userId;
        Rating[] ratingOfUser = restTemplate.getForObject(ratingUrl,Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingOfUser).collect(Collectors.toList());

        List<Rating> ratingList = Arrays.stream(ratingOfUser).map(rating -> {
            //api call to hotel service to get hotel
            String hotelurl = "http://HOTEL-SERVICE/hotel/" + rating.getHotelId();
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(hotelurl,Hotel.class);
            rating.setHotel(forEntity.getBody());
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
