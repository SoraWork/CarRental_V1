package com.hoaiphong.carrental.services;

import com.hoaiphong.carrental.dtos.car.CarCreateDTO;
import com.hoaiphong.carrental.dtos.car.CarDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdateDetailDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdatePricingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<CarDTO> findAll();

    Page<CarDTO> findAll(String keyword, Pageable pageable);

    CarDTO findById(UUID id);

    CarDTO create(CarCreateDTO carCreateDTO);

    CarDTO update(UUID id, CarUpdateDetailDTO carUpdateDetailDTO);

    CarDTO update(UUID id, CarUpdatePricingDTO carUpdatePricingDTO);

}
