package com.hoaiphong.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;

public interface CarBookingRepository extends JpaRepository<CarBooking, CarBookingId>{
    

}
