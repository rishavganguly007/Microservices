package com.rg.Hotel.service;

import com.rg.Hotel.entity.Hotel;
import com.rg.Hotel.exception.ResourceNotFoundException;
import com.rg.Hotel.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements IHotelService{

    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel create(Hotel hotel) {
        String randomID = UUID.randomUUID().toString();
        hotel.setId(randomID);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
    }
}
