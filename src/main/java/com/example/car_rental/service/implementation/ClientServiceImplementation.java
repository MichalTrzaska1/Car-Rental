package com.example.car_rental.service.implementation;

import com.example.car_rental.entity.Car;
import com.example.car_rental.entity.CarStatus;
import com.example.car_rental.entity.Client;
import com.example.car_rental.repository.CarRepository;
import com.example.car_rental.repository.ClientRepository;
import com.example.car_rental.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;


    @Override
    public List<Client> findClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client rentCar(Integer clientId, Integer carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (car.getStatus() == CarStatus.RENTED) {
            throw new RuntimeException("Car is rented");
        }

        car.setStatus(CarStatus.RENTED);
        carRepository.save(car);

        client.getRentedCars().add(car);
        return clientRepository.save(client);
    }

    @Override
    public Client returnCar(Integer clientId, Integer carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (client.getRentedCars().contains(car)) {
            client.getRentedCars().remove(car);
            car.setStatus(CarStatus.AVAILABLE);
            carRepository.save(car);
            return clientRepository.save(client);
        }
        throw new RuntimeException("Client hasn't rented this car");
    }
}
