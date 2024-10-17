package com.hoaiphong.carrental.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.dtos.carbooking.CarBookingDTO;
import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;
import com.hoaiphong.carrental.repositories.CarBookingRepository;
import com.hoaiphong.carrental.services.CarBookingService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarBookingServiceImpl implements CarBookingService {
    private final CarBookingRepository carBookingRepository;

    @Autowired
    public CarBookingServiceImpl(CarBookingRepository carBookingRepository) {
        this.carBookingRepository = carBookingRepository;
    }
    @Override
    public CarBookingDTO createCarBooking(CarBookingDTO carBookingDTO) {
        var carBooking = new CarBooking();
        var carBookingId = new CarBookingId(carBookingDTO.getBookingId(), carBookingDTO.getCarId());
        carBooking.setCarBookingId(carBookingId);
        carBooking.setRenterFullName(carBookingDTO.getRenterFullName());
        carBooking.setRenterPhone(carBookingDTO.getRenterPhone());
        carBooking.setRenterNationalId(carBookingDTO.getRenterNationalId());
        carBooking.setRenterDateOfBirth(carBookingDTO.getRenterDateOfBirth());
        carBooking.setRenterEmail(carBookingDTO.getRenterEmail());
        carBooking.setRenterDrivingLicense(carBookingDTO.getRenterDrivingLicense());
        carBooking.setRenterCity(carBookingDTO.getRenterCity());
        carBooking.setRenterStreet(carBookingDTO.getRenterStreet());
        carBooking.setRenterHouseNumber(carBookingDTO.getRenterHouseNumber());
        carBooking.setDriverFullName(carBookingDTO.getDriverFullName());
        carBooking.setDriverPhone(carBookingDTO.getDriverPhone());
        carBooking.setDriverNationalId(carBookingDTO.getDriverNationalId());
        carBooking.setDriverDateOfBirth(carBookingDTO.getDriverDateOfBirth());
        carBooking.setDriverEmail(carBookingDTO.getDriverEmail());
        carBooking.setDriverDrivingLicense(carBookingDTO.getDriverDrivingLicense());
        carBooking.setDriverCity(carBookingDTO.getDriverCity());
        carBooking.setDriverStreet(carBookingDTO.getDriverStreet());
        carBooking.setDriverHouseNumber(carBookingDTO.getDriverHouseNumber());
        carBooking.setPaymentMethod(carBookingDTO.getPaymentMethod());
        carBooking.setStatus(carBookingDTO.getStatus());
        carBooking.setTotalPrice(carBookingDTO.getTotalPrice());

        var savedCarBooking = carBookingRepository.save(carBooking);
        var dto = new CarBookingDTO();
        dto.setBookingId(savedCarBooking.getCarBookingId().getBookingId());
        dto.setCarId(savedCarBooking.getCarBookingId().getCarId());
        dto.setRenterFullName(savedCarBooking.getRenterFullName());
        dto.setRenterPhone(savedCarBooking.getRenterPhone());
        dto.setRenterNationalId(savedCarBooking.getRenterNationalId());
        dto.setRenterDateOfBirth(savedCarBooking.getRenterDateOfBirth());
        dto.setRenterEmail(savedCarBooking.getRenterEmail());
        dto.setRenterDrivingLicense(savedCarBooking.getRenterDrivingLicense());
        dto.setRenterCity(savedCarBooking.getRenterCity());
        dto.setRenterStreet(savedCarBooking.getRenterStreet());
        dto.setRenterHouseNumber(savedCarBooking.getRenterHouseNumber());
        dto.setDriverFullName(savedCarBooking.getDriverFullName());
        dto.setDriverPhone(savedCarBooking.getDriverPhone());
        dto.setDriverNationalId(savedCarBooking.getDriverNationalId());
        dto.setDriverDateOfBirth(savedCarBooking.getDriverDateOfBirth());
        dto.setDriverEmail(savedCarBooking.getDriverEmail());
        dto.setDriverDrivingLicense(savedCarBooking.getDriverDrivingLicense());
        dto.setDriverCity(savedCarBooking.getDriverCity());
        dto.setDriverStreet(savedCarBooking.getDriverStreet());
        dto.setDriverHouseNumber(savedCarBooking.getDriverHouseNumber());
        dto.setPaymentMethod(savedCarBooking.getPaymentMethod());
        dto.setStatus(savedCarBooking.getStatus());
        dto.setTotalPrice(savedCarBooking.getTotalPrice());

        return dto;
    }

    @Override
    public List<CarBookingDTO> getAllCarBookings() {
        return carBookingRepository.findAll().stream()
                .map(carBooking -> {
                    var dto = new CarBookingDTO();
                    dto.setBookingId(carBooking.getCarBookingId().getBookingId());
                    dto.setCarId(carBooking.getCarBookingId().getCarId());
                    dto.setRenterFullName(carBooking.getRenterFullName());
                    dto.setRenterPhone(carBooking.getRenterPhone());
                    dto.setRenterNationalId(carBooking.getRenterNationalId());
                    dto.setRenterDateOfBirth(carBooking.getRenterDateOfBirth());
                    dto.setRenterEmail(carBooking.getRenterEmail());
                    dto.setRenterDrivingLicense(carBooking.getRenterDrivingLicense());
                    dto.setRenterCity(carBooking.getRenterCity());
                    dto.setRenterStreet(carBooking.getRenterStreet());
                    dto.setRenterHouseNumber(carBooking.getRenterHouseNumber());
                    dto.setDriverFullName(carBooking.getDriverFullName());
                    dto.setDriverPhone(carBooking.getDriverPhone());
                    dto.setDriverNationalId(carBooking.getDriverNationalId());
                    dto.setDriverDateOfBirth(carBooking.getDriverDateOfBirth());
                    dto.setDriverEmail(carBooking.getDriverEmail());
                    dto.setDriverDrivingLicense(carBooking.getDriverDrivingLicense());
                    dto.setDriverCity(carBooking.getDriverCity());
                    dto.setDriverStreet(carBooking.getDriverStreet());
                    dto.setDriverHouseNumber(carBooking.getDriverHouseNumber());
                    dto.setPaymentMethod(carBooking.getPaymentMethod());
                    dto.setStatus(carBooking.getStatus());
                    dto.setTotalPrice(carBooking.getTotalPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarBookingDTO> getCarBookingById(CarBookingId id) {
        return carBookingRepository.findById(id)
                .map(carBooking -> {
                    var dto = new CarBookingDTO();
                    dto.setBookingId(carBooking.getCarBookingId().getBookingId());
                    dto.setCarId(carBooking.getCarBookingId().getCarId());
                    dto.setRenterFullName(carBooking.getRenterFullName());
                    dto.setRenterPhone(carBooking.getRenterPhone());
                    dto.setRenterNationalId(carBooking.getRenterNationalId());
                    dto.setRenterDateOfBirth(carBooking.getRenterDateOfBirth());
                    dto.setRenterEmail(carBooking.getRenterEmail());
                    dto.setRenterDrivingLicense(carBooking.getRenterDrivingLicense());
                    dto.setRenterCity(carBooking.getRenterCity());
                    dto.setRenterStreet(carBooking.getRenterStreet());
                    dto.setRenterHouseNumber(carBooking.getRenterHouseNumber());
                    dto.setDriverFullName(carBooking.getDriverFullName());
                    dto.setDriverPhone(carBooking.getDriverPhone());
                    dto.setDriverNationalId(carBooking.getDriverNationalId());
                    dto.setDriverDateOfBirth(carBooking.getDriverDateOfBirth());
                    dto.setDriverEmail(carBooking.getDriverEmail());
                    dto.setDriverDrivingLicense(carBooking.getDriverDrivingLicense());
                    dto.setDriverCity(carBooking.getDriverCity());
                    dto.setDriverStreet(carBooking.getDriverStreet());
                    dto.setDriverHouseNumber(carBooking.getDriverHouseNumber());
                    dto.setPaymentMethod(carBooking.getPaymentMethod());
                    dto.setStatus(carBooking.getStatus());
                    dto.setTotalPrice(carBooking.getTotalPrice());
                    return dto;
                });
    }



    @Override
    public CarBookingDTO updateCarBooking(CarBookingId id, CarBookingDTO carBookingDTO) {
        var existingCarBooking = carBookingRepository.findById(id);
        if (existingCarBooking.isPresent()) {
            var carBooking = existingCarBooking.get();
            carBooking.setRenterFullName(carBookingDTO.getRenterFullName());
            carBooking.setRenterPhone(carBookingDTO.getRenterPhone());
            carBooking.setRenterNationalId(carBookingDTO.getRenterNationalId());
            carBooking.setRenterDateOfBirth(carBookingDTO.getRenterDateOfBirth());
            carBooking.setRenterEmail(carBookingDTO.getRenterEmail());
            carBooking.setRenterDrivingLicense(carBookingDTO.getRenterDrivingLicense());
            carBooking.setRenterCity(carBookingDTO.getRenterCity());
            carBooking.setRenterStreet(carBookingDTO.getRenterStreet());
            carBooking.setRenterHouseNumber(carBookingDTO.getRenterHouseNumber());
            carBooking.setDriverFullName(carBookingDTO.getDriverFullName());
            carBooking.setDriverPhone(carBookingDTO.getDriverPhone());
            carBooking.setDriverNationalId(carBookingDTO.getDriverNationalId());
            carBooking.setDriverDateOfBirth(carBookingDTO.getDriverDateOfBirth());
            carBooking.setDriverEmail(carBookingDTO.getDriverEmail());
            carBooking.setDriverDrivingLicense(carBookingDTO.getDriverDrivingLicense());
            carBooking.setDriverCity(carBookingDTO.getDriverCity());
            carBooking.setDriverStreet(carBookingDTO.getDriverStreet());
            carBooking.setDriverHouseNumber(carBookingDTO.getDriverHouseNumber());
            carBooking.setPaymentMethod(carBookingDTO.getPaymentMethod());
            carBooking.setStatus(carBookingDTO.getStatus());
            carBooking.setTotalPrice(carBookingDTO.getTotalPrice());

            var updatedCarBooking = carBookingRepository.save(carBooking);
            var dto = new CarBookingDTO();
            dto.setBookingId(updatedCarBooking.getCarBookingId().getBookingId());
            dto.setCarId(updatedCarBooking.getCarBookingId().getCarId());
            dto.setRenterFullName(updatedCarBooking.getRenterFullName());
            dto.setRenterPhone(updatedCarBooking.getRenterPhone());
            dto.setRenterNationalId(updatedCarBooking.getRenterNationalId());
            dto.setRenterDateOfBirth(updatedCarBooking.getRenterDateOfBirth());
            dto.setRenterEmail(updatedCarBooking.getRenterEmail());
            dto.setRenterDrivingLicense(updatedCarBooking.getRenterDrivingLicense());
            dto.setRenterCity(updatedCarBooking.getRenterCity());
            dto.setRenterStreet(updatedCarBooking.getRenterStreet());
            dto.setRenterHouseNumber(updatedCarBooking.getRenterHouseNumber());
            dto.setDriverFullName(updatedCarBooking.getDriverFullName());
            dto.setDriverPhone(updatedCarBooking.getDriverPhone());
            dto.setDriverNationalId(updatedCarBooking.getDriverNationalId());
            dto.setDriverDateOfBirth(updatedCarBooking.getDriverDateOfBirth());
            dto.setDriverEmail(updatedCarBooking.getDriverEmail());
            dto.setDriverDrivingLicense(updatedCarBooking.getDriverDrivingLicense());
            dto.setDriverCity(updatedCarBooking.getDriverCity());
            dto.setDriverStreet(updatedCarBooking.getDriverStreet());
            dto.setDriverHouseNumber(updatedCarBooking.getDriverHouseNumber());
            dto.setPaymentMethod(updatedCarBooking.getPaymentMethod());
            dto.setStatus(updatedCarBooking.getStatus());
            dto.setTotalPrice(updatedCarBooking.getTotalPrice());

            return dto;
        }
        return null; // Hoặc ném ngoại lệ nếu không tìm thấy
    }

    @Override
    public void deleteCarBooking(CarBookingId id) {
        carBookingRepository.deleteById(id);
    }
}
