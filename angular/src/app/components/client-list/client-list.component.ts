import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Client, ClientService } from '../../services/client.service';
import { Car, CarService } from '../../services/car.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
selector: 'client-list',
standalone: true,
imports: [CommonModule, FormsModule],
templateUrl: './client-list.component.html'
})
export class ClientListComponent implements OnInit {
clients: Client[] = [];
availableCars: Car[] = [];

rentModeClientId: number | null = null;
selectedCarId: number | null = null;

@Output() carReturned = new EventEmitter<void>();
@Output() carRented = new EventEmitter<void>();

constructor(
    private clientService: ClientService,
    private carService: CarService
  ) {}

  ngOnInit() {
    this.loadClients();
    this.loadAvailableCars();
  }

  loadClients() {
    this.clientService.getClients().subscribe(data => {
      this.clients = data;
    });
  }

loadAvailableCars() {
  this.carService.getCars().subscribe(data => {
    this.availableCars = [...data.filter(c => c.status === 'AVAILABLE')];
  });
}


  returnCar(client: Client, carId: number) {
    this.clientService.returnCar(client.id!, carId).subscribe({
      next: () => {
        this.loadClients();
        this.loadAvailableCars();
        this.carReturned.emit();
      },
      error: err => alert(err.error?.error || 'Nie udało się zwrócić samochodu')
    });
  }

  enableRentMode(clientId: number) {
    this.rentModeClientId = clientId;
    this.selectedCarId = null;
  }

  cancelRent() {
    this.rentModeClientId = null;
    this.selectedCarId = null;
  }

  rentCar(clientId: number) {
  if (!this.selectedCarId) {
    alert('Wybierz samochód!');
    return;
  }

    this.clientService.rentCar(clientId, this.selectedCarId).subscribe({
    next: () => {
      this.cancelRent();
      this.loadClients();
      this.loadAvailableCars();
      this.carRented.emit();
    },
    error: err => alert(err.error?.error || 'Nie udało się wypożyczyć samochodu')
  });
}
}
