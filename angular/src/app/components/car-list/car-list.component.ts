import { Component, OnInit } from '@angular/core';
import { Car, CarService } from '../../services/car.service';
import { CommonModule } from '@angular/common';

@Component({
selector: 'car-list',
standalone: true,
imports: [CommonModule],
template: `
<h2>Lista Samochodów</h2>
<table border="1">
<thead>
<tr>
<th>Marka</th>
<th>Model</th>
<th>Cena za dzień PLN</th>
<th>Status</th>
</tr>
</thead>
<tbody>
<tr *ngFor="let car of cars">
<td>{{ car.brand }}</td>
<td>{{ car.model }}</td>
<td>{{ car.pricePerDay }}</td>
<td>{{ getStatusText(car.status) }}</td>
        </tr>
      </tbody>
    </table>
  `
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];

  constructor(private carService: CarService) {}

  ngOnInit() {
    this.loadCars();
  }

  loadCars() {
  this.carService.getCars().subscribe(data => {
    this.cars = data.sort((a, b) => (a.id! - b.id!));
  });
}


  getStatusText(status: 'AVAILABLE' | 'RENTED' | undefined): string {
    if (!status) return '';
    return status === 'AVAILABLE' ? 'DOSTĘPNY' : 'WYPOŻYCZONY';
  }
}
