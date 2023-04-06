package com.rg.user.service.controller;

import com.rg.user.service.entity.User;
import com.rg.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
 //   @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback" )
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return  ResponseEntity.ok(user);

    }

    //Creating Fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        User user =  User.builder()
                .userId("1234")
                .email("ahd@gh.com")
                .name("Elo")
                .about("Elo's a great guy")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }
}
