package com.example.car_rental.service.implementation;

import com.example.car_rental.entity.Car;
import com.example.car_rental.entity.CarStatus;
import com.example.car_rental.repository.CarRepository;
import com.example.car_rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImplementation implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findCars() {
        return carRepository.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car changeCarStatus(Integer carId, CarStatus status) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setStatus(status);
        return carRepository.save(car);
    }
}
