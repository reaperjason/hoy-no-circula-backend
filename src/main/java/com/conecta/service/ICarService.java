package com.conecta.service;

import com.conecta.model.entity.Car;

public interface ICarService {

    Car save(Car newCar);

    Car findByPlate(String placa);

}
