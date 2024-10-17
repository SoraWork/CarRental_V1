package com.hoaiphong.carrental.dtos.carbooking;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBookingDTO {
    private UUID bookingId;
    private UUID carId;
    private String renterFullName;
    private String renterPhone;
    private String renterNationalId;
    private LocalDate renterDateOfBirth;
    private String renterEmail;
    private String renterDrivingLicense;
    private String renterCity;
    private String renterStreet;
    private String renterHouseNumber;
    private String driverFullName;
    private String driverPhone;
    private String driverNationalId;
    private LocalDate driverDateOfBirth;
    private String driverEmail;
    private String driverDrivingLicense;
    private String driverCity;
    private String driverStreet;
    private String driverHouseNumber;
    private String paymentMethod;
    private String status;
    private double totalPrice;
}