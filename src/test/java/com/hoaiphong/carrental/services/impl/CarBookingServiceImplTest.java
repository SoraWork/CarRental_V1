package com.hoaiphong.carrental.services.impl;

import com.hoaiphong.carrental.dtos.carbooking.CarBookingDTO;
import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;
import com.hoaiphong.carrental.repositories.CarBookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CarBookingServiceImplTest {

    @InjectMocks
    private CarBookingServiceImpl carBookingService;

    @Mock
    private CarBookingRepository carBookingRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCarBooking() {

        // Arrange
        CarBookingDTO carBookingDTO = new CarBookingDTO();
        carBookingDTO.setBookingId(UUID.randomUUID());
        carBookingDTO.setCarId(UUID.randomUUID());
        carBookingDTO.setRenterFullName("John Doe");
        carBookingDTO.setRenterPhone("123456789");
        carBookingDTO.setRenterNationalId("NID12345");
        carBookingDTO.setRenterDateOfBirth(LocalDate.parse("1990-01-01"));
        carBookingDTO.setRenterEmail("john.doe@example.com");
        carBookingDTO.setRenterDrivingLicense("DL12345");
        carBookingDTO.setRenterCity("New York");
        carBookingDTO.setRenterStreet("Main St");
        carBookingDTO.setRenterHouseNumber("123");
        carBookingDTO.setDriverFullName("Jane Doe");
        carBookingDTO.setDriverPhone("987654321");
        carBookingDTO.setDriverNationalId("NID54321");
        carBookingDTO.setDriverDateOfBirth(LocalDate.parse("1990-01-01"));
        carBookingDTO.setDriverEmail("jane.doe@example.com");
        carBookingDTO.setDriverDrivingLicense("DL54321");
        carBookingDTO.setDriverCity("New York");
        carBookingDTO.setDriverStreet("Main St");
        carBookingDTO.setDriverHouseNumber("456");
        carBookingDTO.setPaymentMethod("Credit Card");
        carBookingDTO.setStatus("Confirmed");
        carBookingDTO.setTotalPrice(100.0);

        CarBooking savedCarBooking = new CarBooking();
        savedCarBooking.setCarBookingId(new CarBookingId(carBookingDTO.getBookingId(), carBookingDTO.getCarId()));
        savedCarBooking.setRenterFullName(carBookingDTO.getRenterFullName());
        savedCarBooking.setRenterPhone(carBookingDTO.getRenterPhone());
        savedCarBooking.setRenterNationalId(carBookingDTO.getRenterNationalId());
        savedCarBooking.setRenterDateOfBirth(carBookingDTO.getRenterDateOfBirth());
        savedCarBooking.setRenterEmail(carBookingDTO.getRenterEmail());
        savedCarBooking.setRenterDrivingLicense(carBookingDTO.getRenterDrivingLicense());
        savedCarBooking.setRenterCity(carBookingDTO.getRenterCity());
        savedCarBooking.setRenterStreet(carBookingDTO.getRenterStreet());
        savedCarBooking.setRenterHouseNumber(carBookingDTO.getRenterHouseNumber());
        savedCarBooking.setDriverFullName(carBookingDTO.getDriverFullName());
        savedCarBooking.setDriverPhone(carBookingDTO.getDriverPhone());
        savedCarBooking.setDriverNationalId(carBookingDTO.getDriverNationalId());
        savedCarBooking.setDriverDateOfBirth(carBookingDTO.getDriverDateOfBirth());
        savedCarBooking.setDriverEmail(carBookingDTO.getDriverEmail());
        savedCarBooking.setDriverDrivingLicense(carBookingDTO.getDriverDrivingLicense());
        savedCarBooking.setDriverCity(carBookingDTO.getDriverCity());
        savedCarBooking.setDriverStreet(carBookingDTO.getDriverStreet());
        savedCarBooking.setDriverHouseNumber(carBookingDTO.getDriverHouseNumber());
        savedCarBooking.setPaymentMethod(carBookingDTO.getPaymentMethod());
        savedCarBooking.setStatus(carBookingDTO.getStatus());
        savedCarBooking.setTotalPrice(carBookingDTO.getTotalPrice());

        when(carBookingRepository.save(any(CarBooking.class))).thenReturn(savedCarBooking);

        // Act
        CarBookingDTO result = carBookingService.createCarBooking(carBookingDTO);

        // Assert
        assertEquals(carBookingDTO.getBookingId(), result.getBookingId());
        assertEquals(carBookingDTO.getCarId(), result.getCarId());
        assertEquals(carBookingDTO.getRenterFullName(), result.getRenterFullName());
        assertEquals(carBookingDTO.getRenterPhone(), result.getRenterPhone());
        assertEquals(carBookingDTO.getRenterNationalId(), result.getRenterNationalId());
        assertEquals(carBookingDTO.getRenterDateOfBirth(), result.getRenterDateOfBirth());
        assertEquals(carBookingDTO.getRenterEmail(), result.getRenterEmail());
        assertEquals(carBookingDTO.getRenterDrivingLicense(), result.getRenterDrivingLicense());
        assertEquals(carBookingDTO.getRenterCity(), result.getRenterCity());
        assertEquals(carBookingDTO.getRenterStreet(), result.getRenterStreet());
        assertEquals(carBookingDTO.getRenterHouseNumber(), result.getRenterHouseNumber());
        assertEquals(carBookingDTO.getDriverFullName(), result.getDriverFullName());
        assertEquals(carBookingDTO.getDriverPhone(), result.getDriverPhone());
        assertEquals(carBookingDTO.getDriverNationalId(), result.getDriverNationalId());
        assertEquals(carBookingDTO.getDriverDateOfBirth(), result.getDriverDateOfBirth());
        assertEquals(carBookingDTO.getDriverEmail(), result.getDriverEmail());
        assertEquals(carBookingDTO.getDriverDrivingLicense(), result.getDriverDrivingLicense());
        assertEquals(carBookingDTO.getDriverCity(), result.getDriverCity());
        assertEquals(carBookingDTO.getDriverStreet(), result.getDriverStreet());
        assertEquals(carBookingDTO.getDriverHouseNumber(), result.getDriverHouseNumber());
        assertEquals(carBookingDTO.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(carBookingDTO.getStatus(), result.getStatus());
        assertEquals(carBookingDTO.getTotalPrice(), result.getTotalPrice());
    }


    @Test
    public void testGetAllCarBookings() {
        // Arrange
        CarBooking carBooking1 = new CarBooking();
        carBooking1.setCarBookingId(new CarBookingId(UUID.randomUUID(), UUID.randomUUID()));
        carBooking1.setRenterFullName("Nguyen Van A");
        carBooking1.setRenterPhone("123456789");
        carBooking1.setRenterNationalId("NID12345");
        carBooking1.setRenterDateOfBirth(LocalDate.parse("1990-01-01"));
        carBooking1.setRenterEmail("john.doe@example.com");
        carBooking1.setRenterDrivingLicense("DL12345");
        carBooking1.setRenterCity("New York");
        carBooking1.setRenterStreet("Main St");
        carBooking1.setRenterHouseNumber("123");
        carBooking1.setDriverFullName("Jane Doe");
        carBooking1.setDriverPhone("987654321");
        carBooking1.setDriverNationalId("NID54321");
        carBooking1.setDriverDateOfBirth(LocalDate.parse("1990-01-01"));
        carBooking1.setDriverEmail("jane.doe@example.com");
        carBooking1.setDriverDrivingLicense("DL54321");
        carBooking1.setDriverCity("New York");
        carBooking1.setDriverStreet("Main St");
        carBooking1.setDriverHouseNumber("456");
        carBooking1.setPaymentMethod("Credit Card");
        carBooking1.setStatus("Confirmed");
        carBooking1.setTotalPrice(100.0);

        CarBooking carBooking2 = new CarBooking();
        carBooking2.setCarBookingId(new CarBookingId(UUID.randomUUID(), UUID.randomUUID()));
        carBooking2.setRenterFullName("Nguyen Van B");
        carBooking2.setRenterPhone("123456780");
        carBooking2.setRenterNationalId("NID12305");
        carBooking2.setRenterDateOfBirth(LocalDate.parse("1990-01-02"));
        carBooking2.setRenterEmail("joh.doe@example.com");
        carBooking2.setRenterDrivingLicense("DL12340");
        carBooking2.setRenterCity("New York");
        carBooking2.setRenterStreet("Main St");
        carBooking2.setRenterHouseNumber("123");
        carBooking2.setDriverFullName("JaneDoe");
        carBooking2.setDriverPhone("987654321");
        carBooking2.setDriverNationalId("NID54321");
        carBooking2.setDriverDateOfBirth(LocalDate.parse("1990-01-01"));
        carBooking2.setDriverEmail("jane.doe@example.com");
        carBooking2.setDriverDrivingLicense("DL54321");
        carBooking2.setDriverCity("New York");
        carBooking2.setDriverStreet("Main St");
        carBooking2.setDriverHouseNumber("456");
        carBooking2.setPaymentMethod("Credit Card");
        carBooking2.setStatus("Confirmed");
        carBooking2.setTotalPrice(100.0);

        when(carBookingRepository.findAll()).thenReturn(Arrays.asList(carBooking1, carBooking2));

        // Act
        List<CarBookingDTO> result = carBookingService.getAllCarBookings();

        // Assert
        assertEquals(2, result.size());
        assertEquals(carBooking1.getRenterFullName(), result.get(0).getRenterFullName());
        assertEquals(carBooking2.getRenterFullName(), result.get(1).getRenterFullName());
    }

    @Test
    public void testGetCarBookingById() {
        // Arrange
        CarBookingId bookingId = new CarBookingId(UUID.randomUUID(), UUID.randomUUID());
        CarBooking carBooking = new CarBooking();
        carBooking.setCarBookingId(bookingId);
        carBooking.setRenterFullName("John Doe");
        carBooking.setRenterPhone("123456789");
        carBooking.setRenterNationalId("NID12345");
        carBooking.setRenterDateOfBirth(LocalDate.parse("1990-01-01"));
        carBooking.setRenterEmail("john.doe@example.com");
        carBooking.setRenterDrivingLicense("DL12345");
        carBooking.setRenterCity("New York");
        carBooking.setRenterStreet("Main St");
        carBooking.setRenterHouseNumber("123");
        carBooking.setDriverFullName("Jane Doe");
        carBooking.setDriverPhone("987654321");
        carBooking.setDriverNationalId("NID54321");
        carBooking.setDriverDateOfBirth(LocalDate.parse("1990-01-01"));
        carBooking.setDriverEmail("jane.doe@example.com");
        carBooking.setDriverDrivingLicense("DL54321");
        carBooking.setDriverCity("New York");
        carBooking.setDriverStreet("Main St");
        carBooking.setDriverHouseNumber("456");
        carBooking.setPaymentMethod("Credit Card");
        carBooking.setStatus("Confirmed");
        carBooking.setTotalPrice(100.0);
        
        System.out.println(carBooking.toString());

        when(carBookingRepository.findById(bookingId)).thenReturn(Optional.of(carBooking));

        // Act
        Optional<CarBookingDTO> result = carBookingService.getCarBookingById(bookingId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(carBooking.getRenterFullName(), result.get().getRenterFullName());
    }

    @Test
    public void testUpdateCarBooking() {
        // Arrange
        CarBookingId bookingId = new CarBookingId(UUID.randomUUID(), UUID.randomUUID());
        CarBooking existingCarBooking = new CarBooking();
        existingCarBooking.setCarBookingId(bookingId);
        existingCarBooking.setRenterFullName("John Doe");
        existingCarBooking.setRenterPhone("123456789");
        existingCarBooking.setRenterNationalId("NID12345");
        existingCarBooking.setRenterDateOfBirth(LocalDate.parse("1990-01-01"));
        existingCarBooking.setRenterEmail("john.doe@example.com");
        existingCarBooking.setRenterDrivingLicense("DL12345");
        existingCarBooking.setRenterCity("New York");
        existingCarBooking.setRenterStreet("Main St");
        existingCarBooking.setRenterHouseNumber("123");
        existingCarBooking.setDriverFullName("Jane Doe");
        existingCarBooking.setDriverPhone("987654321");
        existingCarBooking.setDriverNationalId("NID54321");
        existingCarBooking.setDriverDateOfBirth(LocalDate.parse("1990-01-01"));
        existingCarBooking.setDriverEmail("jane.doe@example.com");
        existingCarBooking.setDriverDrivingLicense("DL54321");
        existingCarBooking.setDriverCity("New York");
        existingCarBooking.setDriverStreet("Main St");
        existingCarBooking.setDriverHouseNumber("456");
        existingCarBooking.setPaymentMethod("Credit Card");
        existingCarBooking.setStatus("Confirmed");
        existingCarBooking.setTotalPrice(100.0);

        CarBookingDTO carBookingDTO = new CarBookingDTO();
        carBookingDTO.setRenterFullName("Nguyen Van A");
        carBookingDTO.setRenterPhone("0987654321");
        carBookingDTO.setRenterNationalId("0012D12");
        carBookingDTO.setRenterDateOfBirth(LocalDate.parse("1990-01-02"));
        carBookingDTO.setRenterEmail("a@example.com");
        carBookingDTO.setRenterDrivingLicense("DL54321");
        carBookingDTO.setRenterCity("New York");
        carBookingDTO.setRenterStreet("Main St");
        carBookingDTO.setRenterHouseNumber("123");
        carBookingDTO.setDriverFullName("Nguyen Van B");
        carBookingDTO.setDriverPhone("987654321");
        carBookingDTO.setDriverNationalId("NID54321");
        carBookingDTO.setDriverDateOfBirth(LocalDate.parse("1990-02-01"));
        carBookingDTO.setDriverEmail("b@example.com");
        carBookingDTO.setDriverDrivingLicense("DL54321");
        carBookingDTO.setDriverCity("New York");
        carBookingDTO.setDriverStreet("Main St");
        carBookingDTO.setDriverHouseNumber("456");
        carBookingDTO.setPaymentMethod("Credit Card");
        carBookingDTO.setStatus("Confirmed");
        carBookingDTO.setTotalPrice(100.0);

        when(carBookingRepository.findById(bookingId)).thenReturn(Optional.of(existingCarBooking));
        when(carBookingRepository.save(any(CarBooking.class))).thenReturn(existingCarBooking);

        // Act
        CarBookingDTO result = carBookingService.updateCarBooking(bookingId, carBookingDTO);

        // Assert
        assertEquals(carBookingDTO.getRenterFullName(), result.getRenterFullName());
    }
}
