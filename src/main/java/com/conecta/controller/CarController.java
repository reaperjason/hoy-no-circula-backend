package com.conecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conecta.model.entity.Car;
import com.conecta.service.ICarService;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    private ICarService carService;

    @PostMapping("car")
    public Car create(@RequestBody Car newCar) {
        return carService.save(newCar);
    }

    @PostMapping("car/plate")
    public Car selectByPlate(@RequestBody String placa) {
        return carService.findByPlate(placa);
    }
}
