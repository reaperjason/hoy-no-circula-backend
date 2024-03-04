package com.conecta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conecta.model.entity.Car;

public interface CarDAO extends CrudRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.placa = :placa")
    Optional<Car> findByPlate(String placa);
}
