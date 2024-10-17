package com.hoaiphong.carrental.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.entities.CarBooking;

public interface CarBookingService {
    void save(CarBooking carBooking);

    List<CarBooking> find(String address, LocalDateTime startDate, LocalDateTime endDate);
    List<CarBooking> findByAddress(String address);
    Page<CarBooking> searchByAddress(String address, Pageable pageable);
    List<CarBooking>findAll();
    CarBooking findById(UUID id);
    CarBooking findByCarId(UUID carId);
    }
