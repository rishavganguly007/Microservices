package com.rg.Hotel.service;

import com.rg.Hotel.entity.Hotel;

import java.util.List;

public interface IHotelService {
    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
