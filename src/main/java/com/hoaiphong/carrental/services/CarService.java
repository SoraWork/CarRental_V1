package com.hoaiphong.carrental.services;

import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Create
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    // Read (Get All)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Read (Get by ID)
    public Optional<Car> getCarById(UUID id) {
        return carRepository.findById(id);
    }

    // Update
    public Car updateCar(UUID id, Car carDetails) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
        
        // Update fields
        car.setName(carDetails.getName());
        car.setLicensePlate(carDetails.getLicensePlate());
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setColor(carDetails.getColor());
        car.setNumOfSeats(carDetails.getNumOfSeats());
        car.setProductionYear(carDetails.getProductionYear());
        car.setTransmissionType(carDetails.isTransmissionType());
        car.setFuelType(carDetails.isFuelType());
        car.setMileage(carDetails.getMileage());
        car.setFuelConsumption(carDetails.getFuelConsumption());
        car.setBasePrice(carDetails.getBasePrice());
        car.setDeposit(carDetails.getDeposit());
        car.setRegistrationPaper(carDetails.getRegistrationPaper());
        car.setCertificateOfInspection(carDetails.getCertificateOfInspection());
        car.setInsurance(carDetails.getInsurance());
        car.setAddress(carDetails.getAddress());
        car.setDescription(carDetails.getDescription());
        car.setFunctionsBluetooth(carDetails.isFunctionsBluetooth());
        car.setFunctionsGPS(carDetails.isFunctionsGPS());
        car.setFunctionsCamera(carDetails.isFunctionsCamera());
        car.setFunctionsSunRoof(carDetails.isFunctionsSunRoof());
        car.setFunctionsChildLock(carDetails.isFunctionsChildLock());
        car.setFunctionsChildSeat(carDetails.isFunctionsChildSeat());
        car.setFunctionsDvd(carDetails.isFunctionsDvd());
        car.setFunctionsUSB(carDetails.isFunctionsUSB());
        car.setTermOfUse(carDetails.getTermOfUse());
        car.setNoSmoking(carDetails.isNoSmoking());
        car.setNoPet(carDetails.isNoPet());
        car.setNoFoodInCar(carDetails.isNoFoodInCar());
        car.setOther(carDetails.isOther());
        car.setOtherMessage(carDetails.getOtherMessage());
        car.setImageFront(carDetails.getImageFront());
        car.setImageBack(carDetails.getImageBack());
        car.setImageLeft(carDetails.getImageLeft());
        car.setImageRight(carDetails.getImageRight());

        return carRepository.save(car);
    }

    // Delete
    public void deleteCar(UUID id) {
        carRepository.deleteById(id);
    }
}
