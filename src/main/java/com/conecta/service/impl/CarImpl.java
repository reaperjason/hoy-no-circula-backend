package com.conecta.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conecta.model.entity.Car;
import com.conecta.repository.CarDAO;
import com.conecta.service.ICarService;

@Service
public class CarImpl implements ICarService {

    @Autowired
    private CarDAO carDAO;

    @Transactional
    @Override
    public Car save(Car newCar) {
        return carDAO.save(newCar);
    }

    @Transactional(readOnly = true)
    @Override
    public Car findByPlate(String placa) {
        Optional<Car> carOptional = carDAO.findByPlate(placa);
        return carOptional.orElse(null);
    }

}
