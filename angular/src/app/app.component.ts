import { Component, ViewChild } from '@angular/core';
import { CarListComponent } from './components/car-list/car-list.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { AddCarFormComponent } from './components/add-car-form/add-car-form.component';
import { AddClientFormComponent } from './components/add-client-form/add-client-form.component';
import { CommonModule } from '@angular/common';

@Component({
selector: 'app-root',
standalone: true,
imports: [
CommonModule,
CarListComponent,
ClientListComponent,
AddCarFormComponent,
AddClientFormComponent
],
template: `
<div class="centered">
<img src="wypozyczalnia.png" alt="Wypożyczalnia">

<add-car-form (carAdded)="onCarAdded()"></add-car-form>
<add-client-form (clientAdded)="clientList.loadClients()"></add-client-form>

<car-list #carList></car-list>

<client-list
#clientList
(carReturned)="onCarReturned()"
(carRented)="onCarRented()">
</client-list>
</div>
`
})
export class AppComponent {
@ViewChild(CarListComponent) carList!: CarListComponent;
@ViewChild(ClientListComponent) clientList!: ClientListComponent;

onCarAdded() {
    this.carList.loadCars();
    this.clientList.loadClients();
    this.clientList.loadAvailableCars();
  }

onCarRented() {
    this.carList.loadCars();
    this.clientList.loadClients();
  }

  onCarReturned() {
    this.carList.loadCars();
    this.clientList.loadClients();
  }
}
