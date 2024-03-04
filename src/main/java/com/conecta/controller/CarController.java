package com.conecta.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conecta.model.entity.Car;
import com.conecta.model.entity.PlateCheckRequest;
import com.conecta.service.ICarService;
import com.conecta.utils.CirculationRules;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    private ICarService carService;

    @PostMapping("car")
    public ResponseEntity<?> create(@RequestBody Car newCar) {
        Map<String, Object> response = new HashMap<>();
        // Validacion de datos nulos
        if (newCar.getPlaca() == null || newCar.getColor() == null || newCar.getModelo() == null
                || newCar.getChasis() == null) {
            response.put("codigo", "422");
            response.put("mensaje", "Faltan campos para el registro del carro");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            carService.save(newCar);
            response.put("codigo", "201");
            response.put("mensaje", "carro creado");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            response.put("codigo", "409");
            response.put("mensaje", "La placa del carro ya esta registrada.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            response.put("codigo", "400");
            response.put("mensaje", "Ocurrió un error al registar el carro.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("car/plate")
    public ResponseEntity<?> checkPlate(@RequestBody PlateCheckRequest plateCheckRequest) {
        Map<String, Object> response = new HashMap<>();
        Car existingCar = carService.findByPlate(plateCheckRequest.getPlaca());
        if (existingCar != null) {
            // check for circulation restricted or allowed
            CirculationRules circulationRules = new CirculationRules();
            boolean canCirculate = circulationRules.canCirculate(plateCheckRequest.getPlaca(),
                    plateCheckRequest.getFecha());
            if (canCirculate) {
                response.put("circula", true);
            } else {
                response.put("circula", false);
            }
            response.put("codigo", "200");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("codigo", "404");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
