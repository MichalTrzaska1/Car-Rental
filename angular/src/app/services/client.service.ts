import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from './car.service';

export interface Client {
id?: number;
firstName: string;
surname: string;
email: string;
rentedCars: Car[];
}

@Injectable({ providedIn: 'root' })
export class ClientService {
private apiUrl = 'http://localhost:8080/clients';

constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  addClient(client: Client): Observable<Client> {
    return this.http.post<Client>(this.apiUrl, client);
  }

returnCar(clientId: number, carId: number): Observable<Client> {
  return this.http.post<Client>(`${this.apiUrl}/${clientId}/return/${carId}`, {});
}

rentCar(clientId: number, carId: number): Observable<Client> {
  return this.http.post<Client>(`${this.apiUrl}/${clientId}/rent/${carId}`, {});
}
}
