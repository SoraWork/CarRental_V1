package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.Optional;

import com.hoaiphong.carrental.dtos.carbooking.CarBookingDTO;
import com.hoaiphong.carrental.entities.CarBookingId;

public interface CarBookingService {
    List<CarBookingDTO> getAllCarBookings();
    Optional<CarBookingDTO> getCarBookingById(CarBookingId id);
    CarBookingDTO createCarBooking(CarBookingDTO carBookingDTO);
    CarBookingDTO updateCarBooking(CarBookingId id, CarBookingDTO carBookingDTO);
    void deleteCarBooking(CarBookingId id);
}