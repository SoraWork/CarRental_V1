package com.hoaiphong.carrental.repositories;

import com.hoaiphong.carrental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {
}
