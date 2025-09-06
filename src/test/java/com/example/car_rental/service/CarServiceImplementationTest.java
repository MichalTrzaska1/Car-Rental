package com.example.car_rental.service;

import com.example.car_rental.entity.Car;
import com.example.car_rental.entity.CarStatus;
import com.example.car_rental.repository.CarRepository;
import com.example.car_rental.service.implementation.CarServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplementationTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImplementation carService;

    private Car car;

    @BeforeEach
    void beforeEach() {
        car = new Car(1, "Polonez", "Caro", new BigDecimal("12342.00"), CarStatus.AVAILABLE);
    }

    @Test
    void findCarsSuccessfully() {
        carService.findCars();
        verify(carRepository).findAll();
    }

    @Test
    void saveCarSuccessfully() {
        when(carRepository.save(car)).thenReturn(car);

        Car result = carService.saveCar(car);

        assertEquals("Polonez", result.getModel());
        assertEquals("Caro", result.getBrand());
        verify(carRepository).save(car);
    }

    @Test
    void changeCarStatusSuccessfully() {
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        carService.changeCarStatus(1, CarStatus.RENTED);

        assertEquals(CarStatus.RENTED, car.getStatus());
        verify(carRepository).save(car);
    }

    @Test
    void changeCarStatuWhenCarNotFound() {
        when(carRepository.findById(232)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> carService.changeCarStatus(232, CarStatus.RENTED));
    }
}