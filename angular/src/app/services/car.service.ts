import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Car {
id?: number;
brand: string;
model: string;
pricePerDay: number;
status: 'AVAILABLE' | 'RENTED';
}

@Injectable({ providedIn: 'root' })
export class CarService {
private apiUrl = 'http://localhost:8080/cars';

constructor(private http: HttpClient) {}

  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.apiUrl);
  }

  addCar(car: Car): Observable<Car> {
    return this.http.post<Car>(this.apiUrl, car);
  }

  updateStatus(id: number, status: 'AVAILABLE' | 'RENTED'): Observable<Car> {
    return this.http.put<Car>(`${this.apiUrl}/${id}/status?status=${status}`, {});
  }
}
