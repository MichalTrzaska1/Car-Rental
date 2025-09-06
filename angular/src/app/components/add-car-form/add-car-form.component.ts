import { Component, EventEmitter, Output } from '@angular/core';
import { Car, CarService } from '../../services/car.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
selector: 'add-car-form',
standalone: true,
imports: [CommonModule, FormsModule],
template: `
<h2>Dodaj Samochód</h2>
<form (ngSubmit)="addCar()">
<input [(ngModel)]="brand" name="brand" placeholder="Marka" required />
<input [(ngModel)]="model" name="model" placeholder="Model" required />
<input [(ngModel)]="pricePerDay" name="pricePerDay" type="number" placeholder="Cena za dzień" required />
<button type="submit">Dodaj</button>
</form>
`
})
export class AddCarFormComponent {
@Output() carAdded = new EventEmitter<void>();

brand = '';
model = '';
pricePerDay: string = '';

constructor(private carService: CarService) {}

  addCar() {
    const price = Number(this.pricePerDay);

    if (isNaN(price) || price <= 0) {
      alert('Podaj poprawną cenę za dzień!');
      return;
    }

    const car: Car = {
      brand: this.brand,
      model: this.model,
      pricePerDay: price,
      status: 'AVAILABLE'
    };

    this.carService.addCar(car).subscribe(() => {
      this.brand = '';
      this.model = '';
      this.pricePerDay = '';
      this.carAdded.emit();
    });
  }
}
