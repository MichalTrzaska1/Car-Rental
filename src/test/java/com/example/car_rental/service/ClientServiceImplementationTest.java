package com.example.car_rental.service;

import com.example.car_rental.entity.Car;
import com.example.car_rental.entity.CarStatus;
import com.example.car_rental.entity.Client;
import com.example.car_rental.repository.CarRepository;
import com.example.car_rental.repository.ClientRepository;
import com.example.car_rental.service.implementation.ClientServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplementationTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private ClientServiceImplementation clientService;

    private Client client;
    private Car car;

    @BeforeEach
    void beforeEach() {
        car = new Car(1, "Civic", "Honda", new BigDecimal("111.00"), CarStatus.AVAILABLE);
        client = new Client(1, "Maryla", "Rodowicz", "maryla.rodowicz@wp.pl", new HashSet<>());
    }

    @Test
    void rentAvailableCarSuccessfully() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        clientService.rentCar(1, 1);

        assertTrue(client.getRentedCars().contains(car));
        assertEquals(CarStatus.RENTED, car.getStatus());
    }

    @Test
    void rentRentedCarFails() {
        car.setStatus(CarStatus.RENTED);
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        assertThrows(RuntimeException.class, () -> clientService.rentCar(1, 1));
    }

    @Test
    void returnRentedCarSuccessfully() {
        client.getRentedCars().add(car);
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        clientService.returnCar(1, 1);

        assertFalse(client.getRentedCars().contains(car));
        assertEquals(CarStatus.AVAILABLE, car.getStatus());
    }

    @Test
    void returnNotRentedCarFails() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        assertThrows(RuntimeException.class, () -> clientService.returnCar(1, 1));
    }
}