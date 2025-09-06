package com.example.car_rental.controller;

import com.example.car_rental.entity.Car;
import com.example.car_rental.entity.CarStatus;
import com.example.car_rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getCars() {
        return carService.findCars();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping("/{id}/status")
    public Car updateCarStatus(@PathVariable Integer id, @RequestParam CarStatus status) {
        return carService.changeCarStatus(id, status);
    }
}
