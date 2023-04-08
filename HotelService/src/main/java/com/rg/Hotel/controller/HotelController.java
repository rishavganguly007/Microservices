package com.rg.Hotel.controller;

import com.rg.Hotel.entity.Hotel;
import com.rg.Hotel.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelServiceImpl hotelService;
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.get(hotelId);
        return  ResponseEntity.ok(hotel);

    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotels = hotelService.getAll();
        return ResponseEntity.ok(hotels);
    }
}
