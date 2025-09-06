package com.example.car_rental.service;

import com.example.car_rental.entity.Car;
import com.example.car_rental.entity.CarStatus;

import java.util.List;

public interface CarService {
    public List<Car> findCars();

    public Car saveCar(Car car);

    public Car changeCarStatus(Integer carId, CarStatus status);

}
