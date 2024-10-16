package com.hoaiphong.carrental.dtos.user;
import lombok.Data;

import java.util.UUID;

@Data
public class CarDTO {

    private UUID id;
    private String name;
    private String licensePlate;
    private String brand;
    private String model;
    private String color;
    private int numOfSeats;
    private int productionYear;
    private boolean transmissionType; // true for automatic, false for manual
    private boolean fuelType; // true for gasoline, false for diesel
    private int mileage;
    private int fuelConsumption;
    private double basePrice;
    private double deposit;

    // Document
    private String registrationPaper;
    private String certificateOfInspection;
    private String insurance;

    // Address
    private String address;
    private String description;

    // Additional functions
    private boolean functionsBluetooth;
    private boolean functionsGPS;
    private boolean functionsCamera;
    private boolean functionsSunRoof;
    private boolean functionsChildLock;
    private boolean functionsChildSeat;
    private boolean functionsDvd;
    private boolean functionsUSB;

    // Terms of use
    private String termOfUse;
    private boolean noSmoking;
    private boolean noPet;
    private boolean noFoodInCar;
    private boolean other;
    private String otherMessage;

    // Image paths
    private String imageFront;
    private String imageBack;
    private String imageLeft;
    private String imageRight;

    // User ID to map car to user
    private UUID userId;
}
