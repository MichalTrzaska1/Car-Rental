import { Component, EventEmitter, Output } from '@angular/core';
import { Client, ClientService } from '../../services/client.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
selector: 'add-client-form',
standalone: true,
imports: [CommonModule, FormsModule],
template: `
<h2>Dodaj Klienta</h2>
<form (ngSubmit)="addClient()">
<input [(ngModel)]="client.firstName" name="firstName" placeholder="Imię" required />
<input [(ngModel)]="client.surname" name="surname" placeholder="Nazwisko" required />
<input [(ngModel)]="client.email" name="email" placeholder="Email" required />
<button type="submit">Dodaj</button>
</form>
`
})
export class AddClientFormComponent {
@Output() clientAdded = new EventEmitter<void>();

client: Client = { firstName: '', surname: '', email: '', rentedCars: [] };

constructor(private clientService: ClientService) {}

  addClient() {
    this.clientService.addClient(this.client).subscribe(() => {
      this.client = { firstName: '', surname: '', email: '', rentedCars: [] };
      this.clientAdded.emit();
    });
  }
}
