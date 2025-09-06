package com.example.car_rental.service;

import com.example.car_rental.entity.Client;

import java.util.List;

public interface ClientService {
    public List<Client> findClients();

    public Client saveClient(Client client);

    public Client rentCar(Integer clientId, Integer carId);

    public Client returnCar(Integer clientId, Integer carId);


}
