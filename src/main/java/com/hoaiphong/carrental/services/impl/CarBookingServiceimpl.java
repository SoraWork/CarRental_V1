package com.hoaiphong.carrental.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;
import com.hoaiphong.carrental.repositories.CarBookingRepository;
import com.hoaiphong.carrental.services.CarBookingService;

@Service
public class CarBookingServiceimpl implements CarBookingService {

    private final CarBookingRepository carBookingRepository;

    public CarBookingServiceimpl(CarBookingRepository carBookingRepository) {
        this.carBookingRepository = carBookingRepository;
    }

    public void save(CarBooking carBooking) {
        carBookingRepository.save(carBooking);
    }




    @Override
    public List<CarBooking>find(String address,
    LocalDateTime startDate, LocalDateTime endDate) {
                List<CarBooking> carBookings = carBookingRepository.findByCar_AddressAndBooking_StartDateGreaterThanEqualAndBooking_EndDateLessThanEqual(
                        address, startDate, endDate 
                );
                return carBookings;
    }

    @Override
    public List<CarBooking> findByAddress(String address) {
        List<CarBooking> carBookings = carBookingRepository.findByCarAddressContaining(address);
        return carBookings;
    }

    @Override
    public Page<CarBooking> searchByAddress(String address, Pageable pageable) {
        return carBookingRepository.findByCarAddressContaining(address, pageable);    }

    @Override
    public List<CarBooking> findAll() {
        // TODO Auto-generated method stub
        return carBookingRepository.findAll();
    }

    @Override
    public CarBooking findById(UUID id) {
        return carBookingRepository.findById((CarBookingId) id).orElse(null);
    }

   
 

 


 

}
