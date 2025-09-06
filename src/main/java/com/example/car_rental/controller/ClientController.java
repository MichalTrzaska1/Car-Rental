package com.example.car_rental.controller;

import com.example.car_rental.entity.Client;
import com.example.car_rental.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients() {
        return clientService.findClients();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PostMapping("/{clientId}/rent/{carId}")
    public Client rentCar(@PathVariable Integer clientId, @PathVariable Integer carId) {
        return clientService.rentCar(clientId, carId);
    }

    @PostMapping("/{clientId}/return/{carId}")
    public Client returnCar(@PathVariable Integer clientId, @PathVariable Integer carId) {
        return clientService.returnCar(clientId, carId);
    }
}
